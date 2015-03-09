package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowAfterResult<K,L,M> extends TableRow<K,L,M> {
	private Team<K> team;
	private TableRow<K,L,M> previousTableRow;
	private Fixture<K> fixture;
	private boolean homeFixture;
	private char winLoseOrDraw;
	private int goalsFor;
	private int goalsAgainst;
		
	public TableRowAfterResult (Team<K> team, TableRow<K,L,M> previousTableRow, Fixture<K> fixture) {
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
	public K getTeamId () {
		return team.getTeamId();
	}

	@Override
	public String getTeamName () {
		return team.getTeamName();
	}

	@Override
	public int getGamesPlayed() {
		return previousTableRow.getGamesPlayed() + 1;
	}

	@Override
	public int getGamesWon() {
		return (winLoseOrDraw == 'W' ? previousTableRow.getGamesWon() + 1 : previousTableRow.getGamesWon());
	}

	@Override
	public int getGamesDrawn() {
		return (winLoseOrDraw == 'D' ? previousTableRow.getGamesDrawn() + 1 : previousTableRow.getGamesDrawn());
	}

	@Override
	public int getGamesLost() {
		return (winLoseOrDraw == 'L' ? previousTableRow.getGamesLost() + 1 : previousTableRow.getGamesLost());
	}

	@Override
	public int getGoalsScored() {
		return previousTableRow.getGoalsScored() + goalsFor;
	}

	@Override
	public int getGoalsConceded() {
		return previousTableRow.getGoalsConceded() + goalsAgainst;
	}

	@Override
	public int getGoalDifference() {
		return previousTableRow.getGoalDifference() + (goalsFor - goalsAgainst);
	}

	@Override
	public int getPoints() {
		if (winLoseOrDraw == 'W') {
			return previousTableRow.getPoints() + 3;
		} else if (winLoseOrDraw == 'D') {
			return previousTableRow.getPoints() + 1;
		} else {
			return previousTableRow.getPoints();
		}
	}
}
