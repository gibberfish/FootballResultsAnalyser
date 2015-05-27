package uk.co.mindbadger.footballresults.table;

import java.util.Set;

import org.apache.log4j.Logger;

import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public class InitialTable<K,L,M> extends Table<K,L,M> {
	Logger logger = Logger.getLogger(InitialTable.class);
	
	public InitialTable (Table<K,L,M> previousTable) {
		super(previousTable);
	}

	public InitialTable (SeasonDivision<K,L> seasonDivision, Set<SeasonDivisionTeam<K,L,M>> seasonDivisionTeams) {
		super(null);
		
		logger.info("Creating initial table for season " + seasonDivision.getSeason().getSeasonNumber() + " / " + seasonDivision.getDivision().getDivisionId());
		
		for (SeasonDivisionTeam<K,L,M> seasonDivisionTeam : seasonDivisionTeams) {
			TableRow<K,L,M> newRow = new InitialTableRow<K,L,M>(seasonDivisionTeam.getTeam());
			addRow(newRow);
		}
		
	}
}
