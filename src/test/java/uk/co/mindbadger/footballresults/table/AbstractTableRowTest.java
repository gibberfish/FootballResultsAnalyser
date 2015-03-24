package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AbstractTableRowTest {
	
	@Mock
	Table<String,String,String> mockTable;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldCorrectlyCompareRowsWhereThePointsAreDifferent () {
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

		// When
		int comparison1 = row1.compareTo(row2);
		
		// Then
		assertEquals (-1, comparison1);
		
		// When
		int comparison2 = row2.compareTo(row1);
		
		// Then
		assertEquals (1, comparison2);
	}
	
	@Test
	public void shouldCorrectlyCompareRowsWhereThePointsAreTheSameButGoalDifferenceIsDifferent () {
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
					return 12;
				} else if (TableRow.GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (TableRow.GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (TableRow.GOALS_SCORED.equals(attributeId)) {
					return 40;
				} else if (TableRow.GOALS_CONCEDED.equals(attributeId)) {
					return 20;
				} else if (TableRow.GOAL_DIFFERENCE.equals(attributeId)) {
					return 20;
				} else if (TableRow.POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
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

	@Test
	public void shouldCorrectlyCompareRowsWhereThePointsAreTheSameAndTheGoalDifferenceIsTheSameButTheGoalsScoredIsDifferent () {
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
					return 50;
				} else if (TableRow.GOALS_CONCEDED.equals(attributeId)) {
					return 10;
				} else if (TableRow.GOAL_DIFFERENCE.equals(attributeId)) {
					return 40;
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
					return 12;
				} else if (TableRow.GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (TableRow.GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (TableRow.GOALS_SCORED.equals(attributeId)) {
					return 49;
				} else if (TableRow.GOALS_CONCEDED.equals(attributeId)) {
					return 9;
				} else if (TableRow.GOAL_DIFFERENCE.equals(attributeId)) {
					return 40;
				} else if (TableRow.POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
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

	@Test
	public void shouldCorrectlyCompareRowsWhereEverythingIsEqualInWhichCaseWeDoItAlphabetically () {
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
					return 50;
				} else if (TableRow.GOALS_CONCEDED.equals(attributeId)) {
					return 10;
				} else if (TableRow.GOAL_DIFFERENCE.equals(attributeId)) {
					return 40;
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
					return 12;
				} else if (TableRow.GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (TableRow.GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (TableRow.GOALS_SCORED.equals(attributeId)) {
					return 50;
				} else if (TableRow.GOALS_CONCEDED.equals(attributeId)) {
					return 10;
				} else if (TableRow.GOAL_DIFFERENCE.equals(attributeId)) {
					return 40;
				} else if (TableRow.POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
		};

		// When
		int comparison1 = row1.compareTo(row2);
		
		// Then
		assertTrue (comparison1 <= -1);
		
		// When
		int comparison2 = row2.compareTo(row1);
		
		// Then
		assertTrue (comparison2 >= 1);
	}
	
	@Test
	public void shouldGetLeaguePositionFromTable () {
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
					return 50;
				} else if (TableRow.GOALS_CONCEDED.equals(attributeId)) {
					return 10;
				} else if (TableRow.GOAL_DIFFERENCE.equals(attributeId)) {
					return 40;
				} else if (TableRow.POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
		};

		row1.parentTable = mockTable;
		
		when (mockTable.getIndexOfTableRow(row1)).thenReturn(4);
		
		// When
		int leaguePosition = row1.getLeaguePosition ();
		
		// Then
		assertEquals (4, leaguePosition);
	}
}
