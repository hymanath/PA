package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IUserCadreManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class CadreManagementAction extends ActionSupport implements ServletRequestAware{
	
	private IUserCadreManagementService userCadreManagementService;
	private HttpServletRequest request;
	private CadreManagementVO cadreManagementVO = null;

	public IUserCadreManagementService getUserCadreManagementService() {
		return userCadreManagementService;
	}

	public void setUserCadreManagementService(
			IUserCadreManagementService userCadreManagementService) {
		this.userCadreManagementService = userCadreManagementService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public CadreManagementVO getCadreManagementVO() {
		return cadreManagementVO;
	}

	public void setCadreManagementVO(CadreManagementVO cadreManagementVO) {
		this.cadreManagementVO = cadreManagementVO;
	}

	public String execute() throws Exception{
		
		System.out.println("In execute of Cadre Management Action ********");
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		Long userID = user.getRegistrationID();
		Long partyID = user.getParty();
		
		cadreManagementVO = userCadreManagementService.getUserData(userID,partyID);
		
		
		
		return Action.SUCCESS;
	}
}
