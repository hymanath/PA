package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IMobileService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MobileAppUserMgmtAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private List<RegistrationVO> resultList;
	private ResultStatus resultStatus;
	private Long userId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<RegistrationVO> getResultList() {
		return resultList;
	}
	public void setResultList(List<RegistrationVO> resultList) {
		this.resultList = resultList;
	}

	private IMobileService mobileService;
	
	public IMobileService getMobileService() {
		return mobileService;
	}
	public void setMobileService(IMobileService mobileService) {
		this.mobileService = mobileService;
	}
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
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	
	public String execute()
	{
		
		return Action.SUCCESS;
		
	}
	
	public String getMobileAppUserData()
	{
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			if(user == null)
			return INPUT;
		
			if(jObj.getString("task").equalsIgnoreCase("getUserData"))
				resultList = mobileService.getMobileAppUserDetails();
			else if(jObj.getString("task").equalsIgnoreCase("getUserDetailInfo"))
				resultList = mobileService.getMobileAppUserDetailInfo(jObj.getLong("mobileAppuserId"));
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
	public String enableOrdisableAccess()
	{
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			if(user == null)
			return INPUT;
			List<Long> mobileAppUserIds = new ArrayList<Long>();
			JSONArray strarr = jObj.getJSONArray("mobileAppuserIds");
			if(strarr.length() > 0)
			for(int i=0;i<strarr.length();i++)
				mobileAppUserIds.add(new Long(strarr.getString(i).toString()));
			resultStatus = mobileService.enableOrdisableAccessByUniqueCode(mobileAppUserIds,jObj.getString("type"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
}
