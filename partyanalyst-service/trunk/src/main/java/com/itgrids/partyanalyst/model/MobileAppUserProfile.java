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
@Table(name = "mobile_app_user_profile")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class MobileAppUserProfile extends BaseModel implements Serializable{
	
	private Long mobileAppUserProfileId;
	
	private MobileAppUser mobileAppUser;
	
	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	/** default constructor */
	public MobileAppUserProfile()
	{
		
	}
	/** full constructor */
	public MobileAppUserProfile(Long mobileAppUserProfileId,MobileAppUser mobileAppUser,String firstName,String lastName,String gender)
	{
		this.mobileAppUserProfileId = mobileAppUserProfileId;
		this.mobileAppUser = mobileAppUser;
		this.firstName =firstName;
		this.lastName = lastName;
		this.gender =gender;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mobile_app_user_profile_id", unique = true, nullable = false)
	public Long getMobileAppUserProfileId() {
		return mobileAppUserProfileId;
	}

	public void setMobileAppUserProfileId(Long mobileAppUserProfileId) {
		this.mobileAppUserProfileId = mobileAppUserProfileId;
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
	@Column(name = "firstname", length = 100)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "lastname", length = 50)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	

}
