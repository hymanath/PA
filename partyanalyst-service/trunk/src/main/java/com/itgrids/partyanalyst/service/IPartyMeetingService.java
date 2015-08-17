package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.PartyMeetingVO;

public interface IPartyMeetingService {
	public PartyMeetingVO getMeetingTypeWiseDescription(Long partyMeetingTypeId);
	public PartyMeetingVO getPartyMeetingDetailsBySearchType(Long tdpCadreId );
	public PartyMeetingVO getPartyMeetingsForCadrePeople(Long tdpCadreId);
	public String updateMeetingPoint(final Long minuteId,final String minuteText,final Long updatedBy);
	public String deleteMeetingMinutePoint(Long minuteId,Long updateBy);
}
