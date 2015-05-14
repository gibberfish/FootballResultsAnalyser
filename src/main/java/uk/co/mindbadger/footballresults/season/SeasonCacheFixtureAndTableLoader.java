package uk.co.mindbadger.footballresults.season;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableFactory;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;
import uk.co.mindbadger.utils.FixtureDateFormatter;

public class SeasonCacheFixtureAndTableLoader {
	Logger logger = Logger.getLogger(SeasonCacheFixtureAndTableLoader.class);
	
	private TableFactory tableFactory;
	private TableRowFactory<String,String,String> tableRowFactory;
	private TeamFixtureContextFactory teamFixtureContextFactory;
	
	public void loadFixture (Fixture<String> fixture, Calendar currentDate, DivisionCache divisionCache) {
		logger.info("Load Fixture for " + fixture.toString());
		
		divisionCache.addFixtureOnDate(currentDate, fixture);
	}
	
	public void loadFixtureIntoTable (Fixture<String> fixture, Table<String,String,String> tableForDate) {
		if (fixture.getHomeGoals() != null && fixture.getAwayGoals() != null) {
			logger.info("Load Fixture and Table Cache for " + fixture.toString());
			tableForDate.addRow(createTableRow(fixture.getHomeTeam(), tableForDate, fixture));
			tableForDate.addRow(createTableRow(fixture.getAwayTeam(), tableForDate, fixture));
		} else {
			logger.info("Not loading Table Cache - unplayed " + fixture.toString());
		}
	}
	
	public void loadTeamFixtureContextsForHomeAndAwayTeams (Fixture<String> fixture, Calendar currentDate, DivisionCache divisionCache, Table<String,String,String> tableForDate) {
		int homeLeaguePosition = tableForDate.getLeaguePositionForTeamWithId(fixture.getHomeTeam().getTeamId());
		int awayLeaguePosition = tableForDate.getLeaguePositionForTeamWithId(fixture.getAwayTeam().getTeamId());
		
		TeamFixtureContext homeContext = teamFixtureContextFactory.createTeamFixtureContext();
		homeContext.setAtHome(true);
		homeContext.setLeaguePosition(homeLeaguePosition);
		homeContext.setTeam(fixture.getHomeTeam());

		TeamFixtureContext awayContext = teamFixtureContextFactory.createTeamFixtureContext();
		awayContext.setAtHome(false);
		awayContext.setLeaguePosition(awayLeaguePosition);
		awayContext.setTeam(fixture.getAwayTeam());

		if (homeLeaguePosition > awayLeaguePosition) {
			homeContext.setPlayingTeamAbove(true);
			awayContext.setPlayingTeamAbove(false);
		} else {
			homeContext.setPlayingTeamAbove(false);
			awayContext.setPlayingTeamAbove(true);
		}
		
		divisionCache.addTeamFixtureContextOnDate(currentDate, fixture.getHomeTeam(), homeContext);
		divisionCache.addTeamFixtureContextOnDate(currentDate, fixture.getAwayTeam(), awayContext);
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

	public TeamFixtureContextFactory getTeamFixtureContextFactory() {
		return teamFixtureContextFactory;
	}

	public void setTeamFixtureContextFactory(TeamFixtureContextFactory teamFixtureContextFactory) {
		this.teamFixtureContextFactory = teamFixtureContextFactory;
	}
}
