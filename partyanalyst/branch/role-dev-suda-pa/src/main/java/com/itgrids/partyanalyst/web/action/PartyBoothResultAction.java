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
import com.opensymphony.xwork2.ActionSupport;

public class PartyBoothResultAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private List<SelectOptionVO> partyVOs;
	private List<SelectOptionVO> electionTypes;
	private List<String> electionYears;
	private HttpServletRequest request;
	private EntitlementsHelper entitlementsHelper;
	public List<SelectOptionVO> getPartyVOs() {
		return partyVOs;
	}

	public void setPartyVOs(List<SelectOptionVO> partyVOs) {
		this.partyVOs = partyVOs;
	}
	

	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
	}

	public void setElectionTypes(List<SelectOptionVO> electionTypes) {
		this.electionTypes = electionTypes;
	}

	public List<String> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(List<String> electionYears) {
		this.electionYears = electionYears;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
		
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute()throws Exception{
		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return ERROR;
		electionTypes = new ArrayList<SelectOptionVO>();		
		SelectOptionVO electionType1 = new SelectOptionVO();
		electionType1.setId(new Long(1));
		electionType1.setName("Parliament");
		
		SelectOptionVO electionType2 = new SelectOptionVO();
		electionType2.setId(new Long(2));
		electionType2.setName("Assembly");
		
		electionTypes.add(electionType1);
		electionTypes.add(electionType2);
		setElectionTypes(electionTypes);
		
		electionYears = new ArrayList<String>();	
		electionYears.add("2009");
		electionYears.add("2008");
		electionYears.add("2006");
		electionYears.add("2004");
		setElectionYears(electionYears);
		System.out.println("before success party Booth results action");
		return SUCCESS;	
		
	}
	
	
	
}
