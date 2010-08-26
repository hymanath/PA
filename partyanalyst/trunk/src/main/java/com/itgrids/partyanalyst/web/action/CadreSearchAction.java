package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreSearchAction extends ActionSupport implements ServletRequestAware 
{
	private HttpServletRequest request;
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	public String execute() throws Exception
	{
		
		return Action.SUCCESS;
	}
	

}
