package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
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
	public TrainingCampScheduleVO getCallerWiseCallsDetails(List<Long> callerIdsList,String searchTypeId,String startDate,String endDate);
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
	public ResultStatus assignMembersToCallerForMemberConfirmation(final Long userId, final Long scheduleId, final Long membersCount,final Long callerId,final Long callPurposeId);
	public ResultStatus assignMembersToCallerForBatchConfirmation(final Long userId, final boolean isOwnMembers , final Long scheduleId, final Long membersCount,final Long callerId,final Long callPurposeId);
	public TrainingCampVO getCampusWiseDateWiseInterestedMembersDetails(String searchType,String startDateStr,String endDateStr);
	public TrainingMemberVO getAvailableMembersCountDetails(Long scheduleId,Long callerId);
	public ResultStatus updateCallBackCadreStatusForTraining(final TrainingCadreVO inputVO);
	
	public TrainingCampCallStatusVO getCallStatusCountByTrainingCampCallerId(Long trainingCampCallerId);
	public Long getAvailableCountForMemberConfirmation(Long scheduleId);
	public List<TraingCampCallerVO> getMembersCountByBatchStatus(Long campCallerId,String batchStatus);
	public List<IdNameVO> getUserIdsByType();
	
}
