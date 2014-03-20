package uk.co.mindbadger.footballresults.table;

public abstract class TableRow {
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
}
