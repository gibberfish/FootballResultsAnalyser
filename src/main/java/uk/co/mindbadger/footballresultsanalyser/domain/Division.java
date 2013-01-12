package uk.co.mindbadger.footballresultsanalyser.domain;

import java.util.Set;

public class Division {
    private Integer divId;
    private String divName;
    private Set<SeasonDivision> seasonsForDivision;
    
	public Integer getDivId() {
		return divId;
	}
	public void setDivId(Integer divId) {
		this.divId = divId;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public Set<SeasonDivision> getSeasonsForDivision() {
		return seasonsForDivision;
	}
	public void setSeasonsForDivision(Set<SeasonDivision> seasonsForDivision) {
		this.seasonsForDivision = seasonsForDivision;
	}
}
