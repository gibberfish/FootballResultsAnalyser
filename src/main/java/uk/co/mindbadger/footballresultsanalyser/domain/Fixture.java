package uk.co.mindbadger.footballresultsanalyser.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "fixture", catalog = "football")
public class Fixture {
    private Integer fixtureId;
    private Season season;
    private Team homeTeam;
    private Team awayTeam;
    private Calendar fixtureDate;
    private Division division;
    private Integer homeGoals;
    private Integer awayGoals;

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "FIXTURE_ID", unique = true, nullable = false)
    public Integer getFixtureId() {
	return fixtureId;
    }
    public void setFixtureId(Integer fixtureId) {
	this.fixtureId = fixtureId;
    }

    @ManyToOne
    @JoinColumn(name = "SSN_NUM")
    public Season getSeason() {
	return season;
    }
    public void setSeason(Season season) {
	this.season = season;
    }

    @ManyToOne
    @JoinColumn(name = "HOME_TEAM_ID")
    public Team getHomeTeam() {
	return homeTeam;
    }
    public void setHomeTeam(Team homeTeam) {
	this.homeTeam = homeTeam;
    }

    @ManyToOne
    @JoinColumn(name = "AWAY_TEAM_ID")
    public Team getAwayTeam() {
	return awayTeam;
    }
    public void setAwayTeam(Team awayTeam) {
	this.awayTeam = awayTeam;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "FIXTURE_DATE", nullable = true)
    public Calendar getFixtureDate() {
	return fixtureDate;
    }
    public void setFixtureDate(Calendar fixtureDate) {
	this.fixtureDate = fixtureDate;
    }

    @ManyToOne
    @JoinColumn(name = "DIV_ID")
    public Division getDivision() {
	return division;
    }
    public void setDivision(Division division) {
	this.division = division;
    }

    @Column(name = "HOME_GOALS", nullable = true)
    public Integer getHomeGoals() {
	return homeGoals;
    }
    public void setHomeGoals(Integer homeGoals) {
	this.homeGoals = homeGoals;
    }

    @Column(name = "AWAY_GOALS", nullable = true)
    public Integer getAwayGoals() {
	return awayGoals;
    }
    public void setAwayGoals(Integer awayGoals) {
	this.awayGoals = awayGoals;
    }
}
