package com.itgrids.partyanalyst.exceptionalReport.service;

import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.PartyMeetingExceptionalReportVO;

public interface IPartyMeetingExceptionalReportService {

	public PartyMeetingExceptionalReportVO getPartyMeetingExceptionReportMeetingLevelWise(InputVO inputVO);
}
