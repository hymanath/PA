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
public class ConstituencyManagementInfluenceScopeOverviewVO implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String influenceScope;
	private List<ConstituencyManagementInfluenceScopeDetailsVO> influenceScopeDetails;
	
	public String getInfluenceScope() {
		return influenceScope;
	}
	public void setInfluenceScope(String influenceScope) {
		this.influenceScope = influenceScope;
	}
	public List<ConstituencyManagementInfluenceScopeDetailsVO> getInfluenceScopeDetails() {
		return influenceScopeDetails;
	}
	public void setInfluenceScopeDetails(
			List<ConstituencyManagementInfluenceScopeDetailsVO> influenceScopeDetails) {
		this.influenceScopeDetails = influenceScopeDetails;
	}

}
