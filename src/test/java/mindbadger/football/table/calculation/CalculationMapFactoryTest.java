package mindbadger.football.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mindbadger.football.table.TableRow;
import mindbadger.football.table.TableRowAfterResult;
import mindbadger.football.table.calculation.AttributeDefinition;
import mindbadger.football.table.calculation.CalculationMapFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.football.domain.Fixture;
import mindbadger.football.domain.Team;

public class CalculationMapFactoryTest {
	private CalculationMapFactory<String, String, String> objectUnderTest;
	
	private List<AttributeDefinition> rawAttributes = new ArrayList<AttributeDefinition> ();
	private List<AttributeDefinition> derivedAttributes = new ArrayList<AttributeDefinition> ();
	
	@Mock
	private TableRowAfterResult mockPreviousTableRow;
	
	@Mock
	private Fixture mockFixture;
	
	@Mock
	private Team mockTeam1;

	@Mock
	private Team mockTeam2;

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
		goalsScoredDefinition.setCalculationClass("mindbadger.football.table.calculation.GoalsScoredCalculation");
		goalsScoredDefinition.setSequence(1);

		AttributeDefinition goalsConcededDefinition = new AttributeDefinition ();
		goalsConcededDefinition.setCalculationClass("mindbadger.football.table.calculation.GoalsConcededCalculation");
		goalsConcededDefinition.setSequence(2);

		AttributeDefinition goalDifferenceDefinition = new AttributeDefinition ();
		goalDifferenceDefinition.setCalculationClass("mindbadger.football.table.calculation.GoalDifferenceCalculation2");
		goalDifferenceDefinition.setSequence(3);

		AttributeDefinition goalDifferenceDefinition2 = new AttributeDefinition ();
		goalDifferenceDefinition2.setCalculationClass("mindbadger.football.table.calculation.DynamicCalculation");
		goalDifferenceDefinition2.setSequence(3);
		goalDifferenceDefinition2.setDynamicCalculation("{goalsScored}-{goalsConceded}");

		rawAttributes.add(goalsConcededDefinition);
		derivedAttributes.add(goalDifferenceDefinition);
		rawAttributes.add(goalsScoredDefinition);
		derivedAttributes.add(goalDifferenceDefinition2);

		// When
		List<AttributeDefinition> attributeDefinitions = objectUnderTest.getAttributeDefinitionList ();
		
		// Then
		assertEquals (goalsScoredDefinition, attributeDefinitions.get(0));
		assertEquals (goalsConcededDefinition, attributeDefinitions.get(1));
		assertEquals (goalDifferenceDefinition, attributeDefinitions.get(2));
		assertEquals (goalDifferenceDefinition2, attributeDefinitions.get(3));
	}
}
