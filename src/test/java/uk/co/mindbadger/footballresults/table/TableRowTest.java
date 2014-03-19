package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowTest {	
	private final Integer TEAM_ID = 123;
	private final String TEAM_NAME = "Portsmouth";
	
	private TableRow objectUnderTest;
	
	@Mock
	private TableRow mockPreviousTableRow;
	
	@Mock
	private Fixture mockFixture;
	
	@Mock
	private Team mockTeam;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		when (mockTeam.getTeamId()).thenReturn(TEAM_ID);
		when (mockTeam.getTeamName()).thenReturn(TEAM_NAME);
	}

	@Test
	public void shouldCreateAnEmptyTableRow () {
		// Given
		objectUnderTest = new TableRow (mockTeam);

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
