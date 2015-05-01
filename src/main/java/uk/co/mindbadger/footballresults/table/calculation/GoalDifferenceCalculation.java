package uk.co.mindbadger.footballresults.table.calculation;

import java.util.Map;

public class GoalDifferenceCalculation extends CompositeCalculation {
	public GoalDifferenceCalculation(Map<String,Calculation> calculations) {
		super(calculations);
	}

	@Override
	public int calculate(boolean reCalculate) {
		int goalsScored = calculations.get("goalsScored").calculate();
		int goalsConceded = calculations.get("goalsConceded").calculate();
		
		return goalsScored - goalsConceded;
	}
}
