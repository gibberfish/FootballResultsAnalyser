package mindbadger.footballresultsanalyser.hibernate;

import java.util.Set;

public class Season {
	private Integer ssnNum;
	public Set<SeasonDivision> divisionsInSeason;

	public Season () {
	}

	public Season (Integer ssnNum) {
		this.ssnNum = ssnNum;
	}
	
	public Integer getSsnNum() {
		return ssnNum;
	}

	public void setSsnNum(Integer ssnNum) {
		this.ssnNum = ssnNum;
	}
	
	public Set<SeasonDivision> getDivisionsInSeason() {
		return divisionsInSeason;
	}
	
	public void setDivisionsInSeason(Set<SeasonDivision> divisionsInSeason) {
		this.divisionsInSeason = divisionsInSeason;
	}
}
