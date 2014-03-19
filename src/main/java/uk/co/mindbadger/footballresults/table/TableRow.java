package uk.co.mindbadger.footballresults.table;

import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRow {
	private Team team;
	
	public TableRow (Team team) {
		this.team = team;
	}
	
	public TableRow (Team team, TableRow previousTableRow, Fixture fixture) {
		
	}

	public Integer getTeamId () {
		return team.getTeamId();
	}

	public String getTeamName () {
		return team.getTeamName();
	}

}
