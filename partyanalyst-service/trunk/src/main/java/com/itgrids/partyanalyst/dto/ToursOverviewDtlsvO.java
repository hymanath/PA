package com.itgrids.partyanalyst.dto;


public class ToursOverviewDtlsvO {

	private Long totalCandiateCount = 0l;
	private Long submittedCandiateCount = 0l;
	private Long notSubmittedCandidateCount = 0l;
	private Double submittedPer;
	private Double notSubmittedPer;
	private Long tdpCadreId;
	private String memberShipNo;
	private String name;
	private String moblieNo;
	private String designation;
	
	public Long getTotalCandiateCount() {
		return totalCandiateCount;
	}
	public void setTotalCandiateCount(Long totalCandiateCount) {
		this.totalCandiateCount = totalCandiateCount;
	}
	public Long getSubmittedCandiateCount() {
		return submittedCandiateCount;
	}
	public void setSubmittedCandiateCount(Long submittedCandiateCount) {
		this.submittedCandiateCount = submittedCandiateCount;
	}
	public Long getNotSubmittedCandidateCount() {
		return notSubmittedCandidateCount;
	}
	public void setNotSubmittedCandidateCount(Long notSubmittedCandidateCount) {
		this.notSubmittedCandidateCount = notSubmittedCandidateCount;
	}
	public Double getSubmittedPer() {
		return submittedPer;
	}
	public void setSubmittedPer(Double submittedPer) {
		this.submittedPer = submittedPer;
	}
	public Double getNotSubmittedPer() {
		return notSubmittedPer;
	}
	public void setNotSubmittedPer(Double notSubmittedPer) {
		this.notSubmittedPer = notSubmittedPer;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoblieNo() {
		return moblieNo;
	}
	public void setMoblieNo(String moblieNo) {
		this.moblieNo = moblieNo;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	
	
	
	
}
