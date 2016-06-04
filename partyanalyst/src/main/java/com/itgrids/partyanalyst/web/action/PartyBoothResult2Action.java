package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyBoothResult2Action extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private String constituencyName;
	private String partyName;
	private String electionYear;
	private String electionType;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private EntitlementsHelper entitlementsHelper;
	private PartyBoothPerformanceVO boothResult;

	
	
	private List<PartyBoothPerformanceVO> PartyBoothPerformanceVOList ;
	private SelectOptionVO selectOptionVO;
	JSONObject jObj;
	private String task;
	
	public List<PartyBoothPerformanceVO> getPartyBoothPerformanceVOList() {
		return PartyBoothPerformanceVOList;
	}
	public void setPartyBoothPerformanceVOList(
			List<PartyBoothPerformanceVO> partyBoothPerformanceVOList) {
		PartyBoothPerformanceVOList = partyBoothPerformanceVOList;
	}
	public SelectOptionVO getSelectOptionVO() {
		return selectOptionVO;
	}
	public void setSelectOptionVO(SelectOptionVO selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}


	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public PartyBoothPerformanceVO getBoothResult() {
		return boothResult;
	}


	public void setBoothResult(PartyBoothPerformanceVO boothResult) {
		this.boothResult = boothResult;
	}


	public String getConstituencyName() {
		return constituencyName;
	}


	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}


	public String getElectionYear() {
		return electionYear;
	}


	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	
	public String getPartyName() {
		return partyName;
	}


	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getElectionType() {
		return electionType;
	}


	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}


	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}


	public String execute(){
		
		HttpSession session = request.getSession();
		
		partyName = request.getParameter("partyName");
		electionYear = request.getParameter("electionYear");
		constituencyName = request.getParameter("constituencyName");
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		 List<String> entitlements = null;
			if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
				entitlements = regVO.getEntitlements();
				if(regVO == null && !entitlements.contains(IConstants.PARTY_BOOTHWISE_RESULTS_REPORT)){
					return INPUT;
				}
				if(!(entitlements.contains(IConstants.PARTY_BOOTHWISE_RESULTS_REPORT) || entitlementsHelper.checkForRegionToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CONSTITUENCY_LEVEL, Long.valueOf(constituencyName)))){
					return ERROR;
				}
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return INPUT;
		
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_BOOTHWISE_RESULTS_REPORT) 
			|| !entitlementsHelper.checkForRegionToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CONSTITUENCY_LEVEL, Long.valueOf(constituencyName)))
			return ERROR;*/
				
		LOG.info(" values from ajax -------- partyName:"+partyName+" constituencyName:"+constituencyName+" electionYear:"+electionYear);
		List<PartyBoothPerformanceVO> boothResults = partyBoothWiseResultsService.getBoothWiseResultsForParty(Long.valueOf(partyName), Long.valueOf(constituencyName), electionYear);
		String path = IWebConstants.STATIC_CONTENT_FOLDER_URL;
		boothResult = partyBoothWiseResultsService.getVotingPercentageWiseBoothResult(boothResults.get(0),true,path);
		boothResult = partyBoothWiseResultsService.getVotingPercentageWiseBoothResult(boothResults.get(0),false,null);
	}
		return SUCCESS;
	}

	public String ajaxHandler()
	{
		try {
			
			jObj = new JSONObject(getTask());
			Long constituencyId = jObj.getLong("constituencyId");
			JSONArray partyArr = jObj.getJSONArray("partyList");
			List<Long> partyIds = new ArrayList<Long>();
			
			if(partyArr != null && partyArr.length()>0)
			{
				for (int i = 0; i < partyArr.length(); i++) {
					partyIds.add(Long.valueOf(partyArr.get(i).toString()));
				}
			}
			
			JSONArray electionYearsArr = jObj.getJSONArray("electionYearsList");
			List<String> electionyears = new ArrayList<String>();
			
			if(electionYearsArr != null && electionYearsArr.length()>0)
			{
				for (int i = 0; i < electionYearsArr.length(); i++) {
					electionyears.add(electionYearsArr.get(i).toString());
				}
			}
			
			List<PartyBoothPerformanceVO> boothResults = partyBoothWiseResultsService.getBoothWiseElectionResults(partyIds, constituencyId, electionyears);
			
			String path = IWebConstants.STATIC_CONTENT_FOLDER_URL;

			List<PartyBoothPerformanceVO> PartyBoothPerformanceVOList1 = new ArrayList<PartyBoothPerformanceVO>();
			
			if(jObj.getString("task").equalsIgnoreCase("assemblyWiseResults"))
			{				
				if(boothResults != null && boothResults.size()>0)
				{
					for (PartyBoothPerformanceVO vo : boothResults) 
					{	
						path = IWebConstants.STATIC_CONTENT_FOLDER_URL+""+vo.getPartyName();
						PartyBoothPerformanceVO boothResult1 = partyBoothWiseResultsService.getVotingPercentageWiseBoothResultForParties(vo,true,path,partyIds);
												boothResult1 = partyBoothWiseResultsService.getVotingPercentageWiseBoothResultForParties(vo,false,null,partyIds);
						
						PartyBoothPerformanceVOList1.add(boothResult1);
					}
				}
			 
			}
			else if(jObj.getString("task").equalsIgnoreCase("parliamentWiseResults"))
			{			
				if(boothResults != null && boothResults.size()>0)
				{
					for (PartyBoothPerformanceVO vo : boothResults) 
					{	
						path = IWebConstants.STATIC_CONTENT_FOLDER_URL+""+vo.getPartyName();
						PartyBoothPerformanceVO boothResult1 = partyBoothWiseResultsService.getVotingPercentageWiseBoothResultForParties(vo,true,path,partyIds);
												boothResult1 = partyBoothWiseResultsService.getVotingPercentageWiseBoothResultForParties(vo,false,null,partyIds);
						
						PartyBoothPerformanceVOList1.add(boothResult1);
					}
				}
			}
			
			boothResult = partyBoothWiseResultsService.segrigateBoothWiseResults(PartyBoothPerformanceVOList1);
			
		} catch (Exception e) {
			LOG.error(" exception occured in ajaxHandler() in PartyBoothResult2Action action class.",e);
		}
		return SUCCESS;
	}
	
	public String getStatewiseDetails()
	{
		try {
			HttpSession session = request.getSession();
			
			if(session == null )
				return Action.ERROR;
			
			
			jObj = new JSONObject(getTask());
			
			Long stateTypeId = jObj.getLong("stateType");
			String electionType = jObj.getString("electionType");
			Long electionYear =   jObj.getLong("electionYear");
			Long constituencyId = jObj.getLong("constituencyId");
			
			if(electionYear.longValue() == 0L)
			{
				electionYear = null;
			}
			
			if(constituencyId.longValue() == 0L)
			{
				constituencyId = null;
			}
			
			selectOptionVO = partyBoothWiseResultsService.getStateWiseDetailByStateType(stateTypeId,electionType,electionYear,constituencyId);
			
		} catch (Exception e) {
			LOG.error(" exception occured in getStatewiseDetails() in PartyBoothResult2Action action class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPartyDetailsForConstituency()
	{
		try {
			HttpSession session = request.getSession();
			
			if(session == null )
				return Action.ERROR;
			
			
			jObj = new JSONObject(getTask());
			
			Long electionYear =   jObj.getLong("electionYear");
			Long constituencyId = jObj.getLong("constiteuncyId");
			
			selectOptionVO = partyBoothWiseResultsService.getPartyDetailsForConstituencyAction(electionYear,constituencyId);
			
		} catch (Exception e) {
			LOG.error(" exception occured in getPartyDetailsForConstituencyAction() in PartyBoothResult2Action action class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAssemblyDetailsForParliamnt()
	{
		try {
			
			HttpSession session = request.getSession();
			
			if(session == null )
				return Action.ERROR;
			
			
			jObj = new JSONObject(getTask());
			
			Long electionYear =   jObj.getLong("electionYear");
			Long parliamentConstiId = jObj.getLong("parliamentConstiId");
			
			selectOptionVO = partyBoothWiseResultsService.getAssemblyDetailsForParliamnt(electionYear,parliamentConstiId);
			
		} catch (Exception e) {
			LOG.error(" exception occured in getAssemblyDetailsForParliamnt() in PartyBoothResult2Action action class.",e);
		}
		return Action.SUCCESS;
	}
}
