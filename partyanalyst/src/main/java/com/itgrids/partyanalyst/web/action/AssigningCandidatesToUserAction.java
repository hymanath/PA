package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AssigningCandidatesToUserAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	HttpSession session;
	private IRegistrationService registrationService;
	private EntitlementVO allRegisteredUsersData;
	private ICandidateDetailsService candidateDetailsService;
	private JSONObject jObj;
	private String task;
	private ResultStatus resultStatus;
	private List<SelectOptionVO> selectOptionVO;
	private List<FileVO> fileVO;
	
	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	public void setServletRequest(HttpServletRequest request) {

		this.request = request;
	}
	
	public EntitlementVO getAllRegisteredUsersData() {
		return allRegisteredUsersData;
	}

	public void setAllRegisteredUsersData(EntitlementVO allRegisteredUsersData) {
		this.allRegisteredUsersData = allRegisteredUsersData;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public List<SelectOptionVO> getSelectOptionVO() {
		return selectOptionVO;
	}

	public void setSelectOptionVO(List<SelectOptionVO> selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}
	
	public List<FileVO> getFileVO() {
		return fileVO;
	}

	public void setFileVO(List<FileVO> fileVO) {
		this.fileVO = fileVO;
	}

	public String execute() {
		session = request.getSession();
		RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		if (registrationVO != null) 
		{
			if (registrationVO.getIsAdmin().equals("true"))
				allRegisteredUsersData = registrationService.getAllRegisterdUsers();
		} 
		else
			return ERROR;
		return Action.SUCCESS;
	}
	public String saveUserCandidateRelation()
	{
		try 
		{
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("saveUserCandidateRelation"))
			  resultStatus  = candidateDetailsService.saveUserCandidateRelation(jObj.getLong("userId"),jObj.getLong("candidateId"));
			else if(jObj.getString("task").equalsIgnoreCase("deleteUserCandidateRelation"))
			  resultStatus  = candidateDetailsService.deleteUserCandidateRelation(jObj.getString("userCandidateRelationIds"));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String getCandidateDetailsBySearchCriteria()
	{   
		Long constituencyId = null;
		String name = null;
		String gender = null;
		try 
		{
			jObj = new JSONObject(getTask());
			if(jObj.getString("name")!= null && jObj.getString("name").trim().length()>0 )
				name = jObj.getString("name");
			
			if(jObj.getString("gender")!= null && jObj.getString("gender").trim().length()>0 )
				gender = jObj.getString("gender");
			
			if(jObj.getString("constituencyId")!= null && jObj.getString("constituencyId").trim().length()>0 )
				constituencyId = jObj.getLong("constituencyId");
			
			selectOptionVO  = candidateDetailsService.getCandidateDetailsBySearchCriteria(gender,name,constituencyId,jObj.getLong("userId"),jObj.getLong("stateId"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String getAllCandidateDetailsAssignedToAUser()
	{   
		try 
		{
			jObj = new JSONObject(getTask());
			
			fileVO  = candidateDetailsService.getAllCandidateDetailsAssignedToAUser(jObj.getLong("userId"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
}
