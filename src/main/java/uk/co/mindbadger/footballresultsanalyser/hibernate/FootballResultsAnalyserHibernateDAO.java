package uk.co.mindbadger.footballresultsanalyser.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionId;
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

    @Override
    public Set<SeasonDivision> getDivisionsForSeason(int seasonNumber) {
	Transaction tx = null;
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	Session session = sessionFactory.openSession();

	tx = session.beginTransaction();
	
	Season season = (Season)session.get(Season.class, seasonNumber);

	// Get the Seasons
	//List seasonDivisions = session.createQuery("from SeasonDivision SD where SD.season.ssnNum = " + seasonNumber + " order by SD.divPos").list();
	//List seasonDivisions = session.createQuery("from SeasonDivision SD").list();
	
	tx.commit();

	return season.getDivisionsInSeason();
    }

    @Override
    public Set<SeasonDivisionTeam> getTeamsForDivisionInSeason(int seasonNumber, int divisionId) {
	Transaction tx = null;
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	Session session = sessionFactory.openSession();

	tx = session.beginTransaction();
	
	Season season = (Season)session.get(Season.class, seasonNumber);
	Division division = (Division)session.get(Division.class, divisionId);
	
	SeasonDivisionId seasonDivisionId = new SeasonDivisionId ();
	seasonDivisionId.setSeason(season);
	seasonDivisionId.setDivision(division);
	
	SeasonDivision seasonDivision = (SeasonDivision)session.get(SeasonDivision.class, seasonDivisionId);
	
	tx.commit();

	return seasonDivision.getTeamsInSeasonDivision();
    }

}
