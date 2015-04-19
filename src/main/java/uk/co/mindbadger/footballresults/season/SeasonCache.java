package uk.co.mindbadger.footballresults.season;

import java.util.HashMap;
import java.util.Map;

public class SeasonCache {
	private Map<String, DivisionCache> divisions = new HashMap<String, DivisionCache> ();
	
	public DivisionCache getCacheForDivision (String divisionId) {
		DivisionCache existing = divisions.get(divisionId);
		if (existing != null) {
			return existing;
		} else {
			DivisionCache newCache = new DivisionCache();
			divisions.put(divisionId, newCache);
			return newCache;
		}
	}
		
	public Map<String, DivisionCache> getDivisionCaches () {
		return divisions;
	}
}
