package uk.co.mindbadger.footballresults.season;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.mindbadger.footballresults.table.InitialTable;
import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowFactory;
import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class SeasonCache {
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
	private class SeasonContainer {
		protected Map<String, DivisionContainer> divisions = new HashMap<String, DivisionContainer> ();
	}
	
	private class DivisionContainer {
		protected Map<Calendar, List<Fixture<String>>> fixtures = new HashMap<Calendar, List<Fixture<String>>> ();
		protected Map<Calendar, Table<String,String,String>> tables = new HashMap<Calendar, Table<String,String,String>> ();
	}
	
	private Map<Integer, SeasonContainer> seasons = new HashMap<Integer, SeasonContainer> ();
	
	private FootballResultsAnalyserDAO<String,String,String> dao;
	private TableRowFactory<String,String,String> tableRowFactory;
	
	
	// CONSTRUCTOR
	public SeasonCache (FootballResultsAnalyserDAO<String, String, String> dao) {
		this.dao = dao;
		
		if (dao == null) {
			throw new IllegalStateException("Please supply a valid DAO");
		}
		
		List<Season<String>> seasons = dao.getSeasons();
		
		if (seasons.size() == 0) {
			throw new IllegalStateException("There is no season data available.");
		}
		
		// Assumes the seasons are in descending order
		Season<String> latestSeason = seasons.get(0);
		
		loadSeason(latestSeason);
		
		// Get the latest season (DAO - getSeasons (ordered), get last row)
		//   create a new SeasonContainer for this season and add it to the seasons map		
		// Get all divisions for the season (DAO - getDivisionsForSeason)
		
		// Loop through each division
	
		// Create a new DivisionContainer for this division and add it to the divisions map in the season container
		
		// Create an initial table
		//     get all of the teams for the division(DAO - getTeamsForDivisionInSeason)
		//     (TableFactory - createInitialTable)
		//     add to the tables map for date 1st July
		
		// get all of the fixtures for that division, ordered by date ascending (DAO - getFixturesForDivisionInSeason - NEW!!!)
		
		// Loop through each fixture
		//   get the date
		//   if the date has changed then
		//        create a new fixtures and tables row for the new date
		//  
		//   add the fixture to the fixtures map
		//   create a new Table from the fixtures and add this to the tables map
		
	}
	
	public void loadSeason(Season<String> season) {
		SeasonContainer seasonContainer = new SeasonContainer ();
		seasons.put(season.getSeasonNumber(), seasonContainer);
		
		Set<SeasonDivision<String, String>> seasonDivisions = dao.getDivisionsForSeason(season);
		
		for (SeasonDivision<String, String> seasonDivision : seasonDivisions) {
			
			DivisionContainer divisionContainer = new DivisionContainer();
			seasonContainer.divisions.put(seasonDivision.getDivision().getDivisionId(), divisionContainer);
			
			Set<SeasonDivisionTeam<String, String, String>> seasonDivisionTeams = dao.getTeamsForDivisionInSeason(seasonDivision);
			
			// Create initial table from the teams
			Table<String,String,String> initialTable = new InitialTable<>(seasonDivision, seasonDivisionTeams);
			Calendar seasonStartDateCalendar = Calendar.getInstance();
			seasonStartDateCalendar.set(Calendar.DAY_OF_MONTH, 0);
			seasonStartDateCalendar.set(Calendar.MONTH, 5);
			seasonStartDateCalendar.set(Calendar.YEAR, season.getSeasonNumber());
			//divisionContainer.tables.put(seasonStartDateCalendar, initialTable);
			
			List<Fixture<String>> fixtures = dao.getFixturesForDivisionInSeason(seasonDivision);
			
			Calendar currentDate = seasonStartDateCalendar;
			List<Fixture<String>> fixturesForDate = null;
			Table<String,String,String> tableForDate = initialTable;
			
			// Loop through fixtures, accumulating on distinct date
			for (Fixture<String> fixture : fixtures) {
				if (fixture.getFixtureDate() != currentDate) {
					// Save the data
					if (tableForDate != null) divisionContainer.tables.put(currentDate, tableForDate);
					if (fixturesForDate != null) divisionContainer.fixtures.put(currentDate, fixturesForDate);
					
					// NEED A FACTORY FOR AN INITIAL TABLE BASED UPON A PREVIOUS ONE
					//tableForDate = new Table<String,String,String> ();
					fixturesForDate = new ArrayList<Fixture<String>> ();
					
					// Set the new current date
					currentDate = fixture.getFixtureDate();
				}
				
				fixturesForDate.add(fixture);
				
				TableRow<String, String, String> previousTableRow = null;
				// If we have access to a previous table, then we should be able to get the row for a specific team from that table.
				// Or if a new table is initialise from the previous one, then we should simply be able to update each row.
				tableRowFactory.createTableRowFromFixture(fixture.getHomeTeam(), tableForDate, previousTableRow , fixture);
				tableRowFactory.createTableRowFromFixture(fixture.getAwayTeam(), tableForDate, previousTableRow, fixture);
				//fixture.g
			}
		}
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
}
