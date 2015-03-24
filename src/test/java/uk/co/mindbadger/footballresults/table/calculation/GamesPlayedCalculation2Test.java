package uk.co.mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.TableRowAfterResult;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesPlayedCalculation2Test {
	private GamesPlayedCalculation2 objectUnderTest;
	
	@Mock
	private Calculation mockGamesWonCalculation;
	
	@Mock
	private Calculation mockGamesDrawnCalculation;

	@Mock
	private Calculation mockGamesLostCalculation;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Map<String, Calculation> calculations = new HashMap<String, Calculation> ();
		calculations.put("gamesWon", mockGamesWonCalculation);
		calculations.put("gamesDrawn", mockGamesDrawnCalculation);
		calculations.put("gamesLost", mockGamesLostCalculation);
		
		objectUnderTest = new GamesPlayedCalculation2(calculations);
	}

	@Test
	public void shouldReturnGamesPlayed () {
		// Given
		when (mockGamesWonCalculation.calculate()).thenReturn(12);
		when (mockGamesDrawnCalculation.calculate()).thenReturn(18);
		when (mockGamesLostCalculation.calculate()).thenReturn(10);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (40, result);
	}
}
