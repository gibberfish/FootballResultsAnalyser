package mindbadger.footballresults.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Table {
	Logger logger = Logger.getLogger(Table.class);
	
	private Map<String, TableRow> tableRows = new HashMap<String, TableRow> ();

	public Table (Table previousTable) {
		if (previousTable != null) {
			for (TableRow row : previousTable.getSortedTable()) {
				addRow(row);
			}
		}
	}
	
	public List<TableRow> getSortedTable() {
		Collection<TableRow> values = tableRows.values();
		List<TableRow> list = new ArrayList<TableRow>(values);
		Collections.sort(list);
				
		return list;
	}

	public int getIndexOfTableRow(TableRow tableRow) {
		return (getSortedTable().indexOf(tableRow)+1);
	}
	
	public void addRow (TableRow tableRow) {
		logger.info("Adding " + tableRow);
		tableRows.put(tableRow.getTeam().getTeamId(), tableRow);
	}
	
	public TableRow getTableRowForTeam (String teamId) {
		return tableRows.get(teamId);
	}
	
	//TODO need test for this
	public int getLeaguePositionForTeamWithId(String teamId) {
		return (getSortedTable().indexOf(getTableRowForTeam(teamId))+1);
	}
}
