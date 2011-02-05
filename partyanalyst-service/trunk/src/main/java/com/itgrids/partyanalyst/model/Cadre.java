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
	 private Registration registration;
	 private CadreLevel cadreLevel;
	 private Long cadreLevelValue;
	 private String firstName;
	 private String middleName;
	 private String lastName;
	 private String gender;
	 private String email;
	 private String mobile;
	 
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
	 private Set<CadreLanguageEfficiency> cadreLanguageEfficiency = new HashSet<CadreLanguageEfficiency>();
	 private Set<CadreFamilyMemberInfo> cadreFamilyMemberInfo = new HashSet<CadreFamilyMemberInfo>();
	 private Set<CadreParticipatedTrainingCamps> cadreParticipatedTrainingCamps = new HashSet<CadreParticipatedTrainingCamps>();
	 private Set<CadreSkills> cadreSkills =  new HashSet<CadreSkills>();
	 private Set<CadreProblemDetails> cadreProblemDetails =  new HashSet<CadreProblemDetails>();
	 	
	 
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
	 @JoinColumn(name = "user_id")
	 @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	 public Registration getRegistration() {
		return registration;
	 }
	
	 public void setRegistration(Registration registration) {
		this.registration = registration;
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

 }
