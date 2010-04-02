package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CandidateDetailsForElectionDetailsReportAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CandidateDetailsForElectionDetailsReportAction.class);
	private HttpServletRequest request;
	private String electionId;
	private String stateID;
	private String electionType;
	private String stateName;
	private String year;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getElectionId() {
		return electionId;
	}

	public void setElectionId(String electionId) {
		this.electionId = electionId;
	}

	public String getStateID() {
		return stateID;
	}

	public void setStateID(String stateID) {
		this.stateID = stateID;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public static Logger getLog() {
		return log;
	}
	
	public String execute () throws Exception 
	{
		return Action.SUCCESS;
		
	}
	
	

}
