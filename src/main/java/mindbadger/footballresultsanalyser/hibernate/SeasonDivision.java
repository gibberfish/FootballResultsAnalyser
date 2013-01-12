package mindbadger.footballresultsanalyser.hibernate;

import java.io.Serializable;
import java.util.Set;

public class SeasonDivision implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Season season;
	private Division division;
	private int divPos;
	private Set<Team> teamsInDivisionInSeason;
	
	public Season getSeason() {
		return season;
	}
	public void setSeason(Season season) {
		this.season = season;
	}
	public Division getDivision() {
		return division;
	}
	public void setDivision(Division division) {
		this.division = division;
	}
	public int getDivPos() {
		return divPos;
	}
	public void setDivPos(int divPos) {
		this.divPos = divPos;
	}
	public Set<Team> getTeamsInDivisionInSeason() {
		return teamsInDivisionInSeason;
	}
	public void setTeamsInDivisionInSeason(Set<Team> teamsInDivisionInSeason) {
		this.teamsInDivisionInSeason = teamsInDivisionInSeason;
	}
}
