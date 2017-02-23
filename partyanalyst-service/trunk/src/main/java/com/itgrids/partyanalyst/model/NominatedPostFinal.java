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
@Table(name = "nominated_post_final")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominatedPostFinal extends BaseModel implements Serializable{

	private Long nominatedPostFinalId;
	private Long nominatedPostMemberId;
	private Long nominationPostCandidateId;
	private Long applicationStatusId;
	private Long insertedBy;
	private Date insertedTime;
	private Long updatedBy;
	private Date updatedTime;
	private String isDeleted;
	private String isPrefered;
	private Long nominatedPostId;
	private Long nominatedPostApplicationId;
	
	private NominatedPostMember nominatedPostMember;
	private NominationPostCandidate nominationPostCandidate;
	private ApplicationStatus applicationStatus;
	private NominatedPost nominatedPost;
	private NominatedPostApplication nominatedPostApplication;
	private String  isExpired;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_final_id", unique = true, nullable = false)
	public Long getNominatedPostFinalId() {
		return nominatedPostFinalId;
	}
	public void setNominatedPostFinalId(Long nominatedPostFinalId) {
		this.nominatedPostFinalId = nominatedPostFinalId;
	}
	
	@Column(name="nominated_post_member_id")
	public Long getNominatedPostMemberId() {
		return nominatedPostMemberId;
	}
	public void setNominatedPostMemberId(Long nominatedPostMemberId) {
		this.nominatedPostMemberId = nominatedPostMemberId;
	}
	
	@Column(name = "nomination_post_candidate_id")
	public Long getNominationPostCandidateId() {
		return nominationPostCandidateId;
	}
	public void setNominationPostCandidateId(Long nominationPostCandidateId) {
		this.nominationPostCandidateId = nominationPostCandidateId;
	}
	
	@Column(name="application_status_id")
	public Long getApplicationStatusId() {
		return applicationStatusId;
	}
	public void setApplicationStatusId(Long applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nominated_post_member_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominatedPostMember getNominatedPostMember() {
		return nominatedPostMember;
	}
	public void setNominatedPostMember(NominatedPostMember nominatedPostMember) {
		this.nominatedPostMember = nominatedPostMember;
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
	@JoinColumn(name="application_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	@Column(name="is_prefered")
	public String getIsPrefered() {
		return isPrefered;
	}
	public void setIsPrefered(String isPrefered) {
		this.isPrefered = isPrefered;
	}
	
	@Column(name="nominated_post_id")
	public Long getNominatedPostId() {
		return nominatedPostId;
	}
	public void setNominatedPostId(Long nominatedPostId) {
		this.nominatedPostId = nominatedPostId;
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
	@Column(name="nominated_post_application_id")
	public Long getNominatedPostApplicationId() {
		return nominatedPostApplicationId;
	}
	public void setNominatedPostApplicationId(Long nominatedPostApplicationId) {
		this.nominatedPostApplicationId = nominatedPostApplicationId;
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
	
	@Column(name="is_expired")
	public String getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
	}
	
}
