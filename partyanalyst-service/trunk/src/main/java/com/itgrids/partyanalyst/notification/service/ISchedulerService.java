package com.itgrids.partyanalyst.notification.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ISchedulerService {
	
	public ResultStatus deleteSearchEngineAccessedURLsFromUserTracking(Date fromDate,Date toDate);
	
	public List<String> getAllSearchEngineIPAddresses();
}
