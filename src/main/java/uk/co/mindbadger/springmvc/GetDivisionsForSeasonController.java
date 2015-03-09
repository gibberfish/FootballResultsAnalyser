package uk.co.mindbadger.springmvc;

import java.util.List;
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
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

@Controller
public class GetDivisionsForSeasonController {
	Logger logger = Logger.getLogger(GetDivisionsForSeasonController.class);

	@Autowired
	FootballResultsAnalyserDAO<String,String,String> dao;
	
	@RequestMapping(value = "/getDivisionsForSeason.html", method = RequestMethod.GET)
	public @ResponseBody String getDivisionsForSeason(@RequestParam("ssn") int seasonNumber) {
	//public @ResponseBody Division[] getDivisionsForSeason(@RequestParam("ssn") int seasonNumber) {
		logger.debug("CONTROLLER: getDivisionsForSeason: " + seasonNumber);

		dao.startSession();
		
		Season<String> season = dao.getSeason(seasonNumber);
		Set<SeasonDivision<String,String>> seasonDivisions = dao.getDivisionsForSeason(season);
		
		Division<String>[] divisions = new Division[seasonDivisions.size()];
		for (SeasonDivision<String,String> seasonDivision : seasonDivisions) {
		    divisions[seasonDivision.getDivisionPosition()-1] = seasonDivision.getDivision();
		}

		//TODO CLUNKY APPROACH - need to get Jackson working properly
		String output = "{\"divisions\": [";
		for (Division<String> division : divisions) {
		    output+="{\"id\":"+division.getDivisionId()+",\"name\":\""+division.getDivisionName()+"\"},";
		}
		if (output.length() > 1) {
		    output = output.substring(0, output.length() - 1);
		}
		output+="]}";
		
		logger.debug("++++++ divisions: " + output);
		
		dao.closeSession();
		
		return output;
	}
}
