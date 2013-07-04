package com.itgrids.electoralconnect.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.ResultStatus;
import com.itgrids.electoralconnect.dto.UserProfileVO;
import com.itgrids.electoralconnect.service.IMailService;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@SuppressWarnings("serial")
public class AdminRegisterSaveAction extends ActionSupport implements ServletRequestAware{

	private static final Logger LOG = Logger.getLogger(AdminRegisterSaveAction.class);
	private String firstName;
	private String lastName;
	private String emailId;
	private String mobileNo;
	private String epicId;
	private IUserService userService;
	private HttpServletRequest request;
	private IMailService mailService;
	private String userType;
	private Long type;
	public String getFirstName() {
		return firstName;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "First Name is Mandatory",  shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD,expression = "^[a-zA-Z ]+$", message = "First Name should not contain special characters and numbers", shortCircuit = true)
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Last Name is Mandatory")
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Last Name should not contain special characters and numbers", shortCircuit = true)
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Email is Mandatory")
	@EmailValidator(type = ValidatorType.FIELD, message = " enter a valid for email.")
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEpicId() {
		return epicId;
	}
	public void setEpicId(String epicId) {
		this.epicId = epicId;
	}
	
	
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IMailService getMailService() {
		return mailService;
	}
	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String execute()
	{
		try {
			LOG.debug("In AdminRegisterSaveAction execute()");
			UserProfileVO userProfileVO=new UserProfileVO();
			userProfileVO.setFirstName(firstName);
			userProfileVO.setLastName(lastName);
			userProfileVO.setEmailId(emailId);
			userProfileVO.setMobileNo(mobileNo);
			userProfileVO.setEpicId(epicId);
			userProfileVO.setUserType(userType);
			RegistrationVO  regVO = userService.registerUser(userProfileVO);
			String requestURL= request.getRequestURL().toString();
			String requestFrom = "";
			if(requestURL.contains("www.partyanalyst.com"))
				requestFrom = IConstants.SERVER;
			else
				requestFrom = IConstants.LOCALHOST;
			
			ResultStatus rs = mailService.sendRegistrationNotification(regVO,requestFrom);
		} catch (Exception e) {
			LOG.error("Exception raised in execute() method in AdminRegisterSaveAction Action", e);
		}
		return SUCCESS;
	
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
