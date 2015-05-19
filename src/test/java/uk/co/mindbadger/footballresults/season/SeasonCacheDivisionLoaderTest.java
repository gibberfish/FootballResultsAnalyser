package uk.co.mindbadger.footballresults.season;

import static org.mockito.Matchers.eq;
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

	@Mock
	private Table<String, String, String> mockTableDate4;

	@Mock
	private Table<String, String, String> mockTableDate5;

	@Mock
	private TeamFixtureContext mockFixture1HomeTeamContext;
	
	@Mock
	private TeamFixtureContext mockFixture1AwayTeamContext;

	@Mock
	private TeamFixtureContext mockFixture2HomeTeamContext;
	
	@Mock
	private TeamFixtureContext mockFixture2AwayTeamContext;

	@Mock
	private TeamFixtureContext mockFixture3HomeTeamContext;
	
	@Mock
	private TeamFixtureContext mockFixture3AwayTeamContext;

	@Mock
	private TeamFixtureContext mockFixture4HomeTeamContext;
	
	@Mock
	private TeamFixtureContext mockFixture4AwayTeamContext;

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
				
		when(mockSeasonCache2015.getCacheForDivision(mockSeasonDivision1)).thenReturn(mockDivisionCache1);
		
		// SETUP FIXTURES
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);
		fixtureDate1.set(Calendar.MONTH, 8);
		when(mockFixture1.getFixtureDate()).thenReturn(fixtureDate1);
		when(mockFixture1.getHomeGoals()).thenReturn(2);
		when(mockFixture1.getAwayGoals()).thenReturn(1);
		
		Calendar fixtureDate2 = Calendar.getInstance();
		fixtureDate2.set(Calendar.DAY_OF_MONTH, 2);
		fixtureDate2.set(Calendar.MONTH, 8);
		when(mockFixture2.getFixtureDate()).thenReturn(fixtureDate2);
		when(mockFixture2.getHomeGoals()).thenReturn(2);
		when(mockFixture2.getAwayGoals()).thenReturn(1);
		
		Calendar fixtureDate3 = Calendar.getInstance();
		fixtureDate3.set(Calendar.DAY_OF_MONTH, 3);
		fixtureDate3.set(Calendar.MONTH, 8);
		when(mockFixture3.getFixtureDate()).thenReturn(fixtureDate3);
		when(mockFixture3.getHomeGoals()).thenReturn(2);
		when(mockFixture3.getAwayGoals()).thenReturn(1);

		Calendar fixtureDate4 = Calendar.getInstance();
		fixtureDate4.set(Calendar.DAY_OF_MONTH, 4);
		fixtureDate4.set(Calendar.MONTH, 8);
		when(mockFixture4.getFixtureDate()).thenReturn(fixtureDate4);
		when(mockFixture4.getHomeGoals()).thenReturn(null);
		when(mockFixture4.getAwayGoals()).thenReturn(null);

		// DAO TO RETURN THE ABOVE FIXTURES
		List<Fixture<String>> fixturesForDivision1 = new ArrayList<Fixture<String>> ();
		fixturesForDivision1.add(mockFixture1);
		fixturesForDivision1.add(mockFixture2);
		fixturesForDivision1.add(mockFixture3);
		fixturesForDivision1.add(mockFixture4);
		when (mockDao.getFixturesForDivisionInSeason(mockSeasonDivision1)).thenReturn(fixturesForDivision1);

		// DAO TO RETURN TEAMS IN DIVISION
		Set<SeasonDivisionTeam<String,String,String>> teamsForDivision1 = new HashSet<SeasonDivisionTeam<String, String, String>> ();
		teamsForDivision1.add(mockDiv1TeamA);
		teamsForDivision1.add(mockDiv1TeamB);
		teamsForDivision1.add(mockDiv1TeamC);
		when(mockDao.getTeamsForDivisionInSeason(mockSeasonDivision1)).thenReturn(teamsForDivision1);
		
		// SEASON DIVISION RECORD
		when(mockSeasonDivision1.getSeason()).thenReturn(mockSeason2015);
		when(mockSeason2015.getSeasonNumber()).thenReturn(2015);

		Calendar initialDate = objectUnderTest.createInitialTableDate(2015);
		
		// TABLES
		when(mockTableFactory.createInitialTable(eq(mockSeasonDivision1), eq(teamsForDivision1))).thenReturn(mockInitialTableDiv1);
		when(mockTableFactory.createTableFromPreviousTable(mockInitialTableDiv1)).thenReturn(mockTableDate1);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDate1)).thenReturn(mockTableDate2);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDate2)).thenReturn(mockTableDate3);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDate3)).thenReturn(mockTableDate4);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDate4)).thenReturn(mockTableDate5);
		
		// CONTEXTS
		when(mockSeasonCacheFixtureAndTableLoader.loadTeamFixtureContextsForTeam(eq(true), eq(mockFixture1), eq(fixtureDate1), eq(mockDivisionCache1), eq(mockInitialTableDiv1))).thenReturn(mockFixture1HomeTeamContext);
		when(mockSeasonCacheFixtureAndTableLoader.loadTeamFixtureContextsForTeam(eq(false), eq(mockFixture1), eq(fixtureDate1), eq(mockDivisionCache1), eq(mockInitialTableDiv1))).thenReturn(mockFixture1AwayTeamContext);
		when(mockSeasonCacheFixtureAndTableLoader.loadTeamFixtureContextsForTeam(eq(true), eq(mockFixture2), eq(fixtureDate2), eq(mockDivisionCache1), eq(mockTableDate1))).thenReturn(mockFixture2HomeTeamContext);;
		when(mockSeasonCacheFixtureAndTableLoader.loadTeamFixtureContextsForTeam(eq(false), eq(mockFixture2), eq(fixtureDate2), eq(mockDivisionCache1), eq(mockTableDate1))).thenReturn(mockFixture2AwayTeamContext);;
		when(mockSeasonCacheFixtureAndTableLoader.loadTeamFixtureContextsForTeam(eq(true), eq(mockFixture3), eq(fixtureDate3), eq(mockDivisionCache1), eq(mockTableDate2))).thenReturn(mockFixture3HomeTeamContext);;
		when(mockSeasonCacheFixtureAndTableLoader.loadTeamFixtureContextsForTeam(eq(false), eq(mockFixture3), eq(fixtureDate3), eq(mockDivisionCache1), eq(mockTableDate2))).thenReturn(mockFixture3AwayTeamContext);;
		when(mockSeasonCacheFixtureAndTableLoader.loadTeamFixtureContextsForTeam(eq(true), eq(mockFixture4), eq(fixtureDate4), eq(mockDivisionCache1), eq(mockTableDate3))).thenReturn(mockFixture4HomeTeamContext);;
		when(mockSeasonCacheFixtureAndTableLoader.loadTeamFixtureContextsForTeam(eq(false), eq(mockFixture4), eq(fixtureDate4), eq(mockDivisionCache1), eq(mockTableDate3))).thenReturn(mockFixture4AwayTeamContext);;
		
		// When
		objectUnderTest.loadDivision(mockSeasonDivision1, mockSeasonCache2015);
		
		// Then
		verify (mockSeasonCache2015,times(1)).getCacheForDivision(eq(mockSeasonDivision1));
		verify (mockDao,times(1)).getFixturesForDivisionInSeason(eq(mockSeasonDivision1));
		verify (mockDao,times(1)).getTeamsForDivisionInSeason(eq(mockSeasonDivision1));
		verify (mockTableFactory,times(1)).createInitialTable(eq(mockSeasonDivision1), eq(teamsForDivision1));
		
		verify (mockDivisionCache1,times(1)).addTableOnDate(eq(initialDate), eq(mockInitialTableDiv1));
		verify (mockTableFactory,times(1)).createTableFromPreviousTable(eq(mockInitialTableDiv1));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadTeamFixtureContextsForTeam(eq(true), eq(mockFixture1), eq(fixtureDate1), eq(mockDivisionCache1), eq(mockInitialTableDiv1));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadTeamFixtureContextsForTeam(eq(false), eq(mockFixture1), eq(fixtureDate1), eq(mockDivisionCache1), eq(mockInitialTableDiv1));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixture(eq(mockFixture1), eq(fixtureDate1), eq(mockDivisionCache1));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixtureIntoTable(eq(mockFixture1), eq(mockTableDate1), eq(mockFixture1HomeTeamContext), eq(mockFixture1AwayTeamContext));

		verify (mockDivisionCache1,times(1)).addTableOnDate(eq(fixtureDate1), eq(mockTableDate1));
		verify (mockTableFactory,times(1)).createTableFromPreviousTable(eq(mockTableDate1));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadTeamFixtureContextsForTeam(eq(true), eq(mockFixture2), eq(fixtureDate2), eq(mockDivisionCache1), eq(mockTableDate1));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadTeamFixtureContextsForTeam(eq(false), eq(mockFixture2), eq(fixtureDate2), eq(mockDivisionCache1), eq(mockTableDate1));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixture(eq(mockFixture2), eq(fixtureDate2), eq(mockDivisionCache1));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixtureIntoTable(eq(mockFixture2), eq(mockTableDate2), eq(mockFixture2HomeTeamContext), eq(mockFixture2AwayTeamContext));

		verify (mockDivisionCache1,times(1)).addTableOnDate(eq(fixtureDate2), eq(mockTableDate2));
		verify (mockTableFactory,times(1)).createTableFromPreviousTable(eq(mockTableDate2));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadTeamFixtureContextsForTeam(eq(true), eq(mockFixture3), eq(fixtureDate3), eq(mockDivisionCache1), eq(mockTableDate2));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadTeamFixtureContextsForTeam(eq(false), eq(mockFixture3), eq(fixtureDate3), eq(mockDivisionCache1), eq(mockTableDate2));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixture(eq(mockFixture3), eq(fixtureDate3), eq(mockDivisionCache1));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixtureIntoTable(eq(mockFixture3), eq(mockTableDate3), eq(mockFixture3HomeTeamContext), eq(mockFixture3AwayTeamContext));

		verify (mockDivisionCache1,times(1)).addTableOnDate(eq(fixtureDate3), eq(mockTableDate3));
		verify (mockTableFactory,times(1)).createTableFromPreviousTable(eq(mockTableDate3));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadTeamFixtureContextsForTeam(eq(true), eq(mockFixture4), eq(fixtureDate4), eq(mockDivisionCache1), eq(mockTableDate3));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadTeamFixtureContextsForTeam(eq(false), eq(mockFixture4), eq(fixtureDate4), eq(mockDivisionCache1), eq(mockTableDate3));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixture(eq(mockFixture4), eq(fixtureDate4), eq(mockDivisionCache1));
		verify (mockSeasonCacheFixtureAndTableLoader,times(1)).loadFixtureIntoTable(eq(mockFixture4), eq(mockTableDate4), eq(mockFixture4HomeTeamContext), eq(mockFixture4AwayTeamContext));

		verify (mockDivisionCache1,times(1)).addTableOnDate(eq(fixtureDate4), eq(mockTableDate4));
	}
}
