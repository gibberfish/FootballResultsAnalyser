package uk.co.mindbadger.footballresults.table;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.calculation.CalculationMapFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowFactory {
	Logger logger = Logger.getLogger(TableRowFactory.class);
	
	private CalculationMapFactory calculationMapFactory;
	
	public TableRowFactory (CalculationMapFactory calculationMapFactory) {
		this.calculationMapFactory = calculationMapFactory;
	}
	
	//TODO Remove the argument for parentTable
	public TableRow createTableRowFromFixture (Team team, TableRow previousTableRow, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext) {
		logger.info("Create table row from fixture, team="+team.getTeamName() + ", fixture=" + fixture.toString());
		TableRowAfterResult tableRow = new TableRowAfterResult(team, previousTableRow, fixture, fixtureTeamContext, oppositionTeamContext);
		tableRow.setCalculationMapFactory(calculationMapFactory);
		return tableRow;
	}
}
