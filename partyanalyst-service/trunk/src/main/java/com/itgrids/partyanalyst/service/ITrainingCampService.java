package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreDetailsVO;
import com.itgrids.partyanalyst.dto.CallBackCountVO;
import com.itgrids.partyanalyst.dto.CallStatusVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
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
	public List<Long> getTrainingCampUserTypeIds();
	public TrainingMemberVO getScheduleCallMemberDetails(TraingCampDataVO inputVo);
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
	public List<CallStatusVO> getMeetingTypes();
	//public List<CallStatusVO> getAllMeetings(Long meetingType,Long locationLevel,Long stateId,Long districtId,Long constituencyId,Long mandalTownDivisonId,Long villageWardId,String startDateString,String endDateString);
	public List<CallStatusVO> getAllMeetings(Long meetingType,Long locationLevel,List<Long> stateIds,List<Long> districtIds,List<Long> constituencyIds,List<Long> mandalTownDivisonIds,List<Long> villageWardIds,String startDateString,String endDateString);
	public CallBackCountVO getCallBackDayWiseDetails(Long campCallerId);
	public List<IdNameVO> getCallerAgentDistricts(Long userId);
	public List<IdNameVO> getCallerAgentConstituencies(Long userId,Long districtId);
	public List<IdNameVO> getCallerAgentMandals(Long userId,Long constituencyId);
	public List<IdNameVO> getCallerAgentVillages(Long userId,Long mandalId);
	public List<TraingCampCallerVO> getAgentCallDetailsByCampCallerId(Long campCallerId);
	
	public List<BasicVO> getAgentsByCampCallerAdminId(Long campCallerAdminId);
	public List<TrainingCampScheduleVO> getCallsDetailsOfCallCenterAdmin(List<Long> userIds,String startDateString,String endDateString);
	public TrainingCampScheduleVO getUpComingBatchDetails(String startdateStr,String endDateStr);
	public MeetingVO getUserAccessLevelAndLocations(Long userId);
	public List<TraingCampCallerVO> getScheduleAvailableCallsCountLocationWiseInfo(Long campId,Long programId,Long scheduleId);
	public PartyMeetingVO getPartyMeetingMinutesAtrDetails(Long partyMeeingId);
	
	public List<CadreDetailsVO> getTdpCadreDetailsforASchedule(Long scheduleId);
}
