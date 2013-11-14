package com.itgrids.partyanalyst.web.action;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MobileDataAction extends ActionSupport implements ServletRequestAware{

	private String task;
	private JSONObject jObj;
	private HttpServletRequest request;
	private HttpSession session;
	private IMobileService mobileService;
	private ResultStatus resultStatus;
	public static final Logger LOG = Logger.getLogger(MobileDataAction.class);
	private List<SelectOptionVO> constituencyList,userList;
	private String filePath;
	
	public List<SelectOptionVO> getUserList() {
		return userList;
	}

	public void setUserList(List<SelectOptionVO> userList) {
		this.userList = userList;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
	this.request = request;	
	}

	public IMobileService getMobileService() {
		return mobileService;
	}

	public void setMobileService(IMobileService mobileService) {
		this.mobileService = mobileService;
	}
	

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	

	public String execute()
	{
		try{
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
        if(user == null)
    	  return ERROR;
		
        if(request.getRequestURL().toString().contains("localhost"))
        	filePath = "/PartyAnalyst/sqlite_dump.sql";
        else
        	filePath = "/sqlite_dump.sql";
        
        constituencyList = mobileService.getConstituencyList();
        if(constituencyList != null)
         constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
        
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error("Exception Occured in execute() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	public String ajaxHandler()
	{
		try{
		 jObj = new JSONObject(getTask());
		 
		 if(jObj.getString("task").equalsIgnoreCase("createDataDump"))
		 {
			 String path = IWebConstants.STATIC_CONTENT_FOLDER_URL+"/sqlite_dump.sql";
			 JSONArray arr = jObj.getJSONArray("userData");
			 JSONObject jSONObject= arr.getJSONObject(0);
			 RegistrationVO regVo = new RegistrationVO();
			 regVo.setFirstName(jSONObject.getString("firstName"));
			 regVo.setLastName(jSONObject.getString("lastName"));
			 regVo.setGender(jSONObject.getString("gender"));
			 regVo.setUserName(jSONObject.getString("userName"));
			 regVo.setPassword(jSONObject.getString("password"));
			 regVo.setUniqueCode(jSONObject.getString("uniqueCode"));// uniqueCode
			 regVo.setAppId(jSONObject.getString("appId"));
			 regVo.setMobile(jSONObject.getString("deviceId"));
			 regVo.setAddress(jSONObject.getString("macAddressId"));
			 
			 resultStatus = mobileService.createDataDumpFileForSelectedConstituency(jObj.getLong("constituencyId"),path,regVo);
		 }
		/* else if(jObj.getString("task").equalsIgnoreCase("saveMobileAppUserDetails"))
		 {
			 JSONArray arr = jObj.getJSONArray("userData");
			 JSONObject jSONObject= arr.getJSONObject(0);
			 RegistrationVO regVo = new RegistrationVO();
			 regVo.setFirstName(jSONObject.getString("firstName"));
			 regVo.setLastName(jSONObject.getString("lastName"));
			 regVo.setGender(jSONObject.getString("gender"));
			 regVo.setUserName(jSONObject.getString("userName"));
			 regVo.setPassword(jSONObject.getString("password"));
			 regVo.setUniqueCode(jSONObject.getString("uniqueCode"));// uniqueCode
			 regVo.setAppId(jSONObject.getString("appId"));
			 regVo.setMobile(jSONObject.getString("deviceId"));
			 regVo.setAddress(jSONObject.getString("macAddressId"));
			
			 resultStatus = mobileService.saveUserData(regVo);
		 }*/
			
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in ajaxHandler() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	public String sendSmsToMobileAppUserAccessKey()
	{
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			Long userID = user.getRegistrationID();
			resultStatus = mobileService.sendSmsToMobileAppUser(jObj.getString("mobileNo"),jObj.getLong("mobileAppuserId"),jObj.getString("accessKey"),userID);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getMobileAppUsers()
	{
		try{
			jObj = new JSONObject(getTask());
			userList= mobileService.getMobileAppUsers();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

}
