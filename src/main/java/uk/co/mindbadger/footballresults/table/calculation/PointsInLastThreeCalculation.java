package uk.co.mindbadger.footballresults.table.calculation;

import static uk.co.mindbadger.footballresults.table.AttributeIds.*;
import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class PointsInLastThreeCalculation extends CalculationForFixture<String, String, String> {
	public PointsInLastThreeCalculation(Team<String> team, Fixture<String> fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow<String,String,String> previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {
		TableRow<String,String,String> previousTableRow = this.previousTableRow;
		TableRow<String,String,String> previousTableRow2 = this.previousTableRow.getPreviousTableRow();
		TableRow<String,String,String> previousTableRow3 = this.previousTableRow.getPreviousTableRow();
		
//		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
//		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
//		
//		int newPoints = 0;
//		if (goalsFor > goalsAgainst) newPoints+=3;
//		if (goalsFor == goalsAgainst) newPoints++;
//
//		int previousPoints = 0;
//		if (previousTableRow != null) {
//			previousPoints = previousTableRow.getAttribute(POINTS);
//		}
//
//		return (previousPoints + newPoints);
	}

}
