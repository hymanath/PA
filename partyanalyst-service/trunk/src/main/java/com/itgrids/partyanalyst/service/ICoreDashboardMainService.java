package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardMainService {
	
	public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts1(Long userId,Long activityMemberId,Long userTypeId,String state,List<Long> basicCommitteeIds,String dateString);
	public List<UserTypeVO> getSelectedChildUserTypeMembers(Long parentActivityMemberId,Long childUserTypeId,String state,List<Long> basicCommitteeIds,String dateString);
}
