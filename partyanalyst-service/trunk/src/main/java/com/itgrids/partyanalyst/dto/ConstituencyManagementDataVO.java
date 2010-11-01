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
public class ConstituencyManagementDataVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dataCategory;
	private ConstituencyManagementRegionWiseOverviewVO regionWiseOverview;
	private List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionWiseOverview;
	private List<ConstituencyManagementInfluenceScopeOverviewVO> influenceScopeOverview;
	
	public String getDataCategory() {
		return dataCategory;
	}
	public void setDataCategory(String dataCategory) {
		this.dataCategory = dataCategory;
	}
	public ConstituencyManagementRegionWiseOverviewVO getRegionWiseOverview() {
		return regionWiseOverview;
	}
	public void setRegionWiseOverview(
			ConstituencyManagementRegionWiseOverviewVO regionWiseOverview) {
		this.regionWiseOverview = regionWiseOverview;
	}
	public List<ConstituencyManagementSubRegionWiseOverviewVO> getSubRegionWiseOverview() {
		return subRegionWiseOverview;
	}
	public void setSubRegionWiseOverview(
			List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionWiseOverview) {
		this.subRegionWiseOverview = subRegionWiseOverview;
	}
	public List<ConstituencyManagementInfluenceScopeOverviewVO> getInfluenceScopeOverview() {
		return influenceScopeOverview;
	}
	public void setInfluenceScopeOverview(
			List<ConstituencyManagementInfluenceScopeOverviewVO> influenceScopeOverview) {
		this.influenceScopeOverview = influenceScopeOverview;
	}
	

}
