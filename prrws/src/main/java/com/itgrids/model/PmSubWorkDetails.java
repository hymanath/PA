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

@Entity
@Table(name = "pm_sub_work_details")
public class PmSubWorkDetails {

	private Long pmSubWorkDetailsId;
	private Long petitionId;
	private Long costEstimation;
	private Long pmDepartmentId;
	private Long pmSubjectId;
	private Long pmSubSubjectId;
	private Long locationScopeId;
	private Long locationValue;
	private String grievanceDescrption;
	private Long addressId;
	private String isDeleted;
	private Long pmLeadId;
	private Long pmBriefLeadId;
	private Long pmGrantId;
	private Long pmStatusId;
	private String coveringLetterPath;
	private Long pmWorkTypeId;
	
	private PmWorkType pmWorkType;
    private User insertedUser;
	private User updatedUser;
	private Date insertedTime;
	private Date updatedTime;
	
	private Petition petition;
	private PmDepartment pmDepartment;
	private PmSubject pmSubject;
	private PmLead pmLead;
	private PmBriefLead pmBriefLead;
	private PmGrant pmGrant;
	private PmStatus pmStatus;
	private LocationAddress locationAddress;
	
	@Id
	@Column(name="pm_sub_work_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmSubWorkDetailsId() {
		return pmSubWorkDetailsId;
	}
	public void setPmSubWorkDetailsId(Long pmSubWorkDetailsId) {
		this.pmSubWorkDetailsId = pmSubWorkDetailsId;
	}
	@Column(name="petition_id")
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	@Column(name="cost_estimation")
	public Long getCostEstimation() {
		return costEstimation;
	}
	public void setCostEstimation(Long costEstimation) {
		this.costEstimation = costEstimation;
	}
	@Column(name="pm_department_id")
	public Long getPmDepartmentId() {
		return pmDepartmentId;
	}
	public void setPmDepartmentId(Long pmDepartmentId) {
		this.pmDepartmentId = pmDepartmentId;
	}
	@Column(name="pm_subject_id")
	public Long getPmSubjectId() {
		return pmSubjectId;
	}
	public void setPmSubjectId(Long pmSubjectId) {
		this.pmSubjectId = pmSubjectId;
	}
	@Column(name="pm_sub_subject_id")
	public Long getPmSubSubjectId() {
		return pmSubSubjectId;
	}
	public void setPmSubSubjectId(Long pmSubSubjectId) {
		this.pmSubSubjectId = pmSubSubjectId;
	}
	@Column(name="location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name="location_scope_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name="grievance_description")
	public String getGrievanceDescrption() {
		return grievanceDescrption;
	}
	public void setGrievanceDescrption(String grievanceDescrption) {
		this.grievanceDescrption = grievanceDescrption;
	}
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="pm_lead_id")
	public Long getPmLeadId() {
		return pmLeadId;
	}
	public void setPmLeadId(Long pmLeadId) {
		this.pmLeadId = pmLeadId;
	}
	@Column(name="pm_brief_lead_id")
	public Long getPmBriefLeadId() {
		return pmBriefLeadId;
	}
	public void setPmBriefLeadId(Long pmBriefLeadId) {
		this.pmBriefLeadId = pmBriefLeadId;
	}
	@Column(name="pm_grant_id")
	public Long getPmGrantId() {
		return pmGrantId;
	}
	public void setPmGrantId(Long pmGrantId) {
		this.pmGrantId = pmGrantId;
	}
	@Column(name="pm_status_id")
	public Long getPmStatusId() {
		return pmStatusId;
	}
	public void setPmStatusId(Long pmStatusId) {
		this.pmStatusId = pmStatusId;
	}
	@Column(name="covering_letter_path")
	public String getCoveringLetterPath() {
		return coveringLetterPath;
	}
	public void setCoveringLetterPath(String coveringLetterPath) {
		this.coveringLetterPath = coveringLetterPath;
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
	@JoinColumn(name = "pm_department_id", insertable = false, updatable = false)
	public PmDepartment getPmDepartment() {
		return pmDepartment;
	}
	public void setPmDepartment(PmDepartment pmDepartment) {
		this.pmDepartment = pmDepartment;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_subject_id", insertable = false, updatable = false)
	public PmSubject getPmSubject() {
		return pmSubject;
	}
	public void setPmSubject(PmSubject pmSubject) {
		this.pmSubject = pmSubject;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_lead_id", insertable = false, updatable = false)
	public PmLead getPmLead() {
		return pmLead;
	}
	public void setPmLead(PmLead pmLead) {
		this.pmLead = pmLead;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_brief_lead_id", insertable = false, updatable = false)
	public PmBriefLead getPmBriefLead() {
		return pmBriefLead;
	}
	public void setPmBriefLead(PmBriefLead pmBriefLead) {
		this.pmBriefLead = pmBriefLead;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_grant_id", insertable = false, updatable = false)
	public PmGrant getPmGrant() {
		return pmGrant;
	}
	public void setPmGrant(PmGrant pmGrant) {
		this.pmGrant = pmGrant;
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
	
	@Column(name="pm_work_type_id")
	public Long getPmWorkTypeId() {
		return pmWorkTypeId;
	}
	public void setPmWorkTypeId(Long pmWorkTypeId) {
		this.pmWorkTypeId = pmWorkTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_work_type_id", insertable = false, updatable = false)
	public PmWorkType getPmWorkType() {
		return pmWorkType;
	}
	public void setPmWorkType(PmWorkType pmWorkType) {
		this.pmWorkType = pmWorkType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	public LocationAddress getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}
}
