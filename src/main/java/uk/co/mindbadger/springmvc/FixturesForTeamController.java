package uk.co.mindbadger.springmvc;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FixturesForTeamController {
	Logger logger = Logger.getLogger(FixturesForTeamController.class);

	@RequestMapping("/fixturesForTeamController.html")
	public ModelAndView fixturesForTeam() {
		logger.debug("CONTROLLER: fixtures for team");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("fixturesForTeamController");

		return mav;
	}
}
