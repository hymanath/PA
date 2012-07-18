package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.service.impl.CandidateDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class AssigningPartyToUserAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	HttpSession session;
	private ICandidateDetailsService candidateDetailsService ;
	private IPartyDetailsService partyDetailsService;
	private JSONObject jObj;
	private String task;
	private List<FileVO> fileVO;
	private ResultStatus resultStatus;
	
	private List<SelectOptionVO> userList;
	private List<SelectOptionVO> partyList;
	
	public void setServletRequest(HttpServletRequest request) {

		this.request = request;
	}
	

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public List<SelectOptionVO> getUserList() {
		return userList;
	}

	public void setUserList(List<SelectOptionVO> userList) {
		this.userList = userList;
	}

	public IPartyDetailsService getPartyDetailsService() {
		return partyDetailsService;
	}


	public void setPartyDetailsService(IPartyDetailsService partyDetailsService) {
		this.partyDetailsService = partyDetailsService;
	}


	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}


	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}

	public JSONObject getjObj() {
		return jObj;
	}


	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public List<FileVO> getFileVO() {
		return fileVO;
	}


	public void setFileVO(List<FileVO> fileVO) {
		this.fileVO = fileVO;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}


	public String execute()
	{
		session = request.getSession();
		RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		if (registrationVO != null) 
		{
			userList = candidateDetailsService.getAllRegisterUsersForAssigningParty();
			partyList = partyDetailsService.getAllPartysNames();
			
		}
		return ActionSupport.SUCCESS;
	}
	
	public String getAllPartyDetailsAssignedToAUser()
	{
		try 
		{
		jObj = new JSONObject(getTask());
				
			fileVO  = candidateDetailsService.getAllPartyDetailsAssignedToAUser(jObj.getLong("userId"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		return SUCCESS;
		
	}
	public String saveUserPartyRelation()
	{
		try 
		{
			jObj = new JSONObject(getTask());
			 if(jObj.getString("task").equalsIgnoreCase("deleteUserPartyRelation"))
				  resultStatus  = candidateDetailsService.deleteUserPartyRelation(jObj.getString("userPartyRelationIds"));
			// else if(jObj.getString("task").equalsIgnoreCase("saveUserPartyRelation"))
			// resultStatus  = candidateDetailsService.saveUserPartyRelation(jObj.getLong("userId"),jObj.getLong("candidateId"));
			 else if(jObj.getString("task").equalsIgnoreCase("saveDataToUserPartyRelation"))
				  resultStatus  = partyDetailsService.saveDataToUserPartyRelation(jObj.getLong("userId"),jObj.getLong("partyId"));
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
}
