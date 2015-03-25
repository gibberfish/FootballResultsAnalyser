package uk.co.mindbadger.footballresults.table;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.mindbadger.footballresults.table.calculation.Calculation;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowAfterResult<K,L,M> extends TableRow<K,L,M> {
	private TableRow<K,L,M> previousTableRow;
	private Fixture<K> fixture;
	private Map<String, String> calculationClasses;
	private Map<String, Calculation> calculations;
	
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
	public int get(String attributeId) {
		return calculations.get(attributeId).calculate();
	}
	
	@Autowired
	public void setCalculationClasses(Map<String, String> calculationClasses) {
		this.calculationClasses = calculationClasses;
	}
}
