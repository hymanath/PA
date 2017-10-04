package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PartyMeetingsVO;

public interface ILoactionDetailsDashBoardService {
	public List<PartyMeetingsVO> getMeetingTypeWiseTotalMeetings(Long locationLevel, Long locationId, String fromDateStr,String toDateStr);
	public List<PartyMeetingsVO> getMeetingLevelWiseTotalMeetings(Long locationLevel, Long locationId, String fromDateStr,String toDateStr);
	public List<PartyMeetingsVO> getCommitteeMeetingStatistics(Long locationLevel, Long locationId, String fromDateStr,String toDateStr);
	public List<PartyMeetingsVO> getSpecialMeetingStatistics(Long locationLevel, Long locationId, String fromDateStr,String toDateStr);
}
