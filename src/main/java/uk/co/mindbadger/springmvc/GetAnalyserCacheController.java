package uk.co.mindbadger.springmvc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import uk.co.mindbadger.footballresults.season.AnalyserCache;

@Controller
public class GetAnalyserCacheController {
	Logger logger = Logger.getLogger(GetAnalyserCacheController.class);

	@Autowired
	AnalyserCache analyserCache;
	
	@RequestMapping("/getAnalyserCacheController.html")
	public ModelAndView getAnalyserCache() {
		logger.debug("CONTROLLER: get analyser cache");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("analyserCache", analyserCache);
		
		mav.setViewName("analyserCacheController");

		return mav;
	}
}
