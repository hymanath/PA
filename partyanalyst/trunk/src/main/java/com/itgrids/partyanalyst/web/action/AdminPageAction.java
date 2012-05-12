package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class AdminPageAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private EntitlementsHelper entitlementsHelper;
	ProblemBeanVO problemBeanVO = new ProblemBeanVO();
	private IProblemManagementReportService problemManagementReportService;
	
	
	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}

	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}

	public void setProblemBeanVO(ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute(){
		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE))
			return ERROR;
		problemBeanVO = problemManagementReportService.getCountOfNewlyPostedProblemsByFreeUser();
		
		
		
		return SUCCESS;
	}
}
