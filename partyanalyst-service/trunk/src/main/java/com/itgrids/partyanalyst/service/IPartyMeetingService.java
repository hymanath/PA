package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PartyMeetingSummaryVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;

public interface IPartyMeetingService {
	public PartyMeetingVO getMeetingTypeWiseDescription(Long partyMeetingTypeId,Long tdpCadreId);
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
	//public PartyMeetingSummaryVO getMeetingSummaryForLocation(Long locationLevel, List<Long> locationIds, String startDateStr, String endDateStr);
	public PartyMeetingSummaryVO getMeetingSummaryForLocation(Long typeOfMeeting,Long locationLevel,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId,String startDateStr,String endDateStr,Long meetingLevel);
	public List<PartyMeetingSummaryVO> getAttendentsInformation(List<Long> partyMeetingIds);
	public List<PartyMeetingSummaryVO> getAtrAndMOMOfMeetings(List<Long> partyMeetingIds);
	public PartyMeetingSummaryVO getMeetingSummaryForLocationCumulative(Long typeOfMeeting,Long locationLevel,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId,String startDateStr,String endDateStr,Long meetingLevel);
	public PartyMeetingSummaryVO getMeetingSummaryForGrouping(Long typeOfMeeting,Long locationLevel,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId,String startDateStr,String endDateStr,String groupingLocationType,Long meetingLevel);
	public PartyMeetingVO getSummaryForAMeeting(Long meetingId,String type);
}
