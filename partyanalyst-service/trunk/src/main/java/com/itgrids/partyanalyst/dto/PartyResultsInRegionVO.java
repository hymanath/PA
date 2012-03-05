/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Febrauary 25, 2011
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sai Krishna
 *
 */
public class PartyResultsInRegionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long regionId;
	private String regionName;
	private Long constituenciesCount;
	
	private List<PartyResultsVO> partyResultsInRegion;
	
	private ResultStatus rs;

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<PartyResultsVO> getPartyResultsInRegion() {
		return partyResultsInRegion;
	}

	public void setPartyResultsInRegion(List<PartyResultsVO> partyResultsInRegion) {
		this.partyResultsInRegion = partyResultsInRegion;
	}

	public ResultStatus getRs() {
		return rs;
	}

	public void setRs(ResultStatus rs) {
		this.rs = rs;
	}

	public void setConstituenciesCount(Long constituenciesCount) {
		this.constituenciesCount = constituenciesCount;
	}

	public Long getConstituenciesCount() {
		return constituenciesCount;
	}
	
	

}
