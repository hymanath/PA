/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 06,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ConstituencyWiseBoothsInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8573509938318844597L;
	
	private Long constituencyId;
	private String constiName;
	private List<BoothTotalVotesVO> boothsDetails;
	
	//getters and setters
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstiName() {
		return constiName;
	}
	public void setConstiName(String constiName) {
		this.constiName = constiName;
	}
	public List<BoothTotalVotesVO> getBoothsDetails() {
		return boothsDetails;
	}
	public void setBoothsDetails(List<BoothTotalVotesVO> boothsDetails) {
		this.boothsDetails = boothsDetails;
	}

}
