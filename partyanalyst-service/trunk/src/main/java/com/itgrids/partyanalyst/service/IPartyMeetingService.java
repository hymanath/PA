package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.PartyMeetingVO;

public interface IPartyMeetingService {
	public PartyMeetingVO getMeetingTypeWiseDescription(Long partyMeetingTypeId);
	public PartyMeetingVO getPartyMeetingDetailsBySearchType(Long tdpCadreId );
	public PartyMeetingVO getPartyMeetingsForCadrePeople(Long tdpCadreId);
	public String updateMeetingPoint(final Long minuteId,final String minuteText,final Long updatedBy,Long partyMeetingId);
	public String deleteMeetingMinutePoint(Long minuteId,Long updateBy);
	public String updateMeetingAtrPoint(final Long atrId, final String request,final String actionTaken,final String raisedBy,final Long updatedBy,final Long locationId,Long meetingId,Long locationScope);
	public String deleteMeetingAtrPoint(final Long atrId,final Long updatedBy);
	public String deletePartyMeetingDocument(Long docId);
	public PartyMeetingVO getAtrPointsForAMeeting(Long partyMeeingId);
	public PartyMeetingVO getDocumentDetailsForAMeeting(Long partyMeetingId);
	public PartyMeetingVO getTheMinutePointsForAMeeting(Long meetingId);
}
