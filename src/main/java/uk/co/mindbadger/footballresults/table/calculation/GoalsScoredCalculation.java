package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GoalsScoredCalculation extends CalculationForFixture<String, String, String> {
	public GoalsScoredCalculation(Team<String> team, TableRow<String,String,String> previousTableRow, Fixture<String> fixture) {
		super(team, previousTableRow, fixture);
	}

	@Override
	public int calculate(boolean reCalculate) {		
		boolean homeFixture = (fixture.getHomeTeam() == team);
		int goalsFor = (homeFixture ? fixture.getHomeGoals() : fixture.getAwayGoals());
		
		int previousGoalsFor = 0;
		if (previousTableRow != null) {
			previousGoalsFor = previousTableRow.getGoalsScored();
		}

		return previousGoalsFor + goalsFor;
	}
}
