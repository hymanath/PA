/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.opensymphony.xwork2.ActionSupport;

public class CandidateElectionResultsAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long candidateId;
	private List<CandidateDetailsVO> candidateElectionDetails;
	private CandidateVO candidateVO;

	public CandidateVO getCandidateVO() {
		return candidateVO;
	}

	public void setCandidateVO(CandidateVO candidateVO) {
		this.candidateVO = candidateVO;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public List<CandidateDetailsVO> getCandidateElectionDetails() {
		return candidateElectionDetails;
	}

	public void setCandidateElectionDetails(
			List<CandidateDetailsVO> candidateElectionDetails) {
		this.candidateElectionDetails = candidateElectionDetails;
	}
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ICandidateDetailsService candidateDetailsService;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public String execute(){
		
		candidateVO = candidateDetailsService.getCandidateDetails(candidateId);
		
		candidateElectionDetails = candidateDetailsService.getCandidateElectionDetails(candidateId);
		
		if(candidateElectionDetails != null)
			return SUCCESS;
		else
			return ERROR;
	}

}
