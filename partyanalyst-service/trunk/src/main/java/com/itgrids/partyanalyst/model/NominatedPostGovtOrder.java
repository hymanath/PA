package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "nominated_post_govt_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominatedPostGovtOrder extends BaseModel implements Serializable{

	private Long nominatedPostGovtOrderId;
	private Long nominatedPostId;
	private Long govtOrderId;
	private Long insertedBy;
	private Date insertedTime;
	private Long updatedBy;
	private Date updatedTime;
	private String isDeleted;
	private Long nominationPostCandidateId;
	private Long nominatedPostApplicationId;
	private NominatedPost nominatedPost;
	private GovtOrder govtOrder;
	private String  isExpired;

	private String deletedRemarks;
	private Long cadreDeletedReasonId;
	private CadreDeleteReason cadreDeleteReason;
	private NominationPostCandidate nominationPostCandidate;
	private NominatedPostApplication nominatedPostApplication;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_govt_order_id", unique = true, nullable = false)
	public Long getNominatedPostGovtOrderId() {
		return nominatedPostGovtOrderId;
	}
	public void setNominatedPostGovtOrderId(Long nominatedPostGovtOrderId) {
		this.nominatedPostGovtOrderId = nominatedPostGovtOrderId;
	}
	
	@Column(name="nominated_post_id")
	public Long getNominatedPostId() {
		return nominatedPostId;
	}
	public void setNominatedPostId(Long nominatedPostId) {
		this.nominatedPostId = nominatedPostId;
	}
	
	@Column(name="govt_order_id")
	public Long getGovtOrderId() {
		return govtOrderId;
	}
	public void setGovtOrderId(Long govtOrderId) {
		this.govtOrderId = govtOrderId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nominated_post_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominatedPost getNominatedPost() {
		return nominatedPost;
	}
	public void setNominatedPost(NominatedPost nominatedPost) {
		this.nominatedPost = nominatedPost;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_order_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtOrder getGovtOrder() {
		return govtOrder;
	}
	public void setGovtOrder(GovtOrder govtOrder) {
		this.govtOrder = govtOrder;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="is_expired")
	public String getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
	}
	@Column(name="deleted_remarks")
	public String getDeletedRemarks() {
		return deletedRemarks;
	}
	public void setDeletedRemarks(String deletedRemarks) {
		this.deletedRemarks = deletedRemarks;
	}
	@Column(name="cadre_delete_reason_id")
	public Long getCadreDeletedReasonId() {
		return cadreDeletedReasonId;
	}
	public void setCadreDeletedReasonId(Long cadreDeletedReasonId) {
		this.cadreDeletedReasonId = cadreDeletedReasonId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_delete_reason_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreDeleteReason getCadreDeleteReason() {
		return cadreDeleteReason;
	}
	public void setCadreDeleteReason(CadreDeleteReason cadreDeleteReason) {
		this.cadreDeleteReason = cadreDeleteReason;
	}
	
	@Column(name="nomination_post_candidate_id")
	public Long getNominationPostCandidateId() {
		return nominationPostCandidateId;
	}
	public void setNominationPostCandidateId(Long nominationPostCandidateId) {
		this.nominationPostCandidateId = nominationPostCandidateId;
	}
	
	@Column(name="nominated_post_application_id")
	public Long getNominatedPostApplicationId() {
		return nominatedPostApplicationId;
	}
	public void setNominatedPostApplicationId(Long nominatedPostApplicationId) {
		this.nominatedPostApplicationId = nominatedPostApplicationId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nomination_post_candidate_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominationPostCandidate getNominationPostCandidate() {
		return nominationPostCandidate;
	}
	public void setNominationPostCandidate(
			NominationPostCandidate nominationPostCandidate) {
		this.nominationPostCandidate = nominationPostCandidate;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nominated_post_application_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominatedPostApplication getNominatedPostApplication() {
		return nominatedPostApplication;
	}
	public void setNominatedPostApplication(
			NominatedPostApplication nominatedPostApplication) {
		this.nominatedPostApplication = nominatedPostApplication;
	}
	
	
	
}
