package mindbadger.football.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mindbadger.football.table.InitialTable;
import mindbadger.football.table.InitialTableRow;
import mindbadger.football.table.TableRow;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.football.domain.Division;
import mindbadger.football.domain.Season;
import mindbadger.football.domain.SeasonDivision;
import mindbadger.football.domain.SeasonDivisionTeam;
import mindbadger.football.domain.Team;

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
		
		when (team1.getTeamId()).thenReturn("123");
		when (team2.getTeamId()).thenReturn("456");
		when (team3.getTeamId()).thenReturn("789");
	}
	
	@Test
	public void shouldCreateInitialTable () {
		// Given
		
		// When
		objectUnderTest = new InitialTable (mockSeasonDivision, seasonDivisionTeams);
		
		// Then
		List<TableRow> tableRows = objectUnderTest.getSortedTable();
		
		TableRow row1 = tableRows.get(0);
		assertEquals ("Aston Villa", row1.getTeam().getTeamName());
		assertTrue (row1 instanceof InitialTableRow);

		TableRow row2 = tableRows.get(1);
		assertEquals ("Portsmouth", row2.getTeam().getTeamName());
		assertTrue (row2 instanceof InitialTableRow);
		
		TableRow row3 = tableRows.get(2);
		assertEquals ("West Brom", row3.getTeam().getTeamName());
		assertTrue (row3 instanceof InitialTableRow);
	}
}
