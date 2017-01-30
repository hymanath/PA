package com.itgrids.partyanalyst.model;

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
@Table(name="self_appraisal_candidate_location_new")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalCandidateLocationNew {

	private Long selfAppraisalCandidateLocationNewId;
	private Long selfAppraisalCandidateId;
	private Long locationScopeId;
	private Long locationValue;
	private Long selfAppraisalTourCategoryId;
	private Long addressId;
	private Long tourTypeId;
	private String isDeleted;
	
	private SelfAppraisalCandidate selfAppraisalCandidate;
	private SelfAppraisalTourCategory selfAppraisalTourCategory;
	private UserAddress userAddress;
	private TourType tourType;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_candidate_location_new_id", unique=true, nullable=false)
	public Long getSelfAppraisalCandidateLocationNewId() {
		return selfAppraisalCandidateLocationNewId;
	}
	public void setSelfAppraisalCandidateLocationNewId(Long selfAppraisalCandidateLocationNewId) {
		this.selfAppraisalCandidateLocationNewId = selfAppraisalCandidateLocationNewId;
	}
	@Column(name="self_appraisal_candidate_id")
	public Long getSelfAppraisalCandidateId() {
		return selfAppraisalCandidateId;
	}
	public void setSelfAppraisalCandidateId(Long selfAppraisalCandidateId) {
		this.selfAppraisalCandidateId = selfAppraisalCandidateId;
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
	@Column(name="self_appraisal_tour_category_id")
	public Long getSelfAppraisalTourCategoryId() {
		return selfAppraisalTourCategoryId;
	}
	public void setSelfAppraisalTourCategoryId(Long selfAppraisalTourCategoryId) {
		this.selfAppraisalTourCategoryId = selfAppraisalTourCategoryId;
	}
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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
	@JoinColumn(name="self_appraisal_tour_category_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalTourCategory getSelfAppraisalTourCategory() {
		return selfAppraisalTourCategory;
	}
	public void setSelfAppraisalTourCategory(SelfAppraisalTourCategory selfAppraisalTourCategory) {
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
	@Column(name="tour_type_id")
	public Long getTourTypeId() {
		return tourTypeId;
	}
	public void setTourTypeId(Long tourTypeId) {
		this.tourTypeId = tourTypeId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
