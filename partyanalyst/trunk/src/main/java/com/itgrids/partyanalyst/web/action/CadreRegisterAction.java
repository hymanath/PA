package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * @author Administrator
 *
 */
public class CadreRegisterAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware {

	private static final long serialVersionUID = -4442134889468509932L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;

	private CadreManagementService cadreManagementService;
	private final static Logger log = Logger
			.getLogger(CadreRegisterAction.class);

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	private CadreInfo cadreInfo = new CadreInfo();
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String state;
	private String district;
	private String constituencyID;
	private String mandal;
	private String village;
	private String cadreLevel;
	private String cadreLevelValue;
	private String mobile;
	private String email;
	private String cadreId;
	private Long cadreID = null;
	private String pstate;
	private String pdistrict;
	private String pconstituencyID;
	private String pParliament;
	private String parliament;
	private String pmandal;
	private String pvillage;
	private String phouseNo;
	private String pstreet;
	private String pPinCode;
	// new fields added
	private String dateOfBirth;
	private String telephone;
	private String houseNo;
	private String street;
	private String pinCode;
	private Boolean sameAsCA;
	private String education;
	private String profession;
	private String socialStatus;
	private String income;
	private String memberType;
	private String designation;
	private String effectiveDate;
	private String endingDate;
	private String fatherOrSpouseName;
	private String dobOption;
	private String age;
	private String windowTask;
	private String noOfFamilyMembers,noOfVoters;
	private String firstFamilyMemberName,secondFamilyMemberName,thirdFamilyMemberName;
	private String firstFamilyMemberRelation,secondFamilyMemberRelation,thirdFamilyMemberRelation;
	private String firstFamilyMemberRelationId,secondFamilyMemberRelationId,thirdFamilyMemberRelationId;
	private String firstFamilyMemberDOB,secondFamilyMemberDOB,thirdFamilyMemberDOB;
	private ResultStatus rs; 
	private List<String> skills;
	private List<String> trainingCamps;
	private List<String> languageOptions_English;
	private List<String> languageOptions_Hindi;
	private String cadreLevelState;
	private String cadreLevelDistrict;
	private String cadreLevelConstituency;
	private String cadreLevelMandal;
	private String cadreLevelVillage;
	//updating session variables
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> villagesList;
	private List<SelectOptionVO> districtsList_o;
	private List<SelectOptionVO> constituenciesList_o;
	private List<SelectOptionVO> mandalsList_o;
	private List<SelectOptionVO> villagesList_o;
	private List<SelectOptionVO> districtsList_c;
	private List<SelectOptionVO> constituenciesList_c;
	private List<SelectOptionVO> mandalsList_c;
	private List<SelectOptionVO> villagesList_c;
	private List<SelectOptionVO> designationsList;
	private Long defaultStateId;
	private Long defaultDistId;
	private Long defaultConstId;
	private Long defaultCadreLevelId;
	private String booth;
	private String pBooth;
	private Long partyCommittee;
	//to display or hide official address form inputs.if set to true, the form inputs are hidden, if set to false form inputs are shown
	private Boolean sameAsCAFlag;
	
	public CadreInfo getCadreInfo() {
		return cadreInfo;
	}

	public void setCadreInfo(CadreInfo cadreInfo) {
		this.cadreInfo = cadreInfo;
	}

	public List<String> getTrainingCamps() {
		return cadreInfo.getTrainingCamps();
	}

	public void setTrainingCamps(List<String> trainingCamps) {
		log.error("inside setting method of training camps"+trainingCamps.size());
		this.cadreInfo.setTrainingCamps(trainingCamps);
	}

	public String getCadreId() {
		return cadreInfo.getCadreId();
	}

	public void setCadreId(String cadreId) {
		this.cadreInfo.setCadreId(cadreId);
	}

	public Long getCadreID() {
		return cadreID;
	}

	public void setCadreID(Long cadreID) {
		this.cadreID = cadreID;
	}

	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}

	public String getFirstName() {
		return cadreInfo.getFirstName();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "First Name is Mandatory",  shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD,expression = "^[a-zA-Z ]+$", message = "First Name should not contain special characters and numbers", shortCircuit = true)
	public void setFirstName(String firstName) {
		this.cadreInfo.setFirstName(firstName);
	}
	
	public String getMiddleName() {
		return cadreInfo.getMiddleName(); 
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Middle Name should not contain special characters and numbers", shortCircuit = true)
	public void setMiddleName(String middleName) {
		this.cadreInfo.setMiddleName(middleName);
	}
	
	public String getLastName() {
		return cadreInfo.getLastName();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Last Name is Mandatory")
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Last Name should not contain special characters and numbers", shortCircuit = true)
	public void setLastName(String lastName) {
		this.cadreInfo.setLastName(lastName);
	}
	
	public String getFatherOrSpouseName() {
		return this.cadreInfo.getFatherOrSpouseName();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Father or Spouse Name is Mandatory", shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Father or Spouse Name should not contain special characters and numbers", shortCircuit = true)
	public void setFatherOrSpouseName(String fatherOrSpouseName) {
		this.cadreInfo.setFatherOrSpouseName(fatherOrSpouseName);
	}
	
	public String getGender() {
		return this.cadreInfo.getGender();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please select Gender")
	public void setGender(String gender) {
		this.cadreInfo.setGender(gender);
	}

	public String getDobOption() {
		return this.cadreInfo.getDobOption();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Select Date Of Birth or Age Option")
	public void setDobOption(String dobOption) {
		this.cadreInfo.setDobOption(dobOption);
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Select Date Of Birth")
	public void setDateOfBirth(String dateOfBirth) {
		this.cadreInfo.setDateOfBirth(dateOfBirth);
	}

	public String getDateOfBirth() {
		return cadreInfo.getDateOfBirth();
	}
	
	public String getMobile() {
		return cadreInfo.getMobile();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mobile Number is Mandatory", shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([789]{1})([02346789]{1})([0-9]{8})$", message = "Invalid Mobile Number", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Invalid Mobile number", minLength = "10", maxLength = "12")	
	public void setMobile(String mobile) {
		this.cadreInfo.setMobile(mobile);
	}

	public String getEmail() {
		return cadreInfo.getEmail();
	}

	@EmailValidator(message = "Invalid Email", shortCircuit = true)
	public void setEmail(String email) {
		this.cadreInfo.setEmail(email);
	}

	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[0-9 ]+[0-9]*$", message = "Telephone Number should not contain alphabets and special characters")
	public void setTelephone(String telephone) {
		this.cadreInfo.setTelephone(telephone);
	}

	public String getTelephone() {
		return cadreInfo.getTelephone();
	}

	public String getHouseNo() {
		return cadreInfo.getHouseNo();
	}

	public void setHouseNo(String houseNo) {
		this.cadreInfo.setHouseNo(houseNo);
	}

	public String getStreet() {
		return this.cadreInfo.getStreet();
	}

	public void setStreet(String street) {
		this.cadreInfo.setStreet(street);
	}

	public String getPinCode() {
		return this.cadreInfo.getPinCode();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "[0-9][0-9][0-9][0-9][0-9][0-9]", message = "Pin Code should contain digits ", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Pincode length should be 6 digits only", minLength = "6", maxLength = "6")
	public void setPinCode(String pinCode) {
		this.cadreInfo.setPinCode(pinCode);
	}

	public String getEducation() {
		return this.cadreInfo.getEducation().toString();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Select Valid Education")
	public void setEducation(String education) {
		this.cadreInfo.setEducation(new Long(education));
	}

	public String getProfession() {
		return this.cadreInfo.getProfession().toString();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Select Valid Profession/Occupation")
	public void setProfession(String profession) {
		this.cadreInfo.setProfession(new Long(profession));
	}

	public String getSocialStatus() {
		return this.cadreInfo.getSocialStatus().toString();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Social Category is Manatory")
	public void setSocialStatus(String socialStatus) {
		this.cadreInfo.setSocialStatus(new Long(socialStatus));
	}
	
	public String getState() {
		return cadreInfo.getState();
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid State Selection in Current Address")
	public void setState(String state) {
		this.cadreInfo.setState(state);
	}

	public String getDistrict() {
		return cadreInfo.getDistrict();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid District Selection in Current Address")
	public void setDistrict(String district) {
		this.cadreInfo.setDistrict(district);
	}

	public String getConstituencyID() {
		return cadreInfo.getConstituencyID().toString();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Constituency Selection in Current Address")
	public void setConstituencyID(String constituencyID) {
		this.cadreInfo.setConstituencyID(new Long(constituencyID));
	}

	public String getMandal() {
		return cadreInfo.getMandal();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Mandal Selection in Current Address")
	public void setMandal(String mandal) {
		this.cadreInfo.setMandal(mandal);
	}

	public String getVillage() {
		return cadreInfo.getVillage();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Village Selection in Current Address")
	public void setVillage(String village) {
		this.cadreInfo.setVillage(village);
	}

	public String getCadreLevel() {
		return cadreInfo.getCadreLevel().toString();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Selection for Cadre Level")
	public void setCadreLevel(String cadreLevel) {
		this.cadreInfo.setCadreLevel(new Long(cadreLevel));
	}

	public String getCadreLevelValue() {
		return cadreInfo.getStrCadreLevelValue();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Cadre Level Value is Mandatory",shortCircuit=true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Selection for Cadre Level Value")
	public void setCadreLevelValue(String cadreLevelValue) {
		this.cadreInfo.setStrCadreLevelValue(cadreLevelValue);
	}	

	public String getIncome() {
		return cadreInfo.getIncome();
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[0-9,]+$", message = "Income field accepts digits and commas only", shortCircuit = true)		
	public void setIncome(String income) {
		this.cadreInfo.setIncome(income);
	}

	public String getMemberType() {
		return cadreInfo.getMemberType();
	}
	
	@RequiredFieldValidator(type = ValidatorType.FIELD, message = "Please Select Cadre Type")
	public void setMemberType(String memberType) {
		this.cadreInfo.setMemberType(memberType);
	}
	
	public Boolean getSameAsCA() {
		return cadreInfo.getSameAsCA();
	}

	public void setSameAsCA(Boolean sameAsCA) {
		this.cadreInfo.setSameAsCA(sameAsCA);
	}

	public String getDesignation() {
		return cadreInfo.getDesignation().toString();
	}
		
	public void setDesignation(String designation) {
		this.cadreInfo.setDesignation(new Long(designation));
	}

	// permanenet addr fields
	public String getPstate() {
		return cadreInfo.getPstate();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid State Selection in Official Address")
	public void setPstate(String pstate) {
		this.cadreInfo.setPstate(pstate);
	}

	public String getPdistrict() {
		return cadreInfo.getPdistrict();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid District Selection in Official Address")
	public void setPdistrict(String pdistrict) {
		this.cadreInfo.setPdistrict(pdistrict);
	}

	public String getPconstituencyID() {
		return cadreInfo.getPconstituencyID().toString();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Constituency Selection in Official Address")
	public void setPconstituencyID(String pconstituencyID) {
		this.cadreInfo.setPconstituencyID(new Long(pconstituencyID));
	}

	public String getPmandal() {
		return cadreInfo.getPmandal();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Mandal/Tehsil Selection in Official Address")
	public void setPmandal(String pmandal) {
		this.cadreInfo.setPmandal(pmandal);
	}

	public String getPvillage() {
		return cadreInfo.getPvillage();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Village Selection in Official Address")
	public void setPvillage(String pvillage) {
		this.cadreInfo.setPvillage(pvillage);
	}

	public String getEffectiveDate() {
		return this.cadreInfo.getEffectiveDate();
	}

	
	public void setEffectiveDate(String effectiveDate) {
		this.cadreInfo.setEffectiveDate(effectiveDate);
	}

	public String getEndingDate() {
		return cadreInfo.getEndingDate();
	}

	public void setEndingDate(String endingDate) {
		this.cadreInfo.setEndingDate(endingDate);
	}

	public String getPhouseNo() {
		return cadreInfo.getPhouseNo();
	}

	public void setPhouseNo(String phouseNo) {
		this.cadreInfo.setPhouseNo(phouseNo);
	}

	public String getPstreet() {
		return cadreInfo.getPstreet();
	}

	public void setPstreet(String pstreet) {
		this.cadreInfo.setPstreet(pstreet);
	}

	public String getPPinCode() {
		return this.cadreInfo.getPpinCode();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([0-9]{6})$", message = "Pin Code should contain digits ", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Pincode length should be 6 digits only", minLength = "6", maxLength = "6")
	public void setPPinCode(String pPinCode) {
		this.cadreInfo.setPpinCode(pPinCode);
	}	

	public String getAge() {
		return this.cadreInfo.getAge();
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Enter Age", shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "([1-9][0-9]?)", message = "Age field accepts digits only")
	public void setAge(String age) {
		this.cadreInfo.setAge(age);
	}	

	public ResultStatus getRs() {
		return rs;
	}

	public void setRs(ResultStatus rs) {
		this.rs = rs;
	}	

	public List<String> getSkills() {
		return cadreInfo.getSkills();
	}

	public void setSkills(List<String> skills) {
		log.error("inside setting method"+skills.size());
		this.cadreInfo.setSkills(skills);
	}	

	public List<String> getLanguageOptions_English() {
		return cadreInfo.getLanguageOptions_English();
	}

	public void setLanguageOptions_English(List<String> languageOptions_English) {
	log.error("inside setter english method:"+languageOptions_English.size());
		this.cadreInfo.setLanguageOptions_English(languageOptions_English);
	}

	public String getFirstFamilyMemberRelationId() {
		return this.cadreInfo.getFirstFamilyMemberRelationId();
	}

	public void setFirstFamilyMemberRelationId(String firstFamilyMemberRelationId) {
		this.cadreInfo.setFirstFamilyMemberRelationId(firstFamilyMemberRelationId);
	}

	public String getSecondFamilyMemberRelationId() {
		return this.cadreInfo.getSecondFamilyMemberRelationId();
	}

	public void setSecondFamilyMemberRelationId(String secondFamilyMemberRelationId) {
		this.cadreInfo.setSecondFamilyMemberRelationId(secondFamilyMemberRelationId);
	}

	public String getThirdFamilyMemberRelationId() {
		return this.cadreInfo.getThirdFamilyMemberRelationId();
	}

	public void setThirdFamilyMemberRelationId(String thirdFamilyMemberRelationId) {
		this.cadreInfo.setThirdFamilyMemberRelationId(thirdFamilyMemberRelationId);
	}

	public List<String> getLanguageOptions_Hindi() {
		return cadreInfo.getLanguageOptions_Hindi();
	}

	public void setLanguageOptions_Hindi(List<String> languageOptions_Hindi) {
		log.error("inside setter hindi method:"+languageOptions_Hindi.size());
		this.cadreInfo.setLanguageOptions_Hindi(languageOptions_Hindi);
	}	

	public String getCadreLevelState() {
		return cadreInfo.getCadreLevelState();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid State Selection in Cadre Level Region")
	public void setCadreLevelState(String cadreLevelState) {
		this.cadreInfo.setCadreLevelState(cadreLevelState);
	}

	public String getCadreLevelDistrict() {
		return cadreInfo.getCadreLevelDistrict();
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid District Selection in Cadre Level Region")
	public void setCadreLevelDistrict(String cadreLevelDistrict) {
		this.cadreInfo.setCadreLevelDistrict(cadreLevelDistrict);
	}

	public String getCadreLevelConstituency() {
		return cadreInfo.getCadreLevelConstituency();
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Constituency Selection in Cadre Level Region")
	public void setCadreLevelConstituency(String cadreLevelConstituency) {
		this.cadreInfo.setCadreLevelConstituency(cadreLevelConstituency);
	}

	public String getCadreLevelMandal() {
		return cadreInfo.getCadreLevelMandal();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Mandal Selection in Cadre Level Region")
	public void setCadreLevelMandal(String cadreLevelMandal) {
		this.cadreInfo.setCadreLevelMandal(cadreLevelMandal);
	}

	public String getCadreLevelVillage() {
		return cadreInfo.getCadreLevelVillage();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Village Selection in Cadre Level Region")
	public void setCadreLevelVillage(String cadreLevelVillage) {
		this.cadreInfo.setCadreLevelVillage(cadreLevelVillage);
	}
	
	

	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}

	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}

	public List<SelectOptionVO> getVillagesList() {
		return villagesList;
	}

	public void setVillagesList(List<SelectOptionVO> villagesList) {
		this.villagesList = villagesList;
	}

	public List<SelectOptionVO> getDistrictsList_o() {
		return districtsList_o;
	}

	public void setDistrictsList_o(List<SelectOptionVO> districtsList_o) {
		this.districtsList_o = districtsList_o;
	}

	public List<SelectOptionVO> getConstituenciesList_o() {
		return constituenciesList_o;
	}

	public void setConstituenciesList_o(List<SelectOptionVO> constituenciesList_o) {
		this.constituenciesList_o = constituenciesList_o;
	}

	public List<SelectOptionVO> getMandalsList_o() {
		return mandalsList_o;
	}

	public void setMandalsList_o(List<SelectOptionVO> mandalsList_o) {
		this.mandalsList_o = mandalsList_o;
	}

	public List<SelectOptionVO> getVillagesList_o() {
		return villagesList_o;
	}

	public void setVillagesList_o(List<SelectOptionVO> villagesList_o) {
		this.villagesList_o = villagesList_o;
	}

	public List<SelectOptionVO> getDistrictsList_c() {
		return districtsList_c;
	}

	public void setDistrictsList_c(List<SelectOptionVO> districtsList_c) {
		this.districtsList_c = districtsList_c;
	}

	public List<SelectOptionVO> getConstituenciesList_c() {
		return constituenciesList_c;
	}

	public void setConstituenciesList_c(List<SelectOptionVO> constituenciesList_c) {
		this.constituenciesList_c = constituenciesList_c;
	}

	public List<SelectOptionVO> getMandalsList_c() {
		return mandalsList_c;
	}

	public void setMandalsList_c(List<SelectOptionVO> mandalsList_c) {
		this.mandalsList_c = mandalsList_c;
	}

	public List<SelectOptionVO> getVillagesList_c() {
		return villagesList_c;
	}

	public void setVillagesList_c(List<SelectOptionVO> villagesList_c) {
		this.villagesList_c = villagesList_c;
	}

	public List<SelectOptionVO> getDesignationsList() {
		return designationsList;
	}

	public void setDesignationsList(List<SelectOptionVO> designationsList) {
		this.designationsList = designationsList;
	}	

	public Long getDefaultStateId() {
		return defaultStateId;
	}

	public void setDefaultStateId(Long defaultStateId) {
		this.defaultStateId = defaultStateId;
	}

	public Long getDefaultDistId() {
		return defaultDistId;
	}

	public void setDefaultDistId(Long defaultDistId) {
		this.defaultDistId = defaultDistId;
	}

	public Long getDefaultConstId() {
		return defaultConstId;
	}

	public void setDefaultConstId(Long defaultConstId) {
		this.defaultConstId = defaultConstId;
	}

	public Long getDefaultCadreLevelId() {
		return defaultCadreLevelId;
	}

	public void setDefaultCadreLevelId(Long defaultCadreLevelId) {
		this.defaultCadreLevelId = defaultCadreLevelId;
	}
	
	// to make the active or normal cadre type radio button to pre select active radio button
	public String getDefaultCadreType() {
		return "Active";
	}
	
	// to pre select cadre level  based on user access type
	public Long getDefaultCadreLevel()
	{
		return this.defaultCadreLevelId;
	}	
	
	public Boolean getSameAsCAFlag() {
		return sameAsCAFlag;
	}
	public void setSameAsCAFlag(Boolean sameAsCAFlag) {
		this.sameAsCAFlag = sameAsCAFlag;
	}
	
	public String getNoOfFamilyMembers() {
		return this.cadreInfo.getNoOfFamilyMembers();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "([1-9][0-9]?)", message = "Please Give Number Value for No of Family Members")
	public void setNoOfFamilyMembers(String noOfFamilyMembers) {
		this.cadreInfo.setNoOfFamilyMembers(noOfFamilyMembers);
	}

	public String getNoOfVoters() {
		return this.cadreInfo.getNoOfVoters();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "([1-9][0-9]?)", message = "Please Give Number Value for No of Voters")
	public void setNoOfVoters(String noOfVoters) {
		this.cadreInfo.setNoOfVoters(noOfVoters);
	}

	public String getFirstFamilyMemberName() {
		return this.cadreInfo.getFirstFamilyMemberName();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD,expression = "^[a-zA-Z ]+$", message = "First Family Member Name should not contain special characters and numbers", shortCircuit = true)
	public void setFirstFamilyMemberName(String firstFamilyMemberName) {
		this.cadreInfo.setFirstFamilyMemberName(firstFamilyMemberName);
	}

	public String getSecondFamilyMemberName() {
		return this.cadreInfo.getSecondFamilyMemberName();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD,expression = "^[a-zA-Z ]+$", message = "Second Family Member Name should not contain special characters and numbers", shortCircuit = true)
	public void setSecondFamilyMemberName(String secondFamilyMemberName) {
		this.cadreInfo.setSecondFamilyMemberName(secondFamilyMemberName);
	}

	public String getThirdFamilyMemberName() {
		return this.cadreInfo.getThirdFamilyMemberName();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD,expression = "^[a-zA-Z ]+$", message = "Third Family Member Name should not contain special characters and numbers", shortCircuit = true)
	public void setThirdFamilyMemberName(String thirdFamilyMemberName) {
		this.cadreInfo.setThirdFamilyMemberName(thirdFamilyMemberName);
	}

	public String getFirstFamilyMemberRelation() {
		return this.cadreInfo.getFirstFamilyMemberRelation();
	}

	public void setFirstFamilyMemberRelation(String firstFamilyMemberRelation) {
		this.cadreInfo.setFirstFamilyMemberRelation(firstFamilyMemberRelation);
	}

	public String getSecondFamilyMemberRelation() {
		return this.cadreInfo.getSecondFamilyMemberRelation();
	}

	public void setSecondFamilyMemberRelation(String secondFamilyMemberRelation) {
		this.cadreInfo.setSecondFamilyMemberRelation(secondFamilyMemberRelation);
	}

	public String getThirdFamilyMemberRelation() {
		return this.cadreInfo.getThirdFamilyMemberRelation();
	}

	public void setThirdFamilyMemberRelation(String thirdFamilyMemberRelation) {
		this.cadreInfo.setThirdFamilyMemberRelation(thirdFamilyMemberRelation);
	}

	public String getFirstFamilyMemberDOB() {
		return this.cadreInfo.getFirstFamilyMemberDOB();
	}

	public void setFirstFamilyMemberDOB(String firstFamilyMemberDOB) {
		this.cadreInfo.setFirstFamilyMemberDOB(firstFamilyMemberDOB);
	}

	public String getSecondFamilyMemberDOB() {
		return this.cadreInfo.getSecondFamilyMemberDOB();
	}

	public void setSecondFamilyMemberDOB(String secondFamilyMemberDOB) {
		this.cadreInfo.setSecondFamilyMemberDOB(secondFamilyMemberDOB);
	}

	public String getThirdFamilyMemberDOB() {
		return this.cadreInfo.getThirdFamilyMemberDOB();
	}

	public void setThirdFamilyMemberDOB(String thirdFamilyMemberDOB) {
		this.cadreInfo.setThirdFamilyMemberDOB(thirdFamilyMemberDOB);
	}
	
	

	public String getPParliament() {
		return this.cadreInfo.getPParliament();
	}

	public void setPParliament(String parliament) {
		this.cadreInfo.setPParliament(parliament);
	}

	public String getParliament() {
		return this.cadreInfo.getParliament();
	}

	public void setParliament(String parliament) {
		this.cadreInfo.setParliament(parliament);
	}
	
	public void setServletRequest(HttpServletRequest req) {
		request = req;
		session = req.getSession();

	}

	public void setServletContext(ServletContext context) {
		this.context = context;

	}

	public String getBooth() {
		return this.cadreInfo.getBooth();
	}

	public void setBooth(String booth) {
		this.cadreInfo.setBooth(booth);
	}

	public String getPBooth() {
		return this.cadreInfo.getPBooth();
	}

	public void setPBooth(String booth) {
		this.cadreInfo.setPBooth(pBooth);
	}

	public Long getPartyCommittee() {
		return cadreInfo.getPartyCommittee();
	}

	public void setPartyCommittee(Long partyCommittee) {
		this.cadreInfo.setPartyCommittee(partyCommittee);
	}

	public String execute() throws Exception {
		log.debug("In The Excecute For Cader");
		session = request.getSession();
		
		String name1 = request.getParameter("firstName");
		String name2 = request.getParameter("lastName");
		String name3 = request.getParameter("constituency");
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		cadreInfo.setUserID(regVO.getRegistrationID());
		cadreInfo.setUserType(regVO.getUserType());
		cadreInfo.setUserPartyName(regVO.getPartyShortName());
		cadreInfo.setAccessType(regVO.getAccessType());
		
		/*
		if ("MP".equals(regVO.getAccessType())) {
			Long constituencyID = cadreInfo.getConstituencyID();
			Long pConstituencyID = cadreInfo.getPconstituencyID();
			List<SelectOptionVO> list = cadreManagementService.getStateDistricConstituencytByConstituencyID(constituencyID);
			List<SelectOptionVO> list1 = cadreManagementService.getStateDistricConstituencytByConstituencyID(pConstituencyID);
			SelectOptionVO obj = new SelectOptionVO();
			if (list.size() == 3)
			{	
				obj = list.get(1);
				cadreInfo.setDistrict(obj.getId().toString());
			}
			if (list1.size() == 3)
			{	
				obj = list1.get(1);
				cadreInfo.setPconstituencyID(obj.getId().longValue());
			}
			
		}*/
		System.out.println(cadreInfo.getMobile());
		rs = cadreManagementService.saveCader(cadreInfo, skills,windowTask);
		if (rs.getExceptionEncountered() == null)
		{
			cadreInfo = new CadreInfo();
			
			if("MLA".equals(regVO.getAccessType()))
			{
				log.debug("Access Type = MLA ****");
				session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.VILLAGES_C, new ArrayList<SelectOptionVO>());
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(4l);
				
							
			}else if("COUNTRY".equals(regVO.getAccessType()))
			{
				log.debug("Access Type = Country ****");
				session.setAttribute(ISessionConstants.DISTRICTS,new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.CONSTITUENCIES,new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS,new ArrayList<SelectOptionVO>());	
				session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.STATES_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.DISTRICTS_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.CONSTITUENCIES_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.VILLAGES_C, new ArrayList<SelectOptionVO>());
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(0l);
				
				
			}else if("STATE".equals(regVO.getAccessType())){
				log.debug("Access Type = State ****");
				session.setAttribute(ISessionConstants.CONSTITUENCIES,new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS,new ArrayList<SelectOptionVO>());	
				session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());
				//clear cadre level selections 
				session.setAttribute(ISessionConstants.DISTRICTS_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.CONSTITUENCIES_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.VILLAGES_C, new ArrayList<SelectOptionVO>());
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(2l);
				
				
				
			}else if("DISTRICT".equals(regVO.getAccessType())){
				log.debug("Access Type = District ****");			

				session.setAttribute(ISessionConstants.MANDALS,new ArrayList<SelectOptionVO>());	
				session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());	
				
				session.setAttribute(ISessionConstants.CONSTITUENCIES_C,new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS_C,new ArrayList<SelectOptionVO>());	
				session.setAttribute(ISessionConstants.VILLAGES_C, new ArrayList<SelectOptionVO>());
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(3l);	
				
			}			
					 
			session.setAttribute(ISessionConstants.DISTRICTS_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.CONSTITUENCIES_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.MANDALS_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.VILLAGES_O, new ArrayList<SelectOptionVO>());
			
			session.setAttribute(ISessionConstants.COMMITTEE_DESIGNATIONS, new ArrayList<SelectOptionVO>());
		}
		
		if (rs.getExceptionEncountered() != null)
			return "fail";
			
		return Action.SUCCESS;
	}
	
	public void validate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		/*int familyMembers = Integer.parseInt(cadreInfo.getNoOfFamilyMembers().trim().length()>0?cadreInfo.getNoOfFamilyMembers().trim():"0");
		int voters = Integer.parseInt(cadreInfo.getNoOfVoters().trim().length()>0?cadreInfo.getNoOfVoters().trim():"0");
		
		if(voters > familyMembers)
		{
			addFieldError("noOfVoters","Please Give Correct value for No of Voters");
		} */
		if("Party".equalsIgnoreCase(regVO.getUserType()) && cadreInfo.getMemberType().equalsIgnoreCase("Active"))
		{
			if(cadreInfo.getPartyCommittee() == 0)
			{
				addFieldError("partyCommittee","Please select Party Committee");
			}
			if(cadreInfo.getDesignation() == 0)
			{
				addFieldError("designation","Please select designation in Party Committee");
			}
			
			if(cadreInfo.getEffectiveDate().length() ==0 )
			{
				addFieldError("effectiveDate","Effective Date is Mandatory");
			}
			
			if(cadreInfo.getEndingDate().length() ==0 )
			{
				addFieldError("endingDate","EndingDate Date is Mandatory");
			}
			
			if(cadreInfo.getEffectiveDate().length() != 0 && cadreInfo.getEndingDate().length()!= 0)
			{
			try
			{
				if(sdf.parse(cadreInfo.getEffectiveDate()).after(sdf.parse(cadreInfo.getEndingDate())))
				{
					addFieldError("effectiveDate","Please Select correct EffectiveDate and Ending Date");
				}
			}catch (ParseException e) {
			
				e.printStackTrace();
			}
		  }
			
		}
		
	}

}
