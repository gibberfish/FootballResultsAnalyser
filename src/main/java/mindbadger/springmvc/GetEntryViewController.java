package mindbadger.springmvc;

import mindbadger.footballresults.season.AnalyserCache;
import mindbadger.footballresults.table.TableShapes;
import mindbadger.footballresults.table.calculation.CalculationMapFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetEntryViewController {
	Logger logger = Logger.getLogger(GetEntryViewController.class);

	@Autowired
	AnalyserCache analyserCache;
	
	@Autowired
	CalculationMapFactory<String, String, String> calculationMapFactory;
	
	@Autowired
	TableShapes tableShapes;
	
	@RequestMapping("/FootballResultsAnalyser.html")
	public ModelAndView getAnalyserCache() {
		logger.debug("CONTROLLER: FootballResultsAnalyser");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("entryView");

		return mav;
	}
}
