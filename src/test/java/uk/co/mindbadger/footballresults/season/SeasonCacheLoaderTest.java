package uk.co.mindbadger.footballresults.season;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	private SeasonCache mockSeasonCache2015;

	@Mock
	private SeasonDivision<String, String> mockSeasonDivision1;
	
	@Mock
	private SeasonDivision<String, String> mockSeasonDivision2;

	@Mock
	private Season<String> mockSeason2015;

	@Mock
	private Season<String> mockSeason2014;

	@Mock
	private Season<String> mockSeason2013;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectUnderTest = new SeasonCacheLoader();
		objectUnderTest.setAnalyserCache(mockAnalyserCache);
		objectUnderTest.setDao(mockDao);
		objectUnderTest.setSeasonCacheDivisionLoader(mockSeasonCacheDivisionLoader);
		
		when (mockSeason2015.getSeasonNumber()).thenReturn(2015);
		when (mockSeason2014.getSeasonNumber()).thenReturn(2014);
		when (mockSeason2013.getSeasonNumber()).thenReturn(2013);
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
	
	@Test
	public void shouldGetCurrentSeason () {
		// Given
		List<Season<String>> seasons = new ArrayList<Season<String>> ();
		seasons.add(mockSeason2015);
		seasons.add(mockSeason2014);
		seasons.add(mockSeason2013);
		when (mockDao.getSeasons()).thenReturn(seasons);
		
		// When
		objectUnderTest.loadCurrentSeason ();
		
		// Then
		verify(mockDao).getSeasons();
		verify(mockAnalyserCache, times(1)).getCacheForSeason(2015);
		verify(mockDao, times(1)).getDivisionsForSeason(mockSeason2015);

		verify(mockAnalyserCache, never()).getCacheForSeason(2014);
		verify(mockAnalyserCache, never()).getCacheForSeason(2013);
	}
}
