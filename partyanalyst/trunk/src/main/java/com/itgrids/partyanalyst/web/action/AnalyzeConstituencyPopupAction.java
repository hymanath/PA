package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICommentsDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AnalyzeConstituencyPopupAction extends ActionSupport implements ServletRequestAware
{
	private HttpServletRequest request;
	private HttpSession session;
	private static final Logger log = Logger.getLogger(ProblemPostControlAction.class);
	
	JSONObject jObj = null;
	private String task = null;
	private String constituencyId;
	private String redirectLoc;
	private List<SelectOptionVO> electionYears;
	private ICommentsDataService commentsDataService;
	private List<CandidateVO> candidateVO;
	private String parliamentConstiId;
	private String parliamentConstiName;
	private String constituencyName;
	private String userId;
	private String taskType;
	private List<CandidateCommentsVO> candidateComments;
	
	
	public List<CandidateCommentsVO> getCandidateComments() {
		return candidateComments;
	}

	public void setCandidateComments(List<CandidateCommentsVO> candidateComments) {
		this.candidateComments = candidateComments;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String userStatus = null;
	
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getParliamentConstiId() {
		return parliamentConstiId;
	}

	public void setParliamentConstiId(String parliamentConstiId) {
		this.parliamentConstiId = parliamentConstiId;
	}

	public List<CandidateVO> getCandidateVO() {
		return candidateVO;
	}

	public void setCandidateVO(List<CandidateVO> candidateVO) {
		this.candidateVO = candidateVO;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public ICommentsDataService getCommentsDataService() {
		return commentsDataService;
	}

	public void setCommentsDataService(ICommentsDataService commentsDataService) {
		this.commentsDataService = commentsDataService;
	}

	public java.util.List<SelectOptionVO> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(java.util.List<SelectOptionVO> electionYears) {
		this.electionYears = electionYears;
	}

	public String getRedirectLoc() {
		return redirectLoc;
	}

	public void setRedirectLoc(String redirectLoc) {
		this.redirectLoc = redirectLoc;
	}

	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}	

	public String getParliamentConstiName() {
		return parliamentConstiName;
	}

	public void setParliamentConstiName(String parliamentConstiName) {
		this.parliamentConstiName = parliamentConstiName;
	}

	public String execute()
	{	
		/*session = request.getSession();
		Object regVO = session.getAttribute(IConstants.USER);
		
		if(regVO == null){
			log.error(" No User Log In .....");
			userStatus = IConstants.PROBLEM_MANAGEMENT_LOGIN;
			return IConstants.NOT_LOGGED_IN;
		}
		
		else if(regVO != null){
			
			RegistrationVO userVO = (RegistrationVO)regVO;
			if(userVO.getUserStatus().equals(IConstants.PARTY_ANALYST_USER)){
				return IConstants.PARTY_ANALYST_USER;
			}else if(userVO.getUserStatus().equals(IConstants.FREE_USER)){
				//task = "pm_redirect";
				electionYears = commentsDataService.getElectionYearsForConstituency(Long.parseLong(constituencyId));
				return getRedirectPageDetails();
			}
				
		}*/
		
		electionYears = commentsDataService.getElectionYearsForConstituency(Long.parseLong(constituencyId), false);
		return Action.SUCCESS;
	}
	
	public String getCandidateResults()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Long constiElectionId = Long.parseLong(jObj.getString("constElecId"));
		
		candidateVO = commentsDataService.getCandidateResultsForConstiElectionId(constiElectionId);
		
		return Action.SUCCESS;
	}
	
	public String getElectionYearsForConstituency()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Long constituencyId = Long.parseLong(jObj.getString("constituencyId"));
		Boolean onlyAssets = jObj.getBoolean("onlyAssets");
		electionYears = commentsDataService.getElectionYearsForConstituency(constituencyId, onlyAssets);
		
		return Action.SUCCESS;
	}
	
	public String getCommentsResults()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Long constiElectionId = Long.parseLong(jObj.getString("constElecId"));
		
		candidateComments = commentsDataService.getAnalyzedResonsWithRatingsForConstituencyInAnElection(false,constiElectionId);
		
		return Action.SUCCESS;
	}
	public String getRedirectPageDetails(){
		
		if(redirectLoc != null){
			if(redirectLoc.equalsIgnoreCase(IConstants.STATE)){
				return "statePageRedirect";
			}else if(redirectLoc.equalsIgnoreCase(IConstants.DISTRICT)){
				return "districtPageRedirect";
			}else if(redirectLoc.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				return "constituencyPageRedirect";
			}else if(redirectLoc.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION)){
				return "localElectionBodyPageRedirect";
			}else if(redirectLoc.equalsIgnoreCase("CANDIDATE_PROFILE")){
				return "candidatePageRedirect";
			}
		}
		
	 return Action.ERROR;
	}
}
