/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 07,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class DistrictWisePartyPositionsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5962915790838575759L;
	
	private Long partyId;
	private String partyName;
	
	private List<PartyPositionsInDistrictVO> partyResultsInDistricts;
	
	//getters and setters
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public List<PartyPositionsInDistrictVO> getPartyResultsInDistricts() {
		return partyResultsInDistricts;
	}
	public void setPartyResultsInDistricts(
			List<PartyPositionsInDistrictVO> partyResultsInDistricts) {
		this.partyResultsInDistricts = partyResultsInDistricts;
	}
	

}
