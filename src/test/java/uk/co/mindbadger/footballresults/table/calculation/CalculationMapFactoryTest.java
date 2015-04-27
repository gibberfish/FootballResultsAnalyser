package uk.co.mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowAfterResult;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class CalculationMapFactoryTest {
	private CalculationMapFactory<String, String, String> objectUnderTest;
	
	private List<AttributeDefinition> rawAttributes = new ArrayList<AttributeDefinition> ();
	private List<AttributeDefinition> derivedAttributes = new ArrayList<AttributeDefinition> ();
	
	@Mock
	private TableRowAfterResult<String,String,String> mockPreviousTableRow;
	
	@Mock
	private Fixture<String> mockFixture;
	
	@Mock
	private Team<String> mockTeam1;

	@Mock
	private Team<String> mockTeam2;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectUnderTest = new CalculationMapFactory<String,String,String>();
		objectUnderTest.setDerivedAttributes(derivedAttributes);
		objectUnderTest.setRawAttributes(rawAttributes);
	}

	@Test
	public void shouldCreateACalculationMapFromValidConfiguration () {
		// Given
		AttributeDefinition goalsScoredDefinition = new AttributeDefinition ();
		goalsScoredDefinition.setCalculationClass("uk.co.mindbadger.footballresults.table.calculation.GoalsScoredCalculation");
		goalsScoredDefinition.setSequence(1);

		AttributeDefinition goalsConcededDefinition = new AttributeDefinition ();
		goalsConcededDefinition.setCalculationClass("uk.co.mindbadger.footballresults.table.calculation.GoalsConcededCalculation");
		goalsConcededDefinition.setSequence(2);

		AttributeDefinition goalDifferenceDefinition = new AttributeDefinition ();
		goalDifferenceDefinition.setCalculationClass("uk.co.mindbadger.footballresults.table.calculation.GoalDifferenceCalculation2");
		goalDifferenceDefinition.setSequence(3);

		rawAttributes.add(goalsConcededDefinition);
		derivedAttributes.add(goalDifferenceDefinition);
		rawAttributes.add(goalsScoredDefinition);


		// When
		List<AttributeDefinition> attributeDefinitions = objectUnderTest.getAttributeDefinitionList ();
		
		// Then
		assertEquals (goalsScoredDefinition, attributeDefinitions.get(0));
		assertEquals (goalsConcededDefinition, attributeDefinitions.get(1));
		assertEquals (goalDifferenceDefinition, attributeDefinitions.get(2));
	}

	@Test
	public void shouldReturnASortedListOfAttributeDefinitions () {
		// Given
		
		// When
		
		// Then
		
	}
}
