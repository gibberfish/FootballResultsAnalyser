package uk.co.mindbadger.footballresults.season;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.utils.FixtureDateFormatter;

public class DivisionCache {
	Logger logger = Logger.getLogger(DivisionCache.class);
	
	private Map<Calendar, List<Fixture<String>>> fixtures = new TreeMap<Calendar, List<Fixture<String>>> ();
	private Map<Calendar, Table<String,String,String>> tables = new TreeMap<Calendar, Table<String,String,String>> ();

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

	public Map<Calendar, List<Fixture<String>>> getFixturesForDivision() {
		return fixtures;
	}

	public Map<Calendar, Table<String, String, String>> getTablesForDivision() {
		return tables;
	}
}
