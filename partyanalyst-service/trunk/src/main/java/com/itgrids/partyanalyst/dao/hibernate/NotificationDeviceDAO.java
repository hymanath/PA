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
	public List<Long> getIsExist(Long projectId,String imeiNo){
		
		Query query = getSession().createQuery(" select model.notificationDeviceId from NotificationDevice model " +
				" where  model.projectId=:projectId and  model.imeiNo=:imeiNo ");

			query.setParameter("projectId", projectId);
			query.setParameter("imeiNo", imeiNo);
			return query.list();
	}
	
	public List<String> getNotificationActiveKeys(){
		Query query = getSession().createQuery(" select distinct model.registeredId from NotificationDevice model where model.isActive ='true' ");
			return query.list();
	}

}
