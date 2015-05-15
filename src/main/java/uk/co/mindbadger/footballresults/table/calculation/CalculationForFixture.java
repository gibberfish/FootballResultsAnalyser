package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public abstract class CalculationForFixture<K,L,M> extends Calculation {
	
	protected Team<K> team;
	protected TableRow<K,L,M> previousTableRow;
	protected Fixture<K> fixture;
	protected TeamFixtureContext fixtureTeamContext;
	protected TeamFixtureContext oppositionTeamContext;
	
	public CalculationForFixture (Team<K> team, Fixture<K> fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow<K,L,M> previousTableRow) {
		this.team = team;
		this.previousTableRow = previousTableRow;
		this.fixture = fixture;
		this.fixtureTeamContext = fixtureTeamContext;
		this.oppositionTeamContext = oppositionTeamContext;
	}
}
