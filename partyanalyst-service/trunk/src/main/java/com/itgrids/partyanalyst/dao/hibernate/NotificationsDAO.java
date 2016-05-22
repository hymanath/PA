package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INotificationsDAO;
import com.itgrids.partyanalyst.model.Notifications;

public class NotificationsDAO extends GenericDaoHibernate<Notifications,Long> implements INotificationsDAO {
	
	public NotificationsDAO() {
		super(Notifications.class);
	}
}
