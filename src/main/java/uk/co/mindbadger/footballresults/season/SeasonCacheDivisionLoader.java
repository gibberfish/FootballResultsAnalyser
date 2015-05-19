package uk.co.mindbadger.footballresults.season;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableFactory;
import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;
import uk.co.mindbadger.utils.FixtureDateFormatter;

public class SeasonCacheDivisionLoader {
	Logger logger = Logger.getLogger(SeasonCacheDivisionLoader.class);
	
	private SeasonCacheFixtureAndTableLoader seasonCacheFixtureAndTableLoader;
	private TableFactory tableFactory;
	private FootballResultsAnalyserDAO<String,String,String> dao;
	
	public void loadDivision (SeasonDivision<String, String> seasonDivision, SeasonCache seasonCache) {
		logger.info("Load Division Cache for " + seasonDivision.getDivision().getDivisionName());
		
		DivisionCache divisionCache = seasonCache.getCacheForDivision(seasonDivision);
		
		// Assumes fixtures come back in ascending date order
		List<Fixture<String>> fixtures = dao.getFixturesForDivisionInSeason(seasonDivision);
		
		Calendar currentDate = createInitialTableDate(seasonDivision.getSeason().getSeasonNumber());
		Table<String,String,String> tableForPreviousDate = null;
		Table<String,String,String> tableForCurrentDate = createInitialTable(seasonDivision);
		
		for (Fixture<String> fixture : fixtures) {
			logger.info("About to process fixture: " + fixture);
			
			Calendar fixtureDate = fixture.getFixtureDate();
			if (!FixtureDateFormatter.isSameDate(fixtureDate, currentDate)) {
				divisionCache.addTableOnDate(currentDate, tableForCurrentDate);
				tableForPreviousDate = tableForCurrentDate;
				tableForCurrentDate = tableFactory.createTableFromPreviousTable(tableForPreviousDate);
				currentDate = fixtureDate;
			}
			
			TeamFixtureContext homeContext = seasonCacheFixtureAndTableLoader.loadTeamFixtureContextsForTeam(true, fixture, fixtureDate, divisionCache, tableForPreviousDate);
			TeamFixtureContext awayContext = seasonCacheFixtureAndTableLoader.loadTeamFixtureContextsForTeam(false, fixture, fixtureDate, divisionCache, tableForPreviousDate);
			seasonCacheFixtureAndTableLoader.loadFixture(fixture, fixtureDate, divisionCache);
			seasonCacheFixtureAndTableLoader.loadFixtureIntoTable(fixture, tableForCurrentDate, homeContext, awayContext);
		}
		
		// Must save the last date
		divisionCache.addTableOnDate(currentDate, tableForCurrentDate);
	}
	
	protected Calendar createInitialTableDate (Integer seasonNumber) {
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			cal.setTime(sdf.parse(seasonNumber+"-05-01 00:00:00"));
			return cal;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException (e);
		}
	}
	
	private Table<String, String, String> createInitialTable(SeasonDivision<String, String> seasonDivision) {
		Set<SeasonDivisionTeam<String, String, String>> seasonDivisionTeams = dao.getTeamsForDivisionInSeason(seasonDivision);
		Table<String,String,String> initialTable = tableFactory.createInitialTable(seasonDivision, seasonDivisionTeams);
		return initialTable;
	}

	public SeasonCacheFixtureAndTableLoader getSeasonCacheFixtureAndTableLoader() {
		return seasonCacheFixtureAndTableLoader;
	}

	public void setSeasonCacheFixtureAndTableLoader(
			SeasonCacheFixtureAndTableLoader seasonCacheFixtureAndTableLoader) {
		this.seasonCacheFixtureAndTableLoader = seasonCacheFixtureAndTableLoader;
	}
	
	public TableFactory getTableFactory() {
		return tableFactory;
	}

	public void setTableFactory(TableFactory tableFactory) {
		this.tableFactory = tableFactory;
	}
	
	public FootballResultsAnalyserDAO<String, String, String> getDao() {
		return dao;
	}

	public void setDao(FootballResultsAnalyserDAO<String, String, String> dao) {
		this.dao = dao;
	}
}
