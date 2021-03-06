package com.itgrids.partyanalyst.web.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;


import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsVO;
import com.itgrids.partyanalyst.dto.VoiceSmsResponseDetailsVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.InfluencingPeopleService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.service.impl.StaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MessageCenterAction  extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String task;
	JSONObject jObj;
	private ResultStatus resultStatus;
	private HttpSession session;
	private HttpServletRequest request;
	private RegionServiceDataImp regionServiceDataImp;
	private Long accessValue;
	private String accessType;
	private List<SelectOptionVO> statesList,sublevelsList,districtList,constituenciesList,subRegions , parliamentConstituencyList,
	booths,hamletsOrWards;
	private InfluencingPeopleService influencingPeopleService;
	private StaticDataService staticDataService;
	private List<SmsVO> mobileNOs;
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private IVotersAnalysisService votersAnalysisService;
	private VoterHouseInfoVO voterHouseInfoVO;
	private Long publicationDateId;
	private Long constituencyId;
	private Long locationValue;
	private String locationType;
	private String locationName;
	private int verifiedNumbersCount;
	private IVoiceSmsService voiceSmsService;
	private CadreManagementService cadreManagementService;

	private EntitlementsHelper entitlementsHelper;	
	private String isAgeSelected;
	private String isCasteSelected;
	private String isFamilySelected;
	private String isNameSelected;
	private String isGenderSelected;
	
	private String startAge;
	private String endAge;
	private String casteIds;
	private String houseNo;
	private String name;
	private String gender;
	private String areaType;
	private List<VoiceSmsResponseDetailsVO> voiceSmsHistory ;
	
	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public List<VoiceSmsResponseDetailsVO> getVoiceSmsHistory() {
		return voiceSmsHistory;
	}

	public void setVoiceSmsHistory(List<VoiceSmsResponseDetailsVO> voiceSmsHistory) {
		this.voiceSmsHistory = voiceSmsHistory;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public String getIsAgeSelected() {
		return isAgeSelected;
	}

	public void setIsAgeSelected(String isAgeSelected) {
		this.isAgeSelected = isAgeSelected;
	}

	public String getIsCasteSelected() {
		return isCasteSelected;
	}

	public void setIsCasteSelected(String isCasteSelected) {
		this.isCasteSelected = isCasteSelected;
	}

	public String getIsFamilySelected() {
		return isFamilySelected;
	}

	public void setIsFamilySelected(String isFamilySelected) {
		this.isFamilySelected = isFamilySelected;
	}

	public String getIsNameSelected() {
		return isNameSelected;
	}

	public void setIsNameSelected(String isNameSelected) {
		this.isNameSelected = isNameSelected;
	}

	public String getIsGenderSelected() {
		return isGenderSelected;
	}

	public void setIsGenderSelected(String isGenderSelected) {
		this.isGenderSelected = isGenderSelected;
	}

	public String getStartAge() {
		return startAge;
	}

	public void setStartAge(String startAge) {
		this.startAge = startAge;
	}

	public String getEndAge() {
		return endAge;
	}

	public void setEndAge(String endAge) {
		this.endAge = endAge;
	}

	public String getCasteIds() {
		return casteIds;
	}

	public void setCasteIds(String casteIds) {
		this.casteIds = casteIds;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public IVoiceSmsService getVoiceSmsService() {
		return voiceSmsService;
	}

	public void setVoiceSmsService(IVoiceSmsService voiceSmsService) {
		this.voiceSmsService = voiceSmsService;
	}

	
	public int getVerifiedNumbersCount() {
		return verifiedNumbersCount;
	}

	public void setVerifiedNumbersCount(int verifiedNumbersCount) {
		this.verifiedNumbersCount = verifiedNumbersCount;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}
	
    //override
	
	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}


	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	
	public Long getAccessValue() {
		return accessValue;
	}


	public void setAccessValue(Long accessValue) {
		this.accessValue = accessValue;
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
	
	
	public List<SelectOptionVO> getSublevelsList() {
		return sublevelsList;
	}


	public void setSublevelsList(List<SelectOptionVO> sublevelsList) {
		this.sublevelsList = sublevelsList;
	}
	
	public InfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}


	public void setInfluencingPeopleService(
			InfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}
	
	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}


	public List<SelectOptionVO> getParliamentConstituencyList() {
		return parliamentConstituencyList;
	}

	public void setParliamentConstituencyList(
			List<SelectOptionVO> parliamentConstituencyList) {
		this.parliamentConstituencyList = parliamentConstituencyList;
	}

	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}
	
	public StaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(StaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}


	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}
	
	public List<SelectOptionVO> getSubRegions() {
		return subRegions;
	}


	public void setSubRegions(List<SelectOptionVO> subRegions) {
		this.subRegions = subRegions;
	}
	
	
	public List<SelectOptionVO> getBooths() {
		return booths;
	}


	public void setBooths(List<SelectOptionVO> booths) {
		this.booths = booths;
	}
	
	
	public List<SelectOptionVO> getHamletsOrWards() {
		return hamletsOrWards;
	}


	public void setHamletsOrWards(List<SelectOptionVO> hamletsOrWards) {
		this.hamletsOrWards = hamletsOrWards;
	}
	
	


	public List<SmsVO> getMobileNOs() {
		return mobileNOs;
	}


	public void setMobileNOs(List<SmsVO> mobileNOs) {
		this.mobileNOs = mobileNOs;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}


	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}


	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}


	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}


	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}


	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}
	
	public VoterHouseInfoVO getVoterHouseInfoVO() {
		return voterHouseInfoVO;
	}
	public void setVoterHouseInfoVO(VoterHouseInfoVO voterHouseInfoVO) {
		this.voterHouseInfoVO = voterHouseInfoVO;
	}
	

	public Long getPublicationDateId() {
		return publicationDateId;
	}


	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}


	public Long getConstituencyId() {
		return constituencyId;
	}


	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}


	public Long getLocationValue() {
		return locationValue;
	}


	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}


	public String getLocationType() {
		return locationType;
	}


	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	@SuppressWarnings("unused")
	public String execute()throws Exception
	{
		session = request.getSession();
		RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		 List<String> entitlements = null;
			if(registrationVO.getEntitlements() != null && registrationVO.getEntitlements().size()>0){
				entitlements = registrationVO.getEntitlements();
				
		if (registrationVO != null) 
		{/*
			if (!registrationVO.getIsAdmin().equals("true")){
				  return ERROR;
			}
		*/} 
		else{			
			if(registrationVO == null && !entitlements.contains(IConstants.VOTER_ANALYSIS)){
				return Action.INPUT;
			}
			if(!entitlements.contains(IConstants.VOTER_ANALYSIS)){
				return Action.ERROR;
			}
			/*if(registrationVO == null && 
					!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.VOTER_ANALYSIS))
				return Action.INPUT;
			if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.VOTER_ANALYSIS))
				return Action.ERROR;*/
		}
		
		accessType=registrationVO.getAccessType();
		accessValue=Long.valueOf(registrationVO.getAccessValue());
		
		Long userId = registrationVO.getRegistrationID();
		sublevelsList=influencingPeopleService.getInfluenceRange();
		//statesList=regionServiceDataImp.getUserStateList(accessType, accessValue);
		
		verifiedNumbersCount = voiceSmsService.getVerifiedNumbersCountOfUser(userId);
		
		districtList = new ArrayList<SelectOptionVO>();
		constituencyList = new ArrayList<SelectOptionVO>();
		statesList = new ArrayList<SelectOptionVO>();
		parliamentConstituencyList = new ArrayList<SelectOptionVO>();
		
		if("MLA".equals(accessType))
		{
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
			statesList.add(list.get(0));			
			districtList.add(list.get(1));			
			constituencyList.add(list.get(2));
			
		}else if("COUNTRY".equals(accessType))
		{
			statesList = cadreManagementService.findStatesByCountryID(accessValue.toString());
			statesList.add(0,new SelectOptionVO(0L, "Select State"));			
			
		}else if("STATE".equals(accessType)){
			
			String name = cadreManagementService.getStateName(accessValue);
			SelectOptionVO obj2 = new SelectOptionVO();
			obj2.setId(accessValue);
			obj2.setName(name);			
			statesList.add(obj2);
			districtList = staticDataService.getDistricts(accessValue);
			districtList.add(0,new SelectOptionVO(0l,"Select District"));	
			
		}else if("DISTRICT".equals(accessType)){
						
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
			statesList.add(list.get(0));			
			districtList.add(list.get(1));
			constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(accessValue);
			constituencyList.add(0, new SelectOptionVO(0l,"Select Constituency"));
			
		} else if("MP".equals(accessType)){
			
			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
			statesList = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
			constituencyList = constituencyInfoVO.getAssembyConstituencies();
			constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			if(parliamentConstituencyList == null)
			parliamentConstituencyList = new ArrayList<SelectOptionVO>();
			parliamentConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName())); 
			
		}
		
		 areaType = regionServiceDataImp.getConstituencyAreaType(Long.valueOf(accessValue));
		
		
		//districtList = regionServiceDataImp.getDistrictsByStateID(statesList.get(0).getId());
		/*districtList = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
		
		districtList.add(0, new SelectOptionVO(0L,"Select District"));
		
		Long electionYear = Long.valueOf(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = Long.valueOf(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userId,electionYear,electionTypeId);
		constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
		constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));*/

		voiceSmsHistory = voiceSmsService.getVoiceSmsHistoryForAuser(registrationVO.getRegistrationID(),0,0,true);
	}
		return Action.SUCCESS;
		
	}
	public String getSublevels(){
		String param=getTask();
		String nameReturned="";
		
		try{
			jObj=new JSONObject(param);
		}catch (Exception e) {
			e.printStackTrace();
		}
		String task=jObj.getString("task");
		Long locationId=jObj.getLong("locationId");
		
		if(task.equalsIgnoreCase("getDistricts")){
			districtList = staticDataService.getDistricts(locationId);
			districtList.add(0,new SelectOptionVO(0l,"Select District"));
			nameReturned="districts";
		}else if(jObj.getString("task").equals("getConstituencies")){
			String areaType = jObj.getString("areaType");
			
			if(areaType!=""){
				constituenciesList = regionServiceDataImp.getConstituenciesByAreaTypeInDistrict(locationId, areaType);
			}
			else{
				constituenciesList = regionServiceDataImp.getConstituenciesByDistrictID(Long.valueOf(jObj.getString("locationId")));
			}
			constituenciesList.add(0,new SelectOptionVO(0l,"Select Constituencies"));
			nameReturned="constituencies";
		}else if(jObj.getString("task").equalsIgnoreCase("subRegionsInConstituency"))
		{
			Long constituencyId = jObj.getLong("locationId");
			String areaType = jObj.getString("areaType");
			subRegions = getRegionServiceDataImp().getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, areaType);
			
			if(areaType.equalsIgnoreCase("URBAN")){
				subRegions.add(0,new SelectOptionVO(0l,"Select Muncipality"));
			}else{
				subRegions.add(0,new SelectOptionVO(0l,"Select Mandal"));
			}
			nameReturned="subRegionsOfConstituency";
		}else if(jObj.getString("task").equalsIgnoreCase("boothsInTehsilOrMunicipality"))
		{
			
			Long constituencyId = jObj.getLong("constId");
			booths = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(locationId, Long.valueOf(IConstants.PRESENT_ELECTION_YEAR), constituencyId);
			nameReturned="booths";
		} else if(jObj.getString("task").equalsIgnoreCase("hamletsOrWardsInRegion"))
		{
			if(locationId !=0){
				hamletsOrWards = getRegionServiceDataImp().getHamletsOrWards(locationId, IConstants.PRESENT_YEAR);
			}
			nameReturned="hamletsOrWards";
		
		}
		
		
		return nameReturned;
	}
	public String getMobileNumbers(){
		String param=getTask();
		try{
			jObj=new JSONObject(param);
			String ids=jObj.getString("ids");
			mobileNOs=influencingPeopleService.getMobileNumbersOfIds(ids);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
		
	public String ajaxHandler()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		try{
		if(user == null)
		 return ERROR;
		jObj=new JSONObject(getTask());
		}catch (Exception e) {
			e.printStackTrace();
		}
	 Long userId = user.getRegistrationID();
	 
	 	 
	 if(jObj.getString("task").equalsIgnoreCase("getVoterDetails"))
	 {
		 VoterHouseInfoVO houseInfoVO = new VoterHouseInfoVO();
		 houseInfoVO.setConstituencyId(jObj.getLong("constituencyId"));
		 houseInfoVO.setLocation(jObj.getString("locationType"));
		 houseInfoVO.setUserId(userId);
		 houseInfoVO.setStartIndex(jObj.getInt("startIndex"));
		 houseInfoVO.setMaxIndex(jObj.getInt("results"));
		 houseInfoVO.setPublicationId(jObj.getLong("publicationDateId"));
		 
		 Long locationValue = jObj.getLong("id");
		 
		 voterHouseInfoVO = votersAnalysisService.getVoterDetailsForSelectedLocation(houseInfoVO,locationValue);
	 }
	 
		
		return Action.SUCCESS;
	}

}
