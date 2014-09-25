package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.service.INewsBullitionService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class NewsBullitionAction extends ActionSupport implements ServletRequestAware{

	
	private static final long           serialVersionUID = 1L;
	private HttpServletRequest          request;
	private HttpSession 				session;	
	private INewsBullitionService       newsBullitionService;
	
	private String 				        task;
	private JSONObject 					jObj;
	
	
	private static final Logger LOG = Logger.getLogger(NewsBullitionAction.class);
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public INewsBullitionService getNewsBullitionService() {
		return newsBullitionService;
	}

	public void setNewsBullitionService(INewsBullitionService newsBullitionService) {
		this.newsBullitionService = newsBullitionService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String execute()
	{
		try {
			
		} catch (Exception e) {
			LOG.error("Exception raised in execute method in NewsBullitionAction Action", e);
		}
		return Action.SUCCESS;
	}

}
