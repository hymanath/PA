package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INotificationTypeDAO;
import com.itgrids.partyanalyst.model.NotificationType;

public class NotificationTypeDAO extends GenericDaoHibernate<NotificationType, Long> implements INotificationTypeDAO{

	public NotificationTypeDAO() {
		super(NotificationType.class);
		
	}
	
	public List<Object[]> getNotificationTypes(){
		Query query = getSession().createQuery("select model.notificationTypeId," +
										" model.notificationType" +
										" from NotificationType model" +
										" where model.isActive = 'true'" +
										" and model.notificationTypeId >= 3");
		return query.list();
	}
}
