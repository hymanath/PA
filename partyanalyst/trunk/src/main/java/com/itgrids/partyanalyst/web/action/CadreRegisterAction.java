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
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
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
	private Long cadreId = null;
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
	private String sameAsCA;
	private String education;
	private String profession;
	private String socialStatus;
	private String income;
	private String memberType;
	private String annualIncome;
	private String designation;
	private String effectiveDate;
	private String endingDate;
	private String fatherOrSpouseName;
	private String dobOption;
	private String age;

	public Long getCadreId() {
		return cadreId;
	}

	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.cadreInfo.setDateOfBirth(dateOfBirth);
	}

	public String getDateOfBirth() {
		return cadreInfo.getDateOfBirth();
	}

	public void setTelephone(String telephone) {
		this.cadreInfo.setLandLineNo(telephone);
	}

	public String getTelephone() {
		return cadreInfo.getLandLineNo();
	}

	public String getHouseNo() {
		return cadreInfo.getHouseNo();
	}

	public void setHouseNo(String houseNo) {
		this.cadreInfo.setHouseNo(houseNo);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.cadreInfo.setStreet(street);
	}

	public String getPinCode() {
		return pinCode;
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([9]{1})([02346789]{1})([0-9]{8})$", message = "Pin Code should contain digits ", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Pincode should be 6 digits only", shortCircuit = true, minLength = "6", maxLength = "6")
	public void setPinCode(String pinCode) {
		this.cadreInfo.setPinCode(pinCode);
	}

	public String getEducation() {
		return education;
	}

	// @RequiredFieldValidator(type = ValidatorType.FIELD, message =
	// "Education Details Required",shortCircuit=true)
	public void setEducation(String education) {
		this.cadreInfo.setEducation(new Long(education));
	}

	public String getProfession() {
		return profession;
	}

	// @RequiredFieldValidator(type = ValidatorType.FIELD, message =
	// "Professon/Occupation Details Required",shortCircuit=true)
	public void setProfession(String profession) {
		this.cadreInfo.setOccupation(new Long(profession));
	}

	public String getSocialStatus() {
		return socialStatus;
	}

	// @RequiredFieldValidator(type = ValidatorType.FIELD, message =
	// "Social Category Required",shortCircuit=true)
	public void setSocialStatus(String socialStatus) {
		this.cadreInfo.setCasteCategory(new Long(socialStatus));
	}

	public String getFirstName() {
		return cadreInfo.getFirstName();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Firstname is required", shortCircuit = true)
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

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Lastname is required", shortCircuit = true)
	public void setLastName(String lastName) {
		this.cadreInfo.setLastName(lastName);
	}

	public String getGender() {
		return cadreInfo.getGender();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please select Gender", shortCircuit = true)
	public void setGender(String gender) {
		this.cadreInfo.setGender(gender);
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

	public String getMobile() {
		return cadreInfo.getMobile();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mobile number is required", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Invalid Mobile number", shortCircuit = true, minLength = "10", maxLength = "12")
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([9]{1})([02346789]{1})([0-9]{8})$", message = "Mobile Number should be digits", shortCircuit = true)
	public void setMobile(String mobile) {
		this.cadreInfo.setMobile(mobile);
	}

	public String getEmail() {
		return cadreInfo.getEmail();
	}

	public void setEmail(String email) {
		this.cadreInfo.setEmail(email);
	}

	public String getIncome() {
		return cadreInfo.getAnnualIncome();
	}

	public void setIncome(String income) {
		this.cadreInfo.setAnnualIncome(income);
	}

	public String getMemberType() {
		return cadreInfo.getMemberType();
	}

	public void setMemberType(String memberType) {
		this.cadreInfo.setMemberType(memberType);
	}

	public String getAnnualIncome() {
		return cadreInfo.getAnnualIncome();
	}

	public void setAnnualIncome(String annualIncome) {
		this.cadreInfo.setAnnualIncome(annualIncome);
	}

	public String getSameAsCA() {
		return cadreInfo.getSameAsCA();
	}

	public void setSameAsCA(String sameAsCA) {
		this.cadreInfo.setSameAsCA(sameAsCA);
	}

	public String getDesignation() {
		return cadreInfo.getDesignation();
	}

	public void setDesignation(String designation) {
		this.cadreInfo.setDesignation(designation);
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

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([9]{1})([02346789]{1})([0-9]{8})$", message = "Pin Code should contain digits only", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Pincode should be 6 digits only", shortCircuit = true, minLength = "6", maxLength = "6")
	public void setPPinCode(String pinCode) {
		this.cadreInfo.setPpinCode(pPinCode);
	}

	public String getFatherOrSpouseName() {
		return this.cadreInfo.getFatherOrSpouseName();
	}

	public void setFatherOrSpouseName(String fatherOrSpouseName) {
		this.cadreInfo.setFatherOrSpouseName(fatherOrSpouseName);
	}

	public String getDobOption() {
		return this.cadreInfo.getDobOption();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Select Date Of Birth or Age", shortCircuit = true)
	public void setDobOption(String dobOption) {
		this.cadreInfo.setDobOption(dobOption);
	}

	public String getAge() {
		return this.cadreInfo.getAge();
	}

	public void setAge(String age) {
		this.cadreInfo.setAge(age);
	}

	public String execute() throws Exception {
		log.debug("In The Excecute For Cader");
		session = request.getSession();
		String[] skills = null;
		String[] trainingCamps = null;
		String[] languageOptions_English = null;
		String[] languageOptions_Hindi = null;
		languageOptions_English = request
				.getParameterValues("languageOptions_English");
		languageOptions_Hindi = request
				.getParameterValues("languageOptions_Hindi");
		String name1 = request.getParameter("firstName");
		String name2 = request.getParameter("lastName");
		String name3 = request.getParameter("constituency");

		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");

		System.out.println("dobOption::::::::::::::::"
				+ request.getParameter("dobOption"));
		cadreInfo.setUserID(regVO.getRegistrationID());
		cadreInfo.setUserType(regVO.getUserType());
		cadreInfo.setUserPartyName(regVO.getPartyShortName());

		if ("MP".equals(regVO.getAccessType())) {
			Long constituencyID = cadreInfo.getConstituencyID();
			List<SelectOptionVO> list = cadreManagementService
					.getStateDistricConstituencytByConstituencyID(constituencyID);
			SelectOptionVO obj = new SelectOptionVO();
			if (list.size() == 3)
				obj = list.get(1);
			cadreInfo.setDistrict(obj.getId().toString());
		}

		if (IConstants.USER_TYPE_PARTY.equals(regVO.getUserType())) {
			skills = request.getParameterValues("skills");
			trainingCamps = request.getParameterValues("trainingCamps");
		}
		if (languageOptions_English != null
				&& languageOptions_English.length > 0)
			cadreInfo.setLanguageOptions_English(languageOptions_English);
		if (languageOptions_Hindi != null && languageOptions_Hindi.length > 0)
			cadreInfo.setLanguageOptions_Hindi(languageOptions_Hindi);
		cadreId = cadreManagementService.saveCader(cadreInfo, skills,
				trainingCamps);
		String result = Action.SUCCESS;
		if (cadreId == null)
			result = "fail";
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
