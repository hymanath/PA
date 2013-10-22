package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "mobile_app_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileAppUser extends BaseModel implements Serializable{
	
	private Long mobileAppUserId;
	private String userName;
	
	private String password;
	private String uniqueCode;
	private Set<MobileAppUserProfile> mobileAppUserProfile = new HashSet<MobileAppUserProfile>(0);
	private Set<MobileAppUserAccess> mobileAppUserAccess = new HashSet<MobileAppUserAccess>(0);
	/** default constructor */
	public MobileAppUser()
	{
		
	}
	/** full constructor */
	public MobileAppUser(Long mobileAppUserId,String userName,String password,String uniqueCode)
	{
	this.mobileAppUserId = mobileAppUserId;
	this.userName = userName;
	this.password = password;
	this.uniqueCode = uniqueCode;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mobile_app_user_id", unique = true, nullable = false)
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}

	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
	}
	@Column(name = "user_name", length = 50)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "password", length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "unique_code", length = 50)
	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mobileAppUser")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<MobileAppUserProfile> getMobileAppUserProfile() {
		return mobileAppUserProfile;
	}

	public void setMobileAppUserProfile(
			Set<MobileAppUserProfile> mobileAppUserProfile) {
		this.mobileAppUserProfile = mobileAppUserProfile;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mobileAppUser")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<MobileAppUserAccess> getMobileAppUserAccess() {
		return mobileAppUserAccess;
	}
	
	public void setMobileAppUserAccess(Set<MobileAppUserAccess> mobileAppUserAccess) {
		this.mobileAppUserAccess = mobileAppUserAccess;
	}

	
}
