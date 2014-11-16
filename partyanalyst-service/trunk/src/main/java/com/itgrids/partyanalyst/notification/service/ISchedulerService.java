package com.itgrids.partyanalyst.notification.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ISchedulerService {
	
	public ResultStatus deleteSearchEngineAccessedURLsFromUserTracking(Date fromDate,Date toDate);
	
	public List<String> getAllSearchEngineIPAddresses();
	
	public void saveDailyWmCorrectedMobileNUmbers(Date fromDate);
	
	public void prepareDatForCardPrinting(String prevDate);
	public void prepareDatForCardPrintingForZebra(String prevDate);
	public void prepareDatForCardPrintingForMax(String prevDate);
}
