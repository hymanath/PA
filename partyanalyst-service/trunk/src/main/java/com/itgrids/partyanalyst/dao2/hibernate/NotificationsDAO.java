package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INotificationsDAO;
import com.itgrids.partyanalyst.model.Notifications;

public class NotificationsDAO extends GenericDaoHibernate<Notifications,Long> implements INotificationsDAO {
	
	public NotificationsDAO() {
		super(Notifications.class);
	}
	
	public List<Object[]> getNotificationsDetailsByNotification (Long notificationTypeId,Long lastNotificationId,Date lastUpdatedDateTime){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct  model2.notificationTypeId, model2.notificationType, model.notificationsId, model.notification,model.orderNo, model.updatedTime ,model2.orderNo ");
		queryStr.append(" from Notifications model,NotificationType model2 where model.notificationTypeId = model2.notificationTypeId and model.isActive='true'  and model2.isActive='true'  ");
		if(notificationTypeId != null && notificationTypeId.longValue()>0L)
			queryStr.append(" and model.notificationTypeId=:notificationTypeId ");
		if(lastNotificationId != null && lastNotificationId.longValue()>0L)
			queryStr.append(" and model.notificationsId > :lastNotificationId ");
		if(lastUpdatedDateTime != null)
			queryStr.append(" and model.updatedTime > :updatedTime ");
		
		queryStr.append(" order by model.notificationTypeId, model.notificationsId,model.orderNo ");
		Query query = getSession().createQuery(queryStr.toString());
		if(notificationTypeId != null && notificationTypeId.longValue()>0L)
			query.setParameter("notificationTypeId", notificationTypeId);
		if(lastNotificationId != null && lastNotificationId.longValue()>0L)
			query.setParameter("lastNotificationId", lastNotificationId);
		if(lastUpdatedDateTime != null)
			query.setParameter("updatedTime", lastUpdatedDateTime);
			return query.list();
	}
	
	public List<Object[]> getInactiveNotificationsDetails (Long notificationTypeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct   model.notificationTypeId, model.notificationsId ");
		queryStr.append(" from Notifications model where model.isActive='false'  ");
		if(notificationTypeId != null && notificationTypeId.longValue()>0L)
			queryStr.append(" and model.notificationTypeId=:notificationTypeId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(notificationTypeId != null && notificationTypeId.longValue()>0L)
			query.setParameter("notificationTypeId", notificationTypeId);
			return query.list();
	}
	
	public List<Long> getInactiveNotificationsTypeDetails (){
		Query query = getSession().createQuery("select distinct  model2.notificationTypeId from NotificationType model2 where model2.isActive='false' ");
		return query.list();
	}
	
	
	public String isActiveStatusNotification(Long notificatonsId){
		Query query = getSession().createQuery("select model.isActive from Notifications model where model.notificationsId=:notificatonsId");
		query.setParameter("notificatonsId",notificatonsId);
		
		return (String)query.uniqueResult();
		
	}
	public List<Object[]> getNotificationsByTypeId (Long notificationTypeId){
		Query query = getSession().createQuery("select model.notificationsId,model.notification from Notifications model where model.notificationTypeId=:notificationTypeId and model.isActive='true' ");
		query.setParameter("notificationTypeId", notificationTypeId);
		return query.list();
	}
	
	public Long getMaxOrderNoBasedOnNotificationType(Long notificationType){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select max(model.orderNo) from Notifications model where model.notificationTypeId= :notificationTypeId");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("notificationTypeId", notificationType);
		return (Long) query.uniqueResult();
	}
}
