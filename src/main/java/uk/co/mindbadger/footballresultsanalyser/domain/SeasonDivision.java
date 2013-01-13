package uk.co.mindbadger.footballresultsanalyser.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "season_division", catalog = "football")
@AssociationOverrides({
		@AssociationOverride(name = "primaryKey.season", 
			joinColumns = @JoinColumn(name = "SSN_NUM")),
		@AssociationOverride(name = "primaryKey.division", 
			joinColumns = @JoinColumn(name = "DIV_ID")) })
public class SeasonDivision implements Serializable {
	private static final long serialVersionUID = -862413151112264079L;
	
	private SeasonDivisionId primaryKey = new SeasonDivisionId();
	private int divPos;
	private Set<SeasonDivisionTeam> teamsInSeasonDivision = new HashSet<SeasonDivisionTeam>(0);
	
	@EmbeddedId
	public SeasonDivisionId getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(SeasonDivisionId primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Transient
	public Season getSeason() {
		return primaryKey.getSeason();
	}
	public void setSeason(Season season) {
		primaryKey.setSeason(season);
	}
	
	@Transient
	public Division getDivision() {
		return primaryKey.getDivision();
	}
	public void setDivision(Division division) {
		primaryKey.setDivision(division);
	}
	
	@Column(name = "DIV_POS", nullable=true)
	public int getDivPos() {
		return divPos;
	}
	public void setDivPos(int divPos) {
		this.divPos = divPos;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryKey.seasonDivision")
	public Set<SeasonDivisionTeam> getTeamsInSeasonDivision() {
		return teamsInSeasonDivision;
	}
	public void setTeamsInSeasonDivision(Set<SeasonDivisionTeam> teamsInSeasonDivision) {
		this.teamsInSeasonDivision = teamsInSeasonDivision;
	}
}
