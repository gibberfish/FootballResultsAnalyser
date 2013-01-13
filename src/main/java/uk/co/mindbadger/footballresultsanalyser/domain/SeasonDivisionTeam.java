package uk.co.mindbadger.footballresultsanalyser.domain;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "season_division_team", catalog = "football")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.seasonDivision", joinColumns = {
	    @JoinColumn(name = "SSN_NUM", referencedColumnName="SSN_NUM"),
	    @JoinColumn(name = "DIV_ID", referencedColumnName="DIV_ID")
    }),
    @AssociationOverride(name = "primaryKey.team", joinColumns = @JoinColumn(name = "TEAM_ID")) })
public class SeasonDivisionTeam implements Serializable {
    private static final long serialVersionUID = -862413151112264079L;

    private SeasonDivisionTeamId primaryKey = new SeasonDivisionTeamId();

    @EmbeddedId
    public SeasonDivisionTeamId getPrimaryKey() {
	return primaryKey;
    }

    public void setPrimaryKey(SeasonDivisionTeamId primaryKey) {
	this.primaryKey = primaryKey;
    }

    @Transient
    public SeasonDivision getSeasonDivision() {
	return primaryKey.getSeasonDivision();
    }

    public void setSeasonDivision(SeasonDivision seasonDivision) {
	primaryKey.setSeasonDivision(seasonDivision);
    }

    @Transient
    public Team getTeam() {
	return primaryKey.getTeam();
    }

    public void setTeam(Team team) {
	primaryKey.setTeam(team);
    }
}
