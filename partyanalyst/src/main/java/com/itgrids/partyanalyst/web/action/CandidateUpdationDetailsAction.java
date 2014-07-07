package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class CandidateUpdationDetailsAction extends ActionSupport implements ServletRequestAware 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}
