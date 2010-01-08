/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 2, 2010
 */
package com.itgrids.partyanalyst.excel.problem;

import java.util.Date;

public class HamletProblemVO {

	private Long problemId;
	private String problemType;
	private String problemDesc;
	private String problemClassification;
	private Date identifiedDate;
	
	//No Argument Constructor
	public HamletProblemVO(){
		
	}

	// Getters And Setters
	public Long getProblemId() {
		return problemId;
	}

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	public String getProblemClassification() {
		return problemClassification;
	}

	public void setProblemClassification(String problemClassification) {
		this.problemClassification = problemClassification;
	}

	public Date getIdentifiedDate() {
		return identifiedDate;
	}

	public void setIdentifiedDate(Date identifiedDate) {
		this.identifiedDate = identifiedDate;
	}
	
	
	
	
	
}
