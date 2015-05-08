package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesWonAtHomeVsTeamsBelowCalculation extends CalculationForFixture<String, String, String> {
	public GamesWonAtHomeVsTeamsBelowCalculation(Team<String> team, TableRow<String,String,String> previousTableRow, Fixture<String> fixture) {
		super(team, previousTableRow, fixture);
	}

	//TODO #3 Change the existing granular classes to use the team Contexts
	@Override
	public int calculate(boolean reCalculate) {

		boolean homeFixture = (fixture.getHomeTeam() == team);
		int goalsFor = (homeFixture ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (homeFixture ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		boolean vsTeamAbove = false;
		
		if (previousTableRow != null) {
			Table<String,String,String> table = previousTableRow.getParentTable();
			TableRow<String,String,String> oppositionRow = null;
			if (homeFixture) {
				oppositionRow = table.getTableRowForTeam(fixture.getAwayTeam().getTeamId());
			} else {
				oppositionRow = table.getTableRowForTeam(fixture.getHomeTeam().getTeamId());
			}
			vsTeamAbove = oppositionRow.getLeaguePosition() < previousTableRow.getLeaguePosition();
		}
		
		int previousGamesWon = 0;
		if (previousTableRow != null) {
			previousGamesWon = previousTableRow.getAttribute(TableRow.GAMES_WON);
		}

		return ((goalsFor > goalsAgainst) && homeFixture && !vsTeamAbove ? previousGamesWon + 1 : previousGamesWon);
	}

}
