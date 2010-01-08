/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 2, 2010
 */
package com.itgrids.partyanalyst.excel.problem;

import java.util.List;

public class HamletProblemUploadVO {

	private Long hamletId;
	private String hamletName;
	private String panchayatName;
	private List<HamletProblemVO> hamletProblems;
	
	//No Argument Constructor
	public HamletProblemUploadVO(){
		
	}

	// Getters And Setters
	public Long getHamletId() {
		return hamletId;
	}

	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}

	public String getHamletName() {
		return hamletName;
	}

	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public List<HamletProblemVO> getHamletProblems() {
		return hamletProblems;
	}

	public void setHamletProblems(List<HamletProblemVO> hamletProblems) {
		this.hamletProblems = hamletProblems;
	}
	
	
}
