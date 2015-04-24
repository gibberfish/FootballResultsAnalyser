package uk.co.mindbadger.footballresults.season;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class AnalyserCache {
	Logger logger = Logger.getLogger(AnalyserCache.class);
	
	private Map<Integer, SeasonCache> seasons = new HashMap<Integer, SeasonCache> ();
	
	public Map<Integer, SeasonCache> getSeasonCaches () {
		return seasons;
	}

	public SeasonCache getCacheForSeason(Integer seasonNumber) {
		SeasonCache existing = seasons.get(seasonNumber);
		if (existing != null) {
			logger.info("Getting existing cache for season " + seasonNumber);
			return existing;
		} else {
			logger.info("Creating new cache for season " + seasonNumber);
			SeasonCache newCache = new SeasonCache();
			seasons.put(seasonNumber, newCache);
			return newCache;
		}
	}
}
