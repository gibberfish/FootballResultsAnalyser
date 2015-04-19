package uk.co.mindbadger.footballresults.season;

import java.util.HashMap;
import java.util.Map;

public class AnalyserCache {
	private Map<Integer, SeasonCache> seasons = new HashMap<Integer, SeasonCache> ();
	
	public void addSeasonCache (Integer seasonNumber, SeasonCache seasonCache) {
		seasons.put(seasonNumber, seasonCache);
	}
	
	public Map<Integer, SeasonCache> getSeasonCaches () {
		return seasons;
	}
}
