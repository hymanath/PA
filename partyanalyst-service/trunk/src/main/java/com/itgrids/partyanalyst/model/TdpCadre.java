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

/**
 * 
 * @author Prasad Thiragabathina
 *
 */

@Entity
@Table(name = "tdp_cadre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadre {
	
	private Long 						tdpCadreId;
	private Long 						voterId;
	private String 						memberShipNo;
	private String 						firstname;
	private String 						lastname;
	private String 						relativename;
	private String 						houseNo;
	private String 						image;
	private String 						mobileNo;
	private String 						landMobileNo;
	
	private Long 						bloodGroupId;
	private String 						gender;
	private Long 						occupationId;
	private Long 						educationId;
	private Date 						dateOfBirth;
	private Long 						age;
	private Long 						casteStateId;
	private Date 						partyMemberSince;
	private String 						previousEnrollmentNo;
	private Long 						addressId;
	private Date 						insertedTime;
	private Date 						updatedTime;
	private Long 						enrollmentYear;
	
	private Long 						updatedUserId;
	private Long 						insertedUserId;
	
	private Voter 						voter;
	private BloodGroup 					bloodGroup;
	private Occupation 					occupation;
	private EducationalQualifications 	educationalQualifications;
	private CasteState 					casteState;
	private UserAddress 				userAddress;
	private User 						updatedBy;
	private User 						insertedBy;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_id", unique = true, nullable = false)
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name="voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	
	@Column(name="membership_id")
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	
	@Column(name="first_name")
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Column(name="last_name")
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Column(name="relative_name")
	public String getRelativename() {
		return relativename;
	}
	public void setRelativename(String relativename) {
		this.relativename = relativename;
	}
	
	@Column(name="house_no")
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	
	@Column(name="image")
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="land_phone_no")
	public String getLandMobileNo() {
		return landMobileNo;
	}
	
	
	public void setLandMobileNo(String landMobileNo) {
		this.landMobileNo = landMobileNo;
	}
	
	@Column(name="blood_group_id")
	public Long getBloodGroupId() {
		return bloodGroupId;
	}
	
	
	public void setBloodGroupId(Long bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}
	
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name="occupation_id")
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	
	@Column(name="education_id")
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	
	@Column(name="date_of_birth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Column(name="age")
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	
	@Column(name="caste_state_id")
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	
	@Column(name="party_member_since")
	public Date getPartyMemberSince() {
		return partyMemberSince;
	}
	public void setPartyMemberSince(Date partyMemberSince) {
		this.partyMemberSince = partyMemberSince;
	}
	
	@Column(name="previous_enrollment_no")
	public String getPreviousEnrollmentNo() {
		return previousEnrollmentNo;
	}
	public void setPreviousEnrollmentNo(String previousEnrollmentNo) {
		this.previousEnrollmentNo = previousEnrollmentNo;
	}
	
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="update_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="enrollment_year")
	public Long getEnrollmentYear() {
		return enrollmentYear;
	}
	public void setEnrollmentYear(Long enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	
	@Column(name="created_by")
	public Long getInsertedUserId() {
		return insertedUserId;
	}
	public void setInsertedUserId(Long insertedUserId) {
		this.insertedUserId = insertedUserId;
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
	@JoinColumn(name = "blood_group_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
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
	@JoinColumn(name = "address_id" , insertable = false, updatable = false)
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
	public User getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "created_by" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(User insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	

}
