package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "mobile_app_user_access_key")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileAppUserAccessKey extends BaseModel implements Serializable{
	
	private Long mobileAppUserAccesskeyId;
	private MobileAppUser mobileAppUser;
	private String accessKey;
	private Long createdBy;
	private Date creationTime;
	private String isUsed;
	private String type;

	public MobileAppUserAccessKey()
	{
		
	}
	
	public MobileAppUserAccessKey(Long mobileAppUserAccesskeyId,MobileAppUser mobileAppUser,String accessKey,Long createdBy,
			Date creationTime,String isUsed,String type)
			
	{
		this.mobileAppUserAccesskeyId = mobileAppUserAccesskeyId;
		this.mobileAppUser = mobileAppUser;
		this.accessKey = accessKey;
		this.createdBy = createdBy;
		this.creationTime = creationTime;
		this.isUsed = isUsed;
		this.type = type;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mobile_app_user_access_key_id", unique = true, nullable = false)
	public Long getMobileAppUserAccesskeyId() {
		return mobileAppUserAccesskeyId;
	}
	public void setMobileAppUserAccesskeyId(Long mobileAppUserAccesskeyId) {
		this.mobileAppUserAccesskeyId = mobileAppUserAccesskeyId;
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
	@Column(name = "access_key", length = 45)
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	@Column(name = "created_by", length = 15)
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "creation_time")
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	@Column(name = "is_used")
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
