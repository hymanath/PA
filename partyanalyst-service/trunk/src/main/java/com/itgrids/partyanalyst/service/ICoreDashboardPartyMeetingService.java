package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MeetingBasicDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingDetailsInfoVO;
import com.itgrids.partyanalyst.dto.MeetingDtlsVO;
import com.itgrids.partyanalyst.dto.MeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsDataVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SessionVO;
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
	public Map<Long,MeetingDetailsInfoVO> getMeetingListDtls(Long activityMemberId,Long state,String startDateString,String endDateString);  
	public List<MeetingBasicDetailsVO> locationWiseMeetingDetails(Long activityMemberId, Long partyMeetingMainTypeId, List<Long> partyMeetingTypeIds,List<Long> locLevelIdList, 
			Long state,String startDateString, String endDateString,Long partyMeetingGroupId);
	public List<PartyMeetingsDataVO> getCustomPartyMeetingsMainTypeOverViewData(Long activitymemberId,Long partyMeetingMainTypeId,Long state,String startDateString, String endDateString);
	public List<PartyMeetingsDataVO> getConstByDistrictIdForWiseCustomPartyMeetings(Long partyMeetinglevelId,Long districtId);
	public List<PartyMeetingsDataVO> getPanchayatOrWardsByMandalOrMuncIdForWiseCustomPartyMeetings(Long partyMeetingLevelId,Long mandalOrMuncId);
	public List<PartyMeetingsDataVO> getMandOrMuncByconstituencyIdForWiseCustomPartyMeetings(Long partyMeetingLevelId,Long constituencyId);
	public List<PartyMeetingsDataVO> getDistrictsForCustomMeetingImgesLst(Long activityMemberId,Long stateId,Long partyMeetingLevelId,String startDateStr,String endDateStr,Long meetingId,Long meetingGropuId,Long locationValue);
	public List<PartyMeetingsDataVO> getCustomWisePartyMeetingDocuments(String fromDateStr,String toDateStr,int startIndex,int maxIndex,Long activityMemberId,Long stateId,Long partyMeetingLevelId,Long meetingId,Long meetingGrpId,Long locationValue);
	public List<List<MeetingDtlsVO>> getDistWiseMeetingDtlsForDiffLevelOfMeetings(Long activityMemberId, Long partyMeetingMainTypeId, Long locLevelId, Long stateId,String startDateString, String endDateString, Long partyMeetingGroupId,Long sessionId);
	public List<PartyMeetingsDataVO> getCmmttsAndPblcRprsnttvMembersInvitedAndAttendedToSeeionWiseMultiLocationMeetingDtls(Long activityMemberId, Long partyMeetingMainTypeId, Long locLevelId, Long stateId,String startDateString, String endDateString, Long partyMeetingGroupId);
	public MeetingVO getMultiLocationWiseMeetingGroupsData(Long partyMeetnMainTypId,Long activityMemberId,String fromDateStr,String toDateStr,Long stateId);
	public MeetingBasicDetailsVO getPartyLevelIdWiseMeetingsCount(Long partyMeetnMainTypId,
			Long activityMemberId,String fromDateStr,String toDateStr,Long stateId,Long partyMeetingLevelId,Long partyMeetngGrpId);
	public IdNameVO getPartyLevelIdWiseMeetingAttendanceDetails(Long partyMeetngId,Long partyMeetnMainTypId,Long activityMemberId,String fromDateStr,String toDateStr,Long stateId,List<Long> levelIdsList,Long partyMeetngGrpId,Long sessionTypId,String cadreType,Long locationValId);
	
	public List<SessionVO> getPartyMeetingsSessionWiseIndividualDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,
				List<Long> partyMeetingTypeValues,String meetingStatus,String partyMeetingLevel,String isComment,Long locationId,String locationType);
	 public List<List<MeetingDtlsVO>> getDistWiseMeetingsBaseDtlsForDiffLevelOfMeetings(Long activityMemberId, Long partyMeetingMainTypeId,
			 Long locLevelId, Long stateId,String startDateString, String endDateString, Long partyMeetingGroupId,Long sessionId,String type);
	 public PartyMeetingsVO getMOMBasicCountDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues);
	 public PartyMeetingsVO getMOMDetailedBlockDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues,String levelType);
}
