package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class InitialTableRow<K,L,M> extends TableRow<K,L,M> {

	public InitialTableRow(Team<K> team) {
		super(team);
	}

	@Override
	public int getAttribute(String attributeId) {
		return 0;
	}
}
