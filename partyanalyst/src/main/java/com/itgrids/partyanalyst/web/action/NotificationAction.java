package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.INotificationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class NotificationAction extends ActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(NotificationAction.class);
	transient private HttpServletRequest 					request;
	transient private HttpSession 						session;
	private String 								task;
	private JSONObject							jObj;
	private ResultStatus 						resultStatus;
	private Long						    notificationDeviceId;
	private INotificationService				notificationService;
	private String status ;
	private String notificatonStats;
	private List<NotificationDeviceVO> notificationTypeList;
	
	
	public List<NotificationDeviceVO> getNotificationTypeList() {
		return notificationTypeList;
	}

	public void setNotificationTypeList(
			List<NotificationDeviceVO> notificationTypeList) {
		this.notificationTypeList = notificationTypeList;
	}

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
	
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}


	public JSONObject getjObj() {
		return jObj;
	}
	
	public String getNotificatonStats() {
		return notificatonStats;
	}

	public void setNotificatonStats(String notificatonStats) {
		this.notificatonStats = notificatonStats;
	}

	public String execute(){
		return Action.SUCCESS;
	}
	
	public String PushNotificationDetails(){
		try{
			LOG.info("Entered into PushNotificationDetails() method of NotificationAction");
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			LOG.info("Entered into PushNotificationDetails() method of NotificationAction");
			Long notifctionTypeId=jObj.getLong("notificationTypeId");
			String notificatonTxt=jObj.getString("notificationText");
			
			NotificationDeviceVO notifyVO = new NotificationDeviceVO();
			notifyVO.setNotificationTypeId(notifctionTypeId);
			notifyVO.setNotification(notificatonTxt);
			status=notificationService.pushNotification(notifyVO,regVO.getRegistrationID());
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
	public String saveNotificationType(){
		try{
			LOG.info("Entered into saveNotificationType() method of NotificationAction");
			jObj = new JSONObject(getTask());
			String notificationTypeText = jObj.getString("notificationTypeText");
			status = notificationService.saveNotificationType(notificationTypeText);
		}catch(Exception e){
			LOG.error("Exception raised at saveNotification() method of NotificationAction",e);
		}
		return Action.SUCCESS;
	}
	public String setActivcationStatusforNotificationAndNotificationType(){
		try{
			LOG.info("Entered into saveNotification() method of NotificationAction");
			jObj = new JSONObject(getTask());
			String updationTypeStr = jObj.getString("updationTypeStr");
			Long id = jObj.getLong("id");
			String activeStatus = jObj.getString("activeStatus");
			status = notificationService.setActivcationStatusforNotificationAndNotificationType(updationTypeStr, id, activeStatus);
		}catch(Exception e){
			LOG.error("Exception raised at setActivcationStatusforNotificationAndNotificationType() method of NotificationAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getNotificationDetails(){//fisrt select box
		try{
			LOG.info("Entered into PushNotificationDetails() method of NotificationAction");
			jObj = new JSONObject(getTask());
			
			notificationTypeList =  notificationService.getNotificationType();
		}catch(Exception e){
			LOG.error("Exception raised at PushNotificationDetails() method of NotificationAction", e);
		}
		return Action.SUCCESS;
	}
	public String getNotificationsByTypeId(){//second select box
		try{
			LOG.info("Entered into PushNotificationDetails() method of NotificationAction");
			jObj = new JSONObject(getTask());
			Long typeId = jObj.getLong("notificationTypeId");
			notificationTypeList =  notificationService.getNotificationDetailsByTypeId(typeId);
		}catch(Exception e){
			LOG.error("Exception raised at PushNotificationDetails() method of NotificationAction", e);
		}
		return Action.SUCCESS;
	}
	
	
	public String isActiveStatusNotification(){
		try{
			LOG.info("Entered into isActiveStatusNotification() method of NotificationAction");
			jObj = new JSONObject(getTask());
		
			Long notificatonsId=jObj.getLong("notificatonsId");
			notificatonStats=notificationService.notificationIsActiveStatus(notificatonsId);
		}catch(Exception e){
			LOG.error("Exception raised at isActiveStatusNotification() method of NotificationAction", e);
		}
		return Action.SUCCESS;
	}

	public void setServletRequest(final HttpServletRequest request) {
		this.request=request;		
	}
	
}