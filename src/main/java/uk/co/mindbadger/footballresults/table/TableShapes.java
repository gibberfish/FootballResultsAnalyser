package uk.co.mindbadger.footballresults.table;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresults.table.calculation.AttributeDefinition;

//TODO Unit test for this
public class TableShapes {
	Logger logger = Logger.getLogger(TableShapes.class);
	
	private List<AttributeDefinition> shortTable;
	private List<AttributeDefinition> homeAwayTable;
	
	public TableShapes () {
	}
	
	public List<AttributeDefinition> getShortTable() {
//		List<AttributeDefinition> definitions = new ArrayList<AttributeDefinition> ();
//		definitions.addAll(shortTable);
		Collections.sort(shortTable);
		return shortTable;
	}
	
	public void setShortTable(List<AttributeDefinition> shortTable) {
		this.shortTable = shortTable;
	}
	
	public List<AttributeDefinition> getHomeAwayTable() {
//		List<AttributeDefinition> definitions = new ArrayList<AttributeDefinition> ();
//		definitions.addAll(homeAwayTable);
		Collections.sort(homeAwayTable);
		return homeAwayTable;
	}
	
	public void setHomeAwayTable(List<AttributeDefinition> homeAwayTable) {
		this.homeAwayTable = homeAwayTable;
	}
}
