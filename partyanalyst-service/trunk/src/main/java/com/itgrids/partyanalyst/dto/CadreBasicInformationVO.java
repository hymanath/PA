/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 26,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreBasicInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long cadreId;
	private Long userId;
	private Long cadreLevelId;
	private Long cadreLevelTypeId;
	private String cadreLevelType;
	private Long cadreLocationId;
	private String cadreLocation;
	private String cadreType;
	
	
	//Getters and Setters
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCadreLevelId() {
		return cadreLevelId;
	}
	public void setCadreLevelId(Long cadreLevelId) {
		this.cadreLevelId = cadreLevelId;
	}
	public String getCadreLevelType() {
		return cadreLevelType;
	}
	public void setCadreLevelType(String cadreLevelType) {
		this.cadreLevelType = cadreLevelType;
	}
	public Long getCadreLocationId() {
		return cadreLocationId;
	}
	public void setCadreLocationId(Long cadreLocationId) {
		this.cadreLocationId = cadreLocationId;
	}
	public String getCadreLocation() {
		return cadreLocation;
	}
	public void setCadreLocation(String cadreLocation) {
		this.cadreLocation = cadreLocation;
	}
	public String getCadreType() {
		return cadreType;
	}
	public void setCadreType(String cadreType) {
		this.cadreType = cadreType;
	}
	public Long getCadreLevelTypeId() {
		return cadreLevelTypeId;
	}
	public void setCadreLevelTypeId(Long cadreLevelTypeId) {
		this.cadreLevelTypeId = cadreLevelTypeId;
	}

}
