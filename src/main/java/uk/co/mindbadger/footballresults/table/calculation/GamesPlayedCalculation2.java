package uk.co.mindbadger.footballresults.table.calculation;

import java.util.Map;

public class GamesPlayedCalculation2 extends CompositeCalculation {
	public GamesPlayedCalculation2(Map<String,Calculation> calculations) {
		super(calculations);
	}

	@Override
	public int calculate(boolean reCalculate) {
		int gamesWon = calculations.get("gamesWon").calculate();
		int gamesDrawn = calculations.get("gamesDrawn").calculate();
		int gamesLost = calculations.get("gamesLost").calculate();
		
		return gamesWon + gamesDrawn + gamesLost;
	}
}
