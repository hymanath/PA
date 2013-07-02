package com.itgrids.electoralconnect.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.electoralconnect.dao.hibernate.UserDAO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements ServletRequestAware{

	private static final Logger LOG = Logger.getLogger(LoginAction.class);
	private String task;
	private JSONObject jobj;
	private HttpServletRequest request;
	private String resultStr = "";
	private IUserService userService;
	private String userName,password,newPassword;
	private String status = "";
	private String passwordChaged;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
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

	
	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	
	public String getPasswordChaged() {
		return passwordChaged;
	}


	public void setPasswordChaged(String passwordChaged) {
		this.passwordChaged = passwordChaged;
	}


	public String execute()
	{
		return Action.SUCCESS;
	}
	
	public String userLoginCheck()
	{
		
		HttpSession session = request.getSession();
		RegistrationVO user = userService.checkForValidUser(userName,password);
		session.setAttribute("USER", user);
		passwordChaged = user.getIsPasswordChanged();
		if(user.getRegistrationID() != null && user.getRegistrationID() > 0)
		{
			resultStr = "success";
			session.setAttribute("loginStatus", "in");
		}
		else
		{
			resultStr = "failure";
			session.setAttribute("loginStatus", "out");
		}
		return SUCCESS;
	}
	
	/*public String changePassword()
	{
		HttpSession session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		ResultStatus resultStatus = userService.updateUserPassword(newPassword, userId);
		if(resultStatus.getResultCode() == 0)
		{
			status = "success";
		}
		else
		{
			status = "failure";
		}
		return SUCCESS;
	}*/
}
