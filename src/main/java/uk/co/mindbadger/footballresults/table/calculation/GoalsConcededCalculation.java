package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GoalsConcededCalculation extends CalculationForFixture<String, String, String> {
	public GoalsConcededCalculation(Team<String> team, TableRow<String,String,String> previousTableRow, Fixture<String> fixture) {
		super(team, previousTableRow, fixture);
	}

	@Override
	public int calculate(boolean reCalculate) {		
		boolean homeFixture = (fixture.getHomeTeam() == team);
		int goalsAgainst = (homeFixture ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		int previousGoalsAgainst = 0;
		if (previousTable != null) {
			previousGoalsAgainst = previousTable.getAttribute(TableRow.GOALS_CONCEDED);
		}

		return previousGoalsAgainst + goalsAgainst;
	}
}
