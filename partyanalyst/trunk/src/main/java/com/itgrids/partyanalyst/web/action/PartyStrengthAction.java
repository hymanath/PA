package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.PartiesDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
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
	private List<PartiesDetailsVO> requiredConstituencyDetails;
	private ElectionInfoVO electionInfo,excludingAlliance,includingAlliance,details;	
	private PartiesDetailsVO alliancesYears;
	private int errorCode = 0;
	private EntitlementsHelper entitlementsHelper;
	private static final org.apache.log4j.Logger log = Logger.getLogger(PartyStrengthAction.class);
	private String task = null;
	JSONObject jObj = null;
		

	//Getters and Setters

	public List<SelectOptionVO> getAllParties() {
		return allParties;
	}
	public ElectionInfoVO getDetails() {
		return details;
	}
	public void setDetails(ElectionInfoVO details) {
		this.details = details;
	}
	public PartiesDetailsVO getAlliancesYears() {
		return alliancesYears;
	}
	public void setAlliancesYears(PartiesDetailsVO alliancesYears) {
		this.alliancesYears = alliancesYears;
	}
	public ElectionInfoVO getIncludingAlliance() {
		return includingAlliance;
	}
	public void setIncludingAlliance(ElectionInfoVO includingAlliance) {
		this.includingAlliance = includingAlliance;
	}
	public ElectionInfoVO getExcludingAlliance() {
		return excludingAlliance;
	}
	public void setExcludingAlliance(ElectionInfoVO excludingAlliance) {
		this.excludingAlliance = excludingAlliance;
	}
	public List<PartiesDetailsVO> getRequiredConstituencyDetails() {
		return requiredConstituencyDetails;
	}
	public void setRequiredConstituencyDetails(
			List<PartiesDetailsVO> requiredConstituencyDetails) {
		this.requiredConstituencyDetails = requiredConstituencyDetails;
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
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
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
		
		try{
			
		HttpSession session = request.getSession();
		session = request.getSession();
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_STRENGTH_AND_WEAKNESS))
			return INPUT;
		
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_STRENGTH_AND_WEAKNESS))
			return ERROR;
		
		partyList = staticDataService.getStaticParties();
		
		partyListWithOutAll = staticDataService.getStaticParties();
		
		electionInfo = partyStrengthService.getPartiesData(IConstants.ASSEMBLY_ELECTION_TYPE,1l,7l,0L,IConstants.FALSE,0l,"");
		
		states = partyStrengthService.getAllStatesHavinElectionData(IConstants.ASSEMBLY_ELECTION_TYPE);	
		
		years = partyStrengthService.getCountOfElectionYears(1l,IConstants.ASSEMBLY_ELECTION_TYPE);
		
		}catch(Exception ex){
			
			log.error("Exception Raised :",ex);
		 return "failure";
		}
		
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
		//	Collections.sort(states);
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
			
			Long stateId = 0l;
			String electionType = jObj.getString("electionType");	
			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
				stateId =  new Long(jObj.getString("stateId"));	
			}
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
			electionInfo = partyStrengthService.getPartiesData(electionType,stateId,countOfElectionYears,partyId,IConstants.FALSE,0l,"");			
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
	
	public String getRequiredConstituenciesAjaxAction(){
		String param=null;		
		param=request.getParameter("task");
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			Long count = new Long(jObj.getString("count"));	
			String partyName = jObj.getString("partyName");
			String electionType = jObj.getString("electionType");
			Long stateId = new Long(jObj.getString("stateId"));	
			Long totalElectionYears = new Long(jObj.getString("elecYears"));	
			requiredConstituencyDetails = partyStrengthService.getAllConstituenciesData(electionType,stateId,partyName,totalElectionYears,count,null);        

		}catch(Exception e){
			e.printStackTrace();			
		}		
		return SUCCESS;  
	}
	
	
	public String getIncludingAllianceDataAjaxAction(){
		String param=null;		
		param=request.getParameter("task");
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {				
			Long partyId = jObj.getLong("partyId");
			String electionType = jObj.getString("electionType");
			Long stateId = new Long(jObj.getString("stateId"));	
			Long totalElectionYears = new Long(jObj.getString("elecYears"));	
			Long  electionId = jObj.getLong("electionId"); 
			String partyName = jObj.getString("partyName");
			includingAlliance = partyStrengthService.getIncludingAllianceDetailsForAParty(electionType,stateId,partyId,totalElectionYears,electionId,partyName);
			//includingAlliance = partyStrengthService.getPartiesData(electionType,stateId,totalElectionYears,partyId,IConstants.TRUE,electionId,partyName);
			
			}catch(Exception e){
			e.printStackTrace();			
		}		
		return SUCCESS;  
	}
	
	
	public String getExcludingAllianceDataAjaxAction(){
		String param=null;		
		param=request.getParameter("task");
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			Long partyId = jObj.getLong("partyName");
			String electionType = jObj.getString("electionType");
			Long stateId = new Long(jObj.getString("stateId"));	
			Long totalElectionYears = new Long(jObj.getString("elecYears"));
			String partyName = jObj.getString("partyShortName");			
			
			excludingAlliance = partyStrengthService.getPartiesData(electionType,stateId,totalElectionYears,partyId,IConstants.FALSE,0l,partyName);
		}catch(Exception e){
			e.printStackTrace();			
		}		
		return SUCCESS;  
	}
	
	public String getAllAllianceYearsForAPartyAjaxAction(){
		String param=null;		
		param=request.getParameter("task");
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			Long partyId = jObj.getLong("partyId");		
			Long stateId = jObj.getLong("stateId");
			String partyName = jObj.getString("partyName");
			alliancesYears = partyStrengthService.getAllElectionAllianceYearsForAParty(partyId,stateId,partyName);			
		}catch(Exception e){
			e.printStackTrace();			
		}		
		return SUCCESS;  
	}

	
	public String getDataMatchingCriteriaAjaxAction(){
		String param=null;		
		param=request.getParameter("task");
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			Long selectedNoOfYears = jObj.getLong("selectedNoOfYears");	
			String electionType = jObj.getString("electionType");	
			Long stateId = jObj.getLong("stateId");
			String searchType = jObj.getString("searchType");			
			String searchText = jObj.getString("searchText");
			
			details = partyStrengthService.getRequiredMatchingConstituencies(selectedNoOfYears,electionType,stateId,searchType,searchText);			
		}catch(Exception e){
			e.printStackTrace();			
		}		
		return SUCCESS;  
	}
	
}
