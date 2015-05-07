package uk.co.mindbadger.footballresults.table.calculation;

public class AttributeDefinition implements Comparable<AttributeDefinition> {
	private String attributeId;
	private String description;
	private String shortDescription;
	private String calculationClass;
	private int sequence;
	private String dynamicCalculation;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getCalculationClass() {
		return calculationClass;
	}
	public void setCalculationClass(String calculationClass) {
		this.calculationClass = calculationClass;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}
	public String getDynamicCalculation() {
		return dynamicCalculation;
	}
	public void setDynamicCalculation(String dynamicCalculation) {
		this.dynamicCalculation = dynamicCalculation;
	}
	
	@Override
	public int compareTo(AttributeDefinition objectToCompareTo) {
		return this.sequence - objectToCompareTo.sequence;
	}
}
