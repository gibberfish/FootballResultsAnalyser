package uk.co.mindbadger.footballresults.table.calculation;

import java.util.Map;

public abstract class CompositeCalculation extends Calculation {
	protected Map<String,Calculation> calculations;
	
	public CompositeCalculation (Map<String,Calculation> calcs) {
		this.calculations = calcs;
	}
}
