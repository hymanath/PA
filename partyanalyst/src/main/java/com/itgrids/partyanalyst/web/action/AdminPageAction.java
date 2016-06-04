package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.service.IThumbnailService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class AdminPageAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9066234868654018283L;
	transient private HttpServletRequest request;
	private EntitlementsHelper entitlementsHelper;
	private ProblemBeanVO problemBeanVO = new ProblemBeanVO();
	private IProblemManagementReportService problemManagementReportService;
	private ISpecialPageService specialPageService;



	
	
	 public ISpecialPageService getSpecialPageService() {
		return specialPageService;
	}

	public void setSpecialPageService(final ISpecialPageService specialPageService) {
		this.specialPageService = specialPageService;
	}


	private  IThumbnailService thumbnailService;
		
		public IThumbnailService getThumbnailService() {
			return thumbnailService;
		}

		public void setThumbnailService(final IThumbnailService thumbnailService) {
			this.thumbnailService = thumbnailService;
		}
		
	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(final 
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}

	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}

	public void setProblemBeanVO(final ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(final EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}

	public String execute(){
		final HttpSession session = request.getSession();
		final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		List<String> entitlements = null;
		if(registrationVO.getEntitlements() != null && registrationVO.getEntitlements().size()>0){
			entitlements = registrationVO.getEntitlements();
			if(registrationVO == null && !entitlements.contains(IConstants.ADMIN_PAGE.trim())){
				return INPUT;
			}
			if(!entitlements.contains(IConstants.ADMIN_PAGE.trim())){
				return ERROR;
			}
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE)){
			return INPUT;
		}
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE)){
			return ERROR;
		}*/
		problemBeanVO = problemManagementReportService.getCountOfNewlyPostedProblemsByFreeUser();
		}
		return SUCCESS;
	}
	public String checkAdmin(){
		final HttpSession session = request.getSession();
		final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		List<String> entitlements = null;
		if(registrationVO.getEntitlements() != null && registrationVO.getEntitlements().size()>0){
			entitlements = registrationVO.getEntitlements();
			if(registrationVO == null && !entitlements.contains(IConstants.ADMIN_PAGE.trim())){
				return INPUT;
			}
			if (!entitlements.contains(IConstants.ADMIN_PAGE.trim())){
				return ERROR;
			}
		
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE)){
			return INPUT;
		}
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE)){
			return ERROR;
		}*/
		}
		return SUCCESS;
	}
	
}
