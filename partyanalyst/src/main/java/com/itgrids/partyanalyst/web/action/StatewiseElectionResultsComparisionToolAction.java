package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
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

import com.itgrids.partyanalyst.dto.AlliancePartyDistrictResultsVO;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyPositionsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionsInDistrictVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IElectionReportService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class StatewiseElectionResultsComparisionToolAction extends ActionSupport implements ServletRequestAware,ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(StatewiseElectionResultsComparisionToolAction.class);
	private HttpServletRequest request;
	private String electionId;
	private String stateID;
	private String electionType;
	private String stateName;
	private String currentElectionyear;
	private String selectedElectionYear;
	private ElectionResultsReportVO electionCompleteDetailsVO;
	private IElectionReportService electionReportService;
	private String task = null;
	JSONObject jObj = null;
	private ServletContext context;
	private HttpSession session;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}
	
	public ServletContext getContext() {
		return context;
	}
	public String getElectionId() {
		return electionId;
	}

	public void setElectionId(String electionId) {
		this.electionId = electionId;
	}

	public String getStateID() {
		return stateID;
	}

	public void setStateID(String stateID) {
		this.stateID = stateID;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCurrentElectionyear() {
		return currentElectionyear;
	}

	public void setCurrentElectionyear(String currentElectionyear) {
		this.currentElectionyear = currentElectionyear;
	}

	public String getSelectedElectionYear() {
		return selectedElectionYear;
	}

	public void setSelectedElectionYear(String selectedElectionYear) {
		this.selectedElectionYear = selectedElectionYear;
	}

	public ElectionResultsReportVO getElectionCompleteDetailsVO() {
		return electionCompleteDetailsVO;
	}

	public void setElectionCompleteDetailsVO(
			ElectionResultsReportVO electionCompleteDetailsVO) {
		this.electionCompleteDetailsVO = electionCompleteDetailsVO;
	}

	public IElectionReportService getElectionReportService() {
		return electionReportService;
	}

	public void setElectionReportService(
			IElectionReportService electionReportService) {
		this.electionReportService = electionReportService;
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

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public String execute () throws Exception 
	{
		return Action.SUCCESS;		
	}	
	
	public String ajaxCallHandler()
	{
		String cPath = request.getContextPath();
		session=request.getSession();
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getSelectedYearElectionResults"))
		{
			electionCompleteDetailsVO = new ElectionResultsReportVO();
			Long stateId = Long.valueOf(jObj.getString("stateID"));
			String electionType = jObj.getString("electionType");
			String year = jObj.getString("year");
			Long electionId = jObj.getLong("electionId");
			electionCompleteDetailsVO = electionReportService.getBasicResultsForAnElection(electionType, year,stateId,IConstants.VOTES_PERCENT_MARGIN,null,electionId);
			
			try{
			//charts for alliance parties state level
			if(electionCompleteDetailsVO.getElectionBasicResultsVO().getAlliancePartiesList() != null && electionCompleteDetailsVO.getElectionBasicResultsVO().getAlliancePartiesList().size() > 0){
				LOG.info("0");
				for(AlliancePartyResultsVO alliancParties:electionCompleteDetailsVO.getElectionBasicResultsVO().getAlliancePartiesList()){
					String chartName = createLineChartForAlliancParties(alliancParties);
					if(chartName != null)
					alliancParties.setChartForPartyResults(chartName);
				}
				
			}
			
			//state Level results line chart
			if(electionCompleteDetailsVO.getElectionBasicResultsVO().getAllPartiesResults() != null && electionCompleteDetailsVO.getElectionBasicResultsVO().getAllPartiesResults().size() > 0){
		     LOG.info("1");
				String stateLevelLineChart = createLineChartForStateLevelResults(electionCompleteDetailsVO.getElectionBasicResultsVO().getAllPartiesResults(),"With_Alliance","All Parties Election Results With Alliance Parties");
			electionCompleteDetailsVO.setStatewiseResultsLineChartName(stateLevelLineChart);
			}
			
			//stateLevel results Line chart for parties without alliance
			if(electionCompleteDetailsVO.getElectionBasicResultsVO().getAllPartiesResultsWithoutGroupingOfAllianc() != null && electionCompleteDetailsVO.getElectionBasicResultsVO().getAllPartiesResultsWithoutGroupingOfAllianc().size() > 0){
				
				String stateLevelLineChartWithoutAllianc = createLineChartForStateLevelResults(electionCompleteDetailsVO.getElectionBasicResultsVO().getAllPartiesResultsWithoutGroupingOfAllianc(),"Without_Alliance","All Parties Election Results Without Grouping Alliance Parties");
			electionCompleteDetailsVO.setStateLevelLineChartWithoutAllianc(stateLevelLineChartWithoutAllianc);
			}
			
			//state level chart
			String partyResultsChartId = electionCompleteDetailsVO.getElectionType().concat(electionCompleteDetailsVO.getElectionYear()).concat("Election_Results").concat("BarChart");
	 		String partyResultsChartName = "partyElectionResults_" + partyResultsChartId + session.getId() +".png";
	 		String partyResultsChartPath = "";
			
			if(cPath.contains("PartyAnalyst"))
				partyResultsChartPath = context.getRealPath("/") + "charts\\" + partyResultsChartName;
			else
	         partyResultsChartPath = IWebConstants.CHART_URL_IN_SERVER + partyResultsChartName;
	 		
	 		ChartProducer.create3DBarChart("Party","Seats",createDatasetForPartyResults(electionCompleteDetailsVO.getElectionBasicResultsVO().getAllPartiesResults()), partyResultsChartPath);
		    request.setAttribute("partyResultsChartName1", partyResultsChartName);
			session.setAttribute("partyResultsChartName1", partyResultsChartName);
			electionCompleteDetailsVO.setStatewiseElectionResultsChartName(partyResultsChartName);
			LOG.info("3");
			}catch(Exception ex){
				ex.printStackTrace();
				LOG.debug("Exception Raised :" + ex);
			}
		}
		return Action.SUCCESS;
	}
	
	/*
	 * method to create line chart for alliancParties
	 */
	public String createLineChartForAlliancParties(AlliancePartyResultsVO alliancParties){
		String cPath = request.getContextPath();
		LOG.debug("Inside createLineChartForAlliancParties...");
		LOG.info("1.1");
		String chartName = null;
		try{
		String alliancePartiesChartId = electionCompleteDetailsVO.getElectionType().concat(electionCompleteDetailsVO.getElectionYear()).concat(alliancParties.getAllianceGroupName()).concat("Election_Results").concat("LineChart");
 		String alliancePartiesChartName = "alliancPartyElectionResults_" + alliancePartiesChartId + session.getId() +".png";
 		 String alliancePartiesChartPath="";
		
		if(cPath.contains("PartyAnalyst"))
			alliancePartiesChartPath = context.getRealPath("/") + "charts\\" + alliancePartiesChartName;
		else
			alliancePartiesChartPath = IWebConstants.CHART_URL_IN_SERVER + alliancePartiesChartName;
 		
        ChartProducer.createLineChart("","","Seats", createDataSetForAlliancPartyOverallResults(alliancParties.getPartiesInAlliance(),"BarChart"), alliancePartiesChartPath,300,600, null,true);
	    request.setAttribute("alliancePartiesChartName1", alliancePartiesChartName);
		session.setAttribute("alliancePartiesChartName1", alliancePartiesChartName);
		chartName = alliancePartiesChartName;
		}catch(Exception ex){
			ex.printStackTrace();
			LOG.debug("Exception Raised :" + ex);
		}
		LOG.debug("Inside createLineChartForAlliancParties... Chart Name :" + chartName);
		return chartName;
	}
	
	/*
	 * creating line chart for overall results
	 */
	public String createLineChartForStateLevelResults(List<PartyPositionsVO> allPartiesResults,String chartType,String title){
		String cPath = request.getContextPath();
		LOG.debug("Inside createLineChartForStateLevelResults...");
		
		String chartName = null;
		try{
			String allPartiesChartId = electionCompleteDetailsVO.getElectionType().concat(electionCompleteDetailsVO.getElectionYear()).concat("Overall").concat("Election_Results").concat("All_Parties_LineChart").concat(chartType);
	 		String allPartiesChartName = "alliancPartyElectionResults_" + allPartiesChartId + session.getId() +".png";
	 		String allPartiesChartPath  = "";
			
			if(cPath.contains("PartyAnalyst"))
				allPartiesChartPath = context.getRealPath("/") + "charts\\" + allPartiesChartName;
			else
				allPartiesChartPath = IWebConstants.CHART_URL_IN_SERVER + allPartiesChartName;
	        ChartProducer.createLineChart(title,"","Seats", createDataSetForAlliancPartyOverallResults(allPartiesResults,"LineChart"), allPartiesChartPath,300,600, null,true);
		    request.setAttribute("allPartiesChartName", allPartiesChartName);
			session.setAttribute("allPartiesChartName", allPartiesChartName);
			chartName = allPartiesChartName;
			
		}catch(Exception ex){
			ex.printStackTrace();
			LOG.debug("Exception Raised :" + ex);
		}
		LOG.debug("Inside createLineChartForStateLevelResults... Chart Name :" + chartName);
		return chartName;
	}
	
	/*
     * creating dataset for complete results
     */
	private CategoryDataset createDatasetForPartyResults(List<PartyPositionsVO> allPartiesResults) {
		
		LOG.debug("Inside createDatasetForPartyResults...");
		LOG.info("1.3");
		  int i=0;
		  final String category1 = "Seats Won";
		  final String category2 = "2nd Pos";  
		  final String category3 = "3rd Pos";
		  final String category4 = "4th Pos";
		  final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	      for(PartyPositionsVO result:allPartiesResults){
	    	if(i==8)
	    	break;
	      	final String series =  result.getPartyName();
	      	dataset.addValue(new Double(result.getTotalSeatsWon()), category1, series);
	      	dataset.addValue(new Double(result.getSecondPosWon()), category2, series);
	      	dataset.addValue(new Double(result.getThirdPosWon()), category3, series);
	      	dataset.addValue(new Double(result.getFourthPosWon()), category4, series);
	      	i++;
	      }
	      return dataset;
    }
	
	/*
	 * creating dataset for alliancParties overall results
	 */
	private CategoryDataset createDataSetForAlliancPartyOverallResults(List<PartyPositionsVO> alliancPartiesResults,String chartType){
		
		LOG.debug("Inside createDataSetForAlliancPartyOverallResults...");
	
		final String series1 = "Seats Won";
		final String series2 = "2nd Pos";
		final String series3 = "3rd Pos";
		final String series4 = "4th Pos";
		final String series5 = "Nth Pos";
		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(PartyPositionsVO parties:alliancPartiesResults){
			final String category = parties.getPartyName();
			if(chartType.equals("LineChart") && category.equals("IND"))
			continue;
			dataset.addValue(Long.valueOf(parties.getTotalSeatsWon()), category, series1);
			dataset.addValue(Long.valueOf(parties.getSecondPosWon()), category, series2);
			dataset.addValue(Long.valueOf(parties.getThirdPosWon()), category, series3);
			dataset.addValue(Long.valueOf(parties.getFourthPosWon()), category, series4);
			dataset.addValue(Long.valueOf(parties.getNthPosWon()), category, series5);
		}
		return dataset;
	}

}
