package uk.co.mindbadger.footballresults.table.calculation;

import java.util.HashMap;
import java.util.Map;

public class DynamicCalculation extends Calculation {
	protected Map<String,Calculation> calculations;
	private String calculationString;
	protected Map<String,Float> values;
	
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
		
		int tempIndex = 1;
		
		while (hasBrackets(currentCalculationString)) {
			currentCalculationString = evaluateOneSetOfBrackets(currentCalculationString, tempIndex);
			tempIndex++;
		}
		
		return Math.round(evalulateCalculationString(currentCalculationString));
	}
	
	protected Map<String,Float> getValuesNeededForCalculation (String calculationString) {
		values = new HashMap<String,Float> ();
		
		int start = 0;
		while (start != -1) {
			start = calculationString.indexOf('{', start);
			
			if (start != -1) {
				int end = calculationString.indexOf('}', start);
				String operand = calculationString.substring((start+1), end);
				int newValue = calculations.get(operand).calculate();
				
				values.put(operand, new Float(newValue));
				
				start = end+1;
			}
		}
		
		return values;
	}
	
	protected String evaluateOneSetOfBrackets (String calculationString, int tempIndex) {
		calculationString = calculationString.replaceAll("\\s+","");
		System.out.println("calculationString="+calculationString);
		int posOfFirstClosingBracket = calculationString.indexOf(')');
		int posOfMatchingOpeningBracket = 0;
		
		int pos = 0;
		while (pos != -1) {
			pos = calculationString.indexOf('(', posOfMatchingOpeningBracket);
			if (pos != -1 && pos < posOfFirstClosingBracket) {
				posOfMatchingOpeningBracket = pos+1;
			} else {
				break;
			}
		}
		
		String startString = calculationString.substring(0, (posOfMatchingOpeningBracket-1));
		System.out.println("start: " + startString);

		String endString = calculationString.substring(posOfFirstClosingBracket+1);
		System.out.println("end: " + endString);

		String simplifiedString = startString + "{temp" + tempIndex + "}" + endString;
		System.out.println("simplified string"+simplifiedString);
		
		String operand = calculationString.substring((posOfMatchingOpeningBracket), posOfFirstClosingBracket);
		System.out.println("operand = " + operand);
		
		float calculatedValue = evalulateCalculationString (operand);
		values.put("temp"+tempIndex, calculatedValue);
		
		return simplifiedString;
	}
	
	protected float evaluateSingleExpression (float currentValue, float newValue, String operator) {
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
	
	protected float evalulateCalculationString (String calculationString) {
		calculationString = calculationString.replaceAll("\\s+","");
		boolean expectingOperand = true;
		float currentValue = 0;
		String operator = "+";
		int start = 0;
		
		while (start != -1) {
			if (expectingOperand) {
				start = calculationString.indexOf('{', start);
				
				if (start != -1) {
					int end = calculationString.indexOf('}', start);
					String operand = calculationString.substring((start+1), end);
					float newValue = values.get(operand);
					
					currentValue = evaluateSingleExpression(currentValue, newValue, operator);
					
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
		
		return currentValue;
	}
}
