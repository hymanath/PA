package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.CensusWisePartyResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IElectionService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.SelectOptionVOComparator;
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
	private List<CensusWisePartyResultsVO> allPartiesResults;
	String chartName = null;
	private EntitlementsHelper entitlementsHelper;
	private	List<SelectOptionVO> yearList;
	
	public List<SelectOptionVO> getYearList() {
		return yearList;
	}

	public void setYearList(List<SelectOptionVO> yearList) {
		this.yearList = yearList;
	}

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

	public List<CensusWisePartyResultsVO> getAllPartiesResults() {
		return allPartiesResults;
	}

	public void setAllPartiesResults(
			List<CensusWisePartyResultsVO> allPartiesResults) {
		this.allPartiesResults = allPartiesResults;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute()
	{
		HttpSession session = request.getSession();
		session = request.getSession();
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CENSUS_REPORT_ENTITLEMENT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CENSUS_REPORT_ENTITLEMENT))
			return ERROR;
		
		states = new ArrayList<SelectOptionVO>();
		states.add(new SelectOptionVO(0L,"Select State"));
		states.add(new SelectOptionVO(1L,"Andhra Pradesh"));
		states.add(new SelectOptionVO(24L,"Tamil Nadu"));
		setYears(getStaticDataService().getElectionYears(2L, false));
		
		List<SelectOptionVO> censusParamList = new ArrayList<SelectOptionVO>(0);
		
		censusParamList = electionService.getAllCensusParameters();
		session.setAttribute("censusParamList",censusParamList);
		
		return Action.SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
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
		
		if("censusByParty".equalsIgnoreCase(jObj.getString("task"))){
			Long partyId = jObj.getLong("partyId");
			censusRange = (List<CensusVO>)electionService.getPartywiseConstituenciesResultsForCensusInfo(censusId,stateId,districtId,year,
					reportLevel, partyId).getFinalResult();	
		}
		else
			censusRange = electionService.getConstituencyCensusDetails(censusId,stateId,districtId,year,reportLevel);
		
		return Action.SUCCESS;
	}
	
	public String getCensusInfoForAllPartiesInAState()
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
		
		allPartiesResults = electionService.findAllPartiesInfoByCensusRanges(censusId, stateId, districtId, year, reportLevel);
		
		return SUCCESS;
	}
	
	public String getPartiesPerformanceInCensusReport()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if("getPartiesByState".equalsIgnoreCase(jObj.getString("task"))){
			electionDataVO = new ElectionDataVO();
			Long stateId = jObj.getLong("stateId");
			List<SelectOptionVO> parties = staticDataService.getStaticPartiesListForAState(stateId);
			//List<SelectOptionVO> parties = staticDataService.getAllPartiesParticapatedInMainElectionsService(stateId);
			Collections.sort(parties, new SelectOptionVOComparator());
			electionDataVO.setParties(new LinkedHashSet<SelectOptionVO>(parties));
			return Action.SUCCESS;
		}
		
		List<Long> locationIds = new ArrayList<Long>();		
		StringBuilder sb = new StringBuilder();
		String electionYear = jObj.getString("yearValue");
		JSONArray jArray = jObj.getJSONArray("idsList");
		Integer cansusTypeId = jObj.getInt("censusTypeId");		
		for (int i = 0; i < jArray.length(); i++) {
			locationIds.add(new Long(jArray.get(i).toString()));
			sb.append(jArray.get(i)).append(",");
		}
		
		electionDataVO = electionService.findAssemblyConstituenciesResultsByConstituencyIds(electionYear, locationIds, null, null, cansusTypeId, true, true);
		
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
		int cnsusTypId=jObj.getInt("censusTypeId");
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
			
		electionDataVO = electionService.findAssemblyConstituenciesResultsByConstituencyIds(electionYear, locationIds, partyIds, districtIds, 
				cnsusTypId, isAll, true);
		
		return Action.SUCCESS;

	}
	
	public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getLatestElectionYears")){
			yearList = new ArrayList<SelectOptionVO>(0);
			yearList = electionService.getLatestElectionYearForAStateBasedOnElectionType(jObj.getLong("stateId"),IConstants.ASSEMBLY_ELECTION_TYPE,IConstants.ELECTION_SUBTYPE_MAIN);
			//yearList.add(0,new SelectOptionVO(0l,"Select Year"));
		}
		return Action.SUCCESS;
	}
	
}
