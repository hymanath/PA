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

@Entity
@Table(name = "mobile_app_user_access_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileAppUserAccessLocation {
	
	private Long mobileAppUserAccessLocationId;
	private Long mobileAppUserId;
	private MobileAppUser mobileAppUser;
	private Long locationLevelId;
	private Long locationValue;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mobile_app_user_access_location_id", unique = true, nullable = false)
	public Long getMobileAppUserAccessLocationId() {
		return mobileAppUserAccessLocationId;
	}
	public void setMobileAppUserAccessLocationId(Long mobileAppUserAccessLocationId) {
		this.mobileAppUserAccessLocationId = mobileAppUserAccessLocationId;
	}
	@Column(name = "mobile_app_user_id")
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}
	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "mobile_app_user_id",insertable=false, updatable=false)
	public MobileAppUser getMobileAppUser() {
		return mobileAppUser;
	}
	public void setMobileAppUser(MobileAppUser mobileAppUser) {
		this.mobileAppUser = mobileAppUser;
	}
	@Column(name = "location_level_id")
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	

}
