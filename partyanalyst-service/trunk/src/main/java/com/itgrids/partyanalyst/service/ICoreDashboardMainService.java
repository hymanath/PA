package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.CoreDebateVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardMainService {
	
	public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts1(Long userId,Long activityMemberId,Long userTypeId,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString);
	public List<UserTypeVO> getSelectedChildUserTypeMembers(Long parentActivityMemberId,Long childUserTypeId,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString);
	public List<UserTypeVO> getDirectChildActivityMemberCommitteeDetails(Long activityMemberId,Long userTypeId,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString);
	public CommitteeDataVO getTopPoorPerformancecommittees(Long activityMemberId,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String state,String dateString);
	public List<CommitteeDataVO> committeesPerformanceCohort(List<Long> tdpCommitteeLevelIdsClicked,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String committeeStatus,Long userLocationLevelId,List<Long> userLocationLevelValues,String dateString,String state);
	public List<CommitteeDataVO> getLevelWiseBasicCommitteesCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString);
	public CommitteeDataVO getCommitteesBasicCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString);
	public List<CommitteeDataVO> getTopPoorCommitteeLocations(Long activityMemberId,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String state,String dateString);
	//Training 
	public TrainingCampProgramVO getTrainingCampBasicDetailsCntOverview(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String dateStr);
	public List<TrainingCampProgramVO> getTrainingCampProgramsDetailsCntByDistrict(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String dateStr);
	public List<List<UserTypeVO>> getUserTypeWiseTotalEligibleAndAttendedCnt(Long userId,Long userTypeId,Long activityMembersId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String dateStr);
	public List<UserTypeVO> getSelectedChildTypeMembersForTrainingProgram(Long parentActivityMemberId,Long childUserTypeId,Long locationLevelId,List<Long> locationLevelValues,String reportType,Long stateId,String dateStr);
	public TrainingCampProgramVO getTrainingProgramPoorCompletedLocationDtls(Long userTypeId,Long activityMemberId,Long stateId,String dateStr);
	public List<TrainingCampProgramVO> getTrainingCampProgramsBasicCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String dateStr);
	//Debate
	public List<CoreDebateVO> getPartyWiseTotalDebateDetails(String startDateStr,String endDateStr,String state);
	public List<CoreDebateVO> getSpokesPersonWiseDebate(String startDateStr,String endDateStr,String searchType,String state);
	public List<CoreDebateVO> getScaleBasedPerformanceCohort(String startDateStr,String endDateStr,String state);
	public List<CoreDebateVO> getCandidateOverAllPerformanceCohort(String startDateStr,String endDateStr,String state);
	public List<CoreDebateVO> getChannelAndPartyWiseDetails(String startDateStr,String endDateStr,String state);
	public List<CoreDebateVO> getRoleBasedPerformanceCohort(String startDateStr,String endDateStr,String state);
	public List<UserDataVO> getbasicCommitteeDetails();
	public List<CoreDebateVO> getRolesPerformanceOfCandidate(String startDateStr,String endDateStr,List<Long> roles,String state);
}
