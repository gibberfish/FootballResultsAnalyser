package uk.co.mindbadger.footballresults.table;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.calculation.Calculation;
import uk.co.mindbadger.footballresults.table.calculation.CalculationMapFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowFactoryTest {	
	private Map<String,Calculation> calculations = new HashMap<String,Calculation> ();
	
	private TableRowFactory<String,String,String> objectUnderTest;
	
	@Mock
	private TableRowAfterResult<String,String,String> mockPreviousTableRow;
	
	@Mock
	private Fixture<String> mockFixture;
	
	@Mock
	private Team<String> mockTeam1;

	@Mock
	private Table<String,String,String> mockParentTable;
	
	@Mock
	private CalculationMapFactory<String, String, String> mockCalculationMapFactory;
	
	@Mock
	private Calculation mockCalculation;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		when(mockCalculationMapFactory.createCalculations(mockTeam1, mockPreviousTableRow, mockFixture)).thenReturn(calculations);
		calculations.put("test", mockCalculation);
	}
	
	@Test
	public void shouldCreateATableRow () {
		// Given
		objectUnderTest = new TableRowFactory<>(mockCalculationMapFactory);
		
		// When
		TableRow<String, String, String> tableRow = objectUnderTest.createTableRowFromFixture(mockTeam1, mockParentTable, mockPreviousTableRow, mockFixture);
		tableRow.getAttribute("test");
		
		// Then
		verify(mockCalculation).calculate();
	}
}
