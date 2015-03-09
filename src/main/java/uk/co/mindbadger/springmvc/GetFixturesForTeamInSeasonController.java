package uk.co.mindbadger.springmvc;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

@Controller
public class GetFixturesForTeamInSeasonController {
	Logger logger = Logger.getLogger(GetFixturesForTeamInSeasonController.class);

	@Autowired
	FootballResultsAnalyserDAO<String,String,String> dao;
	
	@RequestMapping(value = "/getFixturesForTeamInSeason.html", method = RequestMethod.GET)
	public @ResponseBody String getTeamsForDivision(@RequestParam("ssn") int seasonNumber, @RequestParam("div") String divisionId, @RequestParam("team") String teamId) {
		logger.debug("CONTROLLER: getFixturesForTeamInSeason: " + seasonNumber + ", " + divisionId + ", " + teamId);

		dao.startSession();
		Season<String> season = dao.getSeason(seasonNumber);
		Division<String> division = dao.getDivision(divisionId);
		Team<String> team = dao.getTeam(teamId);
		
		List<Fixture<String>> fixtures = dao.getFixturesForTeamInDivisionInSeason(season, division, team);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		//TODO CLUNKY APPROACH - need to get Jackson working properly
		String output = "{\"fixtures\": [";
		for (Fixture<String> fixture : fixtures) {
		    String fixtureDate = dateFormat.format(fixture.getFixtureDate().getTime());
		    Integer homeGoals = fixture.getHomeGoals();
		    Integer awayGoals = fixture.getAwayGoals();
		    String score;
		    if (homeGoals == null || awayGoals == null) {
			score = "-";
		    } else {
			score = homeGoals+"-"+awayGoals;
		    }
		    
		    output+="{\"id\":\""+ fixture.getFixtureId()+"\""+
			    ",\"date\":\""+fixtureDate+"\""+
			    ",\"div\":\""+fixture.getDivision().getDivisionName()+"\""+
			    ",\"homeTeam\":\""+fixture.getHomeTeam().getTeamName()+"\""+
			    ",\"awayTeam\":\""+fixture.getAwayTeam().getTeamName()+"\""+
			    ",\"score\":\""+score+"\""+
			    "},";
		}
		if (output.length() > 1) {
		    output = output.substring(0, output.length() - 1);
		}
		output+="]}";
		
		logger.debug("++++++ fixtures: " + output);
		
		dao.closeSession();
		
		return output;
	}
}
