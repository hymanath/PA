package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.service.IFieldMonitoringService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class FieldMonitoringAction extends ActionSupport implements ServletRequestAware{
	
	private final static Logger LOG = Logger.getLogger(FieldMonitoringAction.class);
	
	//instance variables
		private HttpServletRequest request;
		
		
	//Attributes
	   private IFieldMonitoringService fieldMonitoringService;
	
	
	
	//setters and getters.
	   public void setFieldMonitoringService(IFieldMonitoringService fieldMonitoringService) {
			this.fieldMonitoringService = fieldMonitoringService;
	   }

	//implementation methods
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}


	public String execute(){
		return Action.SUCCESS;
	}
	
}
