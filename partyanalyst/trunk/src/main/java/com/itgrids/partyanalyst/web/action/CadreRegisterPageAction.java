package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.service.CadreManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreRegisterPageAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CadreManagementService cadreManagementService;
	private UserCadresInfoVO userCadresInfoVO = new UserCadresInfoVO();
	private HttpServletRequest request;
	private HttpSession session;
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String execute(){
		
		return Action.SUCCESS;
	}
	

}
