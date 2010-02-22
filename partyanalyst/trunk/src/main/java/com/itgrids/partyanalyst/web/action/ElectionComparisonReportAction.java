package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.googlecode.jsonplugin.annotations.JSON;
import com.itgrids.partyanalyst.dto.ComparedReportVO;
import com.itgrids.partyanalyst.dto.CompleteResultsVO;
import com.itgrids.partyanalyst.dto.ElectionComparisonReportVO;
import com.itgrids.partyanalyst.dto.ElectionComparisonResultVO;
import com.itgrids.partyanalyst.dto.ElectionsComparisonVO;
import com.itgrids.partyanalyst.dto.PartyPositionDisplayVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyResultsPercentageVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IElectionComparisonReportService;
import com.itgrids.partyanalyst.service.IElectionsComparisonService;
import com.itgrids.partyanalyst.service.IPartyService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ElectionComparisonReportAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware,ServletContextAware {

	/**
	 * 
	 */
	private String electionType;
	private String state;
	private String party;
	private String electionYears1;
	private String electionYears2;
	private String allianceCheck;
	private HttpSession session;
	private ServletContext context;
	private ComparedReportVO comparedResultsVO;
	private List<PartyPositionDisplayVO> partyPositionDisplayVO;
	JSONObject jObj = null;
	
	private ElectionComparisonReportVO electionComparisonReportVO;
	private IElectionComparisonReportService electionComparisonReportService;
	private IPartyService partyService;
	
	public static final Logger logger = Logger.getLogger(ElectionComparisonReportAction.class);
	
	public String getAllianceCheck() {
		return allianceCheck;
	}

	public void setAllianceCheck(String allianceCheck) {
		this.allianceCheck = allianceCheck;
	}

	private static final long serialVersionUID = 1L;
	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getElectionYears1() {
		return electionYears1;
	}

	public void setElectionYears1(String electionYears1) {
		this.electionYears1 = electionYears1;
	}

	public String getElectionYears2() {
		return electionYears2;
	}

	public void setElectionYears2(String electionYears2) {
		this.electionYears2 = electionYears2;
	}

	private HttpServletRequest request;
	private HttpServletResponse response;
	
		
	@JSON (serialize= false )
	public ServletContext getContext() {
		return context;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}

	@JSON (serialize= false )
	public HttpSession getSession() {
		return session;
	}

	
	@JSON (serialize= false )
	public ComparedReportVO getComparedResultsVO() {
		return comparedResultsVO;
	}

	public void setComparedResultsVO(ComparedReportVO comparedResultsVO) {
		this.comparedResultsVO = comparedResultsVO;
	}

	public IPartyService getPartyService() {
		return partyService;
	}

	public void setPartyService(IPartyService partyService) {
		this.partyService = partyService;
	}

	@JSON (serialize= false )
	public ElectionComparisonReportVO getElectionComparisonReportVO() {
		return electionComparisonReportVO;
	}

	public void setElectionComparisonReportVO(
			ElectionComparisonReportVO electionComparisonReportVO) {
		this.electionComparisonReportVO = electionComparisonReportVO;
	}

	public IElectionComparisonReportService getElectionComparisonReportService() {
		return electionComparisonReportService;
	}

	public void setElectionComparisonReportService(
			IElectionComparisonReportService electionComparisonReportService) {
		this.electionComparisonReportService = electionComparisonReportService;
	}
	
	public List<PartyPositionDisplayVO> getPartyPositionDisplayVO() {
		return partyPositionDisplayVO;
	}

	public void setPartyPositionDisplayVO(
			List<PartyPositionDisplayVO> partyPositionDisplayVO) {
		this.partyPositionDisplayVO = partyPositionDisplayVO;
	}


	private ElectionsComparisonVO electionsComparisonVO = null;
	private List<ElectionComparisonResultVO> electionComparisonResultVO = null;
	private PartyResultsPercentageVO partyResultsPercentageForYear1;
	private PartyResultsPercentageVO partyResultsPercentageForYear2;
	public PartyResultsPercentageVO getPartyResultsPercentageForYear2() {
		return partyResultsPercentageForYear2;
	}

	public void setPartyResultsPercentageForYear2(
			PartyResultsPercentageVO partyResultsPercentageForYear2) {
		this.partyResultsPercentageForYear2 = partyResultsPercentageForYear2;
	}

	private IElectionsComparisonService electionsComparisonService;

	public ElectionsComparisonVO getElectionsComparisonVO() {
		return electionsComparisonVO;
	}

	public void setElectionsComparisonVO(ElectionsComparisonVO electionsComparisonVO) {
		this.electionsComparisonVO = electionsComparisonVO;
	}

	public PartyResultsPercentageVO getPartyResultsPercentageForYear1() {
		return partyResultsPercentageForYear1;
	}

	public void setPartyResultsPercentageForYear1(
			PartyResultsPercentageVO partyResultsPercentageForYear1) {
		this.partyResultsPercentageForYear1 = partyResultsPercentageForYear1;
	}

	public IElectionsComparisonService getElectionsComparisonService() {
		return electionsComparisonService;
	}

	public void setElectionsComparisonService(IElectionsComparisonService electionsComparisonService) {
		this.electionsComparisonService = electionsComparisonService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public List<ElectionComparisonResultVO> getElectionComparisonResultVO() {
		return electionComparisonResultVO;
	}

	public void setElectionComparisonResultVO(
			List<ElectionComparisonResultVO> electionComparisonResultVO) {
		this.electionComparisonResultVO = electionComparisonResultVO;
	}

	public String execute()
	{
		
		Boolean hasAlliances = new Boolean(allianceCheck);
		if(logger.isDebugEnabled())
			logger.debug("alliance-->" + allianceCheck);
		
		
			String previousYear = getElectionYears1();
			String presentYear = getElectionYears2();
			if(Long.parseLong(getElectionYears1()) > Long.parseLong(getElectionYears2())){
				previousYear = getElectionYears2();
				presentYear = getElectionYears1();
			}
		electionComparisonReportVO = electionComparisonReportService.getDistrictWiseElectionResultsForAParty(Long.parseLong(getElectionType()), Long.parseLong(getParty()),Long.parseLong(getState()), previousYear, presentYear, hasAlliances);
			
		        try{
				session = request.getSession();
				String chartId = state.concat(party).concat(electionType).concat(electionYears1).concat("Comparing with").concat(electionYears2).concat("BarChart");
				String barChartName = "electionsComparisonChart_" + chartId + session.getId()+".png";
		        String chartPath = context.getRealPath("/") + "charts\\" + barChartName;
		       
		        if(electionComparisonReportVO.getPositionsForYearOne() != null && electionComparisonReportVO.getPositionsForYearTwo() != null){
		        	
		        	PartyPositionsVO partyPositionsVOYear1 = getMainPartyPositions(electionComparisonReportVO.getPositionsForYearOne(),Long.parseLong(getParty()));
		        	PartyPositionsVO partyPositionsVOYear2 = getMainPartyPositions(electionComparisonReportVO.getPositionsForYearTwo(),Long.parseLong(getParty()));
		        	String label = partyPositionsVOYear1.getPartyName();
		        	label = label.concat("  Results").concat("  Graph");
		        	ChartProducer.createBarChart(label, "Years", "Seats", createDatasetForBarGraph(previousYear,presentYear,partyPositionsVOYear1,partyPositionsVOYear2), chartPath);
		        	request.setAttribute("barChartName", barChartName);
					session.setAttribute("barChartName", barChartName);
		         }		        
		       	}
				catch(Exception ex){
					ex.printStackTrace();
		        }
		
		return Action.SUCCESS;
	
	}
	
	public PartyPositionsVO getMainPartyPositions(List<PartyPositionsVO> positions,Long party){
		PartyPositionsVO partyPositions = null;
		for(PartyPositionsVO partyPos:positions){
		if(partyPos.getPartyId().equals(party))
		return partyPos;
		}
	return partyPositions;
	}
	
	@SuppressWarnings("unused")
	private CategoryDataset createDatasetForBarGraph(String yearOne,String yearTwo,PartyPositionsVO positionsForYearOne,PartyPositionsVO positionsForYearTwo){
		  // row keys...
		 final String category1 =  "Seats Won";
	     final String category2 = "2nd Pos";
	     final String category3 = "3rd Pos";
        		
        // create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 dataset.addValue(positionsForYearOne.getTotalSeatsWon(), category1, yearOne);
		 dataset.addValue(positionsForYearOne.getSecondPosWon(), category2, yearOne);
		 dataset.addValue(positionsForYearOne.getThirdPosWon(), category3, yearOne);
		 
		 dataset.addValue(positionsForYearTwo.getTotalSeatsWon(), category1, yearTwo);
		 dataset.addValue(positionsForYearTwo.getSecondPosWon(), category2, yearTwo);
		 dataset.addValue(positionsForYearTwo.getThirdPosWon(), category3, yearTwo);
		 
		 
	return dataset;
	}
	
	public String getComparedElectionResults(){
		
		String param=null;		
		param=request.getParameter("task");
		logger.debug("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		
		String elecYearOne = jObj.getString("firstYear");
		String elecYearTwo = jObj.getString("secondYear");
		String districtId  = jObj.getString("district");
		String stateId = jObj.getString("stateId");
		String partyId = jObj.getString("partyId");
		String electionType = jObj.getString("electionType");
		String hasAlliances = jObj.getString("hasAlliance");
		
		logger.debug("Year One:"+elecYearOne);
		logger.debug("Year Two:"+elecYearTwo);
		logger.debug("District Id:"+districtId);
		logger.debug("State Id:"+stateId);
		logger.debug("PartyId Id:"+partyId);
		logger.debug("Election Type:"+electionType);
		logger.debug("Has Alliances:"+hasAlliances);
		
		
		comparedResultsVO = electionComparisonReportService.getComparedElectionResults(new Long(electionType), new Long(stateId), new Long(partyId), elecYearOne, elecYearTwo, new Long(districtId), new Boolean(hasAlliances));
		
		return Action.SUCCESS;
	}
	
	
	public String getPartyPositionDetails(){
		
		String param=null;		
		param=request.getParameter("task");
		logger.debug("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String electionType = jObj.getString("electionType");
		String stateId = jObj.getString("stateId");
		String partyId = jObj.getString("party");
		String electionYear = jObj.getString("electionYear");
		String rank = jObj.getString("rank");
		String hasAllianc = jObj.getString("hasAlliance");
		Long districtId = new Long(0);
		
		partyPositionDisplayVO = partyService.getPartyPositionDetailsForAnElection(new Long(electionType),new Long(stateId),new Long(districtId),new Long(electionYear),new Long(partyId),new Boolean(hasAllianc).booleanValue(),new Integer(rank).intValue(),"State Level");
		
		return Action.SUCCESS;
	}

	
	
}
