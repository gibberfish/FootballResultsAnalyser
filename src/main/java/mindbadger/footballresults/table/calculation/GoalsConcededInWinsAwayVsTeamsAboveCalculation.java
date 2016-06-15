package mindbadger.footballresults.table.calculation;

import static mindbadger.footballresults.table.AttributeIds.*;
import mindbadger.footballresults.season.TeamFixtureContext;
import mindbadger.footballresults.table.TableRow;
import mindbadger.footballresultsanalyser.domain.Fixture;
import mindbadger.footballresultsanalyser.domain.Team;

public class GoalsConcededInWinsAwayVsTeamsAboveCalculation extends CalculationForFixture<String, String, String> {
	public GoalsConcededInWinsAwayVsTeamsAboveCalculation(Team team, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {

		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		boolean won = goalsFor > goalsAgainst;
		
		return ((won && !fixtureTeamContext.isAtHome() && fixtureTeamContext.isPlayingTeamAbove()) ?
				previousTableRow.getAttribute(GOALS_CONCEDED_IN_WINS_AWAY_VS_ABOVE) + goalsAgainst : previousTableRow.getAttribute(GOALS_CONCEDED_IN_WINS_AWAY_VS_ABOVE));
	}

}
