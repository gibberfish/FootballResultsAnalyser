package mindbadger.footballresults.table;

import static mindbadger.footballresults.table.AttributeIds.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import mindbadger.footballresults.season.TeamFixtureContext;
import mindbadger.footballresults.table.TableRow;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.footballresultsanalyser.domain.Fixture;
import mindbadger.footballresultsanalyser.domain.Team;

public class AbstractTableRowTest {
	
	@Mock
	Team mockTeam1;

	@Mock
	Team mockTeam2;

	@Mock
	private TableRow mockPreviousTableRow;
	
	@Mock
	private Fixture mockFixture;
	
	@Mock
	private TeamFixtureContext mockFixtureTeamContext;
	
	@Mock
	private TeamFixtureContext mockOppositionTeamContext;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldCorrectlyCompareRowsWhereThePointsAreDifferent () {
		// Given
		TableRow row1 = new TableRow (mockTeam1) {

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

			@Override
			public TableRow getPreviousTableRow() {
				return mockPreviousTableRow;
			}

			@Override
			public Fixture getFixture() {
				return mockFixture;
			}

			@Override
			public TeamFixtureContext getFixtureTeamContext() {
				return mockFixtureTeamContext;
			}

			@Override
			public TeamFixtureContext getOppositionTeamContext() {
				return mockOppositionTeamContext;
			}
		};
		
		TableRow row2 = new TableRow (mockTeam1) {
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
			

			@Override
			public TableRow getPreviousTableRow() {
				return mockPreviousTableRow;
			}

			@Override
			public Fixture getFixture() {
				return mockFixture;
			}

			@Override
			public TeamFixtureContext getFixtureTeamContext() {
				return mockFixtureTeamContext;
			}

			@Override
			public TeamFixtureContext getOppositionTeamContext() {
				return mockOppositionTeamContext;
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
		TableRow row1 = new TableRow (mockTeam1) {
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

			@Override
			public TableRow getPreviousTableRow() {
				return mockPreviousTableRow;
			}

			@Override
			public Fixture getFixture() {
				return mockFixture;
			}

			@Override
			public TeamFixtureContext getFixtureTeamContext() {
				return mockFixtureTeamContext;
			}

			@Override
			public TeamFixtureContext getOppositionTeamContext() {
				return mockOppositionTeamContext;
			}
		};
		
		TableRow row2 = new TableRow (mockTeam1) {
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
			

			@Override
			public TableRow getPreviousTableRow() {
				return mockPreviousTableRow;
			}

			@Override
			public Fixture getFixture() {
				return mockFixture;
			}

			@Override
			public TeamFixtureContext getFixtureTeamContext() {
				return mockFixtureTeamContext;
			}

			@Override
			public TeamFixtureContext getOppositionTeamContext() {
				return mockOppositionTeamContext;
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
		TableRow row1 = new TableRow (mockTeam1) {
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

			@Override
			public TableRow getPreviousTableRow() {
				return mockPreviousTableRow;
			}

			@Override
			public Fixture getFixture() {
				return mockFixture;
			}

			@Override
			public TeamFixtureContext getFixtureTeamContext() {
				return mockFixtureTeamContext;
			}

			@Override
			public TeamFixtureContext getOppositionTeamContext() {
				return mockOppositionTeamContext;
			}
		};
		
		TableRow row2 = new TableRow (mockTeam1) {
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
			

			@Override
			public TableRow getPreviousTableRow() {
				return mockPreviousTableRow;
			}

			@Override
			public Fixture getFixture() {
				return mockFixture;
			}

			@Override
			public TeamFixtureContext getFixtureTeamContext() {
				return mockFixtureTeamContext;
			}

			@Override
			public TeamFixtureContext getOppositionTeamContext() {
				return mockOppositionTeamContext;
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
		
		TableRow row1 = new TableRow (mockTeam1) {
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
			

			@Override
			public TableRow getPreviousTableRow() {
				return mockPreviousTableRow;
			}

			@Override
			public Fixture getFixture() {
				return mockFixture;
			}

			@Override
			public TeamFixtureContext getFixtureTeamContext() {
				return mockFixtureTeamContext;
			}

			@Override
			public TeamFixtureContext getOppositionTeamContext() {
				return mockOppositionTeamContext;
			}
		};
		
		TableRow row2 = new TableRow (mockTeam2) {
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
			

			@Override
			public TableRow getPreviousTableRow() {
				return mockPreviousTableRow;
			}

			@Override
			public Fixture getFixture() {
				return mockFixture;
			}

			@Override
			public TeamFixtureContext getFixtureTeamContext() {
				return mockFixtureTeamContext;
			}

			@Override
			public TeamFixtureContext getOppositionTeamContext() {
				return mockOppositionTeamContext;
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
		
		TableRow row1 = new TableRow (mockTeam1) {
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
			

			@Override
			public TableRow getPreviousTableRow() {
				return mockPreviousTableRow;
			}

			@Override
			public Fixture getFixture() {
				return mockFixture;
			}

			@Override
			public TeamFixtureContext getFixtureTeamContext() {
				return mockFixtureTeamContext;
			}

			@Override
			public TeamFixtureContext getOppositionTeamContext() {
				return mockOppositionTeamContext;
			}
		};

		// When
		Team team = row1.getTeam();
		
		// Then
		assertEquals (mockTeam1, team);
	}
	
	@Test
	public void shouldGetToStringValue () {
		// Given
		when(mockTeam1.getTeamName()).thenReturn("Portsmouth");
		
		TableRow row1 = new TableRow (mockTeam1) {
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
			

			@Override
			public TableRow getPreviousTableRow() {
				return mockPreviousTableRow;
			}

			@Override
			public Fixture getFixture() {
				return mockFixture;
			}

			@Override
			public TeamFixtureContext getFixtureTeamContext() {
				return mockFixtureTeamContext;
			}

			@Override
			public TeamFixtureContext getOppositionTeamContext() {
				return mockOppositionTeamContext;
			}
		};

		// When
		
		// Then
		assertEquals ("TableRow[team:Portsmouth,points:40,scored:50,conceded:10]", row1.toString());
	}

}
