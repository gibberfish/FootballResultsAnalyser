package mindbadger.football.caches;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import mindbadger.football.table.Table;
import mindbadger.football.table.TeamFixtureContext;
import mindbadger.utils.FixtureDateFormatter;

import org.apache.log4j.Logger;

import mindbadger.football.domain.Fixture;
import mindbadger.football.domain.Team;

public class DivisionCache {
	Logger logger = Logger.getLogger(DivisionCache.class);
	
	private Map<Calendar, List<Fixture>> fixtures = new TreeMap<Calendar, List<Fixture>> ();
	private Map<Calendar, Table> tables = new TreeMap<Calendar, Table> ();
	private Map<Calendar, Map<Team, TeamFixtureContext>> teamFixtureContexts = new HashMap<Calendar, Map<Team, TeamFixtureContext>> ();

	public void addFixtureOnDate (Calendar date, Fixture fixture) {
		List<Fixture> fixturesForDate = fixtures.get(date);
		if (fixturesForDate == null) {
			logger.info("Creating new fixture list in division cache for date " + FixtureDateFormatter.format(date));
			fixturesForDate = new ArrayList<Fixture> ();
			fixtures.put(date, fixturesForDate);
		}
		fixturesForDate.add(fixture);
	}

	public void addTableOnDate (Calendar date, Table table) {
		tables.put(date, table);
	}

	public void addTeamFixtureContextOnDate (Calendar date, Team team, TeamFixtureContext teamFixtureContext) {
		Map<Team, TeamFixtureContext> teams = teamFixtureContexts.get(date);
		if (teams == null) {
			teams = new HashMap<Team, TeamFixtureContext> ();
			teamFixtureContexts.put(date, teams);
		}
		
		teams.put(team, teamFixtureContext);
	}
	
	public Map<Calendar, List<Fixture>> getFixturesForDivision() {
		return fixtures;
	}

	public Map<Calendar, Table> getTablesForDivision() {
		return tables;
	}

	public Map<Calendar, Map<Team, TeamFixtureContext>> getTeamFixtureContexts() {
		return teamFixtureContexts;
	}

	public List<Calendar> getFixtureDates() {
		Set<Calendar> dateSet = fixtures.keySet();
		List<Calendar> dateList = new ArrayList<Calendar> (dateSet);
		Collections.sort(dateList);
		return dateList;
	}
}
