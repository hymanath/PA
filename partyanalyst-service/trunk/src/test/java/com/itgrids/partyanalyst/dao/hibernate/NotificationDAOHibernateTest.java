package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.INotificationsDAO;
import com.itgrids.partyanalyst.service.INotificationService;

public class NotificationDAOHibernateTest extends BaseDaoTestCase {
	private INotificationService notificationService;
	private INotificationsDAO notificationsDAO;

	public INotificationsDAO getNotificationsDAO() {
		return notificationsDAO;
	}

	public void setNotificationsDAO(INotificationsDAO notificationsDAO) {
		this.notificationsDAO = notificationsDAO;
	}
	
	public INotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(INotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public void test(){
		final Long notificationType=11l;
		final String notificationText="abcd";
		String status = notificationService.saveNotification(notificationType,notificationText);
		System.out.println(status);
	}

}
