package com.itgrids.partyanalyst.notification.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ISchedulerService {
	
	public ResultStatus deleteSearchEngineAccessedURLsFromUserTracking();
	
	public List<String> getAllSearchEngineIPAddresses();
}
