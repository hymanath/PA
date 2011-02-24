package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserFeedbackVO;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.itgrids.partyanalyst.service.impl.OpinionPollService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class UserFeedbackAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UserFeedbackAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private OpinionPollService opinionPollService;
			
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletRequest getRequest() {
		return request;
	}

	public OpinionPollService getOpinionPollService() {
		return opinionPollService;
	}
	public void setOpinionPollService(OpinionPollService opinionPollService) {
		this.opinionPollService = opinionPollService;
	}
	public String execute()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		List<SelectOptionVO> feedbackTaskList = new ArrayList<SelectOptionVO>(0);
	    feedbackTaskList =  opinionPollService.getAllValuesFromFeedbackTask();
			session.setAttribute("feedbackTaskList",feedbackTaskList);
			
			List<SelectOptionVO> feedbackCommentList = new ArrayList<SelectOptionVO>(0);
		    feedbackCommentList =  opinionPollService.getAllValuesFromFeedbackComment();
				session.setAttribute("feedbackCommentList",feedbackCommentList);	
		return SUCCESS;
	}
	
}