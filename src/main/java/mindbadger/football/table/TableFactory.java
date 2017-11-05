package mindbadger.football.table;

import java.util.Set;

import org.apache.log4j.Logger;

import mindbadger.football.domain.SeasonDivision;
import mindbadger.football.domain.SeasonDivisionTeam;

public class TableFactory {
	Logger logger = Logger.getLogger(TableFactory.class);
	public Table createInitialTable (SeasonDivision seasonDivision, Set<SeasonDivisionTeam> seasonDivisionTeams) {
		logger.debug("Creating an Initial Table for " + seasonDivision.getSeason().getSeasonNumber() + " / " + seasonDivision.getDivision().getDivisionId());
		return new InitialTable(seasonDivision, seasonDivisionTeams);
	}
	
	public Table createTableFromPreviousTable(Table previousTable) {
		logger.debug("Creating a table from a previous table");
		return new Table (previousTable);
	}
}
