package uk.co.mindbadger.footballresults.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Table<K,L,M> {
	protected Map<K, TableRow<K,L,M>> tableRows = new HashMap<K, TableRow<K,L,M>> ();

	public List<TableRow<K,L,M>> getSortedTable() {
		Collection<TableRow<K,L,M>> values = tableRows.values();
		List<TableRow<K,L,M>> list = new ArrayList<TableRow<K,L,M>>(values);
		Collections.sort(list);
		return list;
	}

	public int getIndexOfTableRow(TableRow<K,L,M> tableRow) {
		return (getSortedTable().indexOf(tableRow)+1);
	}
}
