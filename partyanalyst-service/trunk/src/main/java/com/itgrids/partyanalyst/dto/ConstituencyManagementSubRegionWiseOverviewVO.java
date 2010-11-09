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
public class ConstituencyManagementSubRegionWiseOverviewVO implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long subRegionId;
	private String subRegionName;
	private String subRegionType;
	private String subRegionTitle;
	private Long countValue;

	

	public String getSubRegionTitle() {
		return subRegionTitle;
	}

	public void setSubRegionTitle(String subRegionTitle) {
		this.subRegionTitle = subRegionTitle;
	}

	public Long getSubRegionId() {
		return subRegionId;
	}

	public void setSubRegionId(Long subRegionId) {
		this.subRegionId = subRegionId;
	}

	public String getSubRegionName() {
		return subRegionName;
	}

	public void setSubRegionName(String subRegionName) {
		this.subRegionName = subRegionName;
	}

	public String getSubRegionType() {
		return subRegionType;
	}

	public void setSubRegionType(String subRegionType) {
		this.subRegionType = subRegionType;
	}

	public Long getCountValue() {
		return countValue;
	}

	public void setCountValue(Long countValue) {
		this.countValue = countValue;
	}

}
