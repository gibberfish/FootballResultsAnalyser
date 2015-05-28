package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class InitialTableRow<K,L,M> extends TableRow<K,L,M> {

	public InitialTableRow(Team<K> team) {
		super(team);
	}

	@Override
	public int getAttribute(String attributeId) {
		return 0;
	}

	@Override
	public TableRow<K, L, M> getPreviousTableRow() {
		return null;
	}

	@Override
	public Fixture<K> getFixture() {
		return null;
	}

	@Override
	public TeamFixtureContext getFixtureTeamContext() {
		return null;
	}

	@Override
	public TeamFixtureContext getOppositionTeamContext() {
		return null;
	}
}
