package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class BoothReportAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public String execute(){
		String strBoothID = request.getParameter("partNo");
		if(strBoothID==null)
			return ERROR;
		Long boothID = new Long(strBoothID);
		
		return SUCCESS;
	}
}
