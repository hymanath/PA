package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.DataValidationVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IDataValidationService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VoterDataToolsAction extends ActionSupport implements ServletRequestAware{

  private HttpServletRequest request;
  private HttpSession session;
  private String task;
  JSONObject jObj;
  private IDataValidationService dataValidationService;
  private List<DataValidationVO> dataValidationVOList,resultList;
  private List<SelectOptionVO> constituencyList,userAccessConstituencyList;
  private IVotersAnalysisService votersAnalysisService;
  private ICrossVotingEstimationService crossVotingEstimationService;
  
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

  public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
  public IDataValidationService getDataValidationService() {
	return dataValidationService;
  }
  public void setDataValidationService(
			IDataValidationService dataValidationService) {
		this.dataValidationService = dataValidationService;
	}
  public List<DataValidationVO> getDataValidationVOList() {
		return dataValidationVOList;
	}
  public void setDataValidationVOList(List<DataValidationVO> dataValidationVOList) {
		this.dataValidationVOList = dataValidationVOList;
	}
  public List<DataValidationVO> getResultList() {
		return resultList;
	}
  public void setResultList(List<DataValidationVO> resultList) {
		this.resultList = resultList;
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
	public String execute()
  {
	  HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return INPUT;
		
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.VOTER_SEARCH_AND_EDIT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.VOTER_SEARCH_AND_EDIT))
			return ERROR;*/
		
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
		return SUCCESS;
  }
  
  public String ajaxHandler()
	{
		String param;
		param = getTask();
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		Long userId = null;
		if(user != null && user.getRegistrationID() != null)
			if(user.getParentUserId()!=null)
				userId = user.getMainAccountId();
			else
				userId = user.getRegistrationID();
		else 
		  return "error";
		
		try{
			jObj = new JSONObject(param);	
		}catch (Exception e){
			e.printStackTrace();
		}	
		if(jObj.getString("task").equalsIgnoreCase("getSubLevelInfo"))
			resultList = dataValidationService.getHamletsAssignedValidation(jObj.getLong("constituencyId"),jObj.getLong("publicationDateId"),userId);
		
		
		return SUCCESS;
		
	}
  
  public String getCasteAssignedVotersInfo()
	{
		try{
		Long userId = null;
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute(IConstants.USER);
		if(user != null && user.getRegistrationID() != null){
			if(user.getParentUserId() != null)
				userId = user.getMainAccountId();
			else
			 userId = user.getRegistrationID();
		}
		else
		 return ERROR;
		jObj = new JSONObject(getTask());
		if(jObj.getString("task").equalsIgnoreCase("getVotersCasteDetails"))
		 dataValidationVOList = dataValidationService.getCasteAssignedAndNotAssignedVotersCount(jObj.getLong("constituencyId"),jObj.getLong("publicationId"),jObj.getString("type"),userId);
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Exception Occured in getVoterCasteInfo() method, Exception - "+e);
		}
	  return Action.SUCCESS;
	}

	
}
