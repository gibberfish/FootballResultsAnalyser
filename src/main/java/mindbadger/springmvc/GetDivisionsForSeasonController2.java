package mindbadger.springmvc;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import mindbadger.footballresultsanalyser.domain.Season;
import mindbadger.footballresultsanalyser.domain.SeasonDivision;

@Controller
public class GetDivisionsForSeasonController2 {
	Logger logger = Logger.getLogger(GetDivisionsForSeasonController2.class);

	@Autowired
	FootballResultsAnalyserDAO dao;
	
	@RequestMapping(value = "/getDivisionsForSeason.html", method = RequestMethod.GET)
	public @ResponseBody String getDivisionsForSeason(@RequestParam("ssn") int seasonNumber) {
	//public @ResponseBody Division[] getDivisionsForSeason(@RequestParam("ssn") int seasonNumber) {
		logger.debug("CONTROLLER: getDivisionsForSeason: " + seasonNumber);

		Season season = dao.getSeason(seasonNumber);
		List<SeasonDivision> seasonDivisions = dao.getDivisionsForSeason(season);
		
//		Division[] divisions = new Division[seasonDivisions.size()];
//		for (SeasonDivision seasonDivision : seasonDivisions) {
//		    divisions[seasonDivision.getDivisionPosition()-1] = seasonDivision.getDivision();
//		}

		// CLUNKY APPROACH - need to get Jackson working properly
		String output = "{\"divisions\": [";
//		for (Division division : divisions) {
		for (SeasonDivision seasonDivision : seasonDivisions) {
		    output+="{\"id\": \""+seasonDivision.getDivision().getDivisionId()+"\", \"name\":\""+seasonDivision.getDivision().getDivisionName()+"\"},";
		}
		if (output.length() > 1) {
		    output = output.substring(0, output.length() - 1);
		}
		output+="]}";
		
		logger.debug("++++++ divisions: " + output);
		
		return output;
	}
}
