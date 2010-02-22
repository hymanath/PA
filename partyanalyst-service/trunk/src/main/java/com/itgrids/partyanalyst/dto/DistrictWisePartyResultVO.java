/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 15,2010.
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class DistrictWisePartyResultVO {

	private Long districtId;
	private String districtName;
	private Long totalConstituencies;
	private Long stateId;
	private String stateName;
	private List<PartyResultVO> partyElectionResultsList;
	
	
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getTotalConstituencies() {
		return totalConstituencies;
	}
	public void setTotalConstituencies(Long totalConstituencies) {
		this.totalConstituencies = totalConstituencies;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public List<PartyResultVO> getPartyElectionResultsList() {
		return partyElectionResultsList;
	}
	public void setPartyElectionResultsList(
			List<PartyResultVO> partyElectionResultsList) {
		this.partyElectionResultsList = partyElectionResultsList;
	}
	
	
}
