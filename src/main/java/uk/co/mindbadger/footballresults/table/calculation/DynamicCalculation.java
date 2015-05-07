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
	
	protected Map<String,Float> getValuesNeededForCalculation (String calculationString) {
		return null;
	}
	
	protected String evaluateBrackets (String calculationString) {
		return null;
	}
	
	protected float evaluateSingleExpression (float currentValue, String operand, String operator) {
		int newValue = calculations.get(operand).calculate();
		
		switch (operator) {
			case "+":
				return (float) (currentValue + newValue);
			case "-":
				return (float) (currentValue - newValue);
			case "/":
				return (float) (currentValue / (float)newValue);
			case "*":
				return (float) (currentValue * newValue);
			default:
				throw new IllegalArgumentException ("Invalid Calculation String (invalid operator)");
		}
	}

	protected boolean hasBrackets (String calculationString) {
		int leftBrackets = calculationString.split("\\(", -1).length-1;
		int rightBrackets = calculationString.split("\\)", -1).length-1;
		
		if (leftBrackets != rightBrackets) {
			throw new IllegalArgumentException ("Invalid Calculation String (brackets don't match)");
		}
		
		return (leftBrackets > 0);
	}
	
	//TODO Needs a set of tests after the spike to get it this far... 
	protected float evalulateCalculationString (String calculationString) {
		// This function assumes that no brackets exist in the calculation string passed in
		// e.g. {A}+{B}*{C}
		
		calculationString = calculationString.replaceAll("\\s+","");
		boolean expectingOperand = true;
		float value = 0;
		String operator = "+";
		int start = 0;
		
		while (start != -1) {
			if (expectingOperand) {
				start = calculationString.indexOf('{', start);
				
				if (start != -1) {
					int end = calculationString.indexOf('}', start);
					String operand = calculationString.substring((start+1), end);
					
					//evaluateSingleExpression(value, operand, operator);
					System.out.println("start = " + start + ", end = "+ end);
					System.out.println("evaluateSingleExpression(" + value + ", " + operand + ", " + operator + ");");
					
					start = end+1;
					expectingOperand = false;
				}
			} else {
				int startOfNextOperand = calculationString.indexOf('{', start);
				if (startOfNextOperand == -1) {
					operator = calculationString.substring(start);
				} else {
					operator = calculationString.substring(start, (startOfNextOperand));
				}
				System.out.println("Next operator = " + operator);
				expectingOperand = true;
			}
		}
		
		return value;
	}
	
	public static void main (String args[]) {
		DynamicCalculation dc = new DynamicCalculation ("", null);
		dc.evalulateCalculationString("{AAA}+{BB}-{CCCCC}*{D}/{EE}");
	}
}
