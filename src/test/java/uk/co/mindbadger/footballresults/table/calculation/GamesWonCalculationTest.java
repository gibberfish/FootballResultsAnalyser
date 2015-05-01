package uk.co.mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowAfterResult;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesWonCalculationTest {
	private GamesWonCalculation objectUnderTest;
	
	@Mock
	private TableRowAfterResult<String,String,String> mockPreviousTableRow;
	
	@Mock
	private Fixture<String> mockFixture;
	
	@Mock
	private Team<String> mockTeam1;

	@Mock
	private Team<String> mockTeam2;

	@Mock
	private Calculation mockGamesWonAtHomeCalculation;

	@Mock
	private Calculation mockGamesWonAwayCalculation;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		Map<String, Calculation> calculations = new HashMap<String, Calculation> ();
		calculations.put("gamesWonAtHome", mockGamesWonAtHomeCalculation);
		calculations.put("gamesWonAway", mockGamesWonAwayCalculation);
		
		objectUnderTest = new GamesWonCalculation(calculations);

	}

	@Test
	public void shouldReturnGamesWon () {
		// Given
		when(mockGamesWonAtHomeCalculation.calculate()).thenReturn(4);
		when(mockGamesWonAwayCalculation.calculate()).thenReturn(2);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (6, result);
	}
}
