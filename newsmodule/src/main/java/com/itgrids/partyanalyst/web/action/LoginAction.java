package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ILoginService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements  ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpSession session;

	private String userName = null;
	private String password = null;
	private ILoginService loginService;
	 private String task = null;
	 private ResultStatus resultStatus;
	 public static Logger LOG = Logger.getLogger(LoginAction.class);

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String value) {
		userName = value;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String value) {
		password = value;
	}
	
	

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	
	public String execute(){
		return Action.SUCCESS;
		}

public String ajaxCallForLoginPopup(){
	
	
	try {
	
		if(getTask().equalsIgnoreCase("validateUserForLogin"))
		{
			userName = getUserName();
			password=getPassword();
			 
			session = request.getSession();
			
			//RegistrationVO regVO = loginService.checkForValidUser(userName, password);
			resultStatus = loginService.checkForUserNameAndPassword(userName, password);
			
			if(resultStatus.getResultCode() == 0)
			{
				
				RegistrationVO regVO = loginService.getUserByUserNameAndPassword(userName, password);
				session.setAttribute("USER", regVO);
				
				if(regVO.getUserAccessType().equalsIgnoreCase("subuser"))
					resultStatus.setResultCode(2);
				if(regVO.getUserAccessType().equalsIgnoreCase("debate"))
					resultStatus.setResultCode(3);
				if(regVO.getUserAccessType().equalsIgnoreCase("pfb"))
					resultStatus.setResultCode(4);
				
				resultStatus.setMessage(regVO.getHomeUrl());
			} 
			
			
		}
		
		
	} catch (Exception e) {
		LOG.error(e);
	}
	
	return Action.SUCCESS;
}
	



}