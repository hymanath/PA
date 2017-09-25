package com.itgrids.partyanalyst.model;

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
@Table(name = "tdp_cadre_caste_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreCasteInfo implements java.io.Serializable{

	private Long tdpCadreCasteInfoId;
	private String locationType;
	private Long locationId;
	private Long casteStateId;
	private Long count;
	private Long casteCategoryId;
	private Long tdpCadreEnrollmentId;
	private VoterAgeRange voterAgeRange;
	private Long voterAgeRangeId;
	private String gender;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_caste_info_id", unique = true, nullable = false)
	public Long getTdpCadreCasteInfoId() {
		return tdpCadreCasteInfoId;
	}
	public void setTdpCadreCasteInfoId(Long tdpCadreCasteInfoId) {
		this.tdpCadreCasteInfoId = tdpCadreCasteInfoId;
	}
	
	@Column(name="caste_state_id")
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	
	@Column(name="caste_category_id")
	public Long getCasteCategoryId() {
		return casteCategoryId;
	}
	public void setCasteCategoryId(Long casteCategoryId) {
		this.casteCategoryId = casteCategoryId;
	}
	
	@Column(name="location_type")
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	@Column(name="location_id")
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	@Column(name="count")
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	@Column(name="tdp_cadre_enrollment_id")
	public Long getTdpCadreEnrollmentId() {
		return tdpCadreEnrollmentId;
	}
	public void setTdpCadreEnrollmentId(Long tdpCadreEnrollmentId) {
		this.tdpCadreEnrollmentId = tdpCadreEnrollmentId;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "voter_age_range_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterAgeRange getVoterAgeRange() {
		return voterAgeRange;
	}
	public void setVoterAgeRange(VoterAgeRange voterAgeRange) {
		this.voterAgeRange = voterAgeRange;
	}
	@Column(name="voter_age_range_id")
	public Long getVoterAgeRangeId() {
		return voterAgeRangeId;
	}
	public void setVoterAgeRangeId(Long voterAgeRangeId) {
		this.voterAgeRangeId = voterAgeRangeId;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}