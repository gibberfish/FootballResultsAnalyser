package uk.co.mindbadger.footballresults.season;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class AnalyserCacheTest {
	
	private AnalyserCache objectUnderTest;
	private Integer seasonNumber1 = 2001;
	private Integer seasonNumber2 = 2002;
	private Integer seasonNumber3 = 2003;
	
	@Mock
	private SeasonCache mockSeasonCache1;
	
	@Mock
	private SeasonCache mockSeasonCache2;
	
	@Mock
	private SeasonCache mockSeasonCache3;

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

}
