package uk.co.mindbadger.springmvc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;

@Controller
public class GetDivisionsForSeasonController {
	Logger logger = Logger.getLogger(GetDivisionsForSeasonController.class);

	@Autowired
	FootballResultsAnalyserDAO dao;
	
	@RequestMapping(value = "/getDivisionsForSeason2.html", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<SeasonDivision> getDivisionsForSeason(@RequestParam("ssn") int seasonNumber) {
		logger.debug("CONTROLLER: getDivisionsForSeason2: " + seasonNumber);

		List<SeasonDivision> seasonDivisions = null;
		Season season = dao.getSeason(seasonNumber);
		if (season != null) {
			seasonDivisions = dao.getDivisionsForSeason(season);
		} else {
			seasonDivisions = new ArrayList<SeasonDivision> ();
		}
			
		return seasonDivisions;
	}
}
