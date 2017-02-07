package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
	
}