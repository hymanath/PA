package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreRegionInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String region;
	private Long regionId;
	private Long cadreCount;

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public Long getCadreCount() {
		return cadreCount;
	}
	public void setCadreCount(Long cadreCount) {
		this.cadreCount = cadreCount;
	}
}
