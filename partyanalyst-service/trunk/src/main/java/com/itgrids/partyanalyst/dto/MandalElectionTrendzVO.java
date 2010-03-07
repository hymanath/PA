/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 06,2010
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class MandalElectionTrendzVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6762764336925274871L;

	private Long mandalId;
	private String mandalName;
	private List<ConstituencyWiseBoothsInfoVO> constiWiseBoothInfo;
	private List<ConstituencyWisePartyResultsForMandal> constiWisePartyResults;
	
	//getters and setters
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public List<ConstituencyWiseBoothsInfoVO> getConstiWiseBoothInfo() {
		return constiWiseBoothInfo;
	}
	public void setConstiWiseBoothInfo(
			List<ConstituencyWiseBoothsInfoVO> constiWiseBoothInfo) {
		this.constiWiseBoothInfo = constiWiseBoothInfo;
	}
	public List<ConstituencyWisePartyResultsForMandal> getConstiWisePartyResults() {
		return constiWisePartyResults;
	}
	public void setConstiWisePartyResults(
			List<ConstituencyWisePartyResultsForMandal> constiWisePartyResults) {
		this.constiWisePartyResults = constiWisePartyResults;
	}
	
}
