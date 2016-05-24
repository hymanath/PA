package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.GcmService.GcmService;
import com.itgrids.partyanalyst.dao.hibernate.NotificationDeviceDAO;
import com.itgrids.partyanalyst.dao.hibernate.NotificationsDAO;
import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.model.NotificationDevice;
import com.itgrids.partyanalyst.service.INotificationService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class NotificationService implements INotificationService{
	private static final Logger log = Logger.getLogger(NotificationService.class);
	private TransactionTemplate transactionTemplate = null;
	private NotificationDeviceDAO notificationDeviceDAO;
	private DateUtilService dateUtilService = new DateUtilService();
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
		 GcmService gcmService = new GcmService();
		// NotificationDeviceVO returnVo = new NotificationDeviceVO();
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
			  @SuppressWarnings("unused")
			  NotificationDeviceVO notificationDeviceVO = gcmService.sendNotification(notifyVO.getRegisteredId(),IConstants.GCM_SERVER_STATIC_MESSAGE);
			  
			  if(notificationDeviceVO.getStatus() != null && notificationDeviceVO.getStatus().trim().equalsIgnoreCase("SUCCESS"))
			 	return  new NotificationDeviceVO("SUCCESS");
			  else
				  throw new Exception();
			  
		  }catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in saveUsersDataInNotificationDeviceTable() Method, Exception - "+e);
			  return  new NotificationDeviceVO("FAILURE");
		}
	  }
	 
	 public void pushNotification(NotificationDeviceVO notifyVO){
		 try {
			 GcmService gcmService = new GcmService();
			 NotificationDeviceVO notificationDeviceVO = gcmService.sendNotification(notifyVO.getRegisteredId(),notifyVO.getNotification());
			 System.out.println(notificationDeviceVO);
		} catch (Exception e) {
			  log.error("Exception Occured in pushNotification() Method, Exception - "+e);
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
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			 try {
				 lastUpdatedDate = sdf.parse(lastUpdatedServerTime);
			} catch (Exception e) {
				// TODO: handle exception
			}
			 Map<Long, NotificationDeviceVO> statusMap = new LinkedHashMap<Long, NotificationDeviceVO>(0);
			 Map<Long,List<Long>> inActiveNotificationTypeIdsMap = new HashMap<Long, List<Long>>(0); 
			 List<Object[]> notifyDetails = notificationsDAO.getNotificationsDetailsByNotification(notificationTypeId,lastNotificationId,lastUpdatedDate);
			 List<Object[]> inactiveNotificationsList = notificationsDAO.getInactiveNotificationsDetails(notificationTypeId);
			 List<Long> inactiveNotificationTypeIdsList = notificationsDAO.getInactiveNotificationsTypeDetails();
			 String lastUpdatedTimeStr = sdf.format(dateUtilService.getCurrentDateAndTime());
			 
			 if(commonMethodsUtilService.isListOrSetValid(inactiveNotificationsList)){
				 for (Object[] notification : inactiveNotificationsList) {
					List<Long> inactiveIds = new ArrayList<Long>(0);
					if(inActiveNotificationTypeIdsMap.get(commonMethodsUtilService.getLongValueForObject(notification[0])) != null){
						inactiveIds = inActiveNotificationTypeIdsMap.get(commonMethodsUtilService.getLongValueForObject(notification[0]));
					}
					inactiveIds.add(commonMethodsUtilService.getLongValueForObject(notification[1]));
					inActiveNotificationTypeIdsMap.put(commonMethodsUtilService.getLongValueForObject(notification[0]), inactiveIds);
				}
			 }
			 if(notifyDetails != null && notifyDetails.size()>0){
				 for (Object[] status : notifyDetails) {
					 NotificationDeviceVO notificationTypeVO= statusMap.get(commonMethodsUtilService.getLongValueForObject(status[0]));
					 if(notificationTypeVO == null)
					 {
						 notificationTypeVO = new NotificationDeviceVO();
						 notificationTypeVO.setNotificationType(commonMethodsUtilService.getStringValueForObject(status[1])); // New status[1]
						 notificationTypeVO.setNotificationTypeId(commonMethodsUtilService.getLongValueForObject(status[0]));
						 notificationTypeVO.setLastUpdatedTime(lastUpdatedTimeStr);
						 notificationTypeVO.setInActiviNotifications(inActiveNotificationTypeIdsMap.get(notificationTypeVO.getNotificationTypeId()));
						 notificationTypeVO.setInActiviNotificationTypeIds(inactiveNotificationTypeIdsList);
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
				 }
			 }
		 }catch(Exception e){
			 log.error("Exception occured in getActiveNotifications() Method ",e);
		 }
		return returnList;
		 
	 }
	 
}

