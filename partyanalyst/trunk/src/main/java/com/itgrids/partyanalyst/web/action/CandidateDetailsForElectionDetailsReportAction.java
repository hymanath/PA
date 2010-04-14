package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsReportVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.StaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CandidateDetailsForElectionDetailsReportAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CandidateDetailsForElectionDetailsReportAction.class);
	private HttpServletRequest request;
	private String electionId;
	private String stateID;
	private String electionType;
	private String stateName;
	private String year;
	private String task = null;
	JSONObject jObj = null;
	private CandidateDetailsVO candidateDetailsVO;
	private CandidateDetailsVO statesListObj;
	private IStaticDataService staticDataService; 
	private List<SelectOptionVO> partiesList;
	private List<SelectOptionVO> districtsList;	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getElectionId() {
		return electionId;
	}

	public void setElectionId(String electionId) {
		this.electionId = electionId;
	}

	public String getStateID() {
		return stateID;
	}

	public void setStateID(String stateID) {
		this.stateID = stateID;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public static Logger getLog() {
		return log;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTask() {
		return task;
	}	
	
	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public void setCandidateDetailsVO(CandidateDetailsVO candidateDetailsVO) {
		this.candidateDetailsVO = candidateDetailsVO;
	}

	public CandidateDetailsVO getCandidateDetailsVO() {
		return candidateDetailsVO;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}

	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}

	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}
		
	public CandidateDetailsVO getStatesListObj() {
		return statesListObj;
	}

	public void setStatesListObj(CandidateDetailsVO statesListObj) {
		this.statesListObj = statesListObj;
	}

	public String execute () throws Exception 
	{
		statesListObj = new CandidateDetailsVO();		
		statesListObj = staticDataService.getAllStatesInCountry();
		districtsList = new ArrayList<SelectOptionVO>();
		districtsList = staticDataService.getDistricts(new Long(stateID));
		districtsList.add(0, new SelectOptionVO(0l,"Select A District"));
		partiesList = new ArrayList<SelectOptionVO>();
		if(electionType.equals(IConstants.ZPTC) || electionType.equals(IConstants.MPTC))
		{
			try{
				partiesList = staticDataService.getAllPartiesForAnElectionYear(year, electionType);	
				partiesList.add(0, new SelectOptionVO(0l,"Select A Party"));
			}catch(Exception e){
				partiesList = null;
				log.debug("Error occured in retriving the data in CandidateDetailsForElectionDetailsReportAction class");
			}
		} else if (electionType.equals(IConstants.ASSEMBLY_ELECTION_TYPE) || electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
		{
			try{
				partiesList = staticDataService.getStaticParties();
				partiesList.add(0, new SelectOptionVO(0l,"Select A Party"));
			}catch(Exception e){
				partiesList = null;
				log.debug("Error occured in retriving the data in CandidateDetailsForElectionDetailsReportAction class");
			}
		}
		
		return Action.SUCCESS;
		
	}

	public String ajaxCallHandler()
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getAllCandidates"))
		{
			String resultsCategory =jObj.getString("resultsCategory");
			String resultsCategoryVal = null, electionLevelVal = null;
			String electionLevel = jObj.getString("electionLevel");
			Long locationId = new Long(jObj.getString("locationId"));
			Long partyId = new Long(jObj.getString("partyId"));
			String year = jObj.getString("year");
			String electionType = jObj.getString("electionType");
			String stateID = jObj.getString("stateID");
			candidateDetailsVO = new CandidateDetailsVO();
			if(resultsCategory.equals("allCandidates"))
			{
				resultsCategoryVal = IConstants.ALL_CANDIDATES; 
			}
			if(resultsCategory.equals("wonCandidatesOnly"))
			{
				resultsCategoryVal = IConstants.WINNER_CANDIDATES; 
			}
			if(electionLevel.equals("countrywiseParliament"))
			{
				electionLevelVal = IConstants.COUNTRY_LEVEL; 
			}
			if(electionLevel.equals("statewiseParliament"))
			{
				electionLevelVal = IConstants.STATE_LEVEL; 
			}
			if(electionLevel.equals("statewiseAssembly"))
			{
				electionLevelVal = IConstants.STATE_LEVEL; 
			}
			if(electionLevel.equals("districtwiseAssembly"))
			{
				electionLevelVal = IConstants.DISTRICT_LEVEL; 
			}
			if(electionLevel.equals("stateWiseMptc"))
			{
				electionLevelVal = electionLevel; 
			}
			if(electionLevel.equals("stateWiseZptc"))
			{
				electionLevelVal = electionLevel; 
			}
			log.debug(electionType);
			log.debug(year);
			log.debug(resultsCategoryVal);
			log.debug(electionLevelVal);
			log.debug(locationId);
			log.debug(partyId);
			log.debug(stateID);
			try{
				candidateDetailsVO = staticDataService.getCandidatesPartyInfoForAnElectionType(electionType,year,resultsCategoryVal,electionLevelVal,locationId,partyId,new Long(stateID));
			}catch(Exception e){
				log.debug("Error occured in retriving the data in CandidateDetailsForElectionDetailsReportAction class");
				candidateDetailsVO = null;
			}
			}	
		return Action.SUCCESS;
	}

}
