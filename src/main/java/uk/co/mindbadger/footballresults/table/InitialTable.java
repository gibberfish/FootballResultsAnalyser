package uk.co.mindbadger.footballresults.table;

import java.util.List;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class InitialTable extends Table {
	Logger logger = Logger.getLogger(InitialTable.class);
	
	public InitialTable (Table previousTable) {
		super(previousTable);
	}

	public InitialTable (SeasonDivision seasonDivision, List<SeasonDivisionTeam> seasonDivisionTeams) {
		super(null);
		
		logger.info("Creating initial table for season " + seasonDivision.getSeason().getSeasonNumber() + " / " + seasonDivision.getDivision().getDivisionId());
		
		for (SeasonDivisionTeam seasonDivisionTeam : seasonDivisionTeams) {
			TableRow newRow = new InitialTableRow(seasonDivisionTeam.getTeam());
			addRow(newRow);
		}
		
	}
}
