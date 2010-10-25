package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.impl.InfluencingPeopleService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class InfluencingPersonInfoAction extends ActionSupport implements ServletRequestAware, ServletContextAware{

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private String influencingPersonId;
	private String task;
	JSONObject jObj;
	
	private InfluencingPeopleBeanVO influencingPeopleBeanVO; 
	private InfluencingPeopleService influencingPeopleService; 
	private Integer rows;
	private ServletContext context;
	private HttpSession session;
	
	
	public InfluencingPeopleBeanVO getInfluencingPeopleBeanVO() {
		return influencingPeopleBeanVO;
	}


	public void setInfluencingPeopleBeanVO(
			InfluencingPeopleBeanVO influencingPeopleBeanVO) {
		this.influencingPeopleBeanVO = influencingPeopleBeanVO;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}	
	
	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getInfluencingPersonId() {
		return influencingPersonId;
	}


	public void setInfluencingPersonId(String influencingPersonId) {
		this.influencingPersonId = influencingPersonId;
	}


	public ServletContext getContext() {
		return context;
	}


	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}
	
	public InfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}


	public void setInfluencingPeopleService(
			InfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}


	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}


	public String execute() throws Exception
	{
		
		influencingPersonId = request.getParameter("influencingPersonId");
		
		influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
		influencingPeopleBeanVO = influencingPeopleService.getDetailsByInfluencingPersonId(new Long(influencingPersonId));
				
		return SUCCESS;
	}
	
 }
