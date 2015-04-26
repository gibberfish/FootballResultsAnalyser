package uk.co.mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowAfterResult;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GoalsScoredCalculationTest {
	private GoalsScoredCalculation objectUnderTest;
	
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
	public void shouldReturnHomeGoalsWhenThereIsNoPreviousRowForAHomeFixture () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		objectUnderTest = new GoalsScoredCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnAwayGoalsWhenThereIsNoPreviousRowForAnAwayFixture () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		objectUnderTest = new GoalsScoredCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (1, result);
	}

		

	@Test
	public void shouldReturnAdditionOfHomeGoalsWhenThereIsAPreviousRowForAHomeFixture () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(TableRow.GOALS_SCORED)).thenReturn(3);
		objectUnderTest = new GoalsScoredCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (6, result);
	}

	@Test
	public void shouldReturnAdditionOfAwayGoalsWhenThereIsAPreviousRowForAnAwayFixture () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(TableRow.GOALS_SCORED)).thenReturn(3);
		objectUnderTest = new GoalsScoredCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (4, result);
	}

}
