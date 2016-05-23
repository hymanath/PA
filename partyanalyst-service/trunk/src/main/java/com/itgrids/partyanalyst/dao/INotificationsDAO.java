package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Notifications;

public interface INotificationsDAO extends GenericDao<Notifications,Long>{
	public List<Object[]> getNotificationsDetailsByNotification (Long notificationTypeId,Long lastNotificationId,Date lastUpdatedDate);
}
