package mindbadger.footballresults.table.calculation;

import static mindbadger.footballresults.table.AttributeIds.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import mindbadger.footballresults.season.TeamFixtureContext;
import mindbadger.footballresults.table.Table;
import mindbadger.footballresults.table.TableRow;
import mindbadger.footballresults.table.TableRowAfterResult;
import mindbadger.footballresults.table.calculation.GamesDrawnAwayVsTeamsAboveCalculation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.footballresultsanalyser.domain.Fixture;
import mindbadger.footballresultsanalyser.domain.Team;

public class GamesDrawnAwayVsTeamsAboveCalculationTest {
	private GamesDrawnAwayVsTeamsAboveCalculation objectUnderTest;
	
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
	
	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeWinAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeWinAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
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
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
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
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
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
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
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
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
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
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
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
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
	
	@Test
	public void shouldReturnAnIncrementWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (4, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
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
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
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
		when (mockPreviousTableRow.getAttribute(GAMES_DRAWN_AWAY_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GamesDrawnAwayVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
}
