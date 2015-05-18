package uk.co.mindbadger.footballresults.table;

import java.util.Iterator;
import java.util.Map;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.calculation.Calculation;
import uk.co.mindbadger.footballresults.table.calculation.CalculationMapFactory;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class TableRowAfterResult<K,L,M> extends TableRow<K,L,M> {
	private TableRow<K,L,M> previousTableRow;
	private Fixture<K> fixture;
	private Map<String, Calculation> calculations;
	private CalculationMapFactory<K, L, M> calculationMapFactory;
	private TeamFixtureContext fixtureTeamContext;
	private TeamFixtureContext oppositionTeamContext;
	
	public TableRowAfterResult (Team<K> team, Table<K,L,M> parentTable) {
		super(team, parentTable);
	}
	
	public TableRowAfterResult (Team<K> team, Table<K,L,M> parentTable, TableRow<K,L,M> previousTableRow, Fixture<K> fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext) {
		super(team, parentTable);
		
		if (team == null || parentTable == null || previousTableRow == null || fixture == null) {
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
			
			System.err.println("number:"+calculations.size());
			Iterator i = calculations.keySet().iterator();
			while (i.hasNext()) {
				System.err.println("KEY: " + i.next());
			}
			
			throw new IllegalArgumentException("No value for " + attributeId);
		}
		
		return calculation.calculate();
	}

	public void setCalculationMapFactory(CalculationMapFactory<K, L, M> calculationMapFactory) {
		this.calculationMapFactory = calculationMapFactory;
	}
}
