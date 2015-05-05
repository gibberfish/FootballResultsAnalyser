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

public class GamesWonAtHomeVsTeamsBelowCalculationTest {
	private GamesWonAtHomeVsTeamsBelowCalculation objectUnderTest;
	
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
	
	@Test
	public void shouldReturnOneWhenThereIsNoPreviousRowForAHomeWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (1, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAnAwayWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, null, mockFixture);
		
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
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, null, mockFixture);
		
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
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, null, mockFixture);
		
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
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, null, mockFixture);
		
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
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeWinAgainstATeamAbove () {
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
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnAnIncrementWhenThereIsAPreviousRowForAHomeWinAgainstATeamBelow () {
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
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (4, result);
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
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
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
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
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
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
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
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayWinAgainstATeamAbove () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(10);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
	
	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayWinAgainstATeamBelow () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(3);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
	
	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamAbove () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(10);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamBelow () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(3);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeatAgainstATeamAbove () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(10);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeatAgainstATeamBelow () {
		// Given
		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");

		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		
		when (mockPreviousTableRowForCalcTeam.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		when (mockPreviousTableRowForCalcTeam.getParentTable()).thenReturn(mockParentTable);
		
		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRowForCalcTeam);
		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
		
		when (mockPreviousTableRowForCalcTeam.getLeaguePosition()).thenReturn(3);
		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsBelowCalculation(mockTeamForCalculation, mockPreviousTableRowForCalcTeam, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
}
