package uk.co.mindbadger.footballresults.season;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;
import uk.co.mindbadger.utils.FixtureDateFormatter;

public class DivisionCache {
	Logger logger = Logger.getLogger(DivisionCache.class);
	
	private Map<Calendar, List<Fixture<String>>> fixtures = new TreeMap<Calendar, List<Fixture<String>>> ();
	private Map<Calendar, Table<String,String,String>> tables = new TreeMap<Calendar, Table<String,String,String>> ();
	private Map<Calendar, Map<Team<String>, TeamFixtureContext>> teamFixtureContexts = new HashMap<Calendar, Map<Team<String>, TeamFixtureContext>> ();
	
	public void addFixtureOnDate (Calendar date, Fixture<String> fixture) {
		List<Fixture<String>> fixturesForDate = fixtures.get(date);
		if (fixturesForDate == null) {
			logger.info("Creating new fixture list in division cache for date " + FixtureDateFormatter.format(date));
			fixturesForDate = new ArrayList<Fixture<String>> ();
			fixtures.put(date, fixturesForDate);
		}
		fixturesForDate.add(fixture);
	}

	public void addTableOnDate (Calendar date, Table<String,String,String> table) {
		tables.put(date, table);
	}

	public void addTeamFixtureContextOnDate (Calendar date, Team<String> team, TeamFixtureContext teamFixtureContext) {
		//TODO Implement this method
		throw new RuntimeException ("Not implemented yet");
	}
	
	public Map<Calendar, List<Fixture<String>>> getFixturesForDivision() {
		return fixtures;
	}

	public Map<Calendar, Table<String, String, String>> getTablesForDivision() {
		return tables;
	}

	public List<Calendar> getFixtureDates() {
		Set<Calendar> dateSet = fixtures.keySet();
		List<Calendar> dateList = new ArrayList<Calendar> (dateSet);
		Collections.sort(dateList);
		return dateList;
	}
}
