package com.itgrids.partyanalyst.web.action;

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
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class CadreRegisterAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware {

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
	private String constituency;
	private String mandal;
	private String village;
	private String cadreLevel = "Others";
	private String cadreLevelValue = "0";
	private String mobile;
	private String email;
	private String cadreId;
	private Long cadreID = null;
	private String pstate;
	private String pdistrict;
	private String pconstituency;
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
	private ResultStatus rs; 
	private List<String> skills;
	private List<String> trainingCamps;
	private List<String> languageOptions_English;
	private List<String> languageOptions_Hindi;
	
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

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Firstname is required")
	public void setFirstName(String firstName) {
		this.cadreInfo.setFirstName(firstName);
	}
	
	public String getMiddleName() {
		return cadreInfo.getMiddleName();
	}

	public void setMiddleName(String middleName) {
		this.cadreInfo.setMiddleName(middleName);
	}
	
	public String getLastName() {
		return cadreInfo.getLastName();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Lastname is required")
	public void setLastName(String lastName) {
		this.cadreInfo.setLastName(lastName);
	}
	
	public String getFatherOrSpouseName() {
		return this.cadreInfo.getFatherOrSpouseName();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Father or Spouse Name Required")
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

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Select Date Of Birth or Age")
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

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mobile number is required", shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([789]{1})([02346789]{1})([0-9]{8})$", message = "Invalid Mobile number", shortCircuit = true)
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

	public void setTelephone(String telephone) {
		this.cadreInfo.setTelephone(telephone);
	}

	public String getTelephone() {
		return cadreInfo.getTelephone();
	}

	/**
	 * @return
	 */
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
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Pincode should be 6 digits only", minLength = "6", maxLength = "6")
	public void setPinCode(String pinCode) {
		this.cadreInfo.setPinCode(pinCode);
	}

	public String getEducation() {
		return this.cadreInfo.getEducation().toString();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Education Details Required")
	public void setEducation(String education) {
		this.cadreInfo.setEducation(new Long(education));
	}

	public String getProfession() {
		return this.cadreInfo.getProfession().toString();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message ="Professon/Occupation Details Required")
	public void setProfession(String profession) {
		this.cadreInfo.setProfession(new Long(profession));
	}

	public String getSocialStatus() {
		return this.cadreInfo.getSocialStatus().toString();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Social Category Required")
	public void setSocialStatus(String socialStatus) {
		this.cadreInfo.setSocialStatus(new Long(socialStatus));
	}
	
	public String getState() {
		return cadreInfo.getState();
	}

	public void setState(String state) {
		this.cadreInfo.setState(state);
	}

	public String getDistrict() {
		return cadreInfo.getDistrict();
	}

	public void setDistrict(String district) {
		this.cadreInfo.setDistrict(district);
	}

	public String getConstituency() {
		return cadreInfo.getConstituencyID().toString();
	}

	public void setConstituency(String constituency) {
		this.cadreInfo.setConstituencyID(new Long(constituency));
	}

	public String getMandal() {
		return cadreInfo.getMandal();
	}

	public void setMandal(String mandal) {
		this.cadreInfo.setMandal(mandal);
	}

	public String getVillage() {
		return cadreInfo.getVillage();
	}

	public void setVillage(String village) {
		this.cadreInfo.setVillage(village);
	}

	public String getCadreLevel() {
		return cadreInfo.getCadreLevel().toString();
	}

	public void setCadreLevel(String cadreLevel) {
		this.cadreInfo.setCadreLevel(new Long(cadreLevel));
	}

	public String getCadreLevelValue() {
		return cadreInfo.getStrCadreLevelValue();
	}

	public void setCadreLevelValue(String cadreLevelValue) {
		this.cadreInfo.setStrCadreLevelValue(cadreLevelValue);
	}	

	public String getIncome() {
		return cadreInfo.getIncome();
	}

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

	public void setPstate(String pstate) {
		this.cadreInfo.setPstate(pstate);
	}

	public String getPdistrict() {
		return cadreInfo.getPdistrict();
	}

	public void setPdistrict(String pdistrict) {
		this.cadreInfo.setPdistrict(pdistrict);
	}

	public String getPconstituency() {
		return cadreInfo.getPconstituencyID().toString();
	}

	public void setPconstituency(String pconstituency) {
		this.cadreInfo.setPconstituencyID(new Long(pconstituency));
	}

	public String getPmandal() {
		return cadreInfo.getPmandal();
	}

	public void setPmandal(String pmandal) {
		this.cadreInfo.setPmandal(pmandal);
	}

	public String getPvillage() {
		return cadreInfo.getPvillage();
	}

	public void setPvillage(String pvillage) {
		this.cadreInfo.setPvillage(pvillage);
	}

	public String getEffectiveDate() {
		return this.cadreInfo.getEffectiveDate();
	}

	@RequiredFieldValidator(type = ValidatorType.FIELD, message = "Please Select Effective Date")
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
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Pincode should be 6 digits only", minLength = "6", maxLength = "6")
	public void setPPinCode(String pinCode) {
		this.cadreInfo.setPpinCode(pinCode);
	}	

	public String getAge() {
		return this.cadreInfo.getAge();
	}

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

	public List<String> getLanguageOptions_Hindi() {
		return cadreInfo.getLanguageOptions_Hindi();
	}

	public void setLanguageOptions_Hindi(List<String> languageOptions_Hindi) {
		log.error("inside setter hindi method:"+languageOptions_Hindi.size());
		this.cadreInfo.setLanguageOptions_Hindi(languageOptions_Hindi);
	}

	public String execute() throws Exception {
		log.debug("In The Excecute For Cader");
		session = request.getSession();
		//String[] skills = null;
		//String[] trainingCamps = null;
		//String[] languageOptions_English = null;
		//String[] languageOptions_Hindi = null;
		//languageOptions_English = request.getParameterValues("languageOptions_English");
		//languageOptions_Hindi = request.getParameterValues("languageOptions_Hindi");
		String name1 = request.getParameter("firstName");
		String name2 = request.getParameter("lastName");
		String name3 = request.getParameter("constituency");
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		cadreInfo.setUserID(regVO.getRegistrationID());
		cadreInfo.setUserType(regVO.getUserType());
		cadreInfo.setUserPartyName(regVO.getPartyShortName());
		log.error("languages size:"+cadreInfo.getLanguageOptions_English());
		log.error("skills in execute:"+cadreInfo.getSkills());
		log.error("training camps execute :"+cadreInfo.getTrainingCamps());
		
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
			
		}

		if (IConstants.USER_TYPE_PARTY.equals(regVO.getUserType()) && IConstants.BJP.equals(regVO.getPartyShortName())) {
			//skills = request.getParameterValues("skills");
			//log.error("skills length:"+skills.length);
			//trainingCamps = request.getParameterValues("trainingCamps");
		}
		/*if (languageOptions_English != null && languageOptions_English.length > 0)
			cadreInfo.setLanguageOptions_English(languageOptions_English);
		if (languageOptions_Hindi != null && languageOptions_Hindi.length > 0)
			cadreInfo.setLanguageOptions_Hindi(languageOptions_Hindi);*/
		rs = cadreManagementService.saveCader(cadreInfo, skills,windowTask);
		
		if (rs.getExceptionEncountered() != null)
			return "fail";
		if (rs.getExceptionEncountered() == null && windowTask == IConstants.UPDATE_EXISTING)
			return "update";
		return Action.SUCCESS;
	}

	public void setServletRequest(HttpServletRequest req) {
		request = req;
		session = req.getSession();

	}

	public void setServletContext(ServletContext context) {
		this.context = context;

	}

}
