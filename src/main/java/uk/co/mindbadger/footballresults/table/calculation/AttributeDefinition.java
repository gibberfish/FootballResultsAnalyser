package uk.co.mindbadger.footballresults.table.calculation;

public class AttributeDefinition {
	private String description;
	private String shortDescription;
	private String calculationClass;
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
}
