package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INotificationTypeDAO;
import com.itgrids.partyanalyst.model.NotificationType;

public class NotificationTypeDAO extends GenericDaoHibernate<NotificationType, Long> implements INotificationTypeDAO{

	public NotificationTypeDAO() {
		super(NotificationType.class);
		
	}
}
