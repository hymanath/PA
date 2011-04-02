package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyInfluenceMainAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpSession session;
	
	private List<SelectOptionVO> electionTypes;
	private List<SelectOptionVO> states;	
	private List<String> electionYears;
	private List<SelectOptionVO> newParty;
	private List<SelectOptionVO> partyNames;
	private EntitlementsHelper entitlementsHelper;
	
	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setElectionTypes(List<SelectOptionVO> electionTypes) {
		this.electionTypes = electionTypes;
	}

	public List<SelectOptionVO> getStates() {
		return states;
	}

	public void setStates(List<SelectOptionVO> states) {
		this.states = states;
	}

	public List<String> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(List<String> electionYears) {
		this.electionYears = electionYears;
	}

	public List<SelectOptionVO> getNewParty() {
		return newParty;
	}

	public void setNewParty(List<SelectOptionVO> newParty) {
		this.newParty = newParty;
	}

	public List<SelectOptionVO> getPartyNames() {
		return partyNames;
	}

	public void setPartyNames(List<SelectOptionVO> partyNames) {
		this.partyNames = partyNames;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute(){
		
		session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_INFLUENCE_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_INFLUENCE_REPORT))
			return ERROR;
		
		electionTypes = new ArrayList<SelectOptionVO>();
		states = new ArrayList<SelectOptionVO>();
		electionYears = new ArrayList<String>();
		newParty = new ArrayList<SelectOptionVO>();
		partyNames = new ArrayList<SelectOptionVO>();
		
		// Setting Sample Elections
		electionTypes.add(new SelectOptionVO(1l, "Parliament"));
		electionTypes.add(new SelectOptionVO(2l, "Assembly"));
		
		// Setting Sample states
		states.add(new SelectOptionVO(1l, "Andhra Pradesh"));
		states.add(new SelectOptionVO(15l, "Maharashtra"));
		  
		// Setting Election Years
		electionYears.add("2009");
		electionYears.add("2004");			
		
		// Setting samples of New Party
		newParty.add(new SelectOptionVO(661l, "PRP"));
		newParty.add(new SelectOptionVO(513l, "Lok Satta"));
		
		//Setting party for party names
		partyNames.add(new SelectOptionVO(361l, "INC"));
		partyNames.add(new SelectOptionVO(163l, "BJP"));
		partyNames.add(new SelectOptionVO(265l, "CPI"));
		partyNames.add(new SelectOptionVO(871l, "TDP"));
		partyNames.add(new SelectOptionVO(885l, "TRS"));
				
		return Action.SUCCESS;
	}

	
}
