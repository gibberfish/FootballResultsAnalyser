package uk.co.mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.TableRowAfterResult;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesPlayedCalculationTest {	
	
	private GamesPlayedCalculation objectUnderTest;
	
	@Mock
	private TableRowAfterResult<String,String,String> mockPreviousTableRow;
	
	@Mock
	private Fixture<String> mockFixture;
	
	@Mock
	private Team<String> mockTeam1;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturnOneWhenThereIsNoPreviousRow () {
		// Given
		objectUnderTest = new GamesPlayedCalculation(mockTeam1, null, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (1, result);
	}
	
	@Test
	public void shouldReturnAnIncrementWhenThereIsAPreviousRow () {
		// Given
		when (mockPreviousTableRow.getGamesPlayed()).thenReturn(3);
		objectUnderTest = new GamesPlayedCalculation(mockTeam1, mockPreviousTableRow, mockFixture);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (4, result);
	}	

}
