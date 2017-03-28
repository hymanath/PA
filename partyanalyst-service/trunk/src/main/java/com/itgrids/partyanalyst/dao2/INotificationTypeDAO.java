package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NotificationType;

public interface INotificationTypeDAO extends GenericDao<NotificationType,Long>{
	public List<Object[]> getNotificationTypes();
	public List<Object[]> getNotificationType();
	public List<Object[]> getAllNotificationType();
	public Long getMaxOrderNo();
}
