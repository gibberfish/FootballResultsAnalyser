package uk.co.mindbadger.springmvc;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.hibernate.Tester;

@Controller
public class FixturesForTeamController {
	Logger logger = Logger.getLogger(FixturesForTeamController.class);

	@Autowired
	FootballResultsAnalyserDAO dao;
	
	@RequestMapping("/fixturesForTeamController.html")
	public ModelAndView fixturesForTeam() {
		logger.debug("CONTROLLER: fixtures for team");

		dao.startSession();
		
		//Tester.main(null);
		List<Season> seasons = dao.getSeasons();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("seasons", seasons);
		
		mav.setViewName("fixturesForTeamController");

		dao.closeSession();
		
		return mav;
	}
}
