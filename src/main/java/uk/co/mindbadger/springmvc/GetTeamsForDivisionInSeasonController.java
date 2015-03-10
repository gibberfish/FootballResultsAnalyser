package uk.co.mindbadger.springmvc;

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
	
	@RequestMapping(value = "/getTeamsForDivision.html", method = RequestMethod.GET)
	public @ResponseBody String getTeamsForDivision(@RequestParam("ssn") int seasonNumber, @RequestParam("div") String divisionId) {
		logger.debug("CONTROLLER: getTeamsForDivision: " + seasonNumber + ", " + divisionId);

		Season<String> season = dao.getSeason(seasonNumber);
		Division<String> division = dao.getDivision(divisionId);
		SeasonDivision<String,String> seasonDivision = dao.getSeasonDivision(season, division);
		
		Set<SeasonDivisionTeam<String,String,String>> seasonDivisionTeams = dao.getTeamsForDivisionInSeason(seasonDivision);
		
		//TODO CLUNKY APPROACH - need to get Jackson working properly
		String output = "{\"teams\": [";
		for (SeasonDivisionTeam<String,String,String> seasonDivisionTeam : seasonDivisionTeams) {
		    output+="{\"id\":"+ seasonDivisionTeam.getTeam().getTeamId()+",\"name\":\""+seasonDivisionTeam.getTeam().getTeamName()+"\"},";
		}
		if (output.length() > 1) {
		    output = output.substring(0, output.length() - 1);
		}
		output+="]}";
		
		logger.debug("++++++ teams: " + output);
		
		return output;
	}
}
