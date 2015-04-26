package uk.co.mindbadger.footballresults.season;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Division;

public class SeasonCacheTest {
	
	private SeasonCache objectUnderTest;
	private String divisionId1 = "div1";
	private String divisionId2 = "div2";
	
	@Mock
	private DivisionCache mockDivisionCache1;
	
	@Mock
	private DivisionCache mockDivisionCache2;

	@Mock
	private Division<String> mockDivision1;

	@Mock
	private Division<String> mockDivision2;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		objectUnderTest = new SeasonCache ();
	}
	
	@Test
	public void shouldInitialiseWithNoRowsIfPreviousTableIsNull () {
		// Given
		
		// When
		Map<Division<String>, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		
		// Then
		assertEquals (0, divisionCaches.size());
	}
	
	@Test
	public void shouldAddNewDivisionCacheIfOneDoesntExist () {
		// Given
		when(mockDivision1.getDivisionId()).thenReturn(divisionId1);
		when(mockDivision2.getDivisionId()).thenReturn(divisionId2);
		
		// When
		DivisionCache divisionCache1 = objectUnderTest.getCacheForDivision(mockDivision1);

		// Then
		Map<Division<String>, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (1, divisionCaches.size());
		assertEquals (divisionCache1, divisionCaches.get(mockDivision1));
		
		// When
		DivisionCache divisionCache2 = objectUnderTest.getCacheForDivision(mockDivision2);
		
		// Then
		divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (2, divisionCaches.size());
		assertEquals (divisionCache2, divisionCaches.get(mockDivision2));		
	}
	@Test
	public void shouldReturnExistingSeasonCacheIfItExists () {
		// Given
		DivisionCache divisionCache1 = objectUnderTest.getCacheForDivision(mockDivision1);
		when(mockDivision1.getDivisionId()).thenReturn(divisionId1);
		
		// When
		DivisionCache divisionCache2 = objectUnderTest.getCacheForDivision(mockDivision1);
		
		// Then
		Map<Division<String>, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (1, divisionCaches.size());
		assertEquals (divisionCache1, divisionCaches.get(mockDivision1));
		assertEquals (divisionCache1, divisionCache2);
	}
}
