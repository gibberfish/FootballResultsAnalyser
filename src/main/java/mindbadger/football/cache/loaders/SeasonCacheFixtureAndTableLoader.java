package mindbadger.football.cache.loaders;

import java.util.Calendar;

import mindbadger.football.caches.DivisionCache;
import mindbadger.football.table.Table;
import mindbadger.football.table.TableFactory;
import mindbadger.football.table.TableRow;
import mindbadger.football.table.TableRowFactory;
import mindbadger.football.table.TeamFixtureContext;
import mindbadger.football.table.TeamFixtureContextFactory;

import org.apache.log4j.Logger;

import mindbadger.footballresultsanalyser.domain.Fixture;
import mindbadger.footballresultsanalyser.domain.Team;

public class SeasonCacheFixtureAndTableLoader {
	Logger logger = Logger.getLogger(SeasonCacheFixtureAndTableLoader.class);
	
	private TableFactory tableFactory;
	private TableRowFactory tableRowFactory;
	private TeamFixtureContextFactory teamFixtureContextFactory;
	
	public void loadFixture (Fixture fixture, Calendar currentDate, DivisionCache divisionCache) {
		logger.info("Load Fixture for " + fixture.toString());
		
		divisionCache.addFixtureOnDate(currentDate, fixture);
	}
	
	public void loadFixtureIntoTable (Fixture fixture, Table tableForDate, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext) {
		if (fixture.getHomeGoals() != null && fixture.getAwayGoals() != null) {
			logger.info("Load Fixture and Table Cache for " + fixture.toString());
			tableForDate.addRow(createTableRow(fixture.getHomeTeam(), tableForDate, fixture, fixtureTeamContext, oppositionTeamContext));
			tableForDate.addRow(createTableRow(fixture.getAwayTeam(), tableForDate, fixture, oppositionTeamContext, fixtureTeamContext));
		} else {
			logger.info("Not loading Table Cache - unplayed " + fixture.toString());
		}
	}
	
	public TeamFixtureContext loadTeamFixtureContextsForTeam (boolean homeTeam, Fixture fixture, Calendar currentDate, DivisionCache divisionCache, Table tableForDate) {
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
		
		Integer goalsScored = homeTeam ? fixture.getHomeGoals() : fixture.getAwayGoals();
		Integer goalsConceded = homeTeam ? fixture.getAwayGoals() : fixture.getHomeGoals();

		if (goalsScored != null) {
			context.setGoalsScored(goalsScored);
			context.setGoalsConceded(goalsConceded);
		
			if (goalsScored > goalsConceded) {
				context.setPoints(3);
			} else if (goalsScored == goalsConceded) {
				context.setPoints(1);
			} else {
				context.setPoints(0);
			}
		}
		
		divisionCache.addTeamFixtureContextOnDate(currentDate, (homeTeam ? fixture.getHomeTeam() : fixture.getAwayTeam()), context);
		
		return context;
	}
	
	private TableRow createTableRow (Team team, Table parentTable, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext) {
		TableRow previousTableRow = parentTable.getTableRowForTeam(team.getTeamId());
		return  tableRowFactory.createTableRowFromFixture(team, previousTableRow , fixture, fixtureTeamContext, oppositionTeamContext);
	}

	public TableFactory getTableFactory() {
		return tableFactory;
	}

	public void setTableFactory(TableFactory tableFactory) {
		this.tableFactory = tableFactory;
	}
	
	public TableRowFactory getTableRowFactory() {
		return tableRowFactory;
	}

	public void setTableRowFactory(TableRowFactory tableRowFactory) {
		this.tableRowFactory = tableRowFactory;
	}

	public TeamFixtureContextFactory getTeamFixtureContextFactory() {
		return teamFixtureContextFactory;
	}

	public void setTeamFixtureContextFactory(TeamFixtureContextFactory teamFixtureContextFactory) {
		this.teamFixtureContextFactory = teamFixtureContextFactory;
	}
}
