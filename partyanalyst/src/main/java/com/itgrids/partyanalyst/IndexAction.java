/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.itgrids.partyanalyst;

import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GroupsDetailsForUserVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IUserCadreManagementService;
import com.itgrids.partyanalyst.service.IUserGroupService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.web.action.CadreManagementAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

/**
 * 
 */
@Conversion()
public class IndexAction extends ActionSupport implements ServletRequestAware {
    
    private Date now = new Date(System.currentTimeMillis());
    private List<SelectOptionVO> mlaConstituenciesList;
    private List<SelectOptionVO> mpConstituenciesList;
    private IStaticDataService staticDataService;
    private IUserGroupService userGroupService;
    private IAnanymousUserService ananymousUserService;
    private ICandidateDetailsService candidateDetailsService;
    private List<FileVO> fileVOList;
    private int systemGroups;
    private int userGroups;
	private HttpSession session;
	private HttpServletRequest request;
	private int eventCount;
	private int impDateCount;
	private EntitlementsHelper entitlementsHelper;
	private boolean hasNewsMonitoring = false;;
	private boolean hasSubUserEntitlement = false;
	private boolean hasCallCenterEntitlment = false;
	private static final long serialVersionUID = 1L;
	private JSONObject jObj;
	private String task;
	
	private IUserCadreManagementService userCadreManagementService;
	private CadreManagementVO cadreManagementVO = null;
	private RegistrationVO user = null;
	private String changedUserName = "false";
	private String loginUserProfilePic;
	private boolean hasProfileManagement = false;	
	private final static Logger LOG = Logger.getLogger(CadreManagementAction.class);

	public void setJObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTask() {
		return task;
	}

	public void setHasSubUserEntitlement(boolean hasSubUserEntitlement) {
		this.hasSubUserEntitlement = hasSubUserEntitlement;
	}

	public boolean isHasSubUserEntitlement() {
		return hasSubUserEntitlement;
	}

	public void setHasCallCenterEntitlment(boolean hasCallCenterEntitlment) {
		this.hasCallCenterEntitlment = hasCallCenterEntitlment;
	}

	public boolean isHasCallCenterEntitlment() {
		return hasCallCenterEntitlment;
	}

	public void setHasProfileManagement(boolean hasProfileManagement) {
		this.hasProfileManagement = hasProfileManagement;
	}

	public boolean isHasProfileManagement() {
		return hasProfileManagement;
	}

	public void setFileVOList(List<FileVO> fileVOList) {
		this.fileVOList = fileVOList;
	}

	public List<FileVO> getFileVOList() {
		return fileVOList;
	}

	public void setCandidateDetailsService(ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public String getChangedUserName() {
		return changedUserName;
	}

	public void setChangedUserName(String changedUserName) {
		this.changedUserName = changedUserName;
	}

	public int getImpDateCount() {
		return impDateCount;
	}

	public void setImpDateCount(int impDateCount) {
		this.impDateCount = impDateCount;
	}

	public int getSystemGroups() {
		return systemGroups;
	}

	public int getUserGroups() {
		return userGroups;
	}

	public int getEventCount() {
		return eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}

	public void setSystemGroups(int systemGroups) {
		this.systemGroups = systemGroups;
	}

	public void setUserGroups(int userGroups) {
		this.userGroups = userGroups;
	}

	public void setUserGroupService(IUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	public RegistrationVO getUser() {
		return user;
	}

	public void setUser(RegistrationVO user) {
		this.user = user;
	}

	public IUserCadreManagementService getUserCadreManagementService() {
		return userCadreManagementService;
	}

	public void setUserCadreManagementService(
			IUserCadreManagementService userCadreManagementService) {
		this.userCadreManagementService = userCadreManagementService;
	}

	public CadreManagementVO getCadreManagementVO() {
		return cadreManagementVO;
	}

	public void setCadreManagementVO(CadreManagementVO cadreManagementVO) {
		this.cadreManagementVO = cadreManagementVO;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
    
    
    public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getMlaConstituenciesList() {
		return mlaConstituenciesList;
	}

	public void setMlaConstituenciesList(List<SelectOptionVO> mlaConstituenciesList) {
		this.mlaConstituenciesList = mlaConstituenciesList;
	}

	public List<SelectOptionVO> getMpConstituenciesList() {
		return mpConstituenciesList;
	}

	public void setMpConstituenciesList(List<SelectOptionVO> mpConstituenciesList) {
		this.mpConstituenciesList = mpConstituenciesList;
	}

	public void setLoginUserProfilePic(String loginUserProfilePic) {
		this.loginUserProfilePic = loginUserProfilePic;
	}

	public String getLoginUserProfilePic() {
		return loginUserProfilePic;
	}
	
	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}

	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}

	@TypeConversion(converter = "com.itgrids.partyanalyst.DateConverter")
    public Date getDateNow() { return now; }
    
    public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setHasNewsMonitoring(boolean hasNewsMonitoring) {
		this.hasNewsMonitoring = hasNewsMonitoring;
	}

	public boolean isHasNewsMonitoring() {
		return hasNewsMonitoring;
	}

	public String execute() throws Exception {
    	session = request.getSession();
		user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null){
			return ERROR;
		}else{ 
			LOG.info(user.getUserName());
		}
        now = new Date(System.currentTimeMillis());
        
        /*mlaConstituenciesList = staticDataService.getConstituenciesByElectionTypeAndStateId(Long.valueOf(2), Long.valueOf(1)).getConstituencies();
        mpConstituenciesList = staticDataService.getConstituenciesByElectionTypeAndStateId(Long.valueOf(1), Long.valueOf(1)).getConstituencies();*/
        
        /*LOG.info("Title: " + cadreManagementVO.getUserEvents().get(0).getEventDisplayTitle());
        LOG.info("ImpDate: " + cadreManagementVO.getUserImpDates().get(0).getTitle());*/
        
        /*userGroups = userGroupService.getAllMyGroupsCreatedByUser(user.getRegistrationID()).size();
        systemGroups = userGroupService.subGrpsCountInSystemGrpsForUser(user.getRegistrationID()).size();*/
        /*LOG.info("EventCount: "  + eventCount + "User groups:" + userGroups + "System Groups:" + systemGroups);
        for(Entry<String, Long> s: cadreManagementVO.getCadresByCadreLevel().entrySet()) {
        	LOG.info(s.getKey() + " :" + s.getValue());
        }*/
        loginUserProfilePic = ananymousUserService.getUserProfileImageByUserId(user.getRegistrationID());
        
		if(cadreManagementVO!=null && cadreManagementVO.getExceptionEncountered()!=null)
			LOG.error(cadreManagementVO.getExceptionEncountered().getMessage());
		
       if(user!=null && entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.NEWS_MONITORING_ENTITLEMENT)){
        	hasNewsMonitoring = true;
        	//fileVOList = candidateDetailsService.getNewsGalleryByUserIdFromUserGallery(user.getRegistrationID());
        }
             
       if(user != null && entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.ADD_SUBUSER_ENTITLEMENT)){
    	   hasSubUserEntitlement = true;
       }
       if(user != null && entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.CALL_CENTER_ENTITLEMENT)){
    	   hasCallCenterEntitlment = true;
       }
       if(user != null && entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.PROFILE_MANAGEMENT_ENTITLEMENT))
    	   hasProfileManagement = true;
       
        return SUCCESS;
    }
	
  public String indexPageAjaxCallHandler()
	{
		session = request.getSession();
		user = (RegistrationVO) session.getAttribute("USER");
		try {
			jObj = new JSONObject(getTask());
			
			if(jObj.getString("task").equalsIgnoreCase("getCadreInfo"))
			  cadreManagementVO = userCadreManagementService.getUserTodaysData(user);
			if(jObj.getString("task").equalsIgnoreCase("getUserImportantEvents"))
				cadreManagementVO = userCadreManagementService.getUserPlannedEvents(user.getRegistrationID());
			if(jObj.getString("task").equalsIgnoreCase("getUserImportanDates"))
				cadreManagementVO = userCadreManagementService.getUserTodaysImpDates(user);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
		
	}
}
