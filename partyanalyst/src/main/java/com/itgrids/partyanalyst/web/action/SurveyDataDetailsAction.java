package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.UserBoothDetailsVO;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyDataDetailsAction extends ActionSupport implements ServletRequestAware{

	
	private static final long serialVersionUID = -707498402789127163L;
	public static final Logger LOG = Logger.getLogger(SurveyDataDetailsAction.class);
	HttpServletRequest request;
	private String task;
	private JSONObject jObj;
	private ResultStatus resultStatus;
	private List<GenericVO> returnList;
	@Autowired
	private ISurveyDataDetailsService surveyDataDetailsService;
	private List<UserBoothDetailsVO> assgnedBoothsList;
	private List<SurveyReportVO> dayWiseReportList;
	private List<SelectOptionVO> constituenciesList;
	private List<SurveyReportVO> boothWiseCountList;
	
	public List<SurveyReportVO> getBoothWiseCountList() {
		return boothWiseCountList;
	}

	public void setBoothWiseCountList(List<SurveyReportVO> boothWiseCountList) {
		this.boothWiseCountList = boothWiseCountList;
	}

	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public List<SurveyReportVO> getDayWiseReportList() {
		return dayWiseReportList;
	}

	public void setDayWiseReportList(List<SurveyReportVO> dayWiseReportList) {
		this.dayWiseReportList = dayWiseReportList;
	}

	public List<UserBoothDetailsVO> getAssgnedBoothsList() {
		return assgnedBoothsList;
	}

	public void setAssgnedBoothsList(List<UserBoothDetailsVO> assgnedBoothsList) {
		this.assgnedBoothsList = assgnedBoothsList;
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
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	
	public List<GenericVO> getReturnList() {
		return returnList;
	}
	public void setReturnList(List<GenericVO> returnList) {
		this.returnList = returnList;
	}
	public String execute()
	{
		constituenciesList = 	surveyDataDetailsService.getAllAssemblyConstituenciesByStateId();

		return Action.SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	public String saveSurveyUser()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			resultStatus = surveyDataDetailsService.saveSurveyUser(jObj.getString("firstName"), jObj.getString("lastName"), jObj.getString("userName"), jObj.getString("password"), jObj.getString("address"), jObj.getString("mobileNo"), jObj.getLong("userType"));
		} 
		catch (Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	public String assignTab()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
			{
				return Action.INPUT;
			}
			Long userId = user.getRegistrationID();
			jObj = new JSONObject(getTask());
			String dateObj = jObj.getString("date");
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd" );
			Date Date;
			
			Date = originalFormat.parse(dateObj);
			Date convertDate= targetFormat.parse(targetFormat.format(Date));	
			resultStatus = surveyDataDetailsService.saveSurveyUserTabAssign( jObj.getLong("surveyUserId"), jObj.getString("tabNo"), jObj.getString("remarks"),convertDate);
		} 
		catch (Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	public String deactiveSurveyUser()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			resultStatus = surveyDataDetailsService.deactivateUser( jObj.getLong("userId"),jObj.getString("remarks"));
		} 
		catch (Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	public String saveSurveyUserType()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			resultStatus = surveyDataDetailsService.saveSurveyUserType( jObj.getString("description"), jObj.getString("userType"));
		} 
		catch (Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyUserType()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getUserTypes();
		} 
		catch (Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyUsersByUserType()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getSurveyUsersByUserType(jObj.getLong("userTypeId"));
		} 
		catch (Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	public String saveServeyUserRelationDetails()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			resultStatus = surveyDataDetailsService.saveServeyUserRelationDetails(jObj.getLong("userTypeId"),jObj.getLong("userId"),jObj.getLong("leaderId"),jObj.getLong("constituencyId"));
		} 
		catch (Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	
	public String getSurveyLeaders()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getConstituencyWiseLeaders();
		} 
		catch (Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyUsersByLeaders()
	{
		try
		{
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			{
				return Action.INPUT;
			}
			jObj = new JSONObject(getTask());
			returnList = surveyDataDetailsService.getSurveyUsersByLeades(jObj.getLong("leaderId"),jObj.getLong("constituencyId"));
		} 
		catch (Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	public String getAssignedBoothsDetailsByConstituencyIdAndUserId()
	{
		try {
			
			jObj = new JSONObject(getTask());
			
			Long constituencyId = jObj.getLong("constituencyId");
			Long userId = jObj.getLong("userId");
			
			assgnedBoothsList = surveyDataDetailsService.getAssignedBoothsDetailsByConstituencyIdAndUserId(constituencyId,userId);
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
		
	}
	
	public String saveUserAssignedBoothsDetails()
	{
		
		try {
			jObj = new JSONObject(getTask());
			
			 JSONArray boothDetails = jObj.getJSONArray("boothIds");
			 
			 Long surveyUserId = jObj.getLong("surveyUserId");
			 Long constituencyId = jObj.getLong("constituencyId");
			 
			 List<Long> boothIds = new ArrayList<Long>();
			 
			for(int i = 0 ; i < boothDetails.length() ; i++)
			{
				boothIds.add(Long.valueOf(boothDetails.get(i).toString()));
			}
			
			resultStatus = surveyDataDetailsService.saveSurveyUserBoothAssign(surveyUserId,constituencyId,boothIds);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}
	
	public String getDayWisereportDetailsByConstituencyId()
	{
		try {
			jObj = new JSONObject(getTask());
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			
			dayWiseReportList = surveyDataDetailsService
					.getDayWisereportDetailsByConstituencyId(jObj
							.getLong("constituencyId"),startDate,endDate);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getBoothWiseUserSamplesDetailsByDates()
	{
		try {
			
			jObj = new JSONObject(getTask());
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			Long userId = jObj.getLong("userId");
			
			boothWiseCountList = surveyDataDetailsService.getBoothWiseUserSamplesDetailsByDates(userId,startDate,endDate);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
		
	}

}
