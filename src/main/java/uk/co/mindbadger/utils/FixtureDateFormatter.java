package uk.co.mindbadger.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//TODO Needs unit test
public class FixtureDateFormatter {
	public static String format (Calendar date) {
		String fixtureDate = "NO DATE";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fixtureDate = sdf.format(date.getTime());
		}
		return fixtureDate;
	}
}
