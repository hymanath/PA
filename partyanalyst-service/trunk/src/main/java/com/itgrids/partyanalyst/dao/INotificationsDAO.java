package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Notifications;

public interface INotificationsDAO extends GenericDao<Notifications,Long>{
	public List<Object[]> getNotificationsDetailsByNotification (Long notificationTypeId,Long lastNotificationId,Date lastUpdatedDate);
	public List<Object[]> getInactiveNotificationsDetails (Long notificationTypeId);
	public List<Long> getInactiveNotificationsTypeDetails ();
	public String isActiveStatusNotification(Long NotificatonsId);
	
	public List<Object[]> getNotificationsByTypeId (Long typeId);
	public Long getMaxOrderNoBasedOnNotificationType(Long notificationType);
}
