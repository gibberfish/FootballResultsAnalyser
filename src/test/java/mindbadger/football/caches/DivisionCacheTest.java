package mindbadger.football.caches;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import mindbadger.football.caches.DivisionCache;
import mindbadger.football.table.Table;
import mindbadger.football.table.TeamFixtureContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.football.domain.Fixture;
import mindbadger.football.domain.Team;

public class DivisionCacheTest {
	
	private DivisionCache objectUnderTest;

	private Calendar date1;
	private Calendar date2;
	private Calendar date3;
	
	@Mock
	private Fixture mockFixture1;

	@Mock
	private Fixture mockFixture2;

	@Mock
	private Fixture mockFixture3;

	@Mock
	private Table mockTable1;

	@Mock
	private Table mockTable2;

	@Mock
	private Table mockTable3;

	@Mock
	private Team mockTeam1;

	@Mock
	private Team mockTeam2;

	@Mock
	private Team mockTeam3;

	@Mock
	private Team mockTeam4;

	@Mock
	private TeamFixtureContext mockTeamFixtureContent1;

	@Mock
	private TeamFixtureContext mockTeamFixtureContent2;

	@Mock
	private TeamFixtureContext mockTeamFixtureContent3;

	@Mock
	private TeamFixtureContext mockTeamFixtureContent4;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectUnderTest = new DivisionCache();
		
		date1 = Calendar.getInstance();
		date1.set(Calendar.MONTH, 5);
		
		date2 = Calendar.getInstance();
		date2.set(Calendar.MONTH, 6);
		
		date3 = Calendar.getInstance();
		date3.set(Calendar.MONTH, 7);
	}
	
	@Test
	public void shouldInitialiseWithNoRows () {
		// Given
		
		// When
		Map<Calendar, List<Fixture>> fixtures = objectUnderTest.getFixturesForDivision();
		Map<Calendar, Table> tables = objectUnderTest.getTablesForDivision();
		
		// Then
		assertEquals (0, fixtures.size());
		assertEquals (0, tables.size());
	}
	
	@Test
	public void shouldAddFixtureRowsToCache () {
		// Given
		objectUnderTest.addFixtureOnDate(date1, mockFixture1);
		objectUnderTest.addFixtureOnDate(date1, mockFixture2);
		objectUnderTest.addFixtureOnDate(date2, mockFixture3);
		
		// When
		Map<Calendar, List<Fixture>> fixtures = objectUnderTest.getFixturesForDivision();
		
		// Then
		assertEquals (2, fixtures.size());
		
		List<Fixture> fixturesForDate1 = fixtures.get(date1);
		assertEquals (2, fixturesForDate1.size());
		assertEquals (mockFixture1, fixturesForDate1.get(0));
		assertEquals (mockFixture2, fixturesForDate1.get(1));
		
		List<Fixture> fixturesForDate2 = fixtures.get(date2);
		assertEquals (1, fixturesForDate2.size());
		assertEquals (mockFixture3, fixturesForDate2.get(0));
	}

	@Test
	public void shouldAddTableRowToCache () {
		// Given
		
		// When
		objectUnderTest.addTableOnDate(date1, mockTable1);
		objectUnderTest.addTableOnDate(date2, mockTable2);
		objectUnderTest.addTableOnDate(date3, mockTable3);
		
		// Then
		Map<Calendar, Table> tables = objectUnderTest.getTablesForDivision();
		
		assertEquals (3, tables.size());
		assertEquals (mockTable1, tables.get(date1));
		assertEquals (mockTable2, tables.get(date2));
		assertEquals (mockTable3, tables.get(date3));
	}

	@Test
	public void shouldGetFixtureDatesForDivision () {
		// Given
		objectUnderTest.addFixtureOnDate(date2, mockFixture1);
		objectUnderTest.addFixtureOnDate(date1, mockFixture2);
		objectUnderTest.addFixtureOnDate(date2, mockFixture3);
		
		// When
		List<Calendar> fixtureDates = objectUnderTest.getFixtureDates ();
		
		// Then
		assertEquals(2, fixtureDates.size());
		assertEquals(date1, fixtureDates.get(0));
		assertEquals(date2, fixtureDates.get(1));
	}
	
	@Test
	public void shoulAddTeamFixtureContextOnDate () {
		// Given
		
		// When
		objectUnderTest.addTeamFixtureContextOnDate(date1, mockTeam1, mockTeamFixtureContent1);
		objectUnderTest.addTeamFixtureContextOnDate(date2, mockTeam2, mockTeamFixtureContent2);
		objectUnderTest.addTeamFixtureContextOnDate(date2, mockTeam3, mockTeamFixtureContent3);
		objectUnderTest.addTeamFixtureContextOnDate(date2, mockTeam4, mockTeamFixtureContent4);
		objectUnderTest.addTeamFixtureContextOnDate(date2, mockTeam4, mockTeamFixtureContent4);
		
		// Then
		Map<Calendar, Map<Team, TeamFixtureContext>> contexts = objectUnderTest.getTeamFixtureContexts();
		assertEquals (2, contexts.size());
		
		Map<Team, TeamFixtureContext> teamsForDate1 = contexts.get(date1);
		assertEquals (1, teamsForDate1.size());
		
		Map<Team, TeamFixtureContext> teamsForDate2 = contexts.get(date2);
		assertEquals (3, teamsForDate2.size());
	}
}
