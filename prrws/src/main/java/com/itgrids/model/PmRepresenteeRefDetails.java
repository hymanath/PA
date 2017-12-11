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
@Table(name = "pm_representee_ref_details")
public class PmRepresenteeRefDetails {
	private Long pmRepresenteeRefDetailsId;
	private Long petitionId;
	private Long pmRepresenteeId;
	private Long pmRepresenteeDesignationId;
	private Long pmRefCandidateId;
	private Long pmRefCandidateDesignationId;
	private String isDeleted;
	private Long orderNo;
	
    private User insertedUser;
	private User updatedUser;
	private Date insertedTime;
	private Date updatedTime;
	
	private Petition petition;
	private PmRepresentee pmRepresentee;
	private PmRefCandidate pmRefCandidate; 
	private PmRefCandidateDesignation pmRefCandidateDesignation;
	private PmRepresenteeDesignation pmRepresenteeDesignation;
	
	@Id
	@Column(name="pm_representee_ref_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmRepresenteeRefDetailsId() {
		return pmRepresenteeRefDetailsId;
	}
	public void setPmRepresenteeRefDetailsId(Long pmRepresenteeRefDetailsId) {
		this.pmRepresenteeRefDetailsId = pmRepresenteeRefDetailsId;
	}
	@Column(name="petition_id")
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	@Column(name="pm_representee_id")
	public Long getPmRepresenteeId() {
		return pmRepresenteeId;
	}
	public void setPmRepresenteeId(Long pmRepresenteeId) {
		this.pmRepresenteeId = pmRepresenteeId;
	}
	@Column(name="pm_ref_candidate_id")
	public Long getPmRefCandidateId() {
		return pmRefCandidateId;
	}
	public void setPmRefCandidateId(Long pmRefCandidateId) {
		this.pmRefCandidateId = pmRefCandidateId;
	}
	@Column(name="pm_ref_cand_designation_id")
	public Long getPmRefCandidateDesignationId() {
		return pmRefCandidateDesignationId;
	}
	public void setPmRefCandidateDesignationId(Long pmRefCandidateDesignationId) {
		this.pmRefCandidateDesignationId = pmRefCandidateDesignationId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
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
	@JoinColumn(name = "pm_representee_id", insertable = false, updatable = false)
	public PmRepresentee getPmRepresentee() {
		return pmRepresentee;
	}
	public void setPmRepresentee(PmRepresentee pmRepresentee) {
		this.pmRepresentee = pmRepresentee;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_ref_candidate_id", insertable = false, updatable = false)
	public PmRefCandidate getPmRefCandidate() {
		return pmRefCandidate;
	}
	public void setPmRefCandidate(PmRefCandidate pmRefCandidate) {
		this.pmRefCandidate = pmRefCandidate;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_ref_cand_designation_id", insertable = false, updatable = false)
	public PmRefCandidateDesignation getPmRefCandidateDesignation() {
		return pmRefCandidateDesignation;
	}
	public void setPmRefCandidateDesignation(PmRefCandidateDesignation pmRefCandidateDesignation) {
		this.pmRefCandidateDesignation = pmRefCandidateDesignation;
	}
	
	@Column(name="pm_representtee_designation_id")
	public Long getPmRepresenteeDesignationId() {
		return pmRepresenteeDesignationId;
	}
	public void setPmRepresenteeDesignationId(Long pmRepresenteeDesignationId) {
		this.pmRepresenteeDesignationId = pmRepresenteeDesignationId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_representtee_designation_id", insertable = false, updatable = false)
	public PmRepresenteeDesignation getPmRepresenteeDesignation() {
		return pmRepresenteeDesignation;
	}
	public void setPmRepresenteeDesignation(
			PmRepresenteeDesignation pmRepresenteeDesignation) {
		this.pmRepresenteeDesignation = pmRepresenteeDesignation;
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
	
}
