package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.service.impl.VoiceSmsService;
import com.opensymphony.xwork2.Action;


public class UpdateCustomerContacts implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpSession session;
	private IRegistrationService registrationService;
	private EntitlementVO allRegisteredUsersData;
	private String status;
	private String task;
	JSONObject jObj;
	private IVoiceSmsService voiceSmsService;
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public EntitlementVO getAllRegisteredUsersData() {
		return allRegisteredUsersData;
	}
	public void setAllRegisteredUsersData(EntitlementVO allRegisteredUsersData) {
		this.allRegisteredUsersData = allRegisteredUsersData;
	}
	public IRegistrationService getRegistrationService() {
		return registrationService;
	}
	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public IVoiceSmsService getVoiceSmsService() {
		return voiceSmsService;
	}
	public void setVoiceSmsService(IVoiceSmsService voiceSmsService) {
		this.voiceSmsService = voiceSmsService;
	}
	public String execute(){
		session = request.getSession();
		RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
		if(regvo == null)
			return Action.ERROR;
		else if(regvo.getIsAdmin().equals("true"))
			allRegisteredUsersData = registrationService.getAllRegisterdUsers();
		return Action.SUCCESS;
	}
	
	public String saveCustomerContactUpdations(){
		session = request.getSession();
		RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
		if(regvo == null)
			return Action.ERROR;
		
		try {
			jObj = new JSONObject(getTask());		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("saveMobileNo")){
			Long custmerId = jObj.getLong("candidateId");
			String mobileNo = jObj.getString("mobileNo");
			status = voiceSmsService.saveCustomerContactsUpdations(custmerId,mobileNo);
		}
		return Action.SUCCESS;
	}

}
