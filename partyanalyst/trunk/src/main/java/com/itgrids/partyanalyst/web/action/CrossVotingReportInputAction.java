package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CrossVotingReportInputAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private List<SelectOptionVO> electionYearList;
	private List<SelectOptionVO> parliamentList;
	private HttpServletRequest request;
	private EntitlementsHelper entitlementsHelper;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private String acId;
	private String pcId;
	private String electionYear;
	private String party;
	
	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}
	
	public List<SelectOptionVO> getElectionYearList() {
		return electionYearList;
	}

	public void setElectionYearList(List<SelectOptionVO> electionYearList) {
		this.electionYearList = electionYearList;
	}

	public List<SelectOptionVO> getParliamentList() {
		return parliamentList;
	}

	public void setParliamentList(List<SelectOptionVO> parliamentList) {
		this.parliamentList = parliamentList;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String getAcId() {
		return acId;
	}

	public String getPcId() {
		return pcId;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public String getParty() {
		return party;
	}

	public void setAcId(String acId) {
		this.acId = acId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String execute(){
		
		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CROSS_VOTING_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CROSS_VOTING_REPORT))
			return ERROR;
		
		electionYearList = new ArrayList<SelectOptionVO>();
		/*
		electionYearList.add(new SelectOptionVO(2009l, "2009"));
		electionYearList.add(new SelectOptionVO(2004l, "2004"));
		*/
		
		List<String> years = crossVotingEstimationService.getElectionYearsForBoothResult();
		
		for(String year : years)
			electionYearList.add(new SelectOptionVO(Long.parseLong(year), year));
		
		return Action.SUCCESS;
	}

}
