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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "mobile_app_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileAppUser extends BaseModel implements Serializable{
	
	private Long mobileAppUserId;
	private String userName;
	
	private String password;
	private String uniqueCode;
	
	private String mobileNo;
	private String email;
	
	private User user;
	private Set<MobileAppUserProfile> mobileAppUserProfile = new HashSet<MobileAppUserProfile>(0);
	private Set<MobileAppUserAccess> mobileAppUserAccess = new HashSet<MobileAppUserAccess>(0);
	private Set<MobileAppUserAccessKey> mobileAppUserAccessKey = new HashSet<MobileAppUserAccessKey>(0);
	/** default constructor */
	public MobileAppUser()
	{
		
	}
	/** full constructor */
	public MobileAppUser(Long mobileAppUserId,String userName,String password,String uniqueCode, User user)
	{
	this.mobileAppUserId = mobileAppUserId;
	this.userName = userName;
	this.password = password;
	this.uniqueCode = uniqueCode;
	this.user =user;
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
	@Column(name = "mobile_no", length = 25)
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mobileAppUser")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<MobileAppUserAccessKey> getMobileAppUserAccessKey() {
		return mobileAppUserAccessKey;
	}
	public void setMobileAppUserAccessKey(
			Set<MobileAppUserAccessKey> mobileAppUserAccessKey) {
		this.mobileAppUserAccessKey = mobileAppUserAccessKey;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}
