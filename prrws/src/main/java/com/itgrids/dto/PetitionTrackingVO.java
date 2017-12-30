package com.itgrids.dto;

public class PetitionTrackingVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long petitionId;
	private Long pmSubWorkDetailsId;
	private Long pmTrackingActionId;
	private Long pmStatusId;
	private String remarks;
	private Long documentId;
	private Long pmDeptDesignationOfficerId;
	private Long userId;
		
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	public Long getPmSubWorkDetailsId() {
		return pmSubWorkDetailsId;
	}
	public void setPmSubWorkDetailsId(Long pmSubWorkDetailsId) {
		this.pmSubWorkDetailsId = pmSubWorkDetailsId;
	}
	public Long getPmTrackingActionId() {
		return pmTrackingActionId;
	}
	public void setPmTrackingActionId(Long pmTrackingActionId) {
		this.pmTrackingActionId = pmTrackingActionId;
	}
	public Long getPmStatusId() {
		return pmStatusId;
	}
	public void setPmStatusId(Long pmStatusId) {
		this.pmStatusId = pmStatusId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public Long getPmDeptDesignationOfficerId() {
		return pmDeptDesignationOfficerId;
	}
	public void setPmDeptDesignationOfficerId(Long pmDeptDesignationOfficerId) {
		this.pmDeptDesignationOfficerId = pmDeptDesignationOfficerId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
