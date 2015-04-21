package uk.co.mindbadger.footballresults.season;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import uk.co.mindbadger.footballresults.table.InitialTable;
import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableFactory;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowFactory;
import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class SeasonLoader {
	/* The SeasonCache can hold multiple seasons - When the object loads, it will always load the latest season
	 * Aim of this component:
	 * 
	 * To get this structure:
	 * 	Map<int, Season> seasons
	 *     !--> Map<String, DivisionContainer> Division container
	 *     		!--> container.getFixtures()
	 *              !--> Map<Date, Fixture> Fixtures on Date
	 *          !--> container.getTables()
	 *              !--> Map<Date, Table> Table on Date
	 * 
	 * * For each division
	 * 	 * ** get all of the fixtures for that division, ordered by date ascending (DAO - getFixturesForDivisionInSeason - NEW!!!) 
	 * 
	 * ** Create a Map of Date, Table
	 * 
	 * ** Create a table for that division (TableFactory - createTableFromFixtures)
	 *     (pass in List of Fixtures and Set of Teams)
	 *    ** Loop through all of the teams, creating an initial table row for that team (TableRowFactory - createInitialTableRowForTeam)
	 *    ** Loop through all of the fixtures, creating a new table row for each fixture (TableRowFactory - createTableRowFromFixture)
	 * * Store all tables in a Map keyed by division
	 * * Store all Fixtures in a Map keyed by  
	 */
	
	private FootballResultsAnalyserDAO<String,String,String> dao;
	private TableRowFactory<String,String,String> tableRowFactory;
	private AnalyserCache analyserCache;
	private TableFactory tableFactory;
	
	// CONSTRUCTOR
	public SeasonLoader () {	}
	
	
	
	/*
	 *  Too complicated to test - too many dependencies
	 *  
	 *  Consider putting everything inside the first for loop inside a "SeasonCacheDivisionLoader"
	 *  -- this will only be concerned with creating the initial date and tables and calling...
	 *  
	 *  Consider putting everything inside the second for loop inside a "SeasonCacheFixtureAndTableLoader"
	 *  -- this will only be concerned with the effect a single transaction has on the cache
	 */
	public void loadSeason(Season<String> season) {
		SeasonCache seasonCache = analyserCache.getCacheForSeason(season.getSeasonNumber());
		
		Set<SeasonDivision<String, String>> seasonDivisions = dao.getDivisionsForSeason(season);
		
		for (SeasonDivision<String, String> seasonDivision : seasonDivisions) {
			
			DivisionCache divisionCache = seasonCache.getCacheForDivision(seasonDivision.getDivision().getDivisionId());
			
			List<Fixture<String>> fixtures = dao.getFixturesForDivisionInSeason(seasonDivision);
			
			Calendar currentDate = createInitialTableDate(season.getSeasonNumber());
			Table<String,String,String> tableForDate = createInitialTable(seasonDivision);
			
			for (Fixture<String> fixture : fixtures) {

				if (fixture.getFixtureDate() != currentDate) {
					if (tableForDate != null) divisionCache.addTableOnDate(currentDate, tableForDate);
					tableForDate = tableFactory.createTableFromPreviousTable(tableForDate);
					
					currentDate = fixture.getFixtureDate();
				}

				divisionCache.addFixtureOnDate(currentDate, fixture);
				
				tableForDate.addRow(createTableRow(fixture.getHomeTeam(), tableForDate, fixture));
				tableForDate.addRow(createTableRow(fixture.getAwayTeam(), tableForDate, fixture));
			}
			
			// Must save the last date
			divisionCache.addTableOnDate(currentDate, tableForDate);
		}
	}

	private TableRow<String, String, String> createTableRow (Team<String> team, Table<String,String,String> table, Fixture<String> fixture) {
		TableRow<String, String, String> previousTableRow = table.getTableRowForTeam(team.getTeamId());
		return  tableRowFactory.createTableRowFromFixture(team, table, previousTableRow , fixture);
	}
	
	private Table<String, String, String> createInitialTable(SeasonDivision<String, String> seasonDivision) {
		Set<SeasonDivisionTeam<String, String, String>> seasonDivisionTeams = dao.getTeamsForDivisionInSeason(seasonDivision);
		Table<String,String,String> initialTable = tableFactory.createInitialTable(seasonDivision, seasonDivisionTeams);
		return initialTable;
	}
	
	private Calendar createInitialTableDate (Integer seasonNumber) {
		Calendar seasonStartDateCalendar = Calendar.getInstance();
		seasonStartDateCalendar.set(Calendar.DAY_OF_MONTH, 0);
		seasonStartDateCalendar.set(Calendar.MONTH, 5);
		seasonStartDateCalendar.set(Calendar.YEAR, seasonNumber);
		return seasonStartDateCalendar;
	}
	
	public FootballResultsAnalyserDAO<String, String, String> getDao() {
		return dao;
	}

	public void setDao(FootballResultsAnalyserDAO<String, String, String> dao) {
		this.dao = dao;
	}

	public Season<String> getCurrentSeason() {
		return null;
	}

	public TableRowFactory<String,String,String> getTableRowFactory() {
		return tableRowFactory;
	}

	public void setTableRowFactory(TableRowFactory<String,String,String> tableRowFactory) {
		this.tableRowFactory = tableRowFactory;
	}

	public AnalyserCache getAnalyserCache() {
		return analyserCache;
	}

	public void setAnalyserCache(AnalyserCache analyserCache) {
		this.analyserCache = analyserCache;
	}

	public TableFactory getTableFactory() {
		return tableFactory;
	}

	public void setTableFactory(TableFactory tableFactory) {
		this.tableFactory = tableFactory;
	}
}
