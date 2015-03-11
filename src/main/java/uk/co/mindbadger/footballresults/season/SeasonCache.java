package uk.co.mindbadger.footballresults.season;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;

public class SeasonCache {
	/*
	 * Aim of this component:
	 * 
	 * * Get the latest season by default
	 * * Get all fixtures for the season
	 * * Get all divisions for the season
	 * * For each division, get all of the teams for the season
	 * * Create a dummy table for 
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
