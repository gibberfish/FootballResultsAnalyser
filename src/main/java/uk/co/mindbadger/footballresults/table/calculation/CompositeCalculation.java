package uk.co.mindbadger.footballresults.table.calculation;

public abstract class CompositeCalculation extends Calculation {
	protected Calculation[] calculations;
	
	public CompositeCalculation (Calculation... calcs) {
		this.calculations = calcs;
	}
}
