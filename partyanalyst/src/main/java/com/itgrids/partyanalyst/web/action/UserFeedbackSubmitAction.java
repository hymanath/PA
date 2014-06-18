package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserFeedbackVO;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class UserFeedbackSubmitAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UserFeedbackSubmitAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	private UserFeedbackVO feedbackVO = new UserFeedbackVO();
	JSONObject jObj;
	private Long responseCategoryId;
	private String result;
	private IOpinionPollService opinionPollService;
	
	public UserFeedbackVO getFeedbackVO() {
		return feedbackVO;
	}
	public void setFeedbackVO(UserFeedbackVO feedbackVO) {
		this.feedbackVO = feedbackVO;
	}
	public Long getCommentType() {
		return this.feedbackVO.getCommentType();
	}
	
	public void setCommentType(Long commentType) {
		this.feedbackVO.setCommentType(commentType);
	}
	public Long getCommentTask() {
		return this.feedbackVO.getTaskName();
	}
	
	public void setCommentTask(Long commentTask) {
		this.feedbackVO.setTaskName(commentTask);
	}
	public String getComment() {
		return this.feedbackVO.getComment();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Enter Your Comments",  shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Description should not contain special characters and numbers", shortCircuit = true)
	public void setComment(String comment) {
		this.feedbackVO.setComment(comment);
	}
	public String getResponseCategory() {
		return this.feedbackVO.getResponseCategory();
	}
	public void setResponseCategory(String responseCategory) {
		this.feedbackVO.setResponseCategory(responseCategory);
	}
	public Long getResponseCategoryId() {
		return this.feedbackVO.getResponseCategoryId();
	}
	public void setResponseCategoryId(Long responseCategoryId) {
		this.feedbackVO.setResponseCategoryId(responseCategoryId);
	}
	public IOpinionPollService getOpinionPollService() {
		return opinionPollService;
	}
	public void setOpinionPollService(IOpinionPollService opinionPollService) {
		this.opinionPollService = opinionPollService;
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
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public JSONObject getJObj() {
		return jObj;
	}
	public void setJObj(JSONObject obj) {
		jObj = obj;
	}
	public String execute () throws Exception 
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		feedbackVO.setUserId(user.getRegistrationID());
		feedbackVO.setUserType(user.getUserStatus());
			ResultStatus result = opinionPollService.saveUserFeedback(feedbackVO);
			
			
			if(result.getExceptionEncountered() != null)
            	return Action.ERROR;
            
      return Action.SUCCESS;
			
	}
	
	public void validate()
	{
		if(getCommentType() == null || getCommentType() <= 0 )
			addFieldError("commentType","Please select a kind of Comment");
		
		if(getCommentTask() == null || getCommentTask() <= 0 )
			addFieldError("commentTask","Please select on which you want to comment");
		
		/*if(getComment() == null || getComment().length() <= 0 )
			addFieldError("comment","Please Enter Your Comments");*/
	}
	

}
	
	