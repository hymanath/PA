/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 29, 2010
 */
package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Sai Krishna Basetti
 *
 */
public class AddProblemRegionSelectAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(AddProblemRegionSelectAction.class);
	
	transient private HttpServletRequest request;
	transient private HttpServletResponse response;
	transient private ServletContext context;
    private HttpSession session;

	private String regionType;
	private String regionValue;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}

	
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setServletResponse(final HttpServletResponse response) {
		this.response = response;
	}

	
	public ServletContext getContext() {
		return context;
	}

	public void setServletContext(final ServletContext context) {
		this.context = context;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(final HttpSession session) {
		this.session = session;
	}
	
	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(final String regionType) {
		this.regionType = regionType;
	}

	public String getRegionValue() {
		return regionValue;
	}

	public void setRegionValue(final String regionValue) {
		this.regionValue = regionValue;
	}

	public String execute(){
		
		
		return Action.SUCCESS;
	}
	
	public void getRegionsDataForConstituency(final Long constituencyId){
		
	}

}
