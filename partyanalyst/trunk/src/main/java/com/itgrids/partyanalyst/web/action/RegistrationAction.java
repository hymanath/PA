package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.service.impl.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class RegistrationAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = -2526485695057725966L;
	 //private String registrationId;
	 private String firstName;
	 private String middleName;
	 private String lastName;
	 private String gender;
	 private String userName;
	 private String password; 
	 private String dateOfBirth;
	 private String email;
	 private String phone;
	 private String mobile;
	 private String address;
	 private String country;
	 private String pincode;
	 private String accessType;
	 private String accessValue;
	 private Long party;
	 private String day;
	 private String month;
	 private String year;
	 private UserService userService;
	 private List<String> type = new ArrayList<String>();
	 private String userType;
/*	 private List<String> dobDay = new ArrayList<String>();
	 private List<String> dobMonth = new ArrayList<String>();
	 private List<String> dobYear = new ArrayList<String>();*/
	 
	public void setUserService(UserService userService){
		this.userService  = userService;
	}
	
	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}
	
	 HttpServletRequest request;
	 HttpServletResponse response;
	 IRegistrationService registrationService;
	 HttpSession session;
	 

	/* public List<String> getDobDay() {
		return dobDay;
	}

	public void setDobDay(List<String> dobDay) {
		
		this.dobDay = dobDay;
	}

	public List<String> getDobMonth() {
		return dobMonth;
	}

	public void setDobMonth(List<String> dobMonth) {
		
		this.dobMonth = dobMonth;
	}

	public List<String> getDobYear() {
		return dobYear;
	}

	public void setDobYear(List<String> dobYear) {		
		this.dobYear = dobYear;
	}*/
	 
	 private RegistrationVO regVO = new RegistrationVO();
	 
	 public void setRegistrationService(IRegistrationService registrationService) {
			this.registrationService = registrationService;
	 }
	 
	public IRegistrationService getRegistrationService() {
		 return registrationService;
			
	}
	
	 public void setServletRequest(HttpServletRequest request){
		 this.request = request;
	 }

	 public void setServletResponse(HttpServletResponse response){
		 this.response = response;
	 }
	 
	public Long getParty() {
		return regVO.getParty();
	}

	public void setParty(Long party) {
		this.regVO.setParty(party);;
	}

	public String execute() throws Exception{
		
		//String dobDayValue = dobDay.get(0);
		//String dobMonthValue = dobMonth.get(0);
		//String dobYearValue = dobYear.get(0);
		
		String dob=day+"/"+month+"/"+year;
		System.out.println("date of birth is****" +dob);
		String db1=request.getParameter("day");
		System.out.println("date is ***" +db1);
		
		
		
		this.setDateOfBirth(dob);
		
		String requestStatus = registrationService.saveRegistration(regVO);
		String name = regVO.getFirstName() + " " + regVO.getLastName();
		if(requestStatus != "SUCCESS"){
			addActionError("UserName Already Exists");
			return ERROR;
		}
		else{
			session=request.getSession();
			//session.setAttribute("UserName", name);
			//session.setAttribute("loginStatus", "out");
			//regVO.setAccessType(request.getParameter("accessType"));
			
			//session.setAttribute("USER", regVO);
			session.setAttribute("USER_REG_SUCCESS", "User Successfully Registered");
			
			return SUCCESS;
		}
		
		
	}
 
	public String getFirstName() {
		return regVO.getFirstName();
		
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Firstname is required",shortCircuit=true)
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
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Lastname is required",shortCircuit=true)
	public void setLastName(String lastName) {
		this.regVO.setLastName(lastName);
	}

	public String getGender() {
		return regVO.getGender();
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "please select gender",shortCircuit=true)
	public void setGender(String gender) {
		this.regVO.setGender(gender);
	}

	public String getUserName() {
		return regVO.getUserName();
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Username is required",shortCircuit=true)	
	public void setUserName(String userName) {
		this.regVO.setUserName(userName);
	}
	public String getPassword() {
		return regVO.getPassword();
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Password is required",shortCircuit=true)
	public void setPassword(String password) {
		this.regVO.setPassword(password);
	}

	public String getDateOfBirth() {
		return regVO.getDateOfBirth();
	}

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
		this.userType = userType;
		this.regVO.setUserType(userType);
	}
	 
	 public String getUserType() {
			return regVO.getUserType();//;accessType;
		}
		 @RequiredStringValidator(type = ValidatorType.FIELD, message = "AccessType is required",shortCircuit=true)
		public void setAccessType(String accessType) {
			this.accessType = accessType;
			this.regVO.setAccessType(accessType);
		} 

	public String getAccessValue() {
		//return accessValue;
		return regVO.getAccessValue();
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Access value is required",shortCircuit=true)
	public void setAccessValue(String accessValue) {
		this.accessValue = accessValue;
		this.regVO.setAccessValue(accessValue);
	}
	
	 public String getDay() {
			return day;
		}
	 @RequiredStringValidator(type = ValidatorType.FIELD, message = "day is required",shortCircuit=true)
		public void setDay(String day) {
			this.day = day;
		}
	
		public String getMonth() {
			return month;
		}
		 @RequiredStringValidator(type = ValidatorType.FIELD, message = "month is required",shortCircuit=true)		
		public void setMonth(String month) {
			this.month = month;
		}

		public String getYear() {
			return year;
		}
		@RequiredStringValidator(type = ValidatorType.FIELD, message = "year is required",shortCircuit=true)	
		public void setYear(String year) {
			this.year = year;
		}
	

}