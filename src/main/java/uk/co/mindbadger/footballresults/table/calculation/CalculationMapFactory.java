package uk.co.mindbadger.footballresults.table.calculation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class CalculationMapFactory<K,L,M> {
	Logger logger = Logger.getLogger(CalculationMapFactory.class);
	
	private List<AttributeDefinition> rawAttributes;
	private List<AttributeDefinition> derivedAttributes;
	
	public Map<String, Calculation> createCalculations (Team<K> team, TableRow<K,L,M> previousTableRow, Fixture<K> fixture)  {
		Map<String, Calculation> calculationMap = new HashMap<String, Calculation> ();
		
		logger.debug("createCalculations for team " + team.getTeamId() + " and fixture " + fixture.toString());
		
		// Loop through each value in the class map
		for (AttributeDefinition attributeDefinition : rawAttributes) {
			logger.debug("Adding raw calculation for " + attributeDefinition.getShortDescription());
			
			Class<?> clazz;
			try {
				clazz = Class.forName(attributeDefinition.getCalculationClass());
				Constructor<?> constructor = clazz.getConstructor(Team.class, TableRow.class, Fixture.class);
				Object object = constructor.newInstance(new Object[] {team, previousTableRow, fixture});
				Calculation calculation = (Calculation) object;
				calculationMap.put(attributeDefinition.getAttributeId(), calculation);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		for (AttributeDefinition attributeDefinition : derivedAttributes) {
			logger.debug("Adding derived calculation for " + attributeDefinition.getShortDescription());
			try {
				Class<?> clazz = Class.forName(attributeDefinition.getCalculationClass());
				Constructor<?> constructor = clazz.getConstructor(Map.class);
				Object object = constructor.newInstance(new Object[] {calculationMap});
				Calculation calculation = (Calculation) object;
				
				calculationMap.put(attributeDefinition.getAttributeId(), calculation);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
