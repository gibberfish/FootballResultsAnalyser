package uk.co.mindbadger.footballresults.table;

import java.util.Map;
import java.util.Set;

import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class InitialTable<K> extends Table<K> {
	private SeasonDivision<K> seasonDivision;

	public InitialTable (SeasonDivision<K> seasonDivision) {
		this.seasonDivision = seasonDivision;
		
		Set<SeasonDivisionTeam<K>> seasonDivisionTeams = seasonDivision.getTeamsInSeasonDivision();
		for (SeasonDivisionTeam<K> seasonDivisionTeam : seasonDivisionTeams) {
			TableRow<K> newRow = new InitialTableRow<K>(seasonDivisionTeam.getTeam());
			
			tableRows.put(seasonDivisionTeam.getTeam().getTeamId(), newRow);
		}
	}

	public Map<K, TableRow<K>> getTableRows() {
		return tableRows;
	}
}
