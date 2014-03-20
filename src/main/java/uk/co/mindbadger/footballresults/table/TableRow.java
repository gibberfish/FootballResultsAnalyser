package uk.co.mindbadger.footballresults.table;

public interface TableRow {
	public Integer getTeamId ();
	public String getTeamName ();
	public int getGamesPlayed();
	public int getGamesWon();
	public int getGamesDrawn();
	public int getGamesLost();
	public int getGoalsScored();
	public int getGoalsConceded();
	public int getGoalDifference();
	public int getPoints();
}
