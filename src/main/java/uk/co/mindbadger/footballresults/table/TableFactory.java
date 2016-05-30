package uk.co.mindbadger.footballresults.table;

import java.util.List;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class TableFactory {
	Logger logger = Logger.getLogger(TableFactory.class);
	public Table createInitialTable (SeasonDivision seasonDivision, List<SeasonDivisionTeam> seasonDivisionTeams) {
		logger.debug("Creating an Initial Table for " + seasonDivision.getSeason().getSeasonNumber() + " / " + seasonDivision.getDivision().getDivisionId());
		return new InitialTable(seasonDivision, seasonDivisionTeams);
	}
	
	public Table createTableFromPreviousTable(Table previousTable) {
		logger.debug("Creating a table from a previous table");
		return new Table (previousTable);
	}
}
