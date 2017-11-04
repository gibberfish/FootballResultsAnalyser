package mindbadger.football.table;

import static mindbadger.football.table.AttributeIds.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import mindbadger.football.table.InitialTableRow;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.footballresultsanalyser.domain.Team;

public class InitialTableRowTest {	
	private final String TEAM_ID = "123";
	private final String TEAM_NAME = "Portsmouth";
	
	private InitialTableRow objectUnderTest;
	
	@Mock
	private Team mockTeam1;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		when (mockTeam1.getTeamId()).thenReturn(TEAM_ID);
		when (mockTeam1.getTeamName()).thenReturn(TEAM_NAME);
	}

	@Test
	public void shouldCreateAnInitialTableRow () {
		// Given
		objectUnderTest = new InitialTableRow (mockTeam1);

		// When

		// Then
		assertEquals (TEAM_ID, objectUnderTest.getTeam().getTeamId());
		assertEquals (TEAM_NAME, objectUnderTest.getTeam().getTeamName());
		assertEquals (0, objectUnderTest.getAttribute(GAMES_PLAYED));
		assertEquals (0, objectUnderTest.getAttribute(GAMES_WON));
		assertEquals (0, objectUnderTest.getAttribute(GAMES_DRAWN));
		assertEquals (0, objectUnderTest.getAttribute(GAMES_LOST));
		assertEquals (0, objectUnderTest.getAttribute(GOALS_SCORED));
		assertEquals (0, objectUnderTest.getAttribute(GOALS_CONCEDED));
		assertEquals (0, objectUnderTest.getAttribute(GOAL_DIFFERENCE));
		assertEquals (0, objectUnderTest.getAttribute(POINTS));
	}	
}
