package com.itgrids.partyanalyst.service;

import java.util.Date;

public interface IUserTrackingReportService {
	
	public String saveUserLogOutDetails(Date fromDate, Date toDate, Boolean isToday);

}
