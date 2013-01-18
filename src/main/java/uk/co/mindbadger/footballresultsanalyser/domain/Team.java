package uk.co.mindbadger.footballresultsanalyser.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="team", catalog="football")
public class Team {
	private Integer teamId;
	private String teamName;
	private String teamShortName;
	//private Set<Fixture> homeFixturesForTeam;
	//private Set<Fixture> awayFixturesForTeam;
	
	public Team () {}
	
	public Team(String teamName) {
		this.teamName = teamName;
	}
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "TEAM_ID", unique = true, nullable = false)
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	@Column(name = "TEAM_NAME", nullable = false)
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	@Column(name = "TEAM_SHORT_NAME", nullable = false)
	public String getTeamShortName() {
		return teamShortName;
	}
	public void setTeamShortName(String teamShortName) {
		this.teamShortName = teamShortName;
	}
//	public Set<Fixture> getHomeFixturesForTeam() {
//		return homeFixturesForTeam;
//	}
//	public void setHomeFixturesForTeam(Set<Fixture> homeFixturesForTeam) {
//		this.homeFixturesForTeam = homeFixturesForTeam;
//	}
//	public Set<Fixture> getAwayFixturesForTeam() {
//		return awayFixturesForTeam;
//	}
//	public void setAwayFixturesForTeam(Set<Fixture> awayFixturesForTeam) {
//		this.awayFixturesForTeam = awayFixturesForTeam;
//	}
}
