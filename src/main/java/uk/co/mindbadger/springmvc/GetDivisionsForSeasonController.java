package uk.co.mindbadger.springmvc;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;

@Controller
public class GetDivisionsForSeasonController {
	Logger logger = Logger.getLogger(GetDivisionsForSeasonController.class);

	@Autowired
	FootballResultsAnalyserDAO<String,String,String> dao;
	
	@RequestMapping(value = "/getDivisionsForSeason2.html", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Set<SeasonDivision<String,String>> getDivisionsForSeason(@RequestParam("ssn") int seasonNumber) {
		logger.debug("CONTROLLER: getDivisionsForSeason2: " + seasonNumber);

		Set<SeasonDivision<String,String>> seasonDivisions = null;
		Season<String> season = dao.getSeason(seasonNumber);
		if (season != null) {
			seasonDivisions = dao.getDivisionsForSeason(season);
		} else {
			seasonDivisions = new HashSet<SeasonDivision<String,String>> ();
		}
			
		return seasonDivisions;
	}
}
