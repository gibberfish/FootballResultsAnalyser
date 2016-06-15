package mindbadger.springmvc;

import java.text.SimpleDateFormat;
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
import mindbadger.footballresultsanalyser.domain.Fixture;
import mindbadger.footballresultsanalyser.domain.Season;
import mindbadger.footballresultsanalyser.domain.SeasonDivision;
import mindbadger.footballresultsanalyser.domain.Team;

@Controller
public class GetFixturesForTeamInSeasonController {
	Logger logger = Logger.getLogger(GetFixturesForTeamInSeasonController.class);

	@Autowired
	FootballResultsAnalyserDAO dao;
	
	@RequestMapping(value = "/getFixturesForTeamInSeason.html", method = RequestMethod.GET)
	public @ResponseBody String getTeamsForDivision(@RequestParam("ssn") int seasonNumber, @RequestParam("div") String divisionId, @RequestParam("team") String teamId) {
		logger.debug("CONTROLLER: getFixturesForTeamInSeason: " + seasonNumber + ", " + divisionId + ", " + teamId);

		Season season = dao.getSeason(seasonNumber);
		Division division = dao.getDivision(divisionId);
		Team team = dao.getTeam(teamId);
		
		SeasonDivision seasonDivision = dao.getSeasonDivision(season, division);
		List<Fixture> fixtures = dao.getFixturesForTeamInDivisionInSeason(seasonDivision, team);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		//CLUNKY APPROACH - need to get Jackson working properly
		String output = "{\"fixtures\": [";
		for (Fixture fixture : fixtures) {
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
		
		return output;
	}
}
