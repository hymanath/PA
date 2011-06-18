package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IElectionAnalyzeService;
import com.itgrids.partyanalyst.service.IStaticDataService;
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
	private HttpSession session;
	private String electionId;
	private String stateID;
	private String electionType;
	private String stateName;
	private String year;
	private String task = null;
	JSONObject jObj = null;
	private CandidateDetailsVO candidateDetailsVO;
	private CandidateDetailsVO statesListObj;	
	private List<SelectOptionVO> partiesList;
	private List<SelectOptionVO> districtsList;	
	
	private IStaticDataService staticDataService; 
	private IElectionAnalyzeService electionAnalyzeService;
	private EntitlementsHelper entitlementsHelper;
	private Boolean reasonPostingEntitlement;
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Boolean getReasonPostingEntitlement() {
		return reasonPostingEntitlement;
	}

	public void setReasonPostingEntitlement(Boolean reasonPostingEntitlement) {
		this.reasonPostingEntitlement = reasonPostingEntitlement;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public IElectionAnalyzeService getElectionAnalyzeService() {
		return electionAnalyzeService;
	}

	public void setElectionAnalyzeService(
			IElectionAnalyzeService electionAnalyzeService) {
		this.electionAnalyzeService = electionAnalyzeService;
	}

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
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		
		if(entitlementsHelper.checkForEntitlementToViewReport(regVO, IConstants.REASONS_POSTING))
			reasonPostingEntitlement = true;
		else
			reasonPostingEntitlement = false;
		Long stateId = new Long(stateID);
		statesListObj = staticDataService.getAllStatesInCountry();
		districtsList = staticDataService.getDistricts(stateId);
		districtsList.add(0, new SelectOptionVO(0l,"Select A District"));
		if (IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType) || 
				IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType))
		{
			try{
				partiesList = staticDataService.getStaticPartiesForCandidateDeatailsReport(stateId);
				partiesList.add(0, new SelectOptionVO(0l,"Select A Party"));
			}catch(Exception e){
				partiesList = null;
				log.debug("Error occured in retriving the data in CandidateDetailsForElectionDetailsReportAction class");
			}
		}
		else
		{
			try{
				partiesList = staticDataService.getAllPartiesForAnElectionYear(year, electionType);	
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
			if(resultsCategory.equals("lostCandidatesOnly"))
			{
				resultsCategoryVal = IConstants.LOST_CANDIDATES; 
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
			if(electionLevel.equals("districtWiseZptc"))
			{
				electionLevelVal = electionLevel; 
			}
			if(electionLevel.equals("districtWiseMptc"))
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
				
			/*	if(electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE)){
					candidateDetailsVO = staticDataService.getCandidatesPartyInfoForAnElectionType(electionType,year,resultsCategoryVal,electionLevelVal,locationId,partyId,new Long(stateID),0,0,null,null);					
				}else{*/
					int startIndex = new Long(request.getParameter("startIndex").toString()).intValue();
					
					int maxResult = new Long(request.getParameter("results").toString()).intValue();
					String order = request.getParameter("dir").toString();
					String columnName = request.getParameter("sort").toString();					
					candidateDetailsVO = electionAnalyzeService.getCandidatesPartyInfoForAnElectionType(electionType,year,resultsCategoryVal,electionLevelVal,locationId,partyId,new Long(stateID),startIndex,maxResult,order,columnName);
			//	}	
			}catch(Exception e){
				e.printStackTrace();
				log.debug("Error occured in retriving the data in CandidateDetailsForElectionDetailsReportAction class");
				candidateDetailsVO = null;
			}
			}	
		return Action.SUCCESS;
	}

}
