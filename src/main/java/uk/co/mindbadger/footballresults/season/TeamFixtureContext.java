package uk.co.mindbadger.footballresults.season;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TeamFixtureContext {
	private Team<String> team;
	private int leaguePosition;
	private boolean isAtHome;
	private boolean isPlayingTeamAbove;
	public Team<String> getTeam() {
		return team;
	}
	public void setTeam(Team<String> team) {
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
}
