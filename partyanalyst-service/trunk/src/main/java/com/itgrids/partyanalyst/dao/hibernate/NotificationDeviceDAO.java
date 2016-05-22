package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INotificationDeviceDAO;
import com.itgrids.partyanalyst.model.NotificationDevice;

public class NotificationDeviceDAO extends GenericDaoHibernate<NotificationDevice, Long> implements INotificationDeviceDAO{

	public NotificationDeviceDAO() {
		super(NotificationDevice.class);
		
	}

}
