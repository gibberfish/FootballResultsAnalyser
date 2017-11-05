package mindbadger.football.caches;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.football.caches.DivisionCache;
import mindbadger.football.caches.SeasonCache;
import mindbadger.football.domain.Division;
import mindbadger.football.domain.Season;
import mindbadger.football.domain.SeasonDivision;

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
	private Season mockSeason;
	
	@Mock
	private Division mockDivision1;

	@Mock
	private Division mockDivision2;

	@Mock
	private Division mockDivision3;

	@Mock
	private SeasonDivision mockSeasonDivision1;

	@Mock
	private SeasonDivision mockSeasonDivision2;

	@Mock
	private SeasonDivision mockSeasonDivision3;

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
		Map<SeasonDivision, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		
		// Then
		assertEquals (0, divisionCaches.size());
	}
	
	@Test
	public void shouldAddNewDivisionCacheIfOneDoesntExist () {
		// Given
		when (mockSeasonDivision1.getDivision()).thenReturn(mockDivision1);
		when (mockSeasonDivision1.getSeason()).thenReturn(mockSeason);
		when (mockSeasonDivision1.getDivisionPosition()).thenReturn(1);

		when (mockSeasonDivision2.getDivision()).thenReturn(mockDivision2);
		when (mockSeasonDivision2.getSeason()).thenReturn(mockSeason);
		when (mockSeasonDivision2.getDivisionPosition()).thenReturn(2);

		// When
		DivisionCache divisionCache1 = objectUnderTest.getCacheForDivision(mockSeasonDivision1);

		// Then
		Map<SeasonDivision, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
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
		when (mockSeasonDivision1.getDivision()).thenReturn(mockDivision1);
		when (mockSeasonDivision1.getSeason()).thenReturn(mockSeason);
		when (mockSeasonDivision1.getDivisionPosition()).thenReturn(1);

		DivisionCache divisionCache1 = objectUnderTest.getCacheForDivision(mockSeasonDivision1);
		
		// When
		DivisionCache divisionCache2 = objectUnderTest.getCacheForDivision(mockSeasonDivision1);
		
		// Then
		Map<SeasonDivision, DivisionCache> divisionCaches = objectUnderTest.getDivisionCaches();
		assertEquals (1, divisionCaches.size());
		assertEquals (divisionCache1, divisionCaches.get(mockSeasonDivision1));
		assertEquals (divisionCache1, divisionCache2);
	}
	
	@Test
	public void shouldReturnSortedListOfDivisionsInCache () {
		// Given
		when (mockSeasonDivision1.getDivision()).thenReturn(mockDivision1);
		when (mockSeasonDivision1.getSeason()).thenReturn(mockSeason);
		when (mockSeasonDivision1.getDivisionPosition()).thenReturn(2);
		when (mockSeasonDivision1.compareTo(mockSeasonDivision2)).thenReturn (-1);
		when (mockSeasonDivision1.compareTo(mockSeasonDivision3)).thenReturn (1);

		when (mockSeasonDivision2.getDivision()).thenReturn(mockDivision2);
		when (mockSeasonDivision2.getSeason()).thenReturn(mockSeason);
		when (mockSeasonDivision2.getDivisionPosition()).thenReturn(3);
		when (mockSeasonDivision2.compareTo(mockSeasonDivision1)).thenReturn (1);
		when (mockSeasonDivision2.compareTo(mockSeasonDivision3)).thenReturn (1);

		when (mockSeasonDivision3.getDivision()).thenReturn(mockDivision3);
		when (mockSeasonDivision3.getSeason()).thenReturn(mockSeason);
		when (mockSeasonDivision3.getDivisionPosition()).thenReturn(1);
		when (mockSeasonDivision3.compareTo(mockSeasonDivision1)).thenReturn (-1);
		when (mockSeasonDivision3.compareTo(mockSeasonDivision2)).thenReturn (-1);
		
		// Ensure the caches are initialised
		objectUnderTest.getCacheForDivision(mockSeasonDivision1);
		objectUnderTest.getCacheForDivision(mockSeasonDivision2);
		objectUnderTest.getCacheForDivision(mockSeasonDivision3);
		
		// When
		List<SeasonDivision> seasonDivisions = objectUnderTest.getSeasonDivisionsInCache ();
		
		// Then
		assertEquals(mockSeasonDivision3, seasonDivisions.get(0));
		assertEquals(mockSeasonDivision1, seasonDivisions.get(1));
		assertEquals(mockSeasonDivision2, seasonDivisions.get(2));
	}
}
