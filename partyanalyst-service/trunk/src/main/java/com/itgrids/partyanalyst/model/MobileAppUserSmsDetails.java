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
@Table(name = "mobile_app_user_sms_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileAppUserSmsDetails {
	private Long mobileAppUserSmsDetailsId;
	private Long mobileAppUserId;
	private String username;
	private String password;
	private String mtype;
	private String dr;
	private String advMessage;
	private MobileAppUser mobileAppUser;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mobile_app_user_sms_details_id", unique = true, nullable = false)
	public Long getMobileAppUserSmsDetailsId() {
		return mobileAppUserSmsDetailsId;
	}
	public void setMobileAppUserSmsDetailsId(Long mobileAppUserSmsDetailsId) {
		this.mobileAppUserSmsDetailsId = mobileAppUserSmsDetailsId;
	}
	@Column(name = "mobile_app_user_id")
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}
	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
	}
	@Column(name = "username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "mtype")
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	@Column(name = "dr")
	public String getDr() {
		return dr;
	}
	public void setDr(String dr) {
		this.dr = dr;
	}
	@Column(name = "adv_message")
	public String getAdvMessage() {
		return advMessage;
	}
	public void setAdvMessage(String advMessage) {
		this.advMessage = advMessage;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "mobile_app_user_id",insertable=false, updatable=false)
	public MobileAppUser getMobileAppUser() {
		return mobileAppUser;
	}
	public void setMobileAppUser(MobileAppUser mobileAppUser) {
		this.mobileAppUser = mobileAppUser;
	}
	
	

}
