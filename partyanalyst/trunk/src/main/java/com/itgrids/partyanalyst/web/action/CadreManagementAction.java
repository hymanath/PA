package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IUserCadreManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreManagementAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserCadreManagementService userCadreManagementService;
	private HttpServletRequest request;
	private CadreManagementVO cadreManagementVO = null;
	private final static Logger log = Logger.getLogger(CadreManagementAction.class);
	private ISmsService smsCountrySmsService;
	private Long remainingSms;
	
	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	public Long getRemainingSms() {
		return remainingSms;
	}

	public void setRemainingSms(Long remainingSms) {
		this.remainingSms = remainingSms;
	}
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
	
		Long userID = user.getRegistrationID();
		
		remainingSms = smsCountrySmsService.getRemainingSmsLeftForUser(userID);
		
		cadreManagementVO = userCadreManagementService.getUserData(user);
		if(cadreManagementVO!=null && cadreManagementVO.getExceptionEncountered()!=null)
			log.error(cadreManagementVO.getExceptionEncountered().getMessage());
		return Action.SUCCESS;
	}
}
