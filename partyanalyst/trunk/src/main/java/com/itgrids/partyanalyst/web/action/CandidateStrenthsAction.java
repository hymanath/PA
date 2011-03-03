package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dto.PartiesDetailsVO;
import com.itgrids.partyanalyst.service.IPartyStrengthService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CandidateStrenthsAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// instance variables declaration block
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String electionType;
	private Long selectedStateElmts;
	private String partyName;
	private Long elecYears;
	private Long columnId;
	
	private IPartyStrengthService partyStrengthService;
	
	private List<PartiesDetailsVO> requiredConstituencyDetails;
	
	public List<PartiesDetailsVO> getRequiredConstituencyDetails() {
		return requiredConstituencyDetails;
	}
	public void setRequiredConstituencyDetails(
			List<PartiesDetailsVO> requiredConstituencyDetails) {
		this.requiredConstituencyDetails = requiredConstituencyDetails;
	}
	public IPartyStrengthService getPartyStrengthService() {
		return partyStrengthService;
	}
	public void setPartyStrengthService(IPartyStrengthService partyStrengthService) {
		this.partyStrengthService = partyStrengthService;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	
	public Long getSelectedStateElmts() {
		return selectedStateElmts;
	}
	public void setSelectedStateElmts(Long selectedStateElmts) {
		this.selectedStateElmts = selectedStateElmts;
	}

	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Long getElecYears() {
		return elecYears;
	}
	public void setElecYears(Long elecYears) {
		this.elecYears = elecYears;
	}

	public Long getColumnId() {
		return columnId;
	}
	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String execute(){
		http://localhost/PartyAnalyst/CandidateStrenthsAction.action?electionType=Assembly&selectedStateElmts=1&partyName=TRS&elecYears=7&columnId=1
		/*Long count = new Long(jObj.getString("count"));	
		String partyName = jObj.getString("partyName");
		String electionType = jObj.getString("electionType");
		Long stateId = new Long(jObj.getString("stateId"));	
		Long totalElectionYears = new Long(jObj.getString("elecYears"));*/	
		requiredConstituencyDetails = partyStrengthService.getAllConstituenciesData(electionType,selectedStateElmts,partyName,elecYears,columnId);			

		return Action.SUCCESS;
	}
}
