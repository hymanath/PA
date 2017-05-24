package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.google.gson.JsonObject;
import com.itgrids.partyanalyst.GcmService.GcmService;
import com.itgrids.partyanalyst.dao.IAccommodationTrackingDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.INotificationDeviceDAO;
import com.itgrids.partyanalyst.dao.INotificationTypeDAO;
import com.itgrids.partyanalyst.dao.INotificationsDAO;
import com.itgrids.partyanalyst.dto.AccommodationVO;
import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.model.NotificationDevice;
import com.itgrids.partyanalyst.model.NotificationType;
import com.itgrids.partyanalyst.model.Notifications;
import com.itgrids.partyanalyst.service.INotificationService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class NotificationService implements INotificationService{
	private static final Logger log = Logger.getLogger(NotificationService.class);
	private TransactionTemplate transactionTemplate = null;
	private INotificationDeviceDAO notificationDeviceDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private INotificationsDAO notificationsDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IAccommodationTrackingDAO accommodationTrackingDAO;
	private INotificationTypeDAO notificationTypeDAO;
	private IConstituencyDAO constituencyDAO;
	
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public INotificationTypeDAO getNotificationTypeDAO() {
		return notificationTypeDAO;
	}

	public void setNotificationTypeDAO(INotificationTypeDAO notificationTypeDAO) {
		this.notificationTypeDAO = notificationTypeDAO;
	}

	public IAccommodationTrackingDAO getAccommodationTrackingDAO() {
		return accommodationTrackingDAO;
	}

	public void setAccommodationTrackingDAO(
			IAccommodationTrackingDAO accommodationTrackingDAO) {
		this.accommodationTrackingDAO = accommodationTrackingDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	
	public INotificationDeviceDAO getNotificationDeviceDAO() {
		return notificationDeviceDAO;
	}

	public void setNotificationDeviceDAO(
			INotificationDeviceDAO notificationDeviceDAO) {
		this.notificationDeviceDAO = notificationDeviceDAO;
	}

	public INotificationsDAO getNotificationsDAO() {
		return notificationsDAO;
	}

	public void setNotificationsDAO(INotificationsDAO notificationsDAO) {
		this.notificationsDAO = notificationsDAO;
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
		 //GcmService gcmService = new GcmService();
		// NotificationDeviceVO returnVo = new NotificationDeviceVO();
		  try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
						List<Long> isExistValues = notificationDeviceDAO.getIsExist(notifyVO.getProjectId(),notifyVO.getImeiNo());
						if(commonMethodsUtilService.isListOrSetValid(isExistValues)){
							for (Long deviceId : isExistValues) {
								NotificationDevice notificationDevice = notificationDeviceDAO.get(deviceId);
								notificationDevice.setIsActive("false");
								notificationDeviceDAO.save(notificationDevice);
							}
						}
						
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
							notificateDevice.setIsActive("true");
							notificationDeviceDAO.save(notificateDevice);	
						
					}
				});
				  return  new NotificationDeviceVO("SUCCESS");
		  }catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in saveUsersDataInNotificationDeviceTable() Method, Exception - "+e);
			  return  new NotificationDeviceVO("FAILURE");
		}
	  }
	 
	 public String  pushNotification(NotificationDeviceVO notifyVO,Long userId){
		try {
			GcmService gcmService = new GcmService();
			JsonObject notification = new JsonObject();
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			NotificationType notificationType =notificationTypeDAO.get(notifyVO.getNotificationTypeId());
			notification.addProperty("title", notificationType.getNotificationType());
			notification.addProperty("body", notifyVO.getNotification());
			notification.addProperty("click_action",".Activitys.Activity_Notifications");
			List<String> notificationKeysList = notificationDeviceDAO.getNotificationActiveKeys();
			NotificationDeviceVO notificationDeviceVO = gcmService.sendNotification(notifyVO.getRegisteredId(),notifyVO.getDeviceName(), notification,notificationKeysList, userId);

			Long orderNo = notificationsDAO.getMaxOrderNoBasedOnNotificationType(null);
			 if(orderNo == null || orderNo.longValue() ==0L)
				 orderNo =0L;
			Notifications notifications = new Notifications();
			notifications.setUserId(userId);
			notifications.setNotificationTypeId(notifyVO.getNotificationTypeId());
			notifications.setSuccessCount(notificationDeviceVO.getSuccessCount());
			notifications.setFailureCount(notificationDeviceVO.getFailureCount());
			notifications.setNotification(notifyVO.getNotification());
			notifications.setNotificationsId(notifyVO.getNotificationTypeId());
			notifications.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			notifications.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			notifications.setIsActive("true");
			notificationsDAO.save(notifications);

			return IConstants.SUCCESS;
		} catch (Exception e) {
			  log.error("Exception Occured in pushNotification() Method, Exception - "+e);
		}
		 return IConstants.FAILURE;
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
			 List<Long> inactiveNotificationTypeIdsList = new ArrayList<Long>(0);
			 List<Object[]> notificationTypeDetails = notificationTypeDAO.getAllNotificationType();
			 if(commonMethodsUtilService.isListOrSetValid(notificationTypeDetails)){
				 for (Object[] notification : notificationTypeDetails) {
					 NotificationDeviceVO  notificationTypeVO = new NotificationDeviceVO();
					 notificationTypeVO.setNotificationType(commonMethodsUtilService.getStringValueForObject(notification[1])); // New status[1]
					 notificationTypeVO.setNotificationTypeId(commonMethodsUtilService.getLongValueForObject(notification[0]));
					 notificationTypeVO.setOrderNo(commonMethodsUtilService.getLongValueForObject(notification[2]));
					 notificationTypeVO.setIsActive(commonMethodsUtilService.getStringValueForObject(notification[3]));
					 if(notificationTypeVO.getIsActive().equalsIgnoreCase("false"))
						 inactiveNotificationTypeIdsList.add(notificationTypeVO.getNotificationTypeId());
					 if(notificationTypeVO.getIsActive().equalsIgnoreCase("true"))
						 statusMap.put(notificationTypeVO.getNotificationTypeId(), notificationTypeVO);
				}
			 }
			 List<Object[]> notifyDetails = notificationsDAO.getNotificationsDetailsByNotification(notificationTypeId,lastNotificationId,lastUpdatedDate);
			 List<Object[]> inactiveNotificationsList = notificationsDAO.getInactiveNotificationsDetails(notificationTypeId);
			 //List<Long> inactiveNotificationTypeIdsList = null;//notificationsDAO.getInactiveNotificationsTypeDetails();
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
						 notificationTypeVO.setOrderNo(commonMethodsUtilService.getLongValueForObject(status[6]));
					 }
					 
					 notificationTypeVO.setLastUpdatedTime(lastUpdatedTimeStr);
					 notificationTypeVO.setInActiviNotifications(inActiveNotificationTypeIdsMap.get(notificationTypeVO.getNotificationTypeId()));
					 notificationTypeVO.setInActiviNotificationTypeIds(inactiveNotificationTypeIdsList);
					 
					 NotificationDeviceVO notificationVo = new NotificationDeviceVO();
					 notificationVo.setNotificationId(commonMethodsUtilService.getLongValueForObject(status[2]));
					 notificationVo.setNotification(commonMethodsUtilService.getStringValueForObject(status[3]));
					 notificationVo.setOrderNo(commonMethodsUtilService.getLongValueForObject(status[4]));
					 notificationVo.setLastUpdatedTime(commonMethodsUtilService.getStringValueForObject(status[5]));
					 notificationTypeVO.getActiveNotifications().add(notificationVo) ;
					// statusMap.put((Long)status[0], notificationTypeVO);
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
	 
	 public List<AccommodationVO> getAccommodationTrackingInfoByNotificationType(AccommodationVO inputvo){
		 List<AccommodationVO> returnList = null;
		 try {
			 Map<Long,AccommodationVO> constMap = new LinkedHashMap<Long, AccommodationVO>();
			 List<AccommodationVO> notificationTypeList = new ArrayList<AccommodationVO>();
			 
			 List<Object[]> notList = notificationTypeDAO.getNotificationTypes();
			 if(notList != null && notList.size() > 0){
				 for (Object[] obj : notList) {
					AccommodationVO vo = new AccommodationVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					
					notificationTypeList.add(vo);
				}
			 }
			 
			 List<Object[]> constList = constituencyDAO.getConstituenciesByStateId(1l, 0l);
			 if(commonMethodsUtilService.isListOrSetValid(constList)){
				 for (Object[] obj : constList) {
					Long constituencyId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					AccommodationVO vo = constMap.get(constituencyId);
					if(vo == null){
						vo = new AccommodationVO();
						vo.setConstituencyId(constituencyId);
						vo.setConstituencyName(obj[1] != null ? obj[1].toString():"");
						vo.setDistrictId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						vo.setDistrictName(obj[3] != null ? obj[3].toString():"");
						
						vo.setLocationDetails(notificationTypeList);
						
						constMap.put(constituencyId, vo);
					}
				}
			 }
			List<Object[]> list = accommodationTrackingDAO.getAccommodationTrackingInfoByNotificationType(inputvo.getTypeId(),inputvo.getLocationTypeId(),inputvo.getLastAccommodationTrackingId());
			List<Long> inActiveIdsList = accommodationTrackingDAO.getInactiveAccommodationTrackingInfoByNotificationType(inputvo.getTypeId(),inputvo.getLocationTypeId(),inputvo.getLastAccommodationTrackingId());
			
			Map<Long,AccommodationVO> returnMap = new LinkedHashMap<Long, AccommodationVO>();
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					Long constituencyId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					Long notifTypeId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					AccommodationVO vo = constMap.get(constituencyId);
					if(vo != null){
						AccommodationVO notiTypevo = getMatchedVO(vo.getLocationDetails(), notifTypeId);
						if(notiTypevo != null){
							notiTypevo.setId(notifTypeId);
							notiTypevo.setName(obj[1] != null ? obj[1].toString():"");
							
							AccommodationVO accvo = new AccommodationVO();
							accvo.setLocationName(obj[7] != null ? obj[7].toString():"");
							accvo.setAddress(obj[8] != null ? obj[8].toString():"");
							accvo.setContactPerson(obj[9] != null ? obj[9].toString():"");
							accvo.setMobileNo(obj[10] != null ? obj[10].toString():"");
							accvo.setLongitude(obj[11] != null ? obj[11].toString():"");
							accvo.setLatitude(obj[12] != null ? obj[12].toString():"");
							accvo.setAccommodationTrackingId(commonMethodsUtilService.getLongValueForObject(obj[15]));
							if(commonMethodsUtilService.isListOrSetValid(inActiveIdsList))
								accvo.setInActiveAccommadationTrackingIdsList(inActiveIdsList);
							notiTypevo.getLocationDetails().add(accvo);
						}
						returnMap.put(constituencyId, vo);
					}
				}
			}
			else if(commonMethodsUtilService.isListOrSetValid(inActiveIdsList) && commonMethodsUtilService.isMapValid(constMap)){
				for (Long constituencyId : constMap.keySet()) {
					AccommodationVO vo = constMap.get(constituencyId);
					if(vo != null){
							if(commonMethodsUtilService.isListOrSetValid(inActiveIdsList))
								vo.setInActiveAccommadationTrackingIdsList(inActiveIdsList);
						returnMap.put(constituencyId, vo);
						break;
					}
				}
			}
				
			if(commonMethodsUtilService.isMapValid(returnMap))
				returnList = new ArrayList<AccommodationVO>(returnMap.values());
			
		} catch (Exception e) {
			 log.error("Exception occured in getActiveNotifications() Method ",e);
		}
		return returnList;
	 }
	 
	 public AccommodationVO getMatchedVO(List<AccommodationVO> resultList,Long id)
		{
			if(resultList != null && resultList.size() > 0){
				for(AccommodationVO vo : resultList){
					if(vo.getId().equals(id)){
						return vo;
					}
				}
			 }
			 return null;
		}
	 
	 public String notificationIsActiveStatus(Long notificatonsId){
		 String status=" ";
		 try{
			 status=notificationsDAO.isActiveStatusNotification(notificatonsId);
			
		 }catch(Exception e){
			 log.error("Exception occured in notificationIsActiveStatus() Method ",e);
		 }
		 return status;
	 }
	 public String saveNotification(final Long notificationType,final String notificationText){
		
		 try{
			 transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
					 Long orderNo = notificationsDAO.getMaxOrderNoBasedOnNotificationType(notificationType);
					 if(orderNo == null || orderNo.longValue() ==0L)
						 orderNo =0L;
					 Notifications notifications = new Notifications();
					 notifications.setNotificationTypeId(notificationType);
					 notifications.setNotification(notificationText);
					 notifications.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					 notifications.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					 notifications.setIsActive("true");
					 notifications.setOrderNo(orderNo+1);
					 notificationsDAO.save(notifications);
					}
			 });	
		 }catch(Exception e){
			 log.error("Exception occured in saveNotification() Method ",e);
		 }
		 return IConstants.SUCCESS;
	 }
	 public String saveNotificationType(String notificationTypeText){
		 try{
			 long orderNo = notificationTypeDAO.getMaxOrderNo();
			 NotificationType notificationType = new NotificationType();
			 notificationType.setNotificationType(notificationTypeText);
			 notificationType.setIsActive("true");
			 notificationType.setOrderNo(orderNo+1);
			 notificationType.setTypeId(3l);
			 notificationTypeDAO.save(notificationType);
		 }catch(Exception e){
			 log.error("Exception occured in saveNotificationType() Method ",e);
		 }
		 return IConstants.SUCCESS;
	 }
	 public String setActivcationStatusforNotificationAndNotificationType(String updationTypeStr , Long id, String activeStatus){
		 try{
			 if(updationTypeStr != null && updationTypeStr.equalsIgnoreCase("notification")){
				 Notifications notifications = notificationsDAO.get(id);
				 notifications.setIsActive(activeStatus.trim());
				 notificationsDAO.save(notifications);
			 }
			 else  if(updationTypeStr != null && updationTypeStr.equalsIgnoreCase("notificationType")){
				 NotificationType notificationType = notificationTypeDAO.get(id);
				 notificationType.setIsActive(activeStatus.trim());
				 notificationTypeDAO.save(notificationType);
			 }
		 }catch(Exception e){
			 log.error("Exception occured in setActivcationStatusforNotificationAndNotificationType() Method ",e);
		 }
		 return IConstants.SUCCESS;
	}
	 public List<NotificationDeviceVO> getNotificationType(){
		 List<NotificationDeviceVO> notificationList = null;
		 List<Object[]> notificationTypesLst = notificationTypeDAO.getNotificationType();
		 if(notificationTypesLst != null && notificationTypesLst.size() > 0){
			 notificationList=new ArrayList<NotificationDeviceVO>();
			 for (Object[] obj : notificationTypesLst) {
				 NotificationDeviceVO vo = new NotificationDeviceVO();
				
				vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				vo.setDeviceName(obj[1] != null ? obj[1].toString():"");
				
				notificationList.add(vo);
			}
		 }
		return notificationList;
	 
	 }
	 public List<NotificationDeviceVO> getNotificationDetailsByTypeId(Long typeId){
		 List<NotificationDeviceVO> notificationList = new ArrayList<NotificationDeviceVO>();
		 List<Object[]> notificationsLst = notificationsDAO.getNotificationsByTypeId(typeId,null);
		 if(notificationsLst != null && notificationsLst.size() > 0){
			 for (Object[] obj : notificationsLst) {
				 NotificationDeviceVO vo = new NotificationDeviceVO();
				 
				vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				vo.setNotification(obj[1] != null ? obj[1].toString():"");
				vo.setSuccessCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
				vo.setFailureCount(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
				notificationList.add(vo);
			}
		 }
		return notificationList;
	 
	 }
	 
	 public NotificationDeviceVO getEventAccommodationParkingDetails(List<Long> notificationIds,Long locationId){
		 NotificationDeviceVO finalVo = new NotificationDeviceVO();
		 try {
			 List<Object[]> objList = accommodationTrackingDAO.getEventParkingDetails(notificationIds, locationId);
			 if(objList != null && objList.size() > 0){
				 for (Object[] objects : objList) {
					 NotificationDeviceVO vo = new NotificationDeviceVO();
					 vo.setLocationName(objects[1] != null ? objects[1].toString():"");
					 vo.setAddress(objects[2] != null ? objects[2].toString():"");
					 vo.setLatitude(objects[3] != null ? objects[3].toString():"");
					 vo.setLongitude(objects[4] != null ? objects[4].toString():"");
					 vo.setId(objects[5] != null ? (Long)objects[5]:0l);
					 if(objects[0] != null && (Long)objects[0]==4l)
						 finalVo.getParkingLst().add(vo);
					 else
						 finalVo.getVipParkingLst().add(vo);
				}
			 }
			 
		}catch (Exception e) {
			log.error("Exception occured in getEventParkingDetails() Method ",e);
		}
		 return finalVo;
	  }

	public List<NotificationDeviceVO> getAllNotifications() {
		try{
			Date currentDate = Calendar.getInstance().getTime();
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<Object[]> objList = notificationsDAO.getAllNotifications();
			List<NotificationDeviceVO> notificationDeviceVO =new ArrayList<NotificationDeviceVO>(); 
			 for (Object[] objects : objList) {
				 NotificationDeviceVO vo = new NotificationDeviceVO();
				 if(df.format(currentDate).equalsIgnoreCase(df.format(objects[2]))){
				 vo.setNotification(objects[1] != null ? objects[1].toString():"");
				 vo.setNotificationTypeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				 String date= commonMethodsUtilService.getStringValueForObject(objects[2]);
				 vo.setLastUpdatedTime(date.substring(0, date.length() - 2));
				 vo.setUserId(commonMethodsUtilService.getLongValueForObject(objects[3]));
				 vo.setNotificationType(commonMethodsUtilService.getStringValueForObject(objects[4]));
				 notificationDeviceVO.add(vo);
				 }
			 }
			return notificationDeviceVO;
		}catch(Exception e){
			log.error("Exception occured in getAllNotifications() Method ",e);
			return null;
		}
	}

	public List<NotificationDeviceVO> getAllNotificationsByuser(Long userId) {
		try{
			List<Object[]> objList = notificationsDAO.getAllNotificationsbyUser(userId);
			List<NotificationDeviceVO> notificationDeviceVO =new ArrayList<NotificationDeviceVO>(); 
			 for (Object[] objects : objList) {
				 NotificationDeviceVO vo = new NotificationDeviceVO();
				 vo.setOrderNo(Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
				 vo.setNotificationTypeId(Long.valueOf(objects[1] != null ? objects[1].toString():"0"));
				 vo.setNotificationType(objects[2] != null ? objects[2].toString():"");
				 
				 notificationDeviceVO.add(vo);
			 }
			return notificationDeviceVO;
		}catch(Exception e){
			
			log.error("Exception occured in getAllNotificationsByuser() Method ",e);
			return null;
		}
	}
	 public List<NotificationDeviceVO> getNotificationDetailsByUserTypeId(Long typeId,Long userId){
		 List<NotificationDeviceVO> notificationList = new ArrayList<NotificationDeviceVO>();
		 List<Object[]> notificationsLst = notificationsDAO.getNotificationsByTypeId(typeId,userId);
		 if(notificationsLst != null && notificationsLst.size() > 0){
			 for (Object[] obj : notificationsLst) {
				 NotificationDeviceVO vo = new NotificationDeviceVO();
				 vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				vo.setNotification(obj[1] != null ? obj[1].toString():"");
				vo.setSuccessCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
				vo.setFailureCount(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
				String date= commonMethodsUtilService.getStringValueForObject(obj[4]);
				 vo.setLastUpdatedTime(date.substring(0, date.length() - 2));
				notificationList.add(vo);
			}
		 }
		return notificationList;
		 
		 }
		
 }