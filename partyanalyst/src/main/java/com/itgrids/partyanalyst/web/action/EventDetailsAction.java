package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class EventDetailsAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	JSONObject jObj = null;
	private String task = null;
	private IMahaNaduService mahaNaduService;
	private ResultStatus resultStatus;
	private List<UserEventDetailsVO> subEvents;
	
	
	
	public List<UserEventDetailsVO> getSubEvents() {
		return subEvents;
	}
	public void setSubEvents(List<UserEventDetailsVO> subEvents) {
		this.subEvents = subEvents;
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
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public IMahaNaduService getMahaNaduService() {
		return mahaNaduService;
	}
	public void setMahaNaduService(IMahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}
	public String execute()
	{
		
		
		return Action.SUCCESS;
		
	}
	
	public String insertEventInfo()
	{
		try{
			resultStatus = mahaNaduService.insertDataintoEventInfo();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	public String getSubEventDetails()
	{
		try{
			jObj = new JSONObject(getTask());
	        subEvents = mahaNaduService.getSubEventInfo(jObj.getLong("parentEventId"),jObj.getLong("userId"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

}
