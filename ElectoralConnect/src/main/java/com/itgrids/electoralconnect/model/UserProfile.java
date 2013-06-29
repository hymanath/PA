package com.itgrids.electoralconnect.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="user_profile")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserProfile extends BaseModel implements Serializable {
	private Long userProfileId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String mobileNo;
	private String epicNo;
	
	
	public UserProfile(String firstName,String lastName,String emailId,String mobileNo,String epicId){
		this.firstName=firstName;
		this.lastName=lastName;
		this.emailId=emailId;
		this.mobileNo=mobileNo;
		this.epicNo=epicId;
	}
	public UserProfile(){
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_profile_id", unique=true, nullable=false)
	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	@Column(name = "first_name", length = 70)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 70)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email_id", length = 70)
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "mobile_no", length = 70)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "epic_no", length = 70)
	public String getEpicNo() {
		return epicNo;
	}

	public void setEpicNo(String epicNo) {
		this.epicNo = epicNo;
	}

	
}
