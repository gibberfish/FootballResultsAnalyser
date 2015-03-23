package uk.co.mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
	private Team<String> mockTeam1;

	@Mock
	private Team<String> mockTeam2;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnThreeWhenThereIsNoPreviousRowForAHomeWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		objectUnderTest = new PointsCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnOneWhenThereIsNoPreviousRowForAHomeDraw () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		objectUnderTest = new PointsCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (1, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAHomeDefeat () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		objectUnderTest = new PointsCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnThreeWhenThereIsNoPreviousRowForAnAwayWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		objectUnderTest = new PointsCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnOneWhenThereIsNoPreviousRowForAnAwayDraw () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		objectUnderTest = new PointsCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (1, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAnAwayDefeat () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		objectUnderTest = new PointsCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}
	
	
	
	
	@Test
	public void shouldReturnAnIncrementOfThreeWhenThereIsAPreviousRowForAHomeWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getPoints()).thenReturn(5);
		objectUnderTest = new PointsCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (8, result);
	}

	@Test
	public void shouldReturnAnIncrementOfOneWhenThereIsAPreviousRowForAHomeDraw () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getPoints()).thenReturn(5);
		objectUnderTest = new PointsCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (6, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDefeat () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getPoints()).thenReturn(5);
		objectUnderTest = new PointsCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (5, result);
	}

	@Test
	public void shouldReturnAnIncrementOfThreeWhenThereIsAPreviousRowForAnAwayWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getPoints()).thenReturn(5);
		objectUnderTest = new PointsCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (8, result);
	}

	@Test
	public void shouldReturnAnIncrementOfOneWhenThereIsAPreviousRowForAnAwayDraw () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getPoints()).thenReturn(5);
		objectUnderTest = new PointsCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (6, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeat () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getPoints()).thenReturn(5);
		objectUnderTest = new PointsCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (5, result);
	}
}
