package mindbadger.football.cache.loaders;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.football.caches.AnalyserCache;
import mindbadger.football.caches.SeasonCache;
import mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import mindbadger.footballresultsanalyser.domain.Season;
import mindbadger.footballresultsanalyser.domain.SeasonDivision;

public class SeasonCacheLoaderTest {
	
	private SeasonCacheLoader objectUnderTest;
	
	@Mock
	private FootballResultsAnalyserDAO mockDao;

	@Mock
	private SeasonCacheDivisionLoader mockSeasonCacheDivisionLoader;
	
	@Mock
	private AnalyserCache mockAnalyserCache;

	@Mock
	private SeasonCache mockSeasonCache2015;

	@Mock
	private SeasonDivision mockSeasonDivision1;
	
	@Mock
	private SeasonDivision mockSeasonDivision2;

	@Mock
	private Season mockSeason2015;

	@Mock
	private Season mockSeason2014;

	@Mock
	private Season mockSeason2013;

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
		List<SeasonDivision> seasonDivisions = new ArrayList<SeasonDivision> ();
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
		List<Season> seasons = new ArrayList<Season> ();
		seasons.add(mockSeason2013);
		seasons.add(mockSeason2014);
		seasons.add(mockSeason2015);
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
