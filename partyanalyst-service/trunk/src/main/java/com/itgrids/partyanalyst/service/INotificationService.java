package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AccommodationVO;
import com.itgrids.partyanalyst.dto.NotificationDeviceVO;


public interface INotificationService {
	 public NotificationDeviceVO saveUsersDataInNotificationDeviceTable(final NotificationDeviceVO notifyVO);
	 public List<NotificationDeviceVO> getActiveNotifications(NotificationDeviceVO notifyVO);
	 public List<AccommodationVO> getAccommodationTrackingInfoByNotificationType(Long notificationType, Long locationType);
	 public String pushNotification(NotificationDeviceVO notifyVO);
}
