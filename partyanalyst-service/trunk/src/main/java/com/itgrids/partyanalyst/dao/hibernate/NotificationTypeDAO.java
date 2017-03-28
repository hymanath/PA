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
										" from NotificationType model " +
										" where model.typeId = 3");
									
		return query.list();
	}
	public List<Object[]> getNotificationType(){
		Query query = getSession().createQuery("select model.notificationTypeId, model.notificationType, model.orderNo, model.isActive" +
										" from NotificationType model  where model.isActive = 'true' ");
		return query.list();
	}
	
	public List<Object[]> getAllNotificationType(){
		Query query = getSession().createQuery("select model.notificationTypeId, model.notificationType, model.orderNo, model.isActive" +
										" from NotificationType model  ");
		return query.list();
	}
	public Long getMaxOrderNo(){
		Query query = getSession().createQuery("select max(model.orderNo) from NotificationType model");
		return (Long)query.uniqueResult();
	}
}
