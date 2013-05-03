package com.itgrids.partyanalyst.model;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
/**
 * 
 * @author Narender Akula
 *
 */
@Entity
@Table(name = "cadre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Cadre extends BaseModel{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private Long cadreId;
	 private User user;
	 private CadreLevel cadreLevel;
	 private Long cadreLevelValue;
	 private String firstName;
	 private String middleName;
	 private String lastName;
	 private String gender;
	 private String email;
	 private String mobile;
	 private Voter voter;
	 //new fields
	 private Date dateOfBirth;
	 private String telephone;	 
	 private EducationalQualifications education;
	 private Occupation occupation;
	 private SocialCategory casteCategory;
	 private Double annualIncome;
	 private String memberType;
	 private String memberOfPartySince;
	 private String presentRespInParty;
	 private PartyWorkingCommitteeDesignation designation;
	 private UserAddress currentAddress;
	 private UserAddress permanentAddress;
	 private Date effectiveDate;
	 private Date endingDate;
	 private String fatherOrSpouseName;
	 private String noOfFamilyMembers;
	 private String noOfVoters;
	 private String exactDateOfBirth;
	 private String image;
	 private BloodGroup bloodGroup;
	 private Long bloodGroupId;
	 private CadreOnlineRegistration cadreOnlineRegistration;
	 private Long cadreOnlineRegistrationId;
	 private Set<CadreLanguageEfficiency> cadreLanguageEfficiency = new HashSet<CadreLanguageEfficiency>();
	 private Set<CadreFamilyMemberInfo> cadreFamilyMemberInfo = new HashSet<CadreFamilyMemberInfo>();
	 private Set<CadreParticipatedTrainingCamps> cadreParticipatedTrainingCamps = new HashSet<CadreParticipatedTrainingCamps>();
	 private Set<CadreSkills> cadreSkills =  new HashSet<CadreSkills>();
	 private Set<CadreProblemDetails> cadreProblemDetails =  new HashSet<CadreProblemDetails>();
	 private Set<CadreRoleRelation> cadreRoleRelation =  new HashSet<CadreRoleRelation>();
	 private Set<AssignedProblemProgress> problemProgress = new HashSet<AssignedProblemProgress>(0);
	 private Set<CadreProblems> cadreProblems = new HashSet<CadreProblems>(0);
	 private Set<ProblemAssignedCadre> problemAssignedCadres = new HashSet<ProblemAssignedCadre>(0);
	 
	 private String note;
	 	
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "cadre_id", unique = true, nullable = false)
	 public Long getCadreId() {
		return cadreId;
	 }
	
	 public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	 }
	
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "cadre_level_id")
	 public CadreLevel getCadreLevel() {
		return cadreLevel;
	 }
	
	 public void setCadreLevel(CadreLevel cadreLevel) {
		this.cadreLevel = cadreLevel;
	 }
	
	 @Column(name = "first_name", length = 40)
	 public String getFirstName() {
		return firstName;
	 }
	 public void setFirstName(String firstName) {
		this.firstName = firstName;
	 }
	
	 @Column(name = "middle_name", length = 40)
	 public String getMiddleName() {
		return middleName;
	 }
	
	 public void setMiddleName(String middleName) {
		this.middleName = middleName;
	 }
	
	 @Column(name = "last_name", length = 40)
	 public String getLastName() {
		return lastName;
	 }
	 public void setLastName(String lastName) {
		this.lastName = lastName;
	 }
	
	 @Column(name = "gender", length = 6)
	 public String getGender() {
		return gender;
	 }
	 public void setGender(String gender) {
		this.gender = gender;
	 }
	
	 @Column(name = "email", length = 11)
	 public String getEmail() {
		return email;
	 }
	 public void setEmail(String email) {
		this.email = email;
	 }
	
	 @Column(name = "mobile_no", length = 11)	
	 public String getMobile() {
		return mobile;
	 }
	 public void setMobile(String mobile) {
		this.mobile = mobile;
	 }
	
	
	 
	 
	 @Column(name = "cadre_level_value", length = 15)
	public Long getCadreLevelValue() {
		return cadreLevelValue;
	}

	public void setCadreLevelValue(Long cadreLevelValue) {
		this.cadreLevelValue = cadreLevelValue;
	}

	//new attributes
	@Column(name = "date_of_birth", length = 10)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "telephone", length = 25)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "education")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EducationalQualifications getEducation() {
		return education;
	}

	public void setEducation(EducationalQualifications education) {
		this.education = education;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "occupation")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caste_category")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SocialCategory getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(SocialCategory casteCategory) {
		this.casteCategory = casteCategory;
	}

	@Column(name = "member_type", length = 25)
	public String getMemberType() {
		return memberType;
	}	
	
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
	@Column(name = "annual_income", precision = 2, scale = 0)
	public Double getAnnualIncome() {
		return annualIncome;
	}
	
	public void setAnnualIncome(Double annualIncome) {
		this.annualIncome = annualIncome;
	}	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "party_wkg_committee_designation_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyWorkingCommitteeDesignation getDesignation() {
		return designation;
	}

	public void setDesignation(PartyWorkingCommitteeDesignation designation) {
		this.designation = designation;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "current_address")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(UserAddress currentAddress) {
		this.currentAddress = currentAddress;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blood_group_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "permanent_address")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(UserAddress permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	
	@Column(name = "effective_date", length = 10)
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	@Column(name = "ending_date", length = 10)
	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	@Column(name = "image", length = 100)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "father_or_spouse_name", length = 100)
	public String getFatherOrSpouseName() {
		return fatherOrSpouseName;
	}

	public void setFatherOrSpouseName(String fatherOrSpouseName) {
		this.fatherOrSpouseName = fatherOrSpouseName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadre")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreLanguageEfficiency> getCadreLanguageEfficiency() {
		return cadreLanguageEfficiency;
	}

	public void setCadreLanguageEfficiency(
			Set<CadreLanguageEfficiency> cadreLanguageEfficiency) {
		this.cadreLanguageEfficiency = cadreLanguageEfficiency;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadre")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreParticipatedTrainingCamps> getCadreParticipatedTrainingCamps() {
		return cadreParticipatedTrainingCamps;
	}

	public void setCadreParticipatedTrainingCamps(
			Set<CadreParticipatedTrainingCamps> cadreParticipatedTrainingCamps) {
		this.cadreParticipatedTrainingCamps = cadreParticipatedTrainingCamps;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadre")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreSkills> getCadreSkills() {
		return cadreSkills;
	}

	public void setCadreSkills(Set<CadreSkills> cadreSkills) {
		this.cadreSkills = cadreSkills;
	}

	@Column(name = "exact_date_of_birth", length = 25)
	public String getExactDateOfBirth() {
		return exactDateOfBirth;
	}

	public void setExactDateOfBirth(String exactDateOfBirth) {
		this.exactDateOfBirth = exactDateOfBirth;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadre")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreFamilyMemberInfo> getCadreFamilyMemberInfo() {
		return cadreFamilyMemberInfo;
	}

	public void setCadreFamilyMemberInfo(
			Set<CadreFamilyMemberInfo> cadreFamilyMemberInfo) {
		this.cadreFamilyMemberInfo = cadreFamilyMemberInfo;
	}

	@Column(name = "no_of_family_members", length = 5)
	public String getNoOfFamilyMembers() {
		return noOfFamilyMembers;
	}

	public void setNoOfFamilyMembers(String noOfFamilyMembers) {
		this.noOfFamilyMembers = noOfFamilyMembers;
	}

	@Column(name = "no_of_voters", length = 5)
	public String getNoOfVoters() {
		return noOfVoters;
	}

	public void setNoOfVoters(String noOfVoters) {
		this.noOfVoters = noOfVoters;
	}

	@Column(name = "blood_group_id", length = 10)
	public Long getBloodGroupId() {
		return bloodGroupId;
	}

	public void setBloodGroupId(Long bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}
	
	/**
	 * @return the memberOfPartySince
	 */
	@Column(name = "member_of_party_since", length = 10)
	public String getMemberOfPartySince() {
		return memberOfPartySince;
	}

	/**
	 * @param memberOfPartySince the memberOfPartySince to set
	 */
	public void setMemberOfPartySince(String memberOfPartySince) {
		this.memberOfPartySince = memberOfPartySince;
	}

	/**
	 * @return the presentRespInParty
	 */
	@Column(name = "present_resp_in_party", length = 100)
	public String getPresentRespInParty() {
		return presentRespInParty;
	}

	/**
	 * @param presentRespInParty the presentRespInParty to set
	 */
	public void setPresentRespInParty(String presentRespInParty) {
		this.presentRespInParty = presentRespInParty;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadre")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreProblemDetails> getCadreProblemDetails() {
		return cadreProblemDetails;
	}

	public void setCadreProblemDetails(Set<CadreProblemDetails> cadreProblemDetails) {
		this.cadreProblemDetails = cadreProblemDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadre")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreRoleRelation> getCadreRoleRelation() {
		return cadreRoleRelation;
	}

	public void setCadreRoleRelation(Set<CadreRoleRelation> cadreRoleRelation) {
		this.cadreRoleRelation = cadreRoleRelation;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadre")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<AssignedProblemProgress> getProblemProgress() {
		return problemProgress;
	}

	public void setProblemProgress(Set<AssignedProblemProgress> problemProgress) {
		this.problemProgress = problemProgress;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadre")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreProblems> getCadreProblems() {
		return cadreProblems;
	}

	public void setCadreProblems(Set<CadreProblems> cadreProblems) {
		this.cadreProblems = cadreProblems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadre")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemAssignedCadre> getProblemAssignedCadres() {
		return problemAssignedCadres;
	}

	public void setProblemAssignedCadres(
			Set<ProblemAssignedCadre> problemAssignedCadres) {
		this.problemAssignedCadres = problemAssignedCadres;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_online_registration_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreOnlineRegistration getCadreOnlineRegistration() {
		return cadreOnlineRegistration;
	}

	public void setCadreOnlineRegistration(
			CadreOnlineRegistration cadreOnlineRegistration) {
		this.cadreOnlineRegistration = cadreOnlineRegistration;
	}

	@Column(name = "cadre_online_registration_id")
	public Long getCadreOnlineRegistrationId() {
		return cadreOnlineRegistrationId;
	}

	public void setCadreOnlineRegistrationId(Long cadreOnlineRegistrationId) {
		this.cadreOnlineRegistrationId = cadreOnlineRegistrationId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
	return voter;
	}

	public void setVoter(Voter voter) {
	this.voter = voter;
	}

	@Column(name = "note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
 }
