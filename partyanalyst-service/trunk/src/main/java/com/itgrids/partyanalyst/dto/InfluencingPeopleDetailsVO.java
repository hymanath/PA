/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 29, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sai Krishna
 *
 */
public class InfluencingPeopleDetailsVO extends ResultStatus implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long regionId;
	private String regionName;
	private String regionType;
	private Long count;
	private List<InfluencingPeopleBeanVO> influencingPeopleDetails;

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

	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	public List<InfluencingPeopleBeanVO> getInfluencingPeopleDetails() {
		return influencingPeopleDetails;
	}

	public void setInfluencingPeopleDetails(
			List<InfluencingPeopleBeanVO> influencingPeopleDetails) {
		this.influencingPeopleDetails = influencingPeopleDetails;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
}
