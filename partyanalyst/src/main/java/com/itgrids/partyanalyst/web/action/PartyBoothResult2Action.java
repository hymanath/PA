package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class PartyBoothResult2Action extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private String constituencyName;
	private String partyName;
	private String electionYear;
	private String electionType;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private EntitlementsHelper entitlementsHelper;
	private PartyBoothPerformanceVO boothResult;

	
	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}


	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public PartyBoothPerformanceVO getBoothResult() {
		return boothResult;
	}


	public void setBoothResult(PartyBoothPerformanceVO boothResult) {
		this.boothResult = boothResult;
	}


	public String getConstituencyName() {
		return constituencyName;
	}


	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}


	public String getElectionYear() {
		return electionYear;
	}


	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	
	public String getPartyName() {
		return partyName;
	}


	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getElectionType() {
		return electionType;
	}


	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}


	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}


	public String execute(){
		
		HttpSession session = request.getSession();
		
		partyName = request.getParameter("partyName");
		electionYear = request.getParameter("electionYear");
		constituencyName = request.getParameter("constituencyName");
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return INPUT;
		
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_BOOTHWISE_RESULTS_REPORT) 
			|| !entitlementsHelper.checkForRegionToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CONSTITUENCY_LEVEL, new Long(constituencyName)))
			return ERROR;
				
		System.out.println(" values from ajax -------- partyName:"+partyName+" constituencyName:"+constituencyName+" electionYear:"+electionYear);
		List<PartyBoothPerformanceVO> boothResults = partyBoothWiseResultsService.getBoothWiseResultsForParty(new Long(partyName), new Long(constituencyName), electionYear);
		boothResult = partyBoothWiseResultsService.getVotingPercentageWiseBoothResult(boothResults.get(0),true);
		boothResult = partyBoothWiseResultsService.getVotingPercentageWiseBoothResult(boothResults.get(0),false);
		
		return SUCCESS;
	}

}
