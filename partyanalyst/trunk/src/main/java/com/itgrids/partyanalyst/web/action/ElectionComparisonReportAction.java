package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.ElectionComparisonResultVO;
import com.itgrids.partyanalyst.dto.ElectionsComparisonVO;
import com.itgrids.partyanalyst.dto.PartyResultsPercentageVO;
import com.itgrids.partyanalyst.service.IElectionsComparisonService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ElectionComparisonReportAction extends ActionSupport implements ServletContextAware,
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private String electionType;
	private String state;
	private String party;
	private String electionYears1;
	private String electionYears2;
	
	private static final long serialVersionUID = 1L;
	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getElectionYears1() {
		return electionYears1;
	}

	public void setElectionYears1(String electionYears1) {
		this.electionYears1 = electionYears1;
	}

	public String getElectionYears2() {
		return electionYears2;
	}

	public void setElectionYears2(String electionYears2) {
		this.electionYears2 = electionYears2;
	}

	private HttpServletRequest request;
	private HttpServletResponse response;
	

	

	private ElectionsComparisonVO electionsComparisonVO = null;
	private List<ElectionComparisonResultVO> electionComparisonResultVO = null;
	private PartyResultsPercentageVO partyResultsPercentageForYear1;
	private PartyResultsPercentageVO partyResultsPercentageForYear2;
	public PartyResultsPercentageVO getPartyResultsPercentageForYear2() {
		return partyResultsPercentageForYear2;
	}

	public void setPartyResultsPercentageForYear2(
			PartyResultsPercentageVO partyResultsPercentageForYear2) {
		this.partyResultsPercentageForYear2 = partyResultsPercentageForYear2;
	}

	private IElectionsComparisonService electionsComparisonService;

	public ElectionsComparisonVO getElectionsComparisonVO() {
		return electionsComparisonVO;
	}

	public void setElectionsComparisonVO(ElectionsComparisonVO electionsComparisonVO) {
		this.electionsComparisonVO = electionsComparisonVO;
	}

	public PartyResultsPercentageVO getPartyResultsPercentageForYear1() {
		return partyResultsPercentageForYear1;
	}

	public void setPartyResultsPercentageForYear1(
			PartyResultsPercentageVO partyResultsPercentageForYear1) {
		this.partyResultsPercentageForYear1 = partyResultsPercentageForYear1;
	}

	public IElectionsComparisonService getElectionsComparisonService() {
		return electionsComparisonService;
	}

	public void setElectionsComparisonService(IElectionsComparisonService electionsComparisonService) {
		this.electionsComparisonService = electionsComparisonService;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub

	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public List<ElectionComparisonResultVO> getElectionComparisonResultVO() {
		return electionComparisonResultVO;
	}

	public void setElectionComparisonResultVO(
			List<ElectionComparisonResultVO> electionComparisonResultVO) {
		this.electionComparisonResultVO = electionComparisonResultVO;
	}

	public String execute()
	{
		
		Long electionScopeId = electionsComparisonService.getElectionScopeId(Long.parseLong(getElectionType()), Long.parseLong(getState()));
		
		if(electionScopeId != null){
			
			Boolean firstYear = electionsComparisonService.IsPartyParticipated(electionScopeId, Long.parseLong(getParty()), getElectionYears1());
			Boolean secondYear = electionsComparisonService.IsPartyParticipated(electionScopeId, Long.parseLong(getParty()), getElectionYears2());
			
			if(firstYear.equals(true) && secondYear.equals(true)){
				
				electionsComparisonVO = electionsComparisonService.getPartyElectionComparedResults(electionScopeId, Long.parseLong(getParty()), getElectionYears1(), getElectionYears2());
				electionComparisonResultVO = null;
				partyResultsPercentageForYear1 = electionsComparisonService.getPartyResultsPercentage(electionScopeId, Long.parseLong(getParty()), getElectionYears1());
				partyResultsPercentageForYear2 = electionsComparisonService.getPartyResultsPercentage(electionScopeId, Long.parseLong(getParty()), getElectionYears2());
				
			}
			else if(firstYear.equals(true) && secondYear.equals(false)){
				electionComparisonResultVO = electionsComparisonService.getPartyElectionResults(electionScopeId, Long.parseLong(getParty()), getElectionYears1());
				electionsComparisonVO = null;
				
			}
			else{
				electionComparisonResultVO = electionsComparisonService.getPartyElectionResults(electionScopeId, Long.parseLong(getParty()), getElectionYears2());
				electionsComparisonVO = null;
				
			}
			 
		}
		if(electionsComparisonVO != null || electionComparisonResultVO != null)
			return Action.SUCCESS;
		else
			return Action.ERROR;
	
	}
}
