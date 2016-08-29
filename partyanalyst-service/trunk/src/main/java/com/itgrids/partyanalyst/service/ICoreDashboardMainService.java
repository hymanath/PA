package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardMainService {
	
	public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts1(Long userId,Long activityMemberId,Long userTypeId,String state,List<Long> basicCommitteeIds,String dateString);
	public List<UserTypeVO> getSelectedChildUserTypeMembers(Long parentActivityMemberId,Long childUserTypeId,String state,List<Long> basicCommitteeIds,String dateString);
	public List<UserTypeVO> getDirectChildActivityMemberCommitteeDetails(Long activityMemberId,Long userTypeId,String state,List<Long> basicCommitteeIds,String dateString);
	public CommitteeDataVO getTopPoorPerformancecommittees(Long activityMemberId,List<Long> basicCommitteeIds,String state,String dateString);
	public List<CommitteeDataVO> committeesPerformanceCohort(List<Long> tdpCommitteeLevelIdsClicked,List<Long> basicCommitteeIds,String committeeStatus,Long userLocationLevelId,List<Long> userLocationLevelValues,List<String> groupingLocationsList,String startDateString,String endDateString,String state);
	public List<CommitteeDataVO> getLevelWiseBasicCommitteesCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	public CommitteeDataVO getCommitteesBasicCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	public List<CommitteeDataVO> getTopPoorCommitteeLocations(Long activityMemberId,List<Long> basicCommitteeIds,String state,String dateString);
	
	public TrainingCampProgramVO getTrainingCampBasicDetailsCntOverview(Long userAccessLevelId,List<Long> userAccessLevelValues);
	public List<TrainingCampProgramVO> getTrainingCampProgramsDetailsCntByDistrict(Long userAccessLevelId,List<Long> userAccessLevelValues);
	public List<List<UserTypeVO>> getUserTypeWiseTotalEligibleAndAttendedCnt(Long userId,Long userTypeId,Long activityMembersId,Long userAccessLevelId,List<Long> userAccessLevelValues);
	public List<UserTypeVO> getSelectedChildTypeMembersForTrainingProgram(Long parentActivityMemberId,Long childUserTypeId,Long locationLevelId,List<Long> locationLevelValues,String reportType);
	
}
