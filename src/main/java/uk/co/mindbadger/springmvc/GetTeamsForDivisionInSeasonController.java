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
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

@Controller
public class GetTeamsForDivisionInSeasonController {
	Logger logger = Logger.getLogger(GetTeamsForDivisionInSeasonController.class);

	@Autowired
	FootballResultsAnalyserDAO<String,String,String> dao;
	
	@RequestMapping(value = "/getTeamsForDivision.html", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Set<SeasonDivisionTeam<String,String,String>> getTeamsForDivision(@RequestParam("ssn") int seasonNumber, @RequestParam("div") String divisionId) {
		logger.debug("CONTROLLER: getTeamsForDivision: " + seasonNumber + ", " + divisionId);

		Season<String> season = dao.getSeason(seasonNumber);
		Division<String> division = dao.getDivision(divisionId);
		SeasonDivision<String,String> seasonDivision = null;
		if (season != null && division != null) {
			seasonDivision = dao.getSeasonDivision(season, division);
		}
		
		Set<SeasonDivisionTeam<String,String,String>> seasonDivisionTeams = null;
		if (seasonDivision != null) {
			seasonDivisionTeams = dao.getTeamsForDivisionInSeason(seasonDivision);
		} else {
			seasonDivisionTeams = new HashSet<SeasonDivisionTeam<String,String,String>> ();
		}
		
		return seasonDivisionTeams;
	}
}
