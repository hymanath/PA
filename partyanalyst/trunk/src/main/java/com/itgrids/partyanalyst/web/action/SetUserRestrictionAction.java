package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserDetailsInfoVO;
import com.itgrids.partyanalyst.service.IUserAccessRegionService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SetUserRestrictionAction  extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	
	private HttpSession session;
	
	private List<SelectOptionVO> users;
	private List<SelectOptionVO> usersList;
	private String task;
	JSONObject jObj;
	
	private ResultStatus resultStatus;
	
	
	private IUserAccessRegionService userAccessRegionService;
	
	private List<UserDetailsInfoVO> IpList;
	
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
		
		this.request=arg0;
	}
	public IUserAccessRegionService getUserAccessRegionService() {
		return userAccessRegionService;
	}
	public void setUserAccessRegionService(
			IUserAccessRegionService userAccessRegionService) {
		this.userAccessRegionService = userAccessRegionService;
	}
	
	
	public List<SelectOptionVO> getUsers() {
		return users;
	}
	public void setUsers(List<SelectOptionVO> users) {
		this.users = users;
	}
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	
	public List<SelectOptionVO> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<SelectOptionVO> usersList) {
		this.usersList = usersList;
	}
	
	public List<UserDetailsInfoVO> getIpList() {
		return IpList;
	}
	public void setIpList(List<UserDetailsInfoVO> ipList) {
		IpList = ipList;
	}
	public String execute() throws Exception
	{
		HttpSession session = request.getSession();
		
		if(session.getAttribute(IConstants.USER) == null)
			return ERROR;
		//users = userAccessRegionService.getAllRestrictedUsers();
		//usersList = userAccessRegionService.getAllUsers();
		return SUCCESS;
	}
	
	public String saveRestrictedUser()
	{
	String param;
	param = getTask();
	try{
		jObj = new JSONObject(param);	
		
	}catch (Exception e) {
		e.printStackTrace();
		
	}
		if(jObj.getString("task").equalsIgnoreCase("saveRestrictedUser"))
		{
		resultStatus = 	userAccessRegionService.saveRestrictedUser(jObj.getLong("userId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("saveUserIpAddress"))
		{
			resultStatus = userAccessRegionService.saveUserInUserAccessIpAddress(jObj.getLong("userId"),jObj.getString("IpAddress"));
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("deleteUserIpAddress"))
		{
			List<UserDetailsInfoVO> list = new ArrayList<UserDetailsInfoVO>();
			UserDetailsInfoVO userDetailsInfoVO = null;
			JSONArray userAccessRegionId = jObj.getJSONArray("IpList");
			for(int i= 0 ;i<userAccessRegionId.length();i++)
			{
				userDetailsInfoVO = new UserDetailsInfoVO();
				userDetailsInfoVO.setUserAccessRegionId(new Long(userAccessRegionId.get(i).toString()));
				list.add(userDetailsInfoVO);
			}
			resultStatus = userAccessRegionService.deleteUserIpAddress(list);	
		}
		return SUCCESS;
	}
	
	public String getAllIpAddressForUser()
	{
		String param;
		param = getTask();
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		 if(jObj.getString("task").equalsIgnoreCase("getAllIpAddressForUser"))
		{
			IpList = userAccessRegionService.getAllIpAddressForUser(jObj.getLong("userId"));
		}
		
		
		return SUCCESS;
	}
	public String getAllUserListAjax()
	{
		String param;
		param = getTask();
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	if(jObj.getString("task").equalsIgnoreCase("getAllRestrictedUsers"))
	{
		usersList = userAccessRegionService.getAllRestrictedUsers();
		usersList.add(0,new SelectOptionVO(0L,"Select User"));
	}
	else if(jObj.getString("task").equalsIgnoreCase("getAllUsers"))
	{
		usersList = userAccessRegionService.getAllUsers();
		usersList.add(0, new SelectOptionVO(0L,"Select User"));
	}
	return SUCCESS;
	}
	
}


