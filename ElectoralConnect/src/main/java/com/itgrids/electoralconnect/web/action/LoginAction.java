package com.itgrids.electoralconnect.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.service.IUserService;
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
	private String userName,password;
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


	public String execute()
	{
		return Action.SUCCESS;
	}
	
	public String userLoginCheck()
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
	}
}
