package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.dto.EventActionPlanVO;

public class CreateEventAction extends ActionSupport implements ServletRequestAware, ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String task = null;
	JSONObject jObj = null;
	private UserEventVO event;
	

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
		
		String title = jObj.getString("eventName");
		String startDate = jObj.getString("startDate");// mm/dd/yyyy
		String endDate = jObj.getString("endDate");
		String startTime = jObj.getString("startTime");// hh:mm:ss
		String endTime = jObj.getString("endTime");
		String desc = jObj.getString("desc");
		String organisers = jObj.getString("organisers");
		String actionPlans = jObj.getString("actionPlans");
		
		event = new UserEventVO();
		event.setTitle(title);
		event.setStartDate(Calendar.getInstance().getTime());
		event.setEndDate(Calendar.getInstance().getTime());
		event.setDescription(desc);
		//event.setOrganizers(organizers);
		//event.setActionPlans(actionPlans);
		
		
		System.out.println("In Create Event Action %%%%%%%%%%%%%%");
		return Action.SUCCESS;
	}
}
