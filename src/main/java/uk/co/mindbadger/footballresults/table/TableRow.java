package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRow {
	private Team team;
	
	public TableRow (Team team) {
		this.team = team;
	}
	
	public TableRow (Team team, TableRow previousTableRow, Fixture fixture) {
		
	}

	public Integer getTeamId () {
		return team.getTeamId();
	}

	public String getTeamName () {
		return team.getTeamName();
	}

	public int getGamesPlayed() {
		return 0;
	}

	public Object getGamesWon() {
		return 0;
	}

	public Object getGamesDrawn() {
		return 0;
	}

	public Object getGamesLost() {
		return 0;
	}

	public Object getGoalsScored() {
		return 0;
	}

	public Object getGoalsConceded() {
		return 0;
	}

	public Object getGoalDifference() {
		return 0;
	}

	public Object getPoints() {
		return 0;
	}
}
