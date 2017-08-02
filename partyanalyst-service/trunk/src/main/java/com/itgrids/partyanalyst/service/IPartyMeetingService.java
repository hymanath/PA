package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.MeetingTrackingVO;
import com.itgrids.partyanalyst.dto.MeetingsVO;
import com.itgrids.partyanalyst.dto.PMMinuteVO;
import com.itgrids.partyanalyst.dto.PartyMeetingDataVO;
import com.itgrids.partyanalyst.dto.PartyMeetingStatusVO;
import com.itgrids.partyanalyst.dto.PartyMeetingSummaryVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingWSVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;

public interface IPartyMeetingService {
	public PartyMeetingVO getMeetingTypeWiseDescription(Long partyMeetingTypeId,Long tdpCadreId);
	public PartyMeetingVO getPartyMeetingDetailsBySearchType(Long tdpCadreId );
	public PartyMeetingVO getPartyMeetingsForCadrePeople(Long tdpCadreId);
	public String updateMeetingPoint(final Long minuteId,final String minuteText,final Long updatedBy,final Long partyMeetingId,final Long levelId,final Long levelValue,final String isActionable,
			final Long statusId,final Long stateId,final Long districtId,final Long constituencyId,final Long tehsilId,final Long panchayatId,final Long isGovtParty);
	public String deleteMeetingMinutePoint(Long minuteId,Long updateBy);
	public String updateMeetingAtrPoint(final Long atrId, final String request,final String actionTaken,final String raisedBy,final Long updatedBy,final Long locationId,Long meetingId,Long locationScope);
	public String deleteMeetingAtrPoint(final Long atrId,final Long updatedBy);
	public String deletePartyMeetingDocument(Long docId);
	public PartyMeetingVO getAtrPointsForAMeeting(Long partyMeeingId);
	public PartyMeetingVO getDocumentDetailsForAMeeting(Long partyMeetingId,String accessType,String accessValue);
	public PartyMeetingVO getTheMinutePointsForAMeeting(Long meetingId,String accessType,String accessValue);
	//public PartyMeetingSummaryVO getMeetingSummaryForLocation(Long locationLevel, List<Long> locationIds, String startDateStr, String endDateStr);
	public PartyMeetingSummaryVO getMeetingSummaryForLocation(Long typeOfMeeting,Long locationLevel,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId,String startDateStr,String endDateStr,Long meetingLevel);
	public List<PartyMeetingSummaryVO> getAttendentsInformation(List<Long> partyMeetingIds);
	public List<PartyMeetingSummaryVO> getAtrAndMOMOfMeetings(List<Long> partyMeetingIds);
	public PartyMeetingSummaryVO getMeetingSummaryForLocationCumulative(Long typeOfMeeting,Long locationLevel,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId,String startDateStr,String endDateStr,Long meetingLevel);
	public PartyMeetingSummaryVO getMeetingSummaryForGrouping(Long typeOfMeeting,Long locationLevel,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId,String startDateStr,String endDateStr,String groupingLocationType,Long meetingLevel);
	public PartyMeetingVO getSummaryForAMeeting(Long meetingId,String type,String accessType,String accessValue);
	public MeetingTrackingVO getPartyMeetingsDetailsForCadreByCommitteeLevel(Long tdpCadreId,String searchTypeStr , 
			 Long committeeLevelId,Long committeeLevelValue,String formDateStr,String toDateStr,String isFirst,int firstRecord,int maxResult);
	public PartyMeetingWSVO getAttendedDetailsForPartyMeeting(Long partyMeetingId);
	public PartyMeetingWSVO getTdpCadreDetailsForPartyMeeting(Long partyMeetingId,String searchType,List<String> designationsList);
	public List<PartyMeetingVO> getLevelWiseMeetingDetails(String startDate,String endDate,Long userId,Long levelId);
	public String updateConductedDetails(Long meetingId,String isConducted,String remarks,String conductedDate);
	public String updateConductedStatus(Long meetingId,String isConducted,Long userId);
	public String updateConductedDate(Long meetingId,String conductedDate,Long userId);
	public String updateConductedReason(Long meetingId,String remarks,Long userId);
	public List<PartyMeetingsVO>  getLocationWisePartyMeetings(String locationType,Long locationValue,String startDateString,String endDateString);
	public List<PartyMeetingStatusVO> getMeetingDetailsForALevelByLocationId(String locationType,Long locationValue,List<Long> partyMeetingLevelId,int month,int year);
	public List<PMMinuteVO> getPartyMeetingMinuteRetrieveDetails(Long minuteId);
	
	public List<PartyMeetingDataVO> constituencyWisePartyMeetingDetails(int month , int year);
	public List<MeetingsVO> getConstWiseNotConductedPartyMeetings(int month , int year);
	public List<PartyMeetingVO> getUpdateDetails(Long locationLvlId,String startDateStr,String endDateStr,String status,List<Long> distIds,List<Long> constIds,String locationType,String thridPartyStatus);
	public PartyMeetingVO getDocumentsForMeetingId(Long partyMeetingId);

}
