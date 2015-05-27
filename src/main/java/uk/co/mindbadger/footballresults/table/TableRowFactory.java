package uk.co.mindbadger.footballresults.table;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.calculation.CalculationMapFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowFactory<K,L,M> {
	Logger logger = Logger.getLogger(TableRowFactory.class);
	
	private CalculationMapFactory<K, L, M> calculationMapFactory;
	
	public TableRowFactory (CalculationMapFactory<K, L, M> calculationMapFactory) {
		this.calculationMapFactory = calculationMapFactory;
	}
	
	//TODO Remove the argument for parentTable
	public TableRow<K,L,M> createTableRowFromFixture (Team<K> team, TableRow<K,L,M> previousTableRow, Fixture<K> fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext) {
		logger.info("Create table row from fixture, team="+team.getTeamName() + ", fixture=" + fixture.toString());
		TableRowAfterResult<K,L,M> tableRow = new TableRowAfterResult<>(team, previousTableRow, fixture, fixtureTeamContext, oppositionTeamContext);
		tableRow.setCalculationMapFactory(calculationMapFactory);
		return tableRow;
	}
}
