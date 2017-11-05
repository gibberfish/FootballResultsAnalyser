package mindbadger.football.table;

import java.util.Set;

import org.apache.log4j.Logger;

import mindbadger.football.domain.SeasonDivision;
import mindbadger.football.domain.SeasonDivisionTeam;

public class InitialTable extends Table {
	Logger logger = Logger.getLogger(InitialTable.class);
	
	public InitialTable (Table previousTable) {
		super(previousTable);
	}

	public InitialTable (SeasonDivision seasonDivision, Set<SeasonDivisionTeam> seasonDivisionTeams) {
		super(null);
		
		logger.info("Creating initial table for season " + seasonDivision.getSeason().getSeasonNumber() + " / " + seasonDivision.getDivision().getDivisionId());
		
		for (SeasonDivisionTeam seasonDivisionTeam : seasonDivisionTeams) {
			TableRow newRow = new InitialTableRow(seasonDivisionTeam.getTeam());
			addRow(newRow);
		}
		
	}
}
