package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.AllBoothsResultsForAPartyInAMandal;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.opensymphony.xwork2.ActionSupport;

public class AllBoothsResultsInTehsilAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private static final Logger LOG = Logger.getLogger(AllBoothsResultsInTehsilAction.class);
	private IBiElectionPageService biElectionPageService; 
	private String constituencyId;
	private String partyId;
	private String tehsilId;
	private String electionType;
	private String electionYear;
	private AllBoothsResultsForAPartyInAMandal allBoothsResultsForAPartyInAMandal;
	private String mandalName;
	
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(final HttpServletRequest request) {
		this.request = request;
	}

	public IBiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}

	public void setBiElectionPageService(final 
			IBiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}

	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(final String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(final String partyId) {
		this.partyId = partyId;
	}

	public String getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(final String tehsilId) {
		this.tehsilId = tehsilId;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(final String electionType) {
		this.electionType = electionType;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(final String electionYear) {
		this.electionYear = electionYear;
	}	
	
	public AllBoothsResultsForAPartyInAMandal getAllBoothsResultsForAPartyInAMandal() {
		return allBoothsResultsForAPartyInAMandal;
	}

	public void setAllBoothsResultsForAPartyInAMandal(final 
			AllBoothsResultsForAPartyInAMandal allBoothsResultsForAPartyInAMandal) {
		this.allBoothsResultsForAPartyInAMandal = allBoothsResultsForAPartyInAMandal;
	}

	public void setMandalName(final String mandalName) {
		this.mandalName = mandalName;
	}

	public String getMandalName() {
		return mandalName;
	}

	public String execute () 
	{
		LOG.debug("Entered in to execute method");
		allBoothsResultsForAPartyInAMandal = biElectionPageService.getAllBoothsResultsInAConstituency(Long.valueOf(tehsilId),Long.valueOf(partyId),Long.valueOf(constituencyId), electionYear, electionType);
		LOG.debug("results Size:::"+ allBoothsResultsForAPartyInAMandal.getBoothResults().size());
		return SUCCESS;
	}
	
}
