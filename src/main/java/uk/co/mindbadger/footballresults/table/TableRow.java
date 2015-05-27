package uk.co.mindbadger.footballresults.table;

import static uk.co.mindbadger.footballresults.table.AttributeIds.*;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public abstract class TableRow<K,L,M> implements Comparable<TableRow<K,L,M>> {
	protected Team<K> team;
	//TODO Remove parent table from the table row - it's never used
	protected Table<K,L,M> parentTable;
	
	public TableRow (Team<K> team) {
		this.team = team;
	}
	
	public Team<K> getTeam () {
		return team;
	}
	
	public abstract int getAttribute(String attributeId);
	
	@Override
	public int compareTo (TableRow<K,L,M> otherRow) {
		if (otherRow.getAttribute(POINTS) != getAttribute(POINTS)) {
			return (otherRow.getAttribute(POINTS) < getAttribute(POINTS)) ? -1 : 1;
		} else if (otherRow.getAttribute(GOAL_DIFFERENCE) != getAttribute(GOAL_DIFFERENCE)) {
			return (otherRow.getAttribute(GOAL_DIFFERENCE) < getAttribute(GOAL_DIFFERENCE)) ? -1 : 1;
		} else if (otherRow.getAttribute(GOALS_SCORED) != getAttribute(GOALS_SCORED)) {
			return (otherRow.getAttribute(GOALS_SCORED) < getAttribute(GOALS_SCORED)) ? -1 : 1;
		} else {
			return team.getTeamName().compareTo(otherRow.getTeam().getTeamName());
		}
	}
	
	@Override
	public String toString () {
		return "TableRow[team:" + team.getTeamName() + ",points:" + getAttribute(POINTS) + ",scored:" + getAttribute(GOALS_SCORED) + ",conceded:" + getAttribute(GOALS_CONCEDED) + "]";
	}
}
