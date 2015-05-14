package uk.co.mindbadger.footballresults.season;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableFactory;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class SeasonCacheFixtureAndTableLoaderTest {
	
	private SeasonCacheFixtureAndTableLoader objectUnderTest;
	
	@Mock
	private TableFactory mockTableFactory;
	
	@Mock
	private TableRowFactory<String,String,String> mockTableRowFactory;
	
	@Mock
	private TeamFixtureContextFactory mockTeamFixtureContextFactory;
	
	@Mock
	private Fixture<String> mockFixture1;

	@Mock
	private DivisionCache mockDivisionCache1;

	@Mock
	private Table<String, String, String> mockTableDate1;

	@Mock
	private Table<String, String, String> mockTableDate2;

	@Mock
	private Team<String> mockHomeTeam;

	@Mock
	private Team<String> mockAwayTeam;

	@Mock
	private TableRow<String, String, String> mockTable1;

	@Mock
	private TableRow<String, String, String> mockHomeTableRow;

	@Mock
	private TableRow<String, String, String> mockAwayTableRow;
	
	@Mock
	private TeamFixtureContext mockHomeTeamFixtureContext;

	@Mock
	private TeamFixtureContext mockAwayTeamFixtureContext;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectUnderTest = new SeasonCacheFixtureAndTableLoader();
		objectUnderTest.setTableFactory(mockTableFactory);
		objectUnderTest.setTableRowFactory(mockTableRowFactory);
		objectUnderTest.setTeamFixtureContextFactory(mockTeamFixtureContextFactory);
	}
	
	@Test
	public void shouldSaveAPlayedFixture () {
		// Given
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);
		when(mockFixture1.getFixtureDate()).thenReturn(fixtureDate1);

		when(mockFixture1.getHomeTeam()).thenReturn(mockHomeTeam);
		when(mockFixture1.getAwayTeam()).thenReturn(mockAwayTeam);
		when(mockFixture1.getHomeGoals()).thenReturn(2);
		when(mockFixture1.getAwayGoals()).thenReturn(1);

		// When
		objectUnderTest.loadFixture(mockFixture1, fixtureDate1, mockDivisionCache1);
		
		// Then
		verify(mockDivisionCache1,times(1)).addFixtureOnDate(fixtureDate1, mockFixture1);
	}

	@Test
	public void shouldSaveAnUnplayedFixture () {
		// Given
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);
		when(mockFixture1.getFixtureDate()).thenReturn(fixtureDate1);

		when(mockFixture1.getHomeTeam()).thenReturn(mockHomeTeam);
		when(mockFixture1.getAwayTeam()).thenReturn(mockAwayTeam);
		when(mockFixture1.getHomeGoals()).thenReturn(null);
		when(mockFixture1.getAwayGoals()).thenReturn(null);

		// When
		objectUnderTest.loadFixture(mockFixture1, fixtureDate1, mockDivisionCache1);
		
		// Then
		verify(mockDivisionCache1,times(1)).addFixtureOnDate(fixtureDate1, mockFixture1);
	}

	@Test
	public void shouldLoadPlayedFixtureIntoTableWhereTheDateHasNotChanged () {
		// Given
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);
		when(mockFixture1.getFixtureDate()).thenReturn(fixtureDate1);

		when(mockFixture1.getHomeTeam()).thenReturn(mockHomeTeam);
		when(mockFixture1.getAwayTeam()).thenReturn(mockAwayTeam);
		when(mockFixture1.getHomeGoals()).thenReturn(2);
		when(mockFixture1.getAwayGoals()).thenReturn(1);

		
		when(mockHomeTeam.getTeamId()).thenReturn("HomeId");
		when(mockAwayTeam.getTeamId()).thenReturn("AwayId");
		
		when(mockTableDate1.getTableRowForTeam("HomeId")).thenReturn(mockHomeTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockHomeTableRow);

		when(mockTableDate1.getTableRowForTeam("AwayId")).thenReturn(mockAwayTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockAwayTableRow);
		
		// When
		objectUnderTest.loadFixtureIntoTable(mockFixture1, mockTableDate1);
		
		// Then
		verify(mockDivisionCache1,never()).addTableOnDate(fixtureDate1, mockTableDate1);
		verify(mockTableFactory,never()).createTableFromPreviousTable(mockTableDate1);
		
		verify(mockTableDate1,times(1)).getTableRowForTeam("HomeId");
		verify(mockTableRowFactory,times(1)).createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockHomeTableRow, mockFixture1);
		
		verify(mockTableDate1,times(1)).getTableRowForTeam("AwayId");
		verify(mockTableRowFactory,times(1)).createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockAwayTableRow, mockFixture1);
	}

	@Test
	public void shouldLoadPlayedFixtureIntoTableWhereTheDateHasChanged () {
		// Given
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);

		Calendar fixtureDate2 = Calendar.getInstance();
		fixtureDate2.set(Calendar.DAY_OF_MONTH, 2);
		
		when(mockFixture1.getFixtureDate()).thenReturn(fixtureDate2);

		when(mockTableFactory.createTableFromPreviousTable(mockTableDate1)).thenReturn(mockTableDate2);

		when(mockFixture1.getHomeTeam()).thenReturn(mockHomeTeam);
		when(mockFixture1.getAwayTeam()).thenReturn(mockAwayTeam);
		when(mockFixture1.getHomeGoals()).thenReturn(2);
		when(mockFixture1.getAwayGoals()).thenReturn(1);

		
		when(mockHomeTeam.getTeamId()).thenReturn("HomeId");
		when(mockAwayTeam.getTeamId()).thenReturn("AwayId");
		
		when(mockTableDate1.getTableRowForTeam("HomeId")).thenReturn(mockHomeTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockHomeTableRow);

		when(mockTableDate1.getTableRowForTeam("AwayId")).thenReturn(mockAwayTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockAwayTableRow);

		// When
		objectUnderTest.loadFixtureIntoTable(mockFixture1, mockTableDate1);
		
		// Then
		verify(mockTableDate1,times(1)).getTableRowForTeam("HomeId");
		verify(mockTableRowFactory,times(1)).createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockHomeTableRow, mockFixture1);
		
		verify(mockTableDate1,times(1)).getTableRowForTeam("AwayId");
		verify(mockTableRowFactory,times(1)).createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockAwayTableRow, mockFixture1);
	}
	
	@Test
	public void shouldNotUpdateTableIfTheFixtureIsUnplayed () {
		// Given
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);

		Calendar fixtureDate2 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 2);
		
		when(mockFixture1.getFixtureDate()).thenReturn(fixtureDate2);

		when(mockTableFactory.createTableFromPreviousTable(mockTableDate1)).thenReturn(mockTableDate2);

		when(mockFixture1.getHomeTeam()).thenReturn(mockHomeTeam);
		when(mockFixture1.getAwayTeam()).thenReturn(mockAwayTeam);
		when(mockFixture1.getHomeGoals()).thenReturn(null);
		when(mockFixture1.getAwayGoals()).thenReturn(null);
		
		when(mockHomeTeam.getTeamId()).thenReturn("HomeId");
		when(mockAwayTeam.getTeamId()).thenReturn("AwayId");
		
		when(mockTableDate1.getTableRowForTeam("HomeId")).thenReturn(mockHomeTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockHomeTableRow);

		when(mockTableDate1.getTableRowForTeam("AwayId")).thenReturn(mockAwayTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockAwayTableRow);
		
		// When
		objectUnderTest.loadFixtureIntoTable(mockFixture1, mockTableDate1);
		
		// Then
		verify(mockTableRowFactory,never()).createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockHomeTableRow, mockFixture1);
		verify(mockTableRowFactory,never()).createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockAwayTableRow, mockFixture1);

		verify(mockDivisionCache1,never()).addTableOnDate(fixtureDate1, mockTableDate1);
		verify(mockTableFactory,never()).createTableFromPreviousTable(mockTableDate1);
		verify(mockTableDate1,never()).getTableRowForTeam("HomeId");
		verify(mockTableDate1,never()).getTableRowForTeam("AwayId");
	}
	
	@Test
	public void shouldLoadTeamFixtureContextForHomeTeamAboveAwayTeam () {
		// Given
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);
		
		when(mockFixture1.getHomeTeam()).thenReturn(mockHomeTeam);
		when(mockFixture1.getAwayTeam()).thenReturn(mockAwayTeam);

		when(mockHomeTeam.getTeamId()).thenReturn("HomeId");
		when(mockAwayTeam.getTeamId()).thenReturn("AwayId");
		
		when(mockTableDate1.getTableRowForTeam("HomeId")).thenReturn(mockHomeTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockHomeTableRow);
		when(mockTableDate1.getLeaguePositionForTeamWithId("HomeId")).thenReturn(5);

		when(mockTableDate1.getTableRowForTeam("AwayId")).thenReturn(mockAwayTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockAwayTableRow);
		when(mockTableDate1.getLeaguePositionForTeamWithId("AwayId")).thenReturn(10);
		
		when(mockTeamFixtureContextFactory.createTeamFixtureContext()).thenReturn(mockHomeTeamFixtureContext, mockAwayTeamFixtureContext);

		// When
		objectUnderTest.loadTeamFixtureContextsForHomeAndAwayTeams(mockFixture1, fixtureDate1, mockDivisionCache1, mockTableDate1);
		
		// Then
		verify(mockHomeTeamFixtureContext,times(1)).setAtHome(true);
		verify(mockHomeTeamFixtureContext,times(1)).setLeaguePosition(5);
		verify(mockHomeTeamFixtureContext,times(1)).setPlayingTeamAbove(false);
		verify(mockHomeTeamFixtureContext,times(1)).setTeam(mockHomeTeam);

		verify(mockAwayTeamFixtureContext,times(1)).setAtHome(false);
		verify(mockAwayTeamFixtureContext,times(1)).setLeaguePosition(10);
		verify(mockAwayTeamFixtureContext,times(1)).setPlayingTeamAbove(true);
		verify(mockAwayTeamFixtureContext,times(1)).setTeam(mockAwayTeam);

		verify(mockDivisionCache1,times(1)).addTeamFixtureContextOnDate(fixtureDate1, mockHomeTeam, mockHomeTeamFixtureContext);
		verify(mockDivisionCache1,times(1)).addTeamFixtureContextOnDate(fixtureDate1, mockAwayTeam, mockAwayTeamFixtureContext);
	}
	
	@Test
	public void shouldLoadTeamFixtureContextForHomeTeamBelowAwayTeam () {
		// Given
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);
		
		when(mockFixture1.getHomeTeam()).thenReturn(mockHomeTeam);
		when(mockFixture1.getAwayTeam()).thenReturn(mockAwayTeam);

		when(mockHomeTeam.getTeamId()).thenReturn("HomeId");
		when(mockAwayTeam.getTeamId()).thenReturn("AwayId");
		
		when(mockTableDate1.getTableRowForTeam("HomeId")).thenReturn(mockHomeTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockHomeTableRow);
		when(mockTableDate1.getLeaguePositionForTeamWithId("HomeId")).thenReturn(10);

		when(mockTableDate1.getTableRowForTeam("AwayId")).thenReturn(mockAwayTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockAwayTableRow);
		when(mockTableDate1.getLeaguePositionForTeamWithId("AwayId")).thenReturn(5);
		
		when(mockTeamFixtureContextFactory.createTeamFixtureContext()).thenReturn(mockHomeTeamFixtureContext, mockAwayTeamFixtureContext);

		// When
		objectUnderTest.loadTeamFixtureContextsForHomeAndAwayTeams(mockFixture1, fixtureDate1, mockDivisionCache1, mockTableDate1);
		
		// Then
		verify(mockHomeTeamFixtureContext,times(1)).setAtHome(true);
		verify(mockHomeTeamFixtureContext,times(1)).setLeaguePosition(10);
		verify(mockHomeTeamFixtureContext,times(1)).setPlayingTeamAbove(true);
		verify(mockHomeTeamFixtureContext,times(1)).setTeam(mockHomeTeam);

		verify(mockAwayTeamFixtureContext,times(1)).setAtHome(false);
		verify(mockAwayTeamFixtureContext,times(1)).setLeaguePosition(5);
		verify(mockAwayTeamFixtureContext,times(1)).setPlayingTeamAbove(false);
		verify(mockAwayTeamFixtureContext,times(1)).setTeam(mockAwayTeam);

		verify(mockDivisionCache1,times(1)).addTeamFixtureContextOnDate(fixtureDate1, mockHomeTeam, mockHomeTeamFixtureContext);
		verify(mockDivisionCache1,times(1)).addTeamFixtureContextOnDate(fixtureDate1, mockAwayTeam, mockAwayTeamFixtureContext);
	}	
}
