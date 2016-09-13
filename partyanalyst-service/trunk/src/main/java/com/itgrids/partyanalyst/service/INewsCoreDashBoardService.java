package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ChildUserTypeVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface INewsCoreDashBoardService {
	public List<List<UserTypeVO>> getUserTypeWiseNewsCounts(Long userId,Long activityMemberId,Long userTypeId,String state,String fromDate,String toDate,Long benefitId,List<Long> npIds);
	public List<ChildUserTypeVO> getPartyComparisonChildUserTypeMembers(Long parentActivityMemberId,Long childUserTypeId,String state,String startDate,String endDate,List<Long> npIdsList);
	public List<ChildUserTypeVO> getPartyCompareSubLevelMemberDetails(Long activityMemberId,Long userTypeId,String state,String startDate,String endDate,List<Long> npIds);
}
