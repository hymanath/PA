package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyPageAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	public String execute() throws Exception{
		Log.debug("Entered into party page action");		
		
		return Action.SUCCESS;
	}

}
