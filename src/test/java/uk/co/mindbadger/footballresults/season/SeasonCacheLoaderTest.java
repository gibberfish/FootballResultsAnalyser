package uk.co.mindbadger.footballresults.season;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;

public class SeasonCacheLoaderTest {
	
	private SeasonCacheLoader objectUnderTest;
	
	@Mock
	private FootballResultsAnalyserDAO<String,String,String> mockDao;

	@Mock
	private SeasonCacheDivisionLoader mockSeasonCacheDivisionLoader;
	
	@Mock
	private AnalyserCache mockAnalyserCache;

	@Mock
	private Season<String> mockSeason2015;
	
	@Mock
	private SeasonCache mockSeasonCache2015;

	@Mock
	private SeasonDivision<String, String> mockSeasonDivision1;
	
	@Mock
	private SeasonDivision<String, String> mockSeasonDivision2;

	
	
	
	
	


	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectUnderTest = new SeasonCacheLoader();
		objectUnderTest.setAnalyserCache(mockAnalyserCache);
		objectUnderTest.setDao(mockDao);
		objectUnderTest.setSeasonCacheDivisionLoader(mockSeasonCacheDivisionLoader);
		
		when (mockSeason2015.getSeasonNumber()).thenReturn(2015);
		when(mockAnalyserCache.getCacheForSeason(2015)).thenReturn(mockSeasonCache2015);
	}
	
	@Test
	public void shouldLoadSeasonFromDataInDao () {
		// Given
		Set<SeasonDivision<String, String>> seasonDivisions = new HashSet<SeasonDivision<String, String>> ();
		seasonDivisions.add(mockSeasonDivision1);
		seasonDivisions.add(mockSeasonDivision2);
		when(mockDao.getDivisionsForSeason(mockSeason2015)).thenReturn(seasonDivisions);

		// When
		objectUnderTest.loadSeason(mockSeason2015);
		
		// Then
		verify(mockAnalyserCache, times(1)).getCacheForSeason(2015);
		verify(mockDao, times(1)).getDivisionsForSeason(mockSeason2015);
		verify(mockSeasonCacheDivisionLoader,times(1)).loadDivision(mockSeasonDivision1, mockSeasonCache2015);
		verify(mockSeasonCacheDivisionLoader,times(1)).loadDivision(mockSeasonDivision2, mockSeasonCache2015);
	}
}
