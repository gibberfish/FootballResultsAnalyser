package uk.co.mindbadger.footballresultsanalyser.domain;

public class SeasonDivisionTeam {
	private SeasonDivision seasonDivision;
	private Team team;
	
	public SeasonDivision getSeasonDivision() {
		return seasonDivision;
	}
	public void setSeasonDivision(SeasonDivision seasonDivision) {
		this.seasonDivision = seasonDivision;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
}
