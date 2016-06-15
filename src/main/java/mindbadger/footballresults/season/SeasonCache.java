package mindbadger.footballresults.season;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import mindbadger.footballresultsanalyser.domain.SeasonDivision;

public class SeasonCache {
	Logger logger = Logger.getLogger(SeasonCache.class);
	
	private Map<SeasonDivision, DivisionCache> divisions = new HashMap<SeasonDivision, DivisionCache> ();
	
	public DivisionCache getCacheForDivision (SeasonDivision division) {
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
		
	public Map<SeasonDivision, DivisionCache> getDivisionCaches () {
		return divisions;
	}

	public List<SeasonDivision> getSeasonDivisionsInCache() {
		Set<SeasonDivision> keySet = divisions.keySet();
		
		List<SeasonDivision> seasonDivisionList = new ArrayList<SeasonDivision> (keySet);
		
		Collections.sort(seasonDivisionList);
		
		return seasonDivisionList;
	}
}
