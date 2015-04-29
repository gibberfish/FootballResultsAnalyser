package uk.co.mindbadger.footballresults.season;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;

public class SeasonCache {
	Logger logger = Logger.getLogger(SeasonCache.class);
	
	private Map<SeasonDivision<String,String>, DivisionCache> divisions = new HashMap<SeasonDivision<String,String>, DivisionCache> ();
	
	public DivisionCache getCacheForDivision (SeasonDivision<String,String> division) {
		DivisionCache existing = divisions.get(division);
		if (existing != null) {
			logger.info("Getting existing cache for division " + division.getDivision().getDivisionName());
			return existing;
		} else {
			logger.info("Creating new cache for division " + division.getDivision().getDivisionName());
			DivisionCache newCache = new DivisionCache();
			divisions.put(division, newCache);
			return newCache;
		}
	}
		
	public Map<SeasonDivision<String,String>, DivisionCache> getDivisionCaches () {
		return divisions;
	}

	public List<SeasonDivision<String,String>> getSeasonDivisionsInCache() {
		Set<SeasonDivision<String,String>> keySet = divisions.keySet();
		
		List<SeasonDivision<String,String>> seasonDivisionList = new ArrayList<SeasonDivision<String,String>> (keySet);
		
		Collections.sort(seasonDivisionList);
		
		return seasonDivisionList;
	}
}
