package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IBoothMapperService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VillageBoothMapperAction extends ActionSupport implements
ServletContextAware, ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5313524113544783868L;
	private static final Logger LOG = Logger.getLogger(VillageBoothMapperAction.class);
	private ServletContext context;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private String task;
	org.json.JSONObject jObj;
	
	private IBoothMapperService boothMapperService;
	private ResultStatus resultStatus;
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public IBoothMapperService getBoothMapperService() {
		return boothMapperService;
	}
	public void setBoothMapperService(IBoothMapperService boothMapperService) {
		this.boothMapperService = boothMapperService;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
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

	public void setServletResponse(HttpServletResponse response) {
        this.response = response;
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
	public org.json.JSONObject getJObj() {
		return jObj;
	}
	public void setJObj(org.json.JSONObject obj) {
		jObj = obj;
	}
	
	public String execute(){
		
	RegistrationVO registrationVO = (RegistrationVO) request.getSession().getAttribute(IConstants.USER);
		
		if(registrationVO !=null)
		{
			if(registrationVO.getIsAdmin() != null && registrationVO.getIsAdmin().equals("true"))
			{
				return SUCCESS;
			}
			else
				return Action.ERROR;
		}
		else
			return IConstants.NOT_LOGGED_IN;
	}
	
	public String setDataForVillageBoothRelation(){
		try {
			jObj = new JSONObject(getTask());
			LOG.info(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long districtID = Long.valueOf(jObj.getString("districtId"));
		Long electionYear =  Long.valueOf(jObj.getString("electionYear"));
		resultStatus = boothMapperService.setDataForVillageBoothRelation(districtID,electionYear);
		return SUCCESS;
	}
}
