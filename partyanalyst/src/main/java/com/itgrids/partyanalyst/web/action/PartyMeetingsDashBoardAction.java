package com.itgrids.partyanalyst.web.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.MeetingSummeryVO;
import com.itgrids.partyanalyst.service.IPartyMeetingDashboardService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PartyMeetingsDashBoardAction extends ActionSupport  implements ServletRequestAware{
private static final Logger LOG = Logger.getLogger(PartyMeetingsDashBoardAction.class);
	
	private HttpServletRequest  request;
	private HttpSession session;
	private JSONObject	jObj;
	private String 		task;
	private IPartyMeetingDashboardService partyMeetingDashboardService;
	private MeetingSummeryVO MeetingsDashboardSummary;
	
	
	public void setMeetingsDashboardSummary(
			MeetingSummeryVO meetingsDashboardSummary) {
		MeetingsDashboardSummary = meetingsDashboardSummary;
	}
	public void setPartyMeetingDashboardService(
			IPartyMeetingDashboardService partyMeetingDashboardService) {
		this.partyMeetingDashboardService = partyMeetingDashboardService;
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
		Date d1 = new DateUtilService().getDateByStringAndFormat("2015-08-22","yyyy-MM-dd");
		Date d2 = new DateUtilService().getDateByStringAndFormat("2015-08-28","yyyy-MM-dd");
		MeetingSummeryVO meetingSummeryVO = partyMeetingDashboardService.getMeetingsSummeryForDashboard(2L,d1,d2,null,2l,null);
		return Action.SUCCESS;
	}
	
	public String getMeetingsDashboardSummary()
	{
		try{
			jObj = new JSONObject(getTask());
		}catch(Exception e)
		{
			LOG.error("Exception is - ",e);
		}
		return Action.SUCCESS;
	}
	
	
	
}
