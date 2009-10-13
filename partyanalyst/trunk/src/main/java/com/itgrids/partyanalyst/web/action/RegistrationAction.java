package com.itgrids.partyanalyst.web.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.*;

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
	 
	 HttpServletRequest request;
	 HttpServletResponse response;
	 IRegistrationService registrationService;
	 HttpSession session;
	 
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
	public String execute() throws Exception{
		
		String requestStatus = registrationService.saveRegistration(regVO);
		if(requestStatus != "SUCCESS"){
			addActionError("UserName Already Exists");
			return ERROR;
		}
		else{
			session=request.getSession();
			session.setAttribute("userName", regVO.getUserName());
			session.setAttribute("password",regVO.getPassword());
			session.setAttribute("registrationStatus", true);
			return SUCCESS;
		}
		
		
	}
    
   
	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getFirstName() {
		return regVO.getFirstName();
		
	}

	public void setFirstName(String firstName) {
		this.regVO.setFirstName(firstName);
		
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getMiddleName() {
		return regVO.getMiddleName();
	}

	public void setMiddleName(String middleName) {
		this.regVO.setMiddleName(middleName);
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getLastName() {
		return regVO.getLastName();
	}

	public void setLastName(String lastName) {
		this.regVO.setLastName(lastName);
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getGender() {
		return regVO.getGender();
	}

	public void setGender(String gender) {
		this.regVO.setGender(gender);
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getUserName() {
		return regVO.getUserName();
	}

	public void setUserName(String userName) {
		this.regVO.setUserName(userName);
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getPassword() {
		return regVO.getPassword();
	}

	public void setPassword(String password) {
		this.regVO.setPassword(password);
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getDateOfBirth() {
		return regVO.getDateOfBirth();
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.regVO.setDateOfBirth(dateOfBirth);
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	@EmailValidator(key="invalidEmail",shortCircuit=true)
	public String getEmail() {
		return regVO.getEmail();
	}

	public void setEmail(String email) {
		this.regVO.setEmail(email);
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getPhone() {
		return regVO.getPhone();
	}

	public void setPhone(String phone) {
		this.regVO.setPhone(phone);
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getMobile() {
		return regVO.getMobile();
	}

	public void setMobile(String mobile) {
		this.regVO.setMobile(mobile);
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getAddress() {
		return regVO.getAddress();
	}

	public void setAddress(String address) {
		this.regVO.setAddress(address);
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getCountry() {
		return regVO.getCountry();
	}

	public void setCountry(String country) {
		this.regVO.setCountry(country);
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getPincode() {
		return regVO.getPincode();
	}

	public void setPincode(String pincode) {
		this.regVO.setPincode(pincode);
	}

	
}