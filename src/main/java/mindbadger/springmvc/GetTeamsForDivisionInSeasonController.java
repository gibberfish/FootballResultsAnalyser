package mindbadger.springmvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import mindbadger.footballresultsanalyser.domain.Division;
import mindbadger.footballresultsanalyser.domain.Season;
import mindbadger.footballresultsanalyser.domain.SeasonDivision;
import mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

@Controller
public class GetTeamsForDivisionInSeasonController {
	Logger logger = Logger.getLogger(GetTeamsForDivisionInSeasonController.class);

	@Autowired
	FootballResultsAnalyserDAO dao;
	
	@RequestMapping(value = "/getTeamsForDivision.html", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<SeasonDivisionTeam> getTeamsForDivision(@RequestParam("ssn") int seasonNumber, @RequestParam("div") String divisionId) {
		logger.debug("CONTROLLER: getTeamsForDivision: " + seasonNumber + ", " + divisionId);

		Season season = dao.getSeason(seasonNumber);
		Division division = dao.getDivision(divisionId);
		SeasonDivision seasonDivision = null;
		if (season != null && division != null) {
			seasonDivision = dao.getSeasonDivision(season, division);
		}
		
		List<SeasonDivisionTeam> seasonDivisionTeams = null;
		if (seasonDivision != null) {
			seasonDivisionTeams = dao.getTeamsForDivisionInSeason(seasonDivision);
		} else {
			seasonDivisionTeams = new ArrayList<SeasonDivisionTeam> ();
		}
		
		return seasonDivisionTeams;
	}
}
