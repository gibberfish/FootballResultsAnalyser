package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableTest {
	
	private Table<String,String,String> objectUnderTest;

	@Mock
	private Table<String,String,String> mockPreviousTable;

	@Mock
	private TableRow<String, String, String> mockTableRow1;

	@Mock
	private TableRow<String, String, String> mockTableRow2;

	@Mock
	private TableRow<String, String, String> mockTableRow3;

	@Mock
	private TableRow<String, String, String> mockTableRow4;

	@Mock
	private Team<String> mockTeam1;

	@Mock
	private Team<String> mockTeam2;

	@Mock
	private Team<String> mockTeam3;

	@Mock
	private Team<String> mockTeam4;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		when (mockTableRow1.getTeam()).thenReturn(mockTeam1);		
		when (mockTableRow2.getTeam()).thenReturn(mockTeam2);
		when (mockTableRow3.getTeam()).thenReturn(mockTeam3);
		when (mockTableRow4.getTeam()).thenReturn(mockTeam4);
		
		when (mockTeam1.getTeamId()).thenReturn("123");
		when (mockTeam1.getTeamName()).thenReturn("Portsmouth");
		
		when (mockTeam2.getTeamId()).thenReturn("456");
		when (mockTeam2.getTeamName()).thenReturn("Southampton");
		
		when (mockTeam3.getTeamId()).thenReturn("789");
		when (mockTeam3.getTeamName()).thenReturn("Aston Villa");

		when (mockTeam4.getTeamId()).thenReturn("159");
		when (mockTeam4.getTeamName()).thenReturn("Manchester United");

	}
	
	@Test
	public void shouldInitialiseWithNoRowsIfPreviousTableIsNull () {
		// Given
		
		// When
		objectUnderTest = new Table<String,String,String> (null);
		
		// Then
		assertEquals (0, objectUnderTest.getSortedTable().size());
	}

	@Test
	public void shouldInitialiseWithRowsFromPreviousTable () {
		// Given
		List <TableRow<String, String, String>> list = new ArrayList<TableRow<String, String, String>> ();
		list.add(mockTableRow1);
		list.add(mockTableRow2);
		when (mockPreviousTable.getSortedTable()).thenReturn(list);
		
		// When
		objectUnderTest = new Table<String,String,String> (mockPreviousTable);
		
		// Then
		assertEquals (2, objectUnderTest.getSortedTable().size());
		assertEquals (mockTableRow1, objectUnderTest.getSortedTable().get(0));
		assertEquals (mockTableRow2, objectUnderTest.getSortedTable().get(1));
	}

	@Test
	public void shouldBeAbleToAddRow () {
		// Given
		objectUnderTest = new Table<String,String,String> (null);
		
		// When
		objectUnderTest.addRow(mockTableRow1);
		
		// Then
		assertEquals (1, objectUnderTest.getSortedTable().size());
		assertEquals (mockTableRow1, objectUnderTest.getSortedTable().get(0));
	}
	
	@Test
	public void shouldGetTableRowForTeam () {
		// Given
		List <TableRow<String, String, String>> list = new ArrayList<TableRow<String, String, String>> ();
		list.add(mockTableRow1);
		list.add(mockTableRow2);
		when (mockPreviousTable.getSortedTable()).thenReturn(list);
		objectUnderTest = new Table<String,String,String> (mockPreviousTable);
		
		// When
		TableRow<String,String,String> row = objectUnderTest.getTableRowForTeam("123");

		// Then
		assertEquals (mockTableRow1, row);
	}
}
