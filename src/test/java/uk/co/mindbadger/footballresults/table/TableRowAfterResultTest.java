package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.calculation.Calculation;
import uk.co.mindbadger.footballresults.table.calculation.CalculationMapFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowAfterResultTest {	
	private final String TEAM_ID = "123";
	private final String TEAM_NAME = "Portsmouth";
	private final Calendar FIXTURE_DATE = Calendar.getInstance();
	private Map<String,Calculation> calculations = new HashMap<String,Calculation> ();
	
	private TableRowAfterResult<String,String,String> objectUnderTest;
	
	@Mock
	private TableRowAfterResult<String,String,String> mockPreviousTableRow;
	
	@Mock
	private Fixture<String> mockFixture;
	
	@Mock
	private Team<String> mockTeam1;

	@Mock
	private Team<String> mockTeam2;

	@Mock
	private Table<String,String,String> mockParentTable;
	
	@Mock
	private Calculation mockGamesPlayedCalculation;

	@Mock
	private Calculation mockGamesWonCalculation;

	@Mock
	private Calculation mockGamesLostCalculation;

	@Mock
	private Calculation mockGamesDrawnCalculation;

	@Mock
	private Calculation mockGoalsScoredCalculation;

	@Mock
	private Calculation mockGoalsConcededCalculation;

	@Mock
	private Calculation mockGoalDifferenceCalculation;

	@Mock
	private Calculation mockPointsCalculation;
	
	@Mock
	private CalculationMapFactory<String, String, String> mockCalculationMapFactory;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		when (mockTeam1.getTeamId()).thenReturn(TEAM_ID);
		when (mockTeam1.getTeamName()).thenReturn(TEAM_NAME);
		
		calculations.put(TableRow.GAMES_PLAYED, mockGamesPlayedCalculation);
		calculations.put(TableRow.GAMES_WON, mockGamesWonCalculation);
		calculations.put(TableRow.GAMES_DRAWN, mockGamesDrawnCalculation);
		calculations.put(TableRow.GAMES_LOST, mockGamesLostCalculation);
		calculations.put(TableRow.GOALS_SCORED, mockGoalsScoredCalculation);
		calculations.put(TableRow.GOALS_CONCEDED, mockGoalsConcededCalculation);
		calculations.put(TableRow.GOAL_DIFFERENCE, mockGoalDifferenceCalculation);
		calculations.put(TableRow.POINTS, mockPointsCalculation);
	}
	
	
	@Test
	public void shouldThrowAnExceptionWhenTryingToAccessDataBeforeTheCalculationsHaveBeenInitialised () {
		// Given
		//TableRow<String,String,String> previousTableRow = new InitialTableRow<String,String,String> (mockTeam1, mockParentTable);
		objectUnderTest = new TableRowAfterResult<>(mockTeam1, mockParentTable, mockPreviousTableRow, mockFixture);
		
		// When
		try {
			objectUnderTest.get("points");
			fail("Should get an exception thrown because we have not supplied the calculations");
		} catch (Exception e) {
			// Then
			assertEquals("The TableRow requires a CalculationMapFactory.", e.getMessage());
		}
	}
	
	@Test
	public void shouldThrowExceptionIfTeamNotSupplied () {
		// Given
		TableRow<String,String,String> previousTableRow = new InitialTableRow<String,String,String> (mockTeam1, mockParentTable);
		
		// When
		try {
			objectUnderTest = new TableRowAfterResult<String,String,String> (null, mockParentTable, previousTableRow, mockFixture);
			fail("Should throw an illegal argument exception");
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals("Please supply a Team, a parent table, a previous TableRow and a Fixture", e.getMessage());
		}
	}
	
	@Test
	public void shouldThrowExceptionIfPreviousTableRowNotSupplied () {
		// Given
		
		// When
		try {
			objectUnderTest = new TableRowAfterResult<String,String,String> (mockTeam1, mockParentTable, null, mockFixture);
			fail("Should throw an illegal argument exception");
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals("Please supply a Team, a parent table, a previous TableRow and a Fixture", e.getMessage());
		}		
	}
	
	@Test
	public void shouldThrowExceptionIfFixtureNotSupplied () {
		// Given
		TableRow<String,String,String> previousTableRow = new InitialTableRow<String,String,String> (mockTeam1, mockParentTable);
		
		// When
		try {
			objectUnderTest = new TableRowAfterResult<String,String,String> (mockTeam1, mockParentTable, previousTableRow, null);
			fail("Should throw an illegal argument exception");
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals("Please supply a Team, a parent table, a previous TableRow and a Fixture", e.getMessage());
		}
	}
	
	@Test
	public void shouldInitialiseTheCalculationsOnFirstInvokationOfGet () {
		// Given
		objectUnderTest = new TableRowAfterResult<>(mockTeam1, mockParentTable, mockPreviousTableRow, mockFixture);
		objectUnderTest.setCalculationMapFactory(mockCalculationMapFactory);
		
		// When
		objectUnderTest.get("points");
		
		// Then
		verify(mockCalculationMapFactory, times(1)).createCalculations(mockTeam1, mockPreviousTableRow, mockFixture);
	}

	@Test
	public void shouldNotReInitialiseTheCalculationsOnSubsequentInvokationsOfGet () {
		// Given
		objectUnderTest = new TableRowAfterResult<>(mockTeam1, mockParentTable, mockPreviousTableRow, mockFixture);
		objectUnderTest.setCalculationMapFactory(mockCalculationMapFactory);
		
		// When
		objectUnderTest.get("points");
		objectUnderTest.get("points");
		
		// Then
		verify(mockCalculationMapFactory, times(1)).createCalculations(mockTeam1, mockPreviousTableRow, mockFixture);
	}
	
//	@Test
//	public void shouldNotReInitialiseTheCalculationsOnSubsequentInvokationsOfGet () {
		
//	}
	
	
//
//	
//	@Test
//	public void shouldCreateATableRowForAHomeTeamWinning () {
//		// Given
//		TableRow<String,String,String> previousTableRow = new InitialTableRow<String,String,String> (mockTeam1);
//		
//		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
//		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
//		when (mockFixture.getHomeGoals()).thenReturn(2);
//		when (mockFixture.getAwayGoals()).thenReturn(1);
//		
//		objectUnderTest = new TableRowAfterResult<String,String,String> (mockTeam1, previousTableRow, mockFixture);
//		objectUnderTest.setCalculations(calculations);
//		
//		// When
//		
//		// Then
//		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
//		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_PLAYED));
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_WON));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_DRAWN));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_LOST));
//		assertEquals (2, objectUnderTest.get(TableRow.GOALS_SCORED));
//		assertEquals (1, objectUnderTest.get(TableRow.GOALS_CONCEDED));
//		assertEquals (1, objectUnderTest.get(TableRow.GOAL_DIFFERENCE));
//		assertEquals (3, objectUnderTest.get(TableRow.POINTS));
//	}
//	
//	@Test
//	public void shouldCreateATableRowForAHomeTeamLosing () {
//		// Given
//		TableRow<String,String,String> previousTableRow = new InitialTableRow<String,String,String> (mockTeam1);
//		
//		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
//		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
//		when (mockFixture.getHomeGoals()).thenReturn(2);
//		when (mockFixture.getAwayGoals()).thenReturn(4);
//		
//		objectUnderTest = new TableRowAfterResult<String,String,String> (mockTeam1, previousTableRow, mockFixture);
//		
//		// When
//		
//		// Then
//		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
//		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_PLAYED));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_WON));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_DRAWN));
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_LOST));
//		assertEquals (2, objectUnderTest.get(TableRow.GOALS_SCORED));
//		assertEquals (4, objectUnderTest.get(TableRow.GOALS_CONCEDED));
//		assertEquals (-2, objectUnderTest.get(TableRow.GOAL_DIFFERENCE));
//		assertEquals (0, objectUnderTest.get(TableRow.POINTS));
//	}
//
//	@Test
//	public void shouldCreateATableRowForAHomeTeamDrawing () {
//		// Given
//		TableRow<String,String,String> previousTableRow = new InitialTableRow<String,String,String> (mockTeam1);
//		
//		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
//		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
//		when (mockFixture.getHomeGoals()).thenReturn(3);
//		when (mockFixture.getAwayGoals()).thenReturn(3);
//		
//		objectUnderTest = new TableRowAfterResult<String,String,String> (mockTeam1, previousTableRow, mockFixture);
//		
//		// When
//		
//		// Then
//		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
//		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_PLAYED));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_WON));
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_DRAWN));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_LOST));
//		assertEquals (3, objectUnderTest.get(TableRow.GOALS_SCORED));
//		assertEquals (3, objectUnderTest.get(TableRow.GOALS_CONCEDED));
//		assertEquals (0, objectUnderTest.get(TableRow.GOAL_DIFFERENCE));
//		assertEquals (1, objectUnderTest.get(TableRow.POINTS));
//	}
//	
//	@Test
//	public void shouldCreateATableRowForAnAwayTeamWinning () {
//		// Given
//		TableRow<String,String,String> previousTableRow = new InitialTableRow<String,String,String> (mockTeam1);
//		
//		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
//		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
//		when (mockFixture.getHomeGoals()).thenReturn(1);
//		when (mockFixture.getAwayGoals()).thenReturn(2);
//		
//		objectUnderTest = new TableRowAfterResult<String,String,String> (mockTeam1, previousTableRow, mockFixture);
//		
//		// When
//		
//		// Then
//		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
//		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_PLAYED));
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_WON));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_DRAWN));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_LOST));
//		assertEquals (2, objectUnderTest.get(TableRow.GOALS_SCORED));
//		assertEquals (1, objectUnderTest.get(TableRow.GOALS_CONCEDED));
//		assertEquals (1, objectUnderTest.get(TableRow.GOAL_DIFFERENCE));
//		assertEquals (3, objectUnderTest.get(TableRow.POINTS));
//	}
//	
//	@Test
//	public void shouldCreateATableRowForAnAwayTeamLosing () {
//		// Given
//		TableRow<String,String,String> previousTableRow = new InitialTableRow<String,String,String> (mockTeam1);
//		
//		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
//		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
//		when (mockFixture.getHomeGoals()).thenReturn(4);
//		when (mockFixture.getAwayGoals()).thenReturn(2);
//		
//		objectUnderTest = new TableRowAfterResult<String,String,String> (mockTeam1, previousTableRow, mockFixture);
//		
//		// When
//		
//		// Then
//		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
//		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_PLAYED));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_WON));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_DRAWN));
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_LOST));
//		assertEquals (2, objectUnderTest.get(TableRow.GOALS_SCORED));
//		assertEquals (4, objectUnderTest.get(TableRow.GOALS_CONCEDED));
//		assertEquals (-2, objectUnderTest.get(TableRow.GOAL_DIFFERENCE));
//		assertEquals (0, objectUnderTest.get(TableRow.POINTS));
//	}
//
//	@Test
//	public void shouldCreateATableRowForAnAwayTeamDrawing () {
//		// Given
//		TableRow<String,String,String> previousTableRow = new InitialTableRow<String,String,String> (mockTeam1);
//		
//		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
//		when (mockFixture.getFixtureDate()).thenReturn(FIXTURE_DATE);
//		when (mockFixture.getHomeGoals()).thenReturn(3);
//		when (mockFixture.getAwayGoals()).thenReturn(3);
//		
//		objectUnderTest = new TableRowAfterResult<String,String,String> (mockTeam1, previousTableRow, mockFixture);
//		
//		// When
//		
//		// Then
//		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
//		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_PLAYED));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_WON));
//		assertEquals (1, objectUnderTest.get(TableRow.GAMES_DRAWN));
//		assertEquals (0, objectUnderTest.get(TableRow.GAMES_LOST));
//		assertEquals (3, objectUnderTest.get(TableRow.GOALS_SCORED));
//		assertEquals (3, objectUnderTest.get(TableRow.GOALS_CONCEDED));
//		assertEquals (0, objectUnderTest.get(TableRow.GOAL_DIFFERENCE));
//		assertEquals (1, objectUnderTest.get(TableRow.POINTS));
//	}
	
	
}
