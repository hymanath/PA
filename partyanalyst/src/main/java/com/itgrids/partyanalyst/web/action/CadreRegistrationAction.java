package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreRegistrationAction  extends ActionSupport implements ServletRequestAware{

	private static final Logger         LOG = Logger.getLogger(CadreRegistrationAction.class);
	private HttpServletRequest          request;
	
	private ICadreRegistrationService   cadreRegistrationService;
	
	private String 						task;
	private JSONObject                  jobj;
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	

	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}


	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}


	public String execute()
	{
		try {
			LOG.info("Entered into execute method in CadreRegistrationAction Action");
		} catch (Exception e) {
			LOG.error("Exception raised in execute method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}

}
