package com.itgrids.partyanalyst.web.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import com.opensymphony.xwork2.ActionSupport;
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
	private final static Logger log = Logger.getLogger(CadreRegisterAction.class);

	public void setCadreManagementService(CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	private CadreInfo cadreInfo = new CadreInfo();
	//form input variables
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String state;
	private String district;
	private String constituencyID;
	private String mandal;
	private String village;
	private Long cadreLevel;
	private String cadreLevelValue;
	private String mobile;
	private String email;
	private String cadreId;
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
	private List<Long> skills;
	private List<Long> cadreRoles;
	private List<Long> trainingCamps;
	private List<String> languageOptions_English;
	private List<String> languageOptions_Hindi;
	private String cadreLevelState;
	private String cadreLevelDistrict;
	private String cadreLevelConstituency;	
	private String cadreLevelBooth;
	private Long defaultCadreLevelId;
	private String booth;
	private String pBooth;
	private Long partyCommittee;
	
	private File uploadImage;
    private String uploadImageContentType;
    private String uploadImageFileName;
	
	private String defaultState;
	private String defaultDist;
	private String defaultConst;
	
	//to display or hide official address form inputs.if set to true, the form inputs are hidden, if set to false form inputs are shown
	private Boolean sameAsCAFlag;
	
	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadImageContentType() {
		return uploadImageContentType;
	}

	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}

	public CadreInfo getCadreInfo() {
		return cadreInfo;
	}

	public void setCadreInfo(CadreInfo cadreInfo) {
		this.cadreInfo = cadreInfo;
	}

	public List<Long> getTrainingCamps() {
		return cadreInfo.getTrainingCamps();
	}

	public void setTrainingCamps(List<Long> trainingCamps) {
		log.error("inside setting method of training camps"+trainingCamps.size());
		this.cadreInfo.setTrainingCamps(trainingCamps);
	}

	public String getCadreId() {
		return cadreInfo.getCadreId();
	}

	public void setCadreId(String cadreId) {
		this.cadreInfo.setCadreId(cadreId);
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
	
	public void setDateOfBirth(String dateOfBirth) {
		this.cadreInfo.setDateOfBirth(dateOfBirth);
	}

	public String getDateOfBirth() {
		return cadreInfo.getDateOfBirth();
	}
	
	public String getMobile() {
		return cadreInfo.getMobile();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([789]{1})([0123456789]{1})([0-9]{8})$", message = "Invalid Mobile Number", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Invalid Mobile number...", minLength = "10", maxLength = "12")	
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

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Caste Category is Mandatory")
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

	public Long getCadreLevel() {
		return cadreInfo.getCadreLevel();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Selection for Cadre Level")
	public void setCadreLevel(Long cadreLevel) {
		this.cadreInfo.setCadreLevel(cadreLevel);
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
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "([0-9][0-9]?)", message = "Age field accepts digits only")
	public void setAge(String age) {
		this.cadreInfo.setAge(age);
	}	

	public ResultStatus getRs() {
		return rs;
	}

	public void setRs(ResultStatus rs) {
		this.rs = rs;
	}	

	public List<Long> getSkills() {
		return cadreInfo.getSkills();
	}

	public void setSkills(List<Long> skills) {
		this.cadreInfo.setSkills(skills);
	}	

	public List<Long> getCadreRoles() {
		return cadreInfo.getCadreRoles();
	}

	public void setCadreRoles(List<Long> cadreRoles) {
		this.cadreInfo.setCadreRoles(cadreRoles);
	}

	public List<String> getLanguageOptions_English() {
		return cadreInfo.getLanguageOptions_English();
	}

	public void setLanguageOptions_English(List<String> languageOptions_English) {
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
	
	//@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid State Selection in Cadre Level")
	public void setCadreLevelState(String cadreLevelState) {
		this.cadreInfo.setCadreLevelState(cadreLevelState);
	}

	public String getCadreLevelDistrict() {
		return cadreInfo.getCadreLevelDistrict();
	}
	
	//@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid District Selection in Cadre Level")
	public void setCadreLevelDistrict(String cadreLevelDistrict) {
		this.cadreInfo.setCadreLevelDistrict(cadreLevelDistrict);
	}

	public String getCadreLevelConstituency() {
		return cadreInfo.getCadreLevelConstituency();
	}
	
	//@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Constituency Selection in Cadre Level")
	public void setCadreLevelConstituency(String cadreLevelConstituency) {
		this.cadreInfo.setCadreLevelConstituency(cadreLevelConstituency);
	}

	public String getCadreLevelMandal() {
		return cadreInfo.getCadreLevelMandal();
	}

	//@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Mandal/Municipal/Corp/GMC Selection in Cadre Level")
	public void setCadreLevelMandal(String cadreLevelMandal) {
		this.cadreInfo.setCadreLevelMandal(cadreLevelMandal);
	}

	public String getCadreLevelVillage() {
		return cadreInfo.getCadreLevelVillage();
	}
	//@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Village/Ward/Division Selection in Cadre Level")
	public void setCadreLevelVillage(String cadreLevelVillage) {
		this.cadreInfo.setCadreLevelVillage(cadreLevelVillage);
	}	

	public String getCadreLevelBooth() {
		return this.cadreInfo.getCadreLevelBooth();
	}
	//@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Booth Selection in Cadre Level")
	public void setCadreLevelBooth(String cadreLevelBooth) {
		this.cadreInfo.setCadreLevelBooth(cadreLevelBooth);
	}

	public Long getDefaultCadreLevelId() {
		return defaultCadreLevelId;
	}

	public void setDefaultCadreLevelId(Long defaultCadreLevelId) {
		this.defaultCadreLevelId = defaultCadreLevelId;			
	}	
	
	// to make the active or normal cadre type radio button to pre select active radio button
	public String getDefaultCadreType() {
		if(this.cadreInfo.getMemberType() != null && !this.cadreInfo.getMemberType().isEmpty())
			return this.cadreInfo.getMemberType();
		return "Active";
	}
	
	// to pre select cadre level  based on user access type
	public Long getDefaultCadreLevel()
	{
		if(this.defaultCadreLevelId == null)
			this.defaultCadreLevelId = cadreInfo.getCadreLevel();
		return this.defaultCadreLevelId;
	}	
	
	public String getDefaultState() {
		return defaultState;
	}

	public void setDefaultState(String defaultState) {
		this.defaultState = defaultState;
	}

	public String getDefaultDist() {
		return defaultDist;
	}

	public void setDefaultDist(String defaultDist) {
		this.defaultDist = defaultDist;
	}

	public String getDefaultConst() {
		return defaultConst;
	}

	public void setDefaultConst(String defaultConst) {
		this.defaultConst = defaultConst;
	}

	public String getDefaultStateId() {
		if(this.cadreInfo.getCadreLevelState() != null && !this.cadreInfo.getCadreLevelState().isEmpty())
			return this.cadreInfo.getCadreLevelState();
		return getDefaultState();
	}
	
	
	public String getDefaultDistId() {
		if(this.cadreInfo.getCadreLevelDistrict() != null && !this.cadreInfo.getCadreLevelDistrict().isEmpty())
			return this.cadreInfo.getCadreLevelDistrict();
		return getDefaultDist();
	}	

	public String getDefaultConstId() {
		if(this.cadreInfo.getCadreLevelConstituency() != null && !this.cadreInfo.getCadreLevelConstituency().isEmpty())
			return this.cadreInfo.getCadreLevelConstituency();
		return getDefaultConst();
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

	public void setPBooth(String pBooth) {
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
		
		//cadreInfo.setUserID(regVO.getRegistrationID());
		cadreInfo.setUserID(regVO.getParentUserId() == null ? regVO.getRegistrationID() : regVO.getParentUserId());
		cadreInfo.setUserType(regVO.getUserType());
		cadreInfo.setUserPartyName(regVO.getPartyShortName());
		cadreInfo.setAccessType(regVO.getAccessType());
		
		rs = cadreManagementService.saveCader(cadreInfo, skills,windowTask);
		
		if (rs.getExceptionEncountered() == null)
		{
			String result = uploadCadreImage(rs.getResultState());
			
			if(result != null)
				cadreManagementService.updateCadreImage(rs.getResultState(),rs.getResultState().toString()+"."+uploadImageContentType.split("/")[1]);
			else if(windowTask.equalsIgnoreCase(IConstants.NEW))
				cadreManagementService.updateCadreImage(rs.getResultState(),"human.jpg");
			
			cadreInfo = new CadreInfo();
			
			if("MLA".equals(regVO.getAccessType()))
			{
				log.debug("Access Type = MLA ****");
				session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.BOOTHS, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.VILLAGES_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.BOOTHS_C, new ArrayList<SelectOptionVO>());
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(4l);				
							
			}else if("COUNTRY".equals(regVO.getAccessType()))
			{
				log.debug("Access Type = Country ****");
				session.setAttribute(ISessionConstants.DISTRICTS,new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.CONSTITUENCIES,new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS,new ArrayList<SelectOptionVO>());	
				session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.BOOTHS, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.STATES_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.DISTRICTS_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.CONSTITUENCIES_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.VILLAGES_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.BOOTHS_C, new ArrayList<SelectOptionVO>());
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(0l);				
				
			}else if("STATE".equals(regVO.getAccessType())){
				log.debug("Access Type = State ****");
				session.setAttribute(ISessionConstants.CONSTITUENCIES,new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS,new ArrayList<SelectOptionVO>());	
				session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.BOOTHS, new ArrayList<SelectOptionVO>());
				//clear cadre level selections 
				session.setAttribute(ISessionConstants.DISTRICTS_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.CONSTITUENCIES_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.VILLAGES_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.BOOTHS_C, new ArrayList<SelectOptionVO>());
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(2l);				
				
			}else if("DISTRICT".equals(regVO.getAccessType())){
				log.debug("Access Type = District ****");			

				session.setAttribute(ISessionConstants.MANDALS,new ArrayList<SelectOptionVO>());	
				session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());	
				session.setAttribute(ISessionConstants.BOOTHS, new ArrayList<SelectOptionVO>());
				
				session.setAttribute(ISessionConstants.CONSTITUENCIES_C,new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.MANDALS_C,new ArrayList<SelectOptionVO>());	
				session.setAttribute(ISessionConstants.VILLAGES_C, new ArrayList<SelectOptionVO>());
				session.setAttribute(ISessionConstants.BOOTHS_C, new ArrayList<SelectOptionVO>());
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(3l);				
			}					 
			session.setAttribute(ISessionConstants.DISTRICTS_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.CONSTITUENCIES_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.MANDALS_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.VILLAGES_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.BOOTHS_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.COMMITTEE_DESIGNATIONS, new ArrayList<SelectOptionVO>());
		}
		
		if (rs.getExceptionEncountered() != null)
			return "fail";
			
		return Action.SUCCESS;
	}
	
	public String uploadCadreImage(Long cadreId)
	{
		try{
			String pathSeperator = System.getProperty("file.separator");
			String filePath = null;
			
			if(request.getRequestURL().toString().contains("www.partyanalyst.com"))
				filePath = pathSeperator + "var" + pathSeperator + "www" + pathSeperator + "vsites" + pathSeperator + "partyanalyst.com" + pathSeperator + "httpdocs" + pathSeperator +"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator;
			else
				filePath = context.getRealPath("/")+"images\\"+IConstants.CADRE_IMAGES+"\\";
			
			log.info("Cadre File Path -- "+filePath);
			
			BufferedImage image = ImageIO.read(this.uploadImage);
			
			
			if(image == null)
				return null;
			log.info("Image is Read");
			String constiName[] = uploadImageContentType.split("/");
			String fileName = filePath+cadreId.toString()+"."+constiName[1];
			log.info("file name -- "+fileName);
			//String imageName =  cadreId.toString()+"."+constiName[1];
			
			FileImageOutputStream filName = new FileImageOutputStream(new File(fileName));
			
			ImageIO.write(image, constiName[1],filName);
			log.info("file uploaded");
            filName.close();
            return SUCCESS;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public void validate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		String familyMbrsCount = cadreInfo.getNoOfFamilyMembers().trim(); 
		String votersCount = cadreInfo.getNoOfVoters().trim();	
		if(cadreInfo.getDobOption() != null)
		{
			if(cadreInfo.getDobOption().equalsIgnoreCase("Date Of Birth") && cadreInfo.getDateOfBirth() != null && cadreInfo.getDobOption().isEmpty())
			{
				addFieldError("dateOfBirth", "Please Enter Date Of Birth");
			}
			else if(cadreInfo.getDobOption().equalsIgnoreCase("Age") && cadreInfo.getAge() != null && cadreInfo.getDobOption().trim().isEmpty()){
				 addFieldError("age", "please Enter Age");
			}
		}
		if(familyMbrsCount.isEmpty() && !(votersCount.isEmpty()))
			addFieldError("noOfFamilyMembers","Please Enter No of Family Members");
		if(!(familyMbrsCount.isEmpty()) && votersCount.isEmpty())
			addFieldError("noOfVoters","Please Enter No of Voters");		
		if(!(familyMbrsCount.isEmpty() || votersCount.isEmpty()))
		{
			if(StringUtils.isNumeric(familyMbrsCount) && StringUtils.isNumeric(votersCount))
			{
				if(Integer.parseInt(familyMbrsCount) < Integer.parseInt(votersCount))
					addFieldError("noOfVoters","No of Voters in family should not be greater than No of Family Members");				
			}	
		}		
		if("Party".equalsIgnoreCase(regVO.getUserType()) && cadreInfo.getMemberType().equalsIgnoreCase("Active"))
		{
			/*if(cadreInfo.getPartyCommittee() == 0)
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
			}*/
			
						
			if(cadreInfo.getEffectiveDate()!=null && cadreInfo.getEndingDate()!=null && cadreInfo.getEffectiveDate().length() != 0 && cadreInfo.getEndingDate().length()!= 0
					)
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
