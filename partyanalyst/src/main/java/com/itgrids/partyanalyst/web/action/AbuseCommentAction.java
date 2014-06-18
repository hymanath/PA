package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 7220092157888269387L;
	private static final Logger LOG = Logger.getLogger(AbuseCommentAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	public  transient JSONObject jObj;
	private ICandidateDetailsService candidateDetailsService; 
	private List<CandidateCommentsVO> candidateCommentsList;
	private ResultStatus resultStatus;
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(final ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}


	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}


	public void setCandidateDetailsService(final 
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}


	public List<CandidateCommentsVO> getCandidateCommentsList() {
		return candidateCommentsList;
	}


	public void setCandidateCommentsList(final 
			List<CandidateCommentsVO> candidateCommentsList) {
		this.candidateCommentsList = candidateCommentsList;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(final HttpServletRequest request) {
		this.request = request;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(final HttpSession session) {
		this.session = session;
	}


	public String getTask() {
		return task;
	}


	public void setTask(final String task) {
		this.task = task;
	}
    //override
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
		
	}
	public String execute()
	{
		session = request.getSession();
		final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		if (registrationVO != null) 
		{
			if (!registrationVO.getIsAdmin().equals("true")){
				  return ERROR;
			}
		} 
		else{
			return ERROR;
		}
		return Action.SUCCESS;
		
	}
	public String getAbuseComment()
	{
		
		if(LOG.isDebugEnabled()){
			LOG.debug("Enterd into getAbuseComment() of AbuseCommentAction..............");
		}
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			LOG.error(e);
		}
		final String task = jObj.getString("task");
		
		if(task.equalsIgnoreCase("getAllNewPostedReasons") || task.equalsIgnoreCase("getAllNewPostedReasonsBetweenDates"))
		{
			
			candidateCommentsList = candidateDetailsService.getAbuseComment(jObj.getString("fromDate"), jObj.getString("toDate"),jObj.getString("selectstaus"));
		}
		
		return Action.SUCCESS;
	}
	public String controlAbuseComments()
	{
	
		candidateCommentsList =new ArrayList<CandidateCommentsVO>();
	
		try {
			if(LOG.isDebugEnabled()){
				LOG.debug("controlAbuseComments().......");
			}
			jObj = new JSONObject(getTask());
			
		   final String task = jObj.getString("task");
	       final JSONArray jArray = jObj.getJSONArray("checkedElmts");
	        
	        String actionType = "";
			if(task.equalsIgnoreCase("approved")){
				actionType = IConstants.APPROVED;
			}	
			else if(task.equalsIgnoreCase("rejected")){
				actionType = IConstants.REJECTED;
			}
			CandidateCommentsVO commentsVO = null;		
			for (int i = 0; i < jArray.length(); i++) 
			{
				commentsVO  = new CandidateCommentsVO();
				jObj= (JSONObject)jArray.get(i);
					
				commentsVO.setMessageToCandidateId(jObj.getLong("message_candidate_id"));
				commentsVO.setMessage(jObj.getString("updated_message"));
				
				candidateCommentsList.add(commentsVO);
					
			}
			
			resultStatus = candidateDetailsService.controlAbuseComments(candidateCommentsList,actionType);
			
				
	     } catch (ParseException e) {
			// TODO Auto-generated catch block
	    	 LOG.error("Exception rised in controlAbuseComments",e);
			resultStatus = new ResultStatus();
			resultStatus.setResultState(0l);
			resultStatus.setExceptionEncountered(e);
		   }
	    return Action.SUCCESS;
				
     }
	

}
