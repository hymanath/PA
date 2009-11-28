package com.itgrids.partyanalyst.web.action;


import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.json.JSONObject;

import com.googlecode.jsonplugin.annotations.JSON;
import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionDisplayVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.Constants;
import com.itgrids.partyanalyst.helper.JasperProducer;
import com.itgrids.partyanalyst.service.IPartyService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyPerformanceAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ServletContextAware {

	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(PartyPerformanceAction.class);

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
	private Set<String> years;
	private List<SelectOptionVO> levels;
	private boolean hasAllianceParties;
	private Long electionTypeId;
	JSONObject jObj = null;
	private String task = null;
	private List<PartyPositionDisplayVO> partyPositionDisplayVO;
	private Map statesYearList = new HashMap();


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
    
	public Set<String> getYears() {
		return years;
	}
	public void setYears(Set<String> years) {
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
	
	@JSON (serialize= false )
	public PartyPerformanceReportVO getStateData() {
		return reportVO;
	}
	public void setStateData(PartyPerformanceReportVO stateData) {
		this.reportVO = stateData;
	}
	
	public boolean isHasAllianceParties() {
		return hasAllianceParties;
	}
	@JSON (serialize= false )
	public HttpServletRequest getRequest() {
		return request;
	}
	
	@JSON (serialize= false )
	public Long getDefaultId() {
		return electionTypeId;
	}
	
	@JSON (serialize= false )
	public HttpServletResponse getResponse() {
		return response;
	}
	
	@JSON (serialize= false )
	public HttpSession getSession() {
		return session;
	}
	
	@JSON (serialize= false )
	public ServletContext getContext() {
		return context;
	}
	
	@JSON (serialize= false )
	public PartyPerformanceReportVO getReportVO() {
		return reportVO;
	}
	
	
	@JSON (serialize= false )
	public IPartyService getPartyService() {
		return partyService;
	}
	public void setPartyService(IPartyService partyService) {
	    this.partyService = partyService;
	}
	
	@JSON (serialize= false )   
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
	
	@SuppressWarnings("unchecked")
	public String execute() throws JRException {
		
		log.debug("partyPerformance excute started...");
	
		Map<String, String> params = request.getParameterMap();
		String year = null;
		Long partyId = null;
		
		String param = null;
		electionTypeId = new Long(2);
		
		if(params.containsKey("type")){
			param = request.getParameter("type");
		}
		
		if(param != null) {
			electionTypeId = new Long(param);
		}
		
		setStates(getStaticDataService().getStates(electionTypeId));
		setYears(getStaticDataService().getElectionYears(electionTypeId));
		setParties(getStaticDataService().getStaticParties());
		setDistricts(new ArrayList<SelectOptionVO>());
		setLevels(getReportLevels());    
	
		if(year == null && partyId == null){
			year = getYears().iterator().next();
			partyId = getParties().get(0).getId();
		}
		
		boolean t = getStaticDataService().hasAlliances(year, electionTypeId, partyId);
		setHasAllianceParties(t);
		return Action.SUCCESS;
	 
    }

	@SuppressWarnings("unchecked")
	public String getAlliances() throws JRException {
		String year = null;
		Long partyId = null;
		Long electionType = null;
		Map<String, String> params = request.getParameterMap();
		
		if(params.containsKey("allianceWith")){
			partyId = new Long(request.getParameter("allianceWith"));
			year = request.getParameter("year");
			electionType = new Long(request.getParameter("elecType"));
		}
		
		boolean t = getStaticDataService().hasAlliances(year, electionType, partyId);
		setHasAllianceParties(t);
		
		return Action.SUCCESS;
	}
	private List<SelectOptionVO> getReportLevels() {
		List<SelectOptionVO>levels = new ArrayList<SelectOptionVO>();
	    levels.add(new SelectOptionVO(new Long(1), "State Level"));
	    levels.add(new SelectOptionVO(new Long(2), "District Level"));
		return levels;
	}

	public String getJSON() throws JRException {
		log.debug("partyPerformanceAjax action started...");
		return execute();
	}
	
	public String getDistrictsList(){
		String param = request.getParameter("stateId");
		districts = getStaticDataService().getDistricts(new Long(param));
		return Action.SUCCESS;
	}

	public String getElectionTypeFilterData(){

		Map<String, String> params = request.getParameterMap();
		String param = null;
		electionTypeId = new Long(2);
		
		if(params.containsKey("type")){
			param = request.getParameter("type");
		}
		if(param != null) {
			electionTypeId = new Long(param);
		}
		
		statesYearList.put("STATES", staticDataService.getStates(electionTypeId));
		statesYearList.put("YEARS", staticDataService.getElectionYears(electionTypeId));
		return Action.SUCCESS;
	}
	@JSON (serialize= false )   
	public String getReport() {
		
		log.debug("partyPerformanceReport action started...");
		String district = "0";
		String country = request.getParameter("country");
		String reportLevel = request.getParameter("1");
		String party = request.getParameter("party");
		String electionType = request.getParameter("electionType");
		String year = request.getParameter("year");
		String state = request.getParameter("state");
		Boolean alliances = new Boolean(request.getParameter("alliances"));
		
		if("2".equals(reportLevel)){
			district = request.getParameter("district");
		}
		
		reportVO = getPartyService().getPartyPerformanceReport(state, district, party, year, electionType, country, 0, new BigDecimal(Constants.MAJAR_BRAND), new BigDecimal(Constants.MINOR_BRAND), alliances);
		reportVO.setElectionTypeId(new Long(electionType));
		reportVO.setStateId(new Long(state));
		reportVO.setPartyId(new Long(party));
		reportVO.setHasAlliances(alliances);
		
		if(district!=null)
			reportVO.setDistrictId(new Long(district));
		
		
		SortedMap<String, Integer> positions = reportVO.getPositionDistribution();
		
		session = request.getSession();
		String chartId = country.concat(party).concat(electionType).concat(state).concat(district).concat(year);
		String chartName = "partyPositionsChart_" + chartId + session.getId()+".png";
        String chartPath = context.getRealPath("/") + "charts\\" + chartName;
       
		ChartProducer.createPie3DChart(positions, chartPath, "Party Positions");
		request.setAttribute("chartName", chartName);
		session.setAttribute("reportVO", reportVO);
		session.setAttribute("chartName", chartName);
		return Action.SUCCESS;
    }
	
	@JSON (serialize= false )   
	public String getJasper() throws JRException {
		
		log.debug("Party Performance Jasper execution started...");
		String contextPath = context.getRealPath("/");
		
		session = request.getSession();
		
       
       	String jasperXML = contextPath + request.getParameter("jasperFile");	
       	log.debug(jasperXML);
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
		System.out.println("param:"+param);
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
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
		
		partyPositionDisplayVO = partyService.getNthPositionPartyDetails(new Long(electionTypeID),new Long (stateID),new Long (districtID),new Long (year),new Long (partyID),new Boolean (alliances).booleanValue(),new Integer (rank).intValue());
		
		System.out.println("Length = "+partyPositionDisplayVO.size());
		return Action.SUCCESS;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	} 

}
