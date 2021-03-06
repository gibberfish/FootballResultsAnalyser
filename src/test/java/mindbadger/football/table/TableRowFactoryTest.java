package mindbadger.football.table;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import mindbadger.football.table.TableRow;
import mindbadger.football.table.TableRowAfterResult;
import mindbadger.football.table.TableRowFactory;
import mindbadger.football.table.calculation.Calculation;
import mindbadger.football.table.calculation.CalculationMapFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.football.domain.Fixture;
import mindbadger.football.domain.Team;

public class TableRowFactoryTest {	
	private Map<String,Calculation> calculations = new HashMap<String,Calculation> ();
	
	private TableRowFactory objectUnderTest;
	
	@Mock
	private TableRowAfterResult mockPreviousTableRow;
	
	@Mock
	private Fixture mockFixture;
	
	@Mock
	private Team mockTeam1;

	@Mock
	private TeamFixtureContext mockFixtureTeamContext;
	
	@Mock
	private TeamFixtureContext mockOppositionTeamContext;
	
	@Mock
	private CalculationMapFactory<String, String, String> mockCalculationMapFactory;
	
	@Mock
	private Calculation mockCalculation;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		when(mockCalculationMapFactory.createCalculations(mockTeam1, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow)).thenReturn(calculations);
		calculations.put("test", mockCalculation);
	}
	
	@Test
	public void shouldCreateATableRow () {
		// Given
		objectUnderTest = new TableRowFactory(mockCalculationMapFactory);
		
		// When
		TableRow tableRow = objectUnderTest.createTableRowFromFixture(mockTeam1, mockPreviousTableRow, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext);
		tableRow.getAttribute("test");
		
		// Then
		verify(mockCalculation).calculate();
	}
}
