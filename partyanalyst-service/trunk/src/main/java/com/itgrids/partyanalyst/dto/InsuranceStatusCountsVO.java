package com.itgrids.partyanalyst.dto;

public class InsuranceStatusCountsVO {
	
	private Long waitingForDocs=0l;
	private Long submittedInparty=0l;
	private Long foreadedToInsurance=0l;
	private Long closedAtInsurance=0l;
	private Long closedAtParty=0l;
	private Long approved=0l;
	private Long closedLetters=0l;
	private Long accountRejected=0l;
	public Long getWaitingForDocs() {
		return waitingForDocs;
	}
	public void setWaitingForDocs(Long waitingForDocs) {
		this.waitingForDocs = waitingForDocs;
	}
	public Long getSubmittedInparty() {
		return submittedInparty;
	}
	public void setSubmittedInparty(Long submittedInparty) {
		this.submittedInparty = submittedInparty;
	}
	public Long getForeadedToInsurance() {
		return foreadedToInsurance;
	}
	public void setForeadedToInsurance(Long foreadedToInsurance) {
		this.foreadedToInsurance = foreadedToInsurance;
	}
	public Long getClosedAtInsurance() {
		return closedAtInsurance;
	}
	public void setClosedAtInsurance(Long closedAtInsurance) {
		this.closedAtInsurance = closedAtInsurance;
	}
	public Long getClosedAtParty() {
		return closedAtParty;
	}
	public void setClosedAtParty(Long closedAtParty) {
		this.closedAtParty = closedAtParty;
	}
	public Long getApproved() {
		return approved;
	}
	public void setApproved(Long approved) {
		this.approved = approved;
	}
	public Long getClosedLetters() {
		return closedLetters;
	}
	public void setClosedLetters(Long closedLetters) {
		this.closedLetters = closedLetters;
	}
	public Long getAccountRejected() {
		return accountRejected;
	}
	public void setAccountRejected(Long accountRejected) {
		this.accountRejected = accountRejected;
	}
	
}
