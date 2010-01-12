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
	private final static Logger log = Logger.getLogger(CreateEventAction.class);	

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
	

	public String execute() throws Exception
	{
		log.debug("CreateEventAction.execute()... started");
		String result = "success1";
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
		
		if(user==null)
			return ERROR;
		
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Task::"+jObj.getString("task"));
		if(jObj.getString("task").equalsIgnoreCase("createEvent"))
		{
			event = new UserEventVO();
			
			event.setUserID(user.getRegistrationID());
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
			event.setLocationId(new Long(jObj.getString("locaitonId")));
			event.setLocationType(jObj.getString("locationType"));
			event.setIsDeleted("NO");
			//String organisers = jObj.getString("organisers");
			
			JSONArray organizers = jObj.getJSONArray("organizers");
			int orgzSize = organizers.length();
			
			for(int i=0;i<orgzSize;i++)
			{
				JSONObject orgjsonobj = organizers.getJSONObject(i);				
				String cadreID = orgjsonobj.getString("cadreId");
				String cadreName = orgjsonobj.getString("cadreName");
				
				SelectOptionVO orgVo = new SelectOptionVO();
				orgVo.setId(new Long(cadreID));
				orgVo.setName(cadreName);		
				organizersList.add(orgVo);
			}
			event.setOrganizers(organizersList);
			
			
			JSONArray actionPlans = jObj.getJSONArray("actionPlans");
			int size = actionPlans.length();
			
			for(int j=0;j<size;j++)
			{
				JSONObject jsonobj = actionPlans.getJSONObject(j);				
				String actionPlan = jsonobj.getString("actionPlan");
				JSONArray actionOrganisers = jsonobj.getJSONArray("organisers");
				
				String actionPlanDate = jsonobj.getString("targetDate");
				int actionOrgSize = actionPlans.length();
				for(int k=0;k<actionOrgSize;k++)
				{
					JSONObject actionobj = actionOrganisers.getJSONObject(k);
					String orgID = actionobj.getString("cadreId");
					String orgName = actionobj.getString("cadreName");
					
					SelectOptionVO orgVO = new SelectOptionVO();
					orgVO.setId(new Long(orgID));
					orgVO.setName(orgName);	
					actionOrgList.add(orgVO); 
				}
				
				EventActionPlanVO eventActionPlanVO = new EventActionPlanVO();
				eventActionPlanVO.setAction("actionPlan");
				eventActionPlanVO.setTargetDate(new Date(actionPlanDate));
				eventActionPlanVO.setActionPlanOrganizers(actionOrgList);
				actionPlanList.add(eventActionPlanVO);	
			}					
			event.setActionPlans(actionPlanList);
		
			event = userCalendarService.saveUserPlannedEvents(event);
			if(event.getExceptionEncountered()!=null)
				log.error(event.getExceptionEncountered().getMessage());
			else
				log.debug("No Exception when saving UserEvent");
			result = "success1";
		}
		else if(jObj.getString("task").equalsIgnoreCase("createImpDateEvent"))
		{
			log.debug("inside if....createImpDateEvent");
			ImportantDatesVO importantDatesVO = new ImportantDatesVO();			
			
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
			//System.out.println("Important dates = "+importantDatesVO);
			
			//log.debug("inside if....createImpDateEvent::"+importantDatesVO.getImportantDateId());
			result = "success2";
		}
		else
		{
			return ERROR;
		}
		
		
		System.out.println("In Create Event Action %%%%%%%%%%%%%%");
		
		return result;
	}
	
	public String subscribePartyImpDates() throws Exception{
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		String subscribeStatus = user.getSubscribePartyImpDate();
		String subscribe = new String();
		if("ALL".equalsIgnoreCase(subscribeStatus)){
			subscribe ="Subscribe Party Imp Dates";
			subscribeStatus = "NONE";
		}else {
			subscribe ="Unsubscribe Party Imp Dates";
			subscribeStatus = "ALL";
		}
		
		userCalendarService.userSubscribePartyImpDates(user.getRegistrationID(),subscribeStatus);
		user.setSubscribePartyImpDate(subscribeStatus);
		List<ImportantDatesVO> userImpDates = userCalendarService.getUserImpDates(user,Calendar.getInstance());
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
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String eventId = jObj.getString("eventId");
		String eventType = jObj.getString("eventType");
		if(jObj.getString("taskType").equalsIgnoreCase("impEvent"))
		{
			event = userCalendarService.getUserPlannedEvent(new Long(eventId));
			result = "userEvent";
		}
		else if(jObj.getString("taskType").equalsIgnoreCase("impDate"))
		{
			importantDatesVOs =  userCalendarService.getUserImpDate(new Long(eventId), eventType);
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
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int month = Integer.parseInt(jObj.getString("monthVal"));
		int year = Integer.parseInt(jObj.getString("yearval"));
		
		System.out.println("@@@@@@@@@@@IN getNextMonth method ="+month+" - "+year);
		
		Calendar calendar =Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		
		cadreManagementVO = userCalendarService.getUserImpDateAndEvent(user, calendar);
		
		return SUCCESS;
	}
	
	public String getCadresForEvent() throws Exception
	{	
		System.out.println("IN get cadres method");
		String result = "success1";
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(jObj.getString("cadreLevel").equalsIgnoreCase("locationLevel"))
		{
			cadresLevel = userCalendarService.getCadresByRegionType(user.getRegistrationID(), jObj.getString("regionVal"), new Long(jObj.getString("regionSelectVal")));
			result = "locationLevel";
		}
		else if(jObj.getString("cadreLevel").equalsIgnoreCase("cadreLevel"))
		{
			
			cadresLocation = cadreManagementService.getCadresByCadreLevel(jObj.getString("regionVal"), user.getRegistrationID());
			result = "cadreLevel";
		} 
		return result;
	}

	
}
