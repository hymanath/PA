package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.PartyMeetingVO;

public interface IPartyMeetingService {
	public PartyMeetingVO getMeetingTypeWiseDescription(Long partyMeetingTypeId);
	public PartyMeetingVO getPartyMeetingDetailsBySearchType(Long tdpCadreId );
	public PartyMeetingVO getPartyMeetingsForCadrePeople(Long tdpCadreId);
}
