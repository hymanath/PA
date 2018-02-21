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
@Table(name = "govt_main_work")
public class GovtMainWork {
	
	private Long govtMainWorkId;
	private String govtMainWorkName;
	private Long govtWorkTypeId;
	private Double approvedKm;
	private Long locationScopeId;
	private Long locationValue;
	private Long locationAddressId;
	
	private GovtWorkType govtWorkType;
	private RegionScopes locationScope;
	private LocationAddress locationAddress;
	
	@Id
	@Column(name="govt_main_work_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getGovtMainWorkId() {
		return govtMainWorkId;
	}
	public void setGovtMainWorkId(Long govtMainWorkId) {
		this.govtMainWorkId = govtMainWorkId;
	}
	
	@Column(name="govt_main_work_name")
	public String getGovtMainWorkName() {
		return govtMainWorkName;
	}
	public void setGovtMainWorkName(String govtMainWorkName) {
		this.govtMainWorkName = govtMainWorkName;
	}
	
	@Column(name="govt_work_type_id")
	public Long getGovtWorkTypeId() {
		return govtWorkTypeId;
	}
	public void setGovtWorkTypeId(Long govtWorkTypeId) {
		this.govtWorkTypeId = govtWorkTypeId;
	}
	
	@Column(name="approved_km")
	public Double getApprovedKm() {
		return approvedKm;
	}
	public void setApprovedKm(Double approvedKm) {
		this.approvedKm = approvedKm;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_work_type_id", insertable = false, updatable = false)
	public GovtWorkType getGovtWorkType() {
		return govtWorkType;
	}
	public void setGovtWorkType(GovtWorkType govtWorkType) {
		this.govtWorkType = govtWorkType;
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
	
	@Column(name="location_address_id")
	public Long getLocationAddressId() {
		return locationAddressId;
	}
	public void setLocationAddressId(Long locationAddressId) {
		this.locationAddressId = locationAddressId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id", insertable = false, updatable = false)
	public RegionScopes getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(RegionScopes locationScope) {
		this.locationScope = locationScope;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_address_id", insertable = false, updatable = false)
	public LocationAddress getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}
}
