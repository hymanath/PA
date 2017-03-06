package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MeetingBasicDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingDetailsInfoVO;
import com.itgrids.partyanalyst.dto.MeetingDtlsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsDataVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardPartyMeetingService {
	public PartyMeetingsVO getPartyMeetingBasicCountDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues);
	public List<List<UserTypeVO>> getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt(Long userId,Long userTypeId,Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues);
	public List<PartyMeetingsVO> getPartyMeetingTypeByPartyMeetingMainType(Long partyMeetingMainTypeId);
	
	public List<UserTypeVO> getSelectedChildUserTypeMembersWithMeetingsCount(Long parentActivityMemberId,List<Long> childUserTypeIds,String state,String startDateString,String endDateString,List<Long> partyMeetingTypeIds);
	public List<UserTypeVO> getDirectChildActivityMemberMeetingsDetails(Long activityMemberId,Long userTypeId,String state,String startDateString,String endDateString,List<Long> partyMeetingTypeIds);
	public List<PartyMeetingsDataVO> getTopPoorMeetingLocations(Long activityMemberId,List<Long> partyMeetingTypeIds,String state,String startDateString,String endDateString);
	public List<PartyMeetingsVO> getPartyMeetingCntDetailstLevelWiseByUserAccessLevel(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues);
	public List<PartyMeetingsDataVO> getPartyMeetingsMainTypeOverViewData(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString,Long partyMeetingId);
	public PartyMeetingsDataVO getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString);
	public List<PartyMeetingsDataVO> getParyMeetingTypeDetailsDistrictWise(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString);

	public ResultStatus insertDataInToPartyMeetingStatusTable();
	public List<IdNameVO> getParyMeetingTypeDetailsPerDistrict(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString, Long distId);
	public String getMeetingRecentTime();
	
	public List<PartyMeetingsVO> getPartyMeetingCommentsDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues,String meetingStatus,String partyMeetingLevel,String isComment,Long locationId,String locationType);
	public List<PartyMeetingsVO> getPartyMeetingComulativeCommentDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues,String meetingStatus,String partyMeetingLevel,String isComment,String reportType,Long locationId,String locationType);
	
	public List<PartyMeetingsVO> getDistrictByState(Long stateId,Long activityMemberId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues,String meetingStatus,String partyMeetingLevel,String isComment);
	public List<PartyMeetingsVO> getConstituencyByDistrictId(Long districtId,Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues,String meetingStatus,String partyMeetingLevel,String isComment);
	public List<PartyMeetingsVO> getMandalByConstituyId(Long constituenyId,Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues,String meetingStatus,String partyMeetingLevel,String isComment);
	public List<IdNameVO> getParyMeetingMemberDtls(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,Long meetingId,String state,String startDateString,String endDateString,String status,String searchDesignation);
	public List<MeetingDtlsVO> getParyMeetingDetailsDistrictWise(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString, Long partyMeetingId, Long sessionId);
	public List<PartyMeetingsDataVO> getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedMeetingSessionWise(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString,List<Long> partyMeetingIds);
	public List<IdNameVO> getMeetingMemberDtls(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString, Long partyMeetingId, Long sessionId, String status,Long distId,boolean isNonInvitee);
	public List<IdNameVO> getPartyMeetingSession(Long partyMeetingId);
	public List<IdNameVO> getPublicRepAndcommitteeInviteeDtls(Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,String state,String startDateString, String endDateString,List<Long> partyMeetingIds,String category, List<Long> categoryIds,String location, Long sessionId);
	public MeetingDetailsInfoVO getMeetingDtls(Long activityMemberId, Long partyMeetingMainTypeId,List<Long> partyMeetingTypeIds,Long state,String startDateString,String endDateString);
	public Map<Long,MeetingDetailsInfoVO> getMeetingListDtls(Long activityMemberId,String state,String startDateString,String endDateString);  
	public List<MeetingBasicDetailsVO> locationWiseMeetingDetails(Long activityMemberId, Long partyMeetingMainTypeId, List<Long> partyMeetingTypeIds,List<Long> locLevelIdList, Long state,String startDateString, String endDateString);
	public List<PartyMeetingsDataVO> getCustomPartyMeetingsMainTypeOverViewData(Long activitymemberId,Long partyMeetingMainTypeId,Long state,String startDateString, String endDateString);
}
