package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GoalsScoredInWinsAwayVsTeamsAboveCalculation extends CalculationForFixture<String, String, String> {
	public GoalsScoredInWinsAwayVsTeamsAboveCalculation(Team<String> team, Fixture<String> fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow<String,String,String> previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {

		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		boolean won = goalsFor > goalsAgainst;
		
		return ((won && !fixtureTeamContext.isAtHome() && fixtureTeamContext.isPlayingTeamAbove()) ?
				previousTableRow.getAttribute(TableRow.GOALS_SCORED_IN_WINS_AWAY_VS_ABOVE) + goalsFor : previousTableRow.getAttribute(TableRow.GOALS_SCORED_IN_WINS_AWAY_VS_ABOVE));
	}

}
