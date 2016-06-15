package mindbadger.footballresults.table.calculation;

import static mindbadger.footballresults.table.AttributeIds.*;
import mindbadger.footballresults.season.TeamFixtureContext;
import mindbadger.footballresults.table.TableRow;
import mindbadger.footballresultsanalyser.domain.Fixture;
import mindbadger.footballresultsanalyser.domain.Team;

public class GamesDrawnAwayVsTeamsBelowCalculation extends CalculationForFixture<String, String, String> {
	public GamesDrawnAwayVsTeamsBelowCalculation(Team team, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {

		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		boolean drawn = goalsFor == goalsAgainst;
		
		return ((drawn && !fixtureTeamContext.isAtHome() && !fixtureTeamContext.isPlayingTeamAbove()) ?
				previousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_BELOW) + 1 : previousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_BELOW));
	}

}
