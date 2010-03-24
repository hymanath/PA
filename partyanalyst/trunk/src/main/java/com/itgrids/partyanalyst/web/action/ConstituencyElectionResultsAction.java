/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 24, 2010
 */
package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ConstituencyElectionResultsAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3337107781995690081L;
	
	private static final Logger log = Logger.getLogger(ConstituencyElectionResultsAction.class);
	
	private String electionType;
	private String electionYear;
	private String constituencyId;
	private IStaticDataService staticDataService;
	private ConstituencyElectionResultsVO constituencyElectionResultsVO;
	
	HttpServletRequest request;
	HttpServletResponse response;
	 
	

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public ConstituencyElectionResultsVO getConstituencyElectionResultsVO() {
		return constituencyElectionResultsVO;
	}

	public void setConstituencyElectionResultsVO(
			ConstituencyElectionResultsVO constituencyElectionResultsVO) {
		this.constituencyElectionResultsVO = constituencyElectionResultsVO;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String execute(){
		
		log.info("Inside Execute Method ..");
		
		constituencyElectionResultsVO = staticDataService.getAllCandidatesDetailsForConstituency(new Long(constituencyId), "2009", "Assembly");
		
		if(constituencyElectionResultsVO == null)
			return Action.ERROR;
		
		return Action.SUCCESS;
	}

}
