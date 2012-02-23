package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PositionManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IElectionResultsUpdationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AssignCandidateToElectionAction extends ActionSupport implements ServletRequestAware ,ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6925944204324568325L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private JSONObject jObj;
	private String task;
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> optionVOList;
	private List<SelectOptionVO> statesList;
	private ICandidateDetailsService candidateDetailsService;
	private IElectionResultsUpdationService electionResultsUpdationService;
	private EntitlementsHelper entitlementsHelper;
	private ResultStatus resultStatus;
	
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}	
	
	public IElectionResultsUpdationService getElectionResultsUpdationService() {
		return electionResultsUpdationService;
	}

	public void setElectionResultsUpdationService(
			IElectionResultsUpdationService electionResultsUpdationService) {
		this.electionResultsUpdationService = electionResultsUpdationService;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTask() {
		return task;
	}

	public void setJObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setOptionVOList(List<SelectOptionVO> optionVOList) {
		this.optionVOList = optionVOList;
	}

	public List<SelectOptionVO> getOptionVOList() {
		return optionVOList;
	}
	

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute(){

		HttpSession session = request.getSession();
	
	if(session.getAttribute(IConstants.USER) == null && 
			!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ASSIGN_A_ELECTION_GOVERNING_BODY))
		return IConstants.NOT_LOGGED_IN;
	if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ASSIGN_A_ELECTION_GOVERNING_BODY))
		return ERROR;
		statesList = staticDataService.getParticipatedStatesForAnElectionType(2l);
		
		return SUCCESS;
		
	}
	
	public String ajaxCallHandlerForAssignCandidateToElection(){
		
		try {
			jObj = new JSONObject(getTask());
			
			
			if(jObj.getString("task").equalsIgnoreCase("getStates")){
				Long electionTypeId = new Long(jObj.getString("electionTypeId"));
				optionVOList = staticDataService.getStatesAndCountryBasedOnElectionType(electionTypeId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getElectionYears")){
				Long electionTypeId = new Long(jObj.getString("electionTypeId"));
				if(electionTypeId == 1l){
					optionVOList = staticDataService.getElectionYearsBasedOnElectionType(electionTypeId);
				}
				else
					optionVOList = staticDataService.getElectionYearsBasedOnStateIdAndElecTypeId(new Long(jObj.getString("stateId")),electionTypeId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getPartiesParticipatedInElection")){
				
				Long electionId = new Long(jObj.getString("electionId"));
				optionVOList = staticDataService.getPartiesForAGivenElectionYear(electionId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCandidates")){
				
				 String candidateName = jObj.getString("candidateName");
				Long partyId = new Long(jObj.getString("partyId"));
				Long electionId = new Long(jObj.getString("electionId"));
				optionVOList = candidateDetailsService.getCandidatesBasedOnSelection(candidateName,partyId,electionId);
			}			
			else if(jObj.getString("task").trim().equalsIgnoreCase("getAllPosition"))
			{
				optionVOList = electionResultsUpdationService.getAllPosition();
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getAllElectionTypes"))
			{
				optionVOList = electionResultsUpdationService.getAllElectionTypes();
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getAllElectionYears"))
			{
				optionVOList = electionResultsUpdationService.getAllElectionYears(jObj.getLong("stateId"),jObj.getString("eleType").trim());
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getStatesForAssign"))
			{
				optionVOList = staticDataService.getParticipatedStatesForAnElectionType(jObj.getLong("eleType"));
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getCandidatesToShow"))
			{
				PositionManagementVO positionManagementVO = new PositionManagementVO();
				positionManagementVO.setElectionTypeId(jObj.getLong("electionTypeId"));
				positionManagementVO.setCandidateName(jObj.getString("candidateName").trim());
				positionManagementVO.setYear(jObj.getString("year").trim());
				positionManagementVO.setPartyId(jObj.getLong("partyId"));
				positionManagementVO.setStateId(jObj.getLong("stateId"));
				positionManagementVO.setDistrictId(jObj.getLong("districtId"));
				positionManagementVO.setTehilId(jObj.getLong("tehilId"));
				positionManagementVO.setLocalElecBodyId(jObj.getLong("location"));
				positionManagementVO.setResult(jObj.getString("result").trim());
				optionVOList = electionResultsUpdationService.getCandidates(positionManagementVO);
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getElectionTypeDetails"))
			{
				optionVOList = electionResultsUpdationService.getElectionTypeDetails(jObj.getLong("elecGovPosId"),jObj.getString("positionType").trim());
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getPositionTypeDetails"))
			{
				optionVOList = electionResultsUpdationService.getPositionTypeDetails(jObj.getLong("elecGovPosId"));
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("getStateDetails"))
			{
				optionVOList = electionResultsUpdationService.getStateDetails(jObj.getLong("elecGovPosId"),jObj.getString("positionType").trim());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
public String updateElectionInformation(){
		
		try {
			jObj = new JSONObject(getTask());
			
			if(jObj.getString("task").trim().equalsIgnoreCase("addNewPosition"))
			{
				resultStatus = electionResultsUpdationService.addNewPosition(jObj.getString("newPosition").trim());
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("assignScopeToPosition"))
			{
				resultStatus = electionResultsUpdationService.assignScopeToPosition(jObj.getLong("positionId"),jObj.getLong("electionTypeId"),jObj.getLong("stateId"),jObj.getString("type"));
				
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("assignCandidateToPosition"))
			{
				PositionManagementVO positionManagementVO = new PositionManagementVO();
				positionManagementVO.setElectionTypeId(jObj.getLong("electionTypeId"));
				positionManagementVO.setStateId(jObj.getLong("stateId"));	 
				positionManagementVO.setYear(jObj.getString("year").trim());	   
				positionManagementVO.setCandidateId(jObj.getLong("candidateId"));
				positionManagementVO.setPartyId(jObj.getLong("partyId"));
				positionManagementVO.setDistrictId(jObj.getLong("districtId"));
				positionManagementVO.setTehilId(jObj.getLong("tehilId"));
				positionManagementVO.setLocalElecBodyId(jObj.getLong("location"));
				positionManagementVO.setElectionGovBodyPosId(jObj.getLong("electionGovBodyPosId"));
				positionManagementVO.setType(jObj.getString("type").trim());
				positionManagementVO.setFromDate(getDate(jObj.getString("fromDate").trim()));
				if(jObj.getString("toDate").trim() != null && jObj.getString("toDate").trim().length() >0)
				positionManagementVO.setToDate(getDate(jObj.getString("toDate").trim()));
				positionManagementVO.setStatus(jObj.getString("status").trim());
				resultStatus = electionResultsUpdationService.assignCandidateToAPosition(positionManagementVO);
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
  public Date getDate(String dateStr){
	  String[] dateArray =  dateStr.split("-");
	  Calendar cal = Calendar.getInstance(); 
	  cal.set(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1])-1, Integer.parseInt(dateArray[2]));
	  return cal.getTime();
   }
}
