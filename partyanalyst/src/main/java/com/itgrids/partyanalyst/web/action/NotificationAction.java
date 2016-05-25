package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.INotificationService;
import com.itgrids.partyanalyst.service.ITrainingCampService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class NotificationAction extends ActionSupport implements ServletRequestAware {
	
	private static final Logger LOG = Logger.getLogger(NotificationAction.class);
	private HttpServletRequest 					request;
	private HttpSession 						session;
	private String 								task;
	private JSONObject							jObj;
	private ResultStatus 						resultStatus;
	private Long						    notificationDeviceId;
	private INotificationService				notificationService;
	private String status ;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public INotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(INotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public Long getNotificationDeviceId() {
		return notificationDeviceId;
	}

	public void setNotificationDeviceId(Long notificationDeviceId) {
		this.notificationDeviceId = notificationDeviceId;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
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
	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
	}

	public String execute(){
		return Action.SUCCESS;
	}
	
	public String PushNotificationDetails(){
		try{
			LOG.info("Entered into PushNotificationDetails() method of NotificationAction");
			jObj = new JSONObject(getTask());
			String notifctionType=jObj.getString("notificationTypeId");
			String notificatonTxt=jObj.getString("notificationText");
			
			NotificationDeviceVO notifyVO = new NotificationDeviceVO();
			notifyVO.setRegisteredId(notifctionType);
			notifyVO.setNotification(notificatonTxt);
			status=notificationService.pushNotification(notifyVO);
		}catch(Exception e){
			LOG.error("Exception raised at PushNotificationDetails() method of NotificationAction", e);
		}
		return Action.SUCCESS;
	}
	public String saveNotification(){
		try{
			LOG.info("Entered into saveNotification() method of NotificationAction");
			jObj = new JSONObject(getTask());
			Long notificationType =  jObj.getLong("notificationTypeId");
			String notificationText = jObj.getString("notificationText");
			status = notificationService.saveNotification(notificationType,notificationText);
		}catch(Exception e){
			LOG.error("Exception raised at saveNotification() method of NotificationAction",e);
		}
		return Action.SUCCESS;
	}
	
}