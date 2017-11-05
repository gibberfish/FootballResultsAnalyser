package mindbadger.football.table;

import mindbadger.football.domain.Fixture;
import mindbadger.football.domain.Team;

public class InitialTableRow extends TableRow {

	public InitialTableRow(Team team) {
		super(team);
	}

	@Override
	public int getAttribute(String attributeId) {
		return 0;
	}

	@Override
	public TableRow getPreviousTableRow() {
		return null;
	}

	@Override
	public Fixture getFixture() {
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
