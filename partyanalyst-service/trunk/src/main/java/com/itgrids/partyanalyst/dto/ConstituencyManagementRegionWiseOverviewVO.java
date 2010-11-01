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
public class ConstituencyManagementRegionWiseOverviewVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long regionId;
	private String regionName;
	private String regionType;
	
	private Long countValue;
	
	private List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionWiseOverview;

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

	public Long getCountValue() {
		return countValue;
	}

	public void setCountValue(Long countValue) {
		this.countValue = countValue;
	}

	public List<ConstituencyManagementSubRegionWiseOverviewVO> getSubRegionWiseOverview() {
		return subRegionWiseOverview;
	}

	public void setSubRegionWiseOverview(
			List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionWiseOverview) {
		this.subRegionWiseOverview = subRegionWiseOverview;
	}

}
