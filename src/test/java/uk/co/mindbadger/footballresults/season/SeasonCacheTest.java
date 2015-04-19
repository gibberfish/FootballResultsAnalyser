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
	private String divisionId3 = "div3";
	
	@Mock
	private DivisionCache mockDivisionCache1;
	
	@Mock
	private DivisionCache mockDivisionCache2;
	
	@Mock
	private DivisionCache mockDivisionCache3;

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
	public void shouldAddDivisionCache () {
		// Given
		
		// When
		objectUnderTest.addDivisionCache(divisionId1, mockDivisionCache1);
		objectUnderTest.addDivisionCache(divisionId2, mockDivisionCache2);
		objectUnderTest.addDivisionCache(divisionId3, mockDivisionCache3);
		
		// Then
		Map<String, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (3, divisionCaches.size());
		assertEquals (mockDivisionCache1, divisionCaches.get(divisionId1));
		assertEquals (mockDivisionCache2, divisionCaches.get(divisionId2));
		assertEquals (mockDivisionCache3, divisionCaches.get(divisionId3));
	}

}
