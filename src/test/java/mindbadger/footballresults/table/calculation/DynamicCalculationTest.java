package mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import mindbadger.footballresults.table.calculation.Calculation;
import mindbadger.footballresults.table.calculation.DynamicCalculation;

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

	@Mock
	private Calculation mockCalculation4;

	@Mock
	private Calculation mockCalculation5;

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

		// When
		float result = objectUnderTest.evaluateSingleExpression(12, 13, "+");
		
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
		float result = objectUnderTest.evaluateSingleExpression(12, 13, "-");
		
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
		float result = objectUnderTest.evaluateSingleExpression(2, 5, "/");
		
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
		float result = objectUnderTest.evaluateSingleExpression(3, 23, "*");
		
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
			float result = objectUnderTest.evaluateSingleExpression(3, 23, "£");
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

	@Test
	public void shouldGetTheValuesNeeded () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		when(mockCalculation1.calculate()).thenReturn(3);
		calculations.put("calc1", mockCalculation1);

		when(mockCalculation2.calculate()).thenReturn(23);
		calculations.put("calc2", mockCalculation2);

		when(mockCalculation3.calculate()).thenReturn(8);
		calculations.put("calc3", mockCalculation3);

		when(mockCalculation4.calculate()).thenReturn(7);
		calculations.put("calc4", mockCalculation4);

		when(mockCalculation5.calculate()).thenReturn(5);
		calculations.put("calc5", mockCalculation5);

		Map<String,Float> values = objectUnderTest.getValuesNeededForCalculation(" {calc1} + ((({calc2} - {calc3})) ");

		// Then
		assertEquals (3, values.size());
		assertEquals (3, values.get("calc1"), 0.001);
		assertEquals (23, values.get("calc2"), 0.001);
		assertEquals (8, values.get("calc3"), 0.001);
	}

	@Test
	public void shouldEvalulateComplexCalculationStringWithoutBrackets () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		when(mockCalculation1.calculate()).thenReturn(3);
		calculations.put("calc1", mockCalculation1);

		when(mockCalculation2.calculate()).thenReturn(23);
		calculations.put("calc2", mockCalculation2);

		when(mockCalculation3.calculate()).thenReturn(8);
		calculations.put("calc3", mockCalculation3);

		when(mockCalculation4.calculate()).thenReturn(7);
		calculations.put("calc4", mockCalculation4);

		when(mockCalculation5.calculate()).thenReturn(5);
		calculations.put("calc5", mockCalculation5);

		objectUnderTest.getValuesNeededForCalculation("{calc1}+{calc2}-{calc3}*{calc4}/{calc5}");
		
		// When
		float result = objectUnderTest.evalulateCalculationString("{calc1}+{calc2}-{calc3}*{calc4}/{calc5}");
		
		// Then
		assertEquals (25.2, result, 0.001);
		
	}
	
	@Test
	public void shouldEvalulateComplexCalculationStringWithoutBracketsAndContainingSpaces () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		when(mockCalculation1.calculate()).thenReturn(3);
		calculations.put("calc1", mockCalculation1);

		when(mockCalculation2.calculate()).thenReturn(23);
		calculations.put("calc2", mockCalculation2);

		when(mockCalculation3.calculate()).thenReturn(8);
		calculations.put("calc3", mockCalculation3);

		when(mockCalculation4.calculate()).thenReturn(7);
		calculations.put("calc4", mockCalculation4);

		when(mockCalculation5.calculate()).thenReturn(5);
		calculations.put("calc5", mockCalculation5);

		objectUnderTest.getValuesNeededForCalculation(" {calc1} + {calc2} - {calc3} * {calc4} / {calc5} ");
		
		// When
		float result = objectUnderTest.evalulateCalculationString(" {calc1} + {calc2} - {calc3} * {calc4} / {calc5} ");
		
		// Then
		assertEquals (25.2, result, 0.001);
		
	}
	
	@Test
	public void shouldEvaluateTheInnermostBracketsAndReturnASimplifiedString () {
		// Given
		objectUnderTest = new DynamicCalculation ("", calculations);

		when(mockCalculation1.calculate()).thenReturn(3);
		calculations.put("calc1", mockCalculation1);

		when(mockCalculation2.calculate()).thenReturn(23);
		calculations.put("calc2", mockCalculation2);

		when(mockCalculation3.calculate()).thenReturn(8);
		calculations.put("calc3", mockCalculation3);

		when(mockCalculation4.calculate()).thenReturn(7);
		calculations.put("calc4", mockCalculation4);

		when(mockCalculation5.calculate()).thenReturn(5);
		calculations.put("calc5", mockCalculation5);

		objectUnderTest.getValuesNeededForCalculation(" {calc1} + ((({calc2} - {calc3}) * {calc4}) / {calc5}) ");
		
		// When
		String simplifiedString = objectUnderTest.evaluateOneSetOfBrackets(" {calc1} + ((({calc2} - {calc3}) * {calc4}) / {calc5}) ", 1);

		// Then
		assertEquals ("{calc1}+(({temp1}*{calc4})/{calc5})", simplifiedString);
		assertEquals (new Float(15), objectUnderTest.values.get("temp1"));
	}
	
	@Test
	public void shouldEvalulateComplexCalculationStringContainingBrackets () {
		// Given
		objectUnderTest = new DynamicCalculation (" {calc1} + ((({calc2} - {calc3}) * {calc4}) / {calc5}) ", calculations);

		when(mockCalculation1.calculate()).thenReturn(3);
		calculations.put("calc1", mockCalculation1);

		when(mockCalculation2.calculate()).thenReturn(23);
		calculations.put("calc2", mockCalculation2);

		when(mockCalculation3.calculate()).thenReturn(8);
		calculations.put("calc3", mockCalculation3);

		when(mockCalculation4.calculate()).thenReturn(7);
		calculations.put("calc4", mockCalculation4);

		when(mockCalculation5.calculate()).thenReturn(5);
		calculations.put("calc5", mockCalculation5);

		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (24, result);
	}
	
	@Test
	public void shouldEvalulateComplexCalculationStringContainingBracketsAndRoundUp () {
		// Given
		objectUnderTest = new DynamicCalculation (" {calc1} + ((({calc2} - {calc3}) * {calc4}) / {calc5}) ", calculations);

		when(mockCalculation1.calculate()).thenReturn(7);
		calculations.put("calc1", mockCalculation1);

		when(mockCalculation2.calculate()).thenReturn(23);
		calculations.put("calc2", mockCalculation2);

		when(mockCalculation3.calculate()).thenReturn(17);
		calculations.put("calc3", mockCalculation3);

		when(mockCalculation4.calculate()).thenReturn(43);
		calculations.put("calc4", mockCalculation4);

		when(mockCalculation5.calculate()).thenReturn(13);
		calculations.put("calc5", mockCalculation5);

		// When
		int result = objectUnderTest.calculate();
		
		// Then (rounded from 26.84615)
		assertEquals (27, result);
	}

}
