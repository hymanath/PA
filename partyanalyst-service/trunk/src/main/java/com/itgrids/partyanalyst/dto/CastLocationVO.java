package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CastLocationVO implements Serializable{
	private String caste;
	private List<Long> locationWiseCastesCount;
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public List<Long> getLocationWiseCastesCount() {
		return locationWiseCastesCount;
	}
	public void setLocationWiseCastesCount(List<Long> locationWiseCastesCount) {
		this.locationWiseCastesCount = locationWiseCastesCount;
	}
	
	
}
