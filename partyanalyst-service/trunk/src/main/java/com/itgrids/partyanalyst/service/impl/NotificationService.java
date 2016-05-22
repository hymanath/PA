package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.hibernate.NotificationDeviceDAO;
import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.NotificationDevice;
import com.itgrids.partyanalyst.service.INotificationService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class NotificationService implements INotificationService{
	private static final Logger log = Logger.getLogger(NotificationService.class);
	private TransactionTemplate transactionTemplate = null;
	private NotificationDeviceDAO notificationDeviceDAO;
	private DateUtilService dateUtilService;
	
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
	
	
	
	 public ResultStatus saveUsersDataInNotificationDeviceTable(final NotificationDeviceVO notifyVO)
	  {
		 log.info("Entered into saveVotersDataInVoterInfoTable() Method...");
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
						List<Long> isExistValues = notificationDeviceDAO.getIsExist(notifyVO.getRegisteredId(),notifyVO.getProjectId(),notifyVO.getImeiNo());
						if(isExistValues == null || isExistValues.size()==0){
							NotificationDevice notificateDevice = new NotificationDevice();
							notificateDevice.setNotificationDeviceId(notifyVO.getNotificationDeviceId());
							notificateDevice.setRegisteredId(notifyVO.getRegisteredId());
							notificateDevice.setProjectId(notifyVO.getProjectId());
							notificateDevice.setLongitude(notifyVO.getLongitude());
							notificateDevice.setLatitude(notifyVO.getLatitude());
							notificateDevice.setImeiNo(notifyVO.getImeiNo());
							notificateDevice.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							notificateDevice.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							notificationDeviceDAO.save(notificateDevice);	
						}
					}
				});
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in saveUsersDataInNotificationDeviceTable() Method, Exception - "+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		}
	  }
}

