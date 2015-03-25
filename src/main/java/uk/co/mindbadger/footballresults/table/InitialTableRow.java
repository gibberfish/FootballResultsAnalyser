package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class InitialTableRow<K,L,M> extends TableRow<K,L,M> {

	public InitialTableRow(Team<K> team, Table<K,L,M> parentTable) {
		super(team, parentTable);
	}

	@Override
	public int get(String attributeId) {
		return 0;
	}
}
