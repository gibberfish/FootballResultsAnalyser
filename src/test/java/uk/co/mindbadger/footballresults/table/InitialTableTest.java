package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class InitialTableTest {
	private InitialTable<String,String,String> objectUnderTest;
	
	private Set<SeasonDivisionTeam<String,String,String>> seasonDivisionTeams;
	
	@Mock
	private SeasonDivision<String,String> mockSeasonDivision;
	@Mock
	private Season<String> mockSeason;
	@Mock
	private Division<String> mockDivision;
	@Mock
	private SeasonDivisionTeam<String,String,String> seasonDivisionTeam1;
	@Mock
	private SeasonDivisionTeam<String,String,String> seasonDivisionTeam2;
	@Mock
	private SeasonDivisionTeam<String,String,String> seasonDivisionTeam3;
	@Mock
	private Team<String> team1;
	@Mock
	private Team<String> team2;
	@Mock
	private Team<String> team3;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		seasonDivisionTeams = new HashSet <SeasonDivisionTeam<String,String,String>> ();
		seasonDivisionTeams.add(seasonDivisionTeam1);
		seasonDivisionTeams.add(seasonDivisionTeam2);
		seasonDivisionTeams.add(seasonDivisionTeam3);

		when (mockSeasonDivision.getSeason()).thenReturn(mockSeason);
		when (mockSeasonDivision.getDivision()).thenReturn(mockDivision);
		//when (mockSeasonDivision.getTeamsInSeasonDivision()).thenReturn(seasonDivisionTeams);
		when (seasonDivisionTeam1.getTeam()).thenReturn(team1);
		when (seasonDivisionTeam2.getTeam()).thenReturn(team2);
		when (seasonDivisionTeam3.getTeam()).thenReturn(team3);
		
		when (team1.getTeamName()).thenReturn("Portsmouth");
		when (team2.getTeamName()).thenReturn("Aston Villa");
		when (team3.getTeamName()).thenReturn("West Brom");
		
		when (team1.getTeamId()).thenReturn("123");
		when (team2.getTeamId()).thenReturn("456");
		when (team3.getTeamId()).thenReturn("789");
	}
	
	@Test
	public void shouldCreateInitialTable () {
		// Given
		
		// When
		objectUnderTest = new InitialTable<String,String,String> (mockSeasonDivision, seasonDivisionTeams);
		
		// Then
		List<TableRow<String,String,String>> tableRows = objectUnderTest.getSortedTable();
		
		TableRow<String,String,String> row1 = tableRows.get(0);
		assertEquals ("Aston Villa", row1.getTeam().getTeamName());
		assertTrue (row1 instanceof InitialTableRow);

		TableRow<String,String,String> row2 = tableRows.get(1);
		assertEquals ("Portsmouth", row2.getTeam().getTeamName());
		assertTrue (row2 instanceof InitialTableRow);
		
		TableRow<String,String,String> row3 = tableRows.get(2);
		assertEquals ("West Brom", row3.getTeam().getTeamName());
		assertTrue (row3 instanceof InitialTableRow);
	}
}
