package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;
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
	private Long statusId;
    private List<Long> petitionIdsList = new ArrayList<Long>();
    private List<Long> subworkIdsList = new ArrayList<Long>();
    private List<Long> deptIdsList = new ArrayList<Long>();
	private Long pmPetitionAssignedOfficerId;
	private Long assignedToPmPetitionAssignedOfficerId;
	private Long pmActionTypeId;
	private String actionType;
	private Long pmDocumentTypeId;
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public Long getPmActionTypeId() {
		return pmActionTypeId;
	}
	public void setPmActionTypeId(Long pmActionTypeId) {
		this.pmActionTypeId = pmActionTypeId;
	}
	public Long getAssignedToPmPetitionAssignedOfficerId() {
		return assignedToPmPetitionAssignedOfficerId;
	}
	public void setAssignedToPmPetitionAssignedOfficerId(
			Long assignedToPmPetitionAssignedOfficerId) {
		this.assignedToPmPetitionAssignedOfficerId = assignedToPmPetitionAssignedOfficerId;
	}
	public Long getPmPetitionAssignedOfficerId() {
		return pmPetitionAssignedOfficerId;
	}
	public void setPmPetitionAssignedOfficerId(Long pmPetitionAssignedOfficerId) {
		this.pmPetitionAssignedOfficerId = pmPetitionAssignedOfficerId;
	}
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
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public List<Long> getPetitionIdsList() {
		return petitionIdsList;
	}
	public void setPetitionIdsList(List<Long> petitionIdsList) {
		this.petitionIdsList = petitionIdsList;
	}
	public List<Long> getSubworkIdsList() {
		return subworkIdsList;
	}
	public void setSubworkIdsList(List<Long> subworkIdsList) {
		this.subworkIdsList = subworkIdsList;
	}
	public List<Long> getDeptIdsList() {
		return deptIdsList;
	}
	public void setDeptIdsList(List<Long> deptIdsList) {
		this.deptIdsList = deptIdsList;
	}
	
	
	public Long getPmDocumentTypeId() {
		return pmDocumentTypeId;
	}
	public void setPmDocumentTypeId(Long pmDocumentTypeId) {
		this.pmDocumentTypeId = pmDocumentTypeId;
	}
	
}
