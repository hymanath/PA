package com.itgrids.electoralconnect.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.ResultStatus;
import com.itgrids.electoralconnect.dto.UserProfileVO;
import com.itgrids.electoralconnect.service.IMailService;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.electoralconnect.util.IConstants;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RegisterUserAction extends ActionSupport implements ServletRequestAware{

	private static final Logger LOG = Logger.getLogger(RegisterUserAction.class);
	private String task;
	private JSONObject jobj;
	private String firstName;
	private String lastName;
	private String emailId;
	private String epicId;
	private String mobileNo;
	private String resultStr;
	private String userName;
	private String password;
	private IUserService userService;
	private IMailService mailService;
	private String userType;
	private String passwordStatus = "";
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
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	public String getPasswordStatus() {
		return passwordStatus;
	}

	public void setPasswordStatus(String passwordStatus) {
		this.passwordStatus = passwordStatus;
	}

	public String execute(){
		LOG.debug("In RegisterUserActions execute()");
		System.out.println(firstName+" "+lastName);
		UserProfileVO userProfileVO=new UserProfileVO();
		userProfileVO.setFirstName(firstName);
		userProfileVO.setLastName(lastName);
		userProfileVO.setEmailId(emailId);
		userProfileVO.setMobileNo(mobileNo);
		userProfileVO.setEpicId(epicId);
		userProfileVO.setUserType(userType);
		RegistrationVO  regVO = userService.registerUser(userProfileVO);
		String requestURL= request.getRequestURL().toString();
		System.out.println(requestURL);
		
		String requestFrom = "";
		if(requestURL.contains("www.partyanalyst.com"))
			requestFrom = IConstants.SERVER;
		else
			requestFrom = IConstants.LOCALHOST;
		
		ResultStatus rs = mailService.sendRegistrationNotification(regVO,requestFrom);
		
		return SUCCESS;
	}
	public String validateEmail(){
		try{
			jobj=new JSONObject(getTask());
		}
		catch(Exception e){
			LOG.debug("Exception Raised in RegisterUserActions Validate Email Method ", e);
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
	
	/*public String userLoginCheck()
	{
		
		HttpSession session = request.getSession();
		RegistrationVO user = userService.checkForValidUser(userName,password);
		session.setAttribute("USER", user);
		if(user.getRegistrationID() != null && user.getRegistrationID() > 0)
		{
			resultStr = "success";
		}
		else
		{
			resultStr = "failure";
		}
		return SUCCESS;
	}*/
	
	/*public String verfyingPassword()
	{
		try {
			LOG.debug("Entered into verfyingPassword() method in RegisterUserAction Action ");
			jobj=new JSONObject(getTask());
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			String password = jobj.getString("password");
			if(regVO.getPassword().equals(password))
			{
				passwordStatus = "success";
			}
			else
			{
				passwordStatus = "failure";
			}
		} catch (Exception e) {
			LOG.error("Exception Raised in verfyingPassword Validate Email Method ", e);
		}
		return SUCCESS;
	}*/
}
