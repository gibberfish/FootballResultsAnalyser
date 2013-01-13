package uk.co.mindbadger.footballresultsanalyser.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="division", catalog="football")
public class Division implements Serializable {
	private static final long serialVersionUID = 693092329472146716L;
	
	private Integer divId;
    private String divName;
    private Set<SeasonDivision> seasonsForDivision  = new HashSet<SeasonDivision> ();
    
    public Division (String divName) {
    	this.divName = divName;
    }
    
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "DIV_ID", unique = true, nullable = false)
	public Integer getDivId() {
		return divId;
	}
	public void setDivId(Integer divId) {
		this.divId = divId;
	}
	
	@Column(name = "DIV_NAME", nullable = false)
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryKey.division")
	public Set<SeasonDivision> getSeasonsForDivision() {
		return seasonsForDivision;
	}
	public void setSeasonsForDivision(Set<SeasonDivision> seasonsForDivision) {
		this.seasonsForDivision = seasonsForDivision;
	}
}
