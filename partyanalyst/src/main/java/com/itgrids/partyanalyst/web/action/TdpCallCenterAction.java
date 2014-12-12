package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.service.ITdpCallCenterService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TdpCallCenterAction  extends ActionSupport implements ServletRequestAware{
	private static final Logger LOG = Logger.getLogger(TdpCallCenterAction.class);
	private HttpServletRequest request;
	private String 		task;
	private JSONObject  jobj;
	private ITdpCallCenterService tdpCallCenterService;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getJobj() {
		return jobj;
	}
	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public ITdpCallCenterService getTdpCallCenterService() {
		return tdpCallCenterService;
	}
	public void setTdpCallCenterService(ITdpCallCenterService tdpCallCenterService) {
		this.tdpCallCenterService = tdpCallCenterService;
	}
	public String execute()
	{
		return Action.SUCCESS;
	}
	
	public String getCallCenterInfo()
	{
		try{
			
		}
		catch(Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	
 }
