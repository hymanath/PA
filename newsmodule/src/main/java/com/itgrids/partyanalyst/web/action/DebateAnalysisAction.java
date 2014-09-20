package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DebateAnalysisAction extends ActionSupport implements ServletRequestAware {


	private static final long serialVersionUID = 303202624274758166L;


	private static final Logger 		LOG = Logger.getLogger(DebateAnalysisAction.class); 
	
	private HttpServletRequest 			request;
	private HttpSession 				session;

	private JSONObject 					jObj;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public String execute()
	{
		return Action.SUCCESS;
	}

}
