package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class InitialTableRow implements TableRow {

	private Team team;

	public InitialTableRow(Team team) {
		this.team = team;
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
		return 0;
	}

	@Override
	public int getGamesWon() {
		return 0;
	}

	@Override
	public int getGamesDrawn() {
		return 0;
	}

	@Override
	public int getGamesLost() {
		return 0;
	}

	@Override
	public int getGoalsScored() {
		return 0;
	}

	@Override
	public int getGoalsConceded() {
		return 0;
	}

	@Override
	public int getGoalDifference() {
		return 0;
	}

	@Override
	public int getPoints() {
		return 0;
	}
}
