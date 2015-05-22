package uk.co.mindbadger.footballresults.table;

import java.util.Collections;
import java.util.List;

import uk.co.mindbadger.footballresults.table.calculation.AttributeDefinition;

public class TableShapes {
	private List<AttributeDefinition> shortTable;
	private List<AttributeDefinition> homeAwayTable;
	
	public TableShapes () {
	}
	
	public List<AttributeDefinition> getShortTable() {
		Collections.sort(shortTable);
		return shortTable;
	}
	
	public void setShortTable(List<AttributeDefinition> shortTable) {
		this.shortTable = shortTable;
	}
	
	public List<AttributeDefinition> getHomeAwayTable() {
		Collections.sort(homeAwayTable);
		return homeAwayTable;
	}
	
	public void setHomeAwayTable(List<AttributeDefinition> homeAwayTable) {
		this.homeAwayTable = homeAwayTable;
	}
}
