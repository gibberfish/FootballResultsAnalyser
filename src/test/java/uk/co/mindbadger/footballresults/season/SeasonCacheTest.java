package uk.co.mindbadger.footballresults.season;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;

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
	private Division<String> mockDivision1;

	@Mock
	private Division<String> mockDivision2;

	@Mock
	private Division<String> mockDivision3;

	@Mock
	private SeasonDivision<String,String> mockSeasonDivision1;

	@Mock
	private SeasonDivision<String,String> mockSeasonDivision2;

	@Mock
	private SeasonDivision<String,String> mockSeasonDivision3;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		objectUnderTest = new SeasonCache ();
	}
	
	@Test
	public void shouldInitialiseWithNoRowsIfPreviousTableIsNull () {
		// Given
		
		// When
		Map<SeasonDivision<String,String>, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		
		// Then
		assertEquals (0, divisionCaches.size());
	}
	
	@Test
	public void shouldAddNewDivisionCacheIfOneDoesntExist () {
		// Given
		when(mockDivision1.getDivisionId()).thenReturn(divisionId1);
		when(mockDivision2.getDivisionId()).thenReturn(divisionId2);
		when(mockSeasonDivision1.getDivision()).thenReturn(mockDivision1);
		when(mockSeasonDivision2.getDivision()).thenReturn(mockDivision2);
		
		// When
		DivisionCache divisionCache1 = objectUnderTest.getCacheForDivision(mockSeasonDivision1);

		// Then
		Map<SeasonDivision<String,String>, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (1, divisionCaches.size());
		assertEquals (divisionCache1, divisionCaches.get(mockSeasonDivision1));
		
		// When
		DivisionCache divisionCache2 = objectUnderTest.getCacheForDivision(mockSeasonDivision2);
		
		// Then
		divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (2, divisionCaches.size());
		assertEquals (divisionCache2, divisionCaches.get(mockSeasonDivision2));		
	}
	@Test
	public void shouldReturnExistingSeasonCacheIfItExists () {
		// Given
		when(mockDivision1.getDivisionId()).thenReturn(divisionId1);
		when(mockSeasonDivision1.getDivision()).thenReturn(mockDivision1);

		DivisionCache divisionCache1 = objectUnderTest.getCacheForDivision(mockSeasonDivision1);
		
		// When
		DivisionCache divisionCache2 = objectUnderTest.getCacheForDivision(mockSeasonDivision1);
		
		// Then
		Map<SeasonDivision<String,String>, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (1, divisionCaches.size());
		assertEquals (divisionCache1, divisionCaches.get(mockSeasonDivision1));
		assertEquals (divisionCache1, divisionCache2);
	}
	
	@Ignore
	@Test
	public void shouldReturnSortedListOfDivisionsInCache () {
		// Given
		when(mockDivision1.getDivisionId()).thenReturn(divisionId1);
		when(mockSeasonDivision1.getDivision()).thenReturn(mockDivision1);
		
		when(mockDivision2.getDivisionId()).thenReturn(divisionId2);
		when(mockSeasonDivision2.getDivision()).thenReturn(mockDivision2);
		
		when(mockDivision3.getDivisionId()).thenReturn(divisionId3);
		when(mockSeasonDivision3.getDivision()).thenReturn(mockDivision3);
		
		// When
		List<Division<String>> divisions = objectUnderTest.getDivisionsInCache ();
		
		// Then
		fail("Test not written yet");
	}
}
