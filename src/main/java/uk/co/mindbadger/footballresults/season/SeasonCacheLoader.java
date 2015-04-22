package uk.co.mindbadger.footballresults.season;

import java.util.Set;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;

public class SeasonCacheLoader {
	private FootballResultsAnalyserDAO<String,String,String> dao;
	private AnalyserCache analyserCache;
	private SeasonCacheDivisionLoader seasonCacheDivisionLoader;
	
	public void loadSeason(Season<String> season) {
		SeasonCache seasonCache = analyserCache.getCacheForSeason(season.getSeasonNumber());
		
		Set<SeasonDivision<String, String>> seasonDivisions = dao.getDivisionsForSeason(season);
		
		for (SeasonDivision<String, String> seasonDivision : seasonDivisions) {
			seasonCacheDivisionLoader.loadDivision(seasonDivision, seasonCache);
		}
	}
	
//	public Season<String> getCurrentSeason() {
//		return null;
//	}

	public FootballResultsAnalyserDAO<String, String, String> getDao() {
		return dao;
	}
	
	public void setDao(FootballResultsAnalyserDAO<String, String, String> dao) {
		this.dao = dao;
	}
	
	public AnalyserCache getAnalyserCache() {
		return analyserCache;
	}

	public void setAnalyserCache(AnalyserCache analyserCache) {
		this.analyserCache = analyserCache;
	}

	public SeasonCacheDivisionLoader getSeasonCacheDivisionLoader() {
		return seasonCacheDivisionLoader;
	}

	public void setSeasonCacheDivisionLoader(SeasonCacheDivisionLoader seasonCacheDivisionLoader) {
		this.seasonCacheDivisionLoader = seasonCacheDivisionLoader;
	}
}
