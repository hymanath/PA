package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class AdminPageAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute(){
		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null)
			return INPUT;
		if(!EntitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CROSS_VOTING_REPORT))
			return ERROR;
		return SUCCESS;
	}
}
