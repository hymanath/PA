/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.opensymphony.xwork2.ActionSupport;

public class ConstituencyPageAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	 
	 private Long constituencyId;
	 private List<ConstituencyElectionResultsVO> constituencyElectionResultsVO;
	 private ConstituencyInfoVO constituencyDetails;
	 private String electionType;
	 
	 public String getElectionType() {
			return electionType;
	}

	 public void setElectionType(String electionType) {
			this.electionType = electionType;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public List<ConstituencyElectionResultsVO> getConstituencyElectionResultsVO() {
		return constituencyElectionResultsVO;
	}

	public void setConstituencyElectionResultsVO(
			List<ConstituencyElectionResultsVO> constituencyElectionResultsVO) {
		this.constituencyElectionResultsVO = constituencyElectionResultsVO;
	}

	public ConstituencyInfoVO getConstituencyDetails() {
		return constituencyDetails;
	}

	public void setConstituencyDetails(ConstituencyInfoVO constituencyDetails) {
		this.constituencyDetails = constituencyDetails;
	}

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	IConstituencyPageService constituencyPageService;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public String execute() throws Exception{
		
		constituencyDetails = constituencyPageService.getConstituencyDetails(constituencyId);
				
		constituencyElectionResultsVO = constituencyPageService.getConstituencyElectionResults(constituencyId);
		
		if(constituencyElectionResultsVO != null || constituencyDetails != null){
			//electionType = constituencyElectionResultsVO.get(0).getElectionType();
			return SUCCESS;
		}
		else
			return ERROR;
		
	}
}
