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

public class ConstituencyManagementWithVoterDataAction extends ActionSupport implements ServletRequestAware {

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
		
		if(user == null)
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
	
}


