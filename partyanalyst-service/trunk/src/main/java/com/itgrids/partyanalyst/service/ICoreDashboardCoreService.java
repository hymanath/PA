package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardCoreService {
	public TrainingCampVO getTrainingCampFeedBackDetails(Long userAccessLevelId, List<Long> userAccessLevelValueList,Long stateId, String fromDateStr, String toDateStr, List<Long> enrollmentYearIdList, List<Long> programIdList);
	public List<List<UserTypeVO>> getUserTypeWiseTotalEligibleAndAttendedCnt(Long userId,Long userTypeId,Long activityMembersId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr,List<Long> enrollmentYearIds,List<Long> programIdList);

}
