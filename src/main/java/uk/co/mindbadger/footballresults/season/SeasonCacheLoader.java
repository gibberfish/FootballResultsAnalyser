package uk.co.mindbadger.footballresults.season;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;

public class SeasonCacheLoader {
	Logger logger = Logger.getLogger(SeasonCacheLoader.class);
	
	private FootballResultsAnalyserDAO<String,String,String> dao;
	private AnalyserCache analyserCache;
	private SeasonCacheDivisionLoader seasonCacheDivisionLoader;
	
	public void loadSeason(Season<String> season) {
		logger.info("Load Season Cache for " + season.getSeasonNumber());
		SeasonCache seasonCache = analyserCache.getCacheForSeason(season.getSeasonNumber());
		
		Set<SeasonDivision<String, String>> seasonDivisions = dao.getDivisionsForSeason(season);
		
		for (SeasonDivision<String, String> seasonDivision : seasonDivisions) {
			seasonCacheDivisionLoader.loadDivision(seasonDivision, seasonCache);
		}
	}
	
	@PostConstruct
	public void loadCurrentSeason() {
		List<Season<String>> seasons = dao.getSeasons();
		// Assumes the seasons are sorted in descending order
		loadSeason(seasons.get(0));
	}
	
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
