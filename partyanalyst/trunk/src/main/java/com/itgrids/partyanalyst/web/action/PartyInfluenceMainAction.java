package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.ServletConfigAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.DistrictWiseConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IPartyInfluenceService;

public class PartyInfluenceMainAction extends ActionSupport implements ServletContextAware,ServletConfigAware{

	private HttpServletRequest request;;
	private HttpSession session;
	
	private List<SelectOptionVO> electionTypes;
	private List<SelectOptionVO> states;	
	private List<String> electionYears;
	private List<SelectOptionVO> newParty;
	private List<SelectOptionVO> partyNames;
	
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletConfig(ServletConfig arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
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

	
	public String execute(){
		
		electionTypes = new ArrayList<SelectOptionVO>();
		states = new ArrayList<SelectOptionVO>();
		electionYears = new ArrayList<String>();
		newParty = new ArrayList<SelectOptionVO>();
		partyNames = new ArrayList<SelectOptionVO>();
		
		// Setting Sample Elections
		
		SelectOptionVO eType1 = new SelectOptionVO();
		eType1.setId(new Long(1));
		eType1.setName("Parliament");
		
		SelectOptionVO eType2 = new SelectOptionVO();
		eType2.setId(new Long(2));
		eType2.setName("Assembly");
		
		electionTypes.add(eType1);
		electionTypes.add(eType2);
		
		setElectionTypes(electionTypes);
		
		// Setting Sample states
		
		SelectOptionVO state1 = new SelectOptionVO();
		state1 .setId(new Long(1));
		state1 .setName("Andhra Pradesh");
		
		SelectOptionVO state2  = new SelectOptionVO();
		state2.setId(new Long(15));
		state2.setName("Maharashtra");
		
		states.add(state1);
		states.add(state2);
		
		setStates(states);
		  
		// Setting Election Years
		
		electionYears.add("2009");
		electionYears.add("2004");			
		
		setElectionYears(electionYears);
		
		// Setting samples of New Party
		
		SelectOptionVO nParty1 = new SelectOptionVO();
		nParty1.setId(new Long(43));
		nParty1.setName("PRP");
		
		SelectOptionVO nParty2  = new SelectOptionVO();
		nParty2.setId(new Long(32));
		nParty2.setName("Lok Satta");
		
		newParty.add(nParty1);
		newParty.add(nParty2);
		
		setNewParty(newParty);
		
		//Setting party for party names
		
		SelectOptionVO party1 = new SelectOptionVO();
		party1 .setId(new Long(24));
		party1 .setName("INC");
		
		SelectOptionVO party2  = new SelectOptionVO();
		party2.setId(new Long(15));
		party2.setName("BJP");
		
		SelectOptionVO party3  = new SelectOptionVO();
		party3.setId(new Long(17));
		party3.setName("CPI");
		
		SelectOptionVO party5  = new SelectOptionVO();
		party5.setId(new Long(62));
		party5.setName("TDP");
		
		SelectOptionVO party6  = new SelectOptionVO();
		party6.setId(new Long(61));
		party6.setName("TRS");
		
		partyNames.add(party1);
		partyNames.add(party2);
		partyNames.add(party3);
		partyNames.add(party5);
		partyNames.add(party6);
		
		setPartyNames(partyNames);
		
				
		return Action.SUCCESS;
	}

}
