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
public class Cadre extends BaseModel{

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
	 private State state;
	 private District district;
	 private Constituency constituency;
	 private Tehsil tehsil;
	 //private Long villageId;
	 private Township village;
	 private Long boothId;
	 private Hamlet hamlet = null;
	 
	 //new fields
	 private Date dateOfBirth;
	 private String telephone;	 
	 private String education;
	 private String occupation;
	 private String casteCategory;
	 private Double annualIncome;
	 private String memberType;
	 private PartyWorkingCommitteeDesignation designation;
	 private UserAddress currentAddress;
	 private UserAddress permanentAddress;
	 private Date effectiveDate;
	 private Date endingDate;
	 private String fatherOrSpouseName;
	 private Set<CadreLanguageEfficiency> cadreLanguageEfficiency = new HashSet<CadreLanguageEfficiency>();
	 private Set<CadreParticipatedTrainingCamps> cadreParticipatedTrainingCamps = new HashSet<CadreParticipatedTrainingCamps>();
	 private Set<CadreSkills> cadreSkills =  new HashSet<CadreSkills>();
	 
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
	
	 @Column(name = "gender", length = 1)
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
	
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "state_id")
	 public State getState() {
		return this.state;
	 }
	
	 public void setState(State state) {
		this.state = state;
	 }
		
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "district_id")
	 public District getDistrict() {
		return this.district;
	 }
	 public void setDistrict(District district) {
		this.district = district;
	 }

	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "constituency_id")
	 public Constituency getConstituency(){
		 return constituency;
	 }
	 
	 public void setConstituency(Constituency constituency){
		 this.constituency = constituency;
	 }
	 
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "mandal_id")
	 public Tehsil getTehsil() {
		return this.tehsil;
	 }
	 public void setTehsil(Tehsil tehsil) {
	 	this.tehsil = tehsil;
	 }
	 
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "village_id")
	 public Township getVillage() {
		return village;
	 }
	 public void setVillage(Township village) {
		this.village = village;
	 }
	 @Column(name = "booth_id", length = 15)
	 public Long getBoothId() {
		return boothId;
	 }
	 public void setBoothId(Long boothId) {
		this.boothId = boothId;
	 }
	 @Column(name = "cadre_level_value", length = 15)
	public Long getCadreLevelValue() {
		return cadreLevelValue;
	}

	public void setCadreLevelValue(Long cadreLevelValue) {
		this.cadreLevelValue = cadreLevelValue;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}

	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
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

	@Column(name = "education", length = 75)
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "occupation", length = 75)
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Column(name = "caste_category", length = 25)
	public String getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(String casteCategory) {
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

	@Column(name = "father_spouse_name", length = 100)
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	 
}
