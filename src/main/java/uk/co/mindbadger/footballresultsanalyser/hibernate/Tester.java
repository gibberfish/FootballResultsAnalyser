package uk.co.mindbadger.footballresultsanalyser.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class Tester {

	public static void main(String[] args) {
		Transaction tx = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();

		try {
			tx = session.beginTransaction();

			doSomeWork(session);

			tx.commit();
			
			
			tx = session.beginTransaction();

			checkItsOK(session);

			tx.commit();
			
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

	private static void checkItsOK(Session session) {
		// Get the Seasons
		List seasons = session.createQuery("from Season").list();
		
		for (Season season : (List<Season>) seasons) {
			System.out.println("SEASON: " + season.getSsnNum());
			
			Set<SeasonDivision> seasonDivisions = season.getDivisionsInSeason();
			
			for (SeasonDivision seasonDivision : seasonDivisions) {
				System.out.println("..DIVISION: " + seasonDivision.getDivision().getDivName() + ", pos: " + seasonDivision.getDivPos());
			}
		}
	}

	private static void doSomeWork(Session session) {
		// Add a new seasons
		Season season1 = addSeason(session, 2000);
		Season season2 = addSeason(session, 2001);
		
		// Add a new divisions
		Division division1 = addDivision(session, "Premier");
		Division division2 = addDivision(session, "Championship");
		Division division3 = addDivision(session, "League 1");
		
		// Add a new seasonDivisions
		addSeasonDivision(session, season1, division1, 1);
		addSeasonDivision(session, season1, division2, 2);
		addSeasonDivision(session, season1, division3, 3);

		addSeasonDivision(session, season2, division1, 1);
		addSeasonDivision(session, season2, division2, 2);

		
		
		
//		List divisions = session.createQuery("from Division").list();

//		for (Division division : (List<Division>) divisions) {
//			System.out.println("Division: " + division.getDivName());
//			for (SeasonDivision ssnDiv : season.getDivisionsInSeason()) {
//				Division div = ssnDiv.getDivision();
//				
//				System.out.println("Season num = " + season.getSsnNum() + ", div = " + div + ", pos = " + ssnDiv.getDivPos() + ", name = " + div.getDivName());
//				
//				for (Team team : ssnDiv.getTeamsInDivisionInSeason()) {
//					System.out.println("..Team = " + team.getTeamName());
//				}
//			}
//		}
	}

	private static void addSeasonDivision(Session session, Season season, Division division, int pos) {
		SeasonDivision seasonDivision = new SeasonDivision ();
		seasonDivision.setSeason(season);
		seasonDivision.setDivision(division);
		seasonDivision.setDivPos(pos);
		
		season.getDivisionsInSeason().add(seasonDivision);
		
		session.save(season);
	}

	private static Division addDivision(Session session, String divisionName) {
		Division division = new Division (divisionName);
		Integer divId = (Integer) session.save(division);
		return division;
	}

	private static Season addSeason(Session session, Integer seasonNumber) {
		Season season = new Season (seasonNumber);
		session.save(season);
		return season;
	}

}
