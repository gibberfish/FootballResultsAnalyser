package uk.co.mindbadger.footballresults.table.calculation;

import static uk.co.mindbadger.footballresults.table.AttributeIds.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowAfterResult;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class PointsCalculationTest {
	private PointsCalculation objectUnderTest;
	
	@Mock
	private TableRowAfterResult<String,String,String> mockPreviousTableRow;

	@Mock
	private Fixture<String> mockFixture;
	
	@Mock
	private Team<String> mockTeamForCalculation;

	@Mock
	private TeamFixtureContext mockFixtureTeamContext;
	
	@Mock
	private TeamFixtureContext mockOppositionTeamContext;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnThreeWhenThereIsNoPreviousRowForAHomeWin () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, null);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnOneWhenThereIsNoPreviousRowForAHomeDraw () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, null);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (1, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAHomeDefeat () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, null);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnThreeWhenThereIsNoPreviousRowForAnAwayWin () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, null);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnOneWhenThereIsNoPreviousRowForAnAwayDraw () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, null);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (1, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAnAwayDefeat () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, null);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}
	
	@Test
	public void shouldReturnAnIncrementOfThreeWhenThereIsAPreviousRowForAHomeWin () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(POINTS)).thenReturn(10);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (13, result);
	}

	@Test
	public void shouldReturnAnIncrementOfOneWhenThereIsAPreviousRowForAHomeDraw () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(POINTS)).thenReturn(10);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (11, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDefeat () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(POINTS)).thenReturn(10);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (10, result);
	}

	@Test
	public void shouldReturnAnIncrementOfThreeWhenThereIsAPreviousRowForAnAwayWin () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(POINTS)).thenReturn(10);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (13, result);
	}

	@Test
	public void shouldReturnAnIncrementOfOneWhenThereIsAPreviousRowForAnAwayDraw () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(POINTS)).thenReturn(10);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (11, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeat () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(POINTS)).thenReturn(10);
		
		objectUnderTest = new PointsCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (10, result);
	}
}
