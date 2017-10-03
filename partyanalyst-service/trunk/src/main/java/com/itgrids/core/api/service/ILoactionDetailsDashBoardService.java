package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PartyMeetingsVO;

public interface ILoactionDetailsDashBoardService {
	public List<PartyMeetingsVO> getMeetingTypeWiseTotalMeetings(Long locationLevel, Long locationId, String fromDateStr,String toDateStr);
}
