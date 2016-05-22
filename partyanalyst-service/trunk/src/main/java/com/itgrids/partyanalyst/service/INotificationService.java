package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.dto.ResultStatus;


public interface INotificationService {
	 public ResultStatus saveUsersDataInNotificationDeviceTable(final NotificationDeviceVO notifyVO);
}
