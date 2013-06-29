package com.itgrids.electoralconnect.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.google.gdata.util.parser.Action;
import com.itgrids.electoralconnect.dto.ResultStatus;
import com.itgrids.electoralconnect.dto.UserProfileVO;
import com.itgrids.electoralconnect.dto.UserVO;
import com.itgrids.electoralconnect.model.User;
import com.itgrids.electoralconnect.service.IMailService;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterUserAction extends ActionSupport implements ServletRequestAware{

	private static final Logger log=Logger.getLogger(RegisterUserAction.class);
	private String task;
	private JSONObject jobj;
	private String firstName;
	private String lastName;
	private String emailId;
	private String epicId;
	private String mobileNo;
	private String resultStr;
	
	private IUserService userService;
	private IMailService mailService;
	
	private HttpServletRequest request;
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getJobj() {
		return jobj;
	}

	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEpicId() {
		return epicId;
	}

	public void setEpicId(String epicId) {
		this.epicId = epicId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
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

	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute(){
		log.debug("In RegisterUserActions execute()");
		System.out.println(firstName+" "+lastName);
		UserProfileVO userProfileVO=new UserProfileVO();
		userProfileVO.setFirstName(firstName);
		userProfileVO.setLastName(lastName);
		userProfileVO.setEmailId(emailId);
		userProfileVO.setMobileNo(mobileNo);
		userProfileVO.setEpicId(epicId);
		
		UserVO uservo=userService.registerUser(userProfileVO);
		String requestURL= request.getRequestURL().toString();
		System.out.println(requestURL);
		
		String requestFrom = "";
		if(requestURL.contains("www.partyanalyst.com"))
			requestFrom = IConstants.SERVER;
		else
			requestFrom = IConstants.LOCALHOST;
		
		ResultStatus rs = mailService.sendRegistrationNotification(uservo,requestFrom);
		
		return SUCCESS;
	}
	public String validateEmail(){
		try{
			jobj=new JSONObject(getTask());
		}
		catch(Exception e){
			log.debug("Exception Raised in RegisterUserActions Validate Email Method ", e);
		}
		String emailId=jobj.getString("emailId");
		resultStr=userService.validateEmail(emailId);
		
		return SUCCESS;
	}
	public String registerUser(){
		return SUCCESS;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
}
