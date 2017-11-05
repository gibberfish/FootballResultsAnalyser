package mindbadger.football.table.calculation;

import static mindbadger.football.table.AttributeIds.*;

import mindbadger.football.table.TableRow;
import mindbadger.football.table.TeamFixtureContext;
import mindbadger.football.domain.Fixture;
import mindbadger.football.domain.Team;

public class GoalsConcededInWinsAtHomeVsTeamsBelowCalculation extends CalculationForFixture<String, String, String> {
	public GoalsConcededInWinsAtHomeVsTeamsBelowCalculation(Team team, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {
		
		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		boolean won = goalsFor > goalsAgainst;
		
		return ((won && fixtureTeamContext.isAtHome() && !fixtureTeamContext.isPlayingTeamAbove()) ?
				previousTableRow.getAttribute(GOALS_CONCEDED_IN_WINS_AT_HOME_VS_BELOW) + goalsAgainst : previousTableRow.getAttribute(GOALS_CONCEDED_IN_WINS_AT_HOME_VS_BELOW));
	}

}
