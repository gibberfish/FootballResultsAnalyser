package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowAfterResult<K,L,M> extends TableRow<K,L,M> {
	private Team<K> team;
		
	public TableRowAfterResult (Team<K> team, TableRow<K,L,M> previousTableRow, Fixture<K> fixture) {
		if (team == null || previousTableRow == null || fixture == null) {
			throw new IllegalArgumentException("Please supply a Team, a previous TableRow and a Fixture");
		}
		
		this.team = team;
	}

	@Override
	public K getTeamId () {
		return team.getTeamId();
	}

	@Override
	public String getTeamName () {
		return team.getTeamName();
	}

	@Override
	public int get(String attributeId) {
		return calculations.get(attributeId).calculate();
	}	
}
