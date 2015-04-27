package uk.co.mindbadger.footballresults.table.calculation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class CalculationMapFactory<K,L,M> {
	Logger logger = Logger.getLogger(CalculationMapFactory.class);
	
	private Map<String, AttributeDefinition> rawCalculationClassMap;
	private Map<String, AttributeDefinition> derivedCalculationClassMap;
	
	public Map<String, Calculation> createCalculations (Team<K> team, TableRow<K,L,M> previousTableRow, Fixture<K> fixture)  {
		Map<String, Calculation> calculationMap = new HashMap<String, Calculation> ();
		
		logger.debug("createCalculations for team " + team.getTeamId() + " and fixture " + fixture.toString());
		
		// Loop through each value in the class map
		for (Map.Entry<String, AttributeDefinition> entry : rawCalculationClassMap.entrySet()) {
			String attributeKey = entry.getKey();
			AttributeDefinition attributeDefinition = entry.getValue();
	
			logger.debug("Adding raw calculation for " + attributeKey);
			
			Class<?> clazz;
			try {
				clazz = Class.forName(attributeDefinition.getCalculationClass());
				Constructor<?> constructor = clazz.getConstructor(Team.class, TableRow.class, Fixture.class);
				Object object = constructor.newInstance(new Object[] {team, previousTableRow, fixture});
				Calculation calculation = (Calculation) object;
				calculationMap.put(attributeKey, calculation);
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
		
		for (Map.Entry<String, AttributeDefinition> entry : derivedCalculationClassMap.entrySet()) {
			String attributeKey = entry.getKey();
			AttributeDefinition attributeDefinition = entry.getValue();
			
			logger.debug("Adding derived calculation for " + attributeKey);
			try {
				Class<?> clazz = Class.forName(attributeDefinition.getCalculationClass());
				Constructor<?> constructor = clazz.getConstructor(Map.class);
				Object object = constructor.newInstance(new Object[] {calculationMap});
				Calculation calculation = (Calculation) object;
				
				calculationMap.put(attributeKey, calculation);
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

	public Map<String, AttributeDefinition> getRawCalculationClassMap() {
		return rawCalculationClassMap;
	}

	public void setRawCalculationClassMap(Map<String, AttributeDefinition> rawCalculationClassMap) {
		this.rawCalculationClassMap = rawCalculationClassMap;
	}

	public Map<String, AttributeDefinition> getDerivedCalculationClassMap() {
		return derivedCalculationClassMap;
	}

	public void setDerivedCalculationClassMap(
			Map<String, AttributeDefinition> derivedCalculationClassMap) {
		this.derivedCalculationClassMap = derivedCalculationClassMap;
	}
}
