package uk.co.mindbadger.footballresults.table.calculation;

import java.util.Map;

public class GamesWonCalculation extends CompositeCalculation {
	public GamesWonCalculation(Map<String,Calculation> calculations) {
		super(calculations);
	}

	@Override
	public int calculate(boolean reCalculate) {
		int gamesWonAtHome = calculations.get("gamesWonAtHome").calculate();
		int gamesWonAway = calculations.get("gamesWonAway").calculate();
		
		return gamesWonAtHome + gamesWonAway;
	}
}
