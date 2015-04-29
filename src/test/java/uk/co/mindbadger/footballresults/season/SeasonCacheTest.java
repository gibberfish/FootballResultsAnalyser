package uk.co.mindbadger.footballresults.season;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionImpl;

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
	private Season<String> mockSeason;
	
	@Mock
	private Division<String> mockDivision1;

	@Mock
	private Division<String> mockDivision2;

	@Mock
	private Division<String> mockDivision3;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		objectUnderTest = new SeasonCache ();
		
		when(mockSeason.getSeasonNumber()).thenReturn(2001);
		
		when(mockDivision1.getDivisionId()).thenReturn(divisionId1);
		when(mockDivision2.getDivisionId()).thenReturn(divisionId2);
		when(mockDivision3.getDivisionId()).thenReturn(divisionId3);
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
		SeasonDivision seasonDivision1 = new SeasonDivisionImpl ();
		seasonDivision1.setDivision(mockDivision1);
		seasonDivision1.setSeason(mockSeason);
		seasonDivision1.setDivisionPosition(1);

		SeasonDivision seasonDivision2 = new SeasonDivisionImpl ();
		seasonDivision2.setDivision(mockDivision2);
		seasonDivision2.setSeason(mockSeason);
		seasonDivision2.setDivisionPosition(2);

		// When
		DivisionCache divisionCache1 = objectUnderTest.getCacheForDivision(seasonDivision1);

		// Then
		Map<SeasonDivision<String,String>, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (1, divisionCaches.size());
		assertEquals (divisionCache1, divisionCaches.get(seasonDivision1));
		
		// When
		DivisionCache divisionCache2 = objectUnderTest.getCacheForDivision(seasonDivision2);
		
		// Then
		divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (2, divisionCaches.size());
		assertEquals (divisionCache2, divisionCaches.get(seasonDivision2));		
	}
	@Test
	public void shouldReturnExistingSeasonCacheIfItExists () {
		// Given
		SeasonDivision seasonDivision1 = new SeasonDivisionImpl ();
		seasonDivision1.setDivision(mockDivision1);
		seasonDivision1.setSeason(mockSeason);
		seasonDivision1.setDivisionPosition(1);

		DivisionCache divisionCache1 = objectUnderTest.getCacheForDivision(seasonDivision1);
		
		// When
		DivisionCache divisionCache2 = objectUnderTest.getCacheForDivision(seasonDivision1);
		
		// Then
		Map<SeasonDivision<String,String>, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (1, divisionCaches.size());
		assertEquals (divisionCache1, divisionCaches.get(seasonDivision1));
		assertEquals (divisionCache1, divisionCache2);
	}
	
	@Test
	public void shouldReturnSortedListOfDivisionsInCache () {
		// Given
		SeasonDivision seasonDivision1 = new SeasonDivisionImpl ();
		seasonDivision1.setDivision(mockDivision1);
		seasonDivision1.setSeason(mockSeason);
		seasonDivision1.setDivisionPosition(2);

		SeasonDivision seasonDivision2 = new SeasonDivisionImpl ();
		seasonDivision2.setDivision(mockDivision2);
		seasonDivision2.setSeason(mockSeason);
		seasonDivision2.setDivisionPosition(3);

		SeasonDivision seasonDivision3 = new SeasonDivisionImpl ();
		seasonDivision3.setDivision(mockDivision3);
		seasonDivision3.setSeason(mockSeason);
		seasonDivision3.setDivisionPosition(1);
		
		DivisionCache divisionCache1 = objectUnderTest.getCacheForDivision(seasonDivision1);
		DivisionCache divisionCache2 = objectUnderTest.getCacheForDivision(seasonDivision2);
		DivisionCache divisionCache3 = objectUnderTest.getCacheForDivision(seasonDivision3);
		
		// When
		List<SeasonDivision<String,String>> seasonDivisions = objectUnderTest.getSeasonDivisionsInCache ();
		
		// Then
		assertEquals(seasonDivision3, seasonDivisions.get(0));
		assertEquals(seasonDivision1, seasonDivisions.get(1));
		assertEquals(seasonDivision2, seasonDivisions.get(2));
	}
}
