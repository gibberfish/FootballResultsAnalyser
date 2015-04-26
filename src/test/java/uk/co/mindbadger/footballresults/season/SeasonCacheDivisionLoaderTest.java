package uk.co.mindbadger.footballresults.season;

import static org.mockito.Mockito.*;

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
	private Fixture<String> mockFixture4;
	
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
				
		when(mockSeasonCache2015.getCacheForDivision(mockDivision1)).thenReturn(mockDivisionCache1);
		
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);
		when(mockFixture1.getFixtureDate()).thenReturn(fixtureDate1);
		when(mockFixture1.getHomeGoals()).thenReturn(2);
		when(mockFixture1.getAwayGoals()).thenReturn(1);
		
		Calendar fixtureDate2 = Calendar.getInstance();
		fixtureDate2.set(Calendar.DAY_OF_MONTH, 2);
		when(mockFixture2.getFixtureDate()).thenReturn(fixtureDate2);
		when(mockFixture2.getHomeGoals()).thenReturn(2);
		when(mockFixture2.getAwayGoals()).thenReturn(1);
		
		Calendar fixtureDate3 = Calendar.getInstance();
		fixtureDate3.set(Calendar.DAY_OF_MONTH, 3);
		when(mockFixture3.getFixtureDate()).thenReturn(fixtureDate3);
		when(mockFixture3.getHomeGoals()).thenReturn(2);
		when(mockFixture3.getAwayGoals()).thenReturn(1);

		Calendar fixtureDate4 = Calendar.getInstance();
		fixtureDate4.set(Calendar.DAY_OF_MONTH, 4);
		when(mockFixture4.getFixtureDate()).thenReturn(fixtureDate4);
		when(mockFixture4.getHomeGoals()).thenReturn(null);
		when(mockFixture4.getAwayGoals()).thenReturn(null);

		List<Fixture<String>> fixturesForDivision1 = new ArrayList<Fixture<String>> ();
		fixturesForDivision1.add(mockFixture1);
		fixturesForDivision1.add(mockFixture2);
		fixturesForDivision1.add(mockFixture3);
		fixturesForDivision1.add(mockFixture4);
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
		when (mockSeasonCacheFixtureAndTableLoader.loadFixtureIntoTable(mockFixture1, initialDate, mockDivisionCache1, mockInitialTableDiv1)).thenReturn (mockTableDate1);
		when (mockSeasonCacheFixtureAndTableLoader.loadFixtureIntoTable(mockFixture2, fixtureDate1, mockDivisionCache1, mockTableDate1)).thenReturn (mockTableDate2);
		when (mockSeasonCacheFixtureAndTableLoader.loadFixtureIntoTable(mockFixture3, fixtureDate2, mockDivisionCache1, mockTableDate2)).thenReturn (mockTableDate3);
		
		// When
		objectUnderTest.loadDivision(mockSeasonDivision1, mockSeasonCache2015);
		
		// Then
		verify (mockSeasonCache2015,times(1)).getCacheForDivision(mockDivision1);
		verify (mockDao,times(1)).getFixturesForDivisionInSeason(mockSeasonDivision1);
		verify (mockDao,times(1)).getTeamsForDivisionInSeason(mockSeasonDivision1);
		verify (mockTableFactory,times(1)).createInitialTable(mockSeasonDivision1, teamsForDivision1);
		
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixtureIntoTable(mockFixture1, initialDate, mockDivisionCache1, mockInitialTableDiv1);
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixtureIntoTable(mockFixture2, fixtureDate1, mockDivisionCache1, mockTableDate1);
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixtureIntoTable(mockFixture3, fixtureDate2, mockDivisionCache1, mockTableDate2);
		verify (mockSeasonCacheFixtureAndTableLoader,never()).loadFixtureIntoTable(mockFixture4, fixtureDate3, mockDivisionCache1, mockTableDate3);
		
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixture(mockFixture1, fixtureDate1, mockDivisionCache1);
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixture(mockFixture2, fixtureDate2, mockDivisionCache1);
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixture(mockFixture3, fixtureDate3, mockDivisionCache1);
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixture(mockFixture4, fixtureDate4, mockDivisionCache1);
		
		verify (mockDivisionCache1,times(1)).addTableOnDate(fixtureDate3, mockTableDate3);		
		verify (mockDivisionCache1,never()).addTableOnDate(fixtureDate4, mockTableDate3);
	}
}
