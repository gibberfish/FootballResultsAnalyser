package uk.co.mindbadger.footballresults.table.calculation;

import static uk.co.mindbadger.footballresults.table.AttributeIds.*;
import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesWonAtHomeVsTeamsBelowCalculation extends CalculationForFixture<String, String, String> {
	public GamesWonAtHomeVsTeamsBelowCalculation(Team team, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {
		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		boolean won = goalsFor > goalsAgainst;
		
		return ((won && fixtureTeamContext.isAtHome() && !fixtureTeamContext.isPlayingTeamAbove()) ?
				previousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_BELOW) + 1 : previousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_BELOW));
	}

}
