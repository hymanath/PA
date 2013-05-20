package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IUserCadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private EntitlementsHelper entitlementsHelper;
	private Boolean cadreView = true;
	private Boolean cadreCreate = true;
	private Boolean cadreUpdate = true;
	private Boolean cadreDelete = true;
	private String dateScope;
	private String eventScope;
	private String createDate;
	private String createEvent;
	
	public Boolean getCadreView() {
		return cadreView;
	}

	public void setCadreView(Boolean cadreView) {
		this.cadreView = cadreView;
	}

	public Boolean getCadreCreate() {
		return cadreCreate;
	}

	public void setCadreCreate(Boolean cadreCreate) {
		this.cadreCreate = cadreCreate;
	}

	public Boolean getCadreUpdate() {
		return cadreUpdate;
	}

	public void setCadreUpdate(Boolean cadreUpdate) {
		this.cadreUpdate = cadreUpdate;
	}

	public Boolean getCadreDelete() {
		return cadreDelete;
	}

	public void setCadreDelete(Boolean cadreDelete) {
		this.cadreDelete = cadreDelete;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

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

	public String getDateScope() {
		return dateScope;
	}

	public String getEventScope() {
		return eventScope;
	}

	public void setEventScope(String eventScope) {
		this.eventScope = eventScope;
	}

	public void setDateScope(String dateScope) {
		this.dateScope = dateScope;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateEvent() {
		return createEvent;
	}

	public void setCreateEvent(String createEvent) {
		this.createEvent = createEvent;
	}

	public String execute() throws Exception{
		
		log.debug("In execute of Cadre Management Action ********");
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CADRE_MANAGEMENT_ENTITLEMENT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CADRE_MANAGEMENT_ENTITLEMENT))
			return ERROR;
		
		if(dateScope != null && dateScope.equalsIgnoreCase("createDate"))
			createDate= "true";
		else
			createDate=  "false";
		
		if(eventScope != null && eventScope.equalsIgnoreCase("createEvent"))
			createEvent = "true";
		else
			createEvent = "false";
		
		Long userID = user.getRegistrationID();
		
		remainingSms = smsCountrySmsService.getRemainingSmsLeftForUser(userID);
		
		cadreManagementVO = userCadreManagementService.getUserData(user);
		if(cadreManagementVO!=null && cadreManagementVO.getExceptionEncountered()!=null)
			log.error(cadreManagementVO.getExceptionEncountered().getMessage());
		
		if(user.getParentUserId() != null && ((user != null && !entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.CADRE_VIEW)) ||
				(user == null && !entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.CADRE_VIEW))))
			cadreView = false;			
		
		if(user.getParentUserId() != null && ((user != null && !entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.CADRE_CREATE)) ||
				(user == null && !entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.CADRE_CREATE))))
			cadreCreate = false;			
		
		if(user.getParentUserId() != null && ((user != null && !entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.CADRE_UPDATE)) ||
				(user == null && !entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.CADRE_UPDATE))))
			cadreUpdate =false;
					
		if(user.getParentUserId() != null && ((user != null && !entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.CADRE_DELETE)) ||
				(user == null && !entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.CADRE_DELETE))))
			cadreDelete = false;
		
		return Action.SUCCESS;
	}
}
