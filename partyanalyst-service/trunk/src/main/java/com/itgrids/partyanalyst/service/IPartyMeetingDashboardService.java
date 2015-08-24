package com.itgrids.partyanalyst.service;

import java.util.Date;

import com.itgrids.partyanalyst.dto.MeetingSummeryVO;

public interface IPartyMeetingDashboardService {

	public MeetingSummeryVO getMeetingsSummeryForDashboard(Long partyMeetingLevelId,Date fromDate,Date toDate,Long partyMeetingTypeId,Long locationLevelId,Long locationValue);
}
