package com.itgrids.partyanalyst.dto;

public class GrivenceStatusVO {
	
	private Long notVerified=0l;
	private Long inProgress=0l;
	private Long notEligible=0l;
	private Long notPossible=0l;
	private Long approves=0l;
	private Long completed=0l;
	
	public Long getNotVerified() {
		return notVerified;
	}
	public void setNotVerified(Long notVerified) {
		this.notVerified = notVerified;
	}
	public Long getInProgress() {
		return inProgress;
	}
	public void setInProgress(Long inProgress) {
		this.inProgress = inProgress;
	}
	public Long getNotEligible() {
		return notEligible;
	}
	public void setNotEligible(Long notEligible) {
		this.notEligible = notEligible;
	}
	public Long getNotPossible() {
		return notPossible;
	}
	public void setNotPossible(Long notPossible) {
		this.notPossible = notPossible;
	}
	public Long getApproves() {
		return approves;
	}
	public void setApproves(Long approves) {
		this.approves = approves;
	}
	public Long getCompleted() {
		return completed;
	}
	public void setCompleted(Long completed) {
		this.completed = completed;
	}
	
}
