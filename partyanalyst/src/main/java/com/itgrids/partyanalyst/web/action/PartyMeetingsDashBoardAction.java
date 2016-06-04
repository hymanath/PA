package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MeetingSummeryVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IPartyMeetingDashboardService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PartyMeetingsDashBoardAction extends ActionSupport  implements ServletRequestAware{
private static final Logger LOG = Logger.getLogger(PartyMeetingsDashBoardAction.class);
	
	private HttpServletRequest  request;
	private HttpSession session;
	private JSONObject	jObj;
	private String 		task,pageAccessType;
	private IPartyMeetingDashboardService partyMeetingDashboardService;
	private MeetingSummeryVO MeetingsDashboardSummary;
	private MeetingSummeryVO meetingSummeryVO;
	private EntitlementsHelper entitlementsHelper;
	private ICadreCommitteeService cadreCommitteeService;
	private IdNameVO idNameVO;
	private List<IdNameVO> idNameVoList;

	
	public List<IdNameVO> getIdNameVoList() {
		return idNameVoList;
	}
	public void setIdNameVoList(List<IdNameVO> idNameVoList) {
		this.idNameVoList = idNameVoList;
	}
	public IdNameVO getIdNameVO() {
		return idNameVO;
	}
	public void setIdNameVO(IdNameVO idNameVO) {
		this.idNameVO = idNameVO;
	}
	public String getPageAccessType() {
		return pageAccessType;
	}
	public void setPageAccessType(String pageAccessType) {
		this.pageAccessType = pageAccessType;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
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
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("PARTY_MEETINGS_ENTITLEMENT")) || (entitlements.contains("PARTY_MEETINGS_ADMIN_ENTITLEMENT"))){
				noaccess = true ;
			}
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"PARTY_MEETINGS_ENTITLEMENT") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"PARTY_MEETINGS_ADMIN_ENTITLEMENT"))){
			noaccess = true ;
		}*/
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		
		pageAccessType = cadreCommitteeService.userAccessTypeDetailsForDashBoard(regVO.getRegistrationID(),regVO.getAccessType(),Long.valueOf(regVO.getAccessValue().trim()));
		String accessType = regVO.getAccessType();
		if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP"))
		{
			pageAccessType = accessType;
		}
	}
		return Action.SUCCESS;
		
	}
	
	public String getStatesForLocationLevel(){
		
		try{
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			String accessType = regVO.getAccessType();
			
			
			idNameVoList = cadreCommitteeService.getStatesForLocationLevel(accessType,Long.valueOf(regVO.getAccessValue().trim()));
			
		}catch (Exception e) {
			LOG.error("Exception is getStatesForLocationLevel in PartyMeetingsDashBoardAction class- ",e);
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
