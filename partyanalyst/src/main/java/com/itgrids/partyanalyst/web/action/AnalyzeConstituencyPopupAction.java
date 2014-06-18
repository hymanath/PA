package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICommentsDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AnalyzeConstituencyPopupAction extends ActionSupport implements ServletRequestAware
{
	private static final long serialVersionUID = 2257011258025761934L;
	private HttpServletRequest request;
	private HttpSession session;
	private static final Logger LOG = Logger.getLogger(AnalyzeConstituencyPopupAction.class);
	
	transient private JSONObject jObj = null;
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
	private String parlchecked;
	private Long constiElecId;
	
	private List<CandidateCommentsVO> candidateComments;
	
	
	public List<CandidateCommentsVO> getCandidateComments() {
		return candidateComments;
	}

	public void setCandidateComments(final List<CandidateCommentsVO> candidateComments) {
		this.candidateComments = candidateComments;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(final String taskType) {
		this.taskType = taskType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	private String userStatus = null;
	
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(final String userStatus) {
		this.userStatus = userStatus;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(final HttpSession session) {
		this.session = session;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(final String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getParliamentConstiId() {
		return parliamentConstiId;
	}

	public void setParliamentConstiId(final String parliamentConstiId) {
		this.parliamentConstiId = parliamentConstiId;
	}

	public List<CandidateVO> getCandidateVO() {
		return candidateVO;
	}

	public void setCandidateVO(final List<CandidateVO> candidateVO) {
		this.candidateVO = candidateVO;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(final String task) {
		this.task = task;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(final HttpServletRequest request) {
		this.request = request;
	}
	public ICommentsDataService getCommentsDataService() {
		return commentsDataService;
	}

	public void setCommentsDataService(final ICommentsDataService commentsDataService) {
		this.commentsDataService = commentsDataService;
	}

	public java.util.List<SelectOptionVO> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(final java.util.List<SelectOptionVO> electionYears) {
		this.electionYears = electionYears;
	}

	public String getRedirectLoc() {
		return redirectLoc;
	}

	public void setRedirectLoc(final String redirectLoc) {
		this.redirectLoc = redirectLoc;
	}

	
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;		
	}
	
	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(final String constituencyId) {
		this.constituencyId = constituencyId;
	}	

	public String getParliamentConstiName() {
		return parliamentConstiName;
	}

	public void setParliamentConstiName(final String parliamentConstiName) {
		this.parliamentConstiName = parliamentConstiName;
	}
    
	public String getParlchecked() {
		return parlchecked;
	}

	public void setParlchecked(final String parlchecked) {
		this.parlchecked = parlchecked;
	}

	public Long getConstiElecId() {
		return constiElecId;
	}

	public void setConstiElecId(final Long constiElecId) {
		this.constiElecId = constiElecId;
	}

	public String execute()
	{	
		
		electionYears = commentsDataService.getElectionYearsForConstituency(Long.parseLong(constituencyId), false);
		request.setAttribute("parlsel", parlchecked);
		request.setAttribute("constiElec", constiElecId);
		return Action.SUCCESS;
	}
	
	public String getCandidateResults()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			LOG.error("Exception rised in getCandidateResults ",e);
		} 
		
		final Long constiElectionId = Long.parseLong(jObj.getString("constElecId"));
		
		candidateVO = commentsDataService.getCandidateResultsForConstiElectionId(constiElectionId);
		
		return Action.SUCCESS;
	}
	
	public String getElectionYearsForConstituency()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			LOG.error("Exception rised in getElectionYearsForConstituency ",e);
		} 
		
		final Long constituencyId = Long.parseLong(jObj.getString("constituencyId"));
		final Boolean onlyAssets = jObj.getBoolean("onlyAssets");
		electionYears = commentsDataService.getElectionYearsForConstituency(constituencyId, onlyAssets);
		
		return Action.SUCCESS;
	}
	
	public String getCommentsResults()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			LOG.error("Exception rised in getCommentsResults ",e);
		} 
		
		final Long constiElectionId = Long.parseLong(jObj.getString("constElecId"));
		
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
