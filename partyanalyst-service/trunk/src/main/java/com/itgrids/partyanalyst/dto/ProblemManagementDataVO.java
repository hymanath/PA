/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 8, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ProblemManagementDataVO {

	private List<HamletProblemVO> hamletProblems;
	private ResultStatus resultStatus;
	private List<ProblemBeanVO> newProblems;
	private ProblemBeanVO problemBeanVO;
	
	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}
	public void setProblemBeanVO(ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}
	public List<HamletProblemVO> getHamletProblems() {
		return hamletProblems;
	}
	public void setHamletProblems(List<HamletProblemVO> hamletProblems) {
		this.hamletProblems = hamletProblems;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<ProblemBeanVO> getNewProblems() {
		return newProblems;
	}
	public void setNewProblems(List<ProblemBeanVO> newProblems) {
		this.newProblems = newProblems;
	}
	
}
