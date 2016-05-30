package uk.co.mindbadger.footballresults.table.calculation;

import static uk.co.mindbadger.footballresults.table.AttributeIds.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowAfterResult;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesWonAtHomeVsTeamsAboveCalculationTest {
	private GamesWonAtHomeVsTeamsAboveCalculation objectUnderTest;
	
	@Mock
	private TableRowAfterResult mockPreviousTableRow;

	@Mock
	private Fixture mockFixture;
	
	@Mock
	private Team mockTeamForCalculation;

	@Mock
	private TeamFixtureContext mockFixtureTeamContext;
	
	@Mock
	private TeamFixtureContext mockOppositionTeamContext;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * SCENARIOS
	 * 
	 * First Game of Season (no above or below)
	 * --------------------
	 * Home Win
	 * Home Defeat
	 * Home Draw
	 * Away Win
	 * Away Defeat
	 * Away Draw
	 * 
	 * After First Game of Season
	 * --------------------------
	 * Home Win vs Team Above
	 * Home Win vs Team Below
	 * Home Defeat vs Team Above
	 * Home Defeat vs Team Below
	 * Home Draw vs Team Above
	 * Home Draw vs Team Below
	 * Away Win vs Team Above
	 * Away Win vs Team Below
	 * Away Defeat vs Team Above
	 * Away Defeat vs Team Below
	 * Away Draw vs Team Above
	 * Away Draw vs Team Below
	 */
	
	@Test
	public void shouldReturnAnIncrementWhenThereIsAPreviousRowForAHomeWinAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (4, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeWinAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDrawAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDrawAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDefeatAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDefeatAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayWinAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
	
	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayWinAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
	
	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeatAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeatAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_WON_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
}
