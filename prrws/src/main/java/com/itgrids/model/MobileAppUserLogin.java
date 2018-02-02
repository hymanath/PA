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
@Table(name = "mobile_app_user_login")
public class MobileAppUserLogin {
	private Long mobileAppUserLoginId;
	private Long mobileAppUserId;
	private String username;
	private String password;
	private String loginStatus;
	private Date loginTime;
	private String imeiNo;
	
	private MobileAppUser mobileAppUser;

	@Id
	@Column(name="mobile_app_user_login_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getMobileAppUserLoginId() {
		return mobileAppUserLoginId;
	}

	public void setMobileAppUserLoginId(Long mobileAppUserLoginId) {
		this.mobileAppUserLoginId = mobileAppUserLoginId;
	}

	@Column(name="mobile_app_user_id")
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}

	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
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

	@Column(name="login_status")
	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	@Column(name="login_time")
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name="imei_no")
	public String getImeiNo() {
		return imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "mobile_app_user_id", insertable = false, updatable = false)
	public MobileAppUser getMobileAppUser() {
		return mobileAppUser;
	}

	public void setMobileAppUser(MobileAppUser mobileAppUser) {
		this.mobileAppUser = mobileAppUser;
	}
}
