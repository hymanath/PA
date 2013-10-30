package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMailsSendingService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class ChangePasswordAction implements ServletRequestAware ,ServletResponseAware{

	private HttpServletRequest request;
	private HttpServletResponse response;
	HttpSession session;
	JSONObject jObj = null;
	private String task;
	private ILoginService loginService;
	private String pwdVal;
	private ResultStatus resuStatus;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	private String invalidPassword;
	private IMailsSendingService mailsSendingService;
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public String getPwdVal() {
		return pwdVal;
	}

	public void setPwdVal(String pwdVal) {
		this.pwdVal = pwdVal;
	}

	
	public ResultStatus getResuStatus() {
		return resuStatus;
	}

	public void setResuStatus(ResultStatus resuStatus) {
		this.resuStatus = resuStatus;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Please enter CurrentPassword ", minLength = "1")	
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Please enter NewPassword ", minLength = "1")	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Please enter ConfirmPassword ", minLength = "1")	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
	
	public String getInvalidPassword() {
		return invalidPassword;
	}

	public void setInvalidPassword(String invalidPassword) {
		this.invalidPassword = invalidPassword;
	}


	public IMailsSendingService getMailsSendingService() {
		return mailsSendingService;
	}

	public void setMailsSendingService(IMailsSendingService mailsSendingService) {
		this.mailsSendingService = mailsSendingService;
	}

	public String execute()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute(IConstants.USER);
		
		return "SUCCESS";
	}
	
	public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("changePassword")){
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute(IConstants.USER);
			String userName = (String) session.getAttribute("userName");
			//pwdVal = loginService.changePasswordOfANewUser(jObj.getString("crntPassword"),jObj.getString("newPassword"),email);
			resuStatus = loginService.changePasswordOfANewUser(jObj.getString("crntPassword"),jObj.getString("newPassword"),userName);
			
		}
		else if(jObj.getString("task").equalsIgnoreCase("checkCurrentPassword"))
		{
			session = request.getSession();
			String userName = (String) session.getAttribute("userName");
			pwdVal=loginService.checkUserCurrentPassword(jObj.getString("crntPassword"),userName);
		}
		return Action.SUCCESS;
	}
	
	public String newUserChangePassword()
	{
		try{
			session = request.getSession();
			String userName = (String) session.getAttribute("userName");
			pwdVal=loginService.checkUserCurrentPassword(currentPassword,userName);
			if(pwdVal.equals(IConstants.YesPassword))
			resuStatus = loginService.changePasswordOfANewUser(currentPassword,newPassword,userName);
			else
			{
				invalidPassword="Invalid Password.";
				pwdVal = "Invalid";
				return "changePassword";
			}
			if(resuStatus.getExceptionEncountered() == null && resuStatus.getResultCode() == 0)
			{
				
				
				RegistrationVO regVO = loginService.checkForValidUser(userName,newPassword);
				//session.setAttribute("userName",regVO.getEmail());
				//session.setAttribute("userFullName",regVO.getFirstName() + " " + regVO.getLastName());
				session.setAttribute(IWebConstants.FREE_USER_ROLE, true);
				session.setAttribute("UserType", "FreeUser");
				session.setAttribute("loginStatus", "out");
				session.setAttribute("HiddenCount", 0);
				session.setAttribute("UserName", regVO.getFirstName() + " " + regVO.getLastName());
				//session.setAttribute("userName",regVO.getEmail());
				session.setAttribute(IConstants.USER,regVO);
				return "userProfile";
		  }
        	
			
			else
			{
				pwdVal = "Invalid";
				invalidPassword="Invalid Password.";
				return "changePassword";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
}
