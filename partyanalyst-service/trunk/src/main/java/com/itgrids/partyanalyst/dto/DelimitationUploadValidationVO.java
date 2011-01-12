/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 12,2011
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sai Krishna
 *
 */
public class DelimitationUploadValidationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String state;
	private String district;
	private String constituency;
	private Integer existingMandalsCount;
	private Integer mandalsCountToSave;
	private List<SelectOptionVO> existingMandals;
	private List<SelectOptionVO> mandalsToSave;
		
	private Boolean canSave;

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the constituency
	 */
	public String getConstituency() {
		return constituency;
	}

	/**
	 * @param constituency the constituency to set
	 */
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	/**
	 * @return the existingMandals
	 */
	public List<SelectOptionVO> getExistingMandals() {
		return existingMandals;
	}

	/**
	 * @param existingMandals the existingMandals to set
	 */
	public void setExistingMandals(List<SelectOptionVO> existingMandals) {
		this.existingMandals = existingMandals;
	}

	/**
	 * @return the mandalsToSave
	 */
	public List<SelectOptionVO> getMandalsToSave() {
		return mandalsToSave;
	}

	/**
	 * @param mandalsToSave the mandalsToSave to set
	 */
	public void setMandalsToSave(List<SelectOptionVO> mandalsToSave) {
		this.mandalsToSave = mandalsToSave;
	}

	/**
	 * @return the canSave
	 */
	public Boolean getCanSave() {
		return canSave;
	}

	/**
	 * @param canSave the canSave to set
	 */
	public void setCanSave(Boolean canSave) {
		this.canSave = canSave;
	}

	/**
	 * @return the existingMandalsCount
	 */
	public Integer getExistingMandalsCount() {
		return existingMandalsCount;
	}

	/**
	 * @param existingMandalsCount the existingMandalsCount to set
	 */
	public void setExistingMandalsCount(Integer existingMandalsCount) {
		this.existingMandalsCount = existingMandalsCount;
	}

	/**
	 * @return the mandalsCountToSave
	 */
	public Integer getMandalsCountToSave() {
		return mandalsCountToSave;
	}

	/**
	 * @param mandalsCountToSave the mandalsCountToSave to set
	 */
	public void setMandalsCountToSave(Integer mandalsCountToSave) {
		this.mandalsCountToSave = mandalsCountToSave;
	}
 
}
