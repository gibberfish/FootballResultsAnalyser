package uk.co.mindbadger.footballresults.table.calculation;

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
		TableRow<String,String,String> previousTableRow1 = this.previousTableRow;
		TableRow<String,String,String> previousTableRow2 = this.previousTableRow.getPreviousTableRow();
		TableRow<String,String,String> previousTableRow3 = this.previousTableRow.getPreviousTableRow();
		
		return pointsForTableRow(previousTableRow1) + pointsForTableRow(previousTableRow2) + pointsForTableRow(previousTableRow3);
	}

	private int pointsForTableRow (TableRow<String,String,String> tableRow) {
		if (tableRow != null && tableRow.getFixtureTeamContext() != null) {
			return tableRow.getFixtureTeamContext().getPoints();
		} else {
			return 0;
		}
	}
	
}
