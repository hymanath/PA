package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "tdp_cadre_location_info_count")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreLocationInfoCount extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = -8436245443605379444L;
		
	
	private Long tdpCadreLocationInfoCountId;
	private Long locationScopeId;
	private Long locationValue;
	private Long enrollmentYearId;
	private Long cadreCount;
	private String cadrePercent;
	
	private EnrollmentYear enrollmentYear;
	private RegionScopes regionScopes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_location_info_count_id", unique = true, nullable = false)
	public Long getTdpCadreLocationInfoCountId() {
		return tdpCadreLocationInfoCountId;
	}
	public void setTdpCadreLocationInfoCountId(Long tdpCadreLocationInfoCountId) {
		this.tdpCadreLocationInfoCountId = tdpCadreLocationInfoCountId;
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
	
	@Column(name = "enrollment_year_id")
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}
	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}
	
	@Column(name = "cadre_count")
	public Long getCadreCount() {
		return cadreCount;
	}
	public void setCadreCount(Long cadreCount) {
		this.cadreCount = cadreCount;
	}
	
	@Column(name = "cadre_percent")
	public String getCadrePercent() {
		return cadrePercent;
	}
	public void setCadrePercent(String cadrePercent) {
		this.cadrePercent = cadrePercent;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="enrollment_year_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EnrollmentYear getEnrollmentYear() {
		return enrollmentYear;
	}
	public void setEnrollmentYear(EnrollmentYear enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="location_scope_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	
	
	
}
