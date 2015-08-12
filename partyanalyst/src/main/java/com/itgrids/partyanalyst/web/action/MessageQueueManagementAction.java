package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.message.AttendanceMessagesConsumer;
import com.itgrids.partyanalyst.message.EventMessagesConsumer;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MessageQueueManagementAction  extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -5687650001172804055L;

	private HttpServletRequest request;
	
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private EventMessagesConsumer eventMessagesConsumer;
	private AttendanceMessagesConsumer attendanceMessagesConsumer;
	private String result;
	
	public void setAttendanceMessagesConsumer(
			AttendanceMessagesConsumer attendanceMessagesConsumer) {
		this.attendanceMessagesConsumer = attendanceMessagesConsumer;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setEventMessagesConsumer(EventMessagesConsumer eventMessagesConsumer) {
		this.eventMessagesConsumer = eventMessagesConsumer;
	}
	public void setServletRequest(HttpServletRequest request) {
	this.request = request;
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
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	
	public String execute()
	{
		return Action.SUCCESS;
	}
	
	public String ajaxHandler()
	{
		try{
			jObj = new JSONObject(getTask());
			Integer consumersCount = jObj.getInt("count");
			eventMessagesConsumer.startConsumeMessages(consumersCount);
			attendanceMessagesConsumer.startConsumeMessages(consumersCount);
			result = Action.SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result = Action.ERROR;
		}
		return Action.SUCCESS;
	}
}
