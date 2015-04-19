package uk.co.mindbadger.footballresults.season;

import java.util.HashMap;
import java.util.Map;

public class SeasonCache {
	private Map<String, DivisionCache> divisions = new HashMap<String, DivisionCache> ();
	
	public void addDivisionCache (String divisionId, DivisionCache divisionCache) {
		divisions.put(divisionId, divisionCache);
	}
	
	public Map<String, DivisionCache> getDivisionCaches () {
		return divisions;
	}
}
