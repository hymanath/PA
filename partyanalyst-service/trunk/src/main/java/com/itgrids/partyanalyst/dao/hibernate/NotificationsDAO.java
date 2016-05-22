package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INotificationsDAO;
import com.itgrids.partyanalyst.model.Notifications;

public class NotificationsDAO extends GenericDaoHibernate<Notifications,Long> implements INotificationsDAO {
	
	public NotificationsDAO() {
		super(Notifications.class);
	}
	
	public List<Object[]> getNotificationsDetailsByNotification (Long notificationTypeId,Long lastNotificationId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct  model.notificationTypeId,model2.notificationType, model.notificationsId, model.notification ");
		queryStr.append(" from Notifications model,NotificationType model2 where model.isActive='true' ");
		if(notificationTypeId != null && notificationTypeId.longValue()>0L)
			queryStr.append(" and model.notificationTypeId=:notificationTypeId ");
		if(lastNotificationId != null && lastNotificationId.longValue()>0L)
			queryStr.append(" and model.notificationsId > :notificationTypeId ");
		queryStr.append(" order by model.notificationTypeId,model.orderNo ");
		Query query = getSession().createQuery(queryStr.toString());
			return query.list();
	}
}
