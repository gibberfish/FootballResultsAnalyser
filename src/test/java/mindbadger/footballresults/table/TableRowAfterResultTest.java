package mindbadger.footballresults.table;

import static mindbadger.footballresults.table.AttributeIds.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import mindbadger.footballresults.season.TeamFixtureContext;
import mindbadger.footballresults.table.InitialTableRow;
import mindbadger.footballresults.table.TableRow;
import mindbadger.footballresults.table.TableRowAfterResult;
import mindbadger.footballresults.table.calculation.Calculation;
import mindbadger.footballresults.table.calculation.CalculationMapFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.footballresultsanalyser.domain.Fixture;
import mindbadger.footballresultsanalyser.domain.Team;

public class TableRowAfterResultTest {	
	private final String TEAM_ID = "123";
	private final String TEAM_NAME = "Portsmouth";
	private Map<String,Calculation> calculations = new HashMap<String,Calculation> ();
	
	private TableRowAfterResult objectUnderTest;
	
	@Mock
	private TableRowAfterResult mockPreviousTableRow;
	
	@Mock
	private Fixture mockFixture;
	
	@Mock
	private Team mockTeam1;

	@Mock
	private Team mockTeam2;

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
		
		calculations.put(GAMES_PLAYED, mockGamesPlayedCalculation);
		calculations.put(GAMES_WON, mockGamesWonCalculation);
		calculations.put(GAMES_DRAWN, mockGamesDrawnCalculation);
		calculations.put(GAMES_LOST, mockGamesLostCalculation);
		calculations.put(GOALS_SCORED, mockGoalsScoredCalculation);
		calculations.put(GOALS_CONCEDED, mockGoalsConcededCalculation);
		calculations.put(GOAL_DIFFERENCE, mockGoalDifferenceCalculation);
		calculations.put(POINTS, mockPointsCalculation);
		
		when(mockCalculationMapFactory.createCalculations(mockTeam1, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow)).thenReturn(calculations);
	}
	
	
	@Test
	public void shouldThrowAnExceptionWhenTryingToAccessDataBeforeTheCalculationsHaveBeenInitialised () {
		// Given
		//TableRow previousTableRow = new InitialTableRow (mockTeam1, mockParentTable);
		objectUnderTest = new TableRowAfterResult(mockTeam1, mockPreviousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
		
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
		TableRow previousTableRow = new InitialTableRow (mockTeam1);
		
		// When
		try {
			objectUnderTest = new TableRowAfterResult (null, previousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
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
			objectUnderTest = new TableRowAfterResult (mockTeam1, null, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
			fail("Should throw an illegal argument exception");
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals("Please supply a Team, a parent table, a previous TableRow and a Fixture", e.getMessage());
		}		
	}
	
	@Test
	public void shouldThrowExceptionIfFixtureNotSupplied () {
		// Given
		TableRow previousTableRow = new InitialTableRow (mockTeam1);
		
		// When
		try {
			objectUnderTest = new TableRowAfterResult (mockTeam1, previousTableRow, null, mockFixtureTeamContext, mockOppositionTeamContext);
			fail("Should throw an illegal argument exception");
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals("Please supply a Team, a parent table, a previous TableRow and a Fixture", e.getMessage());
		}
	}
	
	@Test
	public void shouldInitialiseTheCalculationsOnFirstInvokationOfGet () {
		// Given
		objectUnderTest = new TableRowAfterResult(mockTeam1, mockPreviousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
		objectUnderTest.setCalculationMapFactory(mockCalculationMapFactory);
		
		// When
		objectUnderTest.getAttribute("points");
		
		// Then
		verify(mockCalculationMapFactory, times(1)).createCalculations(mockTeam1, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
	}

	@Test
	public void shouldNotReInitialiseTheCalculationsOnSubsequentInvokationsOfGet () {
		// Given
		objectUnderTest = new TableRowAfterResult(mockTeam1, mockPreviousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
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
		objectUnderTest = new TableRowAfterResult(mockTeam1, mockPreviousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
		objectUnderTest.setCalculationMapFactory(mockCalculationMapFactory);
		
		when(mockGamesWonCalculation.calculate()).thenReturn(10);
		
		// When
		int gamesWon = objectUnderTest.getAttribute(GAMES_WON);

		// Then
		assertEquals (10, gamesWon);
	}

	@Test
	public void shouldThrowExceptionWhenInvalidAttributeRequested () {
		// Given
		objectUnderTest = new TableRowAfterResult(mockTeam1, mockPreviousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
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
