package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class InitialTableRow<K,L,M> extends TableRow<K,L,M> {

	private Team<K> team;

	public InitialTableRow(Team<K> team) {
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
		return 0;
	}
}
