package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.QuickRequestVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.impl.MailService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class PostArticleAction extends ActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Instance Variables

	private HttpServletRequest request;
	private String name;
	private String email;
	private String mobileNO;
	private String comment;
	
	JSONObject jObj;
	private String task = null;
	private QuickRequestVO quickRequestVO = new QuickRequestVO();
	private MailService mailService;
	private ResultStatus result = new ResultStatus();

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}

	public String getName() {
		return quickRequestVO.getUserName();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Name is Mandatory",  shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD,expression = "^[a-zA-Z ]+$", message = "Name should not contain special characters and numbers", shortCircuit = true)
	public void setName(String name) {
		this.quickRequestVO.setUserName(name);
	}

	public String getEmail() {
		return quickRequestVO.getEmailId();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Email is Mandatory")
	@EmailValidator(type = ValidatorType.FIELD , message = "Please Enter a valid Email.")
	public void setEmail(String email) {
		this.quickRequestVO.setEmailId(email);
	}

	public String getMobileNO() {
		return quickRequestVO.getMobileNumber();
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mobile is Mandatory")
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([789]{1})([0123456789]{1})([0-9]{8})$", message = "Invalid Mobile Number", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Invalid Mobile number...", minLength = "10", maxLength = "12")	
	public void setMobileNO(String mobileNO) {
		this.quickRequestVO.setMobileNumber(mobileNO);
	}

	public String getRequirement() {
		return quickRequestVO.getUserRequirement();
	}
	
	
	public void setRequirement(String requirement) {
		this.quickRequestVO.setUserRequirement(requirement);
	}

	public String execute() throws Exception {

		String requestURL = request.getRequestURL().toString();
		String requestFrom = "";
		if (requestURL.contains("www.partyanalyst.com"))
			requestFrom = IConstants.SERVER;
		else
			requestFrom = IConstants.LOCALHOST;
		result = mailService.sendArticleToAdmin(quickRequestVO,requestFrom);
		request.setAttribute("result",result);
		return SUCCESS;
	}

}