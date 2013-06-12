package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="surveyor_profile")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SurveyorProfile extends BaseModel implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private long surveyorProfileId;
	private String name;
	private String mobileNo;
	private String phoneNo;
	private String age;
	private String emailId;
	private EducationalQualifications educationalQualifications;
	private Occupation occupation;
	private long casteStateId;
	private String gender;
	private RegionScopes regionScopes;
	private long locationValue;
	private UserAddress userAddress;
	
	private Set<Respondent> respondent = new HashSet<Respondent>(0);
	
	public SurveyorProfile() {
	}
	
	public SurveyorProfile(long surveyorProfileId,String name,String mobileNo,String phoneNo,String age,String emailId,EducationalQualifications educationalQualifications,Occupation occupation,long casteStateId,String gender,RegionScopes regionScopes,long locationValue,UserAddress userAddress) {
		this.surveyorProfileId = surveyorProfileId;
		this.name = name;
		this.mobileNo = mobileNo;
		this.phoneNo = phoneNo;
		this.age = age;
		this.emailId = emailId;
		this.educationalQualifications = educationalQualifications;
		this.occupation = occupation;
		this.casteStateId = casteStateId;
		this.gender = gender;
		this.regionScopes = regionScopes;
		this.locationValue = locationValue;
		this.userAddress = userAddress;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="surveyor_profile_id", unique=true, nullable=false)
	public long getSurveyorProfileId() {
		return surveyorProfileId;
	}

	public void setSurveyorProfileId(long surveyorProfileId) {
		this.surveyorProfileId = surveyorProfileId;
	}

	@Column(name="name", length=60)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="mobile_no", length=15)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name="phone_no",length=15)
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(name="age",length=5)
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Column(name="email_id",length=45)
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="educational_qualifications_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EducationalQualifications getEducationalQualifications() {
		return educationalQualifications;
	}

	public void setEducationalQualifications(
			EducationalQualifications educationalQualifications) {
		this.educationalQualifications = educationalQualifications;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="occupation_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	@Column(name="caste_state_id",length=15)
	public long getCasteStateId() {
		return casteStateId;
	}

	public void setCasteStateId(long casteStateId) {
		this.casteStateId = casteStateId;
	}

	@Column(name="gender", length=8)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="region_scopes_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}

	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}

	@Column(name="location_value",length=20)
	public long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(long locationValue) {
		this.locationValue = locationValue;
	}

	@ManyToOne(cascade =CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="user_address_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "surveyorProfile")
	public Set<Respondent> getRespondent() {
		return respondent;
	}

	public void setRespondent(Set<Respondent> respondent) {
		this.respondent = respondent;
	}
	
	
}
