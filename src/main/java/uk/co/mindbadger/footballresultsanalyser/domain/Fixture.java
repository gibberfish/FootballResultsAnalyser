package uk.co.mindbadger.footballresultsanalyser.domain;

import java.util.Calendar;

public class Fixture {
	private Season season;
	private Team homeTeam;
	private Team awayTeam;
	private Calendar fixtureDate;
	private Division division;
	private Integer homeGoals;
	private Integer awayGoals;
	
	public Season getSeason() {
		return season;
	}
	public void setSeason(Season season) {
		this.season = season;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	public Calendar getFixtureDate() {
		return fixtureDate;
	}
	public void setFixtureDate(Calendar fixtureDate) {
		this.fixtureDate = fixtureDate;
	}
	public Division getDivision() {
		return division;
	}
	public void setDivision(Division division) {
		this.division = division;
	}
	public Integer getHomeGoals() {
		return homeGoals;
	}
	public void setHomeGoals(Integer homeGoals) {
		this.homeGoals = homeGoals;
	}
	public Integer getAwayGoals() {
		return awayGoals;
	}
	public void setAwayGoals(Integer awayGoals) {
		this.awayGoals = awayGoals;
	}
}
