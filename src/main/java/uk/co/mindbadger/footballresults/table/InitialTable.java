package uk.co.mindbadger.footballresults.table;

import java.util.Set;

import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class InitialTable<K,L,M> extends Table<K,L,M> {

	public InitialTable (Table<K,L,M> previousTable) {
		super(previousTable);
	}

	public InitialTable (SeasonDivision<K,L> seasonDivision, Set<SeasonDivisionTeam<K,L,M>> seasonDivisionTeams) {
		super(null);
		
		for (SeasonDivisionTeam<K,L,M> seasonDivisionTeam : seasonDivisionTeams) {
			TableRow<K,L,M> newRow = new InitialTableRow<K,L,M>(seasonDivisionTeam.getTeam(), this);
			addRow(newRow);
		}
		
	}
}
