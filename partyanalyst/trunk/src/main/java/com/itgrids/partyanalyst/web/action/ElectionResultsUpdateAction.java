package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IElectionResultsUpdationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.ElectionResultsUpdationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ElectionResultsUpdateAction  extends ActionSupport implements ServletRequestAware{
	private String task = null;
	JSONObject jObj = null;
	private HttpServletRequest request;
	private IElectionResultsUpdationService electionResultsUpdationService;
	private List<PartyElectionResultsVO> partyElectionResultsVOList;
	private List<SelectOptionVO> selectOptionsList;
	private IStaticDataService staticDataService;
	private EntitlementsHelper entitlementsHelper;
    private static final Logger log = Logger.getLogger(ElectionResultsUpdateAction.class);
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	public IElectionResultsUpdationService getElectionResultsUpdationService() {
		return electionResultsUpdationService;
	}
	public void setElectionResultsUpdationService(
			IElectionResultsUpdationService electionResultsUpdationService) {
		this.electionResultsUpdationService = electionResultsUpdationService;
	}
	
	public List<PartyElectionResultsVO> getPartyElectionResultsVOList() {
		return partyElectionResultsVOList;
	}
	public void setPartyElectionResultsVOList(
			List<PartyElectionResultsVO> partyElectionResultsVOList) {
		this.partyElectionResultsVOList = partyElectionResultsVOList;
	}	
	
	public List<SelectOptionVO> getSelectOptionsList() {
		return selectOptionsList;
	}
	public void setSelectOptionsList(List<SelectOptionVO> selectOptionsList) {
		this.selectOptionsList = selectOptionsList;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	public String execute()
	{
		HttpSession session = request.getSession();
	    RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
	
	if(session.getAttribute(IConstants.USER) == null && 
			!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.LIVE_RESULTS_ANALYSIS_ENTITLEMENT))
		return IConstants.NOT_LOGGED_IN;
	if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.LIVE_RESULTS_ANALYSIS_ENTITLEMENT))
		return ERROR;
	
	return SUCCESS;
	}
	public String getUpdateElectionResults()
	{
		if(log.isDebugEnabled())
			log.debug("Enter into getUpdateElectionResults method of ElectionResultsUpdateAction ");
		try
		{
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").trim().equalsIgnoreCase("getCandidateResults"))
			{
				partyElectionResultsVOList = electionResultsUpdationService.getCandidateResults(jObj.getString("electionType").trim(),jObj.getLong("constituencyId"), jObj.getLong("stateId"), jObj.getString("electionYear"));
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("updateCandidateResults"))
			{
				partyElectionResultsVOList = electionResultsUpdationService.updateCandidateResults(jObj.getLong("candidateId"), jObj.getLong("constiElecId"),jObj.getString("status").trim());
			}
		}
		catch(Exception e)
		{
			log.error("Exception rised in getUpdateElectionResults method of ElectionResultsUpdateAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getConstituenciesOrYears()
	{
		if(log.isDebugEnabled())
			log.debug("Enter into getConstituenciesOrYears method of ElectionResultsUpdateAction ");
		try
		{
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").trim().equalsIgnoreCase("getYears"))
			{
				selectOptionsList = electionResultsUpdationService.getElectionYears( jObj.getLong("stateId"), jObj.getString("electionType").trim());
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getConstisForAState"))
			{
				selectOptionsList = staticDataService.getLatestConstituenciesByStateId(jObj.getLong("stateId"));
			}
		}
		catch(Exception e)
		{
			log.error("Exception rised in getConstituenciesOrYears method of ElectionResultsUpdateAction ",e);
		}
		return Action.SUCCESS;
	}
}
