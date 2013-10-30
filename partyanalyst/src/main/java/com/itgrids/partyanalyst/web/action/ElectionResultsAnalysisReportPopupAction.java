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
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PartyAnalysisReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionAnalysisResultVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotesMarginAnalysisVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.AnalysisReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ElectionResultsAnalysisReportPopupAction extends ActionSupport implements ServletRequestAware,ServletContextAware  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	private static final Logger log = Logger.getLogger(ElectionResultsAnalysisReportPopupAction.class);
	private HttpServletRequest request;
	private IStaticDataService staticDataService; 
	private HttpSession session;
	private AnalysisReportService analysisReportService;
	private PartyPositionAnalysisResultVO partyPositionAnalysisResultVO;
	private PartyAnalysisReportVO partyAnalysisReportVO;
	private String electionType;
	private String electionYear;
	private Long stateId;
	private String stateName; 
	private Long partyId;
	private Long electionTypeId;
	private String task = null;
	JSONObject jObj = null;
	private List<VotesMarginAnalysisVO> votesMarginAnalysisVO;
	private Long electionId;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;		
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public AnalysisReportService getAnalysisReportService() {
		return analysisReportService;
	}

	public void setAnalysisReportService(AnalysisReportService analysisReportService) {
		this.analysisReportService = analysisReportService;
	}

	public PartyPositionAnalysisResultVO getPartyPositionAnalysisResultVO() {
		return partyPositionAnalysisResultVO;
	}

	public void setPartyPositionAnalysisResultVO(
			PartyPositionAnalysisResultVO partyPositionAnalysisResultVO) {
		this.partyPositionAnalysisResultVO = partyPositionAnalysisResultVO;
	}
	
	public void setPartyAnalysisReportVO(PartyAnalysisReportVO partyAnalysisReportVO) {
		this.partyAnalysisReportVO = partyAnalysisReportVO;
	}

	public PartyAnalysisReportVO getPartyAnalysisReportVO() {
		return partyAnalysisReportVO;
	}	

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public Long getElectionTypeId() {
		return electionTypeId;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
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

	public void setVotesMarginAnalysisVO(List<VotesMarginAnalysisVO> votesMarginAnalysisVO) {
		this.votesMarginAnalysisVO = votesMarginAnalysisVO;
	}

	public List<VotesMarginAnalysisVO> getVotesMarginAnalysisVO() {
		return votesMarginAnalysisVO;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateName() {
		return stateName;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public String execute () throws Exception 
	{
		return Action.SUCCESS;
	}
	
	public String ajaxCallHandler () throws Exception 
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getBasicAnalysisDetails"))
		{
			partyAnalysisReportVO = new PartyAnalysisReportVO();
			String electionType = jObj.getString("electionType");
			String electionYear = jObj.getString("electionYear");
			Long stateId = new Long(jObj.getString("stateId"));
			Long partyId = new Long(jObj.getString("partyId"));
			Long electionTypeId = new Long(jObj.getString("electionTypeId"));
			List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> years = new ArrayList<SelectOptionVO>();
			Long electionId = jObj.getLong("electionId");
			partyAnalysisReportVO = analysisReportService.getAnalysisReportForAPartyInAnElection(electionType, electionYear,stateId, partyId,electionId);
			/*if(partyAnalysisReportVO != null)
				createChartForAnalysisResults(partyAnalysisReportVO);*/
			
		}
		return Action.SUCCESS;
	}
	
	/*//Method To Create Chart for Analysis Results
	public void createChartForAnalysisResults(PartyAnalysisReportVO partyAnalysisReportVO){
		
		log.debug("Inside createChartForAnalysisResults Method....");
		session = request.getSession();
		String cPath = request.getContextPath();
		
		int height = 220;
	    int width = 220;
		if(partyAnalysisReportVO != null){
			try{
			
			//Analysis Chart For Main Party
				String analysisMainPartyChartPath;
			String analysisChartMainPartyId = partyAnalysisReportVO.getStateName().concat("_").concat(partyAnalysisReportVO.getElectionType()).concat("_").concat(partyAnalysisReportVO.getElectionYear()).concat("_").concat(partyAnalysisReportVO.getPartyName()).concat("_Analysis_Bar_Chart");
	 		String analysisMainPartyChartName = "analysisChart_" + analysisChartMainPartyId + session.getId() +".png";
	 		if(cPath.contains("PartyAnalyst"))
	 		    analysisMainPartyChartPath = context.getRealPath("/") + "charts\\" + analysisMainPartyChartName;
	 		else
	           analysisMainPartyChartPath =IWebConstants.CHART_URL_IN_SERVER + analysisMainPartyChartName;

	        String title = partyAnalysisReportVO.getPartyBasicAnalysisVO().getPartyName().concat(" Analysis Details");
	       	ChartProducer.create3DBarChartWithInputParams(title,"Analysis","Party","Results",partyAnalysisReportVO.getPartyBasicAnalysisVO().getPartyName(),createDatasetForPartyAnalysisResults(partyAnalysisReportVO.getPartyBasicAnalysisVO()), analysisMainPartyChartPath,width,height, null,false);
	 		request.setAttribute("analysisMainPartyChartName", analysisMainPartyChartName);
			session.setAttribute("analysisMainPartyChartName", analysisMainPartyChartName);
			partyAnalysisReportVO.getPartyBasicAnalysisVO().setAnalysisChart(analysisMainPartyChartName);
			
			//Election Results Chart For Main Party
			 String resultsMainPartyChartPath;
			String resultsChartMainPartyId = partyAnalysisReportVO.getStateName().concat("_").concat(partyAnalysisReportVO.getElectionType()).concat("_").concat(partyAnalysisReportVO.getElectionYear()).concat("_").concat(partyAnalysisReportVO.getPartyName()).concat("_Results_Bar_Chart");
	 		String resultsMainPartyChartName = "resultsChart_" + resultsChartMainPartyId + session.getId() +".png";
	 		if(cPath.contains("PartyAnalyst"))
               resultsMainPartyChartPath = context.getRealPath("/") + "charts\\" + resultsMainPartyChartName;
	 		else
	 		   resultsMainPartyChartPath = IWebConstants.CHART_URL_IN_SERVER + resultsMainPartyChartName;	 
	 		
	        String title1 = partyAnalysisReportVO.getPartyBasicAnalysisVO().getPartyName().concat(" Election Results");
	       	ChartProducer.create3DBarChartWithInputParams(title1,"Results","Party","Seats",partyAnalysisReportVO.getPartyBasicAnalysisVO().getPartyName(),createDatasetForPartyElectionResults(partyAnalysisReportVO.getPartyBasicAnalysisVO()), resultsMainPartyChartPath,width,height, null,false);
	 		request.setAttribute("resultsMainPartyChartName", resultsMainPartyChartName);
			session.setAttribute("resultsMainPartyChartName", resultsMainPartyChartName);
			partyAnalysisReportVO.getPartyBasicAnalysisVO().setResultsChart(resultsMainPartyChartName);
			
						
			//Analysis Chart For Alliance Parties
			String analysisAlliancPartyChartPath ;
			if(partyAnalysisReportVO.getAlliancPartiesBasicAnalysisVO() != null && partyAnalysisReportVO.getAlliancPartiesBasicAnalysisVO().size() > 0){
			for(PartyAnalysisBasicVO alliancAnalysis:partyAnalysisReportVO.getAlliancPartiesBasicAnalysisVO()){
				String analysisChartAlliancPartyId = partyAnalysisReportVO.getStateName().concat("_").concat(partyAnalysisReportVO.getElectionType()).concat("_").concat(partyAnalysisReportVO.getElectionYear()).concat("_").concat(alliancAnalysis.getPartyName()).concat("_Analysis_Bar_Chart");
		 		String analysisAlliancPartyChartName = "analysisChartForAlliancParties_" + analysisChartAlliancPartyId + session.getId() +".png";
		 		if(cPath.contains("PartyAnalyst"))
		          analysisAlliancPartyChartPath = context.getRealPath("/") + "charts\\" + analysisAlliancPartyChartName;
		 		else
		 		  analysisAlliancPartyChartPath = IWebConstants.CHART_URL_IN_SERVER + analysisAlliancPartyChartName;	
		 		
		        String title2 = alliancAnalysis.getPartyName().concat(" Analysis Details");
		      	ChartProducer.create3DBarChartWithInputParams(title2,"Analysis","Party","Results",alliancAnalysis.getPartyName(),createDatasetForPartyAnalysisResults(alliancAnalysis), analysisAlliancPartyChartPath,width,height, null,false);
		 		request.setAttribute("analysisAlliancPartyChartName", analysisAlliancPartyChartName);
				session.setAttribute("analysisAlliancPartyChartName", analysisAlliancPartyChartName);
				alliancAnalysis.setAnalysisChart(analysisAlliancPartyChartName);
				
				//Election Results Chart For Main Party
				String resultsAlliancPartyChartPath;
				String resultsChartAlliancPartyId = partyAnalysisReportVO.getStateName().concat("_").concat(partyAnalysisReportVO.getElectionType()).concat("_").concat(partyAnalysisReportVO.getElectionYear()).concat("_").concat(alliancAnalysis.getPartyName()).concat("_Results_Bar_Chart");
		 		String resultsAlliancPartyChartName = "resultsChartForAlliancParties_" + resultsChartAlliancPartyId + session.getId() +".png";
		 		if(cPath.contains("PartyAnalyst"))
		           resultsAlliancPartyChartPath = context.getRealPath("/") + "charts\\" + resultsAlliancPartyChartName;
		 		else
		 		   resultsAlliancPartyChartPath = IWebConstants.CHART_URL_IN_SERVER + resultsAlliancPartyChartName;
		 		
		        String title3 = alliancAnalysis.getPartyName().concat(" Election Results");
		       	ChartProducer.create3DBarChartWithInputParams(title3,"Results","Party","Seats",alliancAnalysis.getPartyName(),createDatasetForPartyElectionResults(alliancAnalysis), resultsAlliancPartyChartPath,width,height, null,false);
		 		request.setAttribute("resultsAlliancPartyChartName", resultsAlliancPartyChartName);
				session.setAttribute("resultsAlliancPartyChartName", resultsAlliancPartyChartName);
				alliancAnalysis.setResultsChart(resultsAlliancPartyChartName);
			}
			}
			}
			catch(Exception ex){
				ex.printStackTrace();
				log.debug("Exception Raised :" + ex);
			}
		}
	}*/
	
	/*private CategoryDataset createDatasetForPartyAnalysisResults(PartyAnalysisBasicVO partyBasicAnalysisVO) {
		
		  log.debug("Inside createDatasetForPartyAnalysisResults Method....");
		  String analVal = partyBasicAnalysisVO.getAnalyzedConsti().toString();
		  String notAnalVal = partyBasicAnalysisVO.getNotAnalyzedConsti().toString();
		  final String category3 = "Analyzed".concat("(").concat(analVal).concat(")");
		  final String category4 = "Not Analyzed".concat("(").concat(notAnalVal).concat(")");  

		  
		   final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		  
		    final String series =  partyBasicAnalysisVO.getPartyName();
	       	dataset.addValue(partyBasicAnalysisVO.getAnalyzedConsti(), category3, series);
	      	dataset.addValue(partyBasicAnalysisVO.getNotAnalyzedConsti(), category4, series);
	      	
	 return dataset;
   }
	
	private CategoryDataset createDatasetForPartyElectionResults(PartyAnalysisBasicVO partyBasicAnalysisVO) {
		
		  log.debug("Inside createDatasetForPartyElectionResults Method....");
		  String wonVal = partyBasicAnalysisVO.getSeatsWon().toString();
		  String losVal = partyBasicAnalysisVO.getSeatsLost().toString();
		  final String category1 = "Won".concat("(").concat(wonVal).concat(")");
		  final String category2 = "Lost".concat("(").concat(losVal).concat(")");  
		  final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		  
		    final String series =  partyBasicAnalysisVO.getPartyName();
	      	dataset.addValue(partyBasicAnalysisVO.getSeatsWon(), category1, series);
	      	dataset.addValue(partyBasicAnalysisVO.getSeatsLost(), category2, series);
	      	      	
	 return dataset;
 }*/
	public String getAnalysisCategoryResults() throws Exception
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
		if(jObj.getString("task").equalsIgnoreCase("getAnalysisDetailsInPartyWonPositions"))
		{
			partyPositionAnalysisResultVO = new PartyPositionAnalysisResultVO();
			String electionType = jObj.getString("electionType");
			Long electionId = new Long(jObj.getString("electionId"));
			Long stateId = new Long(jObj.getString("stateId"));
			String electionYear = jObj.getString("electionYear");
			Long partyId = new Long(jObj.getString("partyId"));
			partyPositionAnalysisResultVO = analysisReportService.getAnalysisCategoryResultForAPartyInAnElection(electionType,electionYear,electionId,stateId,partyId,IConstants.CANDIDATE_COMMENTS_WON,false);
			
		}
		if(jObj.getString("task").equalsIgnoreCase("getAnalysisDetailsInPartyLostPositions"))
		{
			partyPositionAnalysisResultVO = new PartyPositionAnalysisResultVO();
			String electionType = jObj.getString("electionType");
			Long electionId = new Long(jObj.getString("electionId"));
			Long stateId = new Long(jObj.getString("stateId"));
			String electionYear = jObj.getString("electionYear");
			Long partyId = new Long(jObj.getString("partyId"));
			partyPositionAnalysisResultVO = analysisReportService.getAnalysisCategoryResultForAPartyInAnElection(electionType,electionYear,electionId,stateId,partyId,IConstants.CANDIDATE_COMMENTS_LOST,false);
		}
		return Action.SUCCESS;
	}
	
	public String getVotesMarginInfoForMainParty() throws Exception
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
		String status = jObj.getString("status");
		
		String category = null;
		if(status.equalsIgnoreCase("WON"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(status.equalsIgnoreCase("LOST"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		setVotesMarginAnalysisVO(analysisReportService.getVotesMarginAnalysisResults(electionId, partyId, category,0L,0L));
		
		
		return Action.SUCCESS;
	}
	
}
