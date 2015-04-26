package uk.co.mindbadger.footballresults.table;

import java.util.Map;

import uk.co.mindbadger.footballresults.table.calculation.Calculation;
import uk.co.mindbadger.footballresults.table.calculation.CalculationMapFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowAfterResult<K,L,M> extends TableRow<K,L,M> {
	private TableRow<K,L,M> previousTableRow;
	private Fixture<K> fixture;
	private Map<String, Calculation> calculations;
	private CalculationMapFactory<K, L, M> calculationMapFactory;
	
	public TableRowAfterResult (Team<K> team, Table<K,L,M> parentTable) {
		super(team, parentTable);
	}
	
	public TableRowAfterResult (Team<K> team, Table<K,L,M> parentTable, TableRow<K,L,M> previousTableRow, Fixture<K> fixture) {
		super(team, parentTable);
		
		if (team == null || parentTable == null || previousTableRow == null || fixture == null) {
			throw new IllegalArgumentException("Please supply a Team, a parent table, a previous TableRow and a Fixture");
		}
		
		this.previousTableRow = previousTableRow;
		this.fixture = fixture;
	}

	@Override
	public int getAttribute(String attributeId) {
		if (calculationMapFactory == null) {
			throw new IllegalStateException("The TableRow requires a CalculationMapFactory.");
		}
		
		if (calculations == null) {
			calculations = calculationMapFactory.createCalculations(team, previousTableRow, fixture);
		}
		
		Calculation calculation = calculations.get(attributeId);
		if (calculation == null) {
			throw new IllegalArgumentException("No value for " + attributeId);
		}
		
		return calculation.calculate();
	}

	public void setCalculationMapFactory(CalculationMapFactory<K, L, M> calculationMapFactory) {
		this.calculationMapFactory = calculationMapFactory;
	}
}
