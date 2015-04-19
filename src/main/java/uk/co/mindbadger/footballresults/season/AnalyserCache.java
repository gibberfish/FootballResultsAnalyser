package uk.co.mindbadger.footballresults.season;

import java.util.HashMap;
import java.util.Map;

public class AnalyserCache {
	private Map<Integer, SeasonCache> seasons = new HashMap<Integer, SeasonCache> ();
	
	public Map<Integer, SeasonCache> getSeasonCaches () {
		return seasons;
	}

	public SeasonCache getCacheForSeason(Integer seasonNumber) {
		SeasonCache existing = seasons.get(seasonNumber);
		if (existing != null) {
			return existing;
		} else {
			SeasonCache newCache = new SeasonCache();
			seasons.put(seasonNumber, newCache);
			return newCache;
		}
	}
}
