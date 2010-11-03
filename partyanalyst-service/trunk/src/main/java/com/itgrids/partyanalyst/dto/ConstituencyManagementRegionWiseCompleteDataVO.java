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
public class ConstituencyManagementRegionWiseCompleteDataVO implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ConstituencyManagementSubRegionWiseOverviewVO> regionsList;
	private List<ConstituencyManagementRegionWiseOverviewVO> regionWiseOverview;
	
	public List<ConstituencyManagementSubRegionWiseOverviewVO> getRegionsList() {
		return regionsList;
	}
	public void setRegionsList(List<ConstituencyManagementSubRegionWiseOverviewVO> regionsList) {
		this.regionsList = regionsList;
	}
	public List<ConstituencyManagementRegionWiseOverviewVO> getRegionWiseOverview() {
		return regionWiseOverview;
	}
	public void setRegionWiseOverview(
			List<ConstituencyManagementRegionWiseOverviewVO> regionWiseOverview) {
		this.regionWiseOverview = regionWiseOverview;
	}

}
