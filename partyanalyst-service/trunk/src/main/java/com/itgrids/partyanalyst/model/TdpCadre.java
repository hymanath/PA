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
	private String 						relativeType;
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
	private Date 						insertedTime;
	private Date 						updatedTime;
	private Long 						enrollmentYear;
	
	private Long 						updatedUserId;
	private Long 						insertedUserId;
	
	private Long 						updatedWebUserId;
	private Long 						insertedWebUserId;
	private User				        updatedWebUser;
	private User				        insertedWebUser;
	
	private Voter 						voter;
	private BloodGroup 					bloodGroup;
	private Occupation 					occupation;
	private EducationalQualifications 	educationalQualifications;
	private CasteState 					casteState;
	private UserAddress 				userAddress;
	private CadreSurveyUser				updatedBy;
	private CadreSurveyUser				insertedBy;
	private Date                        surveyTime;
	private Date                        refSurveyTime;
	private String                      isDeleted;
	
	private String 						latitude;
	private String 						longititude;
	
	private String 						dataSourceType;
	private String						uniqueKey;
	private String                      refNo;
	private String						cardNumber;
	
	private String 						nomineeName;
	private String						aadheerNo;
	private Long						nomineeAge;
	private String						nomineeGender;
	
	private Long 						voterRelationId;
	private VoterRelation				voterRelation;
	
	private String						cadreType;
	private String                      isRelative;
	private VoterRelation				relationType;
	private Long						relationTypeId;
	
	private String 						photoType;
	private String						nameType;
	
	private String						cadreAadherNo;
	private Voter 						familyVoter;
	private Long						familyVoterId;
	private String						previousMembershipYear;
	private String						dispatchStatus;
	private String                      noVoterId;
	private String                      isDuplicate;
	private String                      cardNo;
	private Long 						memberId;
	private String 						nameLocal;
	private TdpCadreOnline              tdpCadreOnline;
	private Long 						constituencyId;
	private String 						emailId;
	private String						isPrintReady;
	private String 						mobileType;
	private VoterAgeRange 				voterAgeRange;
	private Long 						voterAgeRangeId;
	private CadreDeleteReason			cadreDeleteReason;
	private String 						deleteRemark;
	
	private Long 						cadreDeleteReasonId;
	
	private Long 						tdpMemberTypeId;
	private Long 						unionTypeId;
	private String						idCardNo;
	private Long						designationId;
	private Long 						tdpCadreLocationId;
	
	private TdpMemberType				tdpMemberType;
	private UnionType					unionType;
	private Designation					designation;
	private TdpCadreLocation			tdpCadreLocation;
	private UserAddress 				permanentAddress;
	private String						voterCardVerified;
	private String						voterCardType;
	private String						mode;
	private String						designationName;
	private Long 						mobileNumberDetailsId;
	private MobileNumberDetails			mobileNumberDetails;
	
	
	private String validStatus;
	//private Long workLocationId;
	private String schoolName;
	//private Long presentAddressId;
	
	private UserAddress presentAddress;
	private UserAddress workLocation;
	
	private Long createdUnionTabUserId;
	private Long updatedUnionTabUserId;
	
	private UnionTabUser createdUnionTabUser;
	private UnionTabUser updatedUnionTabUser;
	
	private String payMentStatus;
	private Long parentTdpCadreId;
	
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
	
	@Column(name="survey_time")
	public Date getSurveyTime() {
		return surveyTime;
	}
	
	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
	
	@Column(name="data_source_type")
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	
	@Column(name="unique_key")
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	@Column(name="ref_no")
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	
	@Column(name="updated_web_user_id")
	public Long getUpdatedWebUserId() {
		return updatedWebUserId;
	}
	public void setUpdatedWebUserId(Long updatedWebUserId) {
		this.updatedWebUserId = updatedWebUserId;
	}
	
	@Column(name="inserted_web_user_id")
	public Long getInsertedWebUserId() {
		return insertedWebUserId;
	}
	
	public void setInsertedWebUserId(Long insertedWebUserId) {
		this.insertedWebUserId = insertedWebUserId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "updated_web_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedWebUser() {
		return updatedWebUser;
	}
	
	public void setUpdatedWebUser(User updatedWebUser) {
		this.updatedWebUser = updatedWebUser;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "inserted_web_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedWebUser() {
		return insertedWebUser;
	}
	
	public void setInsertedWebUser(User insertedWebUser) {
		this.insertedWebUser = insertedWebUser;
	}
	
	@Column(name="card_number")
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	@Column(name="nominee_name")
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	
	
	@Column(name="nominee_age")
	public Long getNomineeAge() {
		return nomineeAge;
	}
	public void setNomineeAge(Long nomineeAge) {
		this.nomineeAge = nomineeAge;
	}
	
	@Column(name="nominee_gender")
	public String getNomineeGender() {
		return nomineeGender;
	}
	public void setNomineeGender(String nomineeGender) {
		this.nomineeGender = nomineeGender;
	}
	
	
	@Column(name="aadhar_no")
	public String getAadheerNo() {
		return aadheerNo;
	}
	public void setAadheerNo(String aadheerNo) {
		this.aadheerNo = aadheerNo;
	}
	
	@Column(name="voter_relation_id")
	public Long getVoterRelationId() {
		return voterRelationId;
	}
	public void setVoterRelationId(Long voterRelationId) {
		this.voterRelationId = voterRelationId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "voter_relation_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterRelation getVoterRelation() {
		return voterRelation;
	}
	public void setVoterRelation(VoterRelation voterRelation) {
		this.voterRelation = voterRelation;
	}
	
	@Column(name="cadre_type")
	public String getCadreType() {
		return cadreType;
	}
	public void setCadreType(String cadreType) {
		this.cadreType = cadreType;
	}
	
	@Column(name="relative_type")
	public String getRelativeType() {
		return relativeType;
	}
	public void setRelativeType(String relativeType) {
		this.relativeType = relativeType;
	}
	
	@Column(name="photo_type")
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	@Column(name="name_type")
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
		
	@Column(name="is_relative")
	public String getIsRelative() {
		return isRelative;
	}
	public void setIsRelative(String isRelative) {
		this.isRelative = isRelative;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "relation_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterRelation getRelationType() {
		return relationType;
	}
	public void setRelationType(VoterRelation relationType) {
		this.relationType = relationType;
	}
	
	@Column(name="relation_type_id")
	public Long getRelationTypeId() {
		return relationTypeId;
	}
	public void setRelationTypeId(Long relationTypeId) {
		this.relationTypeId = relationTypeId;
	}
	
	@Column(name="cadre_aadher_no")
	public String getCadreAadherNo() {
		return cadreAadherNo;
	}
	public void setCadreAadherNo(String cadreAadherNo) {
		this.cadreAadherNo = cadreAadherNo;
	}
	
	@Column(name="family_voterId")
	public Long getFamilyVoterId() {
		return familyVoterId;
	}
	public void setFamilyVoterId(Long familyVoterId) {
		this.familyVoterId = familyVoterId;
	}
	
	@Column(name="previous_membership_year")
	public String getPreviousMembershipYear() {
		return previousMembershipYear;
	}
	public void setPreviousMembershipYear(String previousMembershipYear) {
		this.previousMembershipYear = previousMembershipYear;
	}
	@Column(name="dispatch_status")
	public String getDispatchStatus() {
		return dispatchStatus;
	}
	public void setDispatchStatus(String dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}	
	@Column(name="no_voter_id")
	public String getNoVoterId() {
		return noVoterId;
	}
	public void setNoVoterId(String noVoterId) {
		this.noVoterId = noVoterId;
	}
	@Column(name="duplicate")
	public String getIsDuplicate() {
		return isDuplicate;
	}
	public void setIsDuplicate(String isDuplicate) {
		this.isDuplicate = isDuplicate;
	}
	
	@Column(name="card_no")
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	@Column(name="member_id")
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	@Column(name="name_local")
	public String getNameLocal() {
		return nameLocal;
	}
	public void setNameLocal(String nameLocal) {
		this.nameLocal = nameLocal;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_cadre_online_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadreOnline getTdpCadreOnline() {
		return tdpCadreOnline;
	}
	
	public void setTdpCadreOnline(TdpCadreOnline tdpCadreOnline) {
		this.tdpCadreOnline = tdpCadreOnline;
	}
	
	@Column(name="ref_survey_time")
	public Date getRefSurveyTime() {
		return refSurveyTime;
	}
	public void setRefSurveyTime(Date refSurveyTime) {
		this.refSurveyTime = refSurveyTime;
	}
	
	@Column(name="constituency_id")	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@Column(name="email_id")	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Column(name="is_print_ready")
	public String getIsPrintReady() {
		return isPrintReady;
	}
	public void setIsPrintReady(String isPrintReady) {
		this.isPrintReady = isPrintReady;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "family_voterId" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getFamilyVoter() {
		return familyVoter;
	}
	public void setFamilyVoter(Voter familyVoter) {
		this.familyVoter = familyVoter;
	}
	
	@Column(name="mobile_type")
	public String getMobileType() {
		return mobileType;
	}
	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "age_range_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterAgeRange getVoterAgeRange() {
		return voterAgeRange;
	}	
	public void setVoterAgeRange(VoterAgeRange voterAgeRange) {
		this.voterAgeRange = voterAgeRange;
	}
	
	@Column(name="age_range_id")
	public Long getVoterAgeRangeId() {
		return voterAgeRangeId;
	}
	public void setVoterAgeRangeId(Long voterAgeRangeId) {
		this.voterAgeRangeId = voterAgeRangeId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_delete_reason_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreDeleteReason getCadreDeleteReason() {
		return cadreDeleteReason;
	}
	public void setCadreDeleteReason(CadreDeleteReason cadreDeleteReason) {
		this.cadreDeleteReason = cadreDeleteReason;
	}
	
	@Column(name="delete_remark")
	public String getDeleteRemark() {
		return deleteRemark;
	}
	public void setDeleteRemark(String deleteRemark) {
		this.deleteRemark = deleteRemark;
	}
	
	@Column(name="cadre_delete_reason_id")
	public Long getCadreDeleteReasonId() {
		return cadreDeleteReasonId;
	}
	public void setCadreDeleteReasonId(Long cadreDeleteReasonId) {
		this.cadreDeleteReasonId = cadreDeleteReasonId;
	}
	
	@Column(name="tdp_member_type_id")
	public Long getTdpMemberTypeId() {
		return tdpMemberTypeId;
	}
	public void setTdpMemberTypeId(Long tdpMemberTypeId) {
		this.tdpMemberTypeId = tdpMemberTypeId;
	}
	
	@Column(name="union_type_id")
	public Long getUnionTypeId() {
		return unionTypeId;
	}
	public void setUnionTypeId(Long unionTypeId) {
		this.unionTypeId = unionTypeId;
	}
	
	@Column(name="id_card_no")
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	
	@Column(name="designation_id")
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	
	@Column(name="tdp_cadre_location_id")
	public Long getTdpCadreLocationId() {
		return tdpCadreLocationId;
	}
	public void setTdpCadreLocationId(Long tdpCadreLocationId) {
		this.tdpCadreLocationId = tdpCadreLocationId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_member_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpMemberType getTdpMemberType() {
		return tdpMemberType;
	}
	public void setTdpMemberType(TdpMemberType tdpMemberType) {
		this.tdpMemberType = tdpMemberType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "union_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UnionType getUnionType() {
		return unionType;
	}
	public void setUnionType(UnionType unionType) {
		this.unionType = unionType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "designation_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_location_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadreLocation getTdpCadreLocation() {
		return tdpCadreLocation;
	}
	public void setTdpCadreLocation(TdpCadreLocation tdpCadreLocation) {
		this.tdpCadreLocation = tdpCadreLocation;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "permanent_address_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(UserAddress permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	
	@Column(name="voter_card_verified")
	public String getVoterCardVerified() {
		return voterCardVerified;
	}
	public void setVoterCardVerified(String voterCardVerified) {
		this.voterCardVerified = voterCardVerified;
	}
	
	@Column(name="voter_card_type")
	public String getVoterCardType() {
		return voterCardType;
	}
	public void setVoterCardType(String voterCardType) {
		this.voterCardType = voterCardType;
	}
	
	@Column(name="mode")
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	@Column(name="valid_status")
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	/*
	@Column(name="work_location_id")
	public Long getWorkLocationId() {
		return workLocationId;
	}
	public void setWorkLocationId(Long workLocationId) {
		this.workLocationId = workLocationId;
	}*/
	
	@Column(name="school_name")
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
/*	@Column(name="present_address_id")
	public Long getPresentAddressId() {
		return presentAddressId;
	}
	public void setPresentAddressId(Long presentAddressId) {
		this.presentAddressId = presentAddressId;
	}*/
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "present_address_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getPresentAddress() {
		return presentAddress;
	}
	public void setPresentAddress(UserAddress presentAddress) {
		this.presentAddress = presentAddress;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "work_location_id" )
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getWorkLocation() {
		return workLocation;
	}
	public void setWorkLocation(UserAddress workLocation) {
		this.workLocation = workLocation;
	}
	
	
	@Column(name="created_union_tab_user_id")
	public Long getCreatedUnionTabUserId() {
		return createdUnionTabUserId;
	}
	public void setCreatedUnionTabUserId(Long createdUnionTabUserId) {
		this.createdUnionTabUserId = createdUnionTabUserId;
	}
	
	@Column(name="updated_union_tab_user_id")
	public Long getUpdatedUnionTabUserId() {
		return updatedUnionTabUserId;
	}
	public void setUpdatedUnionTabUserId(Long updatedUnionTabUserId) {
		this.updatedUnionTabUserId = updatedUnionTabUserId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "created_union_tab_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UnionTabUser getCreatedUnionTabUser() {
		return createdUnionTabUser;
	}
	public void setCreatedUnionTabUser(UnionTabUser createdUnionTabUser) {
		this.createdUnionTabUser = createdUnionTabUser;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "updated_union_tab_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UnionTabUser getUpdatedUnionTabUser() {
		return updatedUnionTabUser;
	}
	public void setUpdatedUnionTabUser(UnionTabUser updatedUnionTabUser) {
		this.updatedUnionTabUser = updatedUnionTabUser;
	}
	
	@Column(name="parent_tdp_cadre_id")
	public Long getParentTdpCadreId() {
		return parentTdpCadreId;
	}
	public void setParentTdpCadreId(Long parentTdpCadreId) {
		this.parentTdpCadreId = parentTdpCadreId;
	}
	
	@Column(name="designation_name")
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	
	@Column(name="payment_status")
	public String getPayMentStatus() {
		return payMentStatus;
	}
	public void setPayMentStatus(String payMentStatus) {
		this.payMentStatus = payMentStatus;
	}
	
	@Column(name="mobile_number_details_id")
	public Long getMobileNumberDetailsId() {
		return mobileNumberDetailsId;
	}
	public void setMobileNumberDetailsId(Long mobileNumberDetailsId) {
		this.mobileNumberDetailsId = mobileNumberDetailsId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "mobile_number_details_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MobileNumberDetails getMobileNumberDetails() {
		return mobileNumberDetails;
	}
	public void setMobileNumberDetails(MobileNumberDetails mobileNumberDetails) {
		this.mobileNumberDetails = mobileNumberDetails;
	}
}
