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
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class ChangePasswordAction implements ServletRequestAware ,ServletResponseAware{

	private HttpServletRequest request;
	private HttpServletResponse response;
	HttpSession session;
	JSONObject jObj = null;
	private String task;
	private ILoginService loginService;
	private String pwdVal;
	private ResultStatus resuStatus;
	
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
		return Action.SUCCESS;
	}
}
