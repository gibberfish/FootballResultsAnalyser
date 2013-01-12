package mindbadger.footballresultsanalyser.hibernate;

import java.io.Serializable;

public class Division implements Serializable {
    private Integer divId;
    private String divName;
    
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
}
