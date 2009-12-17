package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.dto.EventActionPlanVO;
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
	

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
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

	public String execute()
	{
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
		
			String title = jObj.getString("eventName");
			String startDate = jObj.getString("startDate");// mm/dd/yyyy
			String endDate = jObj.getString("endDate");
			String startTimeHrs = jObj.getString("startTimeHrs");
			String startTimeMin = jObj.getString("startTimeMin");
			String endTimeHrs = jObj.getString("endTimeHrs");
			String endTimeMin = jObj.getString("endTimeMin");
			String desc = jObj.getString("desc");
			String organisers = jObj.getString("organisers");
			
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
				//eventActionPlanVO.setTargetDate();
				
				actionPlanList.add(eventActionPlanVO);
				
			}
			event = new UserEventVO();
			event.setTitle(title);
			StringBuilder sDate = new StringBuilder();
			sDate.append(startDate).append(" ").append(startTimeHrs).append(":").append(startTimeMin).append(":").append("00");
			event.setStartDate(sDate.toString());

			StringBuilder eDate = new StringBuilder();
			eDate.append(endDate).append(" ").append(endTimeHrs).append(":").append(endTimeMin).append(":").append("00");
			event.setEndDate(eDate.toString()); 
			event.setDescription(desc);
			//event.setOrganizers(organizers);
			event.setActionPlans(actionPlanList);
		}
		else if(jObj.getString("task").equalsIgnoreCase("createImpDateEvent"))
		{
			String title = jObj.getString("eventName");
			String startDate = jObj.getString("startDate");// mm/dd/yyyy			
			String desc = jObj.getString("desc");
			
			event = new UserEventVO();
			event.setTitle(title);
			event.setStartDate(Calendar.getInstance().getTime().toString());			
			event.setDescription(desc);
		}
		
		
		System.out.println("In Create Event Action %%%%%%%%%%%%%%");
		return Action.SUCCESS;
	}
}
