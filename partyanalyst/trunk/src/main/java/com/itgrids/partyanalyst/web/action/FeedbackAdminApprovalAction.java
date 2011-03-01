package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserFeedbackVO;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class FeedbackAdminApprovalAction extends ActionSupport implements ServletRequestAware, ServletContextAware {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FeedbackAdminApprovalAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private IOpinionPollService opinionPollService; 
	private UserFeedbackVO userfeedbackVO ;
	private ServletContext context;
	List<UserFeedbackVO> feedbackList = new ArrayList<UserFeedbackVO>(0);
	ResultStatus resultStatus = new ResultStatus();
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public IOpinionPollService getOpinionPollService() {
		return opinionPollService;
	}

	public void setOpinionPollService(IOpinionPollService opinionPollService) {
		this.opinionPollService = opinionPollService;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public UserFeedbackVO getUserfeedbackVO() {
		return userfeedbackVO;
	}

	public void setUserfeedbackVO(UserFeedbackVO userfeedbackVO) {
		this.userfeedbackVO = userfeedbackVO;
	}

	/*public List<UserFeedbackVO> getFeedbackList() {
		return this.feedbackList;
	}

	public void setFeedbackList(List<UserFeedbackVO> feedbackList) {
		this.feedbackList = feedbackList;
	}*/

	public String execute() throws Exception 
	
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
	   /* userfeedbackVO.setUserId(user.getRegistrationID());
		userfeedbackVO.setUserType(user.getUserStatus());*/
	
			feedbackList =  opinionPollService.getAllDetailsForToday();
			request.setAttribute("feedbackList", feedbackList);
			return SUCCESS;		
	}
	
	


	public String feedBackApprovalAjaxCall()
	{
		try {
			jObj = new JSONObject(getTask());
		}
		catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		List<Long> selectedValues = new ArrayList<Long>(0);
		String task = jObj.getString("task");
		String approveType = jObj.getString("approvetype");
		JSONArray values = jObj.getJSONArray("checkedElmts");
		
				
		for (int i = 0; i < values.length(); i++) {
			selectedValues.add(Long.parseLong(values.getString(i)));
		}
		
		int result = opinionPollService.getAllApprovedDetails(selectedValues,approveType);
		resultStatus.setResultCode(1); 
		return Action.SUCCESS;
	}
}