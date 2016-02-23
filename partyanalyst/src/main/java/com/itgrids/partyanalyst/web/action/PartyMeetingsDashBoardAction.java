package com.itgrids.partyanalyst.web.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.MeetingSummeryVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IPartyMeetingDashboardService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private MeetingSummeryVO meetingSummeryVO;
	private EntitlementsHelper entitlementsHelper;
	
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public MeetingSummeryVO getMeetingSummeryVO() {
		return meetingSummeryVO;
	}
	public void setMeetingSummeryVO(MeetingSummeryVO meetingSummeryVO) {
		this.meetingSummeryVO = meetingSummeryVO;
	}
	public IPartyMeetingDashboardService getPartyMeetingDashboardService() {
		return partyMeetingDashboardService;
	}
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
		/*RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO!=null && regVO.getRegistrationID() >0l){
			return Action.SUCCESS;
		}else{
			return "input";
		}*/
		//Date d1 = new DateUtilService().getDateByStringAndFormat("2015-08-22","yyyy-MM-dd");
		//Date d2 = new DateUtilService().getDateByStringAndFormat("2015-08-28","yyyy-MM-dd");
		//MeetingSummeryVO meetingSummeryVO = partyMeetingDashboardService.getMeetingsSummeryForDashboard(2L,d1,d2,null,2l,null);
		
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"PARTY_MEETINGS_ENTITLEMENT") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"PARTY_MEETINGS_ADMIN_ENTITLEMENT"))){
			noaccess = true ;
		}
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		
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
	
	public String getMeetingSummary(){
		try {
			LOG.info("entered into getMeetingSummary");
			
			jObj = new JSONObject(getTask());
			
			Long meetingLevel = jObj.getLong("meetingLevel");
			Long typeOfMeeting = jObj.getLong("typeOfMeeting");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long locationscope = jObj.getLong("locationscope");
			Long stateId = jObj.getLong("stateId");
			Long distId = jObj.getLong("distId");
			Long constId = jObj.getLong("constId");
			Long manTowDivId = jObj.getLong("manTowDivId");
			Long wardPanId = jObj.getLong("wardPanId");
			
			/*Long partyMeetingLevelId=2l;
			String fromDateString="2015/01/01";
			String toDateString="2015/12/31";
			Long partyMeetingTypeId=0l;
			Long locationLevelId=2l;
			Long locationValue=0l;*/
			
			//meetingSummeryVO = partyMeetingDashboardService.getMeetingsSummeryForDashboard(partyMeetingLevelId,fromDateString,toDateString,partyMeetingTypeId,locationLevelId,locationValue);
			meetingSummeryVO = partyMeetingDashboardService.getMeetingsSummeryForDashboard(meetingLevel,fromDate,toDate,typeOfMeeting,locationscope,stateId,distId,constId,manTowDivId,wardPanId);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getMeetingSummary", e);
		}
		return Action.SUCCESS;
	}
	
}
