package uk.co.mindbadger.footballresults.table.calculation;

public class GoalDifferenceCalculation2 extends CompositeCalculation {
	public GoalDifferenceCalculation2(Calculation... calculations) {
		super(calculations);
	}

	@Override
	public int calculate(boolean reCalculate) {
		int goalsScored = calculations[0].calculate();
		int goalsConceded = calculations[1].calculate();
		
		return goalsScored - goalsConceded;
	}
}
