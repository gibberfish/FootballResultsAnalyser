package uk.co.mindbadger.footballresults.table;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class InitialTable extends Table {
	private SeasonDivision seasonDivision;

	public InitialTable (SeasonDivision seasonDivision) {
		this.seasonDivision = seasonDivision;
		
		Set<SeasonDivisionTeam> seasonDivisionTeams = seasonDivision.getTeamsInSeasonDivision();
		for (SeasonDivisionTeam seasonDivisionTeam : seasonDivisionTeams) {
			TableRow newRow = new InitialTableRow(seasonDivisionTeam.getTeam());
			
			tableRows.put(seasonDivisionTeam.getTeam().getTeamId(), newRow);
		}
	}

	public Map<Integer, TableRow> getTableRows() {
		return tableRows;
	}
}
