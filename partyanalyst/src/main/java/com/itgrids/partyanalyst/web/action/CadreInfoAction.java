package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreInfoAction extends ActionSupport implements ServletRequestAware, ServletContextAware{

	private HttpServletRequest request;
	private String cadreId;
	private String task;
	JSONObject jObj;
	private CadreInfo cadreInfo; 
	private CadreManagementService cadreManagementService; 
	private Integer rows;
	private ServletContext context;
	private HttpSession session;
	
	public CadreInfo getCadreInfo() {
		return cadreInfo;
	}


	public void setCadreInfo(CadreInfo cadreInfo) {
		this.cadreInfo = cadreInfo;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public String getCadreId() {
		return cadreId;
	}


	public void setCadreId(String cadreId) {
		this.cadreId = cadreId;
	}


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}	
	
	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}


	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}	

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
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
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}


	public String execute() throws Exception
	{
		
		return SUCCESS;
	}
	
	public String getCadreInfoByCadreId()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long cadreId = jObj.getLong("cadreId");
		
		cadreInfo = new CadreInfo();
		cadreInfo = cadreManagementService.getCadreCompleteInfo(cadreId);
		
		return Action.SUCCESS;
	}
	
	public String deleteCadre()
	{
		String param = null;
		param = getTask();
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		try {
			jObj = new JSONObject(param);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long cadreId = jObj.getLong("id");
		
		
		rows = cadreManagementService.deleteCadre(cadreId, regVO);
		Log.debug("rows:"+rows);
		return Action.SUCCESS;
	}


	
}
