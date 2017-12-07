package com.itgrids.model;

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
@Table(name = "petition_work_tracking")
public class PetitionWorkTracking {

	private Long petitionWorkTrackingId;
	private Long petitionWorkDetailsId ;
	private String workName;
	private Long petitionLeadId;
	private Long petitionBriefLeadId;
	private Long petitionGrantId ;
	private Long petitionStatusId;
	private Long noOfWorks ;
	private Long costEstimation;
	private String isPeviousPetition ;
	private String previousPetitionRefNo ;
	private String grievanceDescription;
	private String isDeleted;
	
	private PetitionWorkDetails petitionWorkDetails;
	private PetitionLead petitionLead;
	private PetitionBriefLead petitionBriefLead;
	private PetitionGrant petitionGrant;
	private PetitionStatus petitionStatus;
	
	
	@Id
	@Column(name="petition_work_tracking_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionWorkTrackingId() {
		return petitionWorkTrackingId;
	}
	public void setPetitionWorkTrackingId(Long petitionWorkTrackingId) {
		this.petitionWorkTrackingId = petitionWorkTrackingId;
	}
	@Column(name="petition_work_details_id")
	public Long getPetitionWorkDetailsId() {
		return petitionWorkDetailsId;
	}
	public void setPetitionWorkDetailsId(Long petitionWorkDetailsId) {
		this.petitionWorkDetailsId = petitionWorkDetailsId;
	}
	@Column(name="work_name")
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	@Column(name="petition_lead_id")
	public Long getPetitionLeadId() {
		return petitionLeadId;
	}
	public void setPetitionLeadId(Long petitionLeadId) {
		this.petitionLeadId = petitionLeadId;
	}
	@Column(name="petition_brief_lead_id")
	public Long getPetitionBriefLeadId() {
		return petitionBriefLeadId;
	}
	public void setPetitionBriefLeadId(Long petitionBriefLeadId) {
		this.petitionBriefLeadId = petitionBriefLeadId;
	}
	@Column(name="petition_grant_id")
	public Long getPetitionGrantId() {
		return petitionGrantId;
	}
	public void setPetitionGrantId(Long petitionGrantId) {
		this.petitionGrantId = petitionGrantId;
	}
	@Column(name="petition_status_id")
	public Long getPetitionStatusId() {
		return petitionStatusId;
	}
	public void setPetitionStatusId(Long petitionStatusId) {
		this.petitionStatusId = petitionStatusId;
	}
	@Column(name="no_of_works")
	public Long getNoOfWorks() {
		return noOfWorks;
	}
	public void setNoOfWorks(Long noOfWorks) {
		this.noOfWorks = noOfWorks;
	}
	@Column(name="cost_estimation")
	public Long getCostEstimation() {
		return costEstimation;
	}
	public void setCostEstimation(Long costEstimation) {
		this.costEstimation = costEstimation;
	}
	@Column(name="is_pevious_petition")
	public String getIsPeviousPetition() {
		return isPeviousPetition;
	}
	public void setIsPeviousPetition(String isPeviousPetition) {
		this.isPeviousPetition = isPeviousPetition;
	}
	@Column(name="previous_petition_ref_no")
	public String getPreviousPetitionRefNo() {
		return previousPetitionRefNo;
	}
	public void setPreviousPetitionRefNo(String previousPetitionRefNo) {
		this.previousPetitionRefNo = previousPetitionRefNo;
	}
	@Column(name="grievance_description")
	public String getGrievanceDescription() {
		return grievanceDescription;
	}
	public void setGrievanceDescription(String grievanceDescription) {
		this.grievanceDescription = grievanceDescription;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_work_details_id", insertable = false, updatable = false)
	public PetitionWorkDetails getPetitionWorkDetails() {
		return petitionWorkDetails;
	}
	public void setPetitionWorkDetails(PetitionWorkDetails petitionWorkDetails) {
		this.petitionWorkDetails = petitionWorkDetails;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_lead_id", insertable = false, updatable = false)
	public PetitionLead getPetitionLead() {
		return petitionLead;
	}
	public void setPetitionLead(PetitionLead petitionLead) {
		this.petitionLead = petitionLead;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_brief_lead_id", insertable = false, updatable = false)
	public PetitionBriefLead getPetitionBriefLead() {
		return petitionBriefLead;
	}
	public void setPetitionBriefLead(PetitionBriefLead petitionBriefLead) {
		this.petitionBriefLead = petitionBriefLead;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_grant_id", insertable = false, updatable = false)
	public PetitionGrant getPetitionGrant() {
		return petitionGrant;
	}
	public void setPetitionGrant(PetitionGrant petitionGrant) {
		this.petitionGrant = petitionGrant;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_status_id", insertable = false, updatable = false)
	public PetitionStatus getPetitionStatus() {
		return petitionStatus;
	}
	public void setPetitionStatus(PetitionStatus petitionStatus) {
		this.petitionStatus = petitionStatus;
	}
	
	
}
