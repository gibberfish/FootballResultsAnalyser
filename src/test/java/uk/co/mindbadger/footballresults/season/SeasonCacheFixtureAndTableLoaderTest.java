package uk.co.mindbadger.footballresults.season;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableFactory;
import uk.co.mindbadger.footballresults.table.TableRowFactory;
import uk.co.mindbadger.footballresultsanalyser.dao.FootballResultsAnalyserDAO;
import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class SeasonCacheFixtureAndTableLoaderTest {
	
	private SeasonCacheLoader objectUnderTest;
	
	@Mock
	private FootballResultsAnalyserDAO<String,String,String> mockDao;

	@Mock
	private TableRowFactory<String,String,String> mockTableRowFactory;
	
	@Mock
	private AnalyserCache mockAnalyserCache;
	
	@Mock
	private TableFactory mockTableFactory;
	
	@Mock
	private Season<String> mockSeason2015;

	@Mock
	private SeasonCache mockSeasonCache2015;

	@Mock
	private SeasonDivision<String, String> mockSeasonDivision1;

	@Mock
	private SeasonDivision<String, String> mockSeasonDivision2;

	@Mock
	private Division<String> mockDivision1;

	@Mock
	private Division<String> mockDivision2;

	@Mock
	private SeasonDivisionTeam<String, String, String> mockDiv1TeamA;

	@Mock
	private SeasonDivisionTeam<String, String, String> mockDiv1TeamB;

	@Mock
	private SeasonDivisionTeam<String, String, String> mockDiv1TeamC;

	@Mock
	private SeasonDivisionTeam<String, String, String> mockDiv1TeamD;

	@Mock
	private SeasonDivisionTeam<String, String, String> mockDiv1TeamE;

	@Mock
	private SeasonDivisionTeam<String, String, String> mockDiv1TeamF;

	@Mock
	private Fixture<String> mockFixtureAvsB;

	@Mock
	private Fixture<String> mockFixtureAvsC;

	@Mock
	private Fixture<String> mockFixtureBvsA;

	@Mock
	private Fixture<String> mockFixtureBvsC;

	@Mock
	private Fixture<String> mockFixtureCvsA;

	@Mock
	private Fixture<String> mockFixtureCvsB;

	@Mock
	private Fixture<String> mockFixtureDvsE;

	@Mock
	private Fixture<String> mockFixtureDvsF;

	@Mock
	private Fixture<String> mockFixtureEvsD;
	
	@Mock
	private Fixture<String> mockFixtureEvsF;

	@Mock
	private Fixture<String> mockFixtureFvsD;

	@Mock
	private Fixture<String> mockFixtureFvsE;

	@Mock
	private Table<String, String, String> mockInitialTableDiv1;

	@Mock
	private Table<String, String, String> mockInitialTableDiv2;

	@Mock
	private DivisionCache mockDivisionCache1;

	@Mock
	private DivisionCache mockDivisionCache2;

	@Mock
	private Table<String, String, String> mockTableDiv1Date1;

	@Mock
	private Table<String, String, String> mockTableDiv2Date1;

	@Mock
	private Table<String, String, String> mockTableDiv1Date2;

	@Mock
	private Table<String, String, String> mockTableDiv2Date2;

	@Mock
	private Table<String, String, String> mockTableDiv1Date3;

	@Mock
	private Table<String, String, String> mockTableDiv2Date3;

	@Mock
	private Table<String, String, String> mockTableDiv1Date4;

	@Mock
	private Table<String, String, String> mockTableDiv1Date5;

	@Mock
	private Table<String, String, String> mockTableDiv1Date6;

	@Mock
	private Table<String, String, String> mockTableDiv2Date6;

	@Mock
	private Table<String, String, String> mockTableDiv2Date7;

	@Mock
	private Table<String, String, String> mockTableDiv2Date8;

	@Mock
	private Team<String> mockTeamA;

	@Mock
	private Team<String> mockTeamB;

	@Mock
	private Team<String> mockTeamC;

	@Mock
	private Team<String> mockTeamD;

	@Mock
	private Team<String> mockTeamE;

	@Mock
	private Team<String> mockTeamF;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectUnderTest = new SeasonCacheLoader();
		objectUnderTest.setAnalyserCache(mockAnalyserCache);
		objectUnderTest.setDao(mockDao);
		objectUnderTest.setTableFactory(mockTableFactory);
		objectUnderTest.setTableRowFactory(mockTableRowFactory);
		
		when (mockSeason2015.getSeasonNumber()).thenReturn(2015);

		when (mockSeasonDivision1.getDivision()).thenReturn(mockDivision1);
		when (mockSeasonDivision2.getDivision()).thenReturn(mockDivision2);

		when (mockDivision1.getDivisionId()).thenReturn("DIV1");
		when (mockDivision2.getDivisionId()).thenReturn("DIV2");
		
		when (mockTeamA.getTeamId()).thenReturn("TEAMA");
		when (mockTeamB.getTeamId()).thenReturn("TEAMB");
		when (mockTeamC.getTeamId()).thenReturn("TEAMC");
		when (mockTeamD.getTeamId()).thenReturn("TEAMD");
		when (mockTeamE.getTeamId()).thenReturn("TEAME");
		when (mockTeamF.getTeamId()).thenReturn("TEAMF");
	}

	
	/*
	 * NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO!!!!!!
	 */
	
	@Test
	public void shouldLoadSeasonFromDataInDao () {
		// Given
		List<Fixture<String>> fixturesForDivision1 = new ArrayList<Fixture<String>> ();
		when (mockDao.getFixturesForDivisionInSeason(mockSeasonDivision1)).thenReturn(fixturesForDivision1);
		
		List<Fixture<String>> fixturesForDivision2 = new ArrayList<Fixture<String>> ();
		when (mockDao.getFixturesForDivisionInSeason(mockSeasonDivision2)).thenReturn(fixturesForDivision2);

		
		//when(mockTableRowFactory.createTableRowFromFixture(eq(mockTeamA), any(), any(), any())).thenReturn(mockTableRowTeamADate1);
		
		// **** DATE 1 ****
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);

		when(mockFixtureAvsB.getFixtureDate()).thenReturn(fixtureDate1);
		when(mockFixtureAvsB.getHomeTeam()).thenReturn(mockTeamA);
		when(mockFixtureAvsB.getAwayTeam()).thenReturn(mockTeamB);
//		when(mockTableDiv1Date1.getTableRowForTeam("TEAMA")).thenReturn(mockInitialTableRowTeamA);
//		when(mockTableRowFactory.createTableRowFromFixture(mockTeamA, mockTableDiv1Date1, mockInitialTableRowTeamA, mockFixtureAvsB)).thenReturn(mockTableRowTeamADate1);
//		when(mockTableDiv1Date1.getTableRowForTeam("TEAMB")).thenReturn(mockInitialTableRowTeamB);
//		when(mockTableRowFactory.createTableRowFromFixture(mockTeamA, mockTableDiv1Date1, mockInitialTableRowTeamB, mockFixtureAvsB)).thenReturn(mockTableRowTeamBDate1);
		fixturesForDivision1.add(mockFixtureAvsB);
		when(mockTableFactory.createTableFromPreviousTable(mockInitialTableDiv1)).thenReturn(mockTableDiv1Date1);

		when(mockFixtureDvsE.getFixtureDate()).thenReturn(fixtureDate1);
		when(mockFixtureDvsE.getHomeTeam()).thenReturn(mockTeamD);
		when(mockFixtureDvsE.getAwayTeam()).thenReturn(mockTeamE);
		fixturesForDivision2.add(mockFixtureDvsE);
		when(mockTableFactory.createTableFromPreviousTable(mockInitialTableDiv2)).thenReturn(mockTableDiv2Date1);
		
		// **** DATE 2 ****
		Calendar fixtureDate2 = Calendar.getInstance();
		fixtureDate2.set(Calendar.DAY_OF_MONTH, 2);

		when(mockFixtureAvsC.getFixtureDate()).thenReturn(fixtureDate2);
		when(mockFixtureAvsC.getHomeTeam()).thenReturn(mockTeamA);
		when(mockFixtureAvsC.getAwayTeam()).thenReturn(mockTeamC);
		fixturesForDivision1.add(mockFixtureAvsC);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDiv1Date1)).thenReturn(mockTableDiv1Date2);
		
		when(mockFixtureDvsF.getFixtureDate()).thenReturn(fixtureDate2);
		when(mockFixtureDvsF.getHomeTeam()).thenReturn(mockTeamD);
		when(mockFixtureDvsF.getAwayTeam()).thenReturn(mockTeamF);
		fixturesForDivision2.add(mockFixtureDvsF);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDiv2Date1)).thenReturn(mockTableDiv2Date2);


		// **** DATE 3 ****
		Calendar fixtureDate3 = Calendar.getInstance();
		fixtureDate3.set(Calendar.DAY_OF_MONTH, 3);
		when(mockFixtureBvsA.getFixtureDate()).thenReturn(fixtureDate3);
		when(mockFixtureBvsA.getHomeTeam()).thenReturn(mockTeamB);
		when(mockFixtureBvsA.getAwayTeam()).thenReturn(mockTeamA);
		when(mockFixtureEvsD.getFixtureDate()).thenReturn(fixtureDate3);
		when(mockFixtureEvsD.getHomeTeam()).thenReturn(mockTeamE);
		when(mockFixtureEvsD.getAwayTeam()).thenReturn(mockTeamD);
		fixturesForDivision1.add(mockFixtureBvsA);
		fixturesForDivision2.add(mockFixtureEvsD);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDiv1Date2)).thenReturn(mockTableDiv1Date3);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDiv2Date2)).thenReturn(mockTableDiv2Date3);

		// **** DATE 4 ****
		Calendar fixtureDate4 = Calendar.getInstance();
		fixtureDate4.set(Calendar.DAY_OF_MONTH, 4);
		when(mockFixtureBvsC.getFixtureDate()).thenReturn(fixtureDate4);
		when(mockFixtureBvsC.getHomeTeam()).thenReturn(mockTeamB);
		when(mockFixtureBvsC.getAwayTeam()).thenReturn(mockTeamC);
		fixturesForDivision1.add(mockFixtureBvsC);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDiv1Date3)).thenReturn(mockTableDiv1Date4);
		
		// **** DATE 5 ****
		Calendar fixtureDate5 = Calendar.getInstance();
		fixtureDate5.set(Calendar.DAY_OF_MONTH, 5);
		when(mockFixtureCvsA.getFixtureDate()).thenReturn(fixtureDate5);
		when(mockFixtureCvsA.getHomeTeam()).thenReturn(mockTeamC);
		when(mockFixtureCvsA.getAwayTeam()).thenReturn(mockTeamA);
		fixturesForDivision1.add(mockFixtureCvsA);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDiv1Date4)).thenReturn(mockTableDiv1Date5);

		// **** DATE 6 ****
		Calendar fixtureDate6 = Calendar.getInstance();
		fixtureDate6.set(Calendar.DAY_OF_MONTH, 6);
		when(mockFixtureCvsB.getFixtureDate()).thenReturn(fixtureDate6);
		when(mockFixtureCvsB.getHomeTeam()).thenReturn(mockTeamC);
		when(mockFixtureCvsB.getAwayTeam()).thenReturn(mockTeamB);
		when(mockFixtureFvsD.getFixtureDate()).thenReturn(fixtureDate6);
		when(mockFixtureFvsD.getHomeTeam()).thenReturn(mockTeamF);
		when(mockFixtureFvsD.getAwayTeam()).thenReturn(mockTeamD);
		fixturesForDivision1.add(mockFixtureCvsB);
		fixturesForDivision2.add(mockFixtureFvsD);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDiv1Date5)).thenReturn(mockTableDiv1Date6);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDiv2Date3)).thenReturn(mockTableDiv2Date6);

		// **** DATE 7 ****
		Calendar fixtureDate7 = Calendar.getInstance();
		fixtureDate7.set(Calendar.DAY_OF_MONTH, 7);
		when(mockFixtureFvsE.getFixtureDate()).thenReturn(fixtureDate7);
		when(mockFixtureFvsE.getHomeTeam()).thenReturn(mockTeamF);
		when(mockFixtureFvsE.getAwayTeam()).thenReturn(mockTeamE);
		fixturesForDivision2.add(mockFixtureFvsE);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDiv2Date6)).thenReturn(mockTableDiv2Date7);

		// **** DATE 8 ****
		Calendar fixtureDate8 = Calendar.getInstance();
		fixtureDate8.set(Calendar.DAY_OF_MONTH, 8);
		when(mockFixtureEvsF.getFixtureDate()).thenReturn(fixtureDate8);
		when(mockFixtureEvsF.getHomeTeam()).thenReturn(mockTeamE);
		when(mockFixtureEvsF.getAwayTeam()).thenReturn(mockTeamF);
		fixturesForDivision2.add(mockFixtureEvsF);
		when(mockTableFactory.createTableFromPreviousTable(mockTableDiv2Date7)).thenReturn(mockTableDiv2Date8);
		
		
		when(mockAnalyserCache.getCacheForSeason(2015)).thenReturn(mockSeasonCache2015);
		
		when(mockSeasonCache2015.getCacheForDivision("DIV1")).thenReturn(mockDivisionCache1);
		when(mockSeasonCache2015.getCacheForDivision("DIV2")).thenReturn(mockDivisionCache2);
		
		Set<SeasonDivision<String, String>> seasonDivisions = new HashSet<SeasonDivision<String, String>> ();
		seasonDivisions.add(mockSeasonDivision1);
		seasonDivisions.add(mockSeasonDivision2);
		when(mockDao.getDivisionsForSeason(mockSeason2015)).thenReturn(seasonDivisions);
		
		Set<SeasonDivisionTeam<String,String,String>> teamsForDivision1 = new HashSet<SeasonDivisionTeam<String, String, String>> ();
		teamsForDivision1.add(mockDiv1TeamA);
		teamsForDivision1.add(mockDiv1TeamB);
		teamsForDivision1.add(mockDiv1TeamC);
		when(mockDao.getTeamsForDivisionInSeason(mockSeasonDivision1)).thenReturn(teamsForDivision1);

		Set<SeasonDivisionTeam<String,String,String>> teamsForDivision2 = new HashSet<SeasonDivisionTeam<String, String, String>> ();
		teamsForDivision1.add(mockDiv1TeamD);
		teamsForDivision1.add(mockDiv1TeamE);
		teamsForDivision1.add(mockDiv1TeamF);
		when(mockDao.getTeamsForDivisionInSeason(mockSeasonDivision2)).thenReturn(teamsForDivision2);
		
		when(mockTableFactory.createInitialTable(mockSeasonDivision1, teamsForDivision1)).thenReturn(mockInitialTableDiv1);
		when(mockTableFactory.createInitialTable(mockSeasonDivision2, teamsForDivision2)).thenReturn(mockInitialTableDiv2);
		
		
		
		// When
		objectUnderTest.loadSeason(mockSeason2015);
		
		// Then
		Calendar seasonStartDateCalendar = Calendar.getInstance();
		seasonStartDateCalendar.set(Calendar.DAY_OF_MONTH, 0);
		seasonStartDateCalendar.set(Calendar.MONTH, 5);
		seasonStartDateCalendar.set(Calendar.YEAR, 2015);
		
		verify(mockAnalyserCache, times(1)).getCacheForSeason(2015);
		
		verify(mockSeasonCache2015,times(1)).getCacheForDivision("DIV1");
		verify(mockSeasonCache2015,times(1)).getCacheForDivision("DIV2");

		verify(mockDao, times(1)).getDivisionsForSeason(mockSeason2015);
		verify(mockDao, times(1)).getTeamsForDivisionInSeason(mockSeasonDivision1);
		verify(mockDao, times(1)).getTeamsForDivisionInSeason(mockSeasonDivision2);
		
		verify(mockTableFactory, times(1)).createInitialTable(mockSeasonDivision1, teamsForDivision1);
		verify(mockTableFactory, times(1)).createInitialTable(mockSeasonDivision2, teamsForDivision2);
		
		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockInitialTableDiv1);
		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockTableDiv1Date1);
		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockTableDiv1Date2);
		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockTableDiv1Date3);
		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockTableDiv1Date4);
		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockTableDiv1Date5);
		verify(mockTableFactory, never()).createTableFromPreviousTable(mockTableDiv1Date6);

		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockInitialTableDiv2);
		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockTableDiv2Date1);
		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockTableDiv2Date2);
		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockTableDiv2Date3);
		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockTableDiv2Date6);
		verify(mockTableFactory, times(1)).createTableFromPreviousTable(mockTableDiv2Date7);
		verify(mockTableFactory, never()).createTableFromPreviousTable(mockTableDiv2Date8);

		verify(mockDivisionCache1).addTableOnDate((Calendar)any(), eq(mockInitialTableDiv1));
		verify(mockDivisionCache1).addTableOnDate(fixtureDate1, mockTableDiv1Date1);
		verify(mockDivisionCache1).addTableOnDate(fixtureDate2, mockTableDiv1Date2);
		verify(mockDivisionCache1).addTableOnDate(fixtureDate3, mockTableDiv1Date3);
		verify(mockDivisionCache1).addTableOnDate(fixtureDate4, mockTableDiv1Date4);
		verify(mockDivisionCache1).addTableOnDate(fixtureDate5, mockTableDiv1Date5);
		verify(mockDivisionCache1).addTableOnDate(fixtureDate6, mockTableDiv1Date6);
		
		verify(mockDivisionCache2).addTableOnDate((Calendar)any(), eq(mockInitialTableDiv2));
		verify(mockDivisionCache2).addTableOnDate(fixtureDate1, mockTableDiv2Date1);
		verify(mockDivisionCache2).addTableOnDate(fixtureDate2, mockTableDiv2Date2);
		verify(mockDivisionCache2).addTableOnDate(fixtureDate3, mockTableDiv2Date3);
		verify(mockDivisionCache2).addTableOnDate(fixtureDate6, mockTableDiv2Date6);
		verify(mockDivisionCache2).addTableOnDate(fixtureDate7, mockTableDiv2Date7);
		verify(mockDivisionCache2).addTableOnDate(fixtureDate8, mockTableDiv2Date8);

		//verify(mockInitialTableDiv1.addRow(tableRow);)
		
	}

	
	
	
	
	
	
	
	
	
	
	
//	@Ignore ("THIS NEEDS FIXING!!!")
//	@Test
//	public void shouldGetDivisionsWhenLoadingSeason () {
//		// Given
//		List<Season<String>> list = new ArrayList<Season<String>> ();
//		list.add(mockSeason);
//		when(mockDao.getSeasons()).thenReturn(list);
//		objectUnderTest = new SeasonLoader(mockDao);
//		
//		// When
//		objectUnderTest.loadSeason(mockSeason);
//
//		// Then
//		// Called once for the constructor, once for the load
//		verify(mockDao,times(2)).getDivisionsForSeason(mockSeason);
//	}
//
//	@Test
//	public void getSeasonShouldThrowExceptionIfNotInitialised() {
//		// Given
//		Season season1 = domainObjectFactory.createSeason(1999);
//		Season season2 = domainObjectFactory.createSeason(2001);
//		Season season3 = domainObjectFactory.createSeason(2000);
//		
//		List<Season<String>> seasons = new ArrayList<Season<String>> ();
//		seasons.add(season1);
//		seasons.add(season2);
//		seasons.add(season3);
//		
//		when(mockDao.getSeasons()).thenReturn(seasons);
//		
//		// When
//		try {
//			Season<String> season = objectUnderTest.getSeason();
//			fail("Should throw an InitialisationException");
//		} catch (InitialisationException e) {
//			// Then
//			verify(mockDao.getSeasons(), never());
//		}
//	}
//
//	@Test
//	public void getSeasonShouldReturnLatestSeasonIfInitialised() {
//		// Given
//		Season season1 = domainObjectFactory.createSeason(1999);
//		Season season2 = domainObjectFactory.createSeason(2001);
//		Season season3 = domainObjectFactory.createSeason(2000);
//		
//		List<Season<String>> seasons = new ArrayList<Season<String>> ();
//		seasons.add(season1);
//		seasons.add(season2);
//		seasons.add(season3);
//		
//		when(mockDao.getSeasons()).thenReturn(seasons);
//		
//		// When
//		objectUnderTest.loadLatestSeason();
//		Season<String> season = objectUnderTest.getSeason();
//		
//		// Then
//		assertEquals(season2, season);
//	}

}
