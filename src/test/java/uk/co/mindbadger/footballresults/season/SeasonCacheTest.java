package uk.co.mindbadger.footballresults.season;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.DomainObjectFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.DomainObjectFactoryImpl;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;

public class SeasonCacheTest {
	
	private SeasonCache objectUnderTest;
	private DomainObjectFactory<String, String, String> domainObjectFactory = new DomainObjectFactoryImpl();
	
	@Mock
	private FootballResultsAnalyserDAO<String,String,String> mockDao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectUnderTest = new SeasonCache();
		objectUnderTest.setDao(mockDao);
	}

	@Test
	public void getSeasonShouldThrowExceptionIfNotInitialised() {
		// Given
		Season season1 = domainObjectFactory.createSeason(1999);
		Season season2 = domainObjectFactory.createSeason(2001);
		Season season3 = domainObjectFactory.createSeason(2000);
		
		List<Season<String>> seasons = new ArrayList<Season<String>> ();
		seasons.add(season1);
		seasons.add(season2);
		seasons.add(season3);
		
		when(mockDao.getSeasons()).thenReturn(seasons);
		
		// When
		try {
			Season<String> season = objectUnderTest.getSeason();
			fail("Should throw an InitialisationException");
		} catch (InitialisationException e) {
			// Then
			verify(mockDao.getSeasons(), never());
		}
	}

	@Test
	public void getSeasonShouldReturnLatestSeasonIfInitialised() {
		// Given
		Season season1 = domainObjectFactory.createSeason(1999);
		Season season2 = domainObjectFactory.createSeason(2001);
		Season season3 = domainObjectFactory.createSeason(2000);
		
		List<Season<String>> seasons = new ArrayList<Season<String>> ();
		seasons.add(season1);
		seasons.add(season2);
		seasons.add(season3);
		
		when(mockDao.getSeasons()).thenReturn(seasons);
		
		// When
		objectUnderTest.loadLatestSeason();
		Season<String> season = objectUnderTest.getSeason();
		
		// Then
		assertEquals(season2, season);
	}

}
