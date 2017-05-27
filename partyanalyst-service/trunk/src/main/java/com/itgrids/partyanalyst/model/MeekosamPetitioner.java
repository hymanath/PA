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
@Table(name = "govt_department_meekosam_issue_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamPetitioner extends BaseModel implements Serializable {
	private Long meekosamPetitionerId;
	private String name;
	private String relativeName;
	private Long age;
	private Date dateOfBirth;
	private String gender;
	private Long userAddressId;
	private String houseNo;
	private String aadharNo;
	private String voterCardNo;
	private String mobileNo;
	private String emailId;
	private Long meekosamOccupationId;
	private Long meekosamCasteCategoryId;
	private Long meekosamArgeeCategoryId;
	private Long meekosamAnnualIncomeId;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	private UserAddress userAddress;
	private MeekosamOccupation meekosamOccupation;
	private MeekosamCasteCategory meekosamCasteCategory;
	private MeekosamArgeeCategory meekosamArgeeCategory;
	private MeekosamAnnualIncome meekosamAnnualIncome;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meekosam_petitioner_id", unique = true, nullable = false)
	public Long getMeekosamPetitionerId() {
		return meekosamPetitionerId;
	}
	public void setMeekosamPetitionerId(Long meekosamPetitionerId) {
		this.meekosamPetitionerId = meekosamPetitionerId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "relative_name")
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	@Column(name = "age")
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	@Column(name = "date_of_birth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name = "user_address_id")
	public Long getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}
	@Column(name = "house_no")
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	@Column(name = "aadhar_no")
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	@Column(name = "voter_card_no")
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name = "email_id")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Column(name = "meekosam_occupation_id")
	public Long getMeekosamOccupationId() {
		return meekosamOccupationId;
	}
	public void setMeekosamOccupationId(Long meekosamOccupationId) {
		this.meekosamOccupationId = meekosamOccupationId;
	}
	@Column(name = "meekosam_caste_category_id")
	public Long getMeekosamCasteCategoryId() {
		return meekosamCasteCategoryId;
	}
	public void setMeekosamCasteCategoryId(Long meekosamCasteCategoryId) {
		this.meekosamCasteCategoryId = meekosamCasteCategoryId;
	}
	@Column(name = "meekosam_argee_category_id")
	public Long getMeekosamArgeeCategoryId() {
		return meekosamArgeeCategoryId;
	}
	public void setMeekosamArgeeCategoryId(Long meekosamArgeeCategoryId) {
		this.meekosamArgeeCategoryId = meekosamArgeeCategoryId;
	}
	@Column(name = "meekosam_annual_income_id")
	public Long getMeekosamAnnualIncomeId() {
		return meekosamAnnualIncomeId;
	}
	public void setMeekosamAnnualIncomeId(Long meekosamAnnualIncomeId) {
		this.meekosamAnnualIncomeId = meekosamAnnualIncomeId;
	}
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_address_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_occupation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamOccupation getMeekosamOccupation() {
		return meekosamOccupation;
	}
	public void setMeekosamOccupation(MeekosamOccupation meekosamOccupation) {
		this.meekosamOccupation = meekosamOccupation;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_caste_category_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamCasteCategory getMeekosamCasteCategory() {
		return meekosamCasteCategory;
	}
	public void setMeekosamCasteCategory(MeekosamCasteCategory meekosamCasteCategory) {
		this.meekosamCasteCategory = meekosamCasteCategory;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_argee_category_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamArgeeCategory getMeekosamArgeeCategory() {
		return meekosamArgeeCategory;
	}
	public void setMeekosamArgeeCategory(MeekosamArgeeCategory meekosamArgeeCategory) {
		this.meekosamArgeeCategory = meekosamArgeeCategory;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_annual_income_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamAnnualIncome getMeekosamAnnualIncome() {
		return meekosamAnnualIncome;
	}
	public void setMeekosamAnnualIncome(MeekosamAnnualIncome meekosamAnnualIncome) {
		this.meekosamAnnualIncome = meekosamAnnualIncome;
	}
}
