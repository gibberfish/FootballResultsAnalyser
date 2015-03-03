package uk.co.mindbadger.footballresults.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Table<K> {
	protected Map<K, TableRow<K>> tableRows = new HashMap<K, TableRow<K>> ();

	public List<TableRow<K>> getSortedTable() {
		Collection<TableRow<K>> values = tableRows.values();
		List<TableRow<K>> list = new ArrayList<TableRow<K>>(values);
		Collections.sort(list);
		return list;
	}

	public int getIndexOfTableRow(TableRow<K> tableRow) {
		return (getSortedTable().indexOf(tableRow)+1);
	}
}
