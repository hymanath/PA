package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.PartyMeetingDataVO;



public interface IMeetingLocationDashboardService {
	public List<LocationVotersVO> getLocationWiseMeetingsCount(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr);
	public PartyMeetingDataVO getLocationWiseCommitteeMeetings(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr,Long partyMeetingMainTypeId);
	public PartyMeetingDataVO getLocationWiseStateMeetings(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr,Long partyMeetingMainTypeId);
	public PartyMeetingDataVO getLocationWiseSpecialMeetings(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr,Long partyMeetingMainTypeId);
	public List<PartyMeetingDataVO> getLocationWiseMeetingStatusDetails(Long searchLevelId,List<Long> levelValues,String startDateStr,String endDateStr,Long meetingTypeId,Long partyMeetinLevelId);
	public PartyMeetingDataVO getLocationWiseSpecialMeetingsMeetingsExpanction(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr,Long partyMeetingMainTypeId,Long partyMeetingTypeId);
}
