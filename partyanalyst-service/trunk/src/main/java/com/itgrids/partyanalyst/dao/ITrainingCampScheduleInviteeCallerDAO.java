package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeCaller;

public interface ITrainingCampScheduleInviteeCallerDAO extends GenericDao<TrainingCampScheduleInviteeCaller, Long>{
	public List<Long> getAlreadyInvitedMembersInviteeIdsListByScheduleId(Long scheduleId,Long assignId);
	public List<Object[]> getScheduleWiseCallStatusCount(Long callerId,Long callPurposeId);
	public List<Object[]> getBatchWiseWiseCallStatusCount(Long callerId,Long callPurposeId);
	public List<Object[]> getCallerWiseAssignedCalls(List<Long> userIds,Date startDate,Date endDate,String type);
	public List<Object[]> getCallStatusContsOfInvitees(List<Long> userIds,Date startDate,Date endDate);
	public List<Object[]> getScheduleWisememberDetailsCount(TraingCampDataVO inputVo,List<Long> statusIds,String statusType,String status,Date toDayDate);
	public Long getAllCallersCount(Date startDate,Date endDate,String type);
	public List<Object[]> getScheduleAndConfirmationCallsOfCallerToAgent(List<Long> userIds,Date startDate,Date endDate,String type);
	public List<Object[]> getStatusWiseCount(List<Long> userIds,Date startDate,Date endDate,String searchType);
	public List<Long> getInterestedAndInvitedMembersListForBatchConfirmation(Long callerId,Long scheduleId,String callPurposeStr,String memberTypeStr);
	public List<Object[]> getBatchConfirmedMemberDetails(List<Long> userIds,Date startDate,Date endDate,String searchType,String purpose);
	
	public List<Object[]> getCallStatusCountByTrainingCampCallerId(Long trainingCampCallerId);
	
	public List<Object[]> getInterestedMembersCountByCampCallerId(Long trainingCampCallerId);
	
	public List<Object[]> getMembersCountByBatchStatusAndCallerId(Long callerId,String batchStatus);
	
	public List<Object[]> getScheduleConfirmationCallBackDetails(Long campCallerId,Long callPurposeId,Date todayDate,List<Long> scheduleInviteeStatusIdsList);
	
	public List<Object[]> getBatchConfirmationCallBackDetails(Long campCallerId,Long callPurposeId,Date todayDate,List<Long> scheduleInviteeStatusIdsList);
	public List<Object[]> getScheduleWiseDayWiseCallBackCount(Long callerId,Long callPurposeId,Date date);
	public List<Object[]> getBatchWiseDayWiseCallBackCount(Long callerId,Long callPurposeId,Date date);
	
	public List<Object[]> getSchduleBatchConfirmationCallBackDetails(Long campCallerId,Long callPurposeId,Date todayDate,List<Long> scheduleInviteeStatusIdsList,List<Long> batchStatusIdsList);
	public List<Object[]> getCallerDistricts(Long userId);
	public List<Object[]> getCallerConstituenciesByDistrict(Long userId,Long districtId);
	public List<Object[]> getCallerAgentMandalsByConstituency(Long userId,Long constituencyId);
	public List<Object[]> getCallerAgentVillagesByMandal(Long userId,Long mandalId);
	public List<Object[]> getScheduleWisememberDetailsCountForSearch(TraingCampDataVO inputVo,List<Long> statusIds,String statusType,String status,Date toDayDate);
	
}
