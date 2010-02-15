package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class HamletReportAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public String execute(){
		String strHamletID = request.getParameter("hamletID");
		if(strHamletID==null)
			return ERROR;
		Long hamletID = new Long(strHamletID);
		
		return SUCCESS;
	}

}
