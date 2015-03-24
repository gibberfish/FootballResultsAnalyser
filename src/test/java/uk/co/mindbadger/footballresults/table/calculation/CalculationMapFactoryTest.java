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

public class CalculationMapFactoryTest {
	private CalculationMapFactory<String, String, String> objectUnderTest;
	
	private Map<String, String> rawCalculationClassMap = new HashMap<String, String> ();
	private Map<String, String> derivedCalculationClassMap = new HashMap<String, String> ();
	
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
		
		objectUnderTest = new CalculationMapFactory<String,String,String>();
		objectUnderTest.setDerivedCalculationClassMap(derivedCalculationClassMap);
		objectUnderTest.setRawCalculationClassMap(rawCalculationClassMap);
	}

	@Test
	public void shouldCreateACalculationMapFromValidConfiguration () {
		// Given
		rawCalculationClassMap.put("goalsScored", "uk.co.mindbadger.footballresults.table.calculation.GoalsScoredCalculation");
		rawCalculationClassMap.put("goalsConceded", "uk.co.mindbadger.footballresults.table.calculation.GoalsConcededCalculation");
		
		derivedCalculationClassMap.put("goalDifference", "uk.co.mindbadger.footballresults.table.calculation.GoalDifferenceCalculation2");
		
		// When
		Map<String, Calculation> calculations = objectUnderTest.createCalculations (mockTeam1, mockPreviousTableRow, mockFixture);
		
		// Then
		Calculation goalsScored = calculations.get("goalsScored");
		Calculation goalsConceded = calculations.get("goalsConceded");
		Calculation goalDifference = calculations.get("goalDifference");
		
		assertTrue(goalsScored instanceof GoalsScoredCalculation);
		assertTrue(goalsConceded instanceof GoalsConcededCalculation);
		assertTrue(goalDifference instanceof GoalDifferenceCalculation2);
	}

}
