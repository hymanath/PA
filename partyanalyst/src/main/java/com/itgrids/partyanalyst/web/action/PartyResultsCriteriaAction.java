
package com.itgrids.partyanalyst.web.action;


import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IConstituencySearchService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author Mohan 
 *
 */
public class PartyResultsCriteriaAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private List<SelectOptionVO> partyList;
	private IStaticDataService staticDataService;
	private EntitlementsHelper entitlementsHelper;
	private String task = null;
	JSONObject jObj = null;
	private List<SelectOptionVO> statesList;
	private List<SelectOptionVO> constsList;
	private IConstituencySearchService constituencySearchService;
	
	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}
	
	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}
		
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}	

	public List<SelectOptionVO> getConstsList() {
		return constsList;
	}

	public void setConstsList(List<SelectOptionVO> constsList) {
		this.constsList = constsList;
	}	

	public IConstituencySearchService getConstituencySearchService() {
		return constituencySearchService;
	}

	public void setConstituencySearchService(
			IConstituencySearchService constituencySearchService) {
		this.constituencySearchService = constituencySearchService;
	}

	public String execute() {	
		
		session = request.getSession();
		
		if(session.getAttribute(IConstants.USER) == null || ((RegistrationVO)session.getAttribute(IConstants.USER)).getRegistrationID() == null)
			return "showMessage";
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(regVO == null && !entitlements.contains(IConstants.PARTY_RESULTS_REPORT)){
				return INPUT;
			}
			if(!entitlements.contains(IConstants.PARTY_RESULTS_REPORT)){
				return ERROR;
			}
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_RESULTS_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_RESULTS_REPORT))
			return ERROR;*/
		partyList = staticDataService.getStaticParties();
		}
		return SUCCESS;
		
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}	

	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public String getStatesForElection()
	{
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		Long electionType = jObj.getLong("electionType");
		
		statesList = staticDataService.getParticipatedStatesForAnElectionType(electionType);		
		Collections.sort(statesList);	
		return SUCCESS;
	}
	

	public String getConstituenciesByElectionScope()
	{
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		Long electionType = jObj.getLong("electionType");
		Long stateId = 	jObj.getLong("stateId");
		constsList = constituencySearchService.getConstituencyNamesByElectionScope(1l,stateId, electionType);		
			
		return SUCCESS;
	}

}
