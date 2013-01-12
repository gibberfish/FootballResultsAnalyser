package mindbadger.footballresultsanalyser.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Tester {

	public static void main(String[] args) {
		Transaction tx = null;

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session session = sessionFactory.openSession();

		try {
			tx = session.beginTransaction();

			List seasons = session.createQuery("from Season").list();

			for (Season season : (List<Season>) seasons) {
				
				for (SeasonDivision ssnDiv : season.getDivisionsInSeason()) {
					Division div = ssnDiv.getDivision();
					
					System.out.println("Season num = " + season.getSsnNum() + ", div = " + div + ", pos = " + ssnDiv.getDivPos() + ", name = " + div.getDivName());
					
					for (Team team : ssnDiv.getTeamsInDivisionInSeason()) {
						System.out.println("..Team = " + team.getTeamName());
					}
				}
			}

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

}
