package uk.co.mindbadger.footballresults.table;

import java.util.Set;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class TableFactory {
	Logger logger = Logger.getLogger(TableFactory.class);
	public Table<String,String,String> createInitialTable (SeasonDivision<String, String> seasonDivision, Set<SeasonDivisionTeam<String, String, String>> seasonDivisionTeams) {
		logger.debug("Creating an Initial Table for " + seasonDivision.getSeason().getSeasonNumber() + " / " + seasonDivision.getDivision().getDivisionId());
		return new InitialTable<String,String,String>(seasonDivision, seasonDivisionTeams);
	}
	
	public Table<String,String,String> createTableFromPreviousTable(Table<String,String,String> previousTable) {
		logger.debug("Creating a table from a previous table");
		return new Table<String,String,String> (previousTable);
	}
}
