package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.excel.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.opensymphony.xwork2.ActionSupport;

public class PartyBoothResult2Action extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private String constituencyName;
	private String partyName;
	private String electionYear;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
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
	
	public String execute(){
		System.out.println("partyName:"+partyName+" constituencyName:"+constituencyName+" electionYear:"+electionYear);
		List<PartyBoothPerformanceVO> boothResults = partyBoothWiseResultsService.getBoothWiseResultsForParty(new Long(partyName), new Long(constituencyName), electionYear);
		boothResult = boothResults.get(0);
		return SUCCESS;
	}

}
