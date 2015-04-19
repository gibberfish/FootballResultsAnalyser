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
	
	
	// CONSTRUCTOR
	public SeasonLoader (FootballResultsAnalyserDAO<String, String, String> dao) {
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
	}
	
	public void loadSeason(Season<String> season) {
		SeasonCache seasonCache = analyserCache.getCacheForSeason(season.getSeasonNumber());
		
		Set<SeasonDivision<String, String>> seasonDivisions = dao.getDivisionsForSeason(season);
		
		for (SeasonDivision<String, String> seasonDivision : seasonDivisions) {
			
			DivisionCache divisionCache = seasonCache.getCacheForDivision(seasonDivision.getDivision().getDivisionId());
			
			Set<SeasonDivisionTeam<String, String, String>> seasonDivisionTeams = dao.getTeamsForDivisionInSeason(seasonDivision);
			
			// Create initial table from the teams
			Table<String,String,String> initialTable = new InitialTable<>(seasonDivision, seasonDivisionTeams);
			Calendar seasonStartDateCalendar = createInitialTableDate(season.getSeasonNumber());
			
			List<Fixture<String>> fixtures = dao.getFixturesForDivisionInSeason(seasonDivision);
			
			Calendar currentDate = seasonStartDateCalendar;
			Table<String,String,String> tableForDate = initialTable;
			
			// Loop through fixtures, accumulating on distinct date
			for (Fixture<String> fixture : fixtures) {
				boolean fixtureDateHasChanged = fixture.getFixtureDate() != currentDate;
				
				if (fixtureDateHasChanged) {
					// Save the data
					if (tableForDate != null) divisionCache.addTableOnDate(currentDate, tableForDate);
					
					// Create new table and fixtures
					tableForDate = new Table<String,String,String> (tableForDate);
					
					// Set the new current date
					currentDate = fixture.getFixtureDate();
				}

				// Add the fixture to the list
				divisionCache.addFixtureOnDate(currentDate, fixture);
				
				// Update the table rows for the home and away teams
				TableRow<String, String, String> previousTableRowForHomeTeam = tableForDate.getTableRowForTeam(fixture.getHomeTeam().getTeamId());
				TableRow<String, String, String> homeTeamTableRow = tableRowFactory.createTableRowFromFixture(fixture.getHomeTeam(), tableForDate, previousTableRowForHomeTeam , fixture);
				tableForDate.addRow(homeTeamTableRow);
				
				TableRow<String, String, String> previousTableRowForAwayTeam = tableForDate.getTableRowForTeam(fixture.getAwayTeam().getTeamId());
				TableRow<String, String, String> awayTeamTableRow = tableRowFactory.createTableRowFromFixture(fixture.getAwayTeam(), tableForDate, previousTableRowForAwayTeam, fixture);
				tableForDate.addRow(awayTeamTableRow);
			}
		}
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
}
