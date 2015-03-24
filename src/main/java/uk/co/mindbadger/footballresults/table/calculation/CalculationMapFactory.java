package uk.co.mindbadger.footballresults.table.calculation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class CalculationMapFactory<K,L,M> {
	
	private Map<String, String> rawCalculationClassMap;
	private Map<String, String> derivedCalculationClassMap;
	
	public Map<String, Calculation> createCalculations (Team<K> team, TableRow<K,L,M> previousTableRow, Fixture<K> fixture)  {
		Map<String, Calculation> calculationMap = new HashMap<String, Calculation> ();
		
		// Loop through each value in the class map
		for (Map.Entry<String, String> entry : rawCalculationClassMap.entrySet()) {
			String attributeKey = entry.getKey();
			String calculationClass = entry.getValue();
			
			Class<?> clazz;
			try {
				clazz = Class.forName(calculationClass);
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
		
		for (Map.Entry<String, String> entry : derivedCalculationClassMap.entrySet()) {
			String attributeKey = entry.getKey();
			String calculationClass = entry.getValue();
			try {
				Class<?> clazz = Class.forName(calculationClass);
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

	public Map<String, String> getRawCalculationClassMap() {
		return rawCalculationClassMap;
	}

	public void setRawCalculationClassMap(Map<String, String> rawCalculationClassMap) {
		this.rawCalculationClassMap = rawCalculationClassMap;
	}

	public Map<String, String> getDerivedCalculationClassMap() {
		return derivedCalculationClassMap;
	}

	public void setDerivedCalculationClassMap(
			Map<String, String> derivedCalculationClassMap) {
		this.derivedCalculationClassMap = derivedCalculationClassMap;
	}
}
