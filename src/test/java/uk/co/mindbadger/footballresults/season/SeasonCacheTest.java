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

public class SeasonCacheTest {
	
	private SeasonCache objectUnderTest;
	private String divisionId1 = "div1";
	private String divisionId2 = "div2";
	
	@Mock
	private DivisionCache mockDivisionCache1;
	
	@Mock
	private DivisionCache mockDivisionCache2;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		objectUnderTest = new SeasonCache ();
	}
	
	@Test
	public void shouldInitialiseWithNoRowsIfPreviousTableIsNull () {
		// Given
		
		// When
		Map<String, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		
		// Then
		assertEquals (0, divisionCaches.size());
	}
	
	@Test
	public void shouldAddNewDivisionCacheIfOneDoesntExist () {
		// Given
		
		// When
		DivisionCache divisionCache1 = objectUnderTest.getCacheForDivision(divisionId1);
		
		// Then
		Map<String, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (1, divisionCaches.size());
		assertEquals (divisionCache1, divisionCaches.get(divisionId1));
		
		// When
		DivisionCache divisionCache2 = objectUnderTest.getCacheForDivision(divisionId2);
		
		// Then
		divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (2, divisionCaches.size());
		assertEquals (divisionCache2, divisionCaches.get(divisionId2));		
	}
	@Test
	public void shouldReturnExistingSeasonCacheIfItExists () {
		// Given
		DivisionCache divisionCache1 = objectUnderTest.getCacheForDivision(divisionId1);
		
		// When
		DivisionCache divisionCache2 = objectUnderTest.getCacheForDivision(divisionId1);
		
		// Then
		Map<String, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (1, divisionCaches.size());
		assertEquals (divisionCache1, divisionCaches.get(divisionId1));
		assertEquals (divisionCache1, divisionCache2);
	}
}
