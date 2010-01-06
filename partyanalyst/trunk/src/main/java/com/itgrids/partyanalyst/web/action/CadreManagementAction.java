package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
	private final static Logger log = Logger.getLogger(CadreManagementAction.class);

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
		
		log.debug("In execute of Cadre Management Action ********");
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return ERROR;
	
		cadreManagementVO = userCadreManagementService.getUserData(user);
		//log.debug("cadreManagementVO.getUserImpDates().size():"+cadreManagementVO.getUserImpDates().size());
		return Action.SUCCESS;
	}
}
