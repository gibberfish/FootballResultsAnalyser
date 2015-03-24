package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class AbstractTableTest {
	@Test
	public void shouldSortTableRows () {
		// Given
		TableRow<String,String,String> row1 = new TableRow<String,String,String> () {
			public String getTeamId() {return "123";}
			public String getTeamName() {return "Portsmouth";}
			public int get(String attributeId) {
				if (TableRow.GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (TableRow.GAMES_WON.equals(attributeId)) {
					return 12;
				} else if (TableRow.GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (TableRow.GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (TableRow.GOALS_SCORED.equals(attributeId)) {
					return 45;
				} else if (TableRow.GOALS_CONCEDED.equals(attributeId)) {
					return 12;
				} else if (TableRow.GOAL_DIFFERENCE.equals(attributeId)) {
					return 33;
				} else if (TableRow.POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
		};
		
		TableRow<String,String,String> row2 = new TableRow<String,String,String> () {
			public String getTeamId() {return "456";}
			public String getTeamName() {return "Southampton";}
			public int get(String attributeId) {
				if (TableRow.GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (TableRow.GAMES_WON.equals(attributeId)) {
					return 2;
				} else if (TableRow.GAMES_DRAWN.equals(attributeId)) {
					return 5;
				} else if (TableRow.GAMES_LOST.equals(attributeId)) {
					return 11;
				} else if (TableRow.GOALS_SCORED.equals(attributeId)) {
					return 12;
				} else if (TableRow.GOALS_CONCEDED.equals(attributeId)) {
					return 52;
				} else if (TableRow.GOAL_DIFFERENCE.equals(attributeId)) {
					return -40;
				} else if (TableRow.POINTS.equals(attributeId)) {
					return 11;
				} else {
					return 0;
				}
			}
		};

		TableRow<String,String,String> row3 = new TableRow<String,String,String> () {
			public String getTeamId() {return "789";}
			public String getTeamName() {return "Aston Villa";}
			public int get(String attributeId) {
				if (TableRow.GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (TableRow.GAMES_WON.equals(attributeId)) {
					return 2;
				} else if (TableRow.GAMES_DRAWN.equals(attributeId)) {
					return 5;
				} else if (TableRow.GAMES_LOST.equals(attributeId)) {
					return 11;
				} else if (TableRow.GOALS_SCORED.equals(attributeId)) {
					return 12;
				} else if (TableRow.GOALS_CONCEDED.equals(attributeId)) {
					return 52;
				} else if (TableRow.GOAL_DIFFERENCE.equals(attributeId)) {
					return -40;
				} else if (TableRow.POINTS.equals(attributeId)) {
					return 11;
				} else {
					return 0;
				}
			}
		};

		TableRow<String,String,String> row4 = new TableRow<String,String,String> () {
			public String getTeamId() {return "159";}
			public String getTeamName() {return "Manchester United";}
			public int get(String attributeId) {
				if (TableRow.GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (TableRow.GAMES_WON.equals(attributeId)) {
					return 12;
				} else if (TableRow.GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (TableRow.GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (TableRow.GOALS_SCORED.equals(attributeId)) {
					return 44;
				} else if (TableRow.GOALS_CONCEDED.equals(attributeId)) {
					return 11;
				} else if (TableRow.GOAL_DIFFERENCE.equals(attributeId)) {
					return 33;
				} else if (TableRow.POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
		};

		Table<String,String,String> table = new Table<String,String,String> () { };
		table.tableRows.put("123", row1);
		table.tableRows.put("456", row2);
		table.tableRows.put("789", row3);
		table.tableRows.put("159", row4);
		
		// When
		List<TableRow<String,String,String>> sortedTable = table.getSortedTable ();
		
		// Then
		assertEquals (row1, sortedTable.get(0));
		assertEquals (row4, sortedTable.get(1));
		assertEquals (row3, sortedTable.get(2));
		assertEquals (row2, sortedTable.get(3));
		
		// When
		int index = table.getIndexOfTableRow (row3);
		
		// Then
		assertEquals (3, index);
	}
}
