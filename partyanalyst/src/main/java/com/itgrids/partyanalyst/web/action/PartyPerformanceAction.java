package com.itgrids.partyanalyst.web.action;


import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;


import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionDisplayVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotesMarginAnalysisVO;
import com.itgrids.partyanalyst.helper.Constants;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.helper.JasperProducer;
import com.itgrids.partyanalyst.service.IPartyService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.AnalysisReportService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyPerformanceAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ServletContextAware {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(PartyPerformanceAction.class);

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
	private ServletContext context;
	
	private IStaticDataService staticDataService;
	private IPartyService partyService;
	private PartyPerformanceReportVO reportVO;
	private List<SelectOptionVO> states;
	private List<SelectOptionVO> parties;
	private List<SelectOptionVO> districts;
	private List<String> years;
	private List<SelectOptionVO> levels;
	private boolean hasAllianceParties;
	private Long electionTypeId;
	JSONObject jObj = null;
	private String task = null;
	private List<PartyPositionDisplayVO> partyPositionDisplayVO;
	private Map statesYearList = new HashMap();
	private String reportTitle;
	private List<VotesMarginAnalysisVO> votesMarginAnalysisVO;
	private String party;
	private String state;
	private String year;
	private String electionType;
	private String electionYear;
	String electionTypeLiteral = IWebConstants.CHART_URL_IN_SERVER;
	private AnalysisReportService analysisReportService;
	private String partyNameHidden;
	private String stateNameHidden;
	private EntitlementsHelper entitlementsHelper;
	
	public String getStateNameHidden() {
		return stateNameHidden;
	}
	public void setStateNameHidden(String stateNameHidden) {
		this.stateNameHidden = stateNameHidden;
	}
	
	public String getPartyNameHidden() {
		return partyNameHidden;
	}
	public void setPartyNameHidden(String partyNameHidden) {
		this.partyNameHidden = partyNameHidden;
	}
	
	public AnalysisReportService getAnalysisReportService() {
		return analysisReportService;
	}
	public void setAnalysisReportService(AnalysisReportService analysisReportService) {
		this.analysisReportService = analysisReportService;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	
	public List<VotesMarginAnalysisVO> getVotesMarginAnalysisVO() {
		return votesMarginAnalysisVO;
	}
	public void setVotesMarginAnalysisVO(
			List<VotesMarginAnalysisVO> votesMarginAnalysisVO) {
		this.votesMarginAnalysisVO = votesMarginAnalysisVO;
	}
	
	public Map getStatesYearList() {
		return statesYearList;
	}
	public void setStatesYearList(Map statesYearList) {
		this.statesYearList = statesYearList;
	}
	public List<SelectOptionVO> getLevels() {
		return levels;
	}
	public void setLevels(List<SelectOptionVO> levels) {
		this.levels = levels;
	}
    
	public List<String> getYears() {
		return years;
	}
	public void setYears(List<String> years) {
		this.years = years;
	}
	
	public List<SelectOptionVO> getDistricts() {
		return districts;
	}
	public void setDistricts(List<SelectOptionVO> districts) {
		this.districts = districts;
	}

	public List<SelectOptionVO> getParties() {
		return parties;
	}
	public void setParties(List<SelectOptionVO> parties) {
		this.parties = parties;
	}
	
	public List<SelectOptionVO> getStates() {
        return states;
    }
	public void setStates(List<SelectOptionVO> states) {
		this.states = states;
	}
	
	public void setHasAllianceParties(boolean hasAllianceParties) {
		this.hasAllianceParties = hasAllianceParties;
	}
	
	//@JSON (serialize= false )
	public PartyPerformanceReportVO getStateData() {
		return reportVO;
	}
	public void setStateData(PartyPerformanceReportVO stateData) {
		this.reportVO = stateData;
	}
	
	public boolean isHasAllianceParties() {
		return hasAllianceParties;
	}
	//@JSON (serialize= false )
	public HttpServletRequest getRequest() {
		return request;
	}
	
	//@JSON (serialize= false )
	public Long getDefaultId() {
		return electionTypeId;
	}
	
	//@JSON (serialize= false )
	public HttpServletResponse getResponse() {
		return response;
	}
	
	//@JSON (serialize= false )
	public HttpSession getSession() {
		return session;
	}
	
	//@JSON (serialize= false )
	public ServletContext getContext() {
		return context;
	}
	
	//@JSON (serialize= false )
	public PartyPerformanceReportVO getReportVO() {
		return reportVO;
	}
	
	
	//@JSON (serialize= false )
	public IPartyService getPartyService() {
		return partyService;
	}
	public void setPartyService(IPartyService partyService) {
	    this.partyService = partyService;
	}
	
	//@JSON (serialize= false )   
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
    public void setStaticDataService(IStaticDataService staticDataService) {
        this.staticDataService = staticDataService;
    }
    
    public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
    
	public String getTask() {
		return task;
	}
	
	public void setTask(String task) {
		this.task = task;
	}
	
	public List<PartyPositionDisplayVO> getPartyPositionDisplayVO() {
		return partyPositionDisplayVO;
	}
	
	public void setPartyPositionDisplayVO(
			List<PartyPositionDisplayVO> partyPositionDisplayVO) {
		this.partyPositionDisplayVO = partyPositionDisplayVO;
	}	
	
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	
	public String getElectionYear() {
		return electionYear;
	}	
	
	public String getElectionTypeLiteral() {
		return electionTypeLiteral;
	}
	
	public void setElectionTypeLiteral(String electionTypeLiteral) {
		this.electionTypeLiteral = electionTypeLiteral;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	
	@SuppressWarnings("unchecked")
	public String execute() throws JRException {
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(regVO == null && !entitlements.contains(IConstants.PARTY_PERFORMANCE_REPORT)){
				return INPUT;
			}
			if(!entitlements.contains(IConstants.PARTY_PERFORMANCE_REPORT)){
				return ERROR;
			}
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_PERFORMANCE_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_PERFORMANCE_REPORT))
			return ERROR;*/
		
		LOG.debug("partyPerformance excute started...");
	
		Map<String, String> params = request.getParameterMap();
		String year = null;
		Long partyId = null;
		
		String param = null;
		electionTypeId = Long.valueOf(2);
		
		if(params.containsKey("type"))
			param = request.getParameter("type");
		
		if(param != null) 
			electionTypeId = Long.valueOf(param);
		
		//setStates(getStaticDataService().getStates(electionTypeId));
		List<SelectOptionVO> statesListDetails = new ArrayList<SelectOptionVO>();
		statesListDetails.add(new SelectOptionVO(0L,"Select"));
		Collections.sort(statesListDetails);
		statesListDetails.addAll(getStaticDataService().getParticipatedStatesForAnElectionType(electionTypeId));
		setStates(statesListDetails);
		
		setYears(getStaticDataService().getElectionYears(electionTypeId, false));
		setParties(getStaticDataService().getStaticParties());
		setDistricts(new ArrayList<SelectOptionVO>());
		setLevels(getReportLevels());

		if(year == null && partyId == null){
			year = getYears().iterator().next();
			partyId = getParties().get(0).getId();
		}

		boolean t = getStaticDataService().hasAlliances(year, electionTypeId, partyId);
		setHasAllianceParties(t);
		}
		return Action.SUCCESS;
	 
    }

	@SuppressWarnings("unchecked")
	public String getAlliances() throws JRException {
		String year = null;
		Long partyId = null;
		Long electionType = null;
		Map<String, String> params = request.getParameterMap();
		
		if(params.containsKey("allianceWith")){
			partyId = Long.valueOf(request.getParameter("allianceWith"));
			year = request.getParameter("year");
			electionYear = year;
			electionType = Long.valueOf(request.getParameter("elecType"));
		}
		
		boolean t = getStaticDataService().hasAlliances(year, electionType, partyId);
		setHasAllianceParties(t);
		
		return Action.SUCCESS;
	}
	private List<SelectOptionVO> getReportLevels() {
		List<SelectOptionVO>levels = new ArrayList<SelectOptionVO>();
	    levels.add(new SelectOptionVO(Long.valueOf(1), "State Level"));
	    levels.add(new SelectOptionVO(Long.valueOf(2), "District Level"));
	    
	    
		return levels;
	}
	
	private List<SelectOptionVO> getReportLevelsParliament() {
		List<SelectOptionVO>levels = new ArrayList<SelectOptionVO>();
	    levels.add(new SelectOptionVO(Long.valueOf(1), "State Level"));
	    levels.add(new SelectOptionVO(Long.valueOf(3), "Country Level"));
	    return levels;
	}

	public String getJSON() throws JRException {
		LOG.debug("partyPerformanceAjax action started...");
		return execute();
	}
	
	public String getDistrictsList(){
		String stateId = request.getParameter("stateId");
		/*
		districts = getStaticDataService().getDistricts(Long.valueOf(param));
		*/
		String electionYear= request.getParameter("electionYear");
		String partyId = request.getParameter("partyId");
		districts = staticDataService.getDistrictsByPartyNomination(Long.parseLong(stateId),Long.parseLong(partyId),Long.parseLong(electionYear));
		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getElectionTypeFilterData(){

		Map<String, String> params = request.getParameterMap();
		String param = null;
		electionTypeId = Long.valueOf(2);
		
		if(params.containsKey("type")){
			param = request.getParameter("type");
		}
		if(param != null) {
			electionTypeId = Long.valueOf(param);
		}
		
		List<SelectOptionVO> statesList = new ArrayList<SelectOptionVO>();
		statesList.add(new SelectOptionVO(0L,"Select"));
		statesList.addAll(getStaticDataService().getParticipatedStatesForAnElectionType(electionTypeId));
		Collections.sort(statesList);
		statesYearList.put("STATES", statesList);
		statesYearList.put("YEARS", staticDataService.getElectionYears(electionTypeId,false));
		
		if(electionTypeId.equals(Long.valueOf(1)))
			statesYearList.put("LEVELS", getReportLevelsParliament());
		else
			statesYearList.put("LEVELS", getReportLevels());
		
		return Action.SUCCESS;
	} 
	public String checkPartyPerformance(){
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(regVO == null && !entitlements.contains(IConstants.PARTY_PERFORMANCE_REPORT)){
				return INPUT;
			}
			if(!entitlements.contains(IConstants.PARTY_PERFORMANCE_REPORT)){
				return ERROR;
			}
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_PERFORMANCE_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_PERFORMANCE_REPORT))
			return ERROR;*/
		
		String cPath = request.getContextPath();
		
		LOG.debug("partyPerformanceReport action started...");
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String district = "0";
		String country =jObj.getString("country");
		String reportLevel = jObj.getString("reportLevel");
		String party = jObj.getString("party");
		String electionType = jObj.getString("electionType");
		String year = jObj.getString("year");
		String state = jObj.getString("state");
		Boolean alliances = Boolean.valueOf(jObj.getString("alliances"));
		
		if("2".equals(reportLevel)){
			district = jObj.getString("district");
		}
		LOG.debug("Report Level :: " + reportLevel);
		
		if(state == null)
			state="0";
		reportVO = getPartyService().getPartyPerformanceReport(state, district, party, year, electionType, country, 0, new BigDecimal(Constants.MAJAR_BRAND), new BigDecimal(Constants.MINOR_BRAND), alliances,reportLevel);
		hasAllianceParties = reportVO.getReportSuccessOrFailure();
		}
		return Action.SUCCESS;
	}
	//@JSON (serialize= false )   
	public String getReport() {
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(regVO == null && !entitlements.contains(IConstants.PARTY_PERFORMANCE_REPORT)){
				return INPUT;
			}
			if(!entitlements.contains(IConstants.PARTY_PERFORMANCE_REPORT)){
				return ERROR;
			}
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_PERFORMANCE_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_PERFORMANCE_REPORT))
			return ERROR;*/
		
		String cPath = request.getContextPath();
		
		LOG.debug("partyPerformanceReport action started...");
		String district = "0";
		String country = request.getParameter("country");
		String reportLevel = request.getParameter("1");
		String party = request.getParameter("party");
		String electionType = request.getParameter("electionType");
		String year = request.getParameter("year");
		String state = request.getParameter("state");
		Boolean alliances = Boolean.valueOf(request.getParameter("alliances"));
		
		if("2".equals(reportLevel)){
			district = request.getParameter("district");
		}
		LOG.debug("Report Level :: " + reportLevel);
		
		if(state == null)
			state="0";
		reportVO = getPartyService().getPartyPerformanceReport(state, district, party, year, electionType, country, 0, new BigDecimal(Constants.MAJAR_BRAND), new BigDecimal(Constants.MINOR_BRAND), alliances,reportLevel);
		reportVO.setElectionTypeId(Long.valueOf(electionType));
		reportVO.setStateId(Long.valueOf(state));
		reportVO.setPartyId(Long.valueOf(party));
		reportVO.setHasAlliances(alliances);
		reportVO.setReportLevel(reportLevel);
		
		if(district!=null)
			reportVO.setDistrictId(Long.valueOf(district));
		
		String reportLevelLiteral = "";
		String partyNameLiteral = reportVO.getParty();
		String reportLoc = "";
		
		if(Long.valueOf(electionType).equals(Long.valueOf(1)))
			electionTypeLiteral = "Parliament";
		else if(Long.valueOf(electionType).equals(Long.valueOf(2))){
			electionTypeLiteral = "Assembly";
			
		}
		if(Long.valueOf(reportLevel).equals(Long.valueOf(1))){
			reportLevelLiteral = "StateLevel";
			
			reportLoc = reportVO.getState();
		}
		else if(Long.valueOf(reportLevel).equals(Long.valueOf(2))){
			reportLevelLiteral = "DistrictLevel";
			reportLoc = reportVO.getDistrict();
		}
		else if(Long.valueOf(reportLevel).equals(Long.valueOf(3))){
			reportLevelLiteral = "CountryLevel";
			reportLoc = "India";
		}
		if(LOG.isDebugEnabled()){
			LOG.debug("Election Type -->" + electionTypeLiteral);
			LOG.debug("Report Level -->" + reportLevelLiteral);
		}
		
		//Check for report success or failure
		Boolean reportStatus = reportVO.getReportSuccessOrFailure();
		if(reportStatus == false)
			return "failure";
		
	/*	if("2".equals(reportLevel))
			reportTitle =  partyNameLiteral +" Party Performance Report for "+ electionTypeLiteral +" " + year + " in  " + district;
		else
			reportTitle =  partyNameLiteral +" Party Performance Report for "+ electionTypeLiteral +" " + year + " in  " + state;*/
		
		//reportTitle =  partyNameLiteral +" " + electionTypeLiteral + "(" + reportLevelLiteral + ")" + "  Performance Report for the year " + year;
		
		reportTitle =  partyNameLiteral +" " + electionTypeLiteral + "(" + reportLoc + ")" + "  Performance Report for the year " + year;
		
		if(LOG.isDebugEnabled())
			LOG.debug("Report Title -->" + reportTitle);
		
		SortedMap<String, Integer> positions = reportVO.getPositionDistribution();
		try{
		session = request.getSession();
		String chartId = country.concat(party).concat(electionType).concat(state).concat(district).concat(year);
		String chartName = "partyPositionsChart_" + chartId + session.getId()+".png";
		String chartPath = "";
		
		if(cPath.contains("PartyAnalyst"))
			chartPath = context.getRealPath("/") + "charts\\" + chartName;
		else
			chartPath = IWebConstants.CHART_URL_IN_SERVER + chartName;
		
		// jfree chart code comment ...............
		//ChartProducer.createPie3DChart(positions, chartPath, "Party Positions");
      /*  if(reportVO.getPartyPositionsVO() != null && reportVO.getPartyPositionsVO().size() > 0)
        ChartProducer.createBarChart("Party Positions", "Party", "Seats", createDataset(reportVO.getPartyPositionsVO()), chartPath);
		request.setAttribute("chartName", chartName);*/
		session.setAttribute("reportVO", reportVO);
		//session.setAttribute("chartName", chartName);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		    try{
			session = request.getSession();
			/*String chartId = country.concat(party).concat(electionType).concat(state).concat(district).concat(year).concat("LineChart");
			String lineChartName = "partyElectionResultsChart_" + chartId + session.getId()+".png";
			String chartPath = "";
			
			//check the request path wheather from server or local
			if(cPath.contains("PartyAnalyst"))
				chartPath = context.getRealPath("/") + "charts\\" + lineChartName;
			else
				chartPath = IWebConstants.CHART_URL_IN_SERVER + lineChartName;*/
			
			//ChartProducer.createPie3DChart(positions, chartPath, "Party Positions");
	         if(reportVO.getTotalSeatsWon() != 0 && reportVO.getPrevYearTotalSeatsWon() != 0 && reportVO.getTotalPercentageOfVotesWon() != null && reportVO.getPrevYeartotalPercentageOfVotesWon() != null){
	        	//ChartProducer.createBarChart("Results In Elections", "Years", "Seats", createDatasetForLineGraph(reportVO.getTotalSeatsWon(),reportVO.getPrevYearTotalSeatsWon(),reportVO.getTotalPercentageOfVotesWon(),reportVO.getPrevYeartotalPercentageOfVotesWon(),reportVO.getYear(),reportVO.getPrevYear()), chartPath);
	        	//ChartProducer.createALineChart("Election Result", createDatasetForLineGraph(reportVO.getTotalSeatsWon(),reportVO.getPrevYearTotalSeatsWon(),reportVO.getTotalPercentageOfVotesWon(),reportVO.getPrevYeartotalPercentageOfVotesWon(),reportVO.getYear(),reportVO.getPrevYear()), "Years", "Seats", chartPath);
	        	//request.setAttribute("lineChartName", lineChartName);
				session.setAttribute("reportVO", reportVO);
				//session.setAttribute("lineChartName", lineChartName);
				session.setAttribute("totalSeatsWon", reportVO.getTotalSeatsWon());
				session.setAttribute("prevYearTotalSeatsWon", reportVO.getPrevYearTotalSeatsWon());
				session.setAttribute("year", reportVO.getYear());
				session.setAttribute("prevYear", reportVO.getPrevYear());
	         }
	         else if(reportVO.getTotalSeatsWon() != 0 || reportVO.getTotalPercentageOfVotesWon() != null){
	        	// ChartProducer.createALineChart("Election Result", createDatasetForLineGraph(reportVO.getTotalSeatsWon(),reportVO.getPrevYearTotalSeatsWon(),reportVO.getTotalPercentageOfVotesWon(),reportVO.getPrevYeartotalPercentageOfVotesWon(),reportVO.getYear(),reportVO.getPrevYear()), "Years", "Seats", chartPath);	        	 request.setAttribute("lineChartName", lineChartName);
				 session.setAttribute("reportVO", reportVO);
				// session.setAttribute("lineChartName", lineChartName);
				 session.setAttribute("totalSeatsWon", reportVO.getTotalSeatsWon());
				 session.setAttribute("prevYearTotalSeatsWon", reportVO.getPrevYearTotalSeatsWon());
	         }
	        
	       	}
			catch(Exception ex){
				ex.printStackTrace();
		    }
		}
		return Action.SUCCESS;
    }
	
	//@JSON (serialize= false )   
	public String getJasper() throws JRException {
		
		LOG.debug("Party Performance Jasper execution started...");
		String contextPath = context.getRealPath("/");
		
		session = request.getSession();
		
       
       	String jasperXML = contextPath + request.getParameter("jasperFile");	
       	LOG.debug(jasperXML);
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("REPORT_DIR", "charts\\partyPositionsChart_" + request.getSession().getId() + ".png");
		params.put("REPORT_DIR", "charts\\" + session.getAttribute("chartName"));
		params.put("CONTEXT_PATH", contextPath);
		params.put("REPORT_TYPE", request.getParameter("type"));
		
		reportVO = (PartyPerformanceReportVO) request.getSession().getAttribute("reportVO");
		byte[] report = JasperProducer.createJasperReport(jasperXML, params, reportVO);
	   	response.reset();
	   	response.setContentType("application/pdf");
	   	response.setHeader("Content-Disposition", "inline; filename=\"new_report.pdf\"");
	    response.setBufferSize(report.length);
	    OutputStream out = null;
	    
	   	try {
			out  = response.getOutputStream();
			out.write(report);
		   	out.flush();
		   	response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	 
    } 

	public String getpartyPosition() throws Exception{
		
		String param=null;		
		param=request.getParameter("task");
		LOG.info("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			LOG.info("jObj = "+jObj);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}		
		String electionTypeID = jObj.getString("eId");
		String stateID = jObj.getString("stateValue");
		String districtID = jObj.getString("districtValue");
		String year = jObj.getString("yearValue");
		String partyID = jObj.getString("partyValue");
		String alliances = jObj.getString("hasAlliances");
		String rank = jObj.getString("positionValue");
		String reportLevel = jObj.getString("reportLevel");
		
		LOG.debug("Report Level Inside getpartyPosition method (Action) :" + reportLevel);
		
		partyPositionDisplayVO = partyService.getNthPositionPartyDetails(Long.valueOf(electionTypeID),Long.valueOf (stateID),Long.valueOf (districtID),Long.valueOf (year),Long.valueOf (partyID),Boolean.valueOf (alliances).booleanValue(),Integer.valueOf(rank).intValue(),reportLevel);
		
		LOG.info("Length = "+partyPositionDisplayVO.size());
		return Action.SUCCESS;
	}
	
      public String getPartyPositionDetails() throws Exception{
		
		String param=null;		
		param=request.getParameter("task");
		LOG.info("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			LOG.info("jObj = "+jObj);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}		
		String electionTypeID = jObj.getString("eId");
		String stateID = jObj.getString("stateValue");
		String districtID = jObj.getString("districtValue");
		String year = jObj.getString("yearValue");
		String partyID = jObj.getString("partyValue");
		String alliances = jObj.getString("hasAlliances");
		String rank = jObj.getString("positionValue");
		String reportLevel = jObj.getString("reportLevel");
		
		LOG.debug("Report Level Inside getpartyPosition method (Action) :" + reportLevel);
		
		partyPositionDisplayVO = partyService.getPartyPositionDetailsForAnElection(Long.valueOf(electionTypeID),Long.valueOf (stateID),Long.valueOf (districtID),Long.valueOf (year),Long.valueOf (partyID),Boolean.valueOf (alliances).booleanValue(),Integer.valueOf(rank).intValue(),reportLevel);
		
		LOG.debug("Size = "+partyPositionDisplayVO.size());
		return Action.SUCCESS;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	} 

	
	private CategoryDataset createDataset(List<PartyPositionsVO> partyPositionsVO) {
		 final String category1 =  "Seats Won";
	     final String category2 = "2nd Pos";
	     final String category3 = "3rd Pos";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(PartyPositionsVO result: partyPositionsVO){
        	final String series =  result.getPartyName();
        	dataset.addValue(Long.valueOf(result.getTotalSeatsWon()), category1,series );
        	dataset.addValue(Long.valueOf(result.getSecondPosWon()), category2, series);
        	dataset.addValue(Long.valueOf(result.getThirdPosWon()), category3, series);
        }
        return dataset;
        
    }
	
	@SuppressWarnings("unused")
	private CategoryDataset createDatasetForLineGraph(int seatsWonInYear,int seatsWonInPrevYear,BigDecimal totalPercentageOfVotesWon,BigDecimal prevYeartotalPercentageOfVotesWon ,String year,String prevYear){
		  // row keys...
        final String series1 = "Seats Won";
        final String series2 =  "Percentage of Votes";
		
        // create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 dataset.addValue(seatsWonInPrevYear, series1, prevYear);
		 dataset.addValue(prevYeartotalPercentageOfVotesWon, series2, prevYear);
		 
		 dataset.addValue(seatsWonInYear, series1, year);
		 dataset.addValue(totalPercentageOfVotesWon, series2, year);
		 
	return dataset;
	}
	
	
	private CategoryDataset createDatasetForLineGraphNew(int seatsWonInYear,BigDecimal totalPercentageOfVotesWon,String year){
		  // row keys...
        final String series1 = "Seats Won";
        final String series2 =  "Percentage of Votes";
		
        // create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 dataset.addValue(seatsWonInYear, series1, year);
		 dataset.addValue(totalPercentageOfVotesWon, series2, year);
		 
	return dataset;
	}
	
	public String getMarginCount() throws Exception
	{
		
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
		
		Long electionId = Long.valueOf(jObj.getString("electionId"));
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		String status = jObj.getString("status");
		String reportLevel = jObj.getString("reportLevel");
		Long locationId = Long.valueOf(jObj.getString("locationId"));
		Long stateId = Long.valueOf(0);
		Long districtId = Long.valueOf(0);
		
		String category = null;
		if(status.equalsIgnoreCase("WON"))
			category = IConstants.CANDIDATE_COMMENTS_WON;
		else if(status.equalsIgnoreCase("LOST"))
			category = IConstants.CANDIDATE_COMMENTS_LOST;
		
		if("1".equalsIgnoreCase(reportLevel))
			stateId = locationId;
		else if("2".equalsIgnoreCase(reportLevel))
			districtId = locationId;
		
		votesMarginAnalysisVO = analysisReportService.getVotesMarginAnalysisResults(electionId, partyId, category,stateId,districtId);
		
		return Action.SUCCESS;
	}
	
	public String getElectionYearsForParty(){
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				LOG.info("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			String elecType = jObj.getString("elecTypeId");
			Long partyId = Long.valueOf(jObj.getString("partyId"));
			Long stateId = Long.valueOf(jObj.getString("stateId"));
			
			Long countryId = 1l;
			String electionType = null;
			List<SelectOptionVO> yearsList = null;
			if(elecType.equalsIgnoreCase("Parliament"))
				electionType = IConstants.PARLIAMENT_ELECTION_TYPE;
			else 
				electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			
			Long electionScope = staticDataService.getElectionScopeForAElection(stateId, electionType, countryId);
			if(electionScope != null)
				yearsList = staticDataService.getElectionIdsAndYearsByElectionScope(electionScope,partyId);
			if(yearsList != null){
				years = new ArrayList<String>();
				for(SelectOptionVO yearsLst:yearsList){
					years.add(yearsLst.getName());
				}
			}
			
		}
		return Action.SUCCESS;
	}
	
	public String getStaticPartyDetailsAjax(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				LOG.info("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
						
			Long stateID = Long.valueOf(jObj.getString("stateId"));
			String electionType = jObj.getString("elecTypeId");
			String reportLevel = jObj.getString("reportLevel");
			
			if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE) && "3".equalsIgnoreCase(reportLevel)){
				parties = staticDataService.getAllNationalParties();
			Collections.sort(parties);}
			else if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
			{
				parties = staticDataService.getPartitesList(stateID,electionType);
			}
			else{
				//parties = staticDataService.getStaticPartiesListForAState(stateID);
			    parties = staticDataService.getStaticPartiesListByStateAndElection(stateID,electionType);
			Collections.sort(parties);
		}
		}
		return Action.SUCCESS;
	}
	
	public String ajaxHandler()
	{
		try{
			jObj = new JSONObject(getTask());
		}catch(Exception e){
			LOG.error("Exception Occured in AjaxHandler() Method, Exception is - "+e);
			return SUCCESS;
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getPartiesWithAtleatOneWinningSeat"))
		{
			parties = staticDataService.getPartiesWithAtleatOneWinningSeatForAElection(jObj.getLong("electionId"));
		}
		return SUCCESS;
	}
	
	public String getStatesList()
	{
		try {
			LOG.debug("Entered into getStatesList() Method in PartyPerformanceAction Action");
			jObj = new JSONObject(getTask());
			Long electionTypeId = jObj.getLong("electionTypeId");
			states = getStaticDataService().getParticipatedStatesForAnElectionType(electionTypeId);
			//states.add(new SelectOptionVO(0l,"Select State"));
		} catch (Exception e) {
			LOG.error("Exception Occured in getStatesList() Method, Exception is - "+e);
		}
		return SUCCESS;
	}
	
}
