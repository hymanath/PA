package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class ProfileManagePageAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 2619726916593528832L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Long candidateld;
	private EntitlementsHelper entitlementsHelper;
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public Long getCandidateld() {
		return candidateld;
	}

	public void setCandidateld(Long candidateld) {
		this.candidateld = candidateld;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute()  {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PROFILE_MANAGEMENT_ENTITLEMENT))
			return IConstants.NOT_LOGGED_IN;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PROFILE_MANAGEMENT_ENTITLEMENT))
			return ERROR;
		
		return SUCCESS;
	}

}
