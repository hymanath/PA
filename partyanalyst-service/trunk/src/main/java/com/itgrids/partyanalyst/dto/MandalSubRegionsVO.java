/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 13,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sai Krishna
 *
 */
public class MandalSubRegionsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long mandalId;
	private String mandalName;
	private Boolean isPartial;
	private String mandalForTown;
	
	private List<SelectOptionVO> villagesList;

	/**
	 * @return the mandalId
	 */
	public Long getMandalId() {
		return mandalId;
	}

	/**
	 * @param mandalId the mandalId to set
	 */
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}

	/**
	 * @return the mandalName
	 */
	public String getMandalName() {
		return mandalName;
	}

	/**
	 * @param mandalName the mandalName to set
	 */
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	/**
	 * @return the isPartial
	 */
	public Boolean getIsPartial() {
		return isPartial;
	}

	/**
	 * @param isPartial the isPartial to set
	 */
	public void setIsPartial(Boolean isPartial) {
		this.isPartial = isPartial;
	}

	/**
	 * @return the villagesList
	 */
	public List<SelectOptionVO> getVillagesList() {
		return villagesList;
	}

	/**
	 * @param villagesList the villagesList to set
	 */
	public void setVillagesList(List<SelectOptionVO> villagesList) {
		this.villagesList = villagesList;
	}

	/**
	 * @return the mandalForTown
	 */
	public String getMandalForTown() {
		return mandalForTown;
	}

	/**
	 * @param mandalForTown the mandalForTown to set
	 */
	public void setMandalForTown(String mandalForTown) {
		this.mandalForTown = mandalForTown;
	} 

}
