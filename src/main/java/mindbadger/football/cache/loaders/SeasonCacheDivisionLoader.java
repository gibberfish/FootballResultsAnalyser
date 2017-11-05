package mindbadger.football.cache.loaders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mindbadger.football.caches.DivisionCache;
import mindbadger.football.caches.SeasonCache;
import mindbadger.football.domain.Fixture;
import mindbadger.football.domain.SeasonDivision;
import mindbadger.football.domain.SeasonDivisionTeam;
import mindbadger.football.repository.FixtureRepository;
import mindbadger.football.table.Table;
import mindbadger.football.table.TableFactory;
import mindbadger.football.table.TeamFixtureContext;
import mindbadger.utils.FixtureDateFormatter;

public class SeasonCacheDivisionLoader {
	Logger logger = Logger.getLogger(SeasonCacheDivisionLoader.class);
	
	private SeasonCacheFixtureAndTableLoader seasonCacheFixtureAndTableLoader;
	private TableFactory tableFactory;
	
	@Autowired
	protected FixtureRepository fixtureRepository;

	public void loadDivision (SeasonDivision seasonDivision, SeasonCache seasonCache) {
		logger.info("Load Division Cache for " + seasonDivision.getDivision().getDivisionName());
		
		DivisionCache divisionCache = seasonCache.getCacheForDivision(seasonDivision);
		
		List<Fixture> fixtures = fixtureRepository.getFixturesForDivisionInSeason(seasonDivision);
		
		// Sort the list in ascending date order
		Collections.sort(fixtures, new Comparator<Fixture>(){
		     public int compare(Fixture o1, Fixture o2){
		         if(o1.getFixtureDate() == o2.getFixtureDate())
		             return 0;
		         return o1.getFixtureDate().before(o2.getFixtureDate()) ? -1 : 1;
		     }
		});
		
		Calendar currentDate = createInitialTableDate(seasonDivision.getSeason().getSeasonNumber());
		Table tableForPreviousDate = null;
		Table tableForCurrentDate = createInitialTable(seasonDivision);
		
		for (Fixture fixture : fixtures) {
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
	
	private Table createInitialTable(SeasonDivision seasonDivision) {
		
		Set<SeasonDivisionTeam> seasonDivisionTeams = seasonDivision.getSeasonDivisionTeams();
		Table initialTable = tableFactory.createInitialTable(seasonDivision, seasonDivisionTeams);
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
}
