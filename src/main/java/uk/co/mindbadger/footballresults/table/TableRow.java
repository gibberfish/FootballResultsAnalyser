package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public abstract class TableRow<K,L,M> implements Comparable<TableRow<K,L,M>> {
	public static final String GAMES_WON_AT_HOME_VS_ABOVE = "gamesWonAtHomeVsAbove";
	public static final String GAMES_WON_AT_HOME_VS_BELOW = "gamesWonAtHomevsBelow";
	public static final String GAMES_WON_AWAY_VS_ABOVE = "gamesWonAwayVsAbove";
	public static final String GAMES_WON_AWAY_VS_BELOW = "gamesWonAwayvsBelow";
	public static final String GAMES_DRAWN_AT_HOME_VS_ABOVE = "gamesDrawnAtHomeVsAbove";
	public static final String GAMES_DRAWN_AT_HOME_VS_BELOW = "gamesDrawnAtHomevsBelow";
	public static final String GAMES_DRAWN_AWAY_VS_ABOVE = "gamesDrawnAwayVsAbove";
	public static final String GAMES_DRAWN_AWAY_VS_BELOW = "gamesDrawnAwayvsBelow";
	public static final String GAMES_LOST_AT_HOME_VS_ABOVE = "gamesLostAtHomeVsAbove";
	public static final String GAMES_LOST_AT_HOME_VS_BELOW = "gamesLostAtHomevsBelow";
	public static final String GAMES_LOST_AWAY_VS_ABOVE = "gamesLostAwayVsAbove";
	public static final String GAMES_LOST_AWAY_VS_BELOW = "gamesLostAwayvsBelow";

	public static final String GOALS_SCORED_IN_DEFEATS_AT_HOME_VS_ABOVE = "goalsScoredInDefeatsAtHomeVsAbove";
	public static final String GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE = "goalsScoredInDrawsAtHomeVsAbove";
	public static final String GOALS_SCORED_IN_WINS_AT_HOME_VS_ABOVE = "goalsScoredInWinsAtHomeVsAbove";
	public static final String GOALS_SCORED_IN_DEFEATS_AWAY_VS_ABOVE = "goalsScoredInDefeatsAwayVsAbove";
	public static final String GOALS_SCORED_IN_DRAWS_AWAY_VS_ABOVE = "goalsScoredInDrawsAwayVsAbove";
	public static final String GOALS_SCORED_IN_WINS_AWAY_VS_ABOVE = "goalsScoredInWinsAwayVsAbove";
	public static final String GOALS_SCORED_IN_DEFEATS_AT_HOME_VS_BELOW = "goalsScoredInDefeatsAtHomeVsBelow";
	public static final String GOALS_SCORED_IN_DRAWS_AT_HOME_VS_BELOW = "goalsScoredInDrawsAtHomeVsBelow";
	public static final String GOALS_SCORED_IN_WINS_AT_HOME_VS_BELOW = "goalsScoredInWinsAtHomeVsBelow";
	public static final String GOALS_SCORED_IN_DEFEATS_AWAY_VS_BELOW = "goalsScoredInDefeatsAwayVsBelow";
	public static final String GOALS_SCORED_IN_DRAWS_AWAY_VS_BELOW = "goalsScoredInDrawsAwayVsBelow";
	public static final String GOALS_SCORED_IN_WINS_AWAY_VS_BELOW = "goalsScoredInWinsAwayVsBelow";

	public static final String GOALS_CONCEDED_IN_DEFEATS_AT_HOME_VS_ABOVE = "goalsConcededInDefeatsAtHomeVsAbove";
	public static final String GOALS_CONCEDED_IN_DRAWS_AT_HOME_VS_ABOVE = "goalsConcededInDrawsAtHomeVsAbove";
	public static final String GOALS_CONCEDED_IN_WINS_AT_HOME_VS_ABOVE = "goalsConcededInWinsAtHomeVsAbove";
	public static final String GOALS_CONCEDED_IN_DEFEATS_AWAY_VS_ABOVE = "goalsConcededInDefeatsAwayVsAbove";
	public static final String GOALS_CONCEDED_IN_DRAWS_AWAY_VS_ABOVE = "goalsConcededInDrawsAwayVsAbove";
	public static final String GOALS_CONCEDED_IN_WINS_AWAY_VS_ABOVE = "goalsConcededInWinsAwayVsAbove";
	public static final String GOALS_CONCEDED_IN_DEFEATS_AT_HOME_VS_BELOW = "goalsConcededInDefeatsAtHomeVsBelow";
	public static final String GOALS_CONCEDED_IN_DRAWS_AT_HOME_VS_BELOW = "goalsConcededInDrawsAtHomeVsBelow";
	public static final String GOALS_CONCEDED_IN_WINS_AT_HOME_VS_BELOW = "goalsConcededInWinsAtHomeVsBelow";
	public static final String GOALS_CONCEDED_IN_DEFEATS_AWAY_VS_BELOW = "goalsConcededInDefeatsAwayVsBelow";
	public static final String GOALS_CONCEDED_IN_DRAWS_AWAY_VS_BELOW = "goalsConcededInDrawsAwayVsBelow";
	public static final String GOALS_CONCEDED_IN_WINS_AWAY_VS_BELOW = "goalsConcededInWinsAwayVsBelow";
	
	public static final String GOALS_SCORED_AT_HOME_VS_ABOVE = "goalsScoredAtHomeVsAbove";
	public static final String GOALS_SCORED_AT_HOME_VS_BELOW = "goalsScoredAtHomeVsBelow";
	public static final String GOALS_SCORED_AWAY_VS_ABOVE = "goalsScoredAwayVsAbove";
	public static final String GOALS_SCORED_AWAY_VS_BELOW = "goalsScoredAwayVsBelow";
	public static final String GOALS_CONCEDED_AT_HOME_VS_ABOVE = "goalsConcededAtHomeVsAbove";
	public static final String GOALS_CONCEDED_AT_HOME_VS_BELOW = "goalsConcededAtHomeVsBelow";
	public static final String GOALS_CONCEDED_AWAY_VS_ABOVE = "goalsConcededAwayVsAbove";
	public static final String GOALS_CONCEDED_AWAY_VS_BELOW = "goalsConcededAwayVsBelow";

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
		return "TableRow[team:" + team.getTeamName() + ",points:" + getAttribute(POINTS) + ",scored:" + getAttribute(GOALS_SCORED) + ",conceded:" + getAttribute(GOALS_CONCEDED) + "]";
	}
}
