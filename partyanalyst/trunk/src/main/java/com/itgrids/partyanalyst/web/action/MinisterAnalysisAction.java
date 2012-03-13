package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PositionManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IElectionLiveResultsAnalysisService;
import com.itgrids.partyanalyst.service.IMinisterAnalysisService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MinisterAnalysisAction extends ActionSupport implements ServletRequestAware{
	private List<SelectOptionVO> statesList;
	private IStaticDataService staticDataService;
	private IMinisterAnalysisService ministerAnalysisService;
	private HttpServletRequest request;
	private JSONObject jObj;
	private String task;
	private List<PositionManagementVO> results;
	private IElectionLiveResultsAnalysisService electionLiveResultsAnalysisService;
	private EntitlementsHelper entitlementsHelper;
	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	

	public IMinisterAnalysisService getMinisterAnalysisService() {
		return ministerAnalysisService;
	}

	public void setMinisterAnalysisService(
			IMinisterAnalysisService ministerAnalysisService) {
		this.ministerAnalysisService = ministerAnalysisService;
	}
    
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
    
	public List<PositionManagementVO> getResults() {
		return results;
	}

	public void setResults(List<PositionManagementVO> results) {
		this.results = results;
	}
    
	public IElectionLiveResultsAnalysisService getElectionLiveResultsAnalysisService() {
		return electionLiveResultsAnalysisService;
	}

	public void setElectionLiveResultsAnalysisService(
			IElectionLiveResultsAnalysisService electionLiveResultsAnalysisService) {
		this.electionLiveResultsAnalysisService = electionLiveResultsAnalysisService;
	}
	

	public String execute(){
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(IConstants.USER) == null)
			return IConstants.NOT_LOGGED_IN;
		
			statesList = staticDataService.getParticipatedStatesForAnElectionType(2l);
			
			return SUCCESS;
	}
	
	public String getMinisterData()
	{
		try 
		  {
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").trim().equalsIgnoreCase("getMinsKeyCandAnalysisDetails"))
			{
			  results = electionLiveResultsAnalysisService.getCurrentMinistersDetailsForCurrentAndPrevEle(1l, 1l, "", jObj.getLong("electionId"),jObj.getString("reqtype"));
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getDistrictWiseAnalysisDetails"))
			{
				String partyIds = jObj.getString("partyIds");
				String[] partyArray = partyIds.split(",");
				List<Long> partyIdsList = new ArrayList<Long>();
				 for(String partyId:partyArray)
				 {
					 partyIdsList.add(new Long(partyId));
				 }
			  results = electionLiveResultsAnalysisService.getDistrictWisePartyPerfDetailsNew(jObj.getLong("electionId"),jObj.getLong("stateId"),partyIdsList);	
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getDistrictWiseAnalysisDetailsForGraph"))
			{
			  results = electionLiveResultsAnalysisService.getWinCountForGraphs(jObj.getLong("electionId"),jObj.getLong("stateId"),jObj.getLong("districtId"));
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getPartiesForPartialEle"))
			{
			  results = electionLiveResultsAnalysisService.getAllPartiesForPartialElection(jObj.getLong("electionId"),jObj.getLong("stateId"));
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getpartyPerfoDistWiseForPartialEle"))
			{
			   String partyIds = jObj.getString("partyIds");
			  String[] partiesArray = partyIds.split(",");
			  List<Long> partiesList = new ArrayList<Long>();
			  for(String party:partiesArray)
			  {
				  partiesList.add(new Long(party));
			  }
			  results = electionLiveResultsAnalysisService.getAllPartiesPerfoDistWiseForPartialEle(jObj.getLong("electionId"),partiesList);
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getAllPartiesForResults"))
			{
			  results = electionLiveResultsAnalysisService.getAllPartiesForAnElec(jObj.getLong("electionId"),jObj.getLong("stateId"));
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getPartiesPerfResultsWise"))
			{

				   String partyIds = jObj.getString("partyIds");
				  String[] partiesArray = partyIds.split(",");
				  List<Long> partiesList = new ArrayList<Long>();
				  for(String party:partiesArray)
				  {
					  partiesList.add(new Long(party));
				  }
				  results = electionLiveResultsAnalysisService.getAllPartyCountsDistrictWise(jObj.getLong("electionId"),partiesList,jObj.getBoolean("includeAlliances"),jObj.getString("type"));
			}
		  }
		  catch (ParseException e) {
			e.printStackTrace();
		  }
		
		return Action.SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	
}
