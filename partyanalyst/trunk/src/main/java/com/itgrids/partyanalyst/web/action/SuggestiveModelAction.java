package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;

public class SuggestiveModelAction  implements ServletRequestAware {
	private HttpServletRequest request;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public String execute(){
		
		return Action.SUCCESS;
	}
}
