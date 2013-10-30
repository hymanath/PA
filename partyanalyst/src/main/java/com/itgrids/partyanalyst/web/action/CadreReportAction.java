package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.ImportantDatesVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IUserCadreManagementService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreReportAction extends ActionSupport implements ServletContextAware,ServletRequestAware{


	private CadreManagementService cadreManagementService;
	private IUserCadreManagementService userCadreManagementService; 
	private UserCadresInfoVO userCadresInfoVO = new UserCadresInfoVO();
	private HttpServletRequest request;
	private HttpSession session;
	
	private List<UserEventVO> userEventList = new ArrayList<UserEventVO>();
	private List<ImportantDatesVO> ImpDatesList = new ArrayList<ImportantDatesVO>();
	private CadreManagementVO cadreManagementVO = new CadreManagementVO();
	private EntitlementsHelper entitlementsHelper;
	
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public UserCadresInfoVO getUserCadresInfoVO() {
		return userCadresInfoVO;
	}


	public void setUserCadresInfoVO(UserCadresInfoVO userCadresInfoVO) {
		this.userCadresInfoVO = userCadresInfoVO;
	}

	public CadreManagementVO getCadreManagementVO() {
		return cadreManagementVO;
	}

	public void setCadreManagementVO(CadreManagementVO cadreManagementVO) {
		this.cadreManagementVO = cadreManagementVO;
	}

	
	public String execute() throws Exception{
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null)
			return ERROR;
		if(user==null)
		{
			userCadresInfoVO = new UserCadresInfoVO();
		}
		else
		{			
			userCadresInfoVO.setUserID(user.getParentUserId() == null ? user.getRegistrationID() : user.getParentUserId()); 
			
				List<Long> userIDs = new ArrayList<Long>();
				if(user.getParentUserId() != null){
				  userIDs.add(user.getParentUserId());
				}
				userIDs.add(user.getRegistrationID());
				userCadresInfoVO.setUserIds(userIDs);
			userCadresInfoVO.setUserAccessType(user.getAccessType());
			userCadresInfoVO.setUserAccessValue(user.getAccessValue());
			userCadresInfoVO.setIsParent(user.getParentUserId() == null ? true : false);
			if("MLA".equalsIgnoreCase(user.getAccessType())
					|| "MP".equalsIgnoreCase(user.getAccessType())){
				String constituencyName = cadreManagementService.getConstituencyName(new Long(user.getAccessValue()));
				userCadresInfoVO.setUserAccessDisplayValue(constituencyName);
			}else if("COUNTRY".equalsIgnoreCase(user.getAccessType())){
				String countryName = cadreManagementService.getCountryName(new Long(user.getAccessValue()));
				userCadresInfoVO.setUserAccessDisplayValue(countryName);
			}else if("STATE".equalsIgnoreCase(user.getAccessType())){
				String stateName = cadreManagementService.getStateName(new Long(user.getAccessValue()));
				userCadresInfoVO.setUserAccessDisplayValue(stateName);
			}else if("DISTRICT".equalsIgnoreCase(user.getAccessType())){
				String districtName = cadreManagementService.getDistrictName(new Long(user.getAccessValue()));
				userCadresInfoVO.setUserAccessDisplayValue(districtName);
			}else if("MANDAL".equalsIgnoreCase(user.getAccessType())){
				String mandalName = cadreManagementService.getMandalName(new Long(user.getAccessValue()));
				userCadresInfoVO.setUserAccessDisplayValue(mandalName);
			}
			userCadresInfoVO = cadreManagementService.getUserCadresInfo(userCadresInfoVO);
			
			if(user.getParentUserId() != null && ((user != null && !entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.CADRE_VIEW)) ||
					(user == null && !entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.CADRE_VIEW))))
				userCadresInfoVO.setCadreView(false);			
			
			if(user.getParentUserId() != null && ((user != null && !entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.CADRE_CREATE)) ||
					(user == null && !entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.CADRE_CREATE))))
				userCadresInfoVO.setCadreCreate(false);			
			
			if(user.getParentUserId() != null && ((user != null && !entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.CADRE_UPDATE)) ||
					(user == null && !entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.CADRE_UPDATE))))
				userCadresInfoVO.setCadreUpdate(false);
						
			if(user.getParentUserId() != null && ((user != null && !entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.CADRE_DELETE)) ||
					(user == null && !entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.CADRE_DELETE))))
				userCadresInfoVO.setCadreDelete(false);
			
			/*
			 * User Event VO Mock Data
			 */
			/*UserEventVO userEventVO = new UserEventVO();			
			userEventVO.setTitle("Party Meeting");
			userEventVO.setStartDate("20/12/2009 10:15:00");
			userEventVO.setEndDate("22/12/2009 04:15:00");
			userEventVO.setDescription("Party Meeting on 20-Dec");
			
			UserEventVO userEventVO1 = new UserEventVO();
			userEventVO1.setTitle("New Event");
			userEventVO1.setStartDate("24/12/2009 10:15:00");
			userEventVO1.setEndDate("25/12/2009 04:15:00");
			userEventVO1.setDescription("New Event on 24-Dec");
			
			userEventList.add(userEventVO);
			userEventList.add(userEventVO1);
			
			ImportantDatesVO importantDatesVO = new ImportantDatesVO();
			importantDatesVO.setTitle("Party Formation Day");
			importantDatesVO.setStartDate("28/12/2009");
			importantDatesVO.setImportance("Party formed");
			importantDatesVO.setEventType("Yearly");
			
			ImportantDatesVO importantDatesVO1 = new ImportantDatesVO();
			importantDatesVO1.setTitle("Founder Birthday");
			importantDatesVO1.setStartDate("12/01/2010");
			importantDatesVO1.setImportance("Birthday ");
			importantDatesVO1.setEventType("Yearly");
			
			ImpDatesList.add(importantDatesVO);
			ImpDatesList.add(importantDatesVO1);		
						
			cadreManagementVO.setUserImpDates(ImpDatesList);
			cadreManagementVO.setUserEvents(userEventList);	*/
			
			this.setCadreManagementVO(cadreManagementVO);
		}
		
		session.setAttribute("USERCADRESINFOVO", userCadresInfoVO);
		return Action.SUCCESS;
	}
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
}
