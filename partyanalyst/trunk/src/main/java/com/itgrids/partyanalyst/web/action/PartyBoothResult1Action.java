package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyBoothResult1Action extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String task = null;
	private String partyName;
	private String electionType;
	private String electionYear;
	private String constituencyName;
	private PartyBoothPerformanceVO result;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> constituencyVOs;
	private HttpServletResponse response;

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

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

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<SelectOptionVO> getConstituencyVOs() {
		return constituencyVOs;
	}

	public void setConstituencyVOs(List<SelectOptionVO> constituencyVOs) {
		this.constituencyVOs = constituencyVOs;
	}

	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}

	public PartyBoothPerformanceVO getResult() {
		return result;
	}

	public void setResult(PartyBoothPerformanceVO result) {
		this.result = result;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getServletResponse() {
		return response;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String execute() throws Exception {

		System.out.println("In execute = "+electionType+" ,election year =  "+electionYear);
		constituencyVOs = partyBoothWiseResultsService.getConstituenciesForElectionScopeAndYear(new Long(electionType), new Long(electionYear));
		setConstituencyVOs(constituencyVOs);

		return Action.SUCCESS;
	}
	
	public String getParty() throws Exception{
	
		System.out.println("In get party = ");
		constituencyVOs = staticDataService.getPartiesForConstituency(new Long(constituencyName), electionYear);
		return Action.SUCCESS;
	}
	

}
