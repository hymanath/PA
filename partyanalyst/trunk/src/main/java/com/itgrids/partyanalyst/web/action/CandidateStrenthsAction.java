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
	private Long elecId;
	private Long partyId;
	private String type;
	private String excludeType;
	
	private IPartyStrengthService partyStrengthService;
	
	private List<PartiesDetailsVO> requiredConstituencyDetails;
	
	
	
	//Getters and Setters
	
	
	public List<PartiesDetailsVO> getRequiredConstituencyDetails() {
		return requiredConstituencyDetails;
	}
	public String getExcludeType() {
		return excludeType;
	}
	public void setExcludeType(String excludeType) {
		this.excludeType = excludeType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getElecId() {
		return elecId;
	}
	public void setElecId(Long elecId) {
		this.elecId = elecId;
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
		
		if(type==null)
			if(partyId==null)
				requiredConstituencyDetails = partyStrengthService.getAllConstituenciesData(electionType,selectedStateElmts,partyName,elecYears,columnId,excludeType);	
			else
				requiredConstituencyDetails = partyStrengthService.getAllCandidatesDetailsForAllianceData(electionType,selectedStateElmts,partyId,elecYears,elecId,partyName,columnId);				
		else
			//requiredConstituencyDetails = partyStrengthService.getWeaknessConstituenceisDetails(electionType,selectedStateElmts,type,partyId,columnId,elecYears,elecId,partyName);
			requiredConstituencyDetails = partyStrengthService.getWeaknessConstituenceisDetails1(electionType,selectedStateElmts,type,partyId,columnId,elecYears,elecId,partyName);
		////getWeaknessConstituenceisDetails(String electionType,Long stateId,String type,Long partyId,Long colId,Long totalElectionYears,Long electionId,String partyName)
		return Action.SUCCESS;
	}
}
