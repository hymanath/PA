package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PartyMeetingDataVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;

public interface ILoactionDetailsDashBoardService {
	public List<PartyMeetingsVO> getMeetingTypeWiseTotalMeetings(Long locationLevel, List<Long> locationIds, String fromDateStr,String toDateStr);
	public List<PartyMeetingsVO> getMeetingLevelWiseTotalMeetings(Long locationLevel, List<Long> locationIds, String fromDateStr,String toDateStr);
	public List<PartyMeetingsVO> getCommitteeMeetingStatistics(Long locationLevel, List<Long> locationIds, String fromDateStr,String toDateStr);
	public List<PartyMeetingsVO> getSpecialMeetingStatistics(Long locationLevel, List<Long> locationIds, String fromDateStr,String toDateStr);
	public List<PartyMeetingsVO> getBelowLevelMeetingConductedCount(Long locationLevel, List<Long> locationIds, String fromDateStr,String toDateStr);
	public List<PartyMeetingDataVO> getMeetingsDetails(Long locationId, Long locationLevel, List<Long> locationIds, String fromDateStr,String toDateStr,String meetingType);
}
