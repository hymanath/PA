package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="tdp_cadre_enrollment_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreEnrollmentInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	private Long  tdpCadreEnrollmentInfoId;
	private Long  enrollmentYearId;
	private Long  stateId;
	private Long  constituencyId;
	private Date  surveyTime;
	private Long  totalCadre;
	private Long  renewalCadre;
	private Long  newCadre;
	private EnrollmentYear enrollmentYear;
	private Long locationScopeId;
	private Long locationValue;
	private RegionScopes regionScopes;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tdp_cadre_enrollment_info_id", unique=true, nullable=false)
	public Long getTdpCadreEnrollmentInfoId() {
		return tdpCadreEnrollmentInfoId;
	}
	public void setTdpCadreEnrollmentInfoId(Long tdpCadreEnrollmentInfoId) {
		this.tdpCadreEnrollmentInfoId = tdpCadreEnrollmentInfoId;
	}
	
	@Column(name = "enrollment_year_id")
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}
	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}
	
	@Column(name = "state_id")
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	@Column(name = "constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	@Column(name = "survey_time")
	public Date getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}
	
	@Column(name = "total_cadre")
	public Long getTotalCadre() {
		return totalCadre;
	}
	public void setTotalCadre(Long totalCadre) {
		this.totalCadre = totalCadre;
	}
	
	@Column(name = "renewal_cadre")
	public Long getRenewalCadre() {
		return renewalCadre;
	}
	public void setRenewalCadre(Long renewalCadre) {
		this.renewalCadre = renewalCadre;
	}
	
	@Column(name = "new_cadre")
	public Long getNewCadre() {
		return newCadre;
	}
	public void setNewCadre(Long newCadre) {
		this.newCadre = newCadre;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "enrollment_year_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EnrollmentYear getEnrollmentYear() {
		return enrollmentYear;
	}
	public void setEnrollmentYear(EnrollmentYear enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
	}
	@Column(name = "location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "location_scope_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	

}
