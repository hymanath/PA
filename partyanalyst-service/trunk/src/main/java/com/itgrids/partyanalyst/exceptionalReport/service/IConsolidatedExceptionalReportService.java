package com.itgrids.partyanalyst.exceptionalReport.service;

import com.itgrids.partyanalyst.dto.ConsolidatedExceptionalReportVO;
import com.itgrids.partyanalyst.dto.InputVO;

public interface IConsolidatedExceptionalReportService {
	//public ConsolidatedExceptionalReportVO getConsolidatedPartyMeetingExceptionReportMeetingLevelWise(InputVO inputVO);
	public ConsolidatedExceptionalReportVO  getOverAllConsolidatedViewDetails(InputVO inputVO);
}
