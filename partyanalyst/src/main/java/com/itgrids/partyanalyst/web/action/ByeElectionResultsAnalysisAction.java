package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.opensymphony.xwork2.ActionSupport;

public class ByeElectionResultsAnalysisAction extends ActionSupport implements
ServletRequestAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ByeElectionResultsAnalysisAction.class);
	private ServletContext context;
	HttpServletRequest request;
	private String task;
	org.json.JSONObject jObj;
	private List<ConstituencyVO> constituencywiseResults;
	private IBiElectionPageService biElectionPageService;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	
	public void setServletContext(ServletContext context) {
		this.context = context;		
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
	
	public void setTask(String task) {
		this.task = task;
	}


	public String getTask() {
		return task;
	}


	public void setConstituencywiseResults(List<ConstituencyVO> constituencywiseResults) {
		this.constituencywiseResults = constituencywiseResults;
	}


	public List<ConstituencyVO> getConstituencywiseResults() {
		return constituencywiseResults;
	}


	public void setBiElectionPageService(IBiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}


	public IBiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}


	public String execute(){
		log.debug(" Inside Action ..");
		
		
		return SUCCESS;
	}
	
	public String ajaxCallHandler()
	{
		log.debug(" Inside ajaxCallHandler Action ..");
		String param=null;			    
		param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getByeElectionResults"))
		{
			String percentage = jObj.getString("percentage");
			String localElections = jObj.getString("localElections");
			
			constituencywiseResults = biElectionPageService.getAllTelanganaConstituencieswisePartiesResultsBasedOnExpectedPercentage(percentage,new Boolean(localElections));
		}
		return SUCCESS;
	}
	
	

}
