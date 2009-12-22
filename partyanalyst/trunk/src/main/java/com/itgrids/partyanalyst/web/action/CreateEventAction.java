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

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.dto.EventActionPlanVO;
import com.itgrids.partyanalyst.service.IUserCalendarService;
import com.itgrids.partyanalyst.utils.IConstants;

import freemarker.template.SimpleDate;

public class CreateEventAction extends ActionSupport implements ServletRequestAware, ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String task = null;
	JSONObject jObj = null;
	private UserEventVO event;	
	private List<EventActionPlanVO> actionPlanList = new ArrayList<EventActionPlanVO>();
	private IUserCalendarService userCalendarService;
	private HttpSession session;
	private HttpServletRequest request;
	

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
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
	
	public IUserCalendarService getUserCalendarService() {
		return userCalendarService;
	}

	public void setUserCalendarService(IUserCalendarService userCalendarService) {
		this.userCalendarService = userCalendarService;
	}

	
	public String execute()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
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
		
		if(jObj.getString("task").equalsIgnoreCase("createEvent"))
		{
			event = new UserEventVO();
			
			event.setUserID(user.getRegistrationID());
			event.setTitle(jObj.getString("eventName"));
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
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
			event.setStartDate(sDate.toString());
			event.setEndDate(eDate.toString());
			event.setDescription(jObj.getString("desc"));
			event.setLocationId(854L);
			event.setLocationType("MANDAL");
			//String organisers = jObj.getString("organisers");
			
			System.out.println("Start date ============ "+startDate);
			System.out.println("End date ============ "+endDate);
			
			JSONArray actionPlans = jObj.getJSONArray("actionPlans");
			int size = actionPlans.length();
			
			for(int i=0;i<size;i++)
			{
				JSONObject jsonobj = actionPlans.getJSONObject(i);				
				String actionPlan = jsonobj.getString("actionPlan");
				String actionOrganisers = jsonobj.getString("organisers");
				String actionPlanDate = jsonobj.getString("targetDate");
				
				EventActionPlanVO eventActionPlanVO = new EventActionPlanVO();
				eventActionPlanVO.setAction("actionPlan");
				eventActionPlanVO.setTargetDate(new Date(actionPlanDate));				
				actionPlanList.add(eventActionPlanVO);
			
				
			}					
			event.setActionPlans(actionPlanList);
		
			event = userCalendarService.saveUserPlannedEvents(event);
		}
		else if(jObj.getString("task").equalsIgnoreCase("createImpDateEvent"))
		{
			String title = jObj.getString("eventName");
			String startDate = jObj.getString("startDate");// mm/dd/yyyy			
			String desc = jObj.getString("desc");
			
			event = new UserEventVO();
			event.setTitle(title);
			StringBuilder sDate = new StringBuilder();
			sDate.append(startDate);
			event.setStartDate(sDate.toString());			
			event.setDescription(desc);
		}
		
		
		System.out.println("In Create Event Action %%%%%%%%%%%%%%");
		return Action.SUCCESS;
	}
}
