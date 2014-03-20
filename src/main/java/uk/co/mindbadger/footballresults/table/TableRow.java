package uk.co.mindbadger.footballresults.table;

public abstract class TableRow implements Comparable<TableRow> {
	abstract public Integer getTeamId ();
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
	public int compareTo (TableRow otherRow) {
		if (otherRow.getPoints() != getPoints()) {
			return (otherRow.getPoints() < getPoints()) ? -1 : 1;
		}
		
		return 0;
	}
}
