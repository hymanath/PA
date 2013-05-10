package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.DataValidationVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IDataValidationService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ValidationToolsAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private IVotersAnalysisService votersAnalysisService;
	private IDataValidationService dataValidationService;
	private List<DataValidationVO> resultList;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}
	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}
	
	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}
	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}
	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}
	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}
	
	
	public IDataValidationService getDataValidationService() {
		return dataValidationService;
	}
	public void setDataValidationService(
			IDataValidationService dataValidationService) {
		this.dataValidationService = dataValidationService;
	}
	
	
	public List<DataValidationVO> getResultList() {
		return resultList;
	}
	public void setResultList(List<DataValidationVO> resultList) {
		this.resultList = resultList;
	}
	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return INPUT;
		constituencyList = user.getUserAccessVoterConstituencies();
		if(constituencyList == null || constituencyList.isEmpty()){
			Long userID = user.getRegistrationID();
			Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
			Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
			userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
			constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
			constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
			user.setUserAccessVoterConstituencies(constituencyList);
		}
		return SUCCESS;
		
	}
	public String AjaxHandler()
	{
		String param;
		param = getTask();
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return ERROR;
		Long userID = user.getRegistrationID();
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		if(jObj.getString("task").equalsIgnoreCase("getSubLevelInfo"))
		{
			resultList = dataValidationService.getHamletsAssignedValidation(jObj.getLong("constituencyId"),jObj.getLong("publicationDateId"));
		}
		return SUCCESS;
		
	}
	

}
