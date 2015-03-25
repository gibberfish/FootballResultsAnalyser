package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class InitialTableRowTest {	
	private final String TEAM_ID = "123";
	private final String TEAM_NAME = "Portsmouth";
	
	private InitialTableRow<String,String,String> objectUnderTest;
	
	@Mock
	private Team<String> mockTeam1;
	
	@Mock
	Table<String,String,String> mockParentTable;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		when (mockTeam1.getTeamId()).thenReturn(TEAM_ID);
		when (mockTeam1.getTeamName()).thenReturn(TEAM_NAME);
	}

	@Test
	public void shouldCreateAnInitialTableRow () {
		// Given
		objectUnderTest = new InitialTableRow<String,String,String> (mockTeam1, mockParentTable);

		// When

		// Then
		assertEquals (TEAM_ID, objectUnderTest.getTeam().getTeamId());
		assertEquals (TEAM_NAME, objectUnderTest.getTeam().getTeamName());
		assertEquals (0, objectUnderTest.get(TableRow.GAMES_PLAYED));
		assertEquals (0, objectUnderTest.get(TableRow.GAMES_WON));
		assertEquals (0, objectUnderTest.get(TableRow.GAMES_DRAWN));
		assertEquals (0, objectUnderTest.get(TableRow.GAMES_LOST));
		assertEquals (0, objectUnderTest.get(TableRow.GOALS_SCORED));
		assertEquals (0, objectUnderTest.get(TableRow.GOALS_CONCEDED));
		assertEquals (0, objectUnderTest.get(TableRow.GOAL_DIFFERENCE));
		assertEquals (0, objectUnderTest.get(TableRowAfterResult.POINTS));
	}	
}
