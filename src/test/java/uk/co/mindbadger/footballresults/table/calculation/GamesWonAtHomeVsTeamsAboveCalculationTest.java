package uk.co.mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowAfterResult;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesWonAtHomeVsTeamsAboveCalculationTest {
	private GamesWonAtHomeVsTeamsAboveCalculation objectUnderTest;
	
	@Mock
	private TableRowAfterResult<String,String,String> mockPreviousTableRowForCalcTeam;

	@Mock
	private TableRowAfterResult<String,String,String> mockPreviousTableRowForOpposingTeam;

	@Mock
	private Fixture<String> mockFixture;
	
	@Mock
	private Team<String> mockTeamForCalculation;

	@Mock
	private Team<String> mockOpposingTeam;

	@Mock
	private Table<String, String, String> mockParentTable;

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
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAHomeWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAnAwayWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAHomeDraw () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAnAwayDraw () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAHomeDefeat () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAnAwayDefeat () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnAnIncrementWhenThereIsAPreviousRowForAHomeWinAgainstATeamAbove () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(10);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (4, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeWinAgainstATeamBelow () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(3);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDrawAgainstATeamAbove () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(10);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDrawAgainstATeamBelow () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(3);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDefeatAgainstATeamAbove () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(10);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDefeatAgainstATeamBelow () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(3);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayWinAgainstATeamAbove () {
	}
	
	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayWinAgainstATeamBelow () {
	}
	
	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamAbove () {
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamBelow () {
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeatAgainstATeamAbove () {
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeatAgainstATeamBelow () {
	}
}
