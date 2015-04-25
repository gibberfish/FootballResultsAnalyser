package uk.co.mindbadger.footballresults.season;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class SeasonCache {
	Logger logger = Logger.getLogger(SeasonCache.class);
	
	private Map<String, DivisionCache> divisions = new HashMap<String, DivisionCache> ();
	
	public DivisionCache getCacheForDivision (String divisionId) {
		DivisionCache existing = divisions.get(divisionId);
		if (existing != null) {
			logger.info("Getting existing cache for division ID " + divisionId);
			return existing;
		} else {
			logger.info("Creating new cache for division ID " + divisionId);
			DivisionCache newCache = new DivisionCache();
			divisions.put(divisionId, newCache);
			return newCache;
		}
	}
		
	public Map<String, DivisionCache> getDivisionCaches () {
		return divisions;
	}
}
