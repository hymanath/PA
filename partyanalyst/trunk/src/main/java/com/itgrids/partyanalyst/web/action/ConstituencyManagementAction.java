package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementDataVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementRegionWiseCompleteDataVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleDetailsVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocalUserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.RegionSelectOptionVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsResultVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.CrossVotingEstimationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ConstituencyManagementAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ConstituencyManagementAction.class);
	private CadreManagementService cadreManagementService;
	private IProblemManagementService problemManagementService;
	private CrossVotingEstimationService crossVotingEstimationService;
	private ConstituencyManagementVO constituencyManagementVO;
	private HttpSession session;
	private HttpServletRequest request;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> villageList;
	private List<SelectOptionVO> hamletList;
	private IRegionServiceData regionServiceData;
	private List<SelectOptionVO> problemSources;	
	private List<SelectOptionVO> statesList, districtsList, constituenciesList; 
	private IProblemManagementReportService problemManagementReportService;
	private String accessType;
	private Long accessValue;
	private List<InfluencingPeopleVO> influencingPeopleVO;
	private String task = null;
	JSONObject jObj = null;
	private String cmTask;
	private String reportResult;
	private List<SelectOptionVO> parliamentConstituencyList;
	private IStaticDataService staticDataService;
	private ISmsService smsCountrySmsService;
	private Long remainingSms;
	private ConstituencyManagementDataVO constituencyManagementDataVO;
	
	private IInfluencingPeopleService influencingPeopleService;
	private List<InfluencingPeopleDetailsVO> influencingPeopleDetailsVO;
	private String regionName = null;
	private String regionType = null;
	private String scopeType = null;
	private String regionTitle = null;
	
	private ConstituencyManagementRegionWiseCompleteDataVO regionWiseCompleteDataVO;
	private List<LocalUserGroupDetailsVO> localGroupsPeople;
	private SmsResultVO smsResultVO;
	private List<RegionSelectOptionVO> regionSelectOptionVO;
	private InfluencingPeopleBeanVO influencingPersonVO;
	private List<SelectOptionVO> problemStatus;
	private List<SelectOptionVO> groupList;
	
	
	public List<SelectOptionVO> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<SelectOptionVO> groupList) {
		this.groupList = groupList;
	}

	public List<SelectOptionVO> getProblemStatus() {
		return problemStatus;
	}

	public void setProblemStatus(List<SelectOptionVO> problemStatus) {
		this.problemStatus = problemStatus;
	}

	public InfluencingPeopleBeanVO getInfluencingPersonVO() {
		return influencingPersonVO;
	}

	public void setInfluencingPersonVO(InfluencingPeopleBeanVO influencingPersonVO) {
		this.influencingPersonVO = influencingPersonVO;
	}

	public List<RegionSelectOptionVO> getRegionSelectOptionVO() {
		return regionSelectOptionVO;
	}

	public void setRegionSelectOptionVO(
			List<RegionSelectOptionVO> regionSelectOptionVO) {
		this.regionSelectOptionVO = regionSelectOptionVO;
	}

	public SmsResultVO getSmsResultVO() {
		return smsResultVO;
	}

	public void setSmsResultVO(SmsResultVO smsResultVO) {
		this.smsResultVO = smsResultVO;
	}

	public List<LocalUserGroupDetailsVO> getLocalGroupsPeople() {
		return localGroupsPeople;
	}

	public void setLocalGroupsPeople(List<LocalUserGroupDetailsVO> localGroupsPeople) {
		this.localGroupsPeople = localGroupsPeople;
	}

	public String getRegionTitle() {
		return regionTitle;
	}

	public void setRegionTitle(String regionTitle) {
		this.regionTitle = regionTitle;
	}

	public ConstituencyManagementRegionWiseCompleteDataVO getRegionWiseCompleteDataVO() {
		return regionWiseCompleteDataVO;
	}

	public void setRegionWiseCompleteDataVO(
			ConstituencyManagementRegionWiseCompleteDataVO regionWiseCompleteDataVO) {
		this.regionWiseCompleteDataVO = regionWiseCompleteDataVO;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	public String getScopeType() {
		return scopeType;
	}

	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}

	public List<InfluencingPeopleDetailsVO> getInfluencingPeopleDetailsVO() {
		return influencingPeopleDetailsVO;
	}

	public void setInfluencingPeopleDetailsVO(
			List<InfluencingPeopleDetailsVO> influencingPeopleDetailsVO) {
		this.influencingPeopleDetailsVO = influencingPeopleDetailsVO;
	}

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

	public String getCmTask() {
		return cmTask;
	}

	public void setCmTask(String cmTask) {
		this.cmTask = cmTask;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}

	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	
	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}

	public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
		
	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public CrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			CrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}

	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}

	public List<SelectOptionVO> getVillageList() {
		return villageList;
	}

	public void setVillageList(List<SelectOptionVO> villageList) {
		this.villageList = villageList;
	}
		
	public List<SelectOptionVO> getHamletList() {
		return hamletList;
	}

	public void setHamletList(List<SelectOptionVO> hamletList) {
		this.hamletList = hamletList;
	}

	public IRegionServiceData getRegionServiceData() {
		return regionServiceData;
	}

	public void setRegionServiceData(IRegionServiceData regionServiceData) {
		this.regionServiceData = regionServiceData;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
			
	public List<SelectOptionVO> getProblemSources() {
		return problemSources;
	}

	public void setProblemSources(List<SelectOptionVO> problemSources) {
		this.problemSources = problemSources;
	}
	
	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}	

	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}

	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public List<InfluencingPeopleVO> getInfluencingPeopleVO() {
		return influencingPeopleVO;
	}

	public void setInfluencingPeopleVO(List<InfluencingPeopleVO> influencingPeopleVO) {
		this.influencingPeopleVO = influencingPeopleVO;
	}
	
	public String getReportResult() {
		return reportResult;
	}

	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTask() {
		return task;
	}


	public void setParliamentConstituencyList(
			List<SelectOptionVO> parliamentConstituencyList) {
		this.parliamentConstituencyList = parliamentConstituencyList;
	}

	public List<SelectOptionVO> getParliamentConstituencyList() {
		return parliamentConstituencyList;
	}	

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	

	public Long getAccessValue() {
		return accessValue;
	}

	public void setAccessValue(Long accessValue) {
		this.accessValue = accessValue;
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

	public String execute() throws Exception{
		
		log.debug("In execute of Constituency Management Action ********");

		SelectOptionVO probSource1 = new SelectOptionVO(2L, IConstants.CALL_CENTER);
		SelectOptionVO probSource2 = new SelectOptionVO(3L, IConstants.USER);
		SelectOptionVO probSource3 = new SelectOptionVO(4L, IConstants.EXTERNAL_PERSON);
		problemSources = new ArrayList<SelectOptionVO>();
		problemSources.add(probSource1);
		problemSources.add(probSource2);
		problemSources.add(probSource3);
		
		problemSources = staticDataService.getAllInformationSources();	
				
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null || user.getRegistrationID() == null)
			return ERROR;
				
		accessType =user.getAccessType();
		accessValue= new Long(user.getAccessValue());		
		
		Long userID = user.getRegistrationID();
		remainingSms = smsCountrySmsService.getRemainingSmsLeftForUser(userID);
		
		
		stateList = new ArrayList<SelectOptionVO>();
		districtList = new ArrayList<SelectOptionVO>();
		constituencyList = new ArrayList<SelectOptionVO>();
		mandalList = new ArrayList<SelectOptionVO>();
		villageList = new ArrayList<SelectOptionVO>();
		parliamentConstituencyList = new ArrayList<SelectOptionVO>();
		if("MLA".equals(accessType))
		{
			log.debug("Access Type = MLA ****");
			List<SelectOptionVO> list = regionServiceData.getStateDistrictByConstituencyID(accessValue);
			
			stateList.add(list.get(0));			
			districtList.add(list.get(1));			
			constituencyList.add(list.get(2));
			mandalList = regionServiceData.getMandalsByConstituencyID(accessValue);
			mandalList.add(0,new SelectOptionVO(0L,"Select Mandal"));
						
		}else if("COUNTRY".equals(accessType))
		{
			log.debug("Access Type = Country ****");
			stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
			stateList.add(0,new SelectOptionVO(0L, "Select State"));
			
		}else if("STATE".equals(accessType)){
			log.debug("Access Type = State ****");
			
			String name = cadreManagementService.getStateName(accessValue);
			SelectOptionVO obj2 = new SelectOptionVO();
			obj2.setId(accessValue);
			obj2.setName(name);
			stateList.add(obj2);
			districtList = staticDataService.getDistricts(accessValue);
			districtList.add(0,new SelectOptionVO(0l,"Select District"));
			
		}else if("DISTRICT".equals(accessType)){
			log.debug("Access Type = District ****");			

			List<SelectOptionVO> list = regionServiceData.getStateDistrictByDistrictID(accessValue);
			stateList.add(list.get(0));
			districtList.add(list.get(1));
			constituencyList = regionServiceData.getConstituenciesByDistrictID(accessValue);
			constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			
		} else if("MP".equals(accessType)){
			log.debug("Access Type = MP ****");
			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
			stateList = regionServiceData.getStateByParliamentConstituencyID(accessValue);
			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
			constituencyList = constituencyInfoVO.getAssembyConstituencies();
			constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			parliamentConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName()));		
				
			log.debug("constituencyList.size():"+constituencyList.size());		
		}
		
		//problemStatus = staticDataService.getAllProblemStatus(); 
		
		problemStatus = staticDataService.getDefaultProblemStatus(IConstants.PROBLEMS_LIFE_CYCLE);
			
		if(cmTask != null){
		if(cmTask.equalsIgnoreCase(IConstants.PROBLEMS_MANAGEMENT))
			reportResult = IConstants.PROBLEMS_MANAGEMENT;
		else if(cmTask.equalsIgnoreCase(IConstants.CONSTITUENCY_MANAGEMENT))
			reportResult = IConstants.CONSTITUENCY_MANAGEMENT;
		else
			reportResult = "ALL";
		}
		
		return SUCCESS;
	}
	
	public String getInfluencePeopleSelectScope()
	{
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		Long regionId = new Long(jObj.getString("regionId"));
		String regionName = jObj.getString("regionName");
		String regionType = jObj.getString("regionType");
		String selectType = jObj.getString("selectType");
		
		regionSelectOptionVO = influencingPeopleService.getRegionsSelectOptionsForInput(regionId, regionType, selectType);
		return Action.SUCCESS;
	}
	
	public String getInfluencingPeopleByLocation()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
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
		
		if(taskType.equalsIgnoreCase("reGetInfluencingPeopleInAConstituency"))
		{
			accessRegionId = regionId;
			accessRegionType = regionType;
		}
		
		constituencyManagementDataVO = influencingPeopleService.getInfluencingPeopleOverviewDetails(userId,accessRegionType,accessRegionId);
		
		return SUCCESS;		
	}
	
	public String getSubLevelInfluenceAction()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		Long regionId = new Long(jObj.getString("regionId"));
		String regionName = jObj.getString("regionName");
		String regionType = jObj.getString("regionType");
		Long regionTitleId = new Long(jObj.getString("regionTitleId"));
		String regionTitle = jObj.getString("regionTitle");
		String areaType = jObj.getString("areaType");
		
		regionWiseCompleteDataVO = influencingPeopleService.getRegionsAndSubRegionsInfluencePeopleDetailsByRegionType(userId,regionId,regionType,IConstants.INFLUENCING_PEOPLE,0L,"",areaType);
		
		return Action.SUCCESS;
	}
		
	public String getSubLevelLocalGroupsAction()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		Long regionId = new Long(jObj.getString("regionId"));
		String regionName = jObj.getString("regionName");
		String regionType = jObj.getString("regionType");
		Long regionTitleId = new Long(jObj.getString("regionTitleId"));
		String regionTitle = jObj.getString("regionTitle");
		String areaType = jObj.getString("areaType");
		
		regionWiseCompleteDataVO = influencingPeopleService.getRegionsAndSubRegionsInfluencePeopleDetailsByRegionType(userId,regionId,regionType,IConstants.LOCAL_USER_GROUPS,regionTitleId,regionTitle,areaType);
		
		return Action.SUCCESS;
	}
	
	public String getInfluencingPeopleData()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		Long parentRegionId = new Long(request.getParameter("parentRegionId"));
		Long regionId = new Long(request.getParameter("regionId"));
		regionName = request.getParameter("regionName");
		regionType = request.getParameter("regionType");
		scopeType = request.getParameter("scopeType");
		
		if(scopeType.equalsIgnoreCase("region"))
			influencingPeopleDetailsVO = influencingPeopleService.getInfluencingPeopleDetailsByRegion(userId,regionId,regionType,parentRegionId);
		else if(scopeType.equalsIgnoreCase("scope"))
			influencingPeopleDetailsVO = influencingPeopleService.getInfluencingPeopleDetailsByScope(userId,regionId,regionType);
		
		return Action.SUCCESS;
	}
	
	public String sendSMSToInfluencePeople()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		String infPeopleIds = jObj.getString("checkedIdString");
		String message = jObj.getString("smsMessage");
		Boolean includeName = jObj.getBoolean("includeName");
		String taskType = jObj.getString("task");
		String senderName = jObj.getString("senderName");
		
		String module = null;
		
		if(taskType.equalsIgnoreCase("sendSMSToInfluencePeople"))
			module = IConstants.INFLUENCING_PEOPLE;
		else if(taskType.equalsIgnoreCase("sendSMSToLocalGroupPeople"))
			module = IConstants.LOCAL_USER_GROUPS;
		
		smsResultVO = influencingPeopleService.sendSMSToInfluencingPersons(userId, infPeopleIds, message, includeName, module, senderName);
		
		return Action.SUCCESS;
		
	}
	
	public String getInfluencingPersonDetails()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		Long personId = new Long(request.getParameter("personId"));
		
		influencingPersonVO = influencingPeopleService.getMoreResultsForInfluencingPeopleById(personId);
		
		/*influencingPersonVO.setFirstName("Ravi");
		influencingPersonVO.setLastName("P");
		influencingPersonVO.setEmail("ravi@gmail.com");
		influencingPersonVO.setMobile("9989876597");
		influencingPersonVO.setGender("Male");		
		influencingPersonVO.setCast("General");
		influencingPersonVO.setFatherOrSpouseName("Prasad");
		influencingPersonVO.setHouseNo("No 22");
		influencingPersonVO.setStreetName("Bank Street");
		influencingPersonVO.setMandal("Allur");
		influencingPersonVO.setVillage("Allur");
		influencingPersonVO.setConstituency("Kavali");
		influencingPersonVO.setDistrict("Nellore");
		influencingPersonVO.setState("Andhra Pradesh");
		influencingPersonVO.setOccupation("Engineer");
		influencingPersonVO.setParty("INC");
		influencingPersonVO.setPosition("Youth Worker");
		influencingPersonVO.setInfluencingRange("District");
		influencingPersonVO.setInfluencingScopeValue("Nellore");*/
		
		
		
		return Action.SUCCESS;
	}
	
	public String getlocalGroupsPeopleData()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		Long regionId = new Long(request.getParameter("regionId"));
		regionName = request.getParameter("regionName");
		regionType = request.getParameter("regionType");
		Long regionTitleId = new Long(request.getParameter("regionTitleId"));
		regionTitle = request.getParameter("regionTitle");
		
		localGroupsPeople = influencingPeopleService.getLocalUserGroupDetails(userId,regionId,regionType,regionTitleId,regionTitle);
		//localGroupMembers= influencingPeopleService.getUserGroupMemberDetailsForAGroup();
		return Action.SUCCESS;
		
	}
	
	public void deleteLocalUserGroup()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
		}
		catch (ParseException e) {
			
			e.printStackTrace();
		}
		Long localUserGroupIdId = jObj.getLong("localUserGroupIdId");
		
		Integer rows = influencingPeopleService.deleteLocalUserGroup(localUserGroupIdId);
		Log.debug("rows:"+rows);
	}
	
	/*
	public List<InfluencingPeopleDetailsVO> getInfluencePeopleDummyList()
	{
		List<InfluencingPeopleDetailsVO> peopleVO = new ArrayList<InfluencingPeopleDetailsVO>();
		
		//--
		InfluencingPeopleDetailsVO p1 = new InfluencingPeopleDetailsVO();
		p1.setRegionId(new Long(1));
		p1.setRegionName("Kavali");
		p1.setRegionType("Assembly");
		
		List<InfluencingPeopleBeanVO> influencingPeople = new ArrayList<InfluencingPeopleBeanVO>();
		
		InfluencingPeopleBeanVO bean1 = new InfluencingPeopleBeanVO();
		bean1.setFirstName("Sai Krishna");
		bean1.setLastName("Basetti");
		bean1.setEmail("sai@gmail.com");
		bean1.setMobile("9988558877");
		bean1.setGender("Male");
		bean1.setCast("BC");
		bean1.setConstituencyName("Kavali");
		bean1.setDistrictName("Nellore");
		bean1.setStateName("Andhra Pradesh");
		
		InfluencingPeopleBeanVO bean2 = new InfluencingPeopleBeanVO();
		bean2.setFirstName("Siva");
		bean2.setLastName("Reddivari");
		bean2.setEmail("siva@gmail.com");
		bean2.setMobile("995599887788");
		bean2.setGender("Male");
		bean2.setCast("BC");
		bean2.setConstituencyName("Allur");
		bean2.setDistrictName("Nellore");
		bean2.setStateName("Andhra Pradesh");
		
		InfluencingPeopleBeanVO bean3 = new InfluencingPeopleBeanVO();
		bean3.setFirstName("Raghavender");
		bean3.setLastName("Prasad");
		bean3.setEmail("raghu@gmail.com");
		bean3.setMobile("9989922789");
		bean3.setGender("Male");
		bean3.setCast("BC");
		bean3.setConstituencyName("Kavali");
		bean3.setDistrictName("Nellore");
		bean3.setStateName("Andhra Pradesh");
		
		influencingPeople.add(bean1);
		influencingPeople.add(bean2);
		influencingPeople.add(bean3);
		
		p1.setInfluencingPeopleDetails(influencingPeople);
		//--
		
		//--
		InfluencingPeopleDetailsVO p2 = new InfluencingPeopleDetailsVO();
		p2.setRegionId(new Long(1));
		p2.setRegionName("Allur");
		p2.setRegionType("Assembly");
		
		List<InfluencingPeopleBeanVO> influencingPeople1 = new ArrayList<InfluencingPeopleBeanVO>();
		
		InfluencingPeopleBeanVO bean21 = new InfluencingPeopleBeanVO();
		bean21.setFirstName("Sai Krishna");
		bean21.setLastName("Basetti");
		bean21.setEmail("sai@gmail.com");
		bean21.setMobile("9988558877");
		bean21.setGender("Male");
		bean21.setCast("BC");
		bean21.setConstituencyName("Kavali");
		bean21.setDistrictName("Nellore");
		bean21.setStateName("Andhra Pradesh");
		
		InfluencingPeopleBeanVO bean22 = new InfluencingPeopleBeanVO();
		bean22.setFirstName("Siva");
		bean22.setLastName("Reddivari");
		bean22.setEmail("siva@gmail.com");
		bean22.setMobile("995599887788");
		bean22.setGender("Male");
		bean22.setCast("BC");
		bean22.setConstituencyName("Allur");
		bean22.setDistrictName("Nellore");
		bean22.setStateName("Andhra Pradesh");
		
		InfluencingPeopleBeanVO bean23 = new InfluencingPeopleBeanVO();
		bean23.setFirstName("Raghavender");
		bean23.setLastName("Prasad");
		bean23.setEmail("raghu@gmail.com");
		bean23.setMobile("9989922789");
		bean23.setGender("Male");
		bean23.setCast("BC");
		bean23.setConstituencyName("Kavali");
		bean23.setDistrictName("Nellore");
		bean23.setStateName("Andhra Pradesh");
		
		influencingPeople1.add(bean21);
		influencingPeople1.add(bean22);
		influencingPeople1.add(bean23);
		
		p2.setInfluencingPeopleDetails(influencingPeople1);
		//--
		
		//--
		InfluencingPeopleDetailsVO p3 = new InfluencingPeopleDetailsVO();
		p3.setRegionId(new Long(1));
		p3.setRegionName("Atmakur");
		p3.setRegionType("Assembly");
		
		List<InfluencingPeopleBeanVO> influencingPeople3 = new ArrayList<InfluencingPeopleBeanVO>();
		
		InfluencingPeopleBeanVO bean31 = new InfluencingPeopleBeanVO();
		bean31.setFirstName("Sai Krishna");
		bean31.setLastName("Basetti");
		bean31.setEmail("sai@gmail.com");
		bean31.setMobile("9988558877");
		bean31.setGender("Male");
		bean31.setCast("BC");
		bean31.setConstituencyName("Kavali");
		bean31.setDistrictName("Nellore");
		bean31.setStateName("Andhra Pradesh");
		
		InfluencingPeopleBeanVO bean32 = new InfluencingPeopleBeanVO();
		bean32.setFirstName("Siva");
		bean32.setLastName("Reddivari");
		bean32.setEmail("siva@gmail.com");
		bean32.setMobile("995599887788");
		bean32.setGender("Male");
		bean32.setCast("BC");
		bean32.setConstituencyName("Allur");
		bean32.setDistrictName("Nellore");
		bean32.setStateName("Andhra Pradesh");
		
		InfluencingPeopleBeanVO bean33 = new InfluencingPeopleBeanVO();
		bean33.setFirstName("Raghavender");
		bean33.setLastName("Prasad");
		bean33.setEmail("raghu@gmail.com");
		bean33.setMobile("9989922789");
		bean33.setGender("Male");
		bean33.setCast("BC");
		bean33.setConstituencyName("Kavali");
		bean33.setDistrictName("Nellore");
		bean33.setStateName("Andhra Pradesh");
		
		influencingPeople3.add(bean31);
		influencingPeople3.add(bean32);
		influencingPeople3.add(bean33);
		
		p3.setInfluencingPeopleDetails(influencingPeople3);
		//--
		
		peopleVO.add(p1);
		peopleVO.add(p2);
		peopleVO.add(p3);
		
		return peopleVO;		
	}
	public List<ConstituencyManagementRegionWiseOverviewVO> getSubRegionsDummyData()
	{
		List<ConstituencyManagementRegionWiseOverviewVO> constituencyManagementRegionWiseOverviewVO = new ArrayList<ConstituencyManagementRegionWiseOverviewVO>();
		
		//--
		ConstituencyManagementRegionWiseOverviewVO region = new ConstituencyManagementRegionWiseOverviewVO();
		region.setRegionId(new Long(1));
		region.setRegionName("Allur");
		region.setRegionType("Mandal");
		region.setCountValue(new Long(100));
		
		List<ConstituencyManagementSubRegionWiseOverviewVO> subRegions = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
		
		ConstituencyManagementSubRegionWiseOverviewVO sub1 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub1.setSubRegionId(new Long(1));
		sub1.setSubRegionName("Allur");
		sub1.setSubRegionType("Village");
		sub1.setCountValue(new Long(25));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub2 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub2.setSubRegionId(new Long(2));
		sub2.setSubRegionName("Allurpeta");
		sub2.setSubRegionType("Village");
		sub2.setCountValue(new Long(25));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub3 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub3.setSubRegionId(new Long(3));
		sub3.setSubRegionName("Beeramgunta");
		sub3.setSubRegionType("Village");
		sub3.setCountValue(new Long(30));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub4 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub4.setSubRegionId(new Long(4));
		sub4.setSubRegionName("Gogulapalli");
		sub4.setSubRegionType("Village");
		sub4.setCountValue(new Long(20));
		
		subRegions.add(sub1);
		subRegions.add(sub2);
		subRegions.add(sub3);
		subRegions.add(sub4);
		
		region.setSubRegionWiseOverview(subRegions);
		//--
		
		//--
		ConstituencyManagementRegionWiseOverviewVO region2 = new ConstituencyManagementRegionWiseOverviewVO();
		region2.setRegionId(new Long(1));
		region2.setRegionName("Bogole");
		region2.setRegionType("Mandal");
		region2.setCountValue(new Long(200));
		
		List<ConstituencyManagementSubRegionWiseOverviewVO> subRegions2 = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
		
		ConstituencyManagementSubRegionWiseOverviewVO sub21 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub21.setSubRegionId(new Long(1));
		sub21.setSubRegionName("Allur");
		sub21.setSubRegionType("Village");
		sub21.setCountValue(new Long(50));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub22 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub22.setSubRegionId(new Long(2));
		sub22.setSubRegionName("Allurpeta");
		sub22.setSubRegionType("Village");
		sub22.setCountValue(new Long(50));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub23 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub23.setSubRegionId(new Long(3));
		sub23.setSubRegionName("Beeramgunta");
		sub23.setSubRegionType("Village");
		sub23.setCountValue(new Long(25));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub24 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub24.setSubRegionId(new Long(4));
		sub24.setSubRegionName("Gogulapalli");
		sub24.setSubRegionType("Village");
		sub24.setCountValue(new Long(75));
		
		subRegions2.add(sub21);
		subRegions2.add(sub22);
		subRegions2.add(sub23);
		subRegions2.add(sub24);
		
		region2.setSubRegionWiseOverview(subRegions2);
		//--
		
		//--
		ConstituencyManagementRegionWiseOverviewVO region3 = new ConstituencyManagementRegionWiseOverviewVO();
		region3.setRegionId(new Long(1));
		region3.setRegionName("Kavali");
		region3.setRegionType("Municipality");
		region3.setCountValue(new Long(100));
		
		List<ConstituencyManagementSubRegionWiseOverviewVO> subRegions3 = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
		
		ConstituencyManagementSubRegionWiseOverviewVO sub31 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub31.setSubRegionId(new Long(1));
		sub31.setSubRegionName("Anantapuram");
		sub31.setSubRegionType("ward");
		sub31.setCountValue(new Long(20));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub32 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub32.setSubRegionId(new Long(2));
		sub32.setSubRegionName("Laxmipuram");
		sub32.setSubRegionType("Ward");
		sub32.setCountValue(new Long(50));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub33 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub33.setSubRegionId(new Long(3));
		sub33.setSubRegionName("Beeramgunta");
		sub33.setSubRegionType("ward");
		sub33.setCountValue(new Long(10));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub34 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub34.setSubRegionId(new Long(4));
		sub34.setSubRegionName("Gogulapalli");
		sub34.setSubRegionType("Ward");
		sub34.setCountValue(new Long(20));
		
		subRegions3.add(sub31);
		subRegions3.add(sub32);
		subRegions3.add(sub33);
		subRegions3.add(sub34);
		
		region3.setSubRegionWiseOverview(subRegions3);
		//--
		
		//--
		ConstituencyManagementRegionWiseOverviewVO region4 = new ConstituencyManagementRegionWiseOverviewVO();
		region4.setRegionId(new Long(1));
		region4.setRegionName("Dagadarthi");
		region4.setRegionType("mandal");
		region4.setCountValue(new Long(100));
		
		List<ConstituencyManagementSubRegionWiseOverviewVO> subRegions4 = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
		
		ConstituencyManagementSubRegionWiseOverviewVO sub41 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub41.setSubRegionId(new Long(1));
		sub41.setSubRegionName("Allur");
		sub41.setSubRegionType("Village");
		sub41.setCountValue(new Long(20));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub42 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub42.setSubRegionId(new Long(2));
		sub42.setSubRegionName("Allurpeta");
		sub42.setSubRegionType("Village");
		sub42.setCountValue(new Long(30));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub43 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub43.setSubRegionId(new Long(3));
		sub43.setSubRegionName("Beeramgunta");
		sub43.setSubRegionType("Village");
		sub43.setCountValue(new Long(20));
		
		ConstituencyManagementSubRegionWiseOverviewVO sub44 = new ConstituencyManagementSubRegionWiseOverviewVO();
		sub44.setSubRegionId(new Long(4));
		sub44.setSubRegionName("Gogulapalli");
		sub44.setSubRegionType("Village");
		sub44.setCountValue(new Long(30));
		
		subRegions4.add(sub41);
		subRegions4.add(sub42);
		subRegions4.add(sub43);
		subRegions4.add(sub44);
		
		region4.setSubRegionWiseOverview(subRegions4);
		
		//--
		
		constituencyManagementRegionWiseOverviewVO.add(region);
		constituencyManagementRegionWiseOverviewVO.add(region2);
		constituencyManagementRegionWiseOverviewVO.add(region3);
		constituencyManagementRegionWiseOverviewVO.add(region4);
		
		return constituencyManagementRegionWiseOverviewVO;
		
	}

	public ConstituencyManagementDataVO getInfluenceDummyData()
	{
		ConstituencyManagementDataVO constVO = new ConstituencyManagementDataVO();
		
		ConstituencyManagementRegionWiseOverviewVO region = new ConstituencyManagementRegionWiseOverviewVO();
		region.setRegionId(new Long(1));
		region.setRegionName("Kavali");
		region.setRegionType("Assembly");
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
		
		constVO.setRegionWiseOverview(region);
		constVO.setInfluenceScopeOverview(scopeList);
		
		return constVO;
	}
	*/
public String getGroupsForCategoery() {
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long categoeryId = jObj.getLong("groupCategoryval");
		if(jObj.getString("task").equalsIgnoreCase("getgroupsByCategoery")){
			groupList = influencingPeopleService.getGroupNamesBasedOnCategoeryId(categoeryId,userId);
		}
		return Action.SUCCESS;
		
	}
}


