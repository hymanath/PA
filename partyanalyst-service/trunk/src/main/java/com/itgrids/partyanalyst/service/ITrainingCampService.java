package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreDetailsVO;
import com.itgrids.partyanalyst.dto.CadreFeedbackVO;
import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.CallBackCountVO;
import com.itgrids.partyanalyst.dto.CallStatusVO;
import com.itgrids.partyanalyst.dto.CallTrackingVO;
import com.itgrids.partyanalyst.dto.CategoryFeedbackVO;
import com.itgrids.partyanalyst.dto.FeedbackInputVO;
import com.itgrids.partyanalyst.dto.FeedbackQuestionVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.MeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingWSVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SimpleDetailsVO;
import com.itgrids.partyanalyst.dto.SimpleVO;
import com.itgrids.partyanalyst.dto.SurveyTrainingsVO;
import com.itgrids.partyanalyst.dto.TraingCampCallerVO;
import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.dto.TrainingCadreVO;
import com.itgrids.partyanalyst.dto.TrainingCampCallStatusVO;
import com.itgrids.partyanalyst.dto.TrainingCampFeedBackDetailsVO;
import com.itgrids.partyanalyst.dto.TrainingCampScheduleVO;
import com.itgrids.partyanalyst.dto.TrainingCampSheduleDetailsVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.TrainingMemberVO;
import com.itgrids.partyanalyst.dto.VerifierVO;

public interface ITrainingCampService {

	public List<TraingCampCallerVO> getScheduleCallStatusCount(Long userId,Long callPurposeId);
	public TrainingCampScheduleVO getCallerWiseCallsDetails(List<Long> callerIdsList,String searchTypeId,String startDate,String endDate,String agentType);
	public TrainingCampVO getCampusWiseBatchWiseMembersDetails(List<Long> callerIdsList,String searchTypeId,String startDate,String endDate);
	public TrainingCampVO getCampusWiseDateWiseCampDetails(List<Long> campusIdsList,String searchTypeId,String startDate,String endDate);
	//public TrainingCampVO getCampusWiseDateWiseInterestedMembersDetails(List<Long> campusIdsList,String searchTypeId,String startDate,String endDate);
	public List<TraingCampCallerVO> getBatchCallStatusCount(Long userId,Long callPurposeId);
	public List<Long> getTrainingCampUserTypeIds(Long adminId,boolean isAdmin);
	public TrainingMemberVO getScheduleCallMemberDetails(TraingCampDataVO inputVo,Integer startIndex,Integer maxIndex);
	public List<BasicVO> getAllPrograms();
	public List<BasicVO> getCampsByProgramId(Long programId);
	public List<BasicVO> getSchedulesByCampId(Long campId);
	public List<BasicVO> getBatchesByScheduleId(Long scheduleId);
	//public List<BasicVO> getAllPrograms();
	//public List<BasicVO> getAllschedules();
	public TrainingCampScheduleVO getTrainingProgramMembersBatchCount(String startDateString,String endDateString);
	public List<TraingCampCallerVO> getScheduleStatusList();
	public List<TraingCampCallerVO> getStatusList();
	public ResultStatus updateCadreStatusForTraining(final TrainingCadreVO inputVO);
	public TrainingCampScheduleVO getScheduleAndConfirmationCallsOfCallerToAgent(List<Long> userIds,String startDateString,String endDateString);
	public List<IdNameVO> getAllTrainingCampsInfoByDistrictIds(List<Long> districtIds);
	public List<IdNameVO> getProgrammesDetailsByCamps(List<Long> campIdsList);
	public List<IdNameVO> getScheduledDetailsByProgrammes(List<Long> programIds);
	public ResultStatus assignMembersToCallerForMemberConfirmation(final Long userId, final Long scheduleId, final Long membersCount,final Long callerId,final Long callPurposeId,final List<TrainingCampVO> locationTypeList);
	public ResultStatus assignMembersToCallerForBatchConfirmation(final Long userId, final boolean isOwnMembers , final Long scheduleId, final Long membersCount,final Long callerId,final Long callPurposeId,final Long batchId, final List<Long> areaList);
	public TrainingCampVO getCampusWiseDateWiseInterestedMembersDetails(String searchType,String startDateStr,String endDateStr);
	public TrainingMemberVO getAvailableMembersCountDetails(Long scheduleId,Long callerId);
	public ResultStatus updateCallBackCadreStatusForTraining(final TrainingCadreVO inputVO);
	
	public TrainingCampCallStatusVO getCallStatusCountByTrainingCampCallerId(Long trainingCampCallerId);
	public Long getAvailableCountForMemberConfirmation(Long scheduleId);
	public List<TraingCampCallerVO> getMembersCountByBatchStatus(Long campCallerId,String batchStatus);
	public List<IdNameVO> getUserIdsByType();
	public List<CallStatusVO> getTheMeetingLevelDetails(Long userId);
	public List<CallStatusVO> getMeetingTypes(Long locationLevel);
	//public List<CallStatusVO> getAllMeetings(Long meetingType,Long locationLevel,Long stateId,Long districtId,Long constituencyId,Long mandalTownDivisonId,Long villageWardId,String startDateString,String endDateString);
	public List<CallStatusVO> getAllMeetings(Long meetingType,Long locationLevel,List<Long> stateIds,List<Long> districtIds,List<Long> constituencyIds,List<Long> mandalTownDivisonIds,List<Long> villageWardIds,String startDateString,String endDateString);
	public CallBackCountVO getCallBackDayWiseDetails(Long campCallerId);
	public List<IdNameVO> getCallerAgentDistricts(Long userId);
	public List<IdNameVO> getCallerAgentConstituencies(Long userId,Long districtId);
	public List<IdNameVO> getCallerAgentMandals(Long userId,Long constituencyId);
	public List<IdNameVO> getCallerAgentVillages(Long userId,Long mandalId);
	public List<TraingCampCallerVO> getAgentCallDetailsByCampCallerId(Long campCallerId);
	
	public List<BasicVO> getAgentsByCampCallerAdminId(Long campCallerAdminId,boolean isAdmin);
	public List<TrainingCampScheduleVO> getCallsDetailsOfCallCenterAdmin(List<Long> userIds,String startDateString,String endDateString);
	public TrainingCampScheduleVO getUpComingBatchDetails(String startdateStr,String endDateStr);
	public MeetingVO getUserAccessLevelAndLocations(Long userId);
	public List<TraingCampCallerVO> getScheduleAvailableCallsCountLocationWiseInfo(Long campId,Long programId,Long scheduleId,String type);
	public PartyMeetingVO getPartyMeetingMinutesAtrDetails(Long partyMeeingId,String accesstype,String accessValue);
	public String saveFilePaths(Long partyMeetingId,String fileType, String documentType, String filePath, Long userId, String fileName);
	public List<CadreDetailsVO> getTdpCadreDetailsforASchedule(List<Long> schedulesList,Long batchId,Long enrollmentYearId);
	public TrainingCampVO getAdminCallersWiseOverView(Long userId,Long campId,Long programId,Long scheduleId,Long batchId,boolean isAdmin);
	public TrainingCampVO getCallerWiseOverView(List<Long> callerIdsList);
	public List<CallTrackingVO> getDocsOfPartyMeetingId(Long partyMeetingId, String docSourceType,String accessType,String accessValue);
	public CadreDetailsVO getDetailsForACadre(Long tdpCadreId,Long batchId,Long enrollmentYearId );
	public CadreDetailsVO getAllStatusForCadre();
	public CadreDetailsVO saveDetailsOfCadre(final Long tdpCadreId,final Long batchId,final List<String> achieveList,final List<SimpleVO> goalsList,final Long leaderShipLevelId,final Long communicationSkillsId,final Long leaderShipSkillsId,final Long healthId,final String comments,final Long userId,final String smartPhoneId,final String whatsappId,final String whatsappShareId,final String facebookId,final List<String> healthAttachments,final List<String> docs,final List<SimpleVO> feedBackCategories,final Long programId);
	
	public List<CadreDetailsVO> getSchedulesListByProgramAndCenter(Long programId, Long centerId,Long batchId,Long enrollmentYearId );
	public SimpleDetailsVO getProgramsByUser(Long userId);
	public SimpleDetailsVO getAllProgramsAndCamps();
	public List<IdNameVO> getCampsByProgramAndUser(Long campProgramId,Long userId);
	
	public List<IdNameVO> getAttendedCountForBatchesByLocation(String startDateString,String endDateString,Long stateId,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public SimpleVO getInvitedAttendedCadreCountByBatchIds(String startDateString,String endDateString,Long stateId,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public Map<String,TrainingCampVO> getCompletedRunningUpcomingBatchIds(String startDateString,String endDateString,Long stateId,String type,List<Long> enrollmentYearIds,List<Long> programYearIds);
	
	public CadreFeedbackVO  getattendedcountByFeedBacks(List<Long> programIds,Long campId,Long batchId,String fromdate,String todate,String callFrom,List<Long> enrollmentYrIds);
	public List<SimpleDetailsVO> getAttendedCountsByProgramOrCampOrBatch(List<Long> programIds,Long campId,Long batchId,String fromdate,String todate,String fromType,String callFrom,List<Long> enrollmentYrIds);
	public List<SimpleVO> getAttendedCountSummaryByBatch(Long campId,Long batchId,String fromdate,String todate,String callFrom,List<Long> enrollmentYrIds,List<Long> programYearIds);
	public SimpleVO getProgramSummary(Long programId,String fromdate,String todate);
	public SimpleVO getCampSummary(Long programId,Long campId,String fromDate,String toDate);
	public SimpleVO getProgCampBatchNames(Long programId,Long campId,Long batchId);
	public List<CadreVo> getDateWiseAttendedAndAbsentCandidates(Long batchId,Long enrollmentYearId);
	public List<IdNameVO> getBatchesForCentre(Long programId,Long campId);
	public TrainingMemberVO getMaxNumberForBatch(Long batchId,Long sttatusId,String callPurpose);
	public List<TrainingCampVO> getCallBackLaterMembersCount(Long campId, String startDateStr, String endDateStr);
	public List<SimpleVO> getStatusCountOfCadreForInvitationAndAttendance(Long cadreId);
	public SurveyTrainingsVO getAllRecordsOfCampProgramScheduleAndBatch(Long campId, Long programId, Long scheduleId, Long batchId);
	public SimpleVO getAttendedTrainingCampBatchDetailsOfCadre(Long programId,Long cadreId);
	public List<SimpleVO> getRemarkSOfCadreByCallPurpose(Long programId,Long cadreId);
	public List<SimpleVO> getDayWiseCountsForRunningBatches(String startDateString,String endDateString,Long stateId,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public SimpleVO getDayWiseAttendnenceForBatch(Long batchId,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<SimpleVO> getAttendenceForTrainers(String type,String searchType,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<CategoryFeedbackVO> getCategoryFeedBackAnswerForCadre(Long cadreId);
	public List<IdNameVO> getFeedbackCategoriesForTraining(Long programId,Long campId,Long batchId);
	public List<FeedbackQuestionVO> getTrainingFeedBackQuestionsList(FeedbackInputVO inputVo,List<Long> categoryIds);
	public List<IdNameVO> getBatches(String type,Long programId,Long centerId);
	public String saveCadreFeedBackAnswers(Long tdpCadreId,List<SimpleVO> feedbackAnswers);
	public List<SimpleVO> getProgramCampBatchDetailsForAMemberBasedOnCadreId(List<Long> cadreIdList,String type);
	public List<TrainingCampVO> getFeedBackCountsOfTraining(String fromDateStr,String toDateStr,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<IdNameVO> getProgramsForFeedBack();
	public List<TrainingCampVO> getFeedbackCategoryCountsCenterWise(Long programId,String fromDate,String toDate);
	public List<TrainingCampVO> getFeedbackDetailsOfEachDistrictAndConstituencyWise(List<Long> districtIds,List<Long> constituencIds,List<Long> catrgoryIds,Long programId,String type,String fromDate,String toDate);
	public List<SimpleVO> getFeedbackDetailsOfCadre(Long locationId,Long programId,String type,String fromDate,String toDate,Long categoryId);
	public List<IdNameVO> getAllDistrictsByState(Long stateId);
	public List<IdNameVO> getAllConstituencysByDistrict(Long districtId);
	public List<IdNameVO> getAllCategories();
	public List<SimpleVO> getDaysAttendedCadreDetails(Long batchId,String dayType,String type,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<SimpleVO> getAllTrainingCampDetails(List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<SimpleVO> getVillagesForDistrictIdDetails(List<Long> districtId);
	public List<SimpleVO> getMandalsForDistrictIdDetails(List<Long> list);
	public List<CallStatusVO> getMeetingTypesNew(List<Long> locationLevels);
	public List<CallStatusVO> getFinalAllMeetings(Long meetingType,Long locationLevel,List<Long> stateIds,List<Long> districtIds,List<Long> constituencyIds,
			List<Long> mandalTownDivisonIds,List<Long> villageWardIds,String startDateString,String endDateString,String status);
	public String saveFinalizedMeetingDetails(final Long partyMeetingId,final String memberType,final String membershipId,final String name,
			final String mobileNo,final String remark,final String statusId,final String updateBy,final Long userId,final List<String> fileNames);
	public List<CallStatusVO> getCommentsMeetingDetails(Long partyMeetingId);
	public Map<String,TrainingCampVO> getAllTrainingProgWiseCompletedRunningUpcomingBatchIds(String endDateString,String startDateString,Long stateId,String type,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<PartyMeetingWSVO> getattendedcountByFeedBacksCounts(List<Long> programIds,Long campId,Long batchId,String fromdate,String todate,String callFrom,List<Long> enrollmentYrIds,String skillType,Long statusId);
	public List<KeyValueVO> getAllMomAtrClickDetails(Long meetingType,Long locationLevel,List<Long> stateIds,List<Long> districtIds,List<Long> constituencyIds,List<Long> mandalTownDivisonIds,List<Long> villageWardIds,String startDateString,String endDateString,String type,String accessType,String accessValue);
	public List<VerifierVO> getTrainingSurveyDetails(Long trainingProgramId,Long trainignBatchId,Long trainingCampId);
	public String getMinAndMaxDatesOfTraingCamp();
	
	public List<KeyValueVO> getTrainingCampDetailsByCampIds(List<Long> trainingCampIdsList);
	public List<KeyValueVO> getTrainingProgramDetailsByProgramIds(List<Long> triningProgramIdsList);
	public List<TrainingCampSheduleDetailsVO> getInviteeAndNonInviteeTrainingCampWiseDetails(String fromDateStr,String toDateStr,List<Long> enrollmentYearIds,List<Long> programmIds);

	public List<TrainingCampFeedBackDetailsVO> getTrainingCampFeedBAckDeatilesByTdpCadreId(Long tdpCadreId);
}
