package uk.co.mindbadger.footballresultsanalyser.hibernate;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class Tester {

    public static void main(String[] args) {
	Transaction tx = null;
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	Session session = sessionFactory.openSession();

	try {
	    doSomeWork(session, tx);

	    checkItsOK(session, tx);
	} catch (RuntimeException e) {
	    if (tx != null && tx.isActive()) {
		try {
		    // Second try catch as the rollback could fail as well
		    tx.rollback();
		} catch (HibernateException e1) {
		    System.err.println("Error rolling back transaction");
		}
		// throw again the first exception
		throw e;
	    }
	}
    }

    private static void checkItsOK(Session session, Transaction tx) {
	tx = session.beginTransaction();
	
	// Get the Seasons
	List seasons = session.createQuery("from Season").list();

	for (Season season : (List<Season>) seasons) {
	    System.out.println("SEASON: " + season.getSsnNum());

	    Set<SeasonDivision> seasonDivisions = season.getDivisionsInSeason();

	    for (SeasonDivision seasonDivision : seasonDivisions) {
		System.out.println("..DIVISION: " + seasonDivision.getDivision().getDivName() + ", pos: " + seasonDivision.getDivPos());
		
		Set<SeasonDivisionTeam> seasonDivisionTeams = seasonDivision.getTeamsInSeasonDivision();
		
		for (SeasonDivisionTeam seasonDivisionTeam : seasonDivisionTeams) {
		    System.out.println("....TEAM: " + seasonDivisionTeam.getTeam().getTeamName());
		}
	    }
	}
	
	tx.commit();
    }

    private static void doSomeWork(Session session, Transaction tx) {
	tx = session.beginTransaction();
	
	// Add a new seasons
	Season season1 = addSeason(session, 2000);
	Season season2 = addSeason(session, 2001);

	// Add a new divisions
	Division division1 = addDivision(session, "Premier");
	Division division2 = addDivision(session, "Championship");
	Division division3 = addDivision(session, "League 1");

	// Add new teams
	Team team1 = addTeam(session, "Liverpool");
	Team team2 = addTeam(session, "Man U");
	Team team3 = addTeam(session, "Portsmouth");
	Team team4 = addTeam(session, "Leeds");
	Team team5 = addTeam(session, "Carlisle");
	Team team6 = addTeam(session, "Yeovil");

	// Add a new seasonDivisions
	SeasonDivision ssn1div1 = addSeasonDivision(session, season1, division1, 1);
	SeasonDivision ssn1div2 = addSeasonDivision(session, season1, division2, 2);
	SeasonDivision ssn1div3 = addSeasonDivision(session, season1, division3, 3);

	SeasonDivision ssn2div1 = addSeasonDivision(session, season2, division1, 1);
	SeasonDivision ssn2div2 = addSeasonDivision(session, season2, division2, 2);

	tx.commit();
	
	tx = session.beginTransaction();
	
	// Add teams to Season Divisions
	addTeamToSeasonDivision(session, ssn1div1, team1);
	addTeamToSeasonDivision(session, ssn1div1, team2);
	
	addTeamToSeasonDivision(session, ssn1div2, team3);
	addTeamToSeasonDivision(session, ssn1div2, team4);
	
	addTeamToSeasonDivision(session, ssn1div3, team5);
	addTeamToSeasonDivision(session, ssn1div3, team6);
	
	
	addTeamToSeasonDivision(session, ssn2div1, team6);
	addTeamToSeasonDivision(session, ssn2div1, team5);
	addTeamToSeasonDivision(session, ssn2div1, team4);
	
	addTeamToSeasonDivision(session, ssn2div2, team3);
	addTeamToSeasonDivision(session, ssn2div2, team2);
	addTeamToSeasonDivision(session, ssn2div2, team1);

	addFixture (session, Calendar.getInstance(), season1, division1, team1, team2);
	addFixture (session, Calendar.getInstance(), season1, division1, team2, team1);
	
	tx.commit();
	
    }

    private static void addFixture(Session session, Calendar fixtureDate, Season season, Division division, Team homeTeam, Team awayTeam) {
	Fixture fixture = new Fixture ();
	fixture.setFixtureDate(fixtureDate);
	fixture.setSeason(season);
	fixture.setDivision(division);
	fixture.setHomeTeam(homeTeam);
	fixture.setAwayTeam(awayTeam);
	
	session.save(fixture);
    }

    private static void addTeamToSeasonDivision(Session session, SeasonDivision seasonDivision, Team team) {
	SeasonDivisionTeam seasonDivisionTeam = new SeasonDivisionTeam();
	seasonDivisionTeam.setSeasonDivision(seasonDivision);
	seasonDivisionTeam.setTeam(team);
	
	seasonDivision.getTeamsInSeasonDivision().add(seasonDivisionTeam);
	
	session.save(seasonDivisionTeam);
    }

    private static Team addTeam(Session session, String teamName) {
	Team team = new Team(teamName);
	Integer teamId = (Integer) session.save(team);
	return team;
    }

    private static SeasonDivision addSeasonDivision(Session session, Season season, Division division, int pos) {
	SeasonDivision seasonDivision = new SeasonDivision();
	seasonDivision.setSeason(season);
	seasonDivision.setDivision(division);
	seasonDivision.setDivPos(pos);

	season.getDivisionsInSeason().add(seasonDivision);

	session.save(season);

	return seasonDivision;
    }

    private static Division addDivision(Session session, String divisionName) {
	Division division = new Division(divisionName);
	Integer divId = (Integer) session.save(division);
	return division;
    }

    private static Season addSeason(Session session, Integer seasonNumber) {
	Season season = new Season(seasonNumber);
	session.save(season);
	return season;
    }

}
