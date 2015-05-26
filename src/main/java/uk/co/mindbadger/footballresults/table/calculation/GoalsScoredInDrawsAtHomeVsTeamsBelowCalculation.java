package uk.co.mindbadger.footballresults.table.calculation;

import static uk.co.mindbadger.footballresults.table.AttributeIds.*;
import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GoalsScoredInDrawsAtHomeVsTeamsBelowCalculation extends CalculationForFixture<String, String, String> {
	public GoalsScoredInDrawsAtHomeVsTeamsBelowCalculation(Team<String> team, Fixture<String> fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow<String,String,String> previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {
		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		boolean drawn = goalsFor == goalsAgainst;
		
		return ((drawn && fixtureTeamContext.isAtHome() && !fixtureTeamContext.isPlayingTeamAbove()) ?
				previousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_BELOW) + goalsFor : previousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_BELOW));
	}

}
