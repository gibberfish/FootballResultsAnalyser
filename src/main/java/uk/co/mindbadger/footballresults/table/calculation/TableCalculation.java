package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public abstract class TableCalculation<K,L,M> {
	
	protected Team<K> team;
	protected TableRow<K,L,M> previousTableRow;
	protected Fixture<K> fixture;
	
	public TableCalculation (Team<K> team, TableRow<K,L,M> previousTableRow, Fixture<K> fixture) {
		this.team = team;
		this.previousTableRow = previousTableRow;
		this.fixture = fixture;
	}
	
	public int calculate() {
		return calculate(false);
	}
	
	public abstract int calculate (boolean reCalculate);
}
