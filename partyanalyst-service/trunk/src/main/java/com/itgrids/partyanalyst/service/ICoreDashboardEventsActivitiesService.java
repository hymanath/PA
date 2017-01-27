package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardEventsActivitiesService {
	
	
	public List<EventDetailsVO> getEventBasicCountDetails(List<Long> eventIds,Long activityMemberId,Long stateId);
	public List<List<UserTypeVO>> getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(List<Long> eventIds,Long activityMemberId,Long userId,Long userTypeId,Long stateId);
	public List<EventDetailsVO> getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(Long userTypeId,Long stateId,Long activityMemberId,List<Long> eventIds);
    public List<UserTypeVO> getSelectedChildMembersForEvents(Long parentActivityMemberId,List<Long> childUserTypeIds,String reportType,Long stateId,List<Long> eventIds,String searchType);
    public EventDetailsVO getEventPoorPerformanceLocation(Long userTypeId,Long stateId,Long activityMemberId,List<Long> eventsId,String searchType);
    public List<List<UserTypeVO>> getUserTypeActivityConductedCnt(List<Long> activityIds,List<Long> activityLevelIds, Long activityMemberId,Long userId,Long userTypeId,Long stateId,String fromDateStr,String toDateStr);
   
}
