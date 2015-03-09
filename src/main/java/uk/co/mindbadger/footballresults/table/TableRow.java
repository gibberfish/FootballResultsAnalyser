package uk.co.mindbadger.footballresults.table;

public abstract class TableRow<K,L,M> implements Comparable<TableRow<K,L,M>> {
	protected Table<K,L,M> parentTable;
	
	abstract public K getTeamId ();
	abstract public String getTeamName ();
	abstract public int getGamesPlayed();
	abstract public int getGamesWon();
	abstract public int getGamesDrawn();
	abstract public int getGamesLost();
	abstract public int getGoalsScored();
	abstract public int getGoalsConceded();
	abstract public int getGoalDifference();
	abstract public int getPoints();
	
	@Override
	public int compareTo (TableRow<K,L,M> otherRow) {
		if (otherRow.getPoints() != getPoints()) {
			return (otherRow.getPoints() < getPoints()) ? -1 : 1;
		} else if (otherRow.getGoalDifference() != getGoalDifference()) {
			return (otherRow.getGoalDifference() < getGoalDifference()) ? -1 : 1;
		} else if (otherRow.getGoalsScored() != getGoalsScored()) {
			return (otherRow.getGoalsScored() < getGoalsScored()) ? -1 : 1;
		} else {
			return getTeamName().compareTo(otherRow.getTeamName());
		}
	}
	
	@Override
	public String toString () {
		return "TableRow: " + getTeamName();
	}
	
	public int getLeaguePosition() {
		return parentTable.getIndexOfTableRow(this);
	}
}
