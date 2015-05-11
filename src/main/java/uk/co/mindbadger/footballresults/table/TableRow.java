package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public abstract class TableRow<K,L,M> implements Comparable<TableRow<K,L,M>> {
	public static final String GAMES_WON = "gamesWon";
	public static final String GAMES_WON_AT_HOME = "gamesWonAtHome";
	public static final String GAMES_WON_AWAY = "gamesWonAway";
	public static final String GAMES_DRAWN = "gamesDrawn";
	public static final String GAMES_LOST = "gamesLost";
	public static final String GAMES_PLAYED = "gamesPlayed";
	public static final String GOALS_SCORED = "goalsScored";
	public static final String GOALS_CONCEDED = "goalsConceded";
	public static final String GOAL_DIFFERENCE = "goalDifference";
	public static final String POINTS = "points";
	
	protected Team<K> team;
	protected Table<K,L,M> parentTable;
	
	public TableRow (Team<K> team, Table<K,L,M> parentTable) {
		this.team = team;
		this.parentTable = parentTable;
	}
	
	public Team<K> getTeam () {
		return team;
	}
	
	public abstract int getAttribute(String attributeId);
	
	@Override
	public int compareTo (TableRow<K,L,M> otherRow) {
		if (otherRow.getAttribute(POINTS) != getAttribute(POINTS)) {
			return (otherRow.getAttribute(POINTS) < getAttribute(POINTS)) ? -1 : 1;
		} else if (otherRow.getAttribute(GOAL_DIFFERENCE) != getAttribute(GOAL_DIFFERENCE)) {
			return (otherRow.getAttribute(GOAL_DIFFERENCE) < getAttribute(GOAL_DIFFERENCE)) ? -1 : 1;
		} else if (otherRow.getAttribute(GOALS_SCORED) != getAttribute(GOALS_SCORED)) {
			return (otherRow.getAttribute(GOALS_SCORED) < getAttribute(GOALS_SCORED)) ? -1 : 1;
		} else {
			return team.getTeamName().compareTo(otherRow.getTeam().getTeamName());
		}
	}
	
	@Override
	public String toString () {
		return "TableRow[team:" + team.getTeamName() + ",points:" + getAttribute(POINTS) + ",scored:" + getAttribute(GOALS_SCORED) + ",condeded:" + getAttribute(GOALS_CONCEDED) + "]";
	}
	
	public int getLeaguePosition() {
		return parentTable.getIndexOfTableRow(this);
	}

	public Table<K,L,M> getParentTable () {
		return parentTable;
	}
}
