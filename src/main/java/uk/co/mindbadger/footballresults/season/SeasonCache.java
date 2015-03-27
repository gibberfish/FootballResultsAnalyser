package uk.co.mindbadger.footballresults.season;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;

public class SeasonCache {
	/*
	 * Aim of this component:
	 * 
	 * * Get the latest season by default (DAO - getSeasons (ordered), get last row)
	 * * Get all divisions for the season (DAO - getDivisionsForSeason)
	 * * For each division
	 * ** get all of the teams for the division(DAO - getTeamsForDivisionInSeason)
	 * ** get all of the fixtures for that division, ordered by date ascending (DAO - getFixturesForDivisionInSeason - NEW!!!) 
	 * ** Create a table for that division (TableFactory - createTableFromFixtures)
	 *     (pass in List of Fixtures and Set of Teams)
	 *    ** Loop through all of the teams, creating an initial table row for that team (TableRowFactory - createInitialTableRowForTeam)
	 *    ** Loop through all of the fixtures, creating a new table row for each fixture (TableRowFactory - createTableRowFromFixture)
	 * * Store all tables in a Map keyed by division
	 * * Store all Fixtures in a Map keyed by  
	 * 
	 * * 
	 * *  
	 */
	
	private FootballResultsAnalyserDAO<String,String,String> dao;
	
	
	

	public FootballResultsAnalyserDAO<String, String, String> getDao() {
		return dao;
	}

	public void setDao(FootballResultsAnalyserDAO<String, String, String> dao) {
		this.dao = dao;
	}

	public Season<String> getSeason() {
		return null;
	}

	public void loadLatestSeason() {
	}

	
}
