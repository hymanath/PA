package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name="affiliated_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AffiliatedMember{
	private Long affiliatedMemberId;
	private Long voterId;
	private Long tdpCadreId;
	private String fullName;
	private String gender;
	private Long   age;
	private String mobileNo;
	private String emailId;
	private String imagePath;
	private String isSmartPhone;
	private String isInterested;
	private Long educationId;
	private Long occupationId;
	private Long casteStateId;
	private Long affiliateMemberTypeId;
	private String latitude;
	private String longititude;
	private String imei;
	private Long addressId;
	private String appVersion;
	private String unique_key;
	private Double locationAccuracy;	
	private String isDeleted;
	private Date  insertedTime;	
	private Date  updatedTime;
	
	private TdpCadre tdpCadre;
	private Voter voter;
	private UserAddress userAddress;
	private CadreSurveyUser				updatedBy;
	private CadreSurveyUser				insertedBy;
	private EducationalQualifications 	educationalQualifications;
	private Occupation 					occupation;
	private CasteState 					casteState;
	private AffiliateMemberType   affiliateMemberType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "affiliated_member_id", unique = true, nullable = false)
	public Long getAffiliatedMemberId() {
		return affiliatedMemberId;
	}
	public void setAffiliatedMemberId(Long affiliatedMemberId) {
		this.affiliatedMemberId = affiliatedMemberId;
	}
	@Column(name="voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name="full_name")
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="age")
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="email_id")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Column(name="image_path")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Column(name="is_smart_phone")
	public String getIsSmartPhone() {
		return isSmartPhone;
	}
	public void setIsSmartPhone(String isSmartPhone) {
		this.isSmartPhone = isSmartPhone;
	}
	@Column(name="is_interested")
	public String getIsInterested() {
		return isInterested;
	}
	public void setIsInterested(String isInterested) {
		this.isInterested = isInterested;
	}
	@Column(name="education_id")
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	@Column(name="occupation_id")
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	@Column(name="affiliate_member_type_id")
	public Long getAffiliateMemberTypeId() {
		return affiliateMemberTypeId;
	}
	public void setAffiliateMemberTypeId(Long affiliateMemberTypeId) {
		this.affiliateMemberTypeId = affiliateMemberTypeId;
	}
	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name="longititude")
	public String getLongititude() {
		return longititude;
	}
	public void setLongititude(String longititude) {
		this.longititude = longititude;
	}
	@Column(name="imei")
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@Column(name="app_version")
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	@Column(name="unique_key")
	public String getUnique_key() {
		return unique_key;
	}
	public void setUnique_key(String unique_key) {
		this.unique_key = unique_key;
	}
	@Column(name="location_accuracy")
	public Double getLocationAccuracy() {
		return locationAccuracy;
	}
	public void setLocationAccuracy(Double locationAccuracy) {
		this.locationAccuracy = locationAccuracy;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "voter_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "address_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "updated_by" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(CadreSurveyUser updatedBy) {
		this.updatedBy = updatedBy;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "created_by" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(CadreSurveyUser insertedBy) {
		this.insertedBy = insertedBy;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "education_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EducationalQualifications getEducationalQualifications() {
		return educationalQualifications;
	}
	public void setEducationalQualifications(
			EducationalQualifications educationalQualifications) {
		this.educationalQualifications = educationalQualifications;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "occupation_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Occupation getOccupation() {
		return occupation;
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "caste_state_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteState getCasteState() {
		return casteState;
	}
	public void setCasteState(CasteState casteState) {
		this.casteState = casteState;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "affiliate_member_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AffiliateMemberType getAffiliateMemberType() {
		return affiliateMemberType;
	}
	public void setAffiliateMemberType(AffiliateMemberType affiliateMemberType) {
		this.affiliateMemberType = affiliateMemberType;
	}
	@Column(name="caste_state_id")
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	
	
	
	
	

	
	

	
	









	

	

 





	
	
	
	
}
