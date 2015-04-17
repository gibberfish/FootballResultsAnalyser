package uk.co.mindbadger.footballresults.season;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.DomainObjectFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.DomainObjectFactoryImpl;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;

public class SeasonLoaderTest {
	
	private SeasonLoader objectUnderTest;
	//private DomainObjectFactory<String, String, String> domainObjectFactory = new DomainObjectFactoryImpl();
	
	@Mock
	private FootballResultsAnalyserDAO<String,String,String> mockDao;

	@Mock
	private Season<String> mockSeason;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldGetListOfSeasonsOnCreation () {
		// Given
		// When
		try {
			objectUnderTest = new SeasonLoader(mockDao);
		} catch (Exception e) {
			// Then
			verify(mockDao, times(1)).getSeasons();
			// Fine if it throws an exception - all we're looking for is the call to getSeasons
		}
	}

	@Test
	public void shouldThrowAnExceptionIfANullDaoPassedIn () {
		// Given
		// When
		try {
			objectUnderTest = new SeasonLoader(null);
			fail("Should throw an exception if the DAO is null");
		} catch (IllegalStateException e) {
			// Then
			assertEquals ("Please supply a valid DAO", e.getMessage());
		}
	}

	@Test
	public void shouldThrowAnExceptionIfThereAreNoSeasons () {
		// Given
		List<Season<String>> emptyList = new ArrayList<Season<String>> ();
		when(mockDao.getSeasons()).thenReturn(emptyList);
		
		// When
		try {
			objectUnderTest = new SeasonLoader(mockDao);
			fail("Should throw an exception if there is no season data available");
		} catch (IllegalStateException e) {
			// Then
			assertEquals ("There is no season data available.", e.getMessage());
		}
				
	}

	@Test
	public void shouldGetDivisionsWhenLoadingSeason () {
		// Given
		List<Season<String>> list = new ArrayList<Season<String>> ();
		list.add(mockSeason);
		when(mockDao.getSeasons()).thenReturn(list);
		objectUnderTest = new SeasonLoader(mockDao);
		
		// When
		objectUnderTest.loadSeason(mockSeason);

		// Then
		// Called once for the constructor, once for the load
		verify(mockDao,times(2)).getDivisionsForSeason(mockSeason);
	}

	@Test
	public void shouldPopulateCurrentSeasonInTheCacheOnConstruct () {
		// Given
		
		// When
		
		
		// Then
		
		
	}
	
//	@Test
//	public void getSeasonShouldThrowExceptionIfNotInitialised() {
//		// Given
//		Season season1 = domainObjectFactory.createSeason(1999);
//		Season season2 = domainObjectFactory.createSeason(2001);
//		Season season3 = domainObjectFactory.createSeason(2000);
//		
//		List<Season<String>> seasons = new ArrayList<Season<String>> ();
//		seasons.add(season1);
//		seasons.add(season2);
//		seasons.add(season3);
//		
//		when(mockDao.getSeasons()).thenReturn(seasons);
//		
//		// When
//		try {
//			Season<String> season = objectUnderTest.getSeason();
//			fail("Should throw an InitialisationException");
//		} catch (InitialisationException e) {
//			// Then
//			verify(mockDao.getSeasons(), never());
//		}
//	}
//
//	@Test
//	public void getSeasonShouldReturnLatestSeasonIfInitialised() {
//		// Given
//		Season season1 = domainObjectFactory.createSeason(1999);
//		Season season2 = domainObjectFactory.createSeason(2001);
//		Season season3 = domainObjectFactory.createSeason(2000);
//		
//		List<Season<String>> seasons = new ArrayList<Season<String>> ();
//		seasons.add(season1);
//		seasons.add(season2);
//		seasons.add(season3);
//		
//		when(mockDao.getSeasons()).thenReturn(seasons);
//		
//		// When
//		objectUnderTest.loadLatestSeason();
//		Season<String> season = objectUnderTest.getSeason();
//		
//		// Then
//		assertEquals(season2, season);
//	}

}
