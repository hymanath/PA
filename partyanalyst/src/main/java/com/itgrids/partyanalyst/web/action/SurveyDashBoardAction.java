package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyCompletionDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.service.impl.SurveyDashBoardService;
import com.itgrids.partyanalyst.service.impl.SurveyDataDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyDashBoardAction  extends ActionSupport implements ServletRequestAware{
	
	public static final Logger LOG = Logger.getLogger(SurveyDashBoardAction.class);
	HttpServletRequest request;
	
	private String task;
	private JSONObject jObj;
	
	private SurveyDashBoardService surveyDashBoardService;
	private List<SurveyDashBoardVO> resultList;
	private List<SurveyCompletionDetailsVO> completionDtlsList;
	private SurveyDataDetailsService surveyDataDetailsService;
	private List<SelectOptionVO> constituenciesList;
	private List<SurveyReportVO> dayWiseReportList;
	private List<String> casteCollectedDates;
	private Long constituencyId;
	private List<SurveyReportVO> verifiedBooths;
	
	
	public List<SurveyReportVO> getVerifiedBooths() {
		return verifiedBooths;
	}

	public void setVerifiedBooths(List<SurveyReportVO> verifiedBooths) {
		this.verifiedBooths = verifiedBooths;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public List<String> getCasteCollectedDates() {
		return casteCollectedDates;
	}

	public void setCasteCollectedDates(List<String> casteCollectedDates) {
		this.casteCollectedDates = casteCollectedDates;
	}


	
	public List<SurveyReportVO> getDayWiseReportList() {
		return dayWiseReportList;
	}

	public void setDayWiseReportList(List<SurveyReportVO> dayWiseReportList) {
		this.dayWiseReportList = dayWiseReportList;
	}

	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public SurveyDataDetailsService getSurveyDataDetailsService() {
		return surveyDataDetailsService;
	}

	public void setSurveyDataDetailsService(
			SurveyDataDetailsService surveyDataDetailsService) {
		this.surveyDataDetailsService = surveyDataDetailsService;
	}

	public List<SurveyCompletionDetailsVO> getCompletionDtlsList() {
		return completionDtlsList;
	}

	public void setCompletionDtlsList(
			List<SurveyCompletionDetailsVO> completionDtlsList) {
		this.completionDtlsList = completionDtlsList;
	}

	public List<SurveyDashBoardVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<SurveyDashBoardVO> resultList) {
		this.resultList = resultList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	

	public SurveyDashBoardService getSurveyDashBoardService() {
		return surveyDashBoardService;
	}

	public void setSurveyDashBoardService(
			SurveyDashBoardService surveyDashBoardService) {
		this.surveyDashBoardService = surveyDashBoardService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	private SurveyDashBoardVO resultVO;
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SurveyDashBoardVO getResultVO() {
		return resultVO;
	}

	public void setResultVO(SurveyDashBoardVO resultVO) {
		this.resultVO = resultVO;
	}

	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
		{
			return Action.INPUT;
		}
		resultVO = surveyDashBoardService.getCompletdConstituenciesDetails();
		resultList = surveyDashBoardService.getConstituencyWiseCompletionReport(); 
		return Action.SUCCESS;
	}
	
	public String saveSurveyCompletionDetails()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			List<Long> collectorCompletedBoothIds = new ArrayList<Long>();
			List<Long> verifierCompletedBoothIds = new ArrayList<Long>();
			
			JSONArray cCompletedboothDetails = jObj.getJSONArray("collectorCompletedBoothIds");
			 
			for(int i = 0 ; i < cCompletedboothDetails.length() ; i++)
			{
				collectorCompletedBoothIds.add(Long.valueOf(cCompletedboothDetails.get(i).toString()));
			}
			
			JSONArray vCompletedboothDetails = jObj.getJSONArray("verifierCompletedBoothIds");
			 
			for(int i = 0 ; i < vCompletedboothDetails.length() ; i++)
			{
				verifierCompletedBoothIds.add(Long.valueOf(vCompletedboothDetails.get(i).toString()));
			}
			
			Long constituencyId =jObj.getLong("constituencyId");
			Long scopeId = jObj.getLong("scopeId");
			
			SurveyCompletionDetailsVO completionDetails = new SurveyCompletionDetailsVO();
			
			completionDetails.setCollectorCompletedList(collectorCompletedBoothIds);
			completionDetails.setVerifierCompletedList(verifierCompletedBoothIds);
			completionDetails.setLocationValue(constituencyId);			
			completionDetails.setLocationScopeId(scopeId);
			
			
		  status = 	surveyDashBoardService.saveSurveyCompletionDetails(completionDetails);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
		
	}
	
	/*public String saveSurveyCompletionDetails()
	{
		try
		{
		jObj = new JSONObject(getTask());
		
		List<SurveyCompletionDetailsVO> surveycompletionDtlsList = new ArrayList<SurveyCompletionDetailsVO>();

		Long scopeId = jObj.getLong("scopeId");
		
		JSONArray completionDetailsArray = jObj.getJSONArray("surveyCompletionDetails");
		
		for (int i = 0; i < completionDetailsArray.length(); i++) 
		{
			JSONObject details = (JSONObject)completionDetailsArray.get(0);
			
			SurveyCompletionDetailsVO surveyCompletionDetails = new SurveyCompletionDetailsVO();
			
			surveyCompletionDetails.setLocationValue(details.getLong("locationValue"));
			surveyCompletionDetails.setDataCollectorCompleted(details.getBoolean("dataCollectorCompleted"));
			surveyCompletionDetails.setVerifierCompleted(details.getBoolean("verifierCompleted"));
			
			
		}
		
		status = surveyDashBoardService.saveSurveyCompletionDetails(surveycompletionDtlsList,scopeId);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}*/
	
	public String getSurveyCompletionDetailsForAllConstituencies()
	{
		try
		{
			completionDtlsList = surveyDashBoardService.getSurveyCompletionDetailsForAllConstituencies();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyCompletionDetailsOfConstituency()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			Long constituencyId = jObj.getLong("constituencyId");
			
			completionDtlsList = surveyDashBoardService.getSurveyCompletionDetailsOfConstituency(constituencyId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String updateSurveyCompleteDetails()
	{
		constituenciesList = 	surveyDataDetailsService.getAllAssemblyConstituenciesByStateId();
		return Action.SUCCESS;
		
	}
	
	public String saveBoothCompletionStatus()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			Long boothId = jObj.getLong("boothId");
			Long statusId = jObj.getLong("statusId");
			
			status = surveyDashBoardService.saveBoothCompletionStatus(boothId,statusId);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String getCasteCollectionDatesByConstituencyId()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			Long constituencyId = jObj.getLong("constituencyId");
			casteCollectedDates = surveyDashBoardService.getCasteCollecteddatesByConstituencyId(constituencyId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}
	
	
	public String getUsersReport()
	{
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
		{
			return Action.INPUT;
		}
		
		casteCollectedDates = surveyDashBoardService.getCasteCollectedDates();
		
		return Action.SUCCESS;
		
	}
	
	public String getUsersCompleteReportByStartAndEndDates()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			
			dayWiseReportList = surveyDashBoardService.getUsersCompleteReportByStartAndEndDates(startDate,endDate);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getUserReportForADate()
	{
		try {
			jObj = new JSONObject(getTask());
			
			Long userId = jObj.getLong("userId");
			String surveyDate = jObj.getString("surveyDate");
			
			dayWiseReportList = surveyDashBoardService.getUserReportForADate(userId,surveyDate);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return Action.SUCCESS;
		
	}
	
	public String getVerifiedBoothsDetails()
	{
		try
		{
			
			verifiedBooths = surveyDashBoardService.getVerifiedBoothsDetails(status,constituencyId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String getCasteCollectionDatesByUserId()	{
		try
		{
			jObj = new JSONObject(getTask());
			
			Long userId = jObj.getLong("userId");
			casteCollectedDates = surveyDashBoardService.getCasteCollectedDatesByUserId(userId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}
}
