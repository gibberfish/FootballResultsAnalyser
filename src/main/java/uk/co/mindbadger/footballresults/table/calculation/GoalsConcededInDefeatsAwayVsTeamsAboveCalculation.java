package uk.co.mindbadger.footballresults.table.calculation;

import static uk.co.mindbadger.footballresults.table.AttributeIds.*;
import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GoalsConcededInDefeatsAwayVsTeamsAboveCalculation extends CalculationForFixture<String, String, String> {
	public GoalsConcededInDefeatsAwayVsTeamsAboveCalculation(Team team, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {

		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		boolean lost = goalsFor < goalsAgainst;
		
		return ((lost && !fixtureTeamContext.isAtHome() && fixtureTeamContext.isPlayingTeamAbove()) ?
				previousTableRow.getAttribute(GOALS_CONCEDED_IN_DEFEATS_AWAY_VS_ABOVE) + goalsAgainst : previousTableRow.getAttribute(GOALS_CONCEDED_IN_DEFEATS_AWAY_VS_ABOVE));
	}

}
