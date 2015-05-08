package uk.co.mindbadger.footballresults.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Table<K,L,M> {
	Logger logger = Logger.getLogger(Table.class);
	
	private Map<K, TableRow<K,L,M>> tableRows = new HashMap<K, TableRow<K,L,M>> ();

	public Table (Table<K,L,M> previousTable) {
		if (previousTable != null) {
			for (TableRow<K,L,M> row : previousTable.getSortedTable()) {
				addRow(row);
			}
		}
	}
	
	public List<TableRow<K,L,M>> getSortedTable() {
		Collection<TableRow<K,L,M>> values = tableRows.values();
		List<TableRow<K,L,M>> list = new ArrayList<TableRow<K,L,M>>(values);
		Collections.sort(list);
				
		return list;
	}

	public int getIndexOfTableRow(TableRow<K,L,M> tableRow) {
		return (getSortedTable().indexOf(tableRow)+1);
	}
	
	public void addRow (TableRow<K,L,M> tableRow) {
		tableRows.put(tableRow.getTeam().getTeamId(), tableRow);
	}
	
	public TableRow<K,L,M> getTableRowForTeam (K teamId) {
		return tableRows.get(teamId);
	}
}
