package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesLostCalculation extends CalculationForFixture<String, String, String> {
	public GamesLostCalculation(Team<String> team, TableRow<String,String,String> previousTableRow, Fixture<String> fixture) {
		super(team, previousTableRow, fixture);
	}

	@Override
	public int calculate(boolean reCalculate) {
		boolean homeFixture = (fixture.getHomeTeam() == team);
		int goalsFor = (homeFixture ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (homeFixture ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		int previousGamesLost = 0;
		if (previousTableRow != null) {
			previousGamesLost = previousTableRow.get(TableRow.GAMES_LOST);
		}

		return (goalsFor < goalsAgainst ? previousGamesLost + 1 : previousGamesLost);
	}

}
