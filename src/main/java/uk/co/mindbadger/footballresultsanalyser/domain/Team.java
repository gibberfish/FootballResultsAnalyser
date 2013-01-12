package uk.co.mindbadger.footballresultsanalyser.domain;

import java.util.Set;

public class Team {
	private Integer teamId;
	private String teamName;
	private String teamShortName;
	private Set<Fixture> homeFixturesForTeam;
	private Set<Fixture> awayFixturesForTeam;
	
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamShortName() {
		return teamShortName;
	}
	public void setTeamShortName(String teamShortName) {
		this.teamShortName = teamShortName;
	}
	public Set<Fixture> getHomeFixturesForTeam() {
		return homeFixturesForTeam;
	}
	public void setHomeFixturesForTeam(Set<Fixture> homeFixturesForTeam) {
		this.homeFixturesForTeam = homeFixturesForTeam;
	}
	public Set<Fixture> getAwayFixturesForTeam() {
		return awayFixturesForTeam;
	}
	public void setAwayFixturesForTeam(Set<Fixture> awayFixturesForTeam) {
		this.awayFixturesForTeam = awayFixturesForTeam;
	}
}
