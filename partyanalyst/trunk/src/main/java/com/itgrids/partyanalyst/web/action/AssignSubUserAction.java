package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IUserVoterService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AssignSubUserAction extends ActionSupport implements ServletRequestAware{

	private HttpSession session;
	private String task;
	JSONObject jObj;
	private HttpServletRequest request;
	private List<SelectOptionVO> usersList;
	private static final Logger LOG = Logger.getLogger(AssignSubUserAction.class);
	private IUserVoterService userVoterService;
	private SelectOptionVO selectOptionVO;
	private ResultStatus resultStatus;
	
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

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	
	public List<SelectOptionVO> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<SelectOptionVO> usersList) {
		this.usersList = usersList;
	}
	
	public IUserVoterService getUserVoterService() {
		return userVoterService;
	}

	public void setUserVoterService(IUserVoterService userVoterService) {
		this.userVoterService = userVoterService;
	}

	public SelectOptionVO getSelectOptionVO() {
		return selectOptionVO;
	}

	public void setSelectOptionVO(SelectOptionVO selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String execute()
	{
		return Action.SUCCESS;
	}
	
	public String ajaxHandler()
	{
		try{
			HttpSession session = request.getSession();
			
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return ERROR;
			
			Long userId = user.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			
			if(jObj.getString("task").equalsIgnoreCase("getAllPAUsers"))
				usersList = userVoterService.getAllPAUsers();
			else if(jObj.getString("task").equalsIgnoreCase("getAllUsers"))
				selectOptionVO = userVoterService.getAllParentUsers(jObj.getLong("id"));
			else if(jObj.getString("task").equalsIgnoreCase("assignParentUser"))
				resultStatus = userVoterService.assignParentUser(jObj.getLong("id"),jObj.getLong("parentUserId"),jObj.getLong("mainAccountId"));
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getAllPAUsers() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
}
