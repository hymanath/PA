package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class RegistrationAction extends ActionSupport implements
		ServletRequestAware{

	private static final long serialVersionUID = -2526485695057725966L;
	private HttpServletRequest request;
	private IRegistrationService registrationService;
	private IStaticDataService staticDataService;
	private RegistrationVO regVO = new RegistrationVO();
	 
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	 
	public IRegistrationService getRegistrationService() {
		 return registrationService;
			
	}
	
	 public void setServletRequest(HttpServletRequest request){
		 this.request = request;
	 }

	public Long getParty() {
		return regVO.getParty();
	}

	public void setParty(Long party) {
		this.regVO.setParty(party);
	}
	
	public String execute() throws Exception{
		
		String requestStatus = registrationService.saveRegistration(regVO);
		if( !"SUCCESS".equalsIgnoreCase(requestStatus)){	
			addActionError("UserName Already Exists");
			return ERROR;
		}
		else{
			HttpSession session = request.getSession();
			session.setAttribute("USER_REG_SUCCESS", "User Successfully Registered");
			return SUCCESS;
		}

	}
 
	public String getFirstName() {
		return regVO.getFirstName();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Firstname is required")
	public void setFirstName(String firstName) {
		this.regVO.setFirstName(firstName);
		
	}

	public String getMiddleName() {
		return regVO.getMiddleName();
	}

	public void setMiddleName(String middleName) {
		this.regVO.setMiddleName(middleName);
	}

	public String getLastName() {
		return regVO.getLastName();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Lastname is required")
	public void setLastName(String lastName) {
		this.regVO.setLastName(lastName);
	}

	public String getGender() {
		return regVO.getGender();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please select gender")
	public void setGender(String gender) {
		this.regVO.setGender(gender);
	}

	public String getUserName() {
		return regVO.getUserName();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Username is required")	
	public void setUserName(String userName) {
		this.regVO.setUserName(userName);
	}
	
	public String getPassword() {
		return regVO.getPassword();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Password is required")
	public void setPassword(String password) {
		this.regVO.setPassword(password);
	}

	public String getDateOfBirth() {
		return regVO.getDateOfBirth();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Date Of Birth is required")
	public void setDateOfBirth(String dateOfBirth) {
		this.regVO.setDateOfBirth(dateOfBirth);
	}

	public String getEmail() {
		return regVO.getEmail();
	}

	public void setEmail(String email) {
		this.regVO.setEmail(email);
	}

	public String getPhone() {
		return regVO.getPhone();
	}

	public void setPhone(String phone) {
		this.regVO.setPhone(phone);
	}

	public String getMobile() {
		return regVO.getMobile();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mobile number is required",shortCircuit=true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD,message = "mobile number should be 10 digits", shortCircuit = true,  minLength = "10",  maxLength = "10")
	@RegexFieldValidator( type = ValidatorType.FIELD, expression = "^([9]{1})([02346789]{1})([0-9]{8})$", message="mobile number should contain digits",shortCircuit = true)
	public void setMobile(String mobile) {
		this.regVO.setMobile(mobile);
	}
	
	public String getAddress() {
		return regVO.getAddress();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Address is required",shortCircuit=true)
	public void setAddress(String address) {
		this.regVO.setAddress(address);
	}
	
	public String getCountry() {
		return regVO.getCountry();
	}

	public void setCountry(String country) {
		this.regVO.setCountry(country);
	}
	
	public String getPincode() {
		return regVO.getPincode();
	}

	public void setPincode(String pincode) {
		this.regVO.setPincode(pincode);
	}

	public String getAccessType() {
		return regVO.getAccessType();//;accessType;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "User Type is required",shortCircuit=true)
	public void setUserType(String userType) {
		this.regVO.setUserType(userType);
	}
	 
	public String getUserType() {
		return regVO.getUserType();//;accessType;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "AccessType is required",shortCircuit=true)
	public void setAccessType(String accessType) {
		this.regVO.setAccessType(accessType);
	} 

	public String getAccessValue() {
		return regVO.getAccessValue();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Access value is required",shortCircuit=true)
	public void setAccessValue(String accessValue) {
		this.regVO.setAccessValue(accessValue);
	}
	
}