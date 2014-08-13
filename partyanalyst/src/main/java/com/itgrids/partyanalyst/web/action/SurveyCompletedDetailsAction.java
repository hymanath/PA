package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.DuplicateMobileNumbersVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.service.ISurveyCompletedDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyCompletedDetailsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	
	 private HttpSession session;
 	 private HttpServletRequest request;
 	 private HttpServletResponse response;
 	 private Long constituencyId;
 	 private SurveyReportVO resultVO;
 	 private String status;
     private List<SurveyReportVO> resultList;
     private JSONObject jObj;
     private String task;
     private List<DuplicateMobileNumbersVO> duplicateMobileNumbersDetailsList;
     private List<SelectOptionVO> constituenciesList;

	
     
     public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public List<DuplicateMobileNumbersVO> getDuplicateMobileNumbersDetailsList() {
		return duplicateMobileNumbersDetailsList;
	}

	public void setDuplicateMobileNumbersDetailsList(
			List<DuplicateMobileNumbersVO> duplicateMobileNumbersDetailsList) {
		this.duplicateMobileNumbersDetailsList = duplicateMobileNumbersDetailsList;
	}


	@Autowired
	 private ISurveyCompletedDetailsService surveyCompletedDetailsService;
     
   

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

	public List<SurveyReportVO> getResultList() {
		return resultList;
	  }

	  public void setResultList(List<SurveyReportVO> resultList) {
	 	this.resultList = resultList;
	  }
	
	  public SurveyReportVO getResultVO() {
			return resultVO;
	  }

	  public void setResultVO(SurveyReportVO resultVO) {
			this.resultVO = resultVO;
	  }	
 	 
 	 public Long getConstituencyId() {
		return constituencyId;
	 }

	 public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	 }


	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getBoothStatusDetailsByConstituency()
	{
		try
		{
			resultVO = surveyCompletedDetailsService.getBoothsStatusByConstituencyId(constituencyId);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return ActionSupport.SUCCESS;
	}
	
	
	public String saveBoothStatusDetails()
	{
		try
		{
			Long locationValue = Long.parseLong(request.getParameter("locationValue"));
			Long statusId = Long.parseLong(request.getParameter("statusId"));
			String locationType = request.getParameter("locationType");
			
			status = surveyCompletedDetailsService.saveBoothStatusDetails(locationValue,statusId,locationType);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return ActionSupport.SUCCESS;
	}
	
	public String saveSurveyCompletedConstituencyDetails()
	{
		try
		{
			Long constituencyId = Long.parseLong(request.getParameter("constituencyId"));
			Long statusId = Long.parseLong(request.getParameter("statusId"));
			String locationType = request.getParameter("comment");
			
			status = surveyCompletedDetailsService.saveSurveyCompletedConstituencyDetails(statusId,constituencyId,locationType);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return ActionSupport.SUCCESS;
	}
	
	public String getSurveyCompletedConstituencyDetails()
	{
		try
		{
			resultList = surveyCompletedDetailsService.getSurveyCompletedConstituencyDetails();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	
	public String getConstituencyCompletionStatusByConstituencyId()
	{
		try
		{
			status = surveyCompletedDetailsService.getConstituencyCompletionStatusByConstituencyId(constituencyId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	
	public String getDuplicateMobileNumbersDetails()
	{
		try
		{
			jObj=new JSONObject(getTask());
			
			List<Long> selectedConstituencyIds = new ArrayList<Long>();
			
			JSONArray constituencyIds = jObj.getJSONArray("constituencyIds");
			 
			for(int i = 0 ; i < constituencyIds.length() ; i++)
			{
				selectedConstituencyIds.add(Long.valueOf(constituencyIds.get(i).toString()));
			}
			
			duplicateMobileNumbersDetailsList = surveyCompletedDetailsService
					.getDuplicateMobileNumbersDetails(
							jObj.getString("startDate"),
							jObj.getString("endDate"), selectedConstituencyIds,
							jObj.getLong("frequencyCount"));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String duplicateMobileNumbers()
	{
		constituenciesList = surveyCompletedDetailsService.getSurveyStartedConstituencyDetails();
		return Action.SUCCESS;
	}
	
	
}
