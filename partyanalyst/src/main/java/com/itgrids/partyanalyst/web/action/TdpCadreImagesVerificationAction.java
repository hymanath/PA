package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TdpCadreImagesVerificationAction extends ActionSupport implements ServletRequestAware{

	
	private static final long serialVersionUID = 6687324420450621125L;
	private HttpServletRequest request;
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public String execute()
	{
		return Action.SUCCESS;
	}
}
