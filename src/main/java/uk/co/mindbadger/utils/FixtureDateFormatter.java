package uk.co.mindbadger.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//TODO #90 Needs unit test
public class FixtureDateFormatter {
	public static String format (Calendar date) {
		String fixtureDate = "NO DATE";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fixtureDate = sdf.format(date.getTime());
		}
		return fixtureDate;
	}
	
	public static boolean isSameDate (Calendar date1, Calendar date2) {
		String date1String = format(date1);
		String date2String = format(date2);
		return date1String.equals(date2String);
	}
}
