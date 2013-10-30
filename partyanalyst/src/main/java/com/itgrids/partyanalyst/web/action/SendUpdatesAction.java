package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SmsVO;
import com.itgrids.partyanalyst.service.ISendUpdatesService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SendUpdatesAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private ISendUpdatesService sendUpdatesService;
	private ISmsService smsCountrySmsService;
	private List<RegistrationVO> regVO = null;
	private JSONObject jObj;
	private String task=null;
	private ResultStatus resultstatus;
	
	public ResultStatus getResultstatus() {
		return resultstatus;
	}
	public void setResultstatus(ResultStatus resultstatus) {
		this.resultstatus = resultstatus;
	}
	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}
	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}
	public List<RegistrationVO> getRegVO() {
		return regVO;
	}
	public void setRegVO(List<RegistrationVO> regVO) {
		this.regVO = regVO;
	}
	public ISendUpdatesService getSendUpdatesService() {
		return sendUpdatesService;
	}
	public void setSendUpdatesService(ISendUpdatesService sendUpdatesService) {
		this.sendUpdatesService = sendUpdatesService;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}
	
	

	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String execute()
	{
		
		regVO = sendUpdatesService.getAllUsersForSendSms();
		return Action.SUCCESS;
		
	}
	
	
	public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		JSONArray usersArray = jObj.getJSONArray("userIds"); 
		String[] mobilenumbers = new String[usersArray.length()];
		for(int i=0; i<usersArray.length();i++)
		{
			
			mobilenumbers[i] = usersArray.getString(i);
		
			resultstatus = smsCountrySmsService.sendSmsFromAdmin(jObj.getString("txtAreaValue"),true,mobilenumbers[i]);
		}
		
		return Action.SUCCESS;
	}
	
	public String sendSmsForAllUsers()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		sendUpdatesService.sendSmsForAllUsersFromAdmin(jObj.getString("txtAreaValue"));
		return Action.SUCCESS;
	}

}
