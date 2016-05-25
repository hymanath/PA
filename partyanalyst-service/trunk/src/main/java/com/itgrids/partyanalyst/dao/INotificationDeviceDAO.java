package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NotificationDevice;

public interface INotificationDeviceDAO extends GenericDao<NotificationDevice, Long>{
	public List<Long> getIsExist(Long projectId,String imeiNo);
	public List<String> getNotificationActiveKeys();
}