package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ProblemManagementVO {

	private List<ProblemDetailsVO> problemDetails; 
	private List<CategorizedProblemVO> categorizedProblems;
	private List<AssignedProblemVO> assignedProblems;
	private List<FixedProblemVO> fixedProblems;
	
	public List<ProblemDetailsVO> getProblemDetails() {
		return problemDetails;
	}
	public void setProblemDetails(List<ProblemDetailsVO> problemDetails) {
		this.problemDetails = problemDetails;
	}
	public List<CategorizedProblemVO> getCategorizedProblems() {
		return categorizedProblems;
	}
	public void setCategorizedProblems(
			List<CategorizedProblemVO> categorizedProblems) {
		this.categorizedProblems = categorizedProblems;
	}
	public List<AssignedProblemVO> getAssignedProblems() {
		return assignedProblems;
	}
	public void setAssignedProblems(List<AssignedProblemVO> assignedProblems) {
		this.assignedProblems = assignedProblems;
	}
	public List<FixedProblemVO> getFixedProblems() {
		return fixedProblems;
	}
	public void setFixedProblems(List<FixedProblemVO> fixedProblems) {
		this.fixedProblems = fixedProblems;
	}
	
	
}
