package com.itgrids.partyanalyst.dto;

public class InsuranceStatusCountsVO {
	
	private Long waitingForDocs;
	private Long submittedInparty;
	private Long foreadedToInsurance;
	private Long closedAtInsurance;
	private Long closedAtParty;
	private Long approved;
	private Long closedLetters;
	private Long accountRejected;
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
