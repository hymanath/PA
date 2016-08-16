package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardService1 {
	
	public CommitteeDataVO getCommitteesBasicCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	public List<CommitteeDataVO> getLevelWiseBasicCommitteesCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	public List<CommitteeDataVO> getDistrictWiseCommitteesCountReport(String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts(Long userId,Long activityMemberId,Long userTypeId,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts1(Long userId,Long activityMemberId,Long userTypeId,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
}
