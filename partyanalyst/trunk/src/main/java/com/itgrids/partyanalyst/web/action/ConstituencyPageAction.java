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

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.impl.DelimitationConstituencyMandalService;
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
	 
	 private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	 private DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO;
	 private static final Logger log = Logger.getLogger(ConstituencyPageAction.class);
	 
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


	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}

	
	public DelimitationConstituencyMandalResultVO getDelimitationConstituencyMandalResultVO() {
		return delimitationConstituencyMandalResultVO;
	}

	public void setDelimitationConstituencyMandalResultVO(
			DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO) {
		this.delimitationConstituencyMandalResultVO = delimitationConstituencyMandalResultVO;
	}

	public String execute() throws Exception{
		
		constituencyDetails = constituencyPageService.getConstituencyDetails(constituencyId);
				
		constituencyElectionResultsVO = constituencyPageService.getConstituencyElectionResults(constituencyId);
		DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO = delimitationConstituencyMandalService.getMandalsForDelConstituency(constituencyId);
		log.info("delimitationConstituencyMandalResultVO.getMandals().size()::::"+delimitationConstituencyMandalResultVO.getPresentMandals().size());
		log.info("delimitationConstituencyMandalResultVO..getConstituencyType()::::"+delimitationConstituencyMandalResultVO.getConstituencyType());
		setDelimitationConstituencyMandalResultVO(delimitationConstituencyMandalResultVO);
		if(constituencyElectionResultsVO != null || constituencyDetails != null){
			//electionType = constituencyElectionResultsVO.get(0).getElectionType();
			return SUCCESS;
		}
		else
			return ERROR;
		
	}
}
