package uk.co.mindbadger.footballresults.table.calculation;

import java.util.Map;

public class GamesPlayedCalculation extends CompositeCalculation {
	public GamesPlayedCalculation(Map<String,Calculation> calculations) {
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