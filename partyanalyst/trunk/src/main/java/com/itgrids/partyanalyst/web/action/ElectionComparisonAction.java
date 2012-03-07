package com.itgrids.partyanalyst.web.action;

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
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ElectionComparisonAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SelectOptionVO> partyList;
	private List<SelectOptionVO> electionScopes;
	private List<SelectOptionVO> yearsList;
	private HttpServletRequest request;
	private IStaticDataService staticDataService;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private static final Logger log = Logger.getLogger(ElectionComparisonAction.class);
	private EntitlementsHelper entitlementsHelper;
	
	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}
	
	public List<SelectOptionVO> getYearsList() {
		return yearsList;
	}

	public void setYearsList(List<SelectOptionVO> yearsList) {
		this.yearsList = yearsList;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

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

	public List<SelectOptionVO> getElectionScopes() {
		return electionScopes;
	}

	public void setElectionScopes(List<SelectOptionVO> electionScopes) {
		this.electionScopes = electionScopes;
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

	public String execute() throws Exception {
		
		session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null)
			return "showMessage";
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ELECTION_COMPARISION_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ELECTION_COMPARISION_REPORT))
			return ERROR;
		
		//partyList = staticDataService.getStaticParties();
		return Action.SUCCESS;
		
	}
	
	public String getElectionScopesForEC(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(jObj.getString("task").equals("getElectionScopes")){
				electionScopes = staticDataService.getElectionScopesByElectionType(jObj.getLong("electionTypeId"));
				Collections.sort(electionScopes);
				log.debug("getElectionScopes......"+electionScopes.size());
			}
		}
		return SUCCESS;
	}
	
	public String getElectionYearsAndElectionIdsForEC(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(jObj.getString("task").equals("getElectionYears")){
				yearsList = staticDataService.getElectionIdsAndYearsByElectionScope(jObj.getLong("electionScopeId"),jObj.getLong("partyId"));
				log.debug("getElectionScopes......"+yearsList.size());
			}
		}
		return SUCCESS;
	}
	
	public String getPartiesInStateForEC()
	{
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			Long scopeID = jObj.getLong("stateId");
			if(jObj.getString("task").equals("getPartiesInState")){
				
				partyList = staticDataService.getStaticPartiesListFromElectionScope(scopeID);
				//partyList = staticDataService.getStaticPartiesListForAState(stateId);
				Collections.sort(partyList);
			}
		}
		
		return SUCCESS;
	}

}
