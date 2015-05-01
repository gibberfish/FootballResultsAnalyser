package uk.co.mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.TableRowAfterResult;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GoalDifferenceCalculationTest {
	private GoalDifferenceCalculation objectUnderTest;
	
	@Mock
	private Calculation mockGoalsScoredCalculation;
	
	@Mock
	private Calculation mockGoalsConcededCalculation;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Map<String, Calculation> calculations = new HashMap<String, Calculation> ();
		calculations.put("goalsScored", mockGoalsScoredCalculation);
		calculations.put("goalsConceded", mockGoalsConcededCalculation);
		
		objectUnderTest = new GoalDifferenceCalculation(calculations);
	}

	@Test
	public void shouldReturnPositiveGoalDifference () {
		// Given
		when (mockGoalsScoredCalculation.calculate()).thenReturn(45);
		when (mockGoalsConcededCalculation.calculate()).thenReturn(23);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (22, result);
	}

	@Test
	public void shouldReturnZeroGoalDifference () {
		// Given
		when (mockGoalsScoredCalculation.calculate()).thenReturn(45);
		when (mockGoalsConcededCalculation.calculate()).thenReturn(45);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnNegativeGoalDifference () {
		// Given
		when (mockGoalsScoredCalculation.calculate()).thenReturn(13);
		when (mockGoalsConcededCalculation.calculate()).thenReturn(23);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (-10, result);
	}
}
