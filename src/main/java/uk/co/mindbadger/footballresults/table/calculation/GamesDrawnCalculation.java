package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesDrawnCalculation extends CalculationForFixture<String, String, String> {
	public GamesDrawnCalculation(Team<String> team, TableRow<String,String,String> previousTableRow, Fixture<String> fixture) {
		super(team, previousTableRow, fixture);
	}

	@Override
	public int calculate(boolean reCalculate) {
		boolean homeFixture = (fixture.getHomeTeam() == team);
		int goalsFor = (homeFixture ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (homeFixture ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		int previousGamesDrawn = 0;
		if (previousTableRow != null) {
			previousGamesDrawn = previousTableRow.getGamesDrawn();
		}

		return (goalsFor == goalsAgainst ? previousGamesDrawn + 1 : previousGamesDrawn);
	}

}
