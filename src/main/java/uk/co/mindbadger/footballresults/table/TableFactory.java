package uk.co.mindbadger.footballresults.table;

import java.util.Set;

import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class TableFactory {
	public Table<String,String,String> createInitialTable (SeasonDivision<String, String> seasonDivision, Set<SeasonDivisionTeam<String, String, String>> seasonDivisionTeams) {
		return new InitialTable<String,String,String>(seasonDivision, seasonDivisionTeams);
	}
	
	public Table<String,String,String> createTableFromPreviousTable(Table<String,String,String> previousTable) {
		return new Table<String,String,String> (previousTable);
	}
}
