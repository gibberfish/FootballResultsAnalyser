package uk.co.mindbadger.footballresultsanalyser.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionId;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class FootballResultsAnalyserHibernateDAO implements FootballResultsAnalyserDAO {

    private Map<Thread, Session> sessions = new HashMap<Thread, Session>();

    @Override
    public void startSession() {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = sessionFactory.openSession();

	sessions.put(Thread.currentThread(), session);
    }

    @Override
    public void closeSession() {
	Session session = sessions.get(Thread.currentThread());
	session.close();
	
	sessions.remove(session);
    }

    @Override
    public List<Season> getSeasons() {
	Transaction tx = null;

	Session session = sessions.get(Thread.currentThread());

	List seasons = null;
	tx = session.beginTransaction();

	// Get the Seasons
	seasons = session.createQuery("from Season").list();

	tx.commit();

	return seasons;
    }

    @Override
    public Set<SeasonDivision> getDivisionsForSeason(int seasonNumber) {
	Transaction tx = null;

	Session session = sessions.get(Thread.currentThread());

	Season season = null;
	tx = session.beginTransaction();

	season = (Season) session.get(Season.class, seasonNumber);

	// Get the Seasons
	// List seasonDivisions =
	// session.createQuery("from SeasonDivision SD where SD.season.ssnNum = "
	// + seasonNumber + " order by SD.divPos").list();
	// List seasonDivisions =
	// session.createQuery("from SeasonDivision SD").list();

	tx.commit();

	return season.getDivisionsInSeason();
    }

    @Override
    public Set<SeasonDivisionTeam> getTeamsForDivisionInSeason(int seasonNumber, int divisionId) {
	Transaction tx = null;

	Session session = sessions.get(Thread.currentThread());

	SeasonDivision seasonDivision = null;
	tx = session.beginTransaction();

	Season season = (Season) session.get(Season.class, seasonNumber);
	Division division = (Division) session.get(Division.class, divisionId);

	SeasonDivisionId seasonDivisionId = new SeasonDivisionId();
	seasonDivisionId.setSeason(season);
	seasonDivisionId.setDivision(division);

	seasonDivision = (SeasonDivision) session.get(SeasonDivision.class, seasonDivisionId);

	tx.commit();

	return seasonDivision.getTeamsInSeasonDivision();
    }

    @Override
    public List<Fixture> getFixturesForTeamInDivisionInSeason(int seasonNumber, int divisionId, int teamId) {
	Transaction tx = null;

	Session session = sessions.get(Thread.currentThread());

	List fixtures = null;
	tx = session.beginTransaction();

	fixtures = session.createQuery("select F from Fixture F join F.division D join F.season S" + " where S.ssnNum = " + seasonNumber + " and D.divId = " + divisionId + " order by F.fixtureDate").list();

	tx.commit();

	return fixtures;
    }

}
