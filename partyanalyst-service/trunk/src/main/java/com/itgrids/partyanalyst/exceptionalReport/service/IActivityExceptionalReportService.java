package com.itgrids.partyanalyst.exceptionalReport.service;

import com.itgrids.partyanalyst.dto.ActivityExceptionalReportVO;
import com.itgrids.partyanalyst.dto.InputVO;

public interface IActivityExceptionalReportService {

	public ActivityExceptionalReportVO getActivityPerformanceDetailsLocationWise(InputVO inputVO) ;
	public ActivityExceptionalReportVO getActivityAttendedAndImageCoveredDetails(InputVO inputVO);
}
