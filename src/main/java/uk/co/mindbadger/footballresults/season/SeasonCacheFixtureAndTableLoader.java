package uk.co.mindbadger.footballresults.season;

import java.util.Calendar;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableFactory;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class SeasonCacheFixtureAndTableLoader {
	Logger logger = Logger.getLogger(SeasonCacheFixtureAndTableLoader.class);
	
	private TableFactory tableFactory;
	private TableRowFactory<String,String,String> tableRowFactory;
	
	public void loadFixture (Fixture<String> fixture, Calendar currentDate, DivisionCache divisionCache) {
		logger.info("Load Fixture for " + fixture.toString());
		
		divisionCache.addFixtureOnDate(currentDate, fixture);
	}
	
	public Table<String,String,String> loadFixtureIntoTable (Fixture<String> fixture, Calendar currentDate, DivisionCache divisionCache, Table<String,String,String> tableForDate) {
		Table<String,String,String> currentTable = tableForDate;

		if (fixture.getHomeGoals() != null && fixture.getAwayGoals() != null) {
			logger.info("Load Fixture and Table Cache for " + fixture.toString());
			
			if (fixture.getFixtureDate() != currentDate) {
				logger.debug("... The fixture date has changed from " + currentDate + " to " + fixture.getFixtureDate());
				if (tableForDate != null) divisionCache.addTableOnDate(currentDate, tableForDate);
				currentTable = tableFactory.createTableFromPreviousTable(tableForDate);
			}
	
			currentTable.addRow(createTableRow(fixture.getHomeTeam(), tableForDate, fixture));
			currentTable.addRow(createTableRow(fixture.getAwayTeam(), tableForDate, fixture));
		} else {
			logger.info("Not loading Table Cache - unplayed " + fixture.toString());
		}
		
		return currentTable;
	}
	
	private TableRow<String, String, String> createTableRow (Team<String> team, Table<String,String,String> table, Fixture<String> fixture) {
		TableRow<String, String, String> previousTableRow = table.getTableRowForTeam(team.getTeamId());
		return  tableRowFactory.createTableRowFromFixture(team, table, previousTableRow , fixture);
	}

	public TableFactory getTableFactory() {
		return tableFactory;
	}

	public void setTableFactory(TableFactory tableFactory) {
		this.tableFactory = tableFactory;
	}
	
	public TableRowFactory<String,String,String> getTableRowFactory() {
		return tableRowFactory;
	}

	public void setTableRowFactory(TableRowFactory<String,String,String> tableRowFactory) {
		this.tableRowFactory = tableRowFactory;
	}
}
