package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public abstract class TableRow<K,L,M> implements Comparable<TableRow<K,L,M>> {
	public static final String GAMES_WON = "gamesWon";
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
	
	public abstract int get(String attributeId);
	
	@Override
	public int compareTo (TableRow<K,L,M> otherRow) {
		if (otherRow.get(POINTS) != get(POINTS)) {
			return (otherRow.get(POINTS) < get(POINTS)) ? -1 : 1;
		} else if (otherRow.get(GOAL_DIFFERENCE) != get(GOAL_DIFFERENCE)) {
			return (otherRow.get(GOAL_DIFFERENCE) < get(GOAL_DIFFERENCE)) ? -1 : 1;
		} else if (otherRow.get(GOALS_SCORED) != get(GOALS_SCORED)) {
			return (otherRow.get(GOALS_SCORED) < get(GOALS_SCORED)) ? -1 : 1;
		} else {
			return team.getTeamName().compareTo(otherRow.getTeam().getTeamName());
		}
	}
	
	@Override
	public String toString () {
		return "TableRow: " + team.getTeamName();
	}
	
	public int getLeaguePosition() {
		return parentTable.getIndexOfTableRow(this);
	}
	
//	public void getCalculations(Map<String, Calculation> calculations) {
//		this.calculations = calculations;
//	}
}
