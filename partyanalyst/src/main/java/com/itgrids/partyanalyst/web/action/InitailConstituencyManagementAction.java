package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementDataVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementInfluenceScopeDetailsVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementInfluenceScopeOverviewVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementRegionWiseOverviewVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementSubRegionWiseOverviewVO;
import com.itgrids.partyanalyst.dto.LocalUserGroupsInfoVO;
import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersInfoVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IUserGroupService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class InitailConstituencyManagementAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private List<SelectOptionVO> statusList;
	private static final Logger log = Logger.getLogger(InitailConstituencyManagementAction.class);
	private IProblemManagementReportService problemManagementReportService;
	private String accessType;
	private Long accessValue;
	private String task = null;
	JSONObject jObj = null;
	private List<ProblemBeanVO> problemsList;
	private String EXTERNAL_PERSON;
	private ISmsService smsCountrySmsService;
	private Long remainingSms;
	private List<LocalUserGroupsInfoVO> localUserGroupsInfoVO; 
	private List<UserGroupDetailsVO> userGroupDetailsVO;
	private IConstituencyManagementService constituencyManagementService;
	private IUserGroupService userGroupService;
	private List<UserGroupMembersInfoVO> userGroupMembersInfoVO;
	private ConstituencyManagementDataVO constituencyManagementDataVO;
	private IInfluencingPeopleService influencingPeopleService;
	private EntitlementsHelper entitlementsHelper;
	
	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}

	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}

	public ConstituencyManagementDataVO getConstituencyManagementDataVO() {
		return constituencyManagementDataVO;
	}

	public void setConstituencyManagementDataVO(
			ConstituencyManagementDataVO constituencyManagementDataVO) {
		this.constituencyManagementDataVO = constituencyManagementDataVO;
	}

	public IUserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(IUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
	
	public List<UserGroupMembersInfoVO> getUserGroupMembersInfoVO() {
		return userGroupMembersInfoVO;
	}

	public void setUserGroupMembersInfoVO(
			List<UserGroupMembersInfoVO> userGroupMembersInfoVO) {
		this.userGroupMembersInfoVO = userGroupMembersInfoVO;
	}

	public IConstituencyManagementService getConstituencyManagementService() {
		return constituencyManagementService;
	}

	public void setConstituencyManagementService(
			IConstituencyManagementService constituencyManagementService) {
		this.constituencyManagementService = constituencyManagementService;
	}

	public List<UserGroupDetailsVO> getUserGroupDetailsVO() {
		return userGroupDetailsVO;
	}

	public void setUserGroupDetailsVO(List<UserGroupDetailsVO> userGroupDetailsVO) {
		this.userGroupDetailsVO = userGroupDetailsVO;
	}

	public List<LocalUserGroupsInfoVO> getLocalUserGroupsInfoVO() {
		return localUserGroupsInfoVO;
	}

	public void setLocalUserGroupsInfoVO(
			List<LocalUserGroupsInfoVO> localUserGroupsInfoVO) {
		this.localUserGroupsInfoVO = localUserGroupsInfoVO;
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
	
	public String getEXTERNAL_PERSON() {
		return EXTERNAL_PERSON;
	}

	public void setEXTERNAL_PERSON(String external_person) {
		EXTERNAL_PERSON = external_person;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<SelectOptionVO> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<SelectOptionVO> statusList) {
		this.statusList = statusList;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public Long getAccessValue() {
		return accessValue;
	}

	public void setAccessValue(Long accessValue) {
		this.accessValue = accessValue;
	}

	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}	

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}	
	
	public List<ProblemBeanVO> getProblemsList() {
		return problemsList;
	}

	public void setProblemsList(List<ProblemBeanVO> problemsList) {
		this.problemsList = problemsList;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute() throws Exception{
		
		log.debug("In execute of Constituency Management Action ********");
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CONSTITUENCY_MANAGEMENT_ENTITLEMENT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CONSTITUENCY_MANAGEMENT_ENTITLEMENT))
			return ERROR;
				*/
		accessType = user.getAccessType();
		accessValue= new Long(user.getAccessValue());
		
		Long userID = user.getRegistrationID();
		remainingSms = smsCountrySmsService.getRemainingSmsLeftForUser(userID);
		
		EXTERNAL_PERSON = IConstants.EXTERNAL_PERSON;
		
		statusList = problemManagementReportService.getAllProblemStatusInfo();
		statusList.add(0,new SelectOptionVO(-1l, "Select Status"));
		//String accessType, Long accessValue, Long statusId, int limit
		/*LocationwiseProblemStatusInfoVO locationwiseProblemStatusInfoVO = problemManagementReportService.getRecentProblemsWithInTheRegion(accessType, accessValue, 1l, 10);
		problemsList = locationwiseProblemStatusInfoVO.getRecentProblems();	*/	
		problemsList = problemManagementReportService.getRecentProblemsForUser(userID,1l);
		
		return SUCCESS;
	}
	
	public String getProblemDetailsByStatus()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return ERROR;
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			Long status =  jObj.getLong("status");
			Long locationId = new Long(jObj.getLong("locationId"));//contains access value
			String accessType = jObj.getString("accessType");	
			//problemsList = problemManagementReportService.getProblemsInfoByStatusInALocation(locationId, accessType, user.getRegistrationID(), status);
			problemsList = problemManagementReportService.getProblemsInfoByStatusInALocationForUser(locationId,accessType,user.getRegistrationID(),status);
		
		return SUCCESS;
	}
	
	public String getLocalUserGroups()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return ERROR;
					
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		String regionId = jObj.getString("regionId");
		String regionType = jObj.getString("regionType");
		String taskType = jObj.getString("task");
		
		String accessRegionId = "";
		String accessRegionType = "";
		
		if(taskType.equalsIgnoreCase("reGetLocalGroupsInAConstituency"))
		{
			accessRegionId = regionId;
			accessRegionType = regionType;
		}
		
		constituencyManagementDataVO = influencingPeopleService.getLocalUserGroupOverviewDetails(user.getRegistrationID(),accessRegionType,accessRegionId);
		//localUserGroupsInfoVO = constituencyManagementService.getLocalUserGroupsCandidatesByAccesstypeAndAccessValues(user.getRegistrationID(), user.getAccessType(), user.getAccessValue());
		return Action.SUCCESS;
	}
	
	public ConstituencyManagementDataVO getLocalUserGroupsDummyData()
	{
		ConstituencyManagementDataVO userGroupMainVO = new ConstituencyManagementDataVO();
		
		ConstituencyManagementRegionWiseOverviewVO region = new ConstituencyManagementRegionWiseOverviewVO();
		region.setRegionId(new Long(1));
		region.setRegionTitle("Apartment");
		region.setRegionName("Nellore");
		region.setRegionType("District");
		region.setCountValue(new Long(500));
		
		List<ConstituencyManagementSubRegionWiseOverviewVO> subRegions = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
		
		ConstituencyManagementSubRegionWiseOverviewVO sub1 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub1.setSubRegionId(new Long(1));
		sub1.setSubRegionName("Allur");
		sub1.setSubRegionType("Mandal");
		sub1.setCountValue(new Long(100));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub2 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub2.setSubRegionId(new Long(2));
		sub2.setSubRegionName("Bogole");
		sub2.setSubRegionType("Mandal");
		sub2.setCountValue(new Long(200));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub3 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub3.setSubRegionId(new Long(3));
		sub3.setSubRegionName("Kavali");
		sub3.setSubRegionType("Municipality");
		sub3.setCountValue(new Long(100));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub4 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub4.setSubRegionId(new Long(4));
		sub4.setSubRegionName("Dagadarthi");
		sub4.setSubRegionType("Mandal");
		sub4.setCountValue(new Long(100));
		
		subRegions.add(sub1);
		subRegions.add(sub2);
		subRegions.add(sub3);
		subRegions.add(sub4);
		
		region.setSubRegionWiseOverview(subRegions);
		
		List<ConstituencyManagementInfluenceScopeOverviewVO> scopeList = new ArrayList<ConstituencyManagementInfluenceScopeOverviewVO>();
		
		//--
		ConstituencyManagementInfluenceScopeOverviewVO scope1 = new ConstituencyManagementInfluenceScopeOverviewVO();
		scope1.setInfluenceScope("State");
		scope1.setCountValue(new Long(50));
		
		List<ConstituencyManagementInfluenceScopeDetailsVO> subScopeList1 = new ArrayList<ConstituencyManagementInfluenceScopeDetailsVO>();
		
		ConstituencyManagementInfluenceScopeDetailsVO subScope11 = new ConstituencyManagementInfluenceScopeDetailsVO();
		subScope11.setInfluenceScopeRegionId(new Long(1));
		subScope11.setInfluenceScopeRegion("Andhra Pradesh");		
		subScope11.setCountValue(new Long(50));
		
		subScopeList1.add(subScope11);
		scope1.setInfluenceScopeDetails(subScopeList1);
		//--
		
		//--
		ConstituencyManagementInfluenceScopeOverviewVO scope2 = new ConstituencyManagementInfluenceScopeOverviewVO();
		scope2.setInfluenceScope("District");
		scope2.setCountValue(new Long(100));
		
		List<ConstituencyManagementInfluenceScopeDetailsVO> subScopeList2 = new ArrayList<ConstituencyManagementInfluenceScopeDetailsVO>();
		
		ConstituencyManagementInfluenceScopeDetailsVO subScope21 = new ConstituencyManagementInfluenceScopeDetailsVO();
		subScope21.setInfluenceScopeRegionId(new Long(1));
		subScope21.setInfluenceScopeRegion("Nellore");		
		subScope21.setCountValue(new Long(50));
		
		ConstituencyManagementInfluenceScopeDetailsVO subScope22 = new ConstituencyManagementInfluenceScopeDetailsVO();
		subScope22.setInfluenceScopeRegionId(new Long(2));
		subScope22.setInfluenceScopeRegion("Prakasam");		
		subScope22.setCountValue(new Long(20));
		
		ConstituencyManagementInfluenceScopeDetailsVO subScope23 = new ConstituencyManagementInfluenceScopeDetailsVO();
		subScope23.setInfluenceScopeRegionId(new Long(3));
		subScope23.setInfluenceScopeRegion("Chittor");		
		subScope23.setCountValue(new Long(30));
		
		subScopeList2.add(subScope21);
		subScopeList2.add(subScope22);
		subScopeList2.add(subScope23);
		
		scope2.setInfluenceScopeDetails(subScopeList2);
		//--
		
		//--
		ConstituencyManagementInfluenceScopeOverviewVO scope3 = new ConstituencyManagementInfluenceScopeOverviewVO();
		scope3.setInfluenceScope("Constituency");
		scope3.setCountValue(new Long(250));
		
		List<ConstituencyManagementInfluenceScopeDetailsVO> subScopeList3 = new ArrayList<ConstituencyManagementInfluenceScopeDetailsVO>();
		
		ConstituencyManagementInfluenceScopeDetailsVO subScope31 = new ConstituencyManagementInfluenceScopeDetailsVO();
		subScope31.setInfluenceScopeRegionId(new Long(1));
		subScope31.setInfluenceScopeRegion("Kavali");		
		subScope31.setCountValue(new Long(50));
		
		ConstituencyManagementInfluenceScopeDetailsVO subScope32 = new ConstituencyManagementInfluenceScopeDetailsVO();
		subScope32.setInfluenceScopeRegionId(new Long(2));
		subScope32.setInfluenceScopeRegion("Gudur");		
		subScope32.setCountValue(new Long(100));
		
		ConstituencyManagementInfluenceScopeDetailsVO subScope33 = new ConstituencyManagementInfluenceScopeDetailsVO();
		subScope33.setInfluenceScopeRegionId(new Long(3));
		subScope33.setInfluenceScopeRegion("Ongole");		
		subScope33.setCountValue(new Long(100));
		
		subScopeList3.add(subScope31);
		subScopeList3.add(subScope32);
		subScopeList3.add(subScope33);
		
		scope3.setInfluenceScopeDetails(subScopeList3);
		//--
		
		scopeList.add(scope1);
		scopeList.add(scope2);
		scopeList.add(scope3);
		
		userGroupMainVO.setRegionWiseOverview(region);
		userGroupMainVO.setInfluenceScopeOverview(scopeList);
		
		
		List<ConstituencyManagementRegionWiseOverviewVO> categoriesList = new ArrayList<ConstituencyManagementRegionWiseOverviewVO>();
		
		ConstituencyManagementRegionWiseOverviewVO region1 = new ConstituencyManagementRegionWiseOverviewVO();
		region1.setRegionId(new Long(1));
		region1.setRegionTitle("Mahila Group");
		region1.setRegionName("Nellore");
		region1.setRegionType("District");
		region1.setCountValue(new Long(400));
		
		List<ConstituencyManagementSubRegionWiseOverviewVO> subRegions1 = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
		
		ConstituencyManagementSubRegionWiseOverviewVO sub11 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub11.setSubRegionId(new Long(1));
		sub11.setSubRegionName("Allur");
		sub11.setSubRegionType("Mandal");
		sub11.setCountValue(new Long(50));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub21 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub21.setSubRegionId(new Long(2));
		sub21.setSubRegionName("Bogole");
		sub21.setSubRegionType("Mandal");
		sub21.setCountValue(new Long(250));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub31 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub31.setSubRegionId(new Long(3));
		sub31.setSubRegionName("Kavali");
		sub31.setSubRegionType("Municipality");
		sub31.setCountValue(new Long(100));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub41 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub41.setSubRegionId(new Long(4));
		sub41.setSubRegionName("Dagadarthi");
		sub41.setSubRegionType("Mandal");
		sub41.setCountValue(new Long(100));
		
		subRegions1.add(sub11);
		subRegions1.add(sub21);
		subRegions1.add(sub31);
		subRegions1.add(sub41);
		
		region1.setSubRegionWiseOverview(subRegions1);
		
		ConstituencyManagementRegionWiseOverviewVO region2 = new ConstituencyManagementRegionWiseOverviewVO();
		region2.setRegionId(new Long(1));
		region2.setRegionTitle("Dwakara Group");
		region2.setRegionName("Nellore");
		region2.setRegionType("District");
		region2.setCountValue(new Long(600));
		
		List<ConstituencyManagementSubRegionWiseOverviewVO> subRegions2 = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
		
		ConstituencyManagementSubRegionWiseOverviewVO sub12 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub12.setSubRegionId(new Long(1));
		sub12.setSubRegionName("Allur");
		sub12.setSubRegionType("Mandal");
		sub12.setCountValue(new Long(150));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub22 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub22.setSubRegionId(new Long(2));
		sub22.setSubRegionName("Bogole");
		sub22.setSubRegionType("Mandal");
		sub22.setCountValue(new Long(150));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub32 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub32.setSubRegionId(new Long(3));
		sub32.setSubRegionName("Kavali");
		sub32.setSubRegionType("Municipality");
		sub32.setCountValue(new Long(200));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub42 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub42.setSubRegionId(new Long(4));
		sub42.setSubRegionName("Dagadarthi");
		sub42.setSubRegionType("Mandal");
		sub42.setCountValue(new Long(100));
		
		subRegions2.add(sub12);
		subRegions2.add(sub21);
		subRegions2.add(sub22);
		subRegions2.add(sub42);
		
		region2.setSubRegionWiseOverview(subRegions2);
		
		ConstituencyManagementRegionWiseOverviewVO region3 = new ConstituencyManagementRegionWiseOverviewVO();
		region3.setRegionId(new Long(1));
		region3.setRegionTitle("Youth Group");
		region3.setRegionName("Nellore");
		region3.setRegionType("District");
		region3.setCountValue(new Long(300));
		
		List<ConstituencyManagementSubRegionWiseOverviewVO> subRegions3 = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
		
		ConstituencyManagementSubRegionWiseOverviewVO sub13 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub13.setSubRegionId(new Long(1));
		sub13.setSubRegionName("Allur");
		sub13.setSubRegionType("Mandal");
		sub13.setCountValue(new Long(50));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub23 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub23.setSubRegionId(new Long(2));
		sub23.setSubRegionName("Bogole");
		sub23.setSubRegionType("Mandal");
		sub23.setCountValue(new Long(150));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub33 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub33.setSubRegionId(new Long(3));
		sub33.setSubRegionName("Kavali");
		sub33.setSubRegionType("Municipality");
		sub33.setCountValue(new Long(50));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub43 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub43.setSubRegionId(new Long(4));
		sub43.setSubRegionName("Dagadarthi");
		sub43.setSubRegionType("Mandal");
		sub43.setCountValue(new Long(50));
		
		subRegions3.add(sub13);
		subRegions3.add(sub23);
		subRegions3.add(sub33);
		subRegions3.add(sub43);
		
		region3.setSubRegionWiseOverview(subRegions3);
		
		categoriesList.add(region1);
		categoriesList.add(region2);
		categoriesList.add(region3);
		
		userGroupMainVO.setCategoryListOverview(categoriesList);
		
		return userGroupMainVO;
	}
	
	public String getUserGroupsBasedOnCriteria()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return ERROR;
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		String groupName =  jObj.getString("groupName");
		Long groupId = new Long(jObj.getLong("groupId"));//contains access value
		String location = jObj.getString("locationType");
		String locationType = "";
		if("States".equalsIgnoreCase(location))
			locationType = IConstants.STATE;
		else if("Districts".equalsIgnoreCase(location))
			locationType = IConstants.DISTRICT;
		else if("Constituencies".equalsIgnoreCase(location))
			locationType = IConstants.CONSTITUENCY;
		else if("Towns/Cities".equalsIgnoreCase(location))
			locationType = IConstants.LOCAL_ELECTION_BODY;
		else if("Tehsils".equalsIgnoreCase(location))
			locationType = IConstants.TEHSIL;
		else if("Wards".equalsIgnoreCase(location))
			locationType = IConstants.WARD;
		else if("Villages".equalsIgnoreCase(location))
			locationType = IConstants.VILLAGE;		
	
		
		userGroupDetailsVO = constituencyManagementService.findUserGroupsByLocationCategoryAndUserId(user.getRegistrationID(), groupId, locationType);
		
		/*userGroupDetailsVO = new ArrayList<UserGroupDetailsVO>();
		
		UserGroupDetailsVO u1 = new UserGroupDetailsVO();
		u1.setGroupName("Group 1");
		u1.setGroupId(new Long(1));
		u1.setGroupDesc("Group 1 Description");
		u1.setCreatedDate("27-10-2010");
		u1.setNoOfPersons("54");
		u1.setLocationInfo("Kavali Mandal");
		
		UserGroupDetailsVO u2 = new UserGroupDetailsVO();
		u2.setGroupName("Group 1");
		u2.setGroupId(new Long(1));
		u2.setGroupDesc("Group 1 Description");
		u2.setCreatedDate("27-10-2010");
		u2.setNoOfPersons("54");
		u2.setLocationInfo("Atmakur Mandal");
		
		userGroupDetailsVO.add(u1);
		userGroupDetailsVO.add(u2);*/
		
		return Action.SUCCESS;
	}
	
	public String getUserGroupsCandidates()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
					
		Long groupId = new Long(request.getParameter("groupId"));//contains access value
		
		//userGroupMembersInfoVO = userGroupService.getCompleteUserGroupMemberDetailsForAGroup(groupId);
		
		userGroupMembersInfoVO = new ArrayList<UserGroupMembersInfoVO>();
		
		UserGroupMembersInfoVO u1 = new UserGroupMembersInfoVO();
		u1.setName("Candidate 1");
		u1.setUserId(new Long(1));
		u1.setMobileNumber("9959985735");
		u1.setLocation("Nellore");
		
		UserGroupMembersInfoVO u2 = new UserGroupMembersInfoVO();
		u2.setName("Candidate 2");
		u2.setUserId(new Long(2));
		u2.setMobileNumber("99899722789");
		u2.setLocation("Nellore");
		
		UserGroupMembersInfoVO u3 = new UserGroupMembersInfoVO();
		u3.setName("Candidate 3");
		u3.setUserId(new Long(3));
		u3.setMobileNumber("9948755741");
		u3.setLocation("Nellore");
		
		UserGroupMembersInfoVO u4 = new UserGroupMembersInfoVO();
		u4.setName("Candidate 4");
		u4.setUserId(new Long(4));
		u4.setMobileNumber("9989876597");
		u4.setLocation("Nellore");
		
		userGroupMembersInfoVO.add(u1);
		userGroupMembersInfoVO.add(u2);
		userGroupMembersInfoVO.add(u3);
		userGroupMembersInfoVO.add(u4);
		
		return Action.SUCCESS;
	}

	

}
