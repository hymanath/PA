package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PartyAnalysisBasicVO;
import com.itgrids.partyanalyst.dto.PartyAnalysisReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionAnalysisResultVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotesMarginAnalysisVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.AnalysisReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ElectionResultsAnalysisReportAction extends ActionSupport implements ServletRequestAware,ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ElectionResultsAnalysisReportAction.class);
	private HttpServletRequest request;	
	private ServletContext context;
	private IStaticDataService staticDataService; 
	private HttpSession session;
	private List<SelectOptionVO> statesList;
	private List<SelectOptionVO> electionTypes;
	private List<SelectOptionVO> electionYears;
	private List<SelectOptionVO> partiesList;
	
	private PartyAnalysisReportVO partyAnalysisReportVO;
	private List<VotesMarginAnalysisVO> votesMarginAnalysisVO;
	
	private String task = null;
	JSONObject jObj = null;
	private AnalysisReportService analysisReportService;
	private PartyPositionAnalysisResultVO partyPositionAnalysisResultVO;
	
	private EntitlementsHelper entitlementsHelper;
	private String chartProducerURL="/var/www/vsites/partyanalyst.com/httpdocs/charts/";
	public List<VotesMarginAnalysisVO> getVotesMarginAnalysisVO() {
		return votesMarginAnalysisVO;
	}

	public void setVotesMarginAnalysisVO(
			List<VotesMarginAnalysisVO> votesMarginAnalysisVO) {
		this.votesMarginAnalysisVO = votesMarginAnalysisVO;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.setRequest(request);
		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	
	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
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

	public List<SelectOptionVO> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(List<SelectOptionVO> electionYears) {
		this.electionYears = electionYears;
	}

	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}

	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}
	
	
	public PartyAnalysisReportVO getPartyAnalysisReportVO() {
		return partyAnalysisReportVO;
	}

	public void setPartyAnalysisReportVO(PartyAnalysisReportVO partyAnalysisReportVO) {
		this.partyAnalysisReportVO = partyAnalysisReportVO;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}	

	public AnalysisReportService getAnalysisReportService() {
		return analysisReportService;
	}

	public void setAnalysisReportService(AnalysisReportService analysisReportService) {
		this.analysisReportService = analysisReportService;
	}	
	
	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public void setElectionTypes(List<SelectOptionVO> electionTypes) {
		this.electionTypes = electionTypes;
	}

	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
	}

	public void setPartyPositionAnalysisResultVO(
			PartyPositionAnalysisResultVO partyPositionAnalysisResultVO) {
		this.partyPositionAnalysisResultVO = partyPositionAnalysisResultVO;
	}

	public PartyPositionAnalysisResultVO getPartyPositionAnalysisResultVO() {
		return partyPositionAnalysisResultVO;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute () throws Exception 
	{
		session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ELECTION_RESULTS_ANALYSIS_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ELECTION_RESULTS_ANALYSIS_REPORT))
			return ERROR;
		
		/*statesList = new ArrayList<SelectOptionVO>();
		statesList.add(0, new SelectOptionVO(0L,"Select State"));
		statesList.add(1, new SelectOptionVO(1L,"Andhra Pradesh"));*/
		
		statesList = staticDataService.getParticipatedStatesForAnElectionType(2l);
		statesList.add(0, new SelectOptionVO(0L,"Select State"));
		
		/*
		electionTypes = new ArrayList<SelectOptionVO>();
		electionTypes.add(0, new SelectOptionVO(0L, "Select Election Type"));
		electionTypes.add(1, new SelectOptionVO(1L, "Parliament"));
		electionTypes.add(2, new SelectOptionVO(2L, "Assembly"));
		electionTypes.add(3, new SelectOptionVO(3L, "MPTC"));
		electionTypes.add(4, new SelectOptionVO(4L, "ZPTC"));		
		
		electionYears = staticDataService.getElectionIdsAndYearsInfo(2L,new Long(1));
		electionYears.add(0, new SelectOptionVO(0l,"Select Year"));
		
		partiesList = new ArrayList<SelectOptionVO>();
		partiesList = staticDataService.getStaticParties();
		partiesList.add(0, new SelectOptionVO(0l,"Select A Party"));*/
		
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
		
		if(jObj.getString("task").equalsIgnoreCase("getElectionsYears"))
		{
			String electionType = jObj.getString("electionType");
			Long electionTypeId = new Long(jObj.getString("electionTypeId"));
			Long stateID = new Long(jObj.getString("stateID"));
			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
			{
				try{
					electionYears = staticDataService.getElectionIdsAndYearsInfo(electionTypeId,new Long(stateID));
					electionYears.add(0, new SelectOptionVO(0l,"Select Year"));
					
				}catch(Exception e){
					electionYears = null;
					log.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
				}	
			} else 	if(electionType.equals(IConstants.ZPTC) || electionType.equals(IConstants.MPTC))
			{
				try{
					electionYears = staticDataService.getAllElectionYearsForATeshil(electionTypeId);
					electionYears.add(0, new SelectOptionVO(0l,"Select Year"));
					
				}catch(Exception e){
					electionYears = null;
					log.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
				}
			}
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
			List<SelectOptionVO> electionYearsOfParty = new ArrayList<SelectOptionVO>();
			partyAnalysisReportVO = analysisReportService.getAnalysisReportForAPartyInAnElection(electionType, electionYear,stateId, partyId);
			parties = staticDataService.getStaticPartiesListForAState(stateId);
			parties.add(0, new SelectOptionVO(0l,"Select Party"));
			
			partyAnalysisReportVO.setPartiesList(parties);
			
			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
			{
				try{
					years = staticDataService.getElectionIdsAndYearsInfo(electionTypeId, stateId);
					years.add(0, new SelectOptionVO(0l,"Select Year"));
					partyAnalysisReportVO.setElectionYearsList(years);
					electionYearsOfParty = staticDataService.getElectionYearByPartyId( stateId, partyId, electionTypeId);
					electionYearsOfParty.add(0, new SelectOptionVO(0l,"Select Year"));
					partyAnalysisReportVO.setElectionYearsListForParty(electionYearsOfParty);
				}catch(Exception e){
					years = null;
					log.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
				}	
			} else 	if(electionType.equals(IConstants.ZPTC) || electionType.equals(IConstants.MPTC))
			{
				try{
					years = staticDataService.getAllElectionYearsForATeshil(electionTypeId);
					years.add(0, new SelectOptionVO(0l,"Select Year"));
					partyAnalysisReportVO.setElectionYearsList(years);
					
				}catch(Exception e){
					years = null;
					log.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
				}
			}			
			
			if(partyAnalysisReportVO != null)
				createChartForAnalysisResults(partyAnalysisReportVO);
			
		}
		
		return Action.SUCCESS;
	}
	
	//Method To Create Chart for Analysis Results
	public void createChartForAnalysisResults(PartyAnalysisReportVO partyAnalysisReportVO){
		
		log.debug("Inside createChartForAnalysisResults Method....");
		session = request.getSession();
		String cPath = request.getContextPath();
		String analysisMainPartyChartPath;
		String resultsMainPartyChartPath;
		String analysisAlliancPartyChartPath;
		String resultsAlliancPartyChartPath;
		
		int height = 220;
	    int width = 220;
		if(partyAnalysisReportVO != null){
			try{
			
			//Analysis Chart For Main Party
			String analysisChartMainPartyId = partyAnalysisReportVO.getStateName().concat("_").concat(partyAnalysisReportVO.getElectionType()).concat("_").concat(partyAnalysisReportVO.getElectionYear()).concat("_").concat(partyAnalysisReportVO.getPartyName()).concat("_Analysis_Bar_Chart");
	 		String analysisMainPartyChartName = "analysisChart_" + analysisChartMainPartyId + session.getId() +".png";
	 		
	 		if(cPath.contains("PartyAnalyst"))
	           analysisMainPartyChartPath = context.getRealPath("/") + "charts\\" + analysisMainPartyChartName;
	 		else
	 		   analysisMainPartyChartPath = chartProducerURL + analysisMainPartyChartName;
	 		
	        String title = partyAnalysisReportVO.getPartyBasicAnalysisVO().getPartyName().concat(" Analysis Details");
	       	ChartProducer.create3DBarChartWithInputParams(title,"Analysis","Party","Results",partyAnalysisReportVO.getPartyBasicAnalysisVO().getPartyName(),createDatasetForPartyAnalysisResults(partyAnalysisReportVO.getPartyBasicAnalysisVO()), analysisMainPartyChartPath,width,height, null,false);
	 		request.setAttribute("analysisMainPartyChartName", analysisMainPartyChartName);
			session.setAttribute("analysisMainPartyChartName", analysisMainPartyChartName);
			partyAnalysisReportVO.getPartyBasicAnalysisVO().setAnalysisChart(analysisMainPartyChartName);
			
			//Election Results Chart For Main Party
			String resultsChartMainPartyId = partyAnalysisReportVO.getStateName().concat("_").concat(partyAnalysisReportVO.getElectionType()).concat("_").concat(partyAnalysisReportVO.getElectionYear()).concat("_").concat(partyAnalysisReportVO.getPartyName()).concat("_Results_Bar_Chart");
	 		String resultsMainPartyChartName = "resultsChart_" + resultsChartMainPartyId + session.getId() +".png";
	 		
	 		if(cPath.contains("PartyAnalyst"))
	           resultsMainPartyChartPath = context.getRealPath("/") + "charts\\" + resultsMainPartyChartName;
	 		else
	 	       resultsMainPartyChartPath = chartProducerURL + resultsMainPartyChartName;
	 		
	        String title1 = partyAnalysisReportVO.getPartyBasicAnalysisVO().getPartyName().concat(" Election Results");
	       	ChartProducer.create3DBarChartWithInputParams(title1,"Results","Party","Seats",partyAnalysisReportVO.getPartyBasicAnalysisVO().getPartyName(),createDatasetForPartyElectionResults(partyAnalysisReportVO.getPartyBasicAnalysisVO()), resultsMainPartyChartPath,width,height, null,false);
	 		request.setAttribute("resultsMainPartyChartName", resultsMainPartyChartName);
			session.setAttribute("resultsMainPartyChartName", resultsMainPartyChartName);
			partyAnalysisReportVO.getPartyBasicAnalysisVO().setResultsChart(resultsMainPartyChartName);
			
						
			//Analysis Chart For Alliance Parties
			if(partyAnalysisReportVO.getAlliancPartiesBasicAnalysisVO() != null && partyAnalysisReportVO.getAlliancPartiesBasicAnalysisVO().size() > 0){
			for(PartyAnalysisBasicVO alliancAnalysis:partyAnalysisReportVO.getAlliancPartiesBasicAnalysisVO()){
				String analysisChartAlliancPartyId = partyAnalysisReportVO.getStateName().concat("_").concat(partyAnalysisReportVO.getElectionType()).concat("_").concat(partyAnalysisReportVO.getElectionYear()).concat("_").concat(alliancAnalysis.getPartyName()).concat("_Analysis_Bar_Chart");
		 		String analysisAlliancPartyChartName = "analysisChartForAlliancParties_" + analysisChartAlliancPartyId + session.getId() +".png";
		 		
		 		if(cPath.contains("PartyAnalyst"))
					analysisAlliancPartyChartPath = context.getRealPath("/") + "charts\\" + analysisAlliancPartyChartName;
		 		else
		 			analysisAlliancPartyChartPath = chartProducerURL+ analysisAlliancPartyChartName;
		 		
		        String title2 = alliancAnalysis.getPartyName().concat(" Analysis Details");
		      	ChartProducer.create3DBarChartWithInputParams(title2,"Analysis","Party","Results",alliancAnalysis.getPartyName(),createDatasetForPartyAnalysisResults(alliancAnalysis), analysisAlliancPartyChartPath,width,height, null,false);
		 		request.setAttribute("analysisAlliancPartyChartName", analysisAlliancPartyChartName);
				session.setAttribute("analysisAlliancPartyChartName", analysisAlliancPartyChartName);
				alliancAnalysis.setAnalysisChart(analysisAlliancPartyChartName);
				
				//Election Results Chart For Main Party
				String resultsChartAlliancPartyId = partyAnalysisReportVO.getStateName().concat("_").concat(partyAnalysisReportVO.getElectionType()).concat("_").concat(partyAnalysisReportVO.getElectionYear()).concat("_").concat(alliancAnalysis.getPartyName()).concat("_Results_Bar_Chart");
		 		String resultsAlliancPartyChartName = "resultsChartForAlliancParties_" + resultsChartAlliancPartyId + session.getId() +".png";
		 		if(cPath.contains("PartyAnalyst"))
		 	        resultsAlliancPartyChartPath = context.getRealPath("/") + "charts\\" + resultsAlliancPartyChartName;
		 		else
		 	        resultsAlliancPartyChartPath = chartProducerURL + resultsAlliancPartyChartName;
		 		
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
	}
	
	private CategoryDataset createDatasetForPartyAnalysisResults(PartyAnalysisBasicVO partyBasicAnalysisVO) {
		
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
 }
	
	public String getAllElectionTypes() throws Exception	{
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
			electionTypes = new ArrayList<SelectOptionVO>();
			electionTypes.add(0, new SelectOptionVO(0L, "Select Election Type"));
			electionTypes.add(1, new SelectOptionVO(1L, "Parliament"));
			electionTypes.add(2, new SelectOptionVO(2L, "Assembly"));
			electionTypes.add(3, new SelectOptionVO(3L, "MPTC"));
			electionTypes.add(4, new SelectOptionVO(4L, "ZPTC"));		
		return Action.SUCCESS;	
	}
	public String getAllElectionYears() throws Exception	{
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
			String electionType = jObj.getString("electionType");
			Long electionTypeId = new Long(jObj.getString("electionTypeId"));
			Long stateID = new Long(jObj.getString("stateID"));
			if(electionType.equals(IConstants.ASSEMBLY_ELECTION_TYPE) || electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
			{
				try{
					electionYears = staticDataService.getElectionIdsAndYearsInfo(electionTypeId,new Long(stateID));
					electionYears.add(0, new SelectOptionVO(0l,"Select Year"));
					
				}catch(Exception e){
					electionYears = null;
					log.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
				}	
			} else 	if(electionType.equals(IConstants.ZPTC) || electionType.equals(IConstants.MPTC))
			{
				try{
					electionYears = staticDataService.getAllElectionYearsForATeshil(electionTypeId);
					electionYears.add(0, new SelectOptionVO(0l,"Select Year"));
					
				}catch(Exception e){
					electionYears = null;
					log.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
				}
			}
		
		return Action.SUCCESS;	
	}
	
	public String getAllParties() throws Exception	{
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
		partiesList = new ArrayList<SelectOptionVO>();
		partiesList = staticDataService.getStaticParties();
		partiesList.add(0, new SelectOptionVO(0l,"Select A Party"));
					
		return Action.SUCCESS;	
	}
	
	public String getAnalysisCategoryResults() throws Exception
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
			// TODO Auto-generated catch block
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
		
		votesMarginAnalysisVO = analysisReportService.getVotesMarginAnalysisResults(electionId, partyId, category,0L,0L) ;
		
		
		return Action.SUCCESS;
	}
	
	
	public String getElectionTypesForAState(){
		
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
		
		Long stateID = new Long(jObj.getString("stateId"));
		if(!stateID.equals(0L))
		electionTypes = staticDataService.getAllElectionScopesForAState(stateID);
		electionTypes.add(0, new SelectOptionVO(0L,"Select Type"));
		
	 return Action.SUCCESS;
	}
	
	public String electionYearsForstateAndElectionType()
	{
		try {
			jObj = new JSONObject(getTask());
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long stateID = jObj.getLong("stateId");
		String electionType = jObj.getString("electionType");
		
		if(!stateID.equals(0L) && electionType != null && electionType.trim().length() > 0)
		{	
			electionYears = staticDataService.electionYearsForstateAndElectionType(stateID,electionType);
		}
		if(electionYears == null || electionYears.size() == 0)
			electionYears = new ArrayList<SelectOptionVO>(0);
		
		electionYears.add(0,new SelectOptionVO(0L,"Select Year"));
		return Action.SUCCESS;
	}
	
    public String getElectionYearsForAnElectionTypeAndState(){

   	if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			String elecType = jObj.getString("elecTypeId");
			Long partyId = new Long(jObj.getString("partyId"));
			Long stateId = new Long(jObj.getString("stateId"));
			
			Long countryId = 1l;
						
			Long electionScope = staticDataService.getElectionScopeForAElection(stateId, elecType, countryId);
			if(electionScope != null){
				electionYears = staticDataService.getElectionIdsAndYearsByElectionScope(electionScope,partyId);
			}
			electionYears.add(0,new SelectOptionVO(0L,"Select Year"));
						
		}
		return Action.SUCCESS;
	}
    
    public String getPartysInAState(){
		
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
		
		Long stateID = new Long(jObj.getString("stateId"));
		if(!stateID.equals(0L))
		partiesList = staticDataService.getStaticPartiesListForAState(stateID);
		Collections.sort(partiesList);
		partiesList.add(0, new SelectOptionVO(0L,"Select Party"));
	
	 return Action.SUCCESS;
	}
    
    
}
