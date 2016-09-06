package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.DashboardCommentVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardService1 {
	
	//public CommitteeDataVO getCommitteesBasicCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	//public List<CommitteeDataVO> getLevelWiseBasicCommitteesCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	public List<CommitteeDataVO> getDistrictWiseCommitteesCountReport(String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	//public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts(Long userId,Long activityMemberId,Long userTypeId,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	//public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts1(Long userId,Long activityMemberId,Long userTypeId,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	//public List<CommitteeDataVO> committeesPerformanceCohort(List<Long> tdpCommitteeLevelIdsClicked,List<Long> basicCommitteeIds,String committeeStatus,Long userLocationLevelId,List<Long> userLocationLevelValues,List<String> groupingLocationsList,String startDateString,String endDateString,String state);
	//public Long getStateIdByState(String state);
	// public List<Long> getAssemblyConstituencyIdsByParliamentConstituencyIds(List<Long> parliamentIds);
	 //public ResultStatus savingDashboardComment( Long userId,String comment,Long dashBoardComponentId,Long dashboardCommentId);
	  public List<DashboardCommentVO> displayDashboardComments(Long userId,Long dashBoardComponentId);
	  public ResultStatus savingDashboardComment(DashboardCommentVO vo,Long userId);
	  public String deleteDashBoardcomments(Long dashboardCommentId);
	
}
