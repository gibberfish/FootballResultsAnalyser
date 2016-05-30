package uk.co.mindbadger.footballresults.table;

import java.util.Map;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.calculation.Calculation;
import uk.co.mindbadger.footballresults.table.calculation.CalculationMapFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowAfterResult extends TableRow {
	Logger logger = Logger.getLogger(TableRowAfterResult.class);
	
	private TableRow previousTableRow;
	private Fixture fixture;
	private Map<String, Calculation> calculations;
	private CalculationMapFactory calculationMapFactory;
	private TeamFixtureContext fixtureTeamContext;
	private TeamFixtureContext oppositionTeamContext;
	
	public TableRowAfterResult (Team team) {
		super(team);
	}
	
	public TableRowAfterResult (Team team, TableRow previousTableRow, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext) {
		super(team);
		
		if (team == null || previousTableRow == null || fixture == null) {
			throw new IllegalArgumentException("Please supply a Team, a parent table, a previous TableRow and a Fixture");
		}
		
		this.previousTableRow = previousTableRow;
		this.fixture = fixture;
		this.fixtureTeamContext = fixtureTeamContext;
		this.oppositionTeamContext = oppositionTeamContext;
	}

	@Override
	public int getAttribute(String attributeId) {
		if (calculationMapFactory == null) {
			throw new IllegalStateException("The TableRow requires a CalculationMapFactory.");
		}
		
		if (calculations == null) {
			calculations = calculationMapFactory.createCalculations(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
		}
		
		Calculation calculation = calculations.get(attributeId);
		if (calculation == null) {			
			throw new IllegalArgumentException("No value for " + attributeId);
		}
		
		return calculation.calculate();
	}

	public void setCalculationMapFactory(CalculationMapFactory calculationMapFactory) {
		this.calculationMapFactory = calculationMapFactory;
	}

	@Override
	public TableRow getPreviousTableRow() {
		return previousTableRow;
	}

	@Override
	public Fixture getFixture() {
		return fixture;
	}

	@Override
	public TeamFixtureContext getFixtureTeamContext() {
		return fixtureTeamContext;
	}

	@Override
	public TeamFixtureContext getOppositionTeamContext() {
		return oppositionTeamContext;
	}
}
