package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.LocalUserGroupsInfoVO;
import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserGroupBasicInfoVO;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersInfoVO;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
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

	public String execute() throws Exception{
		
		log.debug("In execute of Constituency Management Action ********");
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return ERROR;
				
		accessType = user.getAccessType();
		accessValue= new Long(user.getAccessValue());
		
		Long userID = user.getRegistrationID();
		remainingSms = smsCountrySmsService.getRemainingSmsLeftForUser(userID);
		
		EXTERNAL_PERSON = IConstants.EXTERNAL_PERSON;
		
		statusList = problemManagementReportService.getAllProblemStatusInfo();
		statusList.add(0,new SelectOptionVO(-1l, "Select Status"));
		//String accessType, Long accessValue, Long statusId, int limit
		LocationwiseProblemStatusInfoVO locationwiseProblemStatusInfoVO = problemManagementReportService.getRecentProblemsWithInTheRegion(accessType, accessValue, 1l, 10);
		problemsList = locationwiseProblemStatusInfoVO.getRecentProblems();		
		
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
			String status =  jObj.getString("status");
			Long locationId = new Long(jObj.getLong("locationId"));//contains access value
			String accessType = jObj.getString("accessType");	
			problemsList = problemManagementReportService.getProblemsInfoByStatusInALocation(locationId, accessType, user.getRegistrationID(), status);
		
		return SUCCESS;
	}
	
	public String getLocalUserGroups()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return ERROR;
		
		localUserGroupsInfoVO = constituencyManagementService.getLocalUserGroupsCandidatesByAccesstypeAndAccessValues(user.getRegistrationID(), user.getAccessType(), user.getAccessValue());
		/*localUserGroupsInfoVO = new ArrayList<LocalUserGroupsInfoVO>();
		
		LocalUserGroupsInfoVO l1 = new LocalUserGroupsInfoVO();
		l1.setCategoryId(new Long(1));
		l1.setGroupCategoryName("Apartment");
		l1.setGroupsCount(new Long(25));
		
		List<UserGroupBasicInfoVO> bList = new ArrayList<UserGroupBasicInfoVO>();
		UserGroupBasicInfoVO b1 = new UserGroupBasicInfoVO();
		b1.setAreaType("District");
		b1.setGroupsCount(new Long(20));
		b1.setLocationsCount(new Long(1));
		
		UserGroupBasicInfoVO b2 = new UserGroupBasicInfoVO();
		b2.setAreaType("Constituency");
		b2.setGroupsCount(new Long(5));
		b2.setLocationsCount(new Long(4));
		
		bList.add(b1);
		bList.add(b2);
		
		l1.setLocationsWiseGroupInfo(bList);
		
		LocalUserGroupsInfoVO l2 = new LocalUserGroupsInfoVO();
		l2.setCategoryId(new Long(1));
		l2.setGroupCategoryName("Mahila Savings Group");
		l2.setGroupsCount(new Long(30));
		
		List<UserGroupBasicInfoVO> bMList = new ArrayList<UserGroupBasicInfoVO>();
		UserGroupBasicInfoVO bm1 = new UserGroupBasicInfoVO();
		bm1.setAreaType("District");
		bm1.setGroupsCount(new Long(20));
		bm1.setLocationsCount(new Long(1));
		
		UserGroupBasicInfoVO bm2 = new UserGroupBasicInfoVO();
		bm2.setAreaType("Constituency");
		bm2.setGroupsCount(new Long(10));
		bm2.setLocationsCount(new Long(5));
		
		bMList.add(bm1);
		bMList.add(bm2);
		
		l2.setLocationsWiseGroupInfo(bMList);
		
		localUserGroupsInfoVO.add(l1);
		localUserGroupsInfoVO.add(l2);*/
		
		return Action.SUCCESS;
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
		
		userGroupMembersInfoVO = userGroupService.getCompleteUserGroupMemberDetailsForAGroup(groupId);
		
		return Action.SUCCESS;
	}

	

}
