/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 8, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ProblemManagementDataVO extends ResultStatus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<HamletProblemVO> hamletProblems;
	
	private ProblemsOfUserVO problemsOfUserVO;
	private ProblemBeanVO problemBeanVO;
	private List<ProblemBeanVO> classifiedProblems; 
	private List<SelectOptionVO> probConcernedDepts;
	private List<ProblemBeanVO> assignedProblems;
	private List<ProblemBeanVO> progressedProblems;
	private List<ProblemBeanVO> pendingProblems;
	private List<ProblemBeanVO> fixedProblems;
	private ResultStatus resultStatus;
	
	private Long totalResultsCount;
	
	public ProblemsOfUserVO getProblemsOfUserVO() {
		return problemsOfUserVO;
	}
	
	public void setProblemsOfUserVO(ProblemsOfUserVO problemsOfUserVO) {
		this.problemsOfUserVO = problemsOfUserVO;
	}
	
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

	public List<ProblemBeanVO> getClassifiedProblems() {
		return classifiedProblems;
	}

	public void setClassifiedProblems(List<ProblemBeanVO> classifiedProblems) {
		this.classifiedProblems = classifiedProblems;
	}

	public List<SelectOptionVO> getProbConcernedDepts() {
		return probConcernedDepts;
	}

	public void setProbConcernedDepts(List<SelectOptionVO> probConcernedDepts) {
		this.probConcernedDepts = probConcernedDepts;
	}

	public List<ProblemBeanVO> getAssignedProblems() {
		return assignedProblems;
	}

	public void setAssignedProblems(List<ProblemBeanVO> assignedProblems) {
		this.assignedProblems = assignedProblems;
	}

	public List<ProblemBeanVO> getProgressedProblems() {
		return progressedProblems;
	}

	public void setProgressedProblems(List<ProblemBeanVO> progressedProblems) {
		this.progressedProblems = progressedProblems;
	}

	public List<ProblemBeanVO> getPendingProblems() {
		return pendingProblems;
	}

	public void setPendingProblems(List<ProblemBeanVO> pendingProblems) {
		this.pendingProblems = pendingProblems;
	}

	public List<ProblemBeanVO> getFixedProblems() {
		return fixedProblems;
	}

	public void setFixedProblems(List<ProblemBeanVO> fixedProblems) {
		this.fixedProblems = fixedProblems;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public Long getTotalResultsCount() {
		return totalResultsCount;
	}

	public void setTotalResultsCount(Long totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}	
}
