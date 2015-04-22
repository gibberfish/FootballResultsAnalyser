package uk.co.mindbadger.footballresults.season;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableFactory;
import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class SeasonCacheDivisionLoaderTest {
	
	private SeasonCacheDivisionLoader objectUnderTest;
	
	@Mock
	private SeasonCacheFixtureAndTableLoader mockSeasonCacheFixtureAndTableLoader;
	
	@Mock
	private FootballResultsAnalyserDAO<String,String,String> mockDao;

	@Mock
	private TableFactory mockTableFactory;

	@Mock
	private SeasonCache mockSeasonCache2015;
	
	@Mock
	private SeasonDivision<String, String> mockSeasonDivision1;
	
	@Mock
	private DivisionCache mockDivisionCache1;
	
	@Mock
	private SeasonDivisionTeam<String, String, String> mockDiv1TeamA;

	@Mock
	private SeasonDivisionTeam<String, String, String> mockDiv1TeamB;

	@Mock
	private SeasonDivisionTeam<String, String, String> mockDiv1TeamC;

	@Mock
	private Table<String, String, String> mockInitialTableDiv1;

	@Mock
	private Fixture<String> mockFixture1;

	@Mock
	private Fixture<String> mockFixture2;

	@Mock
	private Fixture<String> mockFixture3;

	@Mock
	private Division<String> mockDivision1;

	@Mock
	private Season<String> mockSeason2015;

	@Mock
	private Table<String, String, String> mockTableDate1;

	@Mock
	private Table<String, String, String> mockTableDate2;

	@Mock
	private Table<String, String, String> mockTableDate3;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectUnderTest = new SeasonCacheDivisionLoader();
		objectUnderTest.setDao(mockDao);
		objectUnderTest.setSeasonCacheFixtureAndTableLoader(mockSeasonCacheFixtureAndTableLoader);
		objectUnderTest.setTableFactory(mockTableFactory);
	}

	@Test
	public void shouldLoadDivisions () {
		// Given
		when (mockSeasonDivision1.getDivision()).thenReturn(mockDivision1);
		when (mockDivision1.getDivisionId()).thenReturn("DIV1");
				
		when(mockSeasonCache2015.getCacheForDivision("DIV1")).thenReturn(mockDivisionCache1);
		
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);
		when(mockFixture1.getFixtureDate()).thenReturn(fixtureDate1);
		
		Calendar fixtureDate2 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 2);
		when(mockFixture2.getFixtureDate()).thenReturn(fixtureDate2);
		
		Calendar fixtureDate3 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 3);
		when(mockFixture3.getFixtureDate()).thenReturn(fixtureDate3);

		List<Fixture<String>> fixturesForDivision1 = new ArrayList<Fixture<String>> ();
		fixturesForDivision1.add(mockFixture1);
		fixturesForDivision1.add(mockFixture2);
		fixturesForDivision1.add(mockFixture3);
		when (mockDao.getFixturesForDivisionInSeason(mockSeasonDivision1)).thenReturn(fixturesForDivision1);

		Set<SeasonDivisionTeam<String,String,String>> teamsForDivision1 = new HashSet<SeasonDivisionTeam<String, String, String>> ();
		teamsForDivision1.add(mockDiv1TeamA);
		teamsForDivision1.add(mockDiv1TeamB);
		teamsForDivision1.add(mockDiv1TeamC);
		when(mockDao.getTeamsForDivisionInSeason(mockSeasonDivision1)).thenReturn(teamsForDivision1);
		
		when(mockTableFactory.createInitialTable(mockSeasonDivision1, teamsForDivision1)).thenReturn(mockInitialTableDiv1);
		
		when(mockSeasonDivision1.getSeason()).thenReturn(mockSeason2015);
		when(mockSeason2015.getSeasonNumber()).thenReturn(2015);

		Calendar initialDate = objectUnderTest.createInitialTableDate(2015);
		when (mockSeasonCacheFixtureAndTableLoader.loadFixtureAndTable(mockFixture1, initialDate, mockDivisionCache1, mockInitialTableDiv1)).thenReturn (mockTableDate1);
		when (mockSeasonCacheFixtureAndTableLoader.loadFixtureAndTable(mockFixture2, fixtureDate1, mockDivisionCache1, mockTableDate1)).thenReturn (mockTableDate2);
		when (mockSeasonCacheFixtureAndTableLoader.loadFixtureAndTable(mockFixture3, fixtureDate2, mockDivisionCache1, mockTableDate2)).thenReturn (mockTableDate3);
		
		// When
		objectUnderTest.loadDivision(mockSeasonDivision1, mockSeasonCache2015);
		
		// Then
		verify (mockSeasonCache2015,times(1)).getCacheForDivision("DIV1");
		verify (mockDao,times(1)).getFixturesForDivisionInSeason(mockSeasonDivision1);
		verify (mockDao,times(1)).getTeamsForDivisionInSeason(mockSeasonDivision1);
		verify (mockTableFactory,times(1)).createInitialTable(mockSeasonDivision1, teamsForDivision1);
		
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixtureAndTable(mockFixture1, initialDate, mockDivisionCache1, mockInitialTableDiv1);
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixtureAndTable(mockFixture2, fixtureDate1, mockDivisionCache1, mockTableDate1);
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixtureAndTable(mockFixture3, fixtureDate2, mockDivisionCache1, mockTableDate2);
		
		verify (mockDivisionCache1,times(1)).addTableOnDate(fixtureDate3, mockTableDate3);		
	}
}
