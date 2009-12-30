/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 06, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.googlecode.jsonplugin.annotations.JSON;
import com.itgrids.partyanalyst.dto.StateElectionResultsVO;
import com.itgrids.partyanalyst.service.IStateElectionResultsService;
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
	private IStateElectionResultsService stateElectionResultsService;
	  

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

	
	@JSON (serialize= false )
	public IStateElectionResultsService getStateElectionResultsService() {
		return stateElectionResultsService;
	}

	public void setStateElectionResultsService(
			IStateElectionResultsService stateElectionResultsService) {
		this.stateElectionResultsService = stateElectionResultsService;
	}
	
	
	public String execute(){
		

		String electionId = request.getParameter("electionId");
					
		stateElectionResults = stateElectionResultsService.getStateElectionResults(Long.parseLong(electionId));
		    
		if(stateElectionResults != null)
	    {
		    return Action.SUCCESS;
		 }
		 
		return Action.ERROR;
	}
	
	public String getJSON() throws JRException {
		//log.debug("partyPerformanceAjax action started...");
		return execute();
	}
}
