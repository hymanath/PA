package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardPartyMeetingService {
	public PartyMeetingsVO getPartyMeetingBasicCountDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues);
	public List<List<UserTypeVO>> getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt(Long userId,Long userTypeId,Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> partyMeetingTypeValues);
	public List<PartyMeetingsVO> getPartyMeetingTypeByPartyMeetingMainType(Long partyMeetingMainTypeId);
	
	public List<UserTypeVO> getSelectedChildUserTypeMembersWithMeetingsCount(Long parentActivityMemberId,Long childUserTypeId,String state,String startDateString,String endDateString,List<Long> partyMeetingTypeIds);
	public List<UserTypeVO> getDirectChildActivityMemberMeetingsDetails(Long activityMemberId,Long userTypeId,String state,String startDateString,String endDateString,List<Long> partyMeetingTypeIds);
}
