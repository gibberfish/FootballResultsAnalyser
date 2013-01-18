package uk.co.mindbadger.footballresultsanalyser.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class SeasonDivisionId implements Serializable {
    private static final long serialVersionUID = 2044020611013634053L;

    private Season season;
    private Division division;

    @ManyToOne
    public Season getSeason() {
	return season;
    }

    public void setSeason(Season season) {
	this.season = season;
    }

    @ManyToOne
    public Division getDivision() {
	return division;
    }

    public void setDivision(Division division) {
	this.division = division;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;

	SeasonDivisionId that = (SeasonDivisionId) o;

	if (season != null ? !season.equals(that.season) : that.season != null)
	    return false;
	if (division != null ? !division.equals(that.division) : that.division != null)
	    return false;

	return true;
    }

    @Override
    public int hashCode() {
	int result;
	result = (season != null ? season.hashCode() : 0);
	result = 31 * result + (division != null ? division.hashCode() : 0);
	return result;
    }
}
