package com.itgrids.partyanalyst.web.action;

import java.io.InputStream;

import java.io.StringBufferInputStream;
import java.util.List;


import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IAlertService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CreateAlertAction extends ActionSupport implements ServletRequestAware, ServletContextAware{
	private HttpSession session;
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;	
	private IAlertService alertService;
	
	private InputStream inputStream;
	private String status;
	private AlertVO alertVO;
	
	private List<BasicVO> basicVO;
	private static final Logger LOG = Logger.getLogger(CreateAlertAction.class);
	public List<BasicVO> getBasicVO() {
		return basicVO;
	}

	public void setBasicVO(List<BasicVO> basicVO) {
		this.basicVO = basicVO;
	}
	
	public AlertVO getAlertVO() {
		return alertVO;
	}

	public void setAlertVO(AlertVO alertVO) {
		this.alertVO = alertVO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public IAlertService getAlertService() {
		return alertService;
	}

	public void setAlertService(IAlertService alertService) {
		this.alertService = alertService;
	}

	
	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public String execute()
	{
		return Action.SUCCESS;
	}
	public String getCandidatesByName(){
		try{
			jObj = new JSONObject(getTask());
			basicVO = alertService.getCandidatesByName(jObj.getString("CandidateName"));
			}
		catch(Exception e){
			LOG.error("Exception Raised in getCandidatesByName() -- createAlertAction" + e); 
		}
		return Action.SUCCESS;
		
	}
	
	public String createAlert()
	{
		try{
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		status = alertService.createAlert(alertVO,regVo.getRegistrationID());
		inputStream = new StringBufferInputStream(status);
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in raiseComplaint",e);
		}
		return Action.SUCCESS;	
	}
	
	public String getLocationLevelWiseAlerts()
	{
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			basicVO = alertService.getLocationLevelWiseAlerts(regVo.getRegistrationID());
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in getLocationLevelWiseAlerts",e);
		}
		return Action.SUCCESS;	
	}
	

}
