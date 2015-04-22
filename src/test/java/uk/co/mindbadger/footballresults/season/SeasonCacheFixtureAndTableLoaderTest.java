package uk.co.mindbadger.footballresults.season;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableFactory;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class SeasonCacheFixtureAndTableLoaderTest {
	
	private SeasonCacheFixtureAndTableLoader objectUnderTest;
	
	@Mock
	private TableFactory mockTableFactory;
	
	@Mock
	private TableRowFactory<String,String,String> mockTableRowFactory;
	
	@Mock
	private Fixture<String> mockFixture1;

	@Mock
	private DivisionCache mockDivisionCache1;

	@Mock
	private Table<String, String, String> mockTableDate1;

	@Mock
	private Table<String, String, String> mockTableDate2;

	@Mock
	private Team<String> mockHomeTeam;

	@Mock
	private Team<String> mockAwayTeam;

	@Mock
	private TableRow<String, String, String> mockTable1;

	@Mock
	private TableRow<String, String, String> mockHomeTableRow;

	@Mock
	private TableRow<String, String, String> mockAwayTableRow;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectUnderTest = new SeasonCacheFixtureAndTableLoader();
		objectUnderTest.setTableFactory(mockTableFactory);
		objectUnderTest.setTableRowFactory(mockTableRowFactory);
	}
	
	@Test
	public void shouldLoadFixtureIntoTableWhereTheDateHasNotChanged () {
		// Given
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);
		when(mockFixture1.getFixtureDate()).thenReturn(fixtureDate1);

		when(mockFixture1.getHomeTeam()).thenReturn(mockHomeTeam);
		when(mockFixture1.getAwayTeam()).thenReturn(mockAwayTeam);
		
		when(mockHomeTeam.getTeamId()).thenReturn("HomeId");
		when(mockAwayTeam.getTeamId()).thenReturn("AwayId");
		
		when(mockTableDate1.getTableRowForTeam("HomeId")).thenReturn(mockHomeTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockHomeTableRow);

		when(mockTableDate1.getTableRowForTeam("AwayId")).thenReturn(mockAwayTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockAwayTableRow);
		
		// When
		Table<String,String,String> newTable = objectUnderTest.loadFixtureAndTable(mockFixture1, fixtureDate1, mockDivisionCache1, mockTableDate1);
		
		// Then
		verify(mockDivisionCache1,never()).addTableOnDate(fixtureDate1, mockTableDate1);
		verify(mockTableFactory,never()).createTableFromPreviousTable(mockTableDate1);
		
		verify(mockDivisionCache1,times(1)).addFixtureOnDate(fixtureDate1, mockFixture1);
		
		verify(mockTableDate1,times(1)).getTableRowForTeam("HomeId");
		verify(mockTableRowFactory,times(1)).createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockHomeTableRow, mockFixture1);
		
		verify(mockTableDate1,times(1)).getTableRowForTeam("AwayId");
		verify(mockTableRowFactory,times(1)).createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockAwayTableRow, mockFixture1);
		
		assertEquals (mockTableDate1, newTable);
	}

	@Test
	public void shouldLoadFixtureIntoTableWhereTheDateHasChanged () {
		// Given
		Calendar fixtureDate1 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 1);

		Calendar fixtureDate2 = Calendar.getInstance();
		fixtureDate1.set(Calendar.DAY_OF_MONTH, 2);
		when(mockFixture1.getFixtureDate()).thenReturn(fixtureDate2);

		when(mockTableFactory.createTableFromPreviousTable(mockTableDate1)).thenReturn(mockTableDate2);

		when(mockFixture1.getHomeTeam()).thenReturn(mockHomeTeam);
		when(mockFixture1.getAwayTeam()).thenReturn(mockAwayTeam);
		
		when(mockHomeTeam.getTeamId()).thenReturn("HomeId");
		when(mockAwayTeam.getTeamId()).thenReturn("AwayId");
		
		when(mockTableDate1.getTableRowForTeam("HomeId")).thenReturn(mockHomeTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockHomeTableRow);

		when(mockTableDate1.getTableRowForTeam("AwayId")).thenReturn(mockAwayTableRow);
		when(mockTableRowFactory.createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockTable1, mockFixture1)).thenReturn(mockAwayTableRow);

		// When
		Table<String,String,String> newTable = objectUnderTest.loadFixtureAndTable(mockFixture1, fixtureDate1, mockDivisionCache1, mockTableDate1);
		
		// Then
		verify(mockDivisionCache1,times(1)).addTableOnDate(fixtureDate1, mockTableDate1);
		verify(mockTableFactory,times(1)).createTableFromPreviousTable(mockTableDate1);
		
		verify(mockDivisionCache1,times(1)).addFixtureOnDate(fixtureDate1, mockFixture1);

		verify(mockTableDate1,times(1)).getTableRowForTeam("HomeId");
		verify(mockTableRowFactory,times(1)).createTableRowFromFixture(mockHomeTeam, mockTableDate1, mockHomeTableRow, mockFixture1);
		
		verify(mockTableDate1,times(1)).getTableRowForTeam("AwayId");
		verify(mockTableRowFactory,times(1)).createTableRowFromFixture(mockAwayTeam, mockTableDate1, mockAwayTableRow, mockFixture1);

		assertEquals (mockTableDate2, newTable);
	}
}
