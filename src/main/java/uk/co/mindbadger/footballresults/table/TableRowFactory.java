package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresults.table.calculation.CalculationMapFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowFactory<K,L,M> {
	private CalculationMapFactory<K, L, M> calculationMapFactory;
	
	public TableRowFactory (CalculationMapFactory<K, L, M> calculationMapFactory) {
		this.calculationMapFactory = calculationMapFactory;
	}
	
	public TableRow<K,L,M> createTableRowFromFixture (Team<K> team, Table<K,L,M> parentTable, TableRow<K,L,M> previousTableRow, Fixture<K> fixture) {
		TableRowAfterResult<K,L,M> tableRow = new TableRowAfterResult<>(team, parentTable, previousTableRow, fixture);
		tableRow.setCalculationMapFactory(calculationMapFactory);
		return tableRow;
	}
}
