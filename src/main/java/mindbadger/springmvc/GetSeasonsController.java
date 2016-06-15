package mindbadger.springmvc;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import mindbadger.footballresultsanalyser.domain.Season;

@Controller
public class GetSeasonsController {
	Logger logger = Logger.getLogger(GetSeasonsController.class);

	@Autowired
	FootballResultsAnalyserDAO dao;
	
	@RequestMapping(value = "/getSeasons.html", method = RequestMethod.GET)
	public @ResponseBody List<Season> getSeasons() {
		logger.debug("CONTROLLER: getSeasons");
		List<Season> seasons = dao.getSeasons();
		return seasons;
	}
}
