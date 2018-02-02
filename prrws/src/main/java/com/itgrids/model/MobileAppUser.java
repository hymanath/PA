package com.itgrids.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mobile_app_user")
public class MobileAppUser {

	private Long mobileAppUserId;
	private String fullName;
	private String username;
	private String password;
	private String mobileNo;
	private Long mobileAppUserTypeId;
	private String isEnabled;
	private String isDeleted;
	
	private  MobileAppUserType mobileAppUserType;

	@Column(name="mobile_app_user_id")
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}

	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
	}

	@Column(name="full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name="username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name="mobile_app_user_type_id")
	public Long getMobileAppUserTypeId() {
		return mobileAppUserTypeId;
	}

	public void setMobileAppUserTypeId(Long mobileAppUserTypeId) {
		this.mobileAppUserTypeId = mobileAppUserTypeId;
	}

	@Column(name="is_enabled")
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "mobile_app_user_type_id", insertable = false, updatable = false)
	public MobileAppUserType getMobileAppUserType() {
		return mobileAppUserType;
	}

	public void setMobileAppUserType(MobileAppUserType mobileAppUserType) {
		this.mobileAppUserType = mobileAppUserType;
	}
	
	
}
