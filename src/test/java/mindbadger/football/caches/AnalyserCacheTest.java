package mindbadger.football.caches;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.football.caches.AnalyserCache;
import mindbadger.football.caches.SeasonCache;

public class AnalyserCacheTest {
	
	private AnalyserCache objectUnderTest;
	private Integer seasonNumber1 = 2001;
	private Integer seasonNumber2 = 2002;
	private Integer seasonNumber3 = 2003;
	
	@Mock
	private SeasonCache mockSeasonCache1;
	
	@Mock
	private SeasonCache mockSeasonCache2;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		objectUnderTest = new AnalyserCache ();
	}
	
	@Test
	public void shouldInitialiseWithNoRowsIfPreviousTableIsNull () {
		// Given
		
		// When
		Map<Integer, SeasonCache> seasonCaches = objectUnderTest.getSeasonCaches();
		
		// Then
		assertEquals (0, seasonCaches.size());
	}

	@Test
	public void shouldAddNewSeasonCacheIfOneDoesntExist () {
		// Given
		
		// When
		SeasonCache seasonCache1 = objectUnderTest.getCacheForSeason(seasonNumber1);
		
		// Then
		Map<Integer, SeasonCache> seasonCaches = objectUnderTest.getSeasonCaches();
		assertEquals (1, seasonCaches.size());
		assertEquals (seasonCache1, seasonCaches.get(seasonNumber1));
		
		// When
		SeasonCache seasonCache2 = objectUnderTest.getCacheForSeason(seasonNumber2);
		
		// Then
		seasonCaches = objectUnderTest.getSeasonCaches();
		assertEquals (2, seasonCaches.size());
		assertEquals (seasonCache2, seasonCaches.get(seasonNumber2));
	}

	@Test
	public void shouldReturnExistingSeasonCacheIfItExists () {
		// Given
		SeasonCache seasonCache1 = objectUnderTest.getCacheForSeason(seasonNumber1);
		
		// When
		SeasonCache seasonCache2 = objectUnderTest.getCacheForSeason(seasonNumber1);
		
		// Then
		Map<Integer, SeasonCache> seasonCaches = objectUnderTest.getSeasonCaches();
		assertEquals (1, seasonCaches.size());
		assertEquals (seasonCache1, seasonCaches.get(seasonNumber1));
		assertEquals (seasonCache1, seasonCache2);
	}

	@Test
	public void shouldReturnSortedListOfSeasonNumbersInCache () {
		// Given
		objectUnderTest.getCacheForSeason(seasonNumber2);
		objectUnderTest.getCacheForSeason(seasonNumber1);
		objectUnderTest.getCacheForSeason(seasonNumber3);
		
		// When
		List<Integer> seasonList = objectUnderTest.getSeasonsInCache();
		
		// Then
		assertEquals(seasonNumber3, seasonList.get(0));
		assertEquals(seasonNumber2, seasonList.get(1));
		assertEquals(seasonNumber1, seasonList.get(2));
	}
}
