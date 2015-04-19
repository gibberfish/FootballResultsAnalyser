package uk.co.mindbadger.footballresults.season;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;

public class DivisionCacheTest {
	
	private DivisionCache objectUnderTest;

	private Calendar date1;
	private Calendar date2;
	private Calendar date3;
	
	@Mock
	private Fixture<String> mockFixture1;

	@Mock
	private Fixture<String> mockFixture2;

	@Mock
	private Fixture<String> mockFixture3;

	@Mock
	Table<String,String,String> mockTable1;

	@Mock
	Table<String,String,String> mockTable2;

	@Mock
	Table<String,String,String> mockTable3;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectUnderTest = new DivisionCache();
		
		date1 = Calendar.getInstance();
		date1.set(Calendar.MONTH, 5);
		
		date2 = Calendar.getInstance();
		date2.set(Calendar.MONTH, 6);
		
		date3 = Calendar.getInstance();
		date3.set(Calendar.MONTH, 7);
	}
	
	@Test
	public void shouldInitialiseWithNoRows () {
		// Given
		
		// When
		Map<Calendar, List<Fixture<String>>> fixtures = objectUnderTest.getFixturesForDivision();
		Map<Calendar, Table<String,String,String>> tables = objectUnderTest.getTablesForDivision();
		
		// Then
		assertEquals (0, fixtures.size());
		assertEquals (0, tables.size());
	}
	
	@Test
	public void shouldAddFixtureRowsToCache () {
		// Given
		
		// When
		objectUnderTest.addFixtureOnDate(date1, mockFixture1);
		objectUnderTest.addFixtureOnDate(date1, mockFixture2);
		objectUnderTest.addFixtureOnDate(date2, mockFixture3);
		
		// Then
		Map<Calendar, List<Fixture<String>>> fixtures = objectUnderTest.getFixturesForDivision();
		
		assertEquals (2, fixtures.size());
		
		List<Fixture<String>> fixturesForDate1 = fixtures.get(date1);
		assertEquals (2, fixturesForDate1.size());
		assertEquals (mockFixture1, fixturesForDate1.get(0));
		assertEquals (mockFixture2, fixturesForDate1.get(1));
		
		List<Fixture<String>> fixturesForDate2 = fixtures.get(date2);
		assertEquals (1, fixturesForDate2.size());
		assertEquals (mockFixture3, fixturesForDate2.get(0));
	}

	@Test
	public void shouldAddTableRowToCache () {
		// Given
		
		// When
		objectUnderTest.addTableOnDate(date1, mockTable1);
		objectUnderTest.addTableOnDate(date2, mockTable2);
		objectUnderTest.addTableOnDate(date3, mockTable3);
		
		// Then
		Map<Calendar, Table<String,String,String>> tables = objectUnderTest.getTablesForDivision();
		
		assertEquals (3, tables.size());
		assertEquals (mockTable1, tables.get(date1));
		assertEquals (mockTable2, tables.get(date2));
		assertEquals (mockTable3, tables.get(date3));
	}

}
