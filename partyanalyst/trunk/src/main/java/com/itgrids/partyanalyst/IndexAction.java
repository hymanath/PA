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
import com.itgrids.partyanalyst.dto.GroupsDetailsForUserVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IUserCadreManagementService;
import com.itgrids.partyanalyst.service.IUserGroupService;
import com.itgrids.partyanalyst.web.action.CadreManagementAction;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

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
    private int systemGroups;
    private int userGroups;
	private HttpSession session;
	private HttpServletRequest request;
	private int eventCount;
	private int impDateCount;
	private static final long serialVersionUID = 1L;
	
	private IUserCadreManagementService userCadreManagementService;
	private CadreManagementVO cadreManagementVO = null;
	private RegistrationVO user = null;
	private String changedUserName = "false";
	private String loginUserProfilePic;	
	private final static Logger log = Logger.getLogger(CadreManagementAction.class);

	
	
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
    
    public String execute() throws Exception {
    	session = request.getSession();
		user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return ERROR;
		else 
			System.out.print(user.getUserName());
        now = new Date(System.currentTimeMillis());
        mlaConstituenciesList = staticDataService.getConstituenciesByElectionTypeAndStateId(new Long(2), new Long(1)).getConstituencies();
        mpConstituenciesList = staticDataService.getConstituenciesByElectionTypeAndStateId(new Long(1), new Long(1)).getConstituencies();
        
        cadreManagementVO = userCadreManagementService.getUserTodaysData(user);
        eventCount = cadreManagementVO.getUserEvents().size();
        impDateCount = cadreManagementVO.getUserImpDates().size();
        /*System.out.println("Title: " + cadreManagementVO.getUserEvents().get(0).getEventDisplayTitle());
        System.out.println("ImpDate: " + cadreManagementVO.getUserImpDates().get(0).getTitle());*/
        userGroups = userGroupService.getAllMyGroupsCreatedByUser(user.getRegistrationID()).size();
        systemGroups = userGroupService.subGrpsCountInSystemGrpsForUser(user.getRegistrationID()).size();
        /*System.out.println("EventCount: "  + eventCount + "User groups:" + userGroups + "System Groups:" + systemGroups);
        for(Entry<String, Long> s: cadreManagementVO.getCadresByCadreLevel().entrySet()) {
        	System.out.println(s.getKey() + " :" + s.getValue());
        }*/
        loginUserProfilePic = ananymousUserService.getUserProfileImageByUserId(user.getRegistrationID());
		if(cadreManagementVO!=null && cadreManagementVO.getExceptionEncountered()!=null)
			log.error(cadreManagementVO.getExceptionEncountered().getMessage());
        
        return SUCCESS;
    }
}
