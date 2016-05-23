package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.hibernate.NotificationDeviceDAO;
import com.itgrids.partyanalyst.dao.hibernate.NotificationsDAO;
import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.model.NotificationDevice;
import com.itgrids.partyanalyst.service.INotificationService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class NotificationService implements INotificationService{
	private static final Logger log = Logger.getLogger(NotificationService.class);
	private TransactionTemplate transactionTemplate = null;
	private NotificationDeviceDAO notificationDeviceDAO;
	private DateUtilService dateUtilService;
	private NotificationsDAO notificationsDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	
	
	
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public NotificationsDAO getNotificationsDAO() {
		return notificationsDAO;
	}

	public void setNotificationsDAO(NotificationsDAO notificationsDAO) {
		this.notificationsDAO = notificationsDAO;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public NotificationDeviceDAO getNotificationDeviceDAO() {
		return notificationDeviceDAO;
	}

	public void setNotificationDeviceDAO(NotificationDeviceDAO notificationDeviceDAO) {
		this.notificationDeviceDAO = notificationDeviceDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	
	
	 public NotificationDeviceVO saveUsersDataInNotificationDeviceTable(final NotificationDeviceVO notifyVO)
	  {
		 log.info("Entered into saveVotersDataInVoterInfoTable() Method...");
		 NotificationDeviceVO returnVo = new NotificationDeviceVO();
		  try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
						//List<Long> isExistValues = notificationDeviceDAO.getIsExist(notifyVO.getRegisteredId(),notifyVO.getProjectId(),notifyVO.getImeiNo());
						//if(isExistValues == null || isExistValues.size()==0){
							NotificationDevice notificateDevice = new NotificationDevice();
							notificateDevice.setNotificationDeviceId(notifyVO.getNotificationDeviceId());
							notificateDevice.setRegisteredId(notifyVO.getRegisteredId());
							notificateDevice.setProjectId(notifyVO.getProjectId());
							notificateDevice.setLongitude(notifyVO.getLongitude());
							notificateDevice.setLatitude(notifyVO.getLatitude());
							notificateDevice.setImeiNo(notifyVO.getImeiNo());
							notificateDevice.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							notificateDevice.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							notificateDevice.setDeviceName(notifyVO.getDeviceName());
							notificationDeviceDAO.save(notificateDevice);	
						//}
					}
				});
			  //returnList =  getActiveNotifications(notifyVO.getNotificationTypeId(),notifyVO.getLastNotificationId());
			  return  new NotificationDeviceVO("SUCCESS");
		  }catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in saveUsersDataInNotificationDeviceTable() Method, Exception - "+e);
			 
			  return  new NotificationDeviceVO("FAILURE");
		}
	  }
	 public List<NotificationDeviceVO> getActiveNotifications(NotificationDeviceVO inputVo) {
		 List<NotificationDeviceVO>  returnList = null;
		 try {
			 
			 log.error("Entered in to getActiveNotifications() Method ");
			 Long notificationTypeId =inputVo.getNotificationTypeId();
			 Long lastNotificationId = inputVo.getNotificationId();
			 String lastUpdatedServerTime = inputVo.getLastUpdatedTime();
			 Date lastUpdatedDate = null;
			 try {
				 lastUpdatedDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(lastUpdatedServerTime);
			} catch (Exception e) {
				// TODO: handle exception
			}
			 Map<Long, NotificationDeviceVO> statusMap = new LinkedHashMap<Long, NotificationDeviceVO>(0);
			 List<Object[]> notifyDetails = notificationsDAO.getNotificationsDetailsByNotification(notificationTypeId,lastNotificationId,lastUpdatedDate);
			 if(notifyDetails != null && notifyDetails.size()>0){
				 for (Object[] status : notifyDetails) {
					 NotificationDeviceVO notificationTypeVO= statusMap.get(commonMethodsUtilService.getLongValueForObject(status[0]));
					 if(notificationTypeVO == null)
					 {
						 notificationTypeVO = new NotificationDeviceVO();
						 notificationTypeVO.setNotificationType(commonMethodsUtilService.getStringValueForObject(status[1])); // New status[1]
						 notificationTypeVO.setNotificationTypeId(commonMethodsUtilService.getLongValueForObject(status[0]));
					 }
					 NotificationDeviceVO notificationVo = new NotificationDeviceVO();
					 notificationVo.setNotificationId(commonMethodsUtilService.getLongValueForObject(status[2]));
					 notificationVo.setNotification(commonMethodsUtilService.getStringValueForObject(status[3]));
					 notificationVo.setOrderNo(commonMethodsUtilService.getLongValueForObject(status[4]));
					 notificationVo.setLastUpdatedTime(commonMethodsUtilService.getStringValueForObject(status[5]));
					 notificationTypeVO.getActiveNotifications().add(notificationVo) ;
					 statusMap.put((Long)status[0], notificationTypeVO);
				}
				 
				 if(commonMethodsUtilService.isMapValid(statusMap)){
					 returnList= new ArrayList<NotificationDeviceVO>(0);
					 returnList.addAll(statusMap.values());
					 
					 NotificationDeviceVO VO = returnList.get(0);
					 VO.setLastUpdatedTime(dateUtilService.getCurrentDateAndTime().toString());
				 }
				 
				 
			 }
		 }catch(Exception e){
			 log.error("Exception occured in getActiveNotifications() Method ");
		 }
		return returnList;
		 
	 }

}

