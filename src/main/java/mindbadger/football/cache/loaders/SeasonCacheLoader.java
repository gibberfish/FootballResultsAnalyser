package mindbadger.football.cache.loaders;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mindbadger.football.caches.AnalyserCache;
import mindbadger.football.caches.SeasonCache;
import mindbadger.football.domain.Season;
import mindbadger.football.domain.SeasonDivision;
import mindbadger.football.repository.SeasonRepository;

public class SeasonCacheLoader {
	Logger logger = Logger.getLogger(SeasonCacheLoader.class);
	
	@Autowired
	protected SeasonRepository seasonRepository;
	
	private AnalyserCache analyserCache;
	private SeasonCacheDivisionLoader seasonCacheDivisionLoader;
	
	public void loadSeason(Season season) {
		logger.info("Load Season Cache for " + season.getSeasonNumber());
		SeasonCache seasonCache = analyserCache.getCacheForSeason(season.getSeasonNumber());
		
		List<SeasonDivision> seasonDivisions = new ArrayList<SeasonDivision> (season.getSeasonDivisions());
		
		for (SeasonDivision seasonDivision : seasonDivisions) {
			seasonCacheDivisionLoader.loadDivision(seasonDivision, seasonCache);
		}
	}
	
	@PostConstruct
	public void loadCurrentSeason() {
		logger.info("Request to Load Season Cache for current season");
		
		Season latestSeason = null;
		for (Season season : seasonRepository.findAll()) {
			if (latestSeason==null || season.getSeasonNumber() > latestSeason.getSeasonNumber()) {
				latestSeason = season;
			}
		}
		
		// Assumes the seasons are sorted in ascending order
		logger.info("...Loading season: " + latestSeason.getSeasonNumber());
		loadSeason(latestSeason);
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
