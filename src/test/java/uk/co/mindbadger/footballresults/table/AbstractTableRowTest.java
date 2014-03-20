package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.*;

import org.junit.*;

public class AbstractTableRowTest {
	@Test
	public void shouldCorrectlyCompareRowsWhereThePointsAreDifferent () {
		// Given
		TableRow row1 = new TableRow () {
			public Integer getTeamId() {return 123;}
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
		
		TableRow row2 = new TableRow () {
			public Integer getTeamId() {return 456;}
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

		// When
		int comparison1 = row1.compareTo(row2);
		
		// Then
		assertEquals (-1, comparison1);
		
		// When
		int comparison2 = row2.compareTo(row1);
		
		// Then
		assertEquals (1, comparison2);

	}
}
