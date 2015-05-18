package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.calculation.Calculation;
import uk.co.mindbadger.footballresults.table.calculation.CalculationMapFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowAfterResultTest {	
	private final String TEAM_ID = "123";
	private final String TEAM_NAME = "Portsmouth";
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
	private TeamFixtureContext mockFixtureTeamContext;
	
	@Mock
	private TeamFixtureContext mockOppositionTeamContext;

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
		
		when(mockCalculationMapFactory.createCalculations(mockTeam1, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow)).thenReturn(calculations);
	}
	
	
	@Test
	public void shouldThrowAnExceptionWhenTryingToAccessDataBeforeTheCalculationsHaveBeenInitialised () {
		// Given
		//TableRow<String,String,String> previousTableRow = new InitialTableRow<String,String,String> (mockTeam1, mockParentTable);
		objectUnderTest = new TableRowAfterResult<>(mockTeam1, mockParentTable, mockPreviousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
		
		// When
		try {
			objectUnderTest.getAttribute("points");
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
			objectUnderTest = new TableRowAfterResult<String,String,String> (null, mockParentTable, previousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
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
			objectUnderTest = new TableRowAfterResult<String,String,String> (mockTeam1, mockParentTable, null, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
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
			objectUnderTest = new TableRowAfterResult<String,String,String> (mockTeam1, mockParentTable, previousTableRow, null, mockFixtureTeamContext, mockOppositionTeamContext);
			fail("Should throw an illegal argument exception");
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals("Please supply a Team, a parent table, a previous TableRow and a Fixture", e.getMessage());
		}
	}
	
	@Test
	public void shouldInitialiseTheCalculationsOnFirstInvokationOfGet () {
		// Given
		objectUnderTest = new TableRowAfterResult<>(mockTeam1, mockParentTable, mockPreviousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
		objectUnderTest.setCalculationMapFactory(mockCalculationMapFactory);
		
		// When
		objectUnderTest.getAttribute("points");
		
		// Then
		verify(mockCalculationMapFactory, times(1)).createCalculations(mockTeam1, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
	}

	@Test
	public void shouldNotReInitialiseTheCalculationsOnSubsequentInvokationsOfGet () {
		// Given
		objectUnderTest = new TableRowAfterResult<>(mockTeam1, mockParentTable, mockPreviousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
		objectUnderTest.setCalculationMapFactory(mockCalculationMapFactory);
		
		// When
		objectUnderTest.getAttribute("points");
		objectUnderTest.getAttribute("points");
		
		// Then
		verify(mockCalculationMapFactory, times(1)).createCalculations(mockTeam1, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
	}
	
	@Test
	public void shouldGetData () {
		// Given
		objectUnderTest = new TableRowAfterResult<>(mockTeam1, mockParentTable, mockPreviousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
		objectUnderTest.setCalculationMapFactory(mockCalculationMapFactory);
		
		when(mockGamesWonCalculation.calculate()).thenReturn(10);
		
		// When
		int gamesWon = objectUnderTest.getAttribute(TableRowAfterResult.GAMES_WON);

		// Then
		assertEquals (10, gamesWon);
	}

	@Test
	public void shouldThrowExceptionWhenInvalidAttributeRequested () {
		// Given
		objectUnderTest = new TableRowAfterResult<>(mockTeam1, mockParentTable, mockPreviousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
		objectUnderTest.setCalculationMapFactory(mockCalculationMapFactory);
		
		// When
		try{
			objectUnderTest.getAttribute("INVALID_ATTRIBUTE");
			fail("Should throw an exception here");
			
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals ("No value for INVALID_ATTRIBUTE", e.getMessage());
		}

	}
}
