package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class StaticDataAction  extends ActionSupport implements ServletRequestAware{

	private static final Logger log = Logger.getLogger(StaticDataAction.class);
	private HttpServletRequest request;
	private IRegionServiceData regionServiceDataImp;

	private IStaticDataService staticDataService;
	private List<SelectOptionVO> states;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> partyList;
	private List<SelectOptionVO> commonList;
	private EntitlementsHelper entitlementsHelper;
	private List<SelectOptionVO> userAccessConstituencyList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private List<Long> constituencyIds;
	private List<SelectOptionVO> returnList;
	JSONObject jObj;
	private String task;
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}
	
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp){
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getStates(){
		return states;
	}
	public void setStates(List<SelectOptionVO> states){
		this.states = states;
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

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}

	public List<SelectOptionVO> getCommonList() {
		return commonList;
	}

	public void setCommonList(List<SelectOptionVO> commonList) {
		this.commonList = commonList;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
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

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	
	public List<Long> getConstituencyIds() {
		return constituencyIds;
	}

	public void setConstituencyIds(List<Long> constituencyIds) {
		this.constituencyIds = constituencyIds;
	}

	
	public List<SelectOptionVO> getreturnList() {
		return returnList;
	}

	public void setreturnList(List<SelectOptionVO> returnList) {
		this.returnList = returnList;
	}

	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getStatesList() throws Exception{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return ERROR;
		Long userID = user.getRegistrationID();
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
		if(userAccessConstituencyList != null && userAccessConstituencyList.size() > 0)
			constituencyIds = new ArrayList<Long>();
		{
			for (SelectOptionVO constituencyList : userAccessConstituencyList) {
				constituencyIds.add(constituencyList.getId());
			}
		}
		
		//states = regionServiceDataImp.getStatesByCountryFromBooth(1L);
		states = staticDataService.getStatesByConstituency(constituencyIds);
		
		
		Collections.sort(states);
		states.add(0, new SelectOptionVO(0L,"Select State"));
		
		partyList = staticDataService.getStaticParties();
		Collections.sort(partyList);
		partyList.add(0, new SelectOptionVO(0L,"Select Party"));
		return SUCCESS;
	}

	public String getList() throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("StaticDataAction.java getList() Started123....");
		String task = request.getParameter("task");
		JSONObject jsonObject = new JSONObject(task);
				
		String type = jsonObject.getString("type");
		String value = jsonObject.getString("value");
		if(log.isDebugEnabled()){
			log.debug("task:"+task);
			log.debug("type:"+type);
			log.debug("value:"+value);
		}
		if("STATE".equals(type)){
			commonList = regionServiceDataImp.getDistrictsByStateIDFromBooth(new Long(value));
		} else if("DISTRICT".equals(type)){
			commonList = regionServiceDataImp.getConstituenciesByDistrictID(new Long(value));
		} else if("CONSTITUENCY".equals(type)){
			//commonList = regionServiceDataImp.getMandalsByConstituencyID(new Long(value));
			commonList = regionServiceDataImp.getMandalsAndMuncipalitiesByConstituencyID(new Long(value));
		}
		if(log.isDebugEnabled())
			log.debug("commonList.size():::"+commonList.size());
		return SUCCESS;
	}
	
	public String getDistrictsAndConstituencysList1()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return ERROR;
		Long userID = user.getRegistrationID();
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
		if(userAccessConstituencyList != null && userAccessConstituencyList.size() > 0)
			constituencyIds = new ArrayList<Long>();
		{
			for (SelectOptionVO constituencyList : userAccessConstituencyList) {
				constituencyIds.add(constituencyList.getId());
			}
		}
		if(jObj.getString("task").equalsIgnoreCase("getDistricts"))
		{
			Long stateId = jObj.getLong("stateId");
			returnList = staticDataService.getDistrictsByConstituency(constituencyIds,stateId);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getConstituencys"))
		{
			Long districtId = jObj.getLong("districtId");
			returnList = staticDataService.getConstituencyForSelDistrict(constituencyIds,districtId);
		}
		
		return SUCCESS;
	}
	
	

}
