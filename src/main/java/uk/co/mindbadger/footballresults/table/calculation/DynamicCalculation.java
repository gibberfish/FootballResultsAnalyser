package uk.co.mindbadger.footballresults.table.calculation;

import java.util.HashMap;
import java.util.Map;

public class DynamicCalculation extends Calculation {
	protected Map<String,Calculation> calculations;
	private String calculationString;
	private Map<String,Float> values;
	
	public DynamicCalculation (String calculationString, Map<String,Calculation> calculations) {
		this.calculations = calculations;
		this.calculationString = calculationString;
	}
	
	/*
	 * This class must be able to parse the following symbols:
	 *    {} - this encloses other calculations that can be obtained from the map
	 *    + - this is used to add the results of calculations
	 *    - - this is used to subtract the results of calculations
	 *    * - this is used to multiply the results of calculations
	 *    / - this is used to divide the results of calculations
	 *    
	 *    e.g.
	 *    Percentage of games won at home could look like:
	 *    ({gamesWonAtHome} / {gamesWon}) * 100
	 *    
	 *    Goal difference would be:
	 *    {goalsScored} - {goalsConceded}
	 *    
	 *    Parser process:
	 *    e.g.
	 *    A + (((B - C) * D) / E)
	 *    
	 *    Find the first ) and work backwards: B-C
	 *    Put the result of this into a new variable X
	 *    So we now have: A + ((X * D) / E)
	 *    
	 *    Do it again - we now find X * D
	 *    Put the result of this into a new variable Y
	 *    So we now have: A + (Y / E)
	 * 
   	 *    Do it again - we now find Y / E
	 *    Put the result of this into a new variable Z
	 *    So we now have: A + Z
	 *    
	 *    There are no brackets, so now evaluate what's left
	 *    
	 *    Round the result up to an integer
	 *    
	 *    Throw an exception if:
	 *    * The brackets don't match
	 *    * Any of the calculations don't match
	 *    
	 *    
	 *    So, to implement:
	 *    Create a map of floats - copy in the calculation from the other map
	 */
	
	@Override
	public int calculate(boolean reCalculate) {
		String currentCalculationString = calculationString;
		
		values = getValuesNeededForCalculation(currentCalculationString);
		
		while (hasBrackets(currentCalculationString)) {
			currentCalculationString = evaluateBrackets(currentCalculationString);
		}
		
		return Math.round(evalulateCalculationString(currentCalculationString));
	}
	
	private Map<String,Float> getValuesNeededForCalculation (String calculationString) {
		return null;
	}
	
	private boolean hasBrackets (String calculationString) {
		int leftBrackets = calculationString.split("(", -1).length-1;
		int rightBrackets = calculationString.split(")", -1).length-1;
		
		if (leftBrackets != rightBrackets) {
			throw new IllegalArgumentException ("Invalid Calculation String (brackets don't match)");
		}
		
		return (leftBrackets > 0);
	}
	
	private String evaluateBrackets (String calculationString) {
		return null;
	}
	
	private float evalulateCalculationString (String calculationString) {
		// This function assumes that no brackets exist in the calculation string passed in
		calculationString = calculationString.replaceAll("\\s+","");
		
		int start = calculationString.indexOf('{');
		if (start != 0) {
			throw new IllegalArgumentException ("Invalid Calculation String (brackets don't match)");
		}
		
		/*
		 * Look for first value (must start with { )
		 * Get this value
		 * Look for operator (e.g. +, -, /, *)
		 * Look for next value (must start with { )
		 * Evaluate the result as a float
		 * Look for the next 
		 */
		return 0;
	}
	
	private float evaluateSingleExpression (String operand1, String operand2, String operator) {
		int value1 = calculations.get(operand1).calculate();
		int value2 = calculations.get(operand2).calculate();
		
		switch (operator) {
			case "+":
				return (float) (value1 + value2);
			case "-":
				return (float) (value1 - value2);
			case "/":
				return (float) ((float)value1 / (float)value2);
			case "*":
				return (float) (value1 * value2);
			default:
				throw new IllegalArgumentException ("Invalid Calculation String (invalid operator)");
		}
	}
}
