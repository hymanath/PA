package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.meeting.service.ICadrePartyMeetingManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
public class CadrePartyMeetingManagementAction extends ActionSupport implements
		ServletRequestAware {
	private static final long serialVersionUID = 1L;

	private static Logger LOG = Logger
			.getLogger(CadrePartyMeetingManagementAction.class);
	private HttpServletRequest request;
    private ICadrePartyMeetingManagementService cadrePartyMeetingManagementService;
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	
	public ICadrePartyMeetingManagementService getCadrePartyMeetingManagementService() {
		return cadrePartyMeetingManagementService;
	}

	public void setCadrePartyMeetingManagementService(
			ICadrePartyMeetingManagementService cadrePartyMeetingManagementService) {
		this.cadrePartyMeetingManagementService = cadrePartyMeetingManagementService;
	}

	public String execute() {
		return Action.SUCCESS;
	}

}
