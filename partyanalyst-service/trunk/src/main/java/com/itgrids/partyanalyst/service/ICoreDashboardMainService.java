package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.BoothInchargesVO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.CoreDebateVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardMainService {
	
	public List<List<UserTypeVO>> getUserTypeWiseCommitteesCompletedCounts1(Long userId,Long activityMemberId,Long userTypeId,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString,List<Long> committeeEnrollmentYearsIdsLst, String committeType);
	public List<UserTypeVO> getSelectedChildUserTypeMembers(Long parentActivityMemberId,List<Long> childUserTypeIds,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString,List<Long> committeeEnrollmentYearsIdsLst,String committeType);
	public List<UserTypeVO> getDirectChildActivityMemberCommitteeDetails(Long activityMemberId,Long userTypeId,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString,List<Long> committeeEnrollmentYearsIdsLst,String committeType);
	public CommitteeDataVO getTopPoorPerformancecommittees(Long activityMemberId,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String state,String dateString,List<Long> committeeEnrollmentYearsIdsLst,String committeType);
	public List<CommitteeDataVO> committeesPerformanceCohort(List<Long> tdpCommitteeLevelIdsClicked,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String committeeStatus,Long userLocationLevelId,List<Long> userLocationLevelValues,String dateString,String state,List<Long> committeeEnrollmentYearsIdsLst,String committeType);
	public List<CommitteeDataVO> getLevelWiseBasicCommitteesCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString,List<Long> committeeEnrollmentYearsIdsLst,String committeType);
	public CommitteeDataVO getCommitteesBasicCountReport(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String dateString,List<Long> committeeEnrollmentYearsIdsLst);
	public List<CommitteeDataVO> getTopPoorCommitteeLocations(Long activityMemberId,Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap,String state,String dateString,List<Long> committeeEnrollmentYearsIdsLst,String committeType);
	//Training 
	public TrainingCampProgramVO getTrainingCampBasicDetailsCntOverview(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String dateStr,List<Long> enrollmentYearIds,List<Long> programIdList);
	public List<TrainingCampProgramVO> getTrainingCampProgramsDetailsCntByDistrict(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String dateStr);
	public List<List<UserTypeVO>> getUserTypeWiseTotalEligibleAndAttendedCnt(Long userId,Long userTypeId,Long activityMembersId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String dateStr,List<Long> enrollmentYearIds,List<Long> programIdList);
	public List<UserTypeVO> getSelectedChildTypeMembersForTrainingProgram(Long parentActivityMemberId,List<Long> childUserTypeIds,Long locationLevelId,List<Long> locationLevelValues,String reportType,Long stateId,String dateStr,List<Long> enrollmentYearIds,List<Long> programIdsList);
	public TrainingCampProgramVO getTrainingProgramPoorCompletedLocationDtls(Long userTypeId,Long activityMemberId,Long stateId,String dateStr,List<Long> enrollmentYearIds,List<Long> programIdList);
	public List<TrainingCampProgramVO> getTrainingCampProgramsBasicCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String dateStr,List<Long> enrollmentYearIds);
	public String getTrainingCampRecentTime();
	//Debate
	public List<CoreDebateVO> getPartyWiseTotalDebateDetails(String startDateStr,String endDateStr,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<CoreDebateVO> getSpokesPersonWiseDebate(String startDateStr,String endDateStr,String searchType,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList);
	public List<CoreDebateVO> getScaleBasedPerformanceCohort(String startDateStr,String endDateStr,String state,List<Long> debateLocationIdList);
	public List<CoreDebateVO> getCandidateOverAllPerformanceCohort(String startDateStr,String endDateStr,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList);
	public List<CoreDebateVO> getChannelAndPartyWiseDetails(String startDateStr,String endDateStr,String state,List<Long> debateLocationIdList);
	public List<CoreDebateVO> getRoleBasedPerformanceCohort(String startDateStr,String endDateStr,String state,List<Long> debateLocationIdList);
	public List<UserDataVO> getbasicCommitteeDetails();
	public List<IdNameVO> getStateLevelCampAttendedDetails(List<Long> programIdList,Long stateId,String dateStr,String option,List<Long> enrollYrIds);
	public List<List<IdNameVO>> getStateLevelCampDetailsRepresentative(List<Long> programIdList, Long stateId, String dateStr,List<Long> enrollYrIds);
	public List<List<IdNameVO>> getDistrictWiseCampAttendedMembers(List<Long> programIdList, Long stateId, String dateStr,List<Long> enrollmentYrIds);
	public List<CoreDebateVO> getRolesPerformanceOfCandidate(String startDateStr,String endDateStr,List<Long> roles,String state,List<Long> participantLocationIdList,List<Long> debateLocationIdList);
	public List<IdNameVO> getDebateRolesNew();
	public List<IdNameVO> getCandidateDtlsPerDist(Long distId, Long programId, Long stateId, String strDate);   //,String dateStr
	public List<IdNameVO> getLeaderShipCandidateDtlsPerDist(Long userAccessLevelId, List<Long> userAccessLevelValues, Long stateId, Long distId, String dateStr,List<Long> enrollmentYearIds,List<Long> trainingCampProgramIds);
	
	public List<TrainingCampProgramVO> getTrainingCampProgramsDetailsCntByUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr,Long userTypeId,Long activityMemberId,List<Long> enrollmentYearIds,List<Long> trainingCampProgramIds);
	public String getLatestDebate();
	public List<CoreDebateVO> getCoreDebateBasicDetailsOfParty(Long partyId,String startDateStr,String endDateStr,String searchType,Long candidateId,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public List<IdNameVO> getTrainingProgramMemberDtlsStatusWise(List<Long> programIdList,Long stateId,String dateStr,String status,String designation,Long designationId);
	public List<List<IdNameVO>> getStateLevelCampDetailsDayWise(List<Long> programIdList, Long stateId, String dateStr);
	public List<CoreDebateVO> getCandidateWiseDebateDetailsOfCore(Long partyId,String startDateStr,String endDateStr,Long candidateId,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList);
	public BoothInchargesVO  getUserTypeWiseBoothCommitteesInchargeDetails(Long activityMemberId,String state,String dateString,List<Long> committeeEnrollmentYearsIdsLst);
	public List<BoothInchargesVO> getBoothCommitteeInchargesCount(Long activityMemId,List<Long> committeeEnrlmntYrIds,String dateStr,Long stateId);
	public TrainingCampProgramVO getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise(Long userAccessLevelId, List<Long> userAccessLevelValues,Long stateId, String fromDate, String toDateStr, List<Long> enrollmentYearIds,List<Long> programIdList);
	public List<Long> getTrainingCampPrograms(Long enrollmentId);
	public List<CommitteeDataVO> getCommitteeDetailedReport(List<Long> enrollmentYearIdsList,Long committeeLevelId,String fromDate, String toDate, List<Long> basicCommitteeTypeIdsList, List<Long> committeeTypeIdsList,Long locationScopeId, List<Long> locationValuesList );
}
