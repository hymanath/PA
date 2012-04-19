package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.UserTrackingReportVO;

public interface IUserTrackingReportService {
	
	public String saveUserLogOutDetails(Date fromDate, Date toDate, Boolean isToday);
	
	public List<UserTrackingReportVO> getTotalUniqueVisitorDetails(Date fromDate, Date toDate);
}
