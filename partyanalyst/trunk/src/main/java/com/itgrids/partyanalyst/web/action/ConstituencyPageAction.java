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

import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.opensymphony.xwork2.ActionSupport;

public class ConstituencyPageAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	 
	 private Long constituencyId;
	 private String constituencyName;
	 private String delimitation;
	 private List<ConstituencyElectionResultsVO> constituencyElectionResultsVO;
	 private ConstituencyInfoVO constituencyDetails;
	 private List<VotersWithDelimitationInfoVO> votersInfo;	 
	 private CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituency;
	 private IProblemManagementReportService problemManagementReportService;
	 private List<ProblemBeanVO> problemBean;
	 private String electionType;	 
	 private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	 private DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO;	
	 private static final Logger log = Logger.getLogger(ConstituencyPageAction.class);
	 
	 public String getDelimitation() {
		return delimitation;
	}

	public void setDelimitation(String delimitation) {
		this.delimitation = delimitation;
	}

		
	 public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}
		
	public List<ProblemBeanVO> getProblemBean() {
		return problemBean;
	}

	public void setProblemBean(List<ProblemBeanVO> problemBean) {
		this.problemBean = problemBean;
	}
	
	public CandidateDetailsForConstituencyTypesVO getCandidateDetailsForConstituency() {
			return candidateDetailsForConstituency;
	}

	public void setCandidateDetailsForConstituency(
			CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituency) {
		this.candidateDetailsForConstituency = candidateDetailsForConstituency;
	}
	
	 public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

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

	public List<VotersWithDelimitationInfoVO> getVotersInfo() {
		return votersInfo;
	}

	public void setVotersInfo(List<VotersWithDelimitationInfoVO> votersInfo) {
		this.votersInfo = votersInfo;
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
		
		Throwable ex = delimitationConstituencyMandalResultVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while retrieving mandal details ", ex);
		}
		
		log.info("delimitationConstituencyMandalResultVO.getMandals().size()::::"+delimitationConstituencyMandalResultVO.getPresentMandals().size());
		log.info("delimitationConstituencyMandalResultVO..getConstituencyType()::::"+delimitationConstituencyMandalResultVO.getConstituencyType());
		setDelimitationConstituencyMandalResultVO(delimitationConstituencyMandalResultVO);
		
		votersInfo = constituencyPageService.getVotersInfoInMandalsForConstituency(constituencyId);
		
		System.out.println("delimitation = >"+delimitation+"<");
		if(delimitation.equalsIgnoreCase(""))
			candidateDetailsForConstituency = constituencyPageService.getCandidateAndPartyInfoForConstituency(constituencyId, electionType);
		else
			candidateDetailsForConstituency  = null;
		
		problemBean = problemManagementReportService.getConstituencyProblemsInfo(constituencyId, 0L,"");
		if(problemBean != null)
			System.out.println("problemBean === "+problemBean.size());
		
		if(constituencyElectionResultsVO != null || constituencyDetails != null){
			//electionType = constituencyElectionResultsVO.get(0).getElectionType();
			return SUCCESS;
		}
		else
			return ERROR;
		
	}
}
