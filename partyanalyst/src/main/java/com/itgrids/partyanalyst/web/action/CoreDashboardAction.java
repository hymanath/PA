package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICoreDashboardService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CoreDashboardAction extends ActionSupport implements ServletRequestAware{
    
	private final static Logger LOG = Logger.getLogger(CoreDashboardAction.class);
	
	//Instance variables
	private HttpServletRequest request;
	
	//Attributes
	private ICoreDashboardService coreDashboardService;
	
	//setters
	public void setCoreDashboardService(ICoreDashboardService coreDashboardService) {
		this.coreDashboardService = coreDashboardService;
	}
	
	
	//Implementation method
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
    
	//business methods
	public String execute(){
		try {
			
			final HttpSession session = request.getSession();
			
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
		}catch(Exception e) {
			LOG.error("Exception raised at execute() in CoreDashBoard Action class", e);
		}
		return Action.SUCCESS;
	}
}
