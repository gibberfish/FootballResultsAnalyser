package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRow {
	private Team team;
	private TableRow previousTableRow;
	private Fixture fixture;
	private boolean homeFixture;
	private char winLoseOrDraw;
	private int goalsFor;
	private int goalsAgainst;
	
	public TableRow (Team team) {
		this.team = team;
	}
	
	public TableRow (Team team, TableRow previousTableRow, Fixture fixture) {
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

	public Integer getTeamId () {
		return team.getTeamId();
	}

	public String getTeamName () {
		return team.getTeamName();
	}

	public int getGamesPlayed() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return previousTableRow.getGamesPlayed() + 1;
		}
	}

	public int getGamesWon() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return (winLoseOrDraw == 'W' ? previousTableRow.getGamesWon() + 1 : previousTableRow.getGamesWon());
		}
	}

	public int getGamesDrawn() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return (winLoseOrDraw == 'D' ? previousTableRow.getGamesDrawn() + 1 : previousTableRow.getGamesDrawn());
		}
	}

	public int getGamesLost() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return (winLoseOrDraw == 'L' ? previousTableRow.getGamesLost() + 1 : previousTableRow.getGamesLost());
		}
	}

	public int getGoalsScored() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return previousTableRow.getGoalsScored() + goalsFor;
		}
	}

	public int getGoalsConceded() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return previousTableRow.getGoalsConceded() + goalsAgainst;
		}
	}

	public int getGoalDifference() {
		if (previousTableRow == null) {
			return 0;
		} else {
			return previousTableRow.getGoalDifference() + (goalsFor - goalsAgainst);
		}
	}

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
