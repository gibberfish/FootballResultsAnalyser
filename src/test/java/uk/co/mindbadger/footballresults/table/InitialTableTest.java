package uk.co.mindbadger.footballresults.table;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.*;
import org.mockito.*;

import uk.co.mindbadger.footballresultsanalyser.domain.Division;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class InitialTableTest {
	private InitialTable objectUnderTest;
	
	private Set<SeasonDivisionTeam> seasonDivisionTeams;
	
	@Mock
	private SeasonDivision mockSeasonDivision;
	@Mock
	private Season mockSeason;
	@Mock
	private Division mockDivision;
	@Mock
	private SeasonDivisionTeam seasonDivisionTeam1;
	@Mock
	private SeasonDivisionTeam seasonDivisionTeam2;
	@Mock
	private SeasonDivisionTeam seasonDivisionTeam3;
	@Mock
	private Team team1;
	@Mock
	private Team team2;
	@Mock
	private Team team3;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		seasonDivisionTeams = new HashSet <SeasonDivisionTeam> ();
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
		
		when (team1.getTeamId()).thenReturn(123);
		when (team2.getTeamId()).thenReturn(456);
		when (team3.getTeamId()).thenReturn(789);
	}
	
	@Test
	public void shouldCreateInitialTable () {
		// Given
		
		// When
		objectUnderTest = new InitialTable (mockSeasonDivision, seasonDivisionTeams);
		
		// Then
		Map<Integer, TableRow> tableRows = objectUnderTest.getTableRows ();
		assertEquals (3, tableRows.size());
		
		TableRow row1 = tableRows.get(123);
		assertEquals ("Portsmouth", row1.getTeamName());
		assertTrue (row1 instanceof InitialTableRow);
		
		TableRow row2 = tableRows.get(456);
		assertEquals ("Aston Villa", row2.getTeamName());
		assertTrue (row2 instanceof InitialTableRow);

		TableRow row3 = tableRows.get(789);
		assertEquals ("West Brom", row3.getTeamName());
		assertTrue (row3 instanceof InitialTableRow);
	}
}
