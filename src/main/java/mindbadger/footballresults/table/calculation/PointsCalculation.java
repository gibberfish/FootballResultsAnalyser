package mindbadger.footballresults.table.calculation;

import static mindbadger.footballresults.table.AttributeIds.*;
import mindbadger.footballresults.season.TeamFixtureContext;
import mindbadger.footballresults.table.TableRow;
import mindbadger.footballresultsanalyser.domain.Fixture;
import mindbadger.footballresultsanalyser.domain.Team;

public class PointsCalculation extends CalculationForFixture<String, String, String> {
	public PointsCalculation(Team team, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {
		
		
		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		int newPoints = 0;
		if (goalsFor > goalsAgainst) newPoints+=3;
		if (goalsFor == goalsAgainst) newPoints++;

		int previousPoints = 0;
		if (previousTableRow != null) {
			previousPoints = previousTableRow.getAttribute(POINTS);
		}

		return (previousPoints + newPoints);
	}

}
