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
@Table(name = "mobile_app_user_access")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileAppUserAccess extends BaseModel implements Serializable{

	private Long mobileAppUserAccessId;
	
	private MobileAppUser mobileAppUser;
	
	private String isAuthorised;
	
	private String appId;
	
	private String macAddress;
	
	private String deviceId;
	/** default constructor */
	public MobileAppUserAccess()
	{
		
	}
	/** full constructor */
	public MobileAppUserAccess(Long mobileAppUserAccessId, MobileAppUser mobileAppUser,String isAuthorised,String appId,
			String macAddress,String deviceId)
	{
		
		this.mobileAppUserAccessId = mobileAppUserAccessId;
		this.mobileAppUser = mobileAppUser;
		this.isAuthorised = isAuthorised;
		this.appId =appId;
		this.macAddress = macAddress;
		this.deviceId = deviceId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mobile_app_user_access_id", unique = true, nullable = false)
	public Long getMobileAppUserAccessId() {
		return mobileAppUserAccessId;
	}

	public void setMobileAppUserAccessId(Long mobileAppUserAccessId) {
		this.mobileAppUserAccessId = mobileAppUserAccessId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="mobile_app_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MobileAppUser getMobileAppUser() {
		return mobileAppUser;
	}

	public void setMobileAppUser(MobileAppUser mobileAppUser) {
		this.mobileAppUser = mobileAppUser;
	}
	@Column(name = "is_authorised", length = 25)
	public String getIsAuthorised() {
		return isAuthorised;
	}

	public void setIsAuthorised(String isAuthorised) {
		this.isAuthorised = isAuthorised;
	}
	@Column(name = "app_id", length = 25)
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	@Column(name = "mac_address", length = 50)
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	@Column(name = "device_id", length = 50)
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	
}
