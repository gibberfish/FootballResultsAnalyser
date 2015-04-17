package uk.co.mindbadger.footballresults.season;

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
		throw new IllegalArgumentException ("Not Implemented Yet");
	}

	public void addTableOnDate (Calendar date, Table<String,String,String> table) {
		throw new IllegalArgumentException ("Not Implemented Yet");
	}
	
}
