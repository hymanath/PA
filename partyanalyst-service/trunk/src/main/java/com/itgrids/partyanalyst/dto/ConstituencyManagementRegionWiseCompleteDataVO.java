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
	private Boolean isAreaTypeRadio;
	private List<SelectOptionVO> areaTypeRadioOptions;
	
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
	public List<SelectOptionVO> getAreaTypeRadioOptions() {
		return areaTypeRadioOptions;
	}
	public void setAreaTypeRadioOptions(List<SelectOptionVO> areaTypeRadioOptions) {
		this.areaTypeRadioOptions = areaTypeRadioOptions;
	}
	public Boolean getIsAreaTypeRadio() {
		return isAreaTypeRadio;
	}
	public void setIsAreaTypeRadio(Boolean isAreaTypeRadio) {
		this.isAreaTypeRadio = isAreaTypeRadio;
	}

}
