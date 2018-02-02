package com.itgrids.model;

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
@Table(name = "mobile_app_user_location")
public class MobileAppUserLocation {
	private Long mobileAppUserLocationId;
	private Long mobileAppUserId;
	private Long locationScopeId;
	private Long locationValue;
	private Long locationAddressId;
	private String isDeleted;
	
	private MobileAppUser mobileAppUser;
	private RegionScopes locationScope;
	private LocationAddress locationAddress;
	
	@Id
	@Column(name="mobile_app_user_location_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getMobileAppUserLocationId() {
		return mobileAppUserLocationId;
	}
	public void setMobileAppUserLocationId(Long mobileAppUserLocationId) {
		this.mobileAppUserLocationId = mobileAppUserLocationId;
	}
	
	@Column(name="mobile_app_user_id")
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}
	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
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
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "mobile_app_user_id", insertable = false, updatable = false)
	public MobileAppUser getMobileAppUser() {
		return mobileAppUser;
	}
	public void setMobileAppUser(MobileAppUser mobileAppUser) {
		this.mobileAppUser = mobileAppUser;
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
