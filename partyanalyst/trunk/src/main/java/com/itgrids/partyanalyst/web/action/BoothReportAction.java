package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class BoothReportAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	private static final Logger log = Logger.getLogger(BoothReportAction.class);

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	@Override
	public String execute() throws Exception {
		log.debug("BoothReportAction.execute()... started");
		String strBoothID = request.getParameter("boothID");
		String strPartNo = request.getParameter("partNo");
		log.debug("strBoothID:::"+strBoothID);
		log.debug("strPartNo:::"+strPartNo);
		if(strBoothID==null)
			return ERROR;
		Long boothID = new Long(strBoothID);
		
		return SUCCESS;
	}
}
