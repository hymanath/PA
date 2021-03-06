package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DistrictWisePartyPerformanceAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1312001188656558078L;
	private HttpServletRequest request;
	private EntitlementsHelper entitlementsHelper;
	public String execute()
	{
        HttpSession session = request.getSession();		
		if(session.getAttribute(IConstants.USER) == null || ((RegistrationVO)session.getAttribute(IConstants.USER)).getRegistrationID() == null)
			return IConstants.NOT_LOGGED_IN;
		return Action.SUCCESS;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	
}
