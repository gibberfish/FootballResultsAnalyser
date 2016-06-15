package mindbadger.footballresults.season;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;

import mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import mindbadger.footballresultsanalyser.domain.Season;
import mindbadger.footballresultsanalyser.domain.SeasonDivision;

public class SeasonCacheLoader {
	Logger logger = Logger.getLogger(SeasonCacheLoader.class);
	
	private FootballResultsAnalyserDAO dao;
	private AnalyserCache analyserCache;
	private SeasonCacheDivisionLoader seasonCacheDivisionLoader;
	
	public void loadSeason(Season season) {
		logger.info("Load Season Cache for " + season.getSeasonNumber());
		SeasonCache seasonCache = analyserCache.getCacheForSeason(season.getSeasonNumber());
		
		List<SeasonDivision> seasonDivisions = dao.getDivisionsForSeason(season);
		
		for (SeasonDivision seasonDivision : seasonDivisions) {
			seasonCacheDivisionLoader.loadDivision(seasonDivision, seasonCache);
		}
	}
	
	@PostConstruct
	public void loadCurrentSeason() {
		logger.info("Request to Load Season Cache for current season");
		
		dao.startSession();
		List<Season> seasons = dao.getSeasons();
		logger.debug("...Found " + seasons.size() + " seasons");
		
		// Assumes the seasons are sorted in ascending order
		loadSeason(seasons.get(seasons.size()-1));
	}
	
	@PreDestroy
	public void closeDatabaseConnection () {
		if (dao != null) {
			dao.closeSession();
		}
	}
	
	public FootballResultsAnalyserDAO getDao() {
		return dao;
	}
	
	public void setDao(FootballResultsAnalyserDAO dao) {
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
