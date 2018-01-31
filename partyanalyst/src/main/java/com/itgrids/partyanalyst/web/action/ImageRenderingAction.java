package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ImageRenderingAction extends ActionSupport implements	ServletRequestAware {

	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String imageRendering()
	{
		try {
			
			final HttpSession session = request.getSession();
			
		/*	final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			*///}
		}catch(Exception e) {
			LOG.error("Exception raised at execute() in CoreDashBoard Action class", e);
		}
		return Action.SUCCESS;
	
	}
	
}
