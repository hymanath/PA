package com.itgrids.partyanalyst.service;

import java.util.Date;

import com.itgrids.partyanalyst.dto.MeetingSummeryVO;

public interface IPartyMeetingDashboardService {

	//public MeetingSummeryVO getMeetingsSummeryForDashboard(Long partyMeetingLevelId,String fromDate,String toDate,Long partyMeetingTypeId,Long locationLevelId,Long locationValue);
	public MeetingSummeryVO getMeetingsSummeryForDashboard(Long partyMeetingLevelId,String fromDateString,String toDateString,Long partyMeetingTypeId,Long locationLevelId,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId);
}
