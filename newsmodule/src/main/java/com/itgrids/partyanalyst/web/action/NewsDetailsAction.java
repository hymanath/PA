package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class NewsDetailsAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	private static final org.apache.log4j.Logger LOG = Logger.getLogger(NewsDetailsAction.class); 
	
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private List<FileVO> fileVOsList;
	private ICandidateDetailsService candidateDetailsService ;
	private ServletContext context;
	private Long contentId;
	
	public void setServletContext(ServletContext context) {
		
		this.context = context;
	}
	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
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
	public List<FileVO> getFileVOsList() {
		return fileVOsList;
	}
	public void setFileVOsList(List<FileVO> fileVOsList) {
		this.fileVOsList = fileVOsList;
	}
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	public String execute()
	{	
		
		return Action.SUCCESS;
	}
	
	public String ajaxHandler()
	{
		try{
		jObj = new JSONObject(getTask());
		 if(jObj.getString("task").equalsIgnoreCase("getLatestNews"))
		 {
		     fileVOsList = candidateDetailsService.getAllNews(jObj.getInt("firstResult"), jObj.getInt("maxResult"), "News Gallary",872L);
		 }
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in ajaxHandler() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
}
