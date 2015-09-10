package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreDetailsVO;
import com.itgrids.partyanalyst.dto.CadreFeedbackVO;
import com.itgrids.partyanalyst.dto.CallBackCountVO;
import com.itgrids.partyanalyst.dto.CallStatusVO;
import com.itgrids.partyanalyst.dto.CallTrackingVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SimpleVO;
import com.itgrids.partyanalyst.dto.TraingCampCallerVO;
import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.dto.TrainingCadreVO;
import com.itgrids.partyanalyst.dto.TrainingCampCallStatusVO;
import com.itgrids.partyanalyst.dto.TrainingCampScheduleVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.TrainingMemberVO;

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
	public PartyMeetingVO getPartyMeetingMinutesAtrDetails(Long partyMeeingId);
	public String saveFilePaths(Long partyMeetingId,String fileType, String documentType, String filePath, Long userId, String fileName);
	public List<CadreDetailsVO> getTdpCadreDetailsforASchedule(List<Long> schedulesList);
	public TrainingCampVO getAdminCallersWiseOverView(Long userId,Long campId,Long programId,Long scheduleId,Long batchId,boolean isAdmin);
	public TrainingCampVO getCallerWiseOverView(List<Long> callerIdsList);
	public List<CallTrackingVO> getDocsOfPartyMeetingId(Long partyMeetingId, String docSourceType);
	public CadreDetailsVO getDetailsForACadre(Long tdpCadreId,Long batchId);
	public CadreDetailsVO getAllStatusForCadre();
	public CadreDetailsVO saveDetailsOfCadre(final Long tdpCadreId,final Long batchId,final List<String> achieveList,final List<SimpleVO> goalsList,final Long leaderShipLevelId,final Long communicationSkillsId,final Long leaderShipSkillsId,final Long healthId,final String comments,final Long userId,final String smartPhoneId,final String whatsappId,final String whatsappShareId,final String facebookId);
	
	public List<CadreDetailsVO> getSchedulesListByProgramAndCenter(Long programId, Long centerId);
	public SimpleVO getProgramsByUser(Long userId);
	public SimpleVO getAllProgramsAndCamps();
	public List<IdNameVO> getCampsByProgramAndUser(Long campProgramId,Long userId);
	
	public List<IdNameVO> getAttendedCountForBatchesByLocation(String startDateString,String endDateString,Long stateId);
	public SimpleVO getInvitedAttendedCadreCountByBatchIds(String startDateString,String endDateString,Long stateId);
	public Map<String,TrainingCampVO> getCompletedRunningUpcomingBatchIds(String startDateString,String endDateString,Long stateId,String type);
	
	public CadreFeedbackVO  getattendedcountByFeedBacks(Long programId,Long campId,Long batchId,String fromdate,String todate);
	public List<SimpleVO> getAttendedCountsByProgramOrCampOrBatch(Long programId,Long campId,Long batchId,String fromdate,String todate);
	public SimpleVO getAttendedCountSummaryByBatch(Long batchId,String fromdate,String todate);
	public SimpleVO getProgramSummary(Long programId,String fromdate,String todate);
	public SimpleVO getCampSummary(Long programId,Long campId,String fromDate,String toDate);
	public SimpleVO getProgCampBatchNames(Long programId,Long campId,Long batchId);
}
