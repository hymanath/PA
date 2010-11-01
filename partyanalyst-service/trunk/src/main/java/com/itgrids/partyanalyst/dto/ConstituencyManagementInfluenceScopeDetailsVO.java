/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 29, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/**
 * @author Sai Krishna
 *
 */
public class ConstituencyManagementInfluenceScopeDetailsVO implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long influenceScopeRegionId;
	private String influenceScopeRegion;
	
	private Long countValue;

	public Long getInfluenceScopeRegionId() {
		return influenceScopeRegionId;
	}

	public void setInfluenceScopeRegionId(Long influenceScopeRegionId) {
		this.influenceScopeRegionId = influenceScopeRegionId;
	}

	public String getInfluenceScopeRegion() {
		return influenceScopeRegion;
	}

	public void setInfluenceScopeRegion(String influenceScopeRegion) {
		this.influenceScopeRegion = influenceScopeRegion;
	}

	public Long getCountValue() {
		return countValue;
	}

	public void setCountValue(Long countValue) {
		this.countValue = countValue;
	}
	

}
