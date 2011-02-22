package com.itgrids.partyanalyst.web.action;

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
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyStrengthAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3646096297094114365L;

	//Instance Variables
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private IStaticDataService staticDataService; 
	private IPartyStrengthService partyStrengthService;
	
	private List<SelectOptionVO> partyList,partyListWithOutAll,electionTypes;
	private String electionType;
	private String state;
	private String electionYears;
	private String partyRadio;
	private String party;
	
	private ElectionInfoVO electionInfo;
	
		
	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
	}
	public void setElectionTypes(List<SelectOptionVO> electionTypes) {
		this.electionTypes = electionTypes;
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
		
		partyList = staticDataService.getStaticParties();
		partyList.add(0,new SelectOptionVO(0l,"All"));
		
		partyListWithOutAll = staticDataService.getStaticParties();
		
		electionInfo = partyStrengthService.getPartiesData(IConstants.ASSEMBLY_ELECTION_TYPE,1l,5l,"All",0L);
		
		System.out.println("electionType---->"+electionType);
		System.out.println("electionYears---->"+electionYears);
		System.out.println("state---->"+state);
		System.out.println("partyRadio---->"+partyRadio);
		System.out.println("party---->"+party);		
		
		return Action.SUCCESS;
	}
	

}
