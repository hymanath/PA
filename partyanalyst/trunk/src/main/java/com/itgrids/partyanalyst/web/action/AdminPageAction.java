package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.service.IThumbnailService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AdminPageAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private EntitlementsHelper entitlementsHelper;
	ProblemBeanVO problemBeanVO = new ProblemBeanVO();
	private IProblemManagementReportService problemManagementReportService;
	private ISpecialPageService specialPageService;



	
	
	 public ISpecialPageService getSpecialPageService() {
		return specialPageService;
	}

	public void setSpecialPageService(ISpecialPageService specialPageService) {
		this.specialPageService = specialPageService;
	}


	private  IThumbnailService thumbnailService;
		
		public IThumbnailService getThumbnailService() {
			return thumbnailService;
		}

		public void setThumbnailService(IThumbnailService thumbnailService) {
			this.thumbnailService = thumbnailService;
		}
		
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
	public String checkAdmin(){
		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE))
			return ERROR;
		
		
		return SUCCESS;
	}
	
	public String  crateThumnailForAdmin()
	{
		int[] ids= new int[]{1,2,3,10,11,24,25,35};
		//  thumbnailService.crateThumnailForAdmin(ids,IWebConstants.STATIC_CONTENT_FOLDER_URL);
		
		
	
		return null;
	}
	
	
	
	
}
