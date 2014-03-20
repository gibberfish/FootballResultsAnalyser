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

public class TableRowAfterResultTest {	
	private final Integer TEAM_ID = 123;
	private final String TEAM_NAME = "Portsmouth";
	private final Calendar FIXTURE_DATE = Calendar.getInstance();
	
	private TableRowAfterResult objectUnderTest;
	
	@Mock
	private TableRowAfterResult mockPreviousTableRow;
	
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
	public void shouldThrowExceptionIfTeamNotSupplied () {
		// Given
		TableRow previousTableRow = new InitialTableRow (mockTeam1);
		
		// When
		try {
			objectUnderTest = new TableRowAfterResult (null, previousTableRow, mockFixture);
			fail("Should throw an illegal argument exception");
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals("Please supply a Team, a previous TableRow and a Fixture", e.getMessage());
		}
	}
	
	@Test
	public void shouldThrowExceptionIfPreviousTableRowNotSupplied () {
		// Given
		
		// When
		try {
			objectUnderTest = new TableRowAfterResult (mockTeam1, null, mockFixture);
			fail("Should throw an illegal argument exception");
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals("Please supply a Team, a previous TableRow and a Fixture", e.getMessage());
		}		
	}
	
	@Test
	public void shouldThrowExceptionIfFixtureNotSupplied () {
		// Given
		TableRow previousTableRow = new InitialTableRow (mockTeam1);
		
		// When
		try {
			objectUnderTest = new TableRowAfterResult (mockTeam1, previousTableRow, null);
			fail("Should throw an illegal argument exception");
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals("Please supply a Team, a previous TableRow and a Fixture", e.getMessage());
		}
	}

	
	@Test
	public void shouldCreateATableRowForAHomeTeamWinning () {
		// Given
		TableRow previousTableRow = new InitialTableRow (mockTeam1);
		
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
		when (mockFixture.getHomeGoals()).thenReturn(2);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		
		objectUnderTest = new TableRowAfterResult (mockTeam1, previousTableRow, mockFixture);
		
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
	
	@Test
	public void shouldCreateATableRowForAHomeTeamLosing () {
		// Given
		TableRow previousTableRow = new InitialTableRow (mockTeam1);
		
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
		when (mockFixture.getHomeGoals()).thenReturn(2);
		when (mockFixture.getAwayGoals()).thenReturn(4);
		
		objectUnderTest = new TableRowAfterResult (mockTeam1, previousTableRow, mockFixture);
		
		// When
		
		// Then
		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
		assertEquals (1, objectUnderTest.getGamesPlayed());
		assertEquals (0, objectUnderTest.getGamesWon());
		assertEquals (0, objectUnderTest.getGamesDrawn());
		assertEquals (1, objectUnderTest.getGamesLost());
		assertEquals (2, objectUnderTest.getGoalsScored());
		assertEquals (4, objectUnderTest.getGoalsConceded());
		assertEquals (-2, objectUnderTest.getGoalDifference());
		assertEquals (0, objectUnderTest.getPoints());
	}

	@Test
	public void shouldCreateATableRowForAHomeTeamDrawing () {
		// Given
		TableRow previousTableRow = new InitialTableRow (mockTeam1);
		
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		objectUnderTest = new TableRowAfterResult (mockTeam1, previousTableRow, mockFixture);
		
		// When
		
		// Then
		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
		assertEquals (1, objectUnderTest.getGamesPlayed());
		assertEquals (0, objectUnderTest.getGamesWon());
		assertEquals (1, objectUnderTest.getGamesDrawn());
		assertEquals (0, objectUnderTest.getGamesLost());
		assertEquals (3, objectUnderTest.getGoalsScored());
		assertEquals (3, objectUnderTest.getGoalsConceded());
		assertEquals (0, objectUnderTest.getGoalDifference());
		assertEquals (1, objectUnderTest.getPoints());
	}
	
	@Test
	public void shouldCreateATableRowForAnAwayTeamWinning () {
		// Given
		TableRow previousTableRow = new InitialTableRow (mockTeam1);
		
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(2);
		
		objectUnderTest = new TableRowAfterResult (mockTeam1, previousTableRow, mockFixture);
		
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
	
	@Test
	public void shouldCreateATableRowForAnAwayTeamLosing () {
		// Given
		TableRow previousTableRow = new InitialTableRow (mockTeam1);
		
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
		when (mockFixture.getHomeGoals()).thenReturn(4);
		when (mockFixture.getAwayGoals()).thenReturn(2);
		
		objectUnderTest = new TableRowAfterResult (mockTeam1, previousTableRow, mockFixture);
		
		// When
		
		// Then
		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
		assertEquals (1, objectUnderTest.getGamesPlayed());
		assertEquals (0, objectUnderTest.getGamesWon());
		assertEquals (0, objectUnderTest.getGamesDrawn());
		assertEquals (1, objectUnderTest.getGamesLost());
		assertEquals (2, objectUnderTest.getGoalsScored());
		assertEquals (4, objectUnderTest.getGoalsConceded());
		assertEquals (-2, objectUnderTest.getGoalDifference());
		assertEquals (0, objectUnderTest.getPoints());
	}

	@Test
	public void shouldCreateATableRowForAnAwayTeamDrawing () {
		// Given
		TableRow previousTableRow = new InitialTableRow (mockTeam1);
		
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		objectUnderTest = new TableRowAfterResult (mockTeam1, previousTableRow, mockFixture);
		
		// When
		
		// Then
		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
		assertEquals (1, objectUnderTest.getGamesPlayed());
		assertEquals (0, objectUnderTest.getGamesWon());
		assertEquals (1, objectUnderTest.getGamesDrawn());
		assertEquals (0, objectUnderTest.getGamesLost());
		assertEquals (3, objectUnderTest.getGoalsScored());
		assertEquals (3, objectUnderTest.getGoalsConceded());
		assertEquals (0, objectUnderTest.getGoalDifference());
		assertEquals (1, objectUnderTest.getPoints());
	}
	
	
}