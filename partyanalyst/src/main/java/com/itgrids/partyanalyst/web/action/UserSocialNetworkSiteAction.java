package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.service.IUserSocialNetworkSiteService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserSocialNetworkSiteAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware, ServletContextAware {
	
	
	private HttpServletRequest request;
	private HttpSession session;
	
	
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
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
	
	private String partyName;
	private String candidateName;	
	private String twitterId;
	private String yourName;
	private String emailId;
	private String  results;
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}

	private IUserSocialNetworkSiteService userSocialNetworkSiteService;
	JSONObject jObj = null;
	private String task = null;
	
	
	
	
	
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
	public IUserSocialNetworkSiteService getUserSocialNetworkSiteService() {
		return userSocialNetworkSiteService;
	}
	public void setUserSocialNetworkSiteService(
			IUserSocialNetworkSiteService userSocialNetworkSiteService) {
		this.userSocialNetworkSiteService = userSocialNetworkSiteService;
	}
	
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	public String getYourName() {
		return yourName;
	}
	public void setYourName(String yourName) {
		this.yourName = yourName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
 public String execute(){
	 
	 return Action.SUCCESS;
 }
	
	public String getUserSocialNetworkSiteInfo(){
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("insertUserSocialNetworkSiteInfo"))
		{
			 partyName = jObj.getString("partyName");
			 candidateName=jObj.getString("candidateName");
			 twitterId= jObj.getString("twitterId");
			 yourName=jObj.getString("yourName");
			 emailId=jObj.getString("emailId");
			 
			 results=userSocialNetworkSiteService.saveUserSocialNetworkSiteInfo(partyName,candidateName,twitterId,yourName,emailId);
			
			 
			
		}
			
		
		return Action.SUCCESS;
		
	}
	

}
