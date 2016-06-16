package mindbadger.footballresults.api;

import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import mindbadger.footballresultsanalyser.domain.Division;
import mindbadger.footballresultsanalyser.domain.Season;
import mindbadger.footballresultsanalyser.domain.SeasonDivision;

@Component
@Path("/seasons")
public class SeasonsAPI {
	Logger logger = Logger.getLogger(SeasonsAPI.class);

	@Autowired
	FootballResultsAnalyserDAO dao;
	
	@GET
	@ApiOperation(
			value = "Lists all seasons", 
		    notes = "Lists all seasons"
		    )
	@ApiResponses(value= {
		    @ApiResponse(code = 200, message = "Successful retrieval of seasons"),
		    @ApiResponse(code = 404, message = "No seasons found"),
		    @ApiResponse(code = 500, message = "Internal server error")
		    })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Season> getSeasons() {
		logger.error("CONTROLLER: getSeasons");
		List<Season> seasons = dao.getSeasons();
		return seasons;
	}
	
	@GET
	@Path("/{seasonNumber}/divisions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDivisionsForSeason (@PathParam("seasonNumber") int seasonNumber) {
		List<SeasonDivision> seasonDivisions = null;
		Season season = dao.getSeason(seasonNumber);
		if (season != null) {
			seasonDivisions = dao.getDivisionsForSeason(season);
		}
		
		if (seasonDivisions == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Season not found").build();
		}
		
		Map<Integer, Division> divisions = new TreeMap<Integer, Division> ();
		for (SeasonDivision seasonDivision : seasonDivisions) {
			divisions.put(seasonDivision.getDivisionPosition(), seasonDivision.getDivision());
		}
		
		return Response.ok(divisions.values()).build();
	}
	
}
