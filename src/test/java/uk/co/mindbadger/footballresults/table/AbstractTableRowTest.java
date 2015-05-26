package uk.co.mindbadger.footballresults.table;

import static uk.co.mindbadger.footballresults.table.AttributeIds.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class AbstractTableRowTest {
	
	@Mock
	Team<String> mockTeam1;

	@Mock
	Team<String> mockTeam2;

	@Mock
	Table<String,String,String> mockParentTable;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldCorrectlyCompareRowsWhereThePointsAreDifferent () {
		// Given
		TableRow<String,String,String> row1 = new TableRow<String,String,String> (mockTeam1, mockParentTable) {
			@Override
			public int getAttribute(String attributeId) {
				if (GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (GAMES_WON.equals(attributeId)) {
					return 12;
				} else if (GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (GOALS_SCORED.equals(attributeId)) {
					return 45;
				} else if (GOALS_CONCEDED.equals(attributeId)) {
					return 12;
				} else if (GOAL_DIFFERENCE.equals(attributeId)) {
					return 33;
				} else if (POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
			
		};
		
		TableRow<String,String,String> row2 = new TableRow<String,String,String> (mockTeam1, mockParentTable) {
			public int getAttribute(String attributeId) {
				if (GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (GAMES_WON.equals(attributeId)) {
					return 2;
				} else if (GAMES_DRAWN.equals(attributeId)) {
					return 5;
				} else if (GAMES_LOST.equals(attributeId)) {
					return 11;
				} else if (GOALS_SCORED.equals(attributeId)) {
					return 12;
				} else if (GOALS_CONCEDED.equals(attributeId)) {
					return 52;
				} else if (GOAL_DIFFERENCE.equals(attributeId)) {
					return -40;
				} else if (POINTS.equals(attributeId)) {
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
		TableRow<String,String,String> row1 = new TableRow<String,String,String> (mockTeam1, mockParentTable) {
			public int getAttribute(String attributeId) {
				if (GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (GAMES_WON.equals(attributeId)) {
					return 12;
				} else if (GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (GOALS_SCORED.equals(attributeId)) {
					return 45;
				} else if (GOALS_CONCEDED.equals(attributeId)) {
					return 12;
				} else if (GOAL_DIFFERENCE.equals(attributeId)) {
					return 33;
				} else if (POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
		};
		
		TableRow<String,String,String> row2 = new TableRow<String,String,String> (mockTeam1, mockParentTable) {
			public int getAttribute(String attributeId) {
				if (GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (GAMES_WON.equals(attributeId)) {
					return 12;
				} else if (GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (GOALS_SCORED.equals(attributeId)) {
					return 40;
				} else if (GOALS_CONCEDED.equals(attributeId)) {
					return 20;
				} else if (GOAL_DIFFERENCE.equals(attributeId)) {
					return 20;
				} else if (POINTS.equals(attributeId)) {
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
		TableRow<String,String,String> row1 = new TableRow<String,String,String> (mockTeam1, mockParentTable) {
			public int getAttribute(String attributeId) {
				if (GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (GAMES_WON.equals(attributeId)) {
					return 12;
				} else if (GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (GOALS_SCORED.equals(attributeId)) {
					return 50;
				} else if (GOALS_CONCEDED.equals(attributeId)) {
					return 10;
				} else if (GOAL_DIFFERENCE.equals(attributeId)) {
					return 40;
				} else if (POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
		};
		
		TableRow<String,String,String> row2 = new TableRow<String,String,String> (mockTeam1, mockParentTable) {
			public int getAttribute(String attributeId) {
				if (GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (GAMES_WON.equals(attributeId)) {
					return 12;
				} else if (GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (GOALS_SCORED.equals(attributeId)) {
					return 49;
				} else if (GOALS_CONCEDED.equals(attributeId)) {
					return 9;
				} else if (GOAL_DIFFERENCE.equals(attributeId)) {
					return 40;
				} else if (POINTS.equals(attributeId)) {
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
		when(mockTeam1.getTeamName()).thenReturn("Portsmouth");
		when(mockTeam2.getTeamName()).thenReturn("Southampton");
		
		TableRow<String,String,String> row1 = new TableRow<String,String,String> (mockTeam1, mockParentTable) {
			public int getAttribute(String attributeId) {
				if (GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (GAMES_WON.equals(attributeId)) {
					return 12;
				} else if (GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (GOALS_SCORED.equals(attributeId)) {
					return 50;
				} else if (GOALS_CONCEDED.equals(attributeId)) {
					return 10;
				} else if (GOAL_DIFFERENCE.equals(attributeId)) {
					return 40;
				} else if (POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
		};
		
		TableRow<String,String,String> row2 = new TableRow<String,String,String> (mockTeam2, mockParentTable) {
			public int getAttribute(String attributeId) {
				if (GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (GAMES_WON.equals(attributeId)) {
					return 12;
				} else if (GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (GOALS_SCORED.equals(attributeId)) {
					return 50;
				} else if (GOALS_CONCEDED.equals(attributeId)) {
					return 10;
				} else if (GOAL_DIFFERENCE.equals(attributeId)) {
					return 40;
				} else if (POINTS.equals(attributeId)) {
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
	public void shouldGetTeam () {
		// Given
		when(mockTeam1.getTeamName()).thenReturn("Portsmouth");
		
		TableRow<String,String,String> row1 = new TableRow<String,String,String> (mockTeam1, mockParentTable) {
			public int getAttribute(String attributeId) {
				if (GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (GAMES_WON.equals(attributeId)) {
					return 12;
				} else if (GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (GOALS_SCORED.equals(attributeId)) {
					return 50;
				} else if (GOALS_CONCEDED.equals(attributeId)) {
					return 10;
				} else if (GOAL_DIFFERENCE.equals(attributeId)) {
					return 40;
				} else if (POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
		};

		// When
		Team<String> team = row1.getTeam();
		
		// Then
		assertEquals (mockTeam1, team);
	}
	
	@Test
	public void shouldGetToStringValue () {
		// Given
		when(mockTeam1.getTeamName()).thenReturn("Portsmouth");
		
		TableRow<String,String,String> row1 = new TableRow<String,String,String> (mockTeam1, mockParentTable) {
			public int getAttribute(String attributeId) {
				if (GAMES_PLAYED.equals(attributeId)) {
					return 18;
				} else if (GAMES_WON.equals(attributeId)) {
					return 12;
				} else if (GAMES_DRAWN.equals(attributeId)) {
					return 4;
				} else if (GAMES_LOST.equals(attributeId)) {
					return 2;
				} else if (GOALS_SCORED.equals(attributeId)) {
					return 50;
				} else if (GOALS_CONCEDED.equals(attributeId)) {
					return 10;
				} else if (GOAL_DIFFERENCE.equals(attributeId)) {
					return 40;
				} else if (POINTS.equals(attributeId)) {
					return 40;
				} else {
					return 0;
				}
			}
		};

		// When
		
		// Then
		assertEquals ("TableRow[team:Portsmouth,points:40,scored:50,conceded:10]", row1.toString());
	}

}
