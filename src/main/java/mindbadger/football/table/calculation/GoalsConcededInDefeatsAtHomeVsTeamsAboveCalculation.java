package mindbadger.football.table.calculation;

import static mindbadger.football.table.AttributeIds.*;

import mindbadger.football.table.TableRow;
import mindbadger.football.table.TeamFixtureContext;
import mindbadger.football.domain.Fixture;
import mindbadger.football.domain.Team;

public class GoalsConcededInDefeatsAtHomeVsTeamsAboveCalculation extends CalculationForFixture<String, String, String> {
	public GoalsConcededInDefeatsAtHomeVsTeamsAboveCalculation(Team team, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {

		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		boolean lost = goalsFor < goalsAgainst;
		
		return ((lost && fixtureTeamContext.isAtHome() && fixtureTeamContext.isPlayingTeamAbove()) ?
				previousTableRow.getAttribute(GOALS_CONCEDED_IN_DEFEATS_AT_HOME_VS_ABOVE) + goalsAgainst : previousTableRow.getAttribute(GOALS_CONCEDED_IN_DEFEATS_AT_HOME_VS_ABOVE));
	}

}
