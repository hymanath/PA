package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.service.IConstituencyInfoService;
import com.itgrids.partyanalyst.utils.IConstants;
public class ConstituencyInfoAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	private IConstituencyInfoService constituencyInfoService;
	private List<VotersDetailsVO> votersDetailsVO;
	private IVotersAnalysisService votersAnalysisService;
	private ICrossVotingEstimationService crossVotingEstimationService;
	
	
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


	public List<VotersDetailsVO> getVotersDetailsVO() {
		return votersDetailsVO;
	}


	public void setVotersDetailsVO(List<VotersDetailsVO> votersDetailsVO) {
		this.votersDetailsVO = votersDetailsVO;
	}


	public IConstituencyInfoService getConstituencyInfoService() {
		return constituencyInfoService;
	}


	public void setConstituencyInfoService(
			IConstituencyInfoService constituencyInfoService) {
		this.constituencyInfoService = constituencyInfoService;
	}


	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}


	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}




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


	public void setServletRequest(HttpServletRequest arg0) {
	this.request = arg0;
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
	
	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
	/*	if(user == null)
		return INPUT;
	
	if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.VOTER_ANALYSIS))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.VOTER_ANALYSIS))
			return ERROR;*/
		    constituencyList = user.getUserAccessVoterConstituencies();
		    if(constituencyList == null || constituencyList.isEmpty()){
			Long userID = user.getRegistrationID();
			Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
			Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
			userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
			constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
			constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
			user.setUserAccessVoterConstituencies(constituencyList);
		}
		return SUCCESS;
		
		
	}
	
	public String getConstituencyBasicInfo()
	{
		try{
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			if(user == null)
			return ERROR;
			Long userID = user.getRegistrationID();
		jObj = new JSONObject(getTask());
		votersDetailsVO = constituencyInfoService.getConstituencyBasicInfoById(jObj.getLong("constituencyId"),jObj.getLong("publicationId"),userID);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}

}
