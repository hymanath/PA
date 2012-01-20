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
import com.itgrids.partyanalyst.dto.DistrictWisePartyPositionsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionsInDistrictVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IElectionReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DistrictwiseElectionResultsAnalysysForElectionReportAction extends ActionSupport implements ServletRequestAware,ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DistrictwiseElectionResultsAnalysysForElectionReportAction.class);
	private HttpServletRequest request;
	private String electionId;
	private String stateID;
	private String electionType;
	private String stateName;
	private String currentElectionyear;
	private String selectedElectionYear;
	private ElectionResultsReportVO electionCompleteDetailsVO;
	private IElectionReportService electionReportService;
	private EntitlementsHelper entitlementsHelper;
	private String task = null;
	JSONObject jObj = null;
	private ServletContext context;
	private HttpSession session;
    private String chartProducerURL="/var/www/vsites/partyanalyst.com/httpdocs/charts/";
		
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;			
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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
	
	public ServletContext getContext() {
		return context;
	}

	/*public void setContext(ServletContext context) {
		this.context = context;
	}*/
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}

	public String execute () throws Exception 
	{
		HttpSession session = request.getSession();
		session = request.getSession();
		
		if(session.getAttribute(IConstants.USER) != null && !entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ELECTION_RESULT_REPORT_DETAILED_ANALYSIS))
			return ERROR;
		
		if(session.getAttribute(IConstants.USER) == null && !entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ELECTION_RESULT_REPORT_DETAILED_ANALYSIS))
			return ERROR;
		
		return Action.SUCCESS;
		
	}
	
	public String ajaxCallHandler()
	{
		session=request.getSession();
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
		if(jObj.getString("task").equalsIgnoreCase("getSelectedYearElectionResults"))
		{
			electionCompleteDetailsVO = new ElectionResultsReportVO();
			Long stateId = new Long(jObj.getString("stateID"));
			String electionType = jObj.getString("electionType");
			String year = jObj.getString("year");
			
			electionCompleteDetailsVO = electionReportService.getBasicResultsForAnElection(electionType, year,stateId,IConstants.VOTES_PERCENT_MARGIN);
			//district level results chart with alliance parties grouped
			String title1 = "";
			if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
				title1 = "All Parties State Wise Election Results With Alliance Parties";
				else
					title1 = "All Parties District Wise Election Results With Alliance Parties";
			if(electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResults() != null && electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResults().size() > 0){
			String partyResultsDistrictWise = createLineChartForPartiesWithDistrictLevelResults(electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResults(),"With_Alliance",title1);
			electionCompleteDetailsVO.setDistrictWiseElecResultsChartName(partyResultsDistrictWise);
			}
			
			//district level results line chart for parties without alliance
			String title2 = "";
			if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
				title2 = "All Parties State Wise Election Results Without Grouping Alliance Parties";
				else
				title2 = "All Parties District Wise Election Results Without Grouping Alliance Parties";
			if(electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResultsWithoutGroupingOfAllianc() != null && electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResultsWithoutGroupingOfAllianc().size() > 0){
			String partyResultsDistrictLevelChartWithoutAllianc = createLineChartForPartiesWithDistrictLevelResults(electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResultsWithoutGroupingOfAllianc(),"Without_Alliance",title2);
			electionCompleteDetailsVO.setPartyResultsDistrictLevelChartWithoutAllianc(partyResultsDistrictLevelChartWithoutAllianc);
			}
			
			//charts for alliance parties district level
			if(electionCompleteDetailsVO.getElectionResultsInDistricts().getAlliancePartiesList() != null && electionCompleteDetailsVO.getElectionResultsInDistricts().getAlliancePartiesList().size() > 0){
				for(AlliancePartyDistrictResultsVO alliancParties:electionCompleteDetailsVO.getElectionResultsInDistricts().getAlliancePartiesList()){
					String chartName = createLineChartForAlliancPartiesForDistrictLevel(alliancParties);
					if(chartName != null)
					alliancParties.setAlliancePartiesChart(chartName);
				}
			}
		}
	
		return Action.SUCCESS;
	}
	
	/*
	 * method to create line chart for for district level overall results for parties
	 */
	public String createLineChartForPartiesWithDistrictLevelResults(List<DistrictWisePartyPositionsVO> allPartiesResults,String chartType,String title){
		String chartName = null;
		String cPath = request.getContextPath();
		String partyDistrictResultsChartPath;
		try{
			String partyDistrictResultsChartId = electionCompleteDetailsVO.getElectionType().concat(electionCompleteDetailsVO.getElectionYear()).concat("Election_Results_Districtwise").concat("LineChart").concat(chartType);
	 		String partyDistrictResultsChartName = "partyDistrictResults_" + partyDistrictResultsChartId + session.getId() +".png";
	 		if(cPath.contains("PartyAnalyst"))
	 			 partyDistrictResultsChartPath = context.getRealPath("/") + "charts\\" + partyDistrictResultsChartName;
	 		else
	 		     partyDistrictResultsChartPath = chartProducerURL + partyDistrictResultsChartName;
	 		ChartProducer.createLineChart(title,"","Votes Percentage", createDataSetForPartyDistrictwiseResults(allPartiesResults), partyDistrictResultsChartPath,300,880, null,true);
	        request.setAttribute("partyDistrictResultsChartName", partyDistrictResultsChartName);
			session.setAttribute("partyDistrictResultsChartName", partyDistrictResultsChartName);
			
			chartName = partyDistrictResultsChartName;
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
	 return chartName;
	}
	
	/*
	 * method to create line chart for alliancParties for district level
	 */
	public String createLineChartForAlliancPartiesForDistrictLevel(AlliancePartyDistrictResultsVO alliancParties){
		String chartName = null;
		 String alliancePartiesChartPath;
		String cPath = request.getContextPath();
		try{
		String alliancePartiesChartId = electionCompleteDetailsVO.getElectionType().concat(electionCompleteDetailsVO.getElectionYear()).concat(alliancParties.getAllianceGroupName()).concat("Election_Results_DistrictWise").concat("LineChart");
 		String alliancePartiesChartName = "alliancPartyElectionResultsDistrictWise_" + alliancePartiesChartId + session.getId() +".png";
 		if(cPath.contains("PartyAnalyst"))
 			alliancePartiesChartPath = context.getRealPath("/") + "charts\\" + alliancePartiesChartName;
 		else
 			alliancePartiesChartPath = chartProducerURL + alliancePartiesChartName;
 		
        ChartProducer.createLineChart("","","Seats", createDataSetForPartyDistrictwiseResults(alliancParties.getPartiesInAlliance()), alliancePartiesChartPath,300,800, null,true);
	    request.setAttribute("alliancePartiesChartName1", alliancePartiesChartName);
		session.setAttribute("alliancePartiesChartName1", alliancePartiesChartName);
		chartName = alliancePartiesChartName;
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
		return chartName;
	}
	
	/*
	 * creating dataset for districtwise results
	 */
	private CategoryDataset createDataSetForPartyDistrictwiseResults(List<DistrictWisePartyPositionsVO> allPartiesResults){
		int i=0;
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(DistrictWisePartyPositionsVO parties:allPartiesResults){
			if(i==10)
			break;
			final String category = parties.getPartyName();
			for(PartyPositionsInDistrictVO districtResults:parties.getPartyResultsInDistricts()){
				dataset.addValue(new Double(districtResults.getCompleteVotesPercent()), category, districtResults.getDistrictName());
			}
			i++;
		}
		return dataset;
	}	
	
}
