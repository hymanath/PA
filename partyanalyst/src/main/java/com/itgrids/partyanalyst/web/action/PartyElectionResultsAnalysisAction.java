package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.ElectionBasicCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAnalysisReportService;
import com.itgrids.partyanalyst.service.ICommentsDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyElectionResultsAnalysisAction extends ActionSupport implements ServletRequestAware,ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(PartyElectionResultsAnalysisAction.class);
	private HttpServletRequest request;	
	private HttpSession session;
	private ServletContext context;
	private IAnalysisReportService analysisReportService; 
	private List<ElectionBasicCommentsVO> electionBasicCommentsVOList;
	private List<CandidateElectionResultVO> candidateElectionResultVO; 
	private ElectionResultPartyVO electionResultPartyVO;
	private String stateId;
	private Long electionId;
	private Long partyId;
	private String partyName;
	private String electionType;
	private String status;
	private String stateName;
	private String electionYear;
	private String position;
	private String categoryId;	
	private String windowTask;
	private String reasonCount;
	private String constituencyCount;
	private String clickIndex;
	private String task = null;
	private String resultStatus;
	JSONObject jObj = null;
	JSONArray jsonArray =null;
	
	private ICommentsDataService commentsDataService;
	private List<CandidateCommentsVO> candidateComments;
	private List<?> constituencyIds;
	

	public List<?> getConstituencyIds() {
		return constituencyIds;
	}

	public void setConstituencyIds(List<?> constituencyIds) {
		this.constituencyIds = constituencyIds;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public List<CandidateCommentsVO> getCandidateComments() {
		return candidateComments;
	}

	public void setCandidateComments(List<CandidateCommentsVO> candidateComments) {
		this.candidateComments = candidateComments;
	}

	public ICommentsDataService getCommentsDataService() {
		return commentsDataService;
	}

	public void setCommentsDataService(ICommentsDataService commentsDataService) {
		this.commentsDataService = commentsDataService;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public String getClickIndex() {
		return clickIndex;
	}

	public void setClickIndex(String clickIndex) {
		this.clickIndex = clickIndex;
	}

	public ElectionResultPartyVO getElectionResultPartyVO() {
		return electionResultPartyVO;
	}

	public void setElectionResultPartyVO(ElectionResultPartyVO electionResultPartyVO) {
		this.electionResultPartyVO = electionResultPartyVO;
	}

	public String getReasonCount() {
		return reasonCount;
	}

	public void setReasonCount(String reasonCount) {
		this.reasonCount = reasonCount;
	}

	public String getConstituencyCount() {
		return constituencyCount;
	}

	public void setConstituencyCount(String constituencyCount) {
		this.constituencyCount = constituencyCount;
	}

	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<CandidateElectionResultVO> getCandidateElectionResultVO() {
		return candidateElectionResultVO;
	}

	public void setCandidateElectionResultVO(
			List<CandidateElectionResultVO> candidateElectionResultVO) {
		this.candidateElectionResultVO = candidateElectionResultVO;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void setAnalysisReportService(IAnalysisReportService analysisReportService) {
		this.analysisReportService = analysisReportService;
	}

	public IAnalysisReportService getAnalysisReportService() {
		return analysisReportService;
	}
	
	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	
	public void setElectionBasicCommentsVOList(
			List<ElectionBasicCommentsVO> electionBasicCommentsVOList) {
		this.electionBasicCommentsVOList = electionBasicCommentsVOList;
	}

	public List<ElectionBasicCommentsVO> getElectionBasicCommentsVOList() {
		return electionBasicCommentsVOList;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}	

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
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

	public String execute () throws Exception 
	{
		return Action.SUCCESS;
	}
	
	public String ajaxCallHandler() throws Exception{
		
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getCandidateComments"))
		{
			Long electionId = Long.valueOf(jObj.getString("electionId"));
			Long partyId = Long.valueOf(jObj.getString("partyId"));
			Long stateId = Long.valueOf(jObj.getString("stateId"));
			if(LOG.isDebugEnabled())
			{
				LOG.debug("Entered in to Action");
				LOG.debug("electionId::::::::::::"+electionId);
				LOG.debug("partyId:::::::::::"+partyId);			
			}
		
		
			electionBasicCommentsVOList = new ArrayList<ElectionBasicCommentsVO>();
			electionBasicCommentsVOList = analysisReportService.getCandidateCommentDetailsInAnElection(electionId, partyId,stateId);
				
		}
		
		return Action.SUCCESS;
	}
	
	public String getNotAnalyzedConstituencies() throws Exception
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long electionId = Long.valueOf(jObj.getString("electionId"));
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		Long stateId = Long.valueOf(jObj.getString("stateId"));
		
		if(jObj.getString("status").equalsIgnoreCase("notAnalyzed"))
		{			
			candidateElectionResultVO = analysisReportService.getElectionResultsForNotAnalyzedConstituencies(electionId, partyId, stateId);
		}
		return Action.SUCCESS;
	}
	
	public String getMainPartyAnalyzedConstituencies() throws Exception
	{

		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long electionId = Long.valueOf(jObj.getString("electionId"));
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		String position = jObj.getString("position");
		Long stateId = Long.valueOf(jObj.getString("stateId"));
		
		String category = null;
		if(position.equals("Won"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(position.equals("Lost"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
			
		electionBasicCommentsVOList = analysisReportService.getCandidateCommentDetailsInAnElection(electionId, partyId,category,Long.valueOf(0),stateId);
		return Action.SUCCESS;
	}
	
	
	public String getMainPartyNotAnalyzedConstituencies() throws Exception
	{

		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long electionId = Long.valueOf(jObj.getString("electionId"));
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		Long stateId = Long.valueOf(jObj.getString("stateId"));
		String position = jObj.getString("position");
		
		String category = null;
		if(position.equals("Won"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(position.equals("Lost"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		if(jObj.getString("status").equalsIgnoreCase("notAnalyzed"))
		{			
			candidateElectionResultVO = analysisReportService.getElectionResultsForNotAnalyzedConstituencies(electionId, partyId, stateId,category);
		}
		return Action.SUCCESS;
	}
	
	public String getMainPartyCategoryAnalyzedConstituencies() throws Exception
	{

		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long electionId = Long.valueOf(jObj.getString("electionId"));
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		Long stateId = Long.valueOf(jObj.getString("stateId"));
		String position = jObj.getString("position");
		Long categoryTypeId = Long.valueOf(jObj.getString("categoryId"));
		
		String category = null;
		if(position.equals("Won"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(position.equals("Lost"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		electionBasicCommentsVOList = analysisReportService.getCandidateCommentDetailsInAnElection(electionId, partyId,category,categoryTypeId,stateId);
		
		return Action.SUCCESS;
	}
	
	public String getMainPartyMultipleReasonsAnalysis() throws Exception
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Long> constituencyIds=new ArrayList<Long>();
		Long electionId = Long.valueOf(jObj.getString("electionId"));
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		Long stateId = Long.valueOf(jObj.getString("stateId"));
		String position = jObj.getString("position");
		Long categoryTypeId = Long.valueOf(jObj.getString("categoryId"));
		JSONArray jsonArray = jObj.getJSONArray("constituencyId");
		if(jsonArray != null && jsonArray.length() > 0)
			for(int i = 0 ; i < jsonArray.length() ; i++)
			{
				if(jsonArray.get(i) != null)
				{
					constituencyIds.add(Long.valueOf(jsonArray.get(i).toString()));
				}
			}
		String category = null;
		if(position.equals("Won"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(position.equals("Lost"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		electionBasicCommentsVOList = analysisReportService.getCandidateCommentDetailsInAnElection1(electionId, partyId,category,categoryTypeId,stateId,constituencyIds);
		//electionBasicCommentsVOList = analysisReportService.getCandidateCommentDetailsInAnElection(electionId, partyId,category,categoryTypeId,stateId);
		return Action.SUCCESS;
	
	}
	
	public String getConstituencyStatusAnalysisForVotesMarginWindow()
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		Long userId =  regVO.getRegistrationID();
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		Long electionId = Long.valueOf(jObj.getString("electionId"));
		String resultStatus = jObj.getString("resultStatus");
		Long clickIndex = Long.valueOf(jObj.getString("clickIndex"));	
		Long locationId= Long.valueOf(jObj.getString("locationId"));
		Long reportLevel = Long.valueOf(jObj.getString("reportLevel"));
		Long stateId = Long.valueOf(jObj.getString("stateId"));;
		Long districtId = Long.valueOf(0);
		
		if(reportLevel != null && reportLevel.equals(1L))
			stateId = locationId;
		else if(reportLevel != null && reportLevel.equals(2L))
			districtId = locationId;
		
		LOG.info(" Report Type :" + reportLevel + " Location ID :" + locationId);
		
		String category = null;
		if(resultStatus.equalsIgnoreCase("WON"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(resultStatus.equalsIgnoreCase("LOST"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		electionResultPartyVO = analysisReportService.getElectionResultsForAnPartyInAnElectionForParticularVotesMargin(electionId, partyId, category, clickIndex,stateId,districtId,userId);
			
		return Action.SUCCESS;
	}
	
	public String getAnalyzedConstituencyStatusAnalysisForVotesMarginWindow() throws Exception
	{
		
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		Long electionId = Long.valueOf(jObj.getString("electionId"));
		String resultStatus = jObj.getString("resultStatus");
		Long clickIndex = Long.valueOf(jObj.getString("clickIndex"));		
		
		String category = null;
		if(resultStatus.equalsIgnoreCase("WON"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(resultStatus.equalsIgnoreCase("LOST"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		electionBasicCommentsVOList = analysisReportService.getCandidateCommentsForAnPartyInAnElectionForParticularVotesMargin(electionId, partyId, category, clickIndex, Long.valueOf(0));
		//electionBasicCommentsVOList = analysisReportService.getCandidateCommentsFromNominationIds(partyId, nominationIdsList, Long.valueOf(0));
		return Action.SUCCESS;
	}
	
	public String getAnalyzedConstituencyStatusAnalysisCategoryForVotesMarginWindow() throws Exception
	{
		
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		Long electionId = Long.valueOf(jObj.getString("electionId"));
		String resultStatus = jObj.getString("resultStatus");
		Long clickIndex = Long.valueOf(jObj.getString("clickIndex"));		
		Long categoryId = Long.valueOf(jObj.getString("categoryId"));
		
		String category = null;
		if(resultStatus.equalsIgnoreCase("WON"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(resultStatus.equalsIgnoreCase("LOST"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		electionBasicCommentsVOList = analysisReportService.getCandidateCommentsForAnPartyInAnElectionForParticularVotesMargin(electionId, partyId, category, clickIndex, categoryId);
		
		return Action.SUCCESS;
	}
	
	public String getConstituencyAnalyzedComments() throws Exception
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long nominationId = Long.valueOf(jObj.getString("nominationId"));
		
		candidateComments = commentsDataService.getAnalyzedResonsWithRatingsForConstituencyInAnElection(true,nominationId);
		
		return Action.SUCCESS;
	}
}
