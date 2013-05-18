package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CastLocationVO implements Serializable{
	private String caste;
	private List<Double> locationWisePercentages;
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public List<Double> getLocationWisePercentages() {
		return locationWisePercentages;
	}
	public void setLocationWisePercentages(List<Double> locationWisePercentages) {
		this.locationWisePercentages = locationWisePercentages;
	}
	
	
}
