package mindbadger.footballresults.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import mindbadger.footballresultsanalyser.domain.Season;

@Component
@Path("/getSeasons")
public class GetSeasonsJaxRs {
	Logger logger = Logger.getLogger(GetSeasonsJaxRs.class);

	@Autowired
	FootballResultsAnalyserDAO dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Season> getSeasons() {
		logger.error("CONTROLLER: getSeasons");
		List<Season> seasons = dao.getSeasons();
		return seasons;
	}	
}
