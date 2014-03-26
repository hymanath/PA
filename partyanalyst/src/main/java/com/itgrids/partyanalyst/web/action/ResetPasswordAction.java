package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private String randomNumber;
	private IAnanymousUserService ananymousUserService;
	private Long registrationId;
	private String userName;
	private HttpSession session;
	JSONObject jObj = null;
	private String task;
	
	private String status;
	private String newPassword;
	private String isValidated;
	
	
	public String getIsValidated() {
		return isValidated;
	}

	public void setIsValidated(String isValidated) {
		this.isValidated = isValidated;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Long getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(Long registrationId) {
		this.registrationId = registrationId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}

	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(String randomNumber) {
		this.randomNumber = randomNumber;
	}
	
	
	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public String execute(){
		
		RegistrationVO regVO=ananymousUserService.verifyLinkForPassword(randomNumber);
		registrationId=regVO.getRegistrationID();
		userName=regVO.getUserName();
		
		if(regVO.getRegistrationID()!=null){
			session=request.getSession();
			session.setAttribute("USER", regVO);
			isValidated ="success";
			return "success";
		}else{
			
			isValidated ="error";
			return "error";
			
		}
	}
	
	public String confirmPasswordAction(){
		session=request.getSession();
		RegistrationVO rvo=(RegistrationVO) session.getAttribute("USER");
		userName=rvo.getUserName();
		
		rvo.setPassword(newPassword);
		
		status=ananymousUserService.confirmChangePassword(rvo,newPassword);
		
		return status;
	}
	
}
