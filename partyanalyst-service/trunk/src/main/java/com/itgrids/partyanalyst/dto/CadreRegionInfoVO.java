package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreRegionInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String region;//regionType: eg. STATE, DISTRICT etc..
	private Long regionId;
	private String regionName;
	private Long cadreCount;

	public CadreRegionInfoVO(){}
	
	public CadreRegionInfoVO(String regionType){
		region = regionType;
	}

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

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Long getCadreCount() {
		return cadreCount;
	}

	public void setCadreCount(Long cadreCount) {
		this.cadreCount = cadreCount;
	} 
	

}
