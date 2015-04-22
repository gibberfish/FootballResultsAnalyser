package uk.co.mindbadger.footballresults.season;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableFactory;
import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class SeasonCacheDivisionLoader {
	private SeasonCacheFixtureAndTableLoader seasonCacheFixtureAndTableLoader;
	private TableFactory tableFactory;
	private FootballResultsAnalyserDAO<String,String,String> dao;
	
	public void loadDivision (SeasonDivision<String, String> seasonDivision, SeasonCache seasonCache) {
		DivisionCache divisionCache = seasonCache.getCacheForDivision(seasonDivision.getDivision().getDivisionId());
		
		// Assumes fixtures come back in ascending date order
		List<Fixture<String>> fixtures = dao.getFixturesForDivisionInSeason(seasonDivision);
		
		Calendar currentDate = createInitialTableDate(seasonDivision.getSeason().getSeasonNumber());
		Table<String,String,String> tableForDate = createInitialTable(seasonDivision);
		
		for (Fixture<String> fixture : fixtures) {
			tableForDate = seasonCacheFixtureAndTableLoader.loadFixtureAndTable(fixture, currentDate, divisionCache, tableForDate);
			currentDate = fixture.getFixtureDate();
		}
		
		// Must save the last date
		divisionCache.addTableOnDate(currentDate, tableForDate);
	}
	
	private Calendar createInitialTableDate (Integer seasonNumber) {
		Calendar seasonStartDateCalendar = Calendar.getInstance();
		seasonStartDateCalendar.set(Calendar.DAY_OF_MONTH, 0);
		seasonStartDateCalendar.set(Calendar.MONTH, 5);
		seasonStartDateCalendar.set(Calendar.YEAR, seasonNumber);
		return seasonStartDateCalendar;
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
