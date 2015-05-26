package uk.co.mindbadger.footballresults.table.calculation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

public class DynamicCalculation extends Calculation {
	Logger logger = Logger.getLogger(DynamicCalculation.class);
	
	protected Map<String,Calculation> calculations;
	private String calculationString;
	protected Map<String,Float> values;
	
	//TODO #5 'Configure' instances of this class in Spring that use the granular classes
	public DynamicCalculation (String calculationString, Map<String,Calculation> calculations) {
		this.calculations = calculations;
		this.calculationString = calculationString;
	}
		
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
				Calculation calculation = calculations.get(operand);
				
				
				//TODO debug only - remove
				if (calculation == null) {
					logger.error("We want to get attribute :" + operand + " but it cannot be found. The attributes we have for this table row are:");
					Iterator i = calculations.keySet().iterator();
					while (i.hasNext()) {
						logger.error("...KEY: " + i.next());
					}

				}
				
				
				int newValue = calculation.calculate();
				
				values.put(operand, new Float(newValue));
				
				start = end+1;
			}
		}
		
		return values;
	}
	
	protected String evaluateOneSetOfBrackets (String calculationString, int tempIndex) {
		calculationString = calculationString.replaceAll("\\s+","");
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
		String endString = calculationString.substring(posOfFirstClosingBracket+1);

		String simplifiedString = startString + "{temp" + tempIndex + "}" + endString;
		
		String operand = calculationString.substring((posOfMatchingOpeningBracket), posOfFirstClosingBracket);
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
				expectingOperand = true;
			}
		}
		
		return currentValue;
	}
}
