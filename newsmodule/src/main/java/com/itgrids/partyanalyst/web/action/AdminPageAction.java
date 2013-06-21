package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AdminPageAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpSession session;
	private ICandidateDetailsService candidateDetailsService;
	private String task;
	private JSONObject jObj;
	private String result;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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
	
	
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute()
	{
		session = request.getSession();	
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		else
		return Action.SUCCESS;
	}
	
	public String uploadMLCCandidateDetailsAction()
	{ 
	   session = request.getSession();	
	   RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
	   
	   if(user == null)
		return ERROR;
	   else		   
	    return Action.SUCCESS;
		
	}
	
	public String insertMLCCandidateDetails()
	{
		

		try{
			
			 session = request.getSession();	
			 RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			   
			   
			jObj = new JSONObject(getTask());
			
			Long partyId = jObj.getLong("partyId");
			String candidateName = jObj.getString("candidateName");
			String education = jObj.getString("education");
			String gender = jObj.getString("gender");
			
			 result   = candidateDetailsService.insertMLCCandidateDetails(partyId ,candidateName ,  education , gender,user.getRegistrationID());
			
			if(result.equalsIgnoreCase("success"))
				return Action.SUCCESS;
			else
				return Action.ERROR;

			
			
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in insertMLCCandidateDetails() method, Exception - "+e);
				return null;
			}
		
	}
	
}
