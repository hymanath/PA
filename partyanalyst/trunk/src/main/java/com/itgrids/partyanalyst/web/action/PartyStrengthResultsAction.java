package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IPartyStrengthService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.SelectOptionVOComparator;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyStrengthResultsAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private IStaticDataService staticDataService; 
	private IPartyStrengthService partyStrengthService;
	
	private List<SelectOptionVO> partyList,partyListWithOutAll;
	private String electionType;
	private String state;
	private String electionYears;
	private String partyRadio;
	private String party;
	
	private ElectionInfoVO electionInfo;
	
	private int errorCode;
		
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public List<SelectOptionVO> getPartyListWithOutAll() {
		return partyListWithOutAll;
	}
	public void setPartyListWithOutAll(List<SelectOptionVO> partyListWithOutAll) {
		this.partyListWithOutAll = partyListWithOutAll;
	}
	public ElectionInfoVO getElectionInfo() {
		return electionInfo;
	}
	public void setElectionInfo(ElectionInfoVO electionInfo) {
		this.electionInfo = electionInfo;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getElectionYears() {
		return electionYears;
	}
	public void setElectionYears(String electionYears) {
		this.electionYears = electionYears;
	}
	public String getPartyRadio() {
		return partyRadio;
	}
	public void setPartyRadio(String partyRadio) {
		this.partyRadio = partyRadio;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}
	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response =  response;
	}


	public String execute(){
		String partyName = new String();
		partyList = staticDataService.getStaticParties();
		partyList.add(0,new SelectOptionVO(0l,"All Parties"));
		
		for(int i=0;i<partyList.size();i++){
			if(partyList.get(i).getId().equals(new Long(party)));
			   partyName = partyList.get(i).getName();
		}
		if(party.equalsIgnoreCase("0")){
			partyListWithOutAll = staticDataService.getStaticParties();
			Collections.sort(partyListWithOutAll,new SelectOptionVOComparator());
		}
		else{
			partyListWithOutAll = new ArrayList<SelectOptionVO>();
			partyListWithOutAll.add(new SelectOptionVO(new Long(party),partyName));
		}
			
		electionInfo = partyStrengthService.getPartiesData(electionType,new Long(state.toString()),new Long(electionYears.toString()),electionType,new Long(party.toString()));
		
		return Action.SUCCESS;
	}
	
	public void validate(){
		if(electionType.equalsIgnoreCase("0")){
			addFieldError("electionType","Select ElectionType");
		}
		if(electionYears.equalsIgnoreCase("0")){
			addFieldError("electionYears","Select Election Year");
		}
		if(state.equalsIgnoreCase("0")){
			addFieldError("state","Select a State");
		}
		if(party.equalsIgnoreCase("-1")){
			addFieldError("party","Select Party");
		}
		
		if(hasFieldErrors())
			errorCode = 1;
		
		partyList = staticDataService.getStaticParties();
		partyList.add(0,new SelectOptionVO(0l,"All"));
	}
}
