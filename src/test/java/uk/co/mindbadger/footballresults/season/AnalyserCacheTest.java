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
	public void shouldAddDivisionCache () {
		// Given
		
		// When
		objectUnderTest.addSeasonCache(seasonNumber1, mockSeasonCache1);
		objectUnderTest.addSeasonCache(seasonNumber2, mockSeasonCache2);
		objectUnderTest.addSeasonCache(seasonNumber3, mockSeasonCache3);
		
		// Then
		Map<Integer, SeasonCache> seasonCaches = objectUnderTest.getSeasonCaches();
		assertEquals (3, seasonCaches.size());
		assertEquals (mockSeasonCache1, seasonCaches.get(seasonNumber1));
		assertEquals (mockSeasonCache2, seasonCaches.get(seasonNumber2));
		assertEquals (mockSeasonCache3, seasonCaches.get(seasonNumber3));
	}

}
