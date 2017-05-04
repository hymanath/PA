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
@Table(name = "alert_caller")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertCaller extends BaseModel implements Serializable{

	private Long alertCallerId;
	private String callerName;
	private String address;
	private String mobileNo;
	private String email;
	
	private String accountId;
	private Long socialMediaTypeId;
	
	private SocialMediaType socialMediaType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_caller_id", unique = true, nullable = false)
	public Long getAlertCallerId() {
		return alertCallerId;
	}
	public void setAlertCallerId(Long alertCallerId) {
		this.alertCallerId = alertCallerId;
	}
	
	@Column(name = "caller_name")
	public String getCallerName() {
		return callerName;
	}
	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "account_id")
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "social_media_type_id")
	public Long getSocialMediaTypeId() {
		return socialMediaTypeId;
	}
	public void setSocialMediaTypeId(Long socialMediaTypeId) {
		this.socialMediaTypeId = socialMediaTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "social_media_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)	
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public SocialMediaType getSocialMediaType() {
		return socialMediaType;
	}
	public void setSocialMediaType(SocialMediaType socialMediaType) {
		this.socialMediaType = socialMediaType;
	}
	
	
	
}
