package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowAfterResult implements TableRow {
	private Team team;
	private TableRow previousTableRow;
	private Fixture fixture;
	private boolean homeFixture;
	private char winLoseOrDraw;
	private int goalsFor;
	private int goalsAgainst;
		
	public TableRowAfterResult (Team team, TableRow previousTableRow, Fixture fixture) {
		if (team == null || previousTableRow == null || fixture == null) {
			throw new IllegalArgumentException("Please supply a Team, a previous TableRow and a Fixture");
		}
		
		this.team = team;
		this.previousTableRow = previousTableRow;
		this.fixture = fixture;
		homeFixture = (fixture.getHomeTeam() == team);
		goalsFor = (homeFixture ? fixture.getHomeGoals() : fixture.getAwayGoals());
		goalsAgainst = (homeFixture ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		if (goalsFor > goalsAgainst) {
			winLoseOrDraw = 'W';
		} else if (goalsFor < goalsAgainst) {
			winLoseOrDraw = 'L';
		} else {
			winLoseOrDraw = 'D';
		}
	}

	@Override
	public Integer getTeamId () {
		return team.getTeamId();
	}

	@Override
	public String getTeamName () {
		return team.getTeamName();
	}

	@Override
	public int getGamesPlayed() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return previousTableRow.getGamesPlayed() + 1;
		}
	}

	@Override
	public int getGamesWon() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return (winLoseOrDraw == 'W' ? previousTableRow.getGamesWon() + 1 : previousTableRow.getGamesWon());
		}
	}

	@Override
	public int getGamesDrawn() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return (winLoseOrDraw == 'D' ? previousTableRow.getGamesDrawn() + 1 : previousTableRow.getGamesDrawn());
		}
	}

	@Override
	public int getGamesLost() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return (winLoseOrDraw == 'L' ? previousTableRow.getGamesLost() + 1 : previousTableRow.getGamesLost());
		}
	}

	@Override
	public int getGoalsScored() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return previousTableRow.getGoalsScored() + goalsFor;
		}
	}

	@Override
	public int getGoalsConceded() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return previousTableRow.getGoalsConceded() + goalsAgainst;
		}
	}

	@Override
	public int getGoalDifference() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return previousTableRow.getGoalDifference() + (goalsFor - goalsAgainst);
		}
	}

	@Override
	public int getPoints() {
		if (previousTableRow == null) {
			return 0;
		} else if (winLoseOrDraw == 'W') {
			return previousTableRow.getPoints() + 3;
		} else if (winLoseOrDraw == 'D') {
			return previousTableRow.getPoints() + 1;
		} else {
			return previousTableRow.getPoints();
		}
	}
}
