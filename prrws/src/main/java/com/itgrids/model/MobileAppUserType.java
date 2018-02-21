package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mobile_app_user_type")
public class MobileAppUserType {
	
	private Long mobileAppUserTypeId;
	private String userType;
	private String description;
	
	@Id
	@Column(name="mobile_app_user_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getMobileAppUserTypeId() {
		return mobileAppUserTypeId;
	}
	public void setMobileAppUserTypeId(Long mobileAppUserTypeId) {
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
