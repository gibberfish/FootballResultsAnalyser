package uk.co.mindbadger.footballresults.season;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;

public class DivisionCache {
	private Map<Calendar, List<Fixture<String>>> fixtures = new HashMap<Calendar, List<Fixture<String>>> ();
	private Map<Calendar, Table<String,String,String>> tables = new HashMap<Calendar, Table<String,String,String>> ();

	public void addFixtureOnDate (Calendar date, Fixture<String> fixture) {
		List<Fixture<String>> fixturesForDate = fixtures.get(date);
		if (fixturesForDate == null) {
			fixturesForDate = new ArrayList<Fixture<String>> ();
			fixtures.put(date, fixturesForDate);
		}
		fixturesForDate.add(fixture);
	}

	public void addTableOnDate (Calendar date, Table<String,String,String> table) {
		tables.put(date, table);
	}

	public Map<Calendar, List<Fixture<String>>> getFixturesForDivision() {
		return fixtures;
	}

	public Map<Calendar, Table<String, String, String>> getTablesForDivision() {
		return tables;
	}
}
