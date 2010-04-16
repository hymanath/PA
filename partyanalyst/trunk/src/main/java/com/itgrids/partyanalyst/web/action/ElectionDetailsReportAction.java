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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AlliancePartyDistrictResultsVO;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyPositionsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionsInDistrictVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyWiseResultVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IElectionReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ElectionDetailsReportAction extends ActionSupport implements ServletRequestAware,ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ElectionDetailsReportAction.class);
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;
	private ElectionResultsReportVO electionCompleteDetailsVO;
	private String electionId;
	private String stateID;
	private String electionType;
	private Long electionTypeId;
	private String stateName;
	private String year;
	private ServletContext context;
	private IElectionReportService electionReportService;
	private IStaticDataService staticDataService; 
	private HttpSession session;
	private List<SelectOptionVO> electionYears;
	private List<SelectOptionVO> partiesList;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}	
	
	public ElectionResultsReportVO getElectionCompleteDetailsVO() {
		return electionCompleteDetailsVO;
	}

	public void setElectionCompleteDetailsVO(
			ElectionResultsReportVO electionCompleteDetailsVO) {
		this.electionCompleteDetailsVO = electionCompleteDetailsVO;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}	
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static Logger getLog() {
		return log;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public ServletContext getContext() {
		return context;
	}

	public IElectionReportService getElectionReportService() {
		return electionReportService;
	}

	public void setElectionReportService(
			IElectionReportService electionReportService) {
		this.electionReportService = electionReportService;
	}	

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	public Long getElectionTypeId() {
		return electionTypeId;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setElectionYears(List<SelectOptionVO> electionYears) {
		this.electionYears = electionYears;
	}

	public List<SelectOptionVO> getElectionYears() {
		return electionYears;
	}

	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}

	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}

	public String execute () throws Exception 
	{
	
		if(log.isDebugEnabled()){
			log.debug("Entered in to Election Details Action");			
		}
		if(log.isDebugEnabled())
			log.debug("stateId:" + stateID);
		
		if(log.isDebugEnabled())
			log.debug("elctionId:" + electionId);		
				
		if(log.isDebugEnabled())
			log.debug("electionType:" + electionType);
		
		if(log.isDebugEnabled())
			log.debug("electionTypeId:" + electionTypeId);
		electionYears = new ArrayList<SelectOptionVO>();
		partiesList = new ArrayList<SelectOptionVO>();
		if(electionType.equals(IConstants.ASSEMBLY_ELECTION_TYPE) || electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
		{
			try{
				electionYears = staticDataService.getElectionIdsAndYearsInfo(electionTypeId,new Long(stateID));
				electionYears.add(0, new SelectOptionVO(0l,"Select Year"));
				partiesList = staticDataService.getStaticParties();
				partiesList.add(0, new SelectOptionVO(0l,"Select A Party"));
			}catch(Exception e){
				partiesList = null;
				electionYears = null;
				log.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
			}	
		} else 	if(electionType.equals(IConstants.ZPTC) || electionType.equals(IConstants.MPTC))
		{
			try{
				electionYears = staticDataService.getAllElectionYearsForATeshil(electionTypeId);
				electionYears.add(0, new SelectOptionVO(0l,"Select Year"));
				partiesList = staticDataService.getAllPartiesForAnElectionYear(year, electionType);	
				partiesList.add(0, new SelectOptionVO(0l,"Select A Party"));
			}catch(Exception e){
				partiesList = null;
				electionYears = null;
				log.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
			}
		}		
		
		return Action.SUCCESS;		
	}
	
	public String ajaxCallHandler()
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
		if(jObj.getString("task").equalsIgnoreCase("electionsBasicInfo"))
		{
			electionCompleteDetailsVO = new ElectionResultsReportVO();
			String electionType = jObj.getString("electionType");			
		} if(jObj.getString("task").equalsIgnoreCase("getResultForAnElection"))
		{
			session=request.getSession();
			electionCompleteDetailsVO = new ElectionResultsReportVO();
			Long stateId = new Long(jObj.getString("stateID"));
			String electionType = jObj.getString("electionType");
			String year = jObj.getString("year");
			
			electionCompleteDetailsVO = electionReportService.getBasicResultsForAnElection(electionType, year,stateId,IConstants.VOTES_PERCENT_MARGIN);
			if(electionCompleteDetailsVO != null){
				if(electionCompleteDetailsVO.getResultStatus().getResultCode() == ResultCodeMapper.FAILURE)
					return Action.ERROR;
				
				try{
				//charts for alliance parties state level
				if(electionCompleteDetailsVO.getElectionBasicResultsVO().getAlliancePartiesList() != null && electionCompleteDetailsVO.getElectionBasicResultsVO().getAlliancePartiesList().size() > 0){
					for(AlliancePartyResultsVO alliancParties:electionCompleteDetailsVO.getElectionBasicResultsVO().getAlliancePartiesList()){
						String chartName = createLineChartForAlliancParties(alliancParties);
						if(chartName != null)
						alliancParties.setChartForPartyResults(chartName);
					}
					
				}
				
				//state Level results line chart
				if(electionCompleteDetailsVO.getElectionBasicResultsVO().getAllPartiesResults() != null && electionCompleteDetailsVO.getElectionBasicResultsVO().getAllPartiesResults().size() > 0){
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
		        String partyResultsChartPath = context.getRealPath("/") + "charts\\" + partyResultsChartName;
		 		
		 		ChartProducer.create3DBarChart("Party","Seats",createDatasetForPartyResults(electionCompleteDetailsVO.getElectionBasicResultsVO().getAllPartiesResults()), partyResultsChartPath);
			    request.setAttribute("partyResultsChartName", partyResultsChartName);
				session.setAttribute("partyResultsChartName", partyResultsChartName);
				electionCompleteDetailsVO.setStatewiseElectionResultsChartName(partyResultsChartName);	
				
				//district level results chart with alliance parties grouped
				if(electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResults() != null && electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResults().size() > 0){
				String partyResultsDistrictWise = createLineChartForPartiesWithDistrictLevelResults(electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResults(),"With_Alliance","All Parties District Wise Election Results With Alliance Parties");
				electionCompleteDetailsVO.setDistrictWiseElecResultsChartName(partyResultsDistrictWise);
				}
				
				//district level results line chart for parties without alliance
				if(electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResultsWithoutGroupingOfAllianc() != null && electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResultsWithoutGroupingOfAllianc().size() > 0){
				String partyResultsDistrictLevelChartWithoutAllianc = createLineChartForPartiesWithDistrictLevelResults(electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResultsWithoutGroupingOfAllianc(),"Without_Alliance","All Parties District Wise Election Results Without Grouping Alliance Parties");
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
				
				}catch(Exception ex){
					ex.printStackTrace();
					log.debug("Exception Raised :" + ex);
				}
			}	
		}	
			return Action.SUCCESS;
		
	}
	
	/*
	 * method to create line chart for alliancParties
	 */
	public String createLineChartForAlliancParties(AlliancePartyResultsVO alliancParties){
		String chartName = null;
		try{
		String alliancePartiesChartId = electionCompleteDetailsVO.getElectionType().concat(electionCompleteDetailsVO.getElectionYear()).concat(alliancParties.getAllianceGroupName()).concat("Election_Results").concat("LineChart");
 		String alliancePartiesChartName = "alliancPartyElectionResults_" + alliancePartiesChartId + session.getId() +".png";
        String alliancePartiesChartPath = context.getRealPath("/") + "charts\\" + alliancePartiesChartName;
 		
        ChartProducer.createLineChart("","","Seats", createDataSetForAlliancPartyOverallResults(alliancParties.getPartiesInAlliance(),"BarChart"), alliancePartiesChartPath,300,600);
	    request.setAttribute("alliancePartiesChartName", alliancePartiesChartName);
		session.setAttribute("alliancePartiesChartName", alliancePartiesChartName);
		chartName = alliancePartiesChartName;
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
		try{
		String alliancePartiesChartId = electionCompleteDetailsVO.getElectionType().concat(electionCompleteDetailsVO.getElectionYear()).concat(alliancParties.getAllianceGroupName()).concat("Election_Results_DistrictWise").concat("LineChart");
 		String alliancePartiesChartName = "alliancPartyElectionResultsDistrictWise_" + alliancePartiesChartId + session.getId() +".png";
        String alliancePartiesChartPath = context.getRealPath("/") + "charts\\" + alliancePartiesChartName;
 		
        ChartProducer.createLineChart("","","Seats", createDataSetForPartyDistrictwiseResults(alliancParties.getPartiesInAlliance()), alliancePartiesChartPath,300,800);
	    request.setAttribute("alliancePartiesChartName", alliancePartiesChartName);
		session.setAttribute("alliancePartiesChartName", alliancePartiesChartName);
		chartName = alliancePartiesChartName;
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
		return chartName;
	}
	
	/*
	 * method to create line chart for for district level overall results for parties
	 */
	public String createLineChartForPartiesWithDistrictLevelResults(List<DistrictWisePartyPositionsVO> allPartiesResults,String chartType,String title){
		String chartName = null;
		try{
			String partyDistrictResultsChartId = electionCompleteDetailsVO.getElectionType().concat(electionCompleteDetailsVO.getElectionYear()).concat("Election_Results_Districtwise").concat("LineChart").concat(chartType);
	 		String partyDistrictResultsChartName = "partyDistrictResults_" + partyDistrictResultsChartId + session.getId() +".png";
	        String partyDistrictResultsChartPath = context.getRealPath("/") + "charts\\" + partyDistrictResultsChartName;
	 		
	 		ChartProducer.createLineChart(title,"","Votes Percentage", createDataSetForPartyDistrictwiseResults(allPartiesResults), partyDistrictResultsChartPath,300,880);
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
	 * creating line chart for overall results
	 */
	public String createLineChartForStateLevelResults(List<PartyPositionsVO> allPartiesResults,String chartType,String title){
		String chartName = null;
		try{
			String allPartiesChartId = electionCompleteDetailsVO.getElectionType().concat(electionCompleteDetailsVO.getElectionYear()).concat("Overall").concat("Election_Results").concat("All_Parties_LineChart").concat(chartType);
	 		String allPartiesChartName = "alliancPartyElectionResults_" + allPartiesChartId + session.getId() +".png";
	        String allPartiesChartPath = context.getRealPath("/") + "charts\\" + allPartiesChartName;
	 		
	        ChartProducer.createLineChart(title,"","Seats", createDataSetForAlliancPartyOverallResults(allPartiesResults,"LineChart"), allPartiesChartPath,300,800);
		    request.setAttribute("allPartiesChartName", allPartiesChartName);
			session.setAttribute("allPartiesChartName", allPartiesChartName);
			chartName = allPartiesChartName;
			
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
		
		return chartName;
	}
		
	    /*
	     * creating dataset for complete results
	     */
		private CategoryDataset createDatasetForPartyResults(List<PartyPositionsVO> allPartiesResults) {
			
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
		
		/*
		 * creating dataset for alliancParties overall results
		 */
		private CategoryDataset createDataSetForAlliancPartyOverallResults(List<PartyPositionsVO> alliancPartiesResults,String chartType){
		
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
				dataset.addValue(new Long(parties.getTotalSeatsWon()), category, series1);
				dataset.addValue(new Long(parties.getSecondPosWon()), category, series2);
				dataset.addValue(new Long(parties.getThirdPosWon()), category, series3);
				dataset.addValue(new Long(parties.getFourthPosWon()), category, series4);
				dataset.addValue(new Long(parties.getNthPosWon()), category, series5);
			}
			return dataset;
		}
		

}
