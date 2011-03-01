package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
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
	
	private List<SelectOptionVO> partyList,partyListWithOutAll,electionTypes,allParties,states,years;
	private String electionType;
	private String state;
	private String electionYears;
	private String partyRadio;
	private String party;
	
	private ElectionInfoVO electionInfo;
	
	private int errorCode = 0;
	
	
	private String task = null;
	JSONObject jObj = null;
		

	//Getters and Setters
	
	public List<SelectOptionVO> getAllParties() {
		return allParties;
	}
	public void setAllParties(List<SelectOptionVO> allParties) {
		this.allParties = allParties;
	}
	public List<SelectOptionVO> getStates() {
		return states;
	}
	public List<SelectOptionVO> getYears() {
		return years;
	}

	public void setYears(List<SelectOptionVO> years) {
		this.years = years;
	}

	public void setStates(List<SelectOptionVO> states) {
		this.states = states;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getJObj() {
		return jObj;
	}
	public void setJObj(JSONObject obj) {
		jObj = obj;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}	
	
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
		//partyList.add(0,new SelectOptionVO(0l,"All Parties"));
		
		partyListWithOutAll = staticDataService.getStaticParties();
		
		electionInfo = partyStrengthService.getPartiesData(IConstants.ASSEMBLY_ELECTION_TYPE,1l,7l,0L);
		
		states = partyStrengthService.getAllStatesHavinElectionData(IConstants.ASSEMBLY_ELECTION_TYPE);	
		
		years = partyStrengthService.getCountOfElectionYears(1l,IConstants.ASSEMBLY_ELECTION_TYPE);
		
		return Action.SUCCESS;
	}
	

	public String getStatesData(){
		String param=null;		
		param=request.getParameter("task");
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		
		try {
			jObj = new JSONObject(getTask());	
					
			states = partyStrengthService.getAllStatesHavinElectionData(jObj.getString("electionType"));	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;  
	}
	
	public String getAllElections(){
		
		String param=null;		
		param=request.getParameter("task");
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		
		try {
			jObj = new JSONObject(getTask());	
			
			Long stateId = new Long(jObj.getString("stateId"));	
			String electionType = jObj.getString("electionType");	
			
			years = partyStrengthService.getCountOfElectionYears(stateId,electionType);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return SUCCESS;  
	}
	
	public String getElectionDetails(){
		
		String param=null;		
		param=request.getParameter("task");
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			Long stateId = new Long(jObj.getString("stateId"));	
			String electionType = jObj.getString("electionType");	
			Long countOfElectionYears  = new Long(jObj.getString("electionYears"));
			Long partyId = new Long(jObj.getString("party"));	
			
			jObj = new JSONObject(getTask());	
			electionInfo = partyStrengthService.getPartiesData(electionType,stateId,countOfElectionYears,partyId);
			
		}catch(Exception e){
			e.printStackTrace();			
		}		
		return SUCCESS;  		
	}
	
	public String getAllPartiesMatchingCriteria(){
		
		String param=null;		
		param=request.getParameter("task");
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			Long stateId = new Long(jObj.getString("stateId"));	
			String electionType = jObj.getString("electionType");
			
			allParties = partyStrengthService.getAllPartiesData(stateId);
		}catch(Exception e){
			e.printStackTrace();			
		}		
		return SUCCESS;  		
	}
	
	
}
