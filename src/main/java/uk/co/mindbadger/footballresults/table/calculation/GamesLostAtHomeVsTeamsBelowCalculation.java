package uk.co.mindbadger.footballresults.table.calculation;

import static uk.co.mindbadger.footballresults.table.AttributeIds.GAMES_LOST_AT_HOME_VS_BELOW;
import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesLostAtHomeVsTeamsBelowCalculation extends CalculationForFixture<String, String, String> {
	public GamesLostAtHomeVsTeamsBelowCalculation(Team<String> team, Fixture<String> fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow<String,String,String> previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {
		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		boolean lost = goalsFor < goalsAgainst;
		
		return ((lost && fixtureTeamContext.isAtHome() && !fixtureTeamContext.isPlayingTeamAbove()) ?
				previousTableRow.getAttribute(GAMES_LOST_AT_HOME_VS_BELOW) + 1 : previousTableRow.getAttribute(GAMES_LOST_AT_HOME_VS_BELOW));
	}

}
