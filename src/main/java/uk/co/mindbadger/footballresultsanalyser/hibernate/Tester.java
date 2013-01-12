package uk.co.mindbadger.footballresultsanalyser.hibernate;

import java.util.List;

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

	private static void doSomeWork(Session session) {
		List divisions = session.createQuery("from Division").list();

		for (Division division : (List<Division>) divisions) {
			System.out.println("Division: " + division.getDivName());
//			for (SeasonDivision ssnDiv : season.getDivisionsInSeason()) {
//				Division div = ssnDiv.getDivision();
//				
//				System.out.println("Season num = " + season.getSsnNum() + ", div = " + div + ", pos = " + ssnDiv.getDivPos() + ", name = " + div.getDivName());
//				
//				for (Team team : ssnDiv.getTeamsInDivisionInSeason()) {
//					System.out.println("..Team = " + team.getTeamName());
//				}
//			}
		}
	}

}
