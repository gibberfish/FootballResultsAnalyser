package uk.co.mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowAfterResult;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesWonAtHomeCalculationTest {
	private GamesWonAtHomeCalculation objectUnderTest;
	
	@Mock
	private TableRowAfterResult<String,String,String> mockPreviousTableRow;
	
	@Mock
	private Fixture<String> mockFixture;
	
	@Mock
	private Team<String> mockTeam1;

	@Mock
	private Team<String> mockTeam2;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnOneWhenThereIsNoPreviousRowForAHomeWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		objectUnderTest = new GamesWonAtHomeCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (1, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAnAwayWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForADraw () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAHomeDefeat () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnZeroWhenThereIsNoPreviousRowForAnAwayDefeat () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		objectUnderTest = new GamesWonAtHomeCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (0, result);
	}

	@Test
	public void shouldReturnAnIncrementWhenThereIsAPreviousRowForAHomeWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (4, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayWin () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForADraw () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDefeat () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam1);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam2);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeat () {
		// Given
		when (mockFixture.getHomeTeam()).thenReturn(mockTeam2);
		when (mockFixture.getAwayTeam()).thenReturn(mockTeam1);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		objectUnderTest = new GamesWonAtHomeCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
}
