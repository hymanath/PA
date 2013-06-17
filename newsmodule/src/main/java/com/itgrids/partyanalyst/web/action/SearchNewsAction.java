package com.itgrids.partyanalyst.web.action;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.googlecode.jsonplugin.annotations.JSON;
import com.itgrids.partyanalyst.dao.hibernate.CandidateRealatedNewsDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.INewsMonitoringService;
import com.opensymphony.xwork2.ActionSupport;


public class SearchNewsAction extends ActionSupport implements ServletContextAware, ServletRequestAware,ServletResponseAware{
	private static final Logger log = Logger.getLogger(NewsDisplayAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private HttpServletResponse response;
	private INewsMonitoringService newsMonitoringService;
	private JSONObject jObj;
	private String task;
	private List<SelectOptionVO> candidatesList;
	
	public List<SelectOptionVO> getCandidatesList() {
		return candidatesList;
	}

	public void setCandidatesList(List<SelectOptionVO> candidatesList) {
		this.candidatesList = candidatesList;
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

	public INewsMonitoringService getNewsMonitoringService() {
		return newsMonitoringService;
	}

	public void setNewsMonitoringService(
			INewsMonitoringService newsMonitoringService) {
		this.newsMonitoringService = newsMonitoringService;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String execute()
	{
		session = request.getSession();
		
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		return SUCCESS;
	}
	
	public String getNewsBySearch(){
		if(log.isDebugEnabled())
			log.debug("In SearchNewsAction's getNewsBySearch");
		try{
			jObj=new JSONObject(getTask());
			String text=jObj.getString("text");
			List<String> items = Arrays.asList(text.split("\\s"));
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return SUCCESS;
	}
	public String getCandidates(){
		if(log.isDebugEnabled())
			log.debug("In SearchNewsAction's getNewsBySearch");
		try {
			candidatesList=new ArrayList<SelectOptionVO>();
			
			candidatesList=newsMonitoringService.getCandidates();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return SUCCESS;
	}
		
}
