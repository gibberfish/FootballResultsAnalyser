package mindbadger.footballresults.table.calculation;

import mindbadger.footballresults.season.TeamFixtureContext;
import mindbadger.footballresults.table.TableRow;
import mindbadger.footballresultsanalyser.domain.Fixture;
import mindbadger.footballresultsanalyser.domain.Team;

public abstract class CalculationForFixture<K,L,M> extends Calculation {
	
	protected Team team;
	protected TableRow previousTableRow;
	protected Fixture fixture;
	protected TeamFixtureContext fixtureTeamContext;
	protected TeamFixtureContext oppositionTeamContext;
	
	public CalculationForFixture (Team team, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow previousTableRow) {
		this.team = team;
		this.previousTableRow = previousTableRow;
		this.fixture = fixture;
		this.fixtureTeamContext = fixtureTeamContext;
		this.oppositionTeamContext = oppositionTeamContext;
	}
}
