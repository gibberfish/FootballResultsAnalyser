package mindbadger.football.table.calculation;

public abstract class Calculation {
	public int calculate() {
		return calculate(false);
	}
	
	public abstract int calculate (boolean reCalculate);
}
