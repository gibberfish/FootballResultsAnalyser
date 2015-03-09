package uk.co.mindbadger.footballresults.table;

import java.util.Map;
import java.util.Set;

import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class InitialTable<K,L,M> extends Table<K,L,M> {
	private SeasonDivision<K,L> seasonDivision;

	public InitialTable (SeasonDivision<K,L> seasonDivision, Set<SeasonDivisionTeam<K,L,M>> seasonDivisionTeams) {
		this.seasonDivision = seasonDivision;
		
		for (SeasonDivisionTeam<K,L,M> seasonDivisionTeam : seasonDivisionTeams) {
			TableRow<K,L,M> newRow = new InitialTableRow(seasonDivisionTeam.getTeam());
			
			tableRows.put(seasonDivisionTeam.getTeam().getTeamId(), newRow);
		}
	}

	public Map<K, TableRow<K,L,M>> getTableRows() {
		return tableRows;
	}
}
