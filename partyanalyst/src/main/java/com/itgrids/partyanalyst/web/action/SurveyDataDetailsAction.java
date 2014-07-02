package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
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
			resultStatus = surveyDataDetailsService.saveSurveyUserTabAssign(jObj.getLong("surveyUserId"), jObj.getString("tabNo"), jObj.getString("remarks"),convertDate);
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
			resultStatus = surveyDataDetailsService.saveSurveyUserType( jObj.getString("description"));
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
	
}
