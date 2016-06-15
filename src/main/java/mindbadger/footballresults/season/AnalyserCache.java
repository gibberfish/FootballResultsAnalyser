package mindbadger.footballresults.season;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public List<Integer> getSeasonsInCache() {
		Set<Integer> seasonSet = seasons.keySet();
		
		List<Integer> seasonList = new ArrayList<Integer> (seasonSet);
		
		Collections.sort(seasonList, Collections.reverseOrder());
		
		return seasonList;
	}
}
