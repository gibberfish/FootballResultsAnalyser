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
			public int getGamesPlayed() {return 18;}
			public int getGamesWon() {return 12;}
			public int getGamesDrawn() {return 4;}
			public int getGamesLost() {return 2;}
			public int getGoalsScored() {return 45;}
			public int getGoalsConceded() {return 12;}
			public int getGoalDifference() {return 33;}
			public int getPoints() {return 40;}
		};
		
		TableRow<String,String,String> row2 = new TableRow<String,String,String> () {
			public String getTeamId() {return "456";}
			public String getTeamName() {return "Southampton";}
			public int getGamesPlayed() {return 18;}
			public int getGamesWon() {return 2;}
			public int getGamesDrawn() {return 5;}
			public int getGamesLost() {return 11;}
			public int getGoalsScored() {return 12;}
			public int getGoalsConceded() {return 52;}
			public int getGoalDifference() {return -40;}
			public int getPoints() {return 11;}
		};

		TableRow<String,String,String> row3 = new TableRow<String,String,String> () {
			public String getTeamId() {return "789";}
			public String getTeamName() {return "Aston Villa";}
			public int getGamesPlayed() {return 18;}
			public int getGamesWon() {return 2;}
			public int getGamesDrawn() {return 5;}
			public int getGamesLost() {return 11;}
			public int getGoalsScored() {return 12;}
			public int getGoalsConceded() {return 52;}
			public int getGoalDifference() {return -40;}
			public int getPoints() {return 11;}
		};

		TableRow<String,String,String> row4 = new TableRow<String,String,String> () {
			public String getTeamId() {return "159";}
			public String getTeamName() {return "Manchester United";}
			public int getGamesPlayed() {return 18;}
			public int getGamesWon() {return 12;}
			public int getGamesDrawn() {return 4;}
			public int getGamesLost() {return 2;}
			public int getGoalsScored() {return 44;}
			public int getGoalsConceded() {return 11;}
			public int getGoalDifference() {return 33;}
			public int getPoints() {return 40;}
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
