package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INotificationDeviceDAO;
import com.itgrids.partyanalyst.model.NotificationDevice;

public class NotificationDeviceDAO extends GenericDaoHibernate<NotificationDevice, Long> implements INotificationDeviceDAO{

	public NotificationDeviceDAO() {
		super(NotificationDevice.class);
		
	}
	public List<Long> getIsExist(Long registeredId,Long projectId,String imeiNo){
		
		Query query = getSession().createQuery(" select model.notificationDeviceId from NotificationDevice model " +
				" where model.registeredId=:registeredId and " +
				" model.projectId=:projectId and " +
				" model.imeiNo=:imeiNo ");

			query.setParameter("registeredId", registeredId);
			query.setParameter("projectId", projectId);
			query.setParameter("imeiNo", imeiNo);
			return query.list();
	}
}
