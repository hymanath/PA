package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.ImportantDatesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.dto.UserSubscribeImpDatesVO;

import com.itgrids.partyanalyst.dto.EventActionPlanVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IUserCalendarService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;


public class CreateEventAction extends ActionSupport implements ServletRequestAware, ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String task = null;
	JSONObject jObj = null;	
	private UserEventVO event;
	private List<ImportantDatesVO> importantDatesVOs;
	private List<EventActionPlanVO> actionPlanList = new ArrayList<EventActionPlanVO>();
	private List<SelectOptionVO> organizersList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> actionOrgList = new ArrayList<SelectOptionVO>();
	private IUserCalendarService userCalendarService;
	private CadreManagementService cadreManagementService;
	private HttpSession session;
	private HttpServletRequest request;
	//private String subscribe;
	private UserSubscribeImpDatesVO userSubscribeImpDates = new UserSubscribeImpDatesVO();
	private CadreManagementVO cadreManagementVO;
	private List<SelectOptionVO> cadresLevel;
	private List<CadreInfo> cadresLocation;
	private String deleteStatus;
	private final static Logger LOG = Logger.getLogger(CreateEventAction.class);	
    private Long updateEventId;
    private boolean updateEvent;
    private boolean notLogged;
    private EntitlementsHelper entitlementsHelper;
    private String requestFrom;
    
	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public List<SelectOptionVO> getCadresLevel() {
		return cadresLevel;
	}

	public void setCadresLevel(List<SelectOptionVO> cadresLevel) {
		this.cadresLevel = cadresLevel;
	}

	public List<CadreInfo> getCadresLocation() {
		return cadresLocation;
	}

	public void setCadresLocation(List<CadreInfo> cadresLocation) {
		this.cadresLocation = cadresLocation;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public UserEventVO getEvent() {
		return event;
	}

	public void setEvent(UserEventVO event) {
		this.event = event;
	}
	
	public CadreManagementVO getCadreManagementVO() {
		return cadreManagementVO;
	}

	public void setCadreManagementVO(CadreManagementVO cadreManagementVO) {
		this.cadreManagementVO = cadreManagementVO;
	}
	
	
/*	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}*/

	public List<ImportantDatesVO> getImportantDatesVOs() {
		return importantDatesVOs;
	}

	public void setImportantDatesVOs(List<ImportantDatesVO> importantDatesVOs) {
		this.importantDatesVOs = importantDatesVOs;
	}
	public IUserCalendarService getUserCalendarService() {
		return userCalendarService;
	}

	public void setUserCalendarService(IUserCalendarService userCalendarService) {
		this.userCalendarService = userCalendarService;
	}
	
	
	public UserSubscribeImpDatesVO getUserSubscribeImpDates() {
		return userSubscribeImpDates;
	}

	public void setUserSubscribeImpDates(UserSubscribeImpDatesVO userSubscribeImpDates) {
		this.userSubscribeImpDates = userSubscribeImpDates;
	}
	

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}


	public Long getUpdateEventId() {
		return updateEventId;
	}

	public void setUpdateEventId(Long updateEventId) {
		this.updateEventId = updateEventId;
	}

	public boolean isUpdateEvent() {
		return updateEvent;
	}

	public void setUpdateEvent(boolean updateEvent) {
		this.updateEvent = updateEvent;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	
	public boolean isNotLogged() {
		return notLogged;
	}

	public void setNotLogged(boolean notLogged) {
		this.notLogged = notLogged;
	}

	public String execute() throws Exception
	{
		LOG.debug("CreateEventAction.execute()... started");
		String result = "success1";
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		if(user==null)
			return ERROR;
		
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			LOG.info(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.debug("Task::"+jObj.getString("task"));
		if(jObj.getString("task").equalsIgnoreCase("createEvent") || jObj.getString("task").equalsIgnoreCase("updateCreateEvent"))
		{
			event = new UserEventVO();
				
			if(!jObj.getString("userEventsId").equalsIgnoreCase(""))			
				event.setUserEventsId(Long.valueOf(jObj.getString("userEventsId")));
			
			event.setUserID(user.getRegistrationID());
			if(!jObj.getString("eventName").equalsIgnoreCase(""))
				event.setTitle(jObj.getString("eventName"));
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			String startTimeHrs = jObj.getString("startTimeHrs");
			String startTimeMin = jObj.getString("startTimeMin");
			String endTimeHrs = jObj.getString("endTimeHrs");
			String endTimeMin = jObj.getString("endTimeMin");
			StringBuilder sDate = new StringBuilder();
			StringBuilder eDate = new StringBuilder();
			sDate.append(startDate).append(IConstants.SPACE).append(startTimeHrs).append(":").append(startTimeMin).append(":00");
			eDate.append(endDate).append(IConstants.SPACE).append(endTimeHrs).append(":").append(endTimeMin).append(":00");
			event.setStartDate(sdf.parse(sDate.toString()));
			event.setEndDate(sdf.parse(eDate.toString()));
			event.setDescription(jObj.getString("desc"));
			event.setLocationId(Long.valueOf(jObj.getString("locationId")));
			event.setLocationType(jObj.getString("locationType"));
			event.setIsDeleted(jObj.getString("isDeleted"));
			//String organisers = jObj.getString("organisers");
			
			if(!jObj.getString("organizers").equals("null"))
			{
				LOG.info("In If Condition --------------------"+jObj.getString("organizers"));
				JSONArray organizers = jObj.getJSONArray("organizers");
				int orgzSize = organizers.length();
				
				for(int i=0;i<orgzSize;i++)
				{
					JSONObject orgjsonobj = organizers.getJSONObject(i);				
					String cadreID = orgjsonobj.getString("id");
					String cadreName = orgjsonobj.getString("name");
					
					SelectOptionVO orgVo = new SelectOptionVO();
					orgVo.setId(Long.valueOf(cadreID));
					orgVo.setName(cadreName);		
					organizersList.add(orgVo);
				}
				event.setOrganizers(organizersList);
			}
			
			if(!jObj.getString("actionPlans").equals("null"))
			{
				JSONArray actionPlans = jObj.getJSONArray("actionPlans");
				int size = actionPlans.length();
				
				for(int j=0;j<size;j++)
				{
					actionOrgList = new ArrayList<SelectOptionVO>();
					JSONObject jsonobj = actionPlans.getJSONObject(j);	
					
					Long userEventsPlanId = null;
					if(!jsonobj.getString("userEventsPlanId").equalsIgnoreCase(""))			
						userEventsPlanId = Long.valueOf(jsonobj.getString("userEventsPlanId"));
					String actionPlan = jsonobj.getString("action");
					String actionPlanDate = jsonobj.getString("targetDate");					
					JSONArray actionOrganisers = jsonobj.getJSONArray("actionPlanOrganizers");
					
					LOG.info("actionOrganisers = "+actionOrganisers);
					if(actionOrganisers.length()>0)
					{
						int actionOrgSize = actionOrganisers.length();
						for(int k=0;k<actionOrgSize;k++)
						{
							JSONObject actionobj = actionOrganisers.getJSONObject(k);
							String orgID = actionobj.getString("id");
							String orgName = actionobj.getString("name");
							
							SelectOptionVO orgVO = new SelectOptionVO();
							orgVO.setId(Long.valueOf(orgID));
							orgVO.setName(orgName);	
							actionOrgList.add(orgVO); 
						}
					}
					
					EventActionPlanVO eventActionPlanVO = new EventActionPlanVO();
					eventActionPlanVO.setAction(actionPlan);
					eventActionPlanVO.setTargetDate(df.parse(actionPlanDate));
							
					 eventActionPlanVO.setEventActionPlanId(userEventsPlanId);
					eventActionPlanVO.setActionPlanOrganizers(actionOrgList);
					actionPlanList.add(eventActionPlanVO);	
				}					
				event.setActionPlans(actionPlanList);
			}
		
			event = userCalendarService.saveUserPlannedEvents(event);
			if(event.getExceptionEncountered()!=null)
				LOG.error(event.getExceptionEncountered().getMessage());
			else
				LOG.debug("No Exception when saving UserEvent");
			result = "success1";
		}
		else if(jObj.getString("task").equalsIgnoreCase("createImpDateEvent") || jObj.getString("task").equalsIgnoreCase("updateImpDateEvent"))
		{
			LOG.debug("inside if....createImpDateEvent");
			ImportantDatesVO importantDatesVO = new ImportantDatesVO();			
			
			if(!jObj.getString("importantDateId").equalsIgnoreCase(""))
			{
				LOG.info(" IN If condition------------------"+jObj.getString("importantDateId"));
				importantDatesVO.setImportantDateId(Long.valueOf(jObj.getString("importantDateId")));
			}
			
			importantDatesVO.setEventId(user.getRegistrationID());
			importantDatesVO.setTitle(jObj.getString("eventName"));
			importantDatesVO.setImportance(jObj.getString("desc"));
			importantDatesVO.setFrequency(jObj.getString("frequency"));		
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			StringBuilder sDate = new StringBuilder();
			StringBuilder eDate = new StringBuilder();
			sDate.append(startDate).append(IConstants.SPACE).append("00").append(":").append("00").append(":00");
			eDate.append(endDate).append(IConstants.SPACE).append("00").append(":").append("00").append(":00");
			
			importantDatesVO.setStartDate(sdf.parse(sDate.toString()));
			importantDatesVO.setEndDate(sdf.parse(eDate.toString()));
			importantDatesVO.setIsDeleted("NO");
		
			importantDatesVOs = userCalendarService.saveUserImpDate(importantDatesVO);
			//LOG.info("Important dates = "+importantDatesVO);
			
			//LOG.debug("inside if....createImpDateEvent::"+importantDatesVO.getImportantDateId());
			result = "success2";
		}
		else
		{
			return ERROR;
		}
		
		
		LOG.info("In Create Event Action %%%%%%%%%%%%%%"+importantDatesVOs);
		
		return result;
	}
	
	public String subscribePartyImpDates() throws Exception{
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		String subscribeStatus = user.getSubscribePartyImpDate();
		String subscribe = "";
		if("ALL".equalsIgnoreCase(subscribeStatus)){
			subscribe ="Subscribe Party Imp Dates";
			subscribeStatus = "NONE";
		}else {
			subscribe ="Unsubscribe Party Imp Dates";
			subscribeStatus = "ALL";
		}
		
		userCalendarService.userSubscribePartyImpDates(user.getRegistrationID(),subscribeStatus);
		user.setSubscribePartyImpDate(subscribeStatus);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		List<ImportantDatesVO> userImpDates = userCalendarService.getUserImpDates(user,calendar);
		userSubscribeImpDates.setSubscribeTitle(subscribe);
		userSubscribeImpDates.setUserImpDates(userImpDates);
		session.setAttribute("USER", user);
		return SUCCESS;
	}
	
	public String showImpDateEvent() throws Exception
	{
		String result = "userEvent";
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			LOG.info(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String eventId = jObj.getString("eventId");
		String[] values = eventId.split("_"); 
		String eventType = jObj.getString("eventType");
		if(jObj.getString("taskType").equalsIgnoreCase("impEvent"))
		{
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			event = userCalendarService.getUserPlannedEvent(Long.valueOf(eventId),user.getRegistrationID());
			result = "userEvent";
		}
		else if(jObj.getString("taskType").equalsIgnoreCase("impDate"))
		{
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.valueOf(jObj.getString("currentYear")).intValue(), Integer.valueOf(jObj.getString("currentMonth")).intValue(),Integer.valueOf(jObj.getString("currentDay")).intValue());
			//cal.set(year, month, date);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			importantDatesVOs =  userCalendarService.getUserImpDate(Long.parseLong(values[0]), eventType, cal);
			result = "impDate";
		}
			
		return result;
	}
	
	public String getNextMonthDatesEvents() throws Exception
	{
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			LOG.info(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int month = Integer.parseInt(jObj.getString("monthVal"));
		int year = Integer.parseInt(jObj.getString("yearval"));
		
		LOG.info("@@@@@@@@@@@IN getNextMonth method ="+month+" - "+year);
		
		Calendar calendar =Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		cadreManagementVO = userCalendarService.getUserImpDateAndEvent(user, calendar);
		
		return SUCCESS;
	}
	
	public String getCadresForEvent() throws Exception
	{	
		LOG.info("IN get cadres method");
		String result = "success1";
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			LOG.info(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Boolean isParent = true;
		if(user.getParentUserId() != null)
			isParent = false;
		
		if(jObj.getString("cadreLevel").equalsIgnoreCase("locationLevel"))
		{
			cadresLevel = userCalendarService.getCadresByRegionType(user.getRegistrationID(), jObj.getString("regionVal"), Long.valueOf(jObj.getString("regionSelectVal")));
			result = "locationLevel";
		}
		else if(jObj.getString("cadreLevel").equalsIgnoreCase("cadreLevel"))
		{
			
			cadresLocation = cadreManagementService.getCadresByCadreLevel(jObj.getString("regionVal"), user.getRegistrationID(), isParent, user.getAccessType(), Long.valueOf(user.getAccessValue()));
			result = "cadreLevel";
		} 
		return result;
	}
	
	public String deleteSelectedEvent() throws Exception
	{	
		LOG.info("IN get cadres method ----------- delete select event");
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			LOG.info(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("deleteEvent"))
		{			
			LOG.info("In delete Event date ***********");
			userCalendarService.deleteUserPlannedEvents( Long.valueOf(jObj.getString("userEventsId"))); 
			this.setDeleteStatus(jObj.getString("userEventsId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("deleteImpDate"))
		{		
			LOG.info("In delete IMP date ***********");
			userCalendarService.deleteUserImpDate( Long.valueOf(jObj.getString("importantDateId")));
			this.setDeleteStatus(jObj.getString("importantDateId"));
		} 
		
		return SUCCESS;
	}
	
	public String createUpdateEvent(){
	 try{
		 
		 HttpSession session = request.getSession();
		 
		 requestFrom = request.getParameter("from");
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			List<String> entitlements = null;
			if(user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if(user == null && !entitlements.contains(IConstants.CADRE_MANAGEMENT_ENTITLEMENT)){
					notLogged = true;
					return SUCCESS;
				}
				if(!entitlements.contains(IConstants.CADRE_MANAGEMENT_ENTITLEMENT))
					return ERROR;
			/*if(session.getAttribute(IConstants.USER) == null && 
					!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CADRE_MANAGEMENT_ENTITLEMENT)){
				notLogged = true;
				return SUCCESS;
			}
			if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CADRE_MANAGEMENT_ENTITLEMENT))
				return ERROR;*/
		String eventId = request.getParameter("eventId");
		if(eventId != null && eventId.trim().length() >0){
			updateEventId = Long.parseLong(eventId.trim());
		    updateEvent = true;
		}
	}
	 }catch(Exception e){
		 LOG.error(e);
	 }
	 return SUCCESS;
	}
}
