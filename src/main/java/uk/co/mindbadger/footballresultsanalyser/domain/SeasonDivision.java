package uk.co.mindbadger.footballresultsanalyser.domain;

import java.util.Set;

public class SeasonDivision{
	
	private Season season;
	private Division division;
	private int divPos;
	private Set<SeasonDivisionTeam> teamsInSeasonDivision;
	
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
	public Set<SeasonDivisionTeam> getTeamsInDivisionInSeason() {
		return teamsInSeasonDivision;
	}
	public void setTeamsInDivisionInSeason(Set<SeasonDivisionTeam> teamsInSeasonDivision) {
		this.teamsInSeasonDivision = teamsInSeasonDivision;
	}
}
