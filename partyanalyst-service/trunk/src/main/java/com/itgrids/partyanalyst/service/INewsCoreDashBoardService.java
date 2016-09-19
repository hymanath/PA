package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ChildUserTypeVO;
import com.itgrids.partyanalyst.dto.CoreDashBoardVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface INewsCoreDashBoardService {
	public List<List<UserTypeVO>> getUserTypeWiseNewsCounts(Long userId,Long activityMemberId,Long userTypeId,String state,String fromDate,String toDate,Long benefitId,List<Long> npIds,List<Long> impactScopeIdsList);
	public List<ChildUserTypeVO> getPartyComparisonChildUserTypeMembers(Long parentActivityMemberId,List<Long> childUserTypeId,String state,String startDate,String endDate,List<Long> npIdsList);
	public List<ChildUserTypeVO> getPartyCompareSubLevelMemberDetails(Long activityMemberId,Long userTypeId,String state,String startDate,String endDate,List<Long> npIds);
	public List<ChildUserTypeVO> getCommanPartyComparisonChildUserTypeMembers(Map<Long,UserTypeVO> childActivityMembersMap,List<CoreDashBoardVO> wsResultList,String output);
}