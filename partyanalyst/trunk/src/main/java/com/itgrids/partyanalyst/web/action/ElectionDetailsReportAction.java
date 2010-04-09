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

import com.itgrids.partyanalyst.dto.DistrictWisePartyPositionsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionsInDistrictVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyWiseResultVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IElectionReportService;
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
	private String stateName;
	private String year;
	private ServletContext context;
	private IElectionReportService electionReportService;
	private HttpSession session;
	
	
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
				
				//state level chart
				String partyResultsChartId = electionCompleteDetailsVO.getElectionType().concat(electionCompleteDetailsVO.getElectionYear()).concat("Election_Results").concat("BarChart");
		 		String partyResultsChartName = "partyElectionResults_" + partyResultsChartId + session.getId() +".png";
		        String partyResultsChartPath = context.getRealPath("/") + "charts\\" + partyResultsChartName;
		 		
		 		ChartProducer.create3DBarChart("Party","Seats",createDatasetForPartyResults(electionCompleteDetailsVO.getElectionBasicResultsVO().getAllPartiesResults()), partyResultsChartPath);
			    request.setAttribute("partyResultsChartName", partyResultsChartName);
				session.setAttribute("partyResultsChartName", partyResultsChartName);
				electionCompleteDetailsVO.setStatewiseElectionResultsChartName(partyResultsChartName);	
				
				//district level results chart
				String partyDistrictResultsChartId = electionCompleteDetailsVO.getElectionType().concat(electionCompleteDetailsVO.getElectionYear()).concat("Election_Results_Districtwise").concat("LineChart");
		 		String partyDistrictResultsChartName = "partyElectionResults_" + partyDistrictResultsChartId + session.getId() +".png";
		        String partyDistrictResultsChartPath = context.getRealPath("/") + "charts\\" + partyDistrictResultsChartName;
		 		
		 		ChartProducer.createLineChart("","","Percentages", createDataSetForPartyDistrictwiseResults(electionCompleteDetailsVO.getElectionResultsInDistricts().getAllPartiesResults()), partyDistrictResultsChartPath,300,880);
		        request.setAttribute("partyDistrictResultsChartName", partyDistrictResultsChartName);
				session.setAttribute("partyDistrictResultsChartName", partyDistrictResultsChartName);
			}	
		}	
			return Action.SUCCESS;
		
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
		

}
