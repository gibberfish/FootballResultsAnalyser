package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class InitialTableRowTest {	
	private final Integer TEAM_ID = 123;
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
		assertEquals (TEAM_ID, objectUnderTest.getTeamId());
		assertEquals (TEAM_NAME, objectUnderTest.getTeamName());
		assertEquals (0, objectUnderTest.getGamesPlayed());
		assertEquals (0, objectUnderTest.getGamesWon());
		assertEquals (0, objectUnderTest.getGamesDrawn());
		assertEquals (0, objectUnderTest.getGamesLost());
		assertEquals (0, objectUnderTest.getGoalsScored());
		assertEquals (0, objectUnderTest.getGoalsConceded());
		assertEquals (0, objectUnderTest.getGoalDifference());
		assertEquals (0, objectUnderTest.getPoints());
	}	
}
