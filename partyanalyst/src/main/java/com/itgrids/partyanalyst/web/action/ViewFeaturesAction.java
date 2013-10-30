package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ViewFeaturesAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
		
	}
	
	public String execute() throws Exception
	{
		
		return Action.SUCCESS;
	}

}
