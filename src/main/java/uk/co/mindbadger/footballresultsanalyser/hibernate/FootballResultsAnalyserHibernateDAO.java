package uk.co.mindbadger.footballresultsanalyser.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class FootballResultsAnalyserHibernateDAO implements FootballResultsAnalyserDAO {

    @Override
    public List<Season> getSeasons() {
	Transaction tx = null;
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	Session session = sessionFactory.openSession();

	tx = session.beginTransaction();
	
	// Get the Seasons
	List seasons = session.createQuery("from Season").list();
	
	tx.commit();

	return seasons;
    }

}
