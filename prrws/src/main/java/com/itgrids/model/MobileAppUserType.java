package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mobile_app_user_type")
public class MobileAppUserType {
	
	private String mobileAppUserTypeId;
	private String userType;
	private String description;
	
	@Column(name="mobile_app_user_type_id")
	public String getMobileAppUserTypeId() {
		return mobileAppUserTypeId;
	}
	public void setMobileAppUserTypeId(String mobileAppUserTypeId) {
		this.mobileAppUserTypeId = mobileAppUserTypeId;
	}
	
	@Column(name="user_type")
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
