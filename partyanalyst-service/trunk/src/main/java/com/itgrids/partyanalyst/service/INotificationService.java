package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.NotificationDeviceVO;


public interface INotificationService {
	 public List<NotificationDeviceVO> saveUsersDataInNotificationDeviceTable(final NotificationDeviceVO notifyVO);
	 public List<NotificationDeviceVO> getActiveNotifications(Long notificationTypeId,Long lastNotificationId);
}
