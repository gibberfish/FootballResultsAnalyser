package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowTest {	
	private final Integer TEAM_ID = 123;
	private final String TEAM_NAME = "Portsmouth";
	private final Calendar FIXTURE_DATE = Calendar.getInstance();
	
	private TableRow objectUnderTest;
	
	@Mock
	private TableRow mockPreviousTableRow;
	
	@Mock
	private Fixture mockFixture;
	
	@Mock
	private Team mockTeam1;

	@Mock
	private Team mockTeam2;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		when (mockTeam1.getTeamId()).thenReturn(TEAM_ID);
		when (mockTeam1.getTeamName()).thenReturn(TEAM_NAME);
	}

	@Test
	public void shouldCreateAnEmptyTableRow () {
		// Given
		objectUnderTest = new TableRow (mockTeam1);

		// When

		// Then
		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
		assertEquals (0, objectUnderTest.getGamesPlayed());
		assertEquals (0, objectUnderTest.getGamesWon());
		assertEquals (0, objectUnderTest.getGamesDrawn());
		assertEquals (0, objectUnderTest.getGamesLost());
		assertEquals (0, objectUnderTest.getGoalsScored());
		assertEquals (0, objectUnderTest.getGoalsConceded());
		assertEquals (0, objectUnderTest.getGoalDifference());
		assertEquals (0, objectUnderTest.getPoints());
	}
	
	@Test
	public void shouldCreateATableRowForAHomeTeamWinning () {
		// Given
		TableRow previousTableRow = new TableRow (mockTeam1);
		
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
		when (mockFixture.getHomeGoals()).thenReturn(2);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		
		objectUnderTest = new TableRow (mockTeam1, previousTableRow, mockFixture);
		
		// When
		
		// Then
		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
		assertEquals (1, objectUnderTest.getGamesPlayed());
		assertEquals (1, objectUnderTest.getGamesWon());
		assertEquals (0, objectUnderTest.getGamesDrawn());
		assertEquals (0, objectUnderTest.getGamesLost());
		assertEquals (2, objectUnderTest.getGoalsScored());
		assertEquals (1, objectUnderTest.getGoalsConceded());
		assertEquals (1, objectUnderTest.getGoalDifference());
		assertEquals (3, objectUnderTest.getPoints());
	}
}
