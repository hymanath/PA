package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IElectionService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CensusReportAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	private List<SelectOptionVO> states;
	private IStaticDataService staticDataService; 
	private List<String> years;
	JSONObject jObj = null;
	private String task;
	private IElectionService electionService;
	private List<CensusVO> censusRange;
	private ElectionDataVO electionDataVO;
	private List<ConstituencyElectionResultsVO> constituencyElectionResults;
	String chartName = null;
	
	private static final Logger log = Logger.getLogger(CensusReportAction.class);
	
	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public List<ConstituencyElectionResultsVO> getConstituencyElectionResults() {
		return constituencyElectionResults;
	}

	public void setConstituencyElectionResults(
			List<ConstituencyElectionResultsVO> constituencyElectionResults) {
		this.constituencyElectionResults = constituencyElectionResults;
	}

	public List<CensusVO> getCensusRange() {
		return censusRange;
	}

	public void setCensusRange(List<CensusVO> censusRange) {
		this.censusRange = censusRange;
	}

	public IElectionService getElectionService() {
		return electionService;
	}

	public void setElectionService(IElectionService electionService) {
		this.electionService = electionService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getStates() {
		return states;
	}

	public void setStates(List<SelectOptionVO> states) {
		this.states = states;
	}

	public ElectionDataVO getElectionDataVO() {
		return electionDataVO;
	}

	public void setElectionDataVO(ElectionDataVO electionDataVO) {
		this.electionDataVO = electionDataVO;
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

	public String execute()
	{
		states = new ArrayList<SelectOptionVO>();		
		states.add(new SelectOptionVO(1L,"Andhra Pradesh"));
		setYears(getStaticDataService().getElectionYears(2L, false));
		
		return Action.SUCCESS;
	}
	
	public String getCensusInfoInAState()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Integer censusId    = jObj.getInt("censusValue");
		Long stateId        = jObj.getLong("stateId");
		Long districtId     = jObj.getLong("districtId");
		String reportLevel  = jObj.getString("reportLevel");
		Long year           = jObj.getLong("yearValue");
		
		censusRange = electionService.getConstituencyCensusDetails(censusId,stateId,districtId,year,reportLevel);
		
		return Action.SUCCESS;
	}
	
	public String getPartiesPerformanceInCensusReport()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Long> locationIds = new ArrayList<Long>();		
		StringBuilder sb = new StringBuilder();
		String electionYear = jObj.getString("yearValue");
		JSONArray jArray = jObj.getJSONArray("idsList");
		
		for (int i = 0; i < jArray.length(); i++) {
			locationIds.add(new Long(jArray.get(i).toString()));
			sb.append(jArray.get(i)).append(",");
		}

		electionDataVO = electionService.findAssemblyConstituenciesResultsByConstituencyIds(electionYear, locationIds, null, null, true, 0);
		
		return Action.SUCCESS;

	}
	
	public String getPartiesPerformanceInCensusReportByDistrictOrParties()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Long> locationIds = new ArrayList<Long>();
		List<Long> districtIds = new ArrayList<Long>();
		List<Long> partyIds = new ArrayList<Long>();
		StringBuilder sb = new StringBuilder();
		String electionYear = jObj.getString("yearValue");
		Boolean isAll = jObj.getBoolean("isAll");
		JSONArray jArray = jObj.getJSONArray("idsList");
		JSONArray jDistrictIds = jObj.getJSONArray("districtIds");
		JSONArray jPartyIds = jObj.getJSONArray("partyIds");
		
		for (int i = 0; i < jArray.length(); i++) {
			locationIds.add(new Long(jArray.get(i).toString()));
			sb.append(jArray.get(i)).append(",");
		}

		for (int i = 0; i < jDistrictIds.length(); i++) 
			districtIds.add(new Long(jDistrictIds.get(i).toString()));
			
		for (int i = 0; i < jPartyIds.length(); i++) 
			partyIds.add(new Long(jPartyIds.get(i).toString()));
			
		electionDataVO = electionService.findAssemblyConstituenciesResultsByConstituencyIds(electionYear, locationIds, partyIds, districtIds, isAll, 0);
		
		return Action.SUCCESS;

	}
	
}
