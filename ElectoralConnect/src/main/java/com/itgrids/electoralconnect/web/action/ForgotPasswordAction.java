package com.itgrids.electoralconnect.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.ResultStatus;
import com.itgrids.electoralconnect.service.IMailService;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.electoralconnect.util.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ForgotPasswordAction extends ActionSupport implements ServletRequestAware
{
	private static final Logger LOG = Logger.getLogger(ForgotPasswordAction.class);
	private HttpServletRequest request;
	private String task;
	private JSONObject jobj;
	private IUserService userService;
	private IMailService mailService;
	private String statusMessage;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	

	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IMailService getMailService() {
		return mailService;
	}
	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String execute()
	{
		try {
			LOG.debug("Enterd into execute() method in ForgotPasswordAction Action");
			jobj = new JSONObject(getTask());
			String username = jobj.getString("username");
			RegistrationVO regVO = userService.forgetPasswordService(username);
			
			String requestURL= request.getRequestURL().toString();
			System.out.println(requestURL);
			
			String requestFrom = "";
			if(requestURL.contains("www.partyanalyst.com"))
				requestFrom = IConstants.SERVER;
			else
				requestFrom = IConstants.LOCALHOST;
			
			ResultStatus rs = mailService.sendMailToUserToRecoverPassword(regVO,requestFrom);
			if(rs.getResultCode() == 0)
			{
				statusMessage = "success";
			}
			else
			{
				statusMessage = "failure";
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in the execute() method in ForgotPasswordAction Action", e);
		}
		return Action.SUCCESS;
	}
}
