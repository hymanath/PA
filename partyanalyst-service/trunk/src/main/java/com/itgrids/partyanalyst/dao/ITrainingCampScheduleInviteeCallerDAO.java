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
	public List<Object[]> getCallerWiseAssignedCalls(List<Long> userIds,Date startDate,Date endDate,String type,String agetType);
	public List<Object[]> getCallStatusContsOfInvitees(List<Long> userIds,Date startDate,Date endDate,String agentType);
	public List<Object[]> getScheduleWisememberDetailsCount(TraingCampDataVO inputVo,List<Long> statusIds,String statusType,String status,Date toDayDate,Integer startIndex,Integer maxIndex);
	public Long getAllCallersCount(Date startDate,Date endDate,String type);
	public List<Object[]> getScheduleAndConfirmationCallsOfCallerToAgent(List<Long> userIds,Date startDate,Date endDate,String type);
	public List<Object[]> getStatusWiseCount(List<Long> userIds,Date startDate,Date endDate,String searchType);
	public List<Long> getInterestedAndInvitedMembersListForBatchConfirmation(List<Long> callerIdsList,Long scheduleId,Long batchId, String callPurposeStr,String memberTypeStr);
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
	public List<Object[]> getScheduleWisememberDetailsCountForSearch(TraingCampDataVO inputVo,List<Long> statusIds,String statusType,String status,Date toDayDate,Integer startIndex,Integer maxIndex);
	
	public List<Object[]> getAgentCallDetailsByCampCallerId(Long campCallerId,Long callPurposeId,Date toDayDate,List<Long> batchStatusIdsList);
	
	public List<Object[]> getAllocatedCallsForBatchConfirmationDetails(String searchType, Date startDate, Date endDate,Date todayDate);
	
	public List<Object[]> getAgentsByCampCallerAdminId(Long campCallerAdminId);
	
	public Long getCallDetailsOfCaller(List<Long> userIds,Date startDate,Date endDate,String type,String agentType);
	public List<Object[]> getCallDetailsOfCallerByStatus(List<Long> userIds,Date startDate,Date endDate,String agentType);
	public List<Long> getAllUpcomingTrainingCampScheduleDetails(List<Long> scheduleIds,Date fromDate,Date toDate,String type,Date todayDate);
	public List<Long> getAllocatedCountForConfirmation(Date fromDate,Date toDate,String type,Long callPurpose,Date todayDate);
	public List<Object[]> getScheduleWiseDetailsCount(List<Long> callerIdsList,Date fromDate,Date toDate,String dataType,String searchType,Date todayDate);
	public List<Object[]> getCallerWiseAssignedCount(String searchType,List<Long> callerIdsList);
	public List<Object[]> getCallStatusWiseCountDetailsForCallers(String searchType,List<Long> callerIdsList);
	public List<Object[]> getdialedCallsForBatchConfirmationDetails(String searchType, Date startDate, Date endDate,Date todayDate);
	public List<Object[]> getTrainingProgramMembersBatchCount(Date startDate,Date endDate,String status,String type);
	public List<Long> getUpcomingBatchConfirmation(Date fromDate,Date toDate,String type,Date todayDate);
	public List<Long> getAssignedInviteesIdsList();
	public List<Object[]> getScheduleConfirmationDetails(Long purposeId,Long userId);
	public List getScheduleWisememberDetailsTotalCount(TraingCampDataVO inputVo,List<Long> statusIds,String statusType,String status,Date toDayDate);
	public List getScheduleWisememberDetailsCountForSearchCount(TraingCampDataVO inputVo,List<Long> statusIds,String statusType,String status,Date toDayDate);
	public List<Object[]> getScheduleWiseCallStatulsDetails(String searchType, Date startDate, Date endDate,Date todayDate,Long callPurposeId);
	public List<Object[]> getBatchWiseCallStatulsDetails(Long callPurposeId);
	public List<Object[]> getCallStatusCountByCallStatus(List<Long> userIds,Date startDate,Date endDate,String agentType);
}
