package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class PointsCalculation extends CalculationForFixture<String, String, String> {
	public PointsCalculation(Team<String> team, TableRow<String,String,String> previousTableRow, Fixture<String> fixture) {
		super(team, previousTableRow, fixture);
	}

	@Override
	public int calculate(boolean reCalculate) {
		boolean homeFixture = (fixture.getHomeTeam() == team);
		int goalsFor = (homeFixture ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (homeFixture ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		int newPoints = 0;
		if (goalsFor > goalsAgainst) newPoints+=3;
		if (goalsFor == goalsAgainst) newPoints++;
		
		int previousPoints = 0;
		if (previousTableRow != null) {
			previousPoints = previousTableRow.getAttribute(TableRow.POINTS);
		}

		return (previousPoints + newPoints);
	}

}
