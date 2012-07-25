package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AbuseCommentAction  extends ActionSupport implements ServletRequestAware  {
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private ICandidateDetailsService candidateDetailsService; 
	private List<CandidateCommentsVO> candidateCommentsList;
	private ResultStatus resultStatus;
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}


	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}


	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}


	public List<CandidateCommentsVO> getCandidateCommentsList() {
		return candidateCommentsList;
	}


	public void setCandidateCommentsList(
			List<CandidateCommentsVO> candidateCommentsList) {
		this.candidateCommentsList = candidateCommentsList;
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
    //override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public String execute()throws Exception
	{
		session = request.getSession();
		RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		if (registrationVO != null) 
		{
			if (!registrationVO.getIsAdmin().equals("true"))
				  return ERROR;
		} 
		else
			return ERROR;
		
		return Action.SUCCESS;
		
	}
	public String getAbuseComment()
	{
		String fromDate = null;
		String toDate = null;
		String task = null;
		String selectstatus=null;
		
		
		if(Log.isDebugEnabled())
			Log.debug("Enterd into getAbuseComment() of AbuseCommentAction..............");

		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace( );
		}
		fromDate = jObj.getString("fromDate");
		toDate = jObj.getString("toDate");
		task = jObj.getString("task");
		selectstatus = jObj.getString("selectstaus");
		
		if(task.equalsIgnoreCase("getAllNewPostedReasons") || task.equalsIgnoreCase("getAllNewPostedReasonsBetweenDates"))
		{
			
			candidateCommentsList = candidateDetailsService.getAbuseComment(fromDate, toDate,selectstatus);
		}
		
		return Action.SUCCESS;
	}
	public String controlAbuseComments()
	{
	
		List<Long> id = new ArrayList<Long>();
		List<String> rejectStatus = new ArrayList<String>();
		candidateCommentsList =new ArrayList<CandidateCommentsVO>();
	
		try {
			if(Log.isDebugEnabled())
				Log.debug("controlAbuseComments().......");
			
			jObj = new JSONObject(getTask());
			
		    String task = jObj.getString("task");
	        JSONArray jArray = jObj.getJSONArray("checkedElmts");
	        
	        String actionType = "";
			if(task.equalsIgnoreCase("approved"))
				actionType+= IConstants.APPROVED;
				
			else if(task.equalsIgnoreCase("rejected"))
				actionType+= IConstants.REJECTED;
					
			for (int i = 0; i < jArray.length(); i++) 
			{
				CandidateCommentsVO candidateCommentsVO  = new CandidateCommentsVO();
				jObj= (JSONObject)jArray.get(i);
					
				
				id.add(jObj.getLong("message_candidate_id"));
				rejectStatus.add(jObj.getString("updated_message"));
		    	candidateCommentsVO.setMessageToCandidateId(id.get(i));
		    	candidateCommentsVO.setMessage(rejectStatus.get(i));
				
				candidateCommentsList.add(candidateCommentsVO);
					
			}
			
			resultStatus = candidateDetailsService.controlAbuseComments(candidateCommentsList,actionType);
			
				
	     } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultStatus = new ResultStatus();
			resultStatus.setResultState(0l);
			resultStatus.setExceptionEncountered(e);
		   }
	    return Action.SUCCESS;
				
     }
	

}
