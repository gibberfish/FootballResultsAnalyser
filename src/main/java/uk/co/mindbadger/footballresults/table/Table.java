package uk.co.mindbadger.footballresults.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Table {
	protected Map<Integer, TableRow> tableRows = new HashMap<Integer, TableRow> ();

	public List<TableRow> getSortedTable() {
		Collection<TableRow> values = tableRows.values();
		List<TableRow> list = new ArrayList<TableRow>(values);
		Collections.sort(list);
		return list;
	}

	public int getIndexOfTableRow(TableRow tableRow) {
		return (getSortedTable().indexOf(tableRow)+1);
	}
}
