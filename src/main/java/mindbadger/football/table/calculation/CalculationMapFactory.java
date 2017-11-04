package mindbadger.football.table.calculation;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mindbadger.football.table.TableRow;
import mindbadger.football.table.TeamFixtureContext;

import org.apache.log4j.Logger;

import mindbadger.footballresultsanalyser.domain.Fixture;
import mindbadger.footballresultsanalyser.domain.Team;

public class CalculationMapFactory<K,L,M> {
	Logger logger = Logger.getLogger(CalculationMapFactory.class);
	
	private List<AttributeDefinition> rawAttributes;
	private List<AttributeDefinition> derivedAttributes;
	
	
	
	
	public Map<String, Calculation> createCalculations (Team team, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow previousTableRow)  {
	//public Map<String, Calculation> createCalculations (Team team, TableRow<K,L,M> previousTableRow, Fixture fixture)  {
		Map<String, Calculation> calculationMap = new HashMap<String, Calculation> ();
		
		logger.debug("createCalculations for team " + team.getTeamId() + " and fixture " + fixture.toString());
		
		// Loop through each value in the class map
		for (AttributeDefinition attributeDefinition : rawAttributes) {
			logger.debug("Adding raw calculation for " + attributeDefinition.getShortDescription());
			
			Class<?> clazz;
			try {
				clazz = Class.forName(attributeDefinition.getCalculationClass());
				Constructor<?> constructor = clazz.getConstructor(Team.class, Fixture.class, TeamFixtureContext.class, TeamFixtureContext.class, TableRow.class);
				Object object = constructor.newInstance(new Object[] {team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow});
				Calculation calculation = (Calculation) object;
				calculationMap.put(attributeDefinition.getAttributeId(), calculation);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}			
		}
		
		for (AttributeDefinition attributeDefinition : derivedAttributes) {
			logger.debug("Adding derived calculation for " + attributeDefinition.getShortDescription());
			try {
				Class<?> clazz = Class.forName(attributeDefinition.getCalculationClass());
				Object object = null;
				
				if (attributeDefinition.getDynamicCalculation() == null) {
					Constructor<?> constructor = clazz.getConstructor(Map.class);
					object = constructor.newInstance(new Object[] {calculationMap});
					logger.info("... created non-dynamic class: " + object);
				} else {
					Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, Map.class);
					String dynamicCalculation = attributeDefinition.getDynamicCalculation();
					object = constructor.newInstance(new Object[] {dynamicCalculation, calculationMap});
					logger.info("... created dynamic class: " + object);
				}
				
				Calculation calculation = (Calculation) object;
				
				calculationMap.put(attributeDefinition.getAttributeId(), calculation);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}			
		}
		
		return calculationMap;
	}
	
	public List<AttributeDefinition> getAttributeDefinitionList() {
		List<AttributeDefinition> allDefinitions = new ArrayList<AttributeDefinition> ();
		allDefinitions.addAll(rawAttributes);
		allDefinitions.addAll(derivedAttributes);
		
		Collections.sort(allDefinitions);
		
		return allDefinitions;
	}

	public List<AttributeDefinition> getRawAttributes() {
		return rawAttributes;
	}

	public void setRawAttributes(List<AttributeDefinition> rawCalculationClassMap) {
		this.rawAttributes = rawCalculationClassMap;
	}

	public List<AttributeDefinition> getDerivedAttributes() {
		return derivedAttributes;
	}

	public void setDerivedAttributes(List<AttributeDefinition> derivedCalculationClassMap) {
		this.derivedAttributes = derivedCalculationClassMap;
	}
}
