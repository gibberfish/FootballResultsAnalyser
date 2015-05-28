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
	private TeamFixtureContextFactory teamFixtureContextFactory;
	
	public void loadFixture (Fixture<String> fixture, Calendar currentDate, DivisionCache divisionCache) {
		logger.info("Load Fixture for " + fixture.toString());
		
		divisionCache.addFixtureOnDate(currentDate, fixture);
	}
	
	public void loadFixtureIntoTable (Fixture<String> fixture, Table<String,String,String> tableForDate, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext) {
		if (fixture.getHomeGoals() != null && fixture.getAwayGoals() != null) {
			logger.info("Load Fixture and Table Cache for " + fixture.toString());
			tableForDate.addRow(createTableRow(fixture.getHomeTeam(), tableForDate, fixture, fixtureTeamContext, oppositionTeamContext));
			tableForDate.addRow(createTableRow(fixture.getAwayTeam(), tableForDate, fixture, oppositionTeamContext, fixtureTeamContext));
		} else {
			logger.info("Not loading Table Cache - unplayed " + fixture.toString());
		}
	}
	
	public TeamFixtureContext loadTeamFixtureContextsForTeam (boolean homeTeam, Fixture<String> fixture, Calendar currentDate, DivisionCache divisionCache, Table<String,String,String> tableForDate) {
		int homeLeaguePosition = tableForDate.getLeaguePositionForTeamWithId(fixture.getHomeTeam().getTeamId());
		int awayLeaguePosition = tableForDate.getLeaguePositionForTeamWithId(fixture.getAwayTeam().getTeamId());
		
		TeamFixtureContext context = teamFixtureContextFactory.createTeamFixtureContext();
		context.setAtHome(homeTeam);
		context.setLeaguePosition(homeTeam ? homeLeaguePosition : awayLeaguePosition);
		context.setTeam(homeTeam ? fixture.getHomeTeam() : fixture.getAwayTeam());

		boolean homeIsAbove = homeLeaguePosition < awayLeaguePosition;
		
		if (homeTeam) {
			context.setPlayingTeamAbove(!homeIsAbove);
		} else {
			context.setPlayingTeamAbove(homeIsAbove);
		}
		
		context.setGoalsScored(homeTeam ? fixture.getHomeGoals() : fixture.getAwayGoals());
		context.setGoalsConceded(homeTeam ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		divisionCache.addTeamFixtureContextOnDate(currentDate, (homeTeam ? fixture.getHomeTeam() : fixture.getAwayTeam()), context);
		
		return context;
	}
	
	private TableRow<String, String, String> createTableRow (Team<String> team, Table<String,String,String> parentTable, Fixture<String> fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext) {
		TableRow<String, String, String> previousTableRow = parentTable.getTableRowForTeam(team.getTeamId());
		return  tableRowFactory.createTableRowFromFixture(team, previousTableRow , fixture, fixtureTeamContext, oppositionTeamContext);
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
