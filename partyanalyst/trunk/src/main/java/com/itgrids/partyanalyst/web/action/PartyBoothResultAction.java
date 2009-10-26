package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.opensymphony.xwork2.ActionSupport;

public class PartyBoothResultAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private List<SelectOptionVO> partyVOs;
	private List<SelectOptionVO> electionTypes;
	private List<String> electionYears;
	private HttpServletRequest request;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	
	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}

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
	
	
	
	//modified By Dp
	
	public String execute()throws Exception{
		
		System.out.println("In execute party Booth results action");
		partyVOs = partyBoothWiseResultsService.getParties();
		
		electionTypes = new ArrayList<SelectOptionVO>();		
		SelectOptionVO electionType = new SelectOptionVO();
		electionType.setId(new Long(2));
		electionType.setName("Assembly");
		electionTypes.add(electionType);
		setElectionTypes(electionTypes);
		
		electionYears = new ArrayList<String>();		
		electionYears.add("2004");
		setElectionYears(electionYears);
		System.out.println("before success party Booth results action");
		
		/*String param = getTask();
		try {
			JSONObject task1 = new JSONObject(param);
			System.out.println(task1);
			this.setPartyName(task1.getString("partyId"));
			this.setElectionType(task1.getString("electionType"));
			this.setElectionYear(task1.getString("electionYear"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Party Name with getter = "+this.getPartyName());
		System.out.println("Party Name with parameter = "+partyName);
		constituencyVOs = partyBoothWiseResultsService.getConstituenciesForParty(new Long(this.getPartyName()),new Long(this.getElectionType()), this.getElectionYear());
		
		setConstituencyVOs(constituencyVOs);
	System.out.println("Before party ************ action"+constituencyVOs.size());*/
		return SUCCESS;	
		
	}
	
	
	
}
