/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 8, 2010
 */
package com.itgrids.partyanalyst.dto;

public class HamletProblemVO {

	private Long problemId;
	private String problemDesc;
	private String identifiedDate;
	private String problemSource;
	private String problemStatus;
	private String year;
	
	public Long getProblemId() {
		return problemId;
	}
	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}
	public String getProblemDesc() {
		return problemDesc;
	}
	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}
	public String getIdentifiedDate() {
		return identifiedDate;
	}
	public void setIdentifiedDate(String identifiedDate) {
		this.identifiedDate = identifiedDate;
	}
	public String getProblemSource() {
		return problemSource;
	}
	public void setProblemSource(String problemSource) {
		this.problemSource = problemSource;
	}
	public String getProblemStatus() {
		return problemStatus;
	}
	public void setProblemStatus(String problemStatus) {
		this.problemStatus = problemStatus;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
}
