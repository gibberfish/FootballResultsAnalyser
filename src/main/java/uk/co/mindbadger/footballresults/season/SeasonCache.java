package uk.co.mindbadger.footballresults.season;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresultsanalyser.domain.Division;

public class SeasonCache {
	Logger logger = Logger.getLogger(SeasonCache.class);
	
	private Map<Division<String>, DivisionCache> divisions = new HashMap<Division<String>, DivisionCache> ();
	
	public DivisionCache getCacheForDivision (Division<String> division) {
		DivisionCache existing = divisions.get(division);
		if (existing != null) {
			logger.info("Getting existing cache for division ID " + division.getDivisionName());
			return existing;
		} else {
			logger.info("Creating new cache for division ID " + division.getDivisionName());
			DivisionCache newCache = new DivisionCache();
			divisions.put(division, newCache);
			return newCache;
		}
	}
		
	public Map<Division<String>, DivisionCache> getDivisionCaches () {
		return divisions;
	}
}
