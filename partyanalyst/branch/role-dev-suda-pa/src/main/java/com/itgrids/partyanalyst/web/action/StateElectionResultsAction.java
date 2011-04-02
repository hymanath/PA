/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 06, 2009
 */
package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;

import com.itgrids.partyanalyst.dto.StateElectionResultsVO;
import com.itgrids.partyanalyst.service.IStatePageService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class StateElectionResultsAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private String electionId;
	
	private StateElectionResultsVO stateElectionResults;
	
	/*public String getElectionId() {
		return electionId;
	}

	public void setElectionId(String electionId) {
		this.electionId = electionId;
	}*/

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private IStatePageService statePageService;
	  

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	@JSON (serialize= false )
	public HttpServletRequest getRequest() {
		return request;
	}
	@JSON (serialize= false )
	public HttpServletResponse getResponse() {
		return response;
	}
    
	@JSON (serialize= false )
	public HttpSession getSession() {
		return session;
	}
	public StateElectionResultsVO getStateElectionResults() {
		return stateElectionResults;
	}

	public void setStateElectionResults(
			StateElectionResultsVO stateElectionResults) {
		this.stateElectionResults = stateElectionResults;
	}

	 
	public void setServletRequest(HttpServletRequest request) {
		
       this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		
       this.response = response;
	}
	
	public String execute(){
		

		String electionId = request.getParameter("electionId");
					
		stateElectionResults = statePageService.getStateElectionResults(Long.parseLong(electionId));
		    
		if(stateElectionResults != null)
	    {
		    return Action.SUCCESS;
		 }
		 
		return Action.ERROR;
	}
	
	@JSON (serialize= false )
	public IStatePageService getStatePageService() {
		return statePageService;
	}

	public void setStatePageService(IStatePageService statePageService) {
		this.statePageService = statePageService;
	}

	public String getJSON() throws JRException {
		//log.debug("partyPerformanceAjax action started...");
		return execute();
	}
}
