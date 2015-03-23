package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesPlayedCalculation extends CalculationForFixture<String, String, String> {
	public GamesPlayedCalculation(Team<String> team, TableRow<String,String,String> previousTableRow, Fixture<String> fixture) {
		super(team, previousTableRow, fixture);
	}

	@Override
	public int calculate(boolean reCalculate) {
		if (previousTableRow == null) {
			return 1;
		} else {
			return previousTableRow.getGamesPlayed() + 1;
		}
	}
}
