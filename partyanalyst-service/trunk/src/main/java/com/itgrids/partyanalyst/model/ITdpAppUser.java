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
@Table(name="itdp_app_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ITdpAppUser {

	
	private Long iTdpAppUserId;
	private String name;
	private String userName;
	private String password;
	private String mobile;
	private String isDeleted;
	private String isEnabled;
	private String userType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "itdp_app_user_id", unique = true, nullable = false)
	public Long getiTdpAppUserId() {
		return iTdpAppUserId;
	}
	public void setiTdpAppUserId(Long iTdpAppUserId) {
		this.iTdpAppUserId = iTdpAppUserId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="username")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="is_enabled")
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	@Column(name="user_type")
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	
}
