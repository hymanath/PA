package com.itgrids.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author sys
 *
 */
@Entity
@Table(name = "pm_tracking")
public class PmTracking {

	
	private Long pmTrackingId;
	private Long petitionId;
	private Long pmSubWorkDetailsId;
	private Long pmStatusId;
	private Long pmDepartmentDesignationId;
	private Long pmTrackingActionId;
	private Long documentId;
	private Long pmDepartmentDesignationOfficerId;
	private Long assignedToPmDepartmentDesignationOfficerId;
	private Long insertedUserId;
	private Long updateUserId;
	private String remarks;
	private String isDeleted;
    private User insertedUser;
	private User updatedUser;
	private Date insertedTime;
	private Date updatedTime;
	private Long pmActionTypeId;
	private String actionType;
	
	 private Petition petition;
	 private PmSubWorkDetails pmSubWorkDetails;
	 private PmStatus pmStatus;
	 private PmDepartmentDesignation pmDepartmentDesignation;
	 
	 private PmTrackingAction PmTrackingAction;
	 private Document document;
	 private PmDepartmentDesignationOfficer pmDepartmentDesignationOfficer;
	 private PmDepartmentDesignationOfficer assignedToPmDepartmentDesignationOfficer;
	 private PmActionType pmActionType;
	 private Long pmDocumentTypeId;
	private PmDocumentType documentType;
	 
	@Id
	@Column(name="pm_tracking_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmTrackingId() {
		return pmTrackingId;
	}
	public void setPmTrackingId(Long pmTrackingId) {
		this.pmTrackingId = pmTrackingId;
	}
	@Column(name="petition_id")
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	@Column(name="pm_sub_work_details_id")
	public Long getPmSubWorkDetailsId() {
		return pmSubWorkDetailsId;
	}
	public void setPmSubWorkDetailsId(Long pmSubWorkDetailsId) {
		this.pmSubWorkDetailsId = pmSubWorkDetailsId;
	}
	@Column(name="pm_status_id")
	public Long getPmStatusId() {
		return pmStatusId;
	}
	public void setPmStatusId(Long pmStatusId) {
		this.pmStatusId = pmStatusId;
	}
	@Column(name="pm_dept_designation_id")
	public Long getPmDepartmentDesignationId() {
		return pmDepartmentDesignationId;
	}
	public void setPmDepartmentDesignationId(Long pmDepartmentDesignationId) {
		this.pmDepartmentDesignationId = pmDepartmentDesignationId;
	}
	@Column(name="pm_tracking_action_id")
	public Long getPmTrackingActionId() {
		return pmTrackingActionId;
	}
	public void setPmTrackingActionId(Long pmTrackingActionId) {
		this.pmTrackingActionId = pmTrackingActionId;
	}
	
	@Column(name="pm_dept_designation_officer_id")
	public Long getPmDepartmentDesignationOfficerId() {
		return pmDepartmentDesignationOfficerId;
	}
	public void setPmDepartmentDesignationOfficerId(Long pmDepartmentDesignationOfficerId) {
		this.pmDepartmentDesignationOfficerId = pmDepartmentDesignationOfficerId;
	}
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name="inserted_user_id")
	public Long getInsertedUserId() {
		return insertedUserId;
	}
	public void setInsertedUserId(Long insertedUserId) {
		this.insertedUserId = insertedUserId;
	}
	@Column(name="updated_user_id")
	public Long getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_id", insertable = false, updatable = false)
	public Petition getPetition() {
		return petition;
	}
	public void setPetition(Petition petition) {
		this.petition = petition;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_sub_work_details_id", insertable = false, updatable = false)
	public PmSubWorkDetails getPmSubWorkDetails() {
		return pmSubWorkDetails;
	}
	public void setPmSubWorkDetails(PmSubWorkDetails pmSubWorkDetails) {
		this.pmSubWorkDetails = pmSubWorkDetails;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_status_id", insertable = false, updatable = false)
	public PmStatus getPmStatus() {
		return pmStatus;
	}
	public void setPmStatus(PmStatus pmStatus) {
		this.pmStatus = pmStatus;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_dept_designation_id", insertable = false, updatable = false)
	public PmDepartmentDesignation getPmDepartmentDesignation() {
		return pmDepartmentDesignation;
	}
	public void setPmDepartmentDesignation(PmDepartmentDesignation pmDepartmentDesignation) {
		this.pmDepartmentDesignation = pmDepartmentDesignation;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_tracking_action_id", insertable = false, updatable = false)
	public PmTrackingAction getPmTrackingAction() {
		return PmTrackingAction;
	}
	public void setPmTrackingAction(PmTrackingAction pmTrackingAction) {
		PmTrackingAction = pmTrackingAction;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_dept_designation_officer_id", insertable = false, updatable = false)
	public PmDepartmentDesignationOfficer getPmDepartmentDesignationOfficer() {
		return pmDepartmentDesignationOfficer;
	}
	public void setPmDepartmentDesignationOfficer(PmDepartmentDesignationOfficer pmDepartmentDesignationOfficer) {
		this.pmDepartmentDesignationOfficer = pmDepartmentDesignationOfficer;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_user_id", insertable = false, updatable = false)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_user_id", insertable = false, updatable = false)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="document_id")
	public Long getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id", insertable = false, updatable = false)
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
	@Column(name="assigned_to_pm_petition_assigned_officer_id")
	public Long getAssignedToPmDepartmentDesignationOfficerId() {
		return assignedToPmDepartmentDesignationOfficerId;
	}
	public void setAssignedToPmDepartmentDesignationOfficerId(
			Long assignedToPmDepartmentDesignationOfficerId) {
		this.assignedToPmDepartmentDesignationOfficerId = assignedToPmDepartmentDesignationOfficerId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "assigned_to_pm_petition_assigned_officer_id", insertable = false, updatable = false)
	public PmDepartmentDesignationOfficer getAssignedToPmDepartmentDesignationOfficer() {
		return assignedToPmDepartmentDesignationOfficer;
	}
	public void setAssignedToPmDepartmentDesignationOfficer(
			PmDepartmentDesignationOfficer assignedToPmDepartmentDesignationOfficer) {
		this.assignedToPmDepartmentDesignationOfficer = assignedToPmDepartmentDesignationOfficer;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="pm_action_type_id")
	public Long getPmActionTypeId() {
		return pmActionTypeId;
	}
	public void setPmActionTypeId(Long pmActionTypeId) {
		this.pmActionTypeId = pmActionTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_action_type_id", insertable = false, updatable = false)
	public PmActionType getPmActionType() {
		return pmActionType;
	}
	public void setPmActionType(PmActionType pmActionType) {
		this.pmActionType = pmActionType;
	}
	
	@Column(name="action_type")
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	@Column(name="pm_document_type_id")
	public Long getPmDocumentTypeId() {
		return pmDocumentTypeId;
	}
	public void setPmDocumentTypeId(Long pmDocumentTypeId) {
		this.pmDocumentTypeId = pmDocumentTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_document_type_id", insertable = false, updatable = false)
	public PmDocumentType getDocumentType() {
		return documentType;
	}
	public void setDocumentType(PmDocumentType documentType) {
		this.documentType = documentType;
	}
	
	
}
