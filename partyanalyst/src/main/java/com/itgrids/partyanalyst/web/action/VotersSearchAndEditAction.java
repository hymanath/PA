package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VotersSearchAndEditAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	
	private HttpSession session;
	
	private String task;
	
	JSONObject jObj;
	
	private ICrossVotingEstimationService crossVotingEstimationService;
	
	private IVotersAnalysisService votersAnalysisService;
	
	private EntitlementsHelper entitlementsHelper;
	private String isAdmin ;
	private Boolean isVoterDataTools = false;
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
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


	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	//@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0; 
	}
	
	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Boolean getIsVoterDataTools() {
		return isVoterDataTools;
	}

	public void setIsVoterDataTools(Boolean isVoterDataTools) {
		this.isVoterDataTools = isVoterDataTools;
	}

	public String execute() throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.VOTER_SEARCH_AND_EDIT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.VOTER_SEARCH_AND_EDIT))
			return ERROR;
		
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE))
				isAdmin = "true";
		
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute("USER"), IConstants.VOTER_DATA_TOOLS))
		 isVoterDataTools = true;*/
		List<String> entitlements = null;
	    if(user.getEntitlements() != null && user.getEntitlements().size()>0){
	      entitlements = user.getEntitlements();
	      if(user == null && !entitlements.contains(IConstants.VOTER_SEARCH_AND_EDIT)){
	    	  return INPUT;
	      }
	      if(!entitlements.contains(IConstants.VOTER_SEARCH_AND_EDIT))
	    	  return ERROR;
	      if(entitlements.contains( IConstants.ADMIN_PAGE))
	    	  isAdmin = "true";
	      if(entitlements.contains(IConstants.VOTER_DATA_TOOLS))
	    	  isVoterDataTools = true;
		
	    constituencyList = user.getUserAccessVoterConstituencies();
		if(constituencyList == null || constituencyList.isEmpty()){
			Long userID = user.getRegistrationID();
			Long electionYear = Long.valueOf(IConstants.PRESENT_ELECTION_YEAR);
			Long electionTypeId = Long.valueOf(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
			userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
			constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
			constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
			user.setUserAccessVoterConstituencies(constituencyList);
		}
	  }
		return SUCCESS;
		
	}
	
	public String updateSelectedVoters()
	{
		return Action.SUCCESS;		
	}

}
