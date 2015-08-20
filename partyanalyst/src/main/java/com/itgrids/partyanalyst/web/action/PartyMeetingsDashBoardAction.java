package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.service.IPartyMeetingService;
import com.itgrids.partyanalyst.service.IPartyMeetingsDashBoardService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyMeetingsDashBoardAction extends ActionSupport  implements ServletRequestAware{
private static final Logger LOG = Logger.getLogger(PartyMeetingsDashBoardAction.class);
	
	private HttpServletRequest  request;
	private HttpSession session;
	private JSONObject	jObj;
	private String 		task;
	private IPartyMeetingsDashBoardService partyMeetingsDashBoardService;
	
	
	
	public IPartyMeetingsDashBoardService getPartyMeetingsDashBoardService() {
		return partyMeetingsDashBoardService;
	}
	public void setPartyMeetingsDashBoardService(
			IPartyMeetingsDashBoardService partyMeetingsDashBoardService) {
		this.partyMeetingsDashBoardService = partyMeetingsDashBoardService;
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
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute()
	{
		return Action.SUCCESS;
	}
	
	
	
}
