package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ElectionBasicCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
import com.itgrids.partyanalyst.service.IAnalysisReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyElectionResultsAnalysisAction extends ActionSupport implements ServletRequestAware,ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(PartyElectionResultsAnalysisAction.class);
	private HttpServletRequest request;	
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
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getCandidateComments"))
		{
			Long electionId = new Long(jObj.getString("electionId"));
			Long partyId = new Long(jObj.getString("partyId"));
			if(log.isDebugEnabled())
			{
				log.debug("Entered in to Action");
				log.debug("electionId::::::::::::"+electionId);
				log.debug("partyId:::::::::::"+partyId);			
			}
		
		
			electionBasicCommentsVOList = new ArrayList<ElectionBasicCommentsVO>();
			electionBasicCommentsVOList = analysisReportService.getCandidateCommentDetailsInAnElection(electionId, partyId);
				
		}
		
		return Action.SUCCESS;
	}
	
	public String getNotAnalyzedConstituencies() throws Exception
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long electionId = new Long(jObj.getString("electionId"));
		Long partyId = new Long(jObj.getString("partyId"));
		Long stateId = new Long(jObj.getString("stateId"));
		
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
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long electionId = new Long(jObj.getString("electionId"));
		Long partyId = new Long(jObj.getString("partyId"));
		String position = jObj.getString("position");
		
		String category = null;
		if(position.equals("Won"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(position.equals("Lost"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
			
		electionBasicCommentsVOList = analysisReportService.getCandidateCommentDetailsInAnElection(electionId, partyId,category,new Long(0));
		return Action.SUCCESS;
	}
	
	
	public String getMainPartyNotAnalyzedConstituencies() throws Exception
	{

		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long electionId = new Long(jObj.getString("electionId"));
		Long partyId = new Long(jObj.getString("partyId"));
		Long stateId = new Long(jObj.getString("stateId"));
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
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long electionId = new Long(jObj.getString("electionId"));
		Long partyId = new Long(jObj.getString("partyId"));
		Long stateId = new Long(jObj.getString("stateId"));
		String position = jObj.getString("position");
		Long categoryTypeId = new Long(jObj.getString("categoryId"));
		
		String category = null;
		if(position.equals("Won"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(position.equals("Lost"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		electionBasicCommentsVOList = analysisReportService.getCandidateCommentDetailsInAnElection(electionId, partyId,category,categoryTypeId);
		
		return Action.SUCCESS;
	}
	
	public String getMainPartyMultipleReasonsAnalysis() throws Exception
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long electionId = new Long(jObj.getString("electionId"));
		Long partyId = new Long(jObj.getString("partyId"));
		Long stateId = new Long(jObj.getString("stateId"));
		String position = jObj.getString("position");
		Long categoryTypeId = new Long(jObj.getString("categoryId"));
		
		String category = null;
		if(position.equals("Won"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(position.equals("Lost"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		
		//electionBasicCommentsVOList = analysisReportService.getCandidateCommentDetailsInAnElection(electionId, partyId,category,categoryTypeId);
		return Action.SUCCESS;
	
	}
	
	public String getConstituencyStatusAnalysisForVotesMarginWindow()
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long partyId = new Long(jObj.getString("partyId"));
		Long electionId = new Long(jObj.getString("electionId"));
		String resultStatus = jObj.getString("resultStatus");
		Long clickIndex = new Long(jObj.getString("clickIndex"));		
		
		String category = null;
		if(resultStatus.equalsIgnoreCase("WON"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(resultStatus.equalsIgnoreCase("LOST"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		electionResultPartyVO = analysisReportService.getElectionResultsForAnPartyInAnElectionForParticularVotesMargin(electionId, partyId, category, clickIndex);
			
		return Action.SUCCESS;
	}
	
	public String getAnalyzedConstituencyStatusAnalysisForVotesMarginWindow() throws Exception
	{
		
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long partyId = new Long(jObj.getString("partyId"));
		Long electionId = new Long(jObj.getString("electionId"));
		String resultStatus = jObj.getString("resultStatus");
		Long clickIndex = new Long(jObj.getString("clickIndex"));		
		
		String category = null;
		if(resultStatus.equalsIgnoreCase("WON"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(resultStatus.equalsIgnoreCase("LOST"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		electionBasicCommentsVOList = analysisReportService.getCandidateCommentsForAnPartyInAnElectionForParticularVotesMargin(electionId, partyId, category, clickIndex, new Long(0));
		//electionBasicCommentsVOList = analysisReportService.getCandidateCommentsFromNominationIds(partyId, nominationIdsList, new Long(0));
		return Action.SUCCESS;
	}
	
	public String getAnalyzedConstituencyStatusAnalysisCategoryForVotesMarginWindow() throws Exception
	{
		
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long partyId = new Long(jObj.getString("partyId"));
		Long electionId = new Long(jObj.getString("electionId"));
		String resultStatus = jObj.getString("resultStatus");
		Long clickIndex = new Long(jObj.getString("clickIndex"));		
		Long categoryId = new Long(jObj.getString("categoryId"));
		
		String category = null;
		if(resultStatus.equalsIgnoreCase("WON"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(resultStatus.equalsIgnoreCase("LOST"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		electionBasicCommentsVOList = analysisReportService.getCandidateCommentsForAnPartyInAnElectionForParticularVotesMargin(electionId, partyId, category, clickIndex, categoryId);
		
		return Action.SUCCESS;
	}
}
