package mindbadger.football.table;

import static mindbadger.football.table.AttributeIds.*;

import mindbadger.football.domain.Fixture;
import mindbadger.football.domain.Team;

public abstract class TableRow implements Comparable<TableRow> {
	protected Team team;
	protected Table parentTable;
	
	public TableRow (Team team) {
		this.team = team;
	}
	
	public Team getTeam () {
		return team;
	}
	
	public abstract int getAttribute(String attributeId);
	public abstract TableRow getPreviousTableRow();
	public abstract Fixture getFixture();
	public abstract TeamFixtureContext getFixtureTeamContext();
	public abstract TeamFixtureContext getOppositionTeamContext();
	
	@Override
	public int compareTo (TableRow otherRow) {
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
