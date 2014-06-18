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
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ApproveMessageAction extends ActionSupport implements ServletRequestAware  {

	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private ICandidateDetailsService candidateDetailsService; 
	private List<CandidateCommentsVO> candidateCommentsList;
	private ResultStatus resultStatus;
	private EntitlementsHelper entitlementsHelper;
	
	
    
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<CandidateCommentsVO> getCandidateCommentsList() {
		return candidateCommentsList;
	}
	public void setCandidateCommentsList(
			List<CandidateCommentsVO> candidateCommentsList) {
		this.candidateCommentsList = candidateCommentsList;
	}
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
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
	public String getMessages()
	{
		String fromDate = null;
		String toDate = null;
		String task = null;
		String selectstatus=null;
		String decidestatus=null;
		
		if(LOG.isDebugEnabled())
			LOG.debug("getMessages()..............");

		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fromDate = jObj.getString("fromDate");
		toDate = jObj.getString("toDate");
		task = jObj.getString("task");
		selectstatus = jObj.getString("selectstaus");
		decidestatus = jObj.getString("decidestatus");
		if(task.equalsIgnoreCase("getAllNewPostedReasons") || task.equalsIgnoreCase("getAllNewPostedReasonsBetweenDates"))
		{
			candidateCommentsList = candidateDetailsService.getMessages(fromDate, toDate,selectstatus,decidestatus);
		}
		
		return Action.SUCCESS;
	}
	public String controlMessages()
	{
		

		List<Long> id = new ArrayList<Long>();
		List<String> message = new ArrayList<String>();
		candidateCommentsList =new ArrayList<CandidateCommentsVO>();
	
		try {
			if(LOG.isDebugEnabled())
				LOG.debug("controlMessages().......");
			
			jObj = new JSONObject(getTask());
			
		    String task = jObj.getString("task");
	        JSONArray jArray = jObj.getJSONArray("checkedElmts");
	        String decideStatus = jObj.getString("decideStatus");
	      
	        String actionType = "";
			if(task.equalsIgnoreCase("approved"))
				actionType+= IConstants.APPROVED;
				
			else if(task.equalsIgnoreCase("rejected"))
				actionType+= IConstants.REJECTED;
					
			for (int i = 0; i < jArray.length(); i++) 
			{
				jObj= (JSONObject)jArray.get(i);
					
				message.add(jObj.getString("updated_message"));
				id.add(jObj.getLong("message_candidate_id"));
					
			}
			for (int i = 0; i < id.size(); i++) {
					
			   CandidateCommentsVO candidateCommentsVO  = new CandidateCommentsVO();
		    	candidateCommentsVO.setMessageToCandidateId(id.get(i));
				candidateCommentsVO.setMessage(message.get(i));
				candidateCommentsList.add(candidateCommentsVO);
					
			}
			if(decideStatus.equals(IConstants.CANDIDATE_MEG))
			     resultStatus = candidateDetailsService.controlMessages(candidateCommentsList,actionType);
			else if(decideStatus.equals(IConstants.PARTY_MEG))
				resultStatus =candidateDetailsService. adminModifiedMessages(candidateCommentsList,actionType);
				
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
