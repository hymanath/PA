package com.itgrids.partyanalyst.dto;

import java.util.List;


public class ToursOverviewDtlsvO {

	private Long totalCandiateCount = 0l;
	private Long totalUniqueCandidateCount = 0l;
	private Long submittedCandiateCount = 0l;
	private Long notSubmittedCandidateCount = 0l;
	private Long uniqueCandidateSubmittedCount = 0l;
	private Long uniqueNotSubmittedCount = 0l;
	private Long before15thDateTourSubmittedCoun =0l;
	private Long after15thDateTourSubmittedCoun =0l;
	private Double submittedPer;
	private Double notSubmittedPer;
	private Long tdpCadreId;
	private String memberShipNo;
	private String name;
	private String moblieNo;
	private String designation;
	private AddressVO addressVO;
	private Long candidateId;
	private Long designationId;
	
	private List<ToursOverviewDtlsvO> subList1;
	private List<ToursOverviewDtlsvO> subList2;
	
	
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
	public Long getBefore15thDateTourSubmittedCoun() {
		return before15thDateTourSubmittedCoun;
	}
	public void setBefore15thDateTourSubmittedCoun(
			Long before15thDateTourSubmittedCoun) {
		this.before15thDateTourSubmittedCoun = before15thDateTourSubmittedCoun;
	}
	public Long getAfter15thDateTourSubmittedCoun() {
		return after15thDateTourSubmittedCoun;
	}
	public void setAfter15thDateTourSubmittedCoun(
			Long after15thDateTourSubmittedCoun) {
		this.after15thDateTourSubmittedCoun = after15thDateTourSubmittedCoun;
	}
	public List<ToursOverviewDtlsvO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<ToursOverviewDtlsvO> subList1) {
		this.subList1 = subList1;
	}
	public List<ToursOverviewDtlsvO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<ToursOverviewDtlsvO> subList2) {
		this.subList2 = subList2;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public Long getUniqueCandidateSubmittedCount() {
		return uniqueCandidateSubmittedCount;
	}
	public void setUniqueCandidateSubmittedCount(Long uniqueCandidateSubmittedCount) {
		this.uniqueCandidateSubmittedCount = uniqueCandidateSubmittedCount;
	}
	public Long getUniqueNotSubmittedCount() {
		return uniqueNotSubmittedCount;
	}
	public void setUniqueNotSubmittedCount(Long uniqueNotSubmittedCount) {
		this.uniqueNotSubmittedCount = uniqueNotSubmittedCount;
	}
	public Long getTotalUniqueCandidateCount() {
		return totalUniqueCandidateCount;
	}
	public void setTotalUniqueCandidateCount(Long totalUniqueCandidateCount) {
		this.totalUniqueCandidateCount = totalUniqueCandidateCount;
	}
}
