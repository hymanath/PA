package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
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
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IMinisterAnalysisService;
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
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.MINISTERS_AND_SPECIAL_CANDIDATES_ANALYSIS))
			return IConstants.NOT_LOGGED_IN;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.MINISTERS_AND_SPECIAL_CANDIDATES_ANALYSIS))
			return ERROR;
			statesList = staticDataService.getParticipatedStatesForAnElectionType(2l);
			
			return SUCCESS;
	}
	
	public String getMinisterData()
	{
		try 
		  {
			jObj = new JSONObject(getTask());
			results = electionLiveResultsAnalysisService.getCurrentMinistersDetailsForCurrentAndPrevEle(1l, 1l, "", jObj.getLong("electionId"),jObj.getString("reqtype"));
			
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
