package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GoalDifferenceCalculation extends CalculationForFixture<String, String, String> {
	public GoalDifferenceCalculation(Team<String> team, TableRow<String,String,String> previousTableRow, Fixture<String> fixture) {
		super(team, previousTableRow, fixture);
	}

	@Override
	public int calculate(boolean reCalculate) {		
		boolean homeFixture = (fixture.getHomeTeam() == team);
		int goalDifference = (homeFixture ? fixture.getHomeGoals() - fixture.getAwayGoals() : fixture.getAwayGoals() - fixture.getHomeGoals());
		
		int previousGoalDifference = 0;
		if (previousTableRow != null) {
			previousGoalDifference = previousTableRow.getGoalDifference();
		}

		return previousGoalDifference + goalDifference;
	}
}
