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
@Table(name="self_appraisal_candidate_details_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalCandidateDetailsLocation {

	private Long selfAppraisalCandidateDetailsLocationId;
	private Long selfAppraisalCandidateDetailsNewId;
	private Long locationScopeId;
	private Long locationValue;
	private Long addressId;
	
	private SelfAppraisalCandidateDetailsNew selfAppraisalCandidateDetailsNew;
	private UserAddress address;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_candidate_details_location_id", unique=true, nullable=false)
	public Long getSelfAppraisalCandidateDetailsLocationId() {
		return selfAppraisalCandidateDetailsLocationId;
	}
	public void setSelfAppraisalCandidateDetailsLocationId(
			Long selfAppraisalCandidateDetailsLocationId) {
		this.selfAppraisalCandidateDetailsLocationId = selfAppraisalCandidateDetailsLocationId;
	}
	@Column(name="self_appraisal_candidate_details_new_id")
	public Long getSelfAppraisalCandidateDetailsNewId() {
		return selfAppraisalCandidateDetailsNewId;
	}
	public void setSelfAppraisalCandidateDetailsNewId(
			Long selfAppraisalCandidateDetailsNewId) {
		this.selfAppraisalCandidateDetailsNewId = selfAppraisalCandidateDetailsNewId;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_candidate_details_new_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalCandidateDetailsNew getSelfAppraisalCandidateDetailsNew() {
		return selfAppraisalCandidateDetailsNew;
	}
	public void setSelfAppraisalCandidateDetailsNew(
			SelfAppraisalCandidateDetailsNew selfAppraisalCandidateDetailsNew) {
		this.selfAppraisalCandidateDetailsNew = selfAppraisalCandidateDetailsNew;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="address_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
	
	
}
