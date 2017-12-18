package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ComplaintStatusCountVO;
import com.itgrids.partyanalyst.dto.GrievanceReportVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.dto.TrainingCampSurveyVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardCoreService {
	public TrainingCampVO getTrainingCampFeedBackDetails(Long userAccessLevelId, List<Long> userAccessLevelValueList,Long stateId, String fromDateStr, String toDateStr, List<Long> enrollmentYearIdList, List<Long> programIdList);
	public List<List<UserTypeVO>> getUserTypeWiseTotalEligibleAndAttendedCnt(Long userId,Long userTypeId,Long activityMembersId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,String toDateStr,List<Long> enrollmentYearIds,List<Long> programIdList);
	public List<TrainingCampSurveyVO> getFeedbackOnLeaders(Long userAccessLevelId, List<Long> userAccessLevelValues, List<Long> trainingProgramIds,Long traingCampEnrollmentYearId,List<Long> trainingCampLevelIds,Long groupType);
	public List<KeyValueVO> getProgramIdsList();
	public List<KeyValueVO> getCadreCommiteeEnrollmentIds();
	public TrainingCampProgramVO getTrainingCampBasicDetailsCntOverviewDayWise(Long globalActivityMemberId,Long stateId,String fromDate,String toDate,List<Long> enrollmentYearIdsList,List<Long> programIdList,List<Long> tdpCommitteeLevelIds);
	public TrainingCampVO getTrainingCampFeedBackDetails(Long activityMemberId,String commiteeLevelId ,Long stateId, String dateStr, List<Long> enrollmentYearIdList, List<Long> programIdList);
	public  List<TrainingCampSurveyVO> getTrainingCampFeedBackDetailsProgramWise(List<Long> programIdList,Long userAccessLevelId,List<Long> userAccessLevelValues,List<Long> enrollmentYrIds,List<Long> committeeLevelIdList);
	public List<TrainingCampSurveyVO> getTrainingQuizDetails(List<Long> programIdList,Long userAccessLevelId,List<Long> userAccessLevelValues,List<Long> enrollmentYrIds,List<Long> committeeLevelIdArr);
	public List<GrievanceReportVO> getEffiencyDetailsForgrivanceByDates( List<Integer> daysList,String grievanceReqType, Long enrollmentyearId);
	public List<GrievanceReportVO> getgrivanceDetailsByIssueType(Long enrollmentyearId,String searchType,String searchValue);
	public List<GrievanceReportVO> getgrivanceDetailsBySearch(Long enrollmentyearId,String searchType,String searchValue);
	public List<ComplaintStatusCountVO> getCategoryAndIssuetypeStatusCount(String inputType,String fromDate,String toDate,String stateIds,Long enrollmentYrId,String task);
}
