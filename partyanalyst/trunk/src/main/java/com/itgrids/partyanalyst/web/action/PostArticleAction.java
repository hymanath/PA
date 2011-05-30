package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.QuickRequestVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.impl.MailService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class PostArticleAction extends ActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Instance Variables

	private HttpServletRequest request;
		
	JSONObject jObj;
	private String task = null;
	private QuickRequestVO quickRequestVO = new QuickRequestVO();
	private MailService mailService;
	private ResultStatus result = new ResultStatus();

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}

	public String execute() throws Exception {

		 return SUCCESS;
	}
	
	public String ajaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
			
			quickRequestVO.setUserName(jObj.getString("name"));
			quickRequestVO.setEmailId(jObj.getString("email"));
			quickRequestVO.setMobileNumber(jObj.getString("mobile"));
			quickRequestVO.setUserRequirement(jObj.getString("requirement"));			
			String requestURL= request.getRequestURL().toString();
			String requestFrom = "";
			if(requestURL.contains("www.partyanalyst.com"))
				requestFrom = IConstants.SERVER;
			else
				requestFrom = IConstants.LOCALHOST;
			result = mailService.sendArticleToAdmin(quickRequestVO,requestFrom);
			
		}
		catch (ParseException e) {
			
			e.printStackTrace();
		}
		
	    return SUCCESS;
	}
}