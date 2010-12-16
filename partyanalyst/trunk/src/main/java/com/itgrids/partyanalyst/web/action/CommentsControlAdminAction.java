package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.service.ICommentsDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CommentsControlAdminAction extends ActionSupport implements ServletRequestAware {
	
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private ICommentsDataService commentsDataService;
	private List<CandidateCommentsVO> candidateCommentsVO;
	
	public List<CandidateCommentsVO> getCandidateCommentsVO() {
		return candidateCommentsVO;
	}

	public void setCandidateCommentsVO(List<CandidateCommentsVO> candidateCommentsVO) {
		this.candidateCommentsVO = candidateCommentsVO;
	}

	public ICommentsDataService getCommentsDataService() {
		return commentsDataService;
	}

	public void setCommentsDataService(ICommentsDataService commentsDataService) {
		this.commentsDataService = commentsDataService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
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

	public void setServletRequest(HttpServletRequest request) {		 
		this.request = request;
	}

	public String execute() throws Exception
	{
		
		return Action.SUCCESS;
	}
	
	public String getComments()
	{
		String fromDate = null;
		String toDate = null;
		String task = null;
		String choice = null;
		
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fromDate = jObj.getString("fromDate");
		toDate = jObj.getString("toDate");
		task = jObj.getString("task");
		choice = jObj.getString("choice");
		
		if(task.equalsIgnoreCase("getAllNewPostedReasons") || task.equalsIgnoreCase("getAllNewPostedReasonsBetweenDates"))
		{
			candidateCommentsVO = commentsDataService.getAllComments(fromDate, toDate);
		}
		
		return Action.SUCCESS;
	}
	
	public String controlReasons()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		String task = jObj.getString("task");
		JSONArray jArray = jObj.getJSONArray("checkedElmts");
		List<Long> reasonIds = new ArrayList<Long>();
		
		String actionType = null;
		
		if(task.equalsIgnoreCase("approved"))
			actionType = IConstants.APPROVED;
		else if(task.equalsIgnoreCase("rejected"))
			actionType = IConstants.REJECTED;
			
		for (int i = 0; i < jArray.length(); i++) 
		{
			reasonIds.add(new Long(jArray.getString(i)));		
		}
		
		candidateCommentsVO = commentsDataService.scrutinizePostedComments(reasonIds, actionType, fromDate, toDate);
		
		return Action.SUCCESS;
	}
	
}
