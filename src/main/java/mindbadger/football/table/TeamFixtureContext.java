package mindbadger.football.table;

import mindbadger.football.domain.Team;

public class TeamFixtureContext {
	private Team team;
	private int leaguePosition;
	private boolean isAtHome;
	private boolean isPlayingTeamAbove;
	private int goalsScored;
	private int goalsConceded;
	private int points;
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public int getLeaguePosition() {
		return leaguePosition;
	}
	public void setLeaguePosition(int leaguePosition) {
		this.leaguePosition = leaguePosition;
	}
	public boolean isAtHome() {
		return isAtHome;
	}
	public void setAtHome(boolean isAtHome) {
		this.isAtHome = isAtHome;
	}
	public boolean isPlayingTeamAbove() {
		return isPlayingTeamAbove;
	}
	public void setPlayingTeamAbove(boolean isPlayingTeamAbove) {
		this.isPlayingTeamAbove = isPlayingTeamAbove;
	}
	public int getGoalsScored() {
		return goalsScored;
	}
	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}
	public int getGoalsConceded() {
		return goalsConceded;
	}
	public void setGoalsConceded(int goalsConceded) {
		this.goalsConceded = goalsConceded;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
