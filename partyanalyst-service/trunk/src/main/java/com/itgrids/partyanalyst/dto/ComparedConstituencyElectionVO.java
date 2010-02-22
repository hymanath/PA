/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 19,2010.
 */
package com.itgrids.partyanalyst.dto;

import com.itgrids.partyanalyst.model.ConstituencyElection;

public class ComparedConstituencyElectionVO {

	private String yearOne;
	private String yearTwo;
	private ConstituencyElection constiElecForYearOne;
	private ConstituencyElection constiElecForYearTwo;
	
	
	public String getYearOne() {
		return yearOne;
	}
	public void setYearOne(String yearOne) {
		this.yearOne = yearOne;
	}
	public String getYearTwo() {
		return yearTwo;
	}
	public void setYearTwo(String yearTwo) {
		this.yearTwo = yearTwo;
	}
	public ConstituencyElection getConstiElecForYearOne() {
		return constiElecForYearOne;
	}
	public void setConstiElecForYearOne(ConstituencyElection constiElecForYearOne) {
		this.constiElecForYearOne = constiElecForYearOne;
	}
	public ConstituencyElection getConstiElecForYearTwo() {
		return constiElecForYearTwo;
	}
	public void setConstiElecForYearTwo(ConstituencyElection constiElecForYearTwo) {
		this.constiElecForYearTwo = constiElecForYearTwo;
	}
}
