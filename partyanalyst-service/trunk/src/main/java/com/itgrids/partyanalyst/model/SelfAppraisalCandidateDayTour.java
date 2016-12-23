package com.itgrids.partyanalyst.model;

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
@Table(name="self_appraisal_candidate_day_tour")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalCandidateDayTour {
    /*
     * Author : santosh
     */
	private Long selfAppraisalCandidateDayTourId;
	private Long selfAppraisalCandidateId;
	private Long selfAppraisalDesignationId;
	private Long selfAppraisalTourCategoryId; 
	private Date tourDate;
	private Long locationScopeId;
	private Long locationValue;
	private Long addressId;
	private Long tourTypeId;
	private String comment;
	private Date insertedTime;
	private Date updatedTime;
	private Long insertedBy;
	private Long updatedBy;
	private String isDeleted;
	
	private SelfAppraisalCandidate selfAppraisalCandidate;
	private SelfAppraisalDesignation selfAppraisalDesignation;
	private SelfAppraisalTourCategory selfAppraisalTourCategory;
	private TourType tourType;
	private UserAddress userAddress;
	private User insertedUser;
	private User updatedUser;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_candidate_day_tour_id", unique=true, nullable=false)
	public Long getSelfAppraisalCandidateDayTourId() {
		return selfAppraisalCandidateDayTourId;
	}
	public void setSelfAppraisalCandidateDayTourId(
			Long selfAppraisalCandidateDayTourId) {
		this.selfAppraisalCandidateDayTourId = selfAppraisalCandidateDayTourId;
	}
	@Column(name="self_appraisal_candidate_id")
	public Long getSelfAppraisalCandidateId() {
		return selfAppraisalCandidateId;
	}
	public void setSelfAppraisalCandidateId(Long selfAppraisalCandidateId) {
		this.selfAppraisalCandidateId = selfAppraisalCandidateId;
	}
	@Column(name="self_appraisal_designation_id")
	public Long getSelfAppraisalDesignationId() {
		return selfAppraisalDesignationId;
	}
	public void setSelfAppraisalDesignationId(Long selfAppraisalDesignationId) {
		this.selfAppraisalDesignationId = selfAppraisalDesignationId;
	}
	@Column(name="self_appraisal_tour_category_id")
	public Long getSelfAppraisalTourCategoryId() {
		return selfAppraisalTourCategoryId;
	}
	public void setSelfAppraisalTourCategoryId(Long selfAppraisalTourCategoryId) {
		this.selfAppraisalTourCategoryId = selfAppraisalTourCategoryId;
	}
	@Column(name="tour_date")
	public Date getTourDate() {
		return tourDate;
	}
	public void setTourDate(Date tourDate) {
		this.tourDate = tourDate;
	}
	@Column(name="location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@Column(name="tour_type_id")
	public Long getTourTypeId() {
		return tourTypeId;
	}
	public void setTourTypeId(Long tourTypeId) {
		this.tourTypeId = tourTypeId;
	}
	@Column(name="comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_candidate_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalCandidate getSelfAppraisalCandidate() {
		return selfAppraisalCandidate;
	}
	public void setSelfAppraisalCandidate(
			SelfAppraisalCandidate selfAppraisalCandidate) {
		this.selfAppraisalCandidate = selfAppraisalCandidate;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_designation_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalDesignation getSelfAppraisalDesignation() {
		return selfAppraisalDesignation;
	}
	public void setSelfAppraisalDesignation(
			SelfAppraisalDesignation selfAppraisalDesignation) {
		this.selfAppraisalDesignation = selfAppraisalDesignation;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_tour_category_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalTourCategory getSelfAppraisalTourCategory() {
		return selfAppraisalTourCategory;
	}
	public void setSelfAppraisalTourCategory(
			SelfAppraisalTourCategory selfAppraisalTourCategory) {
		this.selfAppraisalTourCategory = selfAppraisalTourCategory;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="address_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tour_type_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TourType getTourType() {
		return tourType;
	}
	public void setTourType(TourType tourType) {
		this.tourType = tourType;
	}
	
	
}
