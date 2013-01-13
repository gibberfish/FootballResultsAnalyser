package uk.co.mindbadger.footballresultsanalyser.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="season", catalog="football")
public class Season implements Serializable {
	private static final long serialVersionUID = -4032527106145900975L;

	private Integer ssnNum;
	private Set<SeasonDivision> divisionsInSeason = new HashSet<SeasonDivision>(0);
	//public Set<Fixture> fixturesInSeason;

	public Season (Integer ssnNum) {
		this.ssnNum = ssnNum;
	}
	
	@Id
	@Column(name="SSN_NUM", unique=true, nullable=false)
	public Integer getSsnNum() {
		return ssnNum;
	}
	
	public void setSsnNum(Integer ssnNum) {
		this.ssnNum = ssnNum;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryKey.season", cascade=CascadeType.ALL)
	public Set<SeasonDivision> getDivisionsInSeason() {
		return divisionsInSeason;
	}
	public void setDivisionsInSeason(Set<SeasonDivision> divisionsInSeason) {
		this.divisionsInSeason = divisionsInSeason;
	}
	
	public void addDivisionToSeason (Division division, int divisionPosition) {
		SeasonDivision seasonDivision = new SeasonDivision ();
		seasonDivision.setSeason(this);
		seasonDivision.setDivision(division);
		seasonDivision.setDivPos(divisionPosition);
		this.divisionsInSeason.add(seasonDivision);
		division.getSeasonsForDivision().add(seasonDivision);
	}
	
//	public Set<Fixture> getFixturesInSeason() {
//		return fixturesInSeason;
//	}
//	public void setFixturesInSeason(Set<Fixture> fixturesInSeason) {
//		this.fixturesInSeason = fixturesInSeason;
//	}
}
