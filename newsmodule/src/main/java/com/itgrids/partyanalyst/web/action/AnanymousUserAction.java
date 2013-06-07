package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AnanymousUserAction extends ActionSupport implements
ServletRequestAware, ModelDriven<RegistrationVO>, Preparable  {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(AnanymousUserAction.class);
	
	private HttpServletRequest request;
	private String task;
	private JSONObject jObj;
	private HttpSession session;
	private Long registrationId;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private RegistrationVO regVO = new RegistrationVO();
	private IRegistrationService registrationService;
	private Long result;
	private Boolean savedSuccessfully;
	
	public Boolean getSavedSuccessfully() {
		return savedSuccessfully;
	}


	public void setSavedSuccessfully(Boolean savedSuccessfully) {
		this.savedSuccessfully = savedSuccessfully;
	}


	public Long getResult() {
		return result;
	}


	public void setResult(Long result) {
		this.result = result;
	}


	public RegistrationVO getRegVO() {
		return regVO;
	}


	public void setRegVO(RegistrationVO regVO) {
		this.regVO = regVO;
	}


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


	public IRegistrationService getRegistrationService() {
		return registrationService;
	}


	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getEmail() {
		return regVO.getEmail();
	}


	public void setEmail(String email) {
		this.regVO.setEmail(email);
	}


	public Long getRegistrationId() {
		return regVO.getRegistrationID();
	}


	public void setRegistrationId(Long registrationId) {
		this.regVO.setRegistrationID(registrationId);
	}


	public String getUserName() {
		return regVO.getUserName();
	}


	public void setUserName(String userName) {
		this.regVO.setUserName(userName);
	}


	public String getFirstName() {
		return this.regVO.getFirstName();
	}


	public void setFirstName(String firstName) {
		 this.regVO.setFirstName(firstName);
	}


	public String getLastName() {
		return regVO.getLastName();
	}


	public void setLastName(String lastName) {
		this.regVO.setLastName(lastName);
	}


	@Override
	public void prepare() throws Exception {
		
	}


	@Override
	public RegistrationVO getModel() {
	
		return null;
	}


	@Override
	public void setServletRequest(HttpServletRequest arg) {
	this.request = arg;
	}
   

	public String execute()
	{
		
		return Action.SUCCESS;
		
	}
	
	public String saveUserData()
	{
		String userType = "";
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user != null)
		userType = user.getUserType();
		if(regVO.getEmail() != null)
        savedSuccessfully = registrationService.saveDataIntoUser(regVO,userType);
		else
		savedSuccessfully = false;	
        return Action.SUCCESS;
			
	}
	
	public String checkForUserNameAvailabilityForFreashUser(){

		try {
			jObj = new JSONObject(getTask());
			System.out.println(jObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result =  new Long(registrationService.checkForUserNameAvailabilityForFreashUser(jObj.getString("userName")).getResultCode());
		 
		return SUCCESS;
	}


}
