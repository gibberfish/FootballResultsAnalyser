package uk.co.mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DynamicCalculationTest {
	private DynamicCalculation objectUnderTest;

	@Mock
	private Calculation mockCalculation1;

	@Mock
	private Calculation mockCalculation2;

	@Mock
	private Calculation mockCalculation3;

	private Map<String,Calculation> calculations;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		calculations = new HashMap<String,Calculation> ();
	}

	@Test
	public void shouldEvaluateAdditionExpression () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		when(mockCalculation1.calculate()).thenReturn(12);
		calculations.put("calc1", mockCalculation1);

		when(mockCalculation2.calculate()).thenReturn(13);
		calculations.put("calc2", mockCalculation2);

		// When
		float result = objectUnderTest.evaluateSingleExpression(12, "calc2", "+");
		
		// Then
		assertEquals (25, result, 0.001);
	}
	
	@Test
	public void shouldEvaluateSubtractionExpression () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		when(mockCalculation1.calculate()).thenReturn(12);
		calculations.put("calc1", mockCalculation1);

		when(mockCalculation2.calculate()).thenReturn(13);
		calculations.put("calc2", mockCalculation2);

		// When
		float result = objectUnderTest.evaluateSingleExpression(12, "calc2", "-");
		
		// Then
		assertEquals (-1, result, 0.001);	
	}

	@Test
	public void shouldEvaluateDivisionExpression () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		when(mockCalculation1.calculate()).thenReturn(2);
		calculations.put("calc1", mockCalculation1);

		when(mockCalculation2.calculate()).thenReturn(5);
		calculations.put("calc2", mockCalculation2);

		// When
		float result = objectUnderTest.evaluateSingleExpression(2, "calc2", "/");
		
		// Then
		assertEquals (0.4, result, 0.001);	
	}

	@Test
	public void shouldEvaluateMultiplicationExpression () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		when(mockCalculation1.calculate()).thenReturn(3);
		calculations.put("calc1", mockCalculation1);

		when(mockCalculation2.calculate()).thenReturn(23);
		calculations.put("calc2", mockCalculation2);

		// When
		float result = objectUnderTest.evaluateSingleExpression(3, "calc2", "*");
		
		// Then
		assertEquals (69, result, 0.001);		
	}

	@Test
	public void shouldThrowExceptionWhenAttemptingToEvaluateExpressionWithInvalidMultiplier () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		when(mockCalculation1.calculate()).thenReturn(3);
		calculations.put("calc1", mockCalculation1);

		when(mockCalculation2.calculate()).thenReturn(23);
		calculations.put("calc2", mockCalculation2);

		try {
			// When
			float result = objectUnderTest.evaluateSingleExpression(3, "calc2", "£");
			fail ("Should throw an exception");
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals ("Invalid Calculation String (invalid operator)", e.getMessage());
		}
	}

	@Test
	public void shouldIndicateWhereAnExpressionHasBrackets () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		// When
		boolean hasBranckets = objectUnderTest.hasBrackets("((A+B))");

		// Then
		assertTrue(hasBranckets);
	}

	@Test
	public void shouldIndicateWhereAnExpressionDoesNotHaveBrackets () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		// When
		boolean hasBranckets = objectUnderTest.hasBrackets("A+B+C");

		// Then
		assertFalse(hasBranckets);
	}

	@Test
	public void shouldThrowExceptionWhenProvidingAnExpressionWithNonMatchingBrackets () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		try {
			// When
			objectUnderTest.hasBrackets("((A+B)+C))");
			fail ("Should throw an exception");
		} catch (IllegalArgumentException e) {
			// Then
			assertEquals ("Invalid Calculation String (brackets don't match)", e.getMessage());
		}
	}

}
