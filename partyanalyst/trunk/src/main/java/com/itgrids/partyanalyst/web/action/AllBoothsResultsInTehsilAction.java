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
	private static final Logger log = Logger.getLogger(AllBoothsResultsInTehsilAction.class);
	private IBiElectionPageService biElectionPageService; 
	private String constituencyId;
	private String partyId;
	private String tehsilId;
	private String electionType;
	private String electionYear;
	private AllBoothsResultsForAPartyInAMandal allBoothsResultsForAPartyInAMandal;
	private String mandalName;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IBiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}

	public void setBiElectionPageService(
			IBiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}

	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
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
	
	public AllBoothsResultsForAPartyInAMandal getAllBoothsResultsForAPartyInAMandal() {
		return allBoothsResultsForAPartyInAMandal;
	}

	public void setAllBoothsResultsForAPartyInAMandal(
			AllBoothsResultsForAPartyInAMandal allBoothsResultsForAPartyInAMandal) {
		this.allBoothsResultsForAPartyInAMandal = allBoothsResultsForAPartyInAMandal;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String getMandalName() {
		return mandalName;
	}

	public String execute () 
	{
		log.debug("Entered in to execute method");
		allBoothsResultsForAPartyInAMandal = biElectionPageService.getAllBoothsResultsInAConstituency(new Long(tehsilId), new Long(partyId), new Long(constituencyId), electionYear, electionType);
		log.debug("results Size:::"+ allBoothsResultsForAPartyInAMandal.getBoothResults().size());
		return SUCCESS;
	}
	
}
