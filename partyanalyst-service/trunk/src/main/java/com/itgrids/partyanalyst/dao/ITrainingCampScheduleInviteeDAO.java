package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampScheduleInvitee;

public interface ITrainingCampScheduleInviteeDAO extends GenericDao<TrainingCampScheduleInvitee, Long>{
	public List<Object[]> getCampusWiseScheduleWiseMembersDetails(String searchType, Date startDate, Date endDate,Date today);
	public List<Long> getInvitedCandidatesListByScheduleIdAndCount(List<Long> callerIdsList , Long scheduleId,List<Long> alreadyInvitedMemberIdsList,int membersCount,Long batchId);
	public List<Object[]> getTrainingProgramMembersBatchCount(Date startDate,Date endDate,String status,String type);
	//public List<Object[]> getBatchWiseProgramWiseInterestedMembersDetails(String membersType, String searchType, Date startDate, Date endDate);
	public List<Object[]> getCampusWiseBatchWiseMembersDetails(String searchType, Date startDate, Date endDate);
	public List getTrainingCampScheduleInviteeId(Long tdpCadreId,Long programId,Long campId,Long scheduleId);
	public Long getAvailableInvitedCandidatesListByScheduleIdAndCount(Long scheduleId);
	public Long getAreadyAssignedCandidatesListByScheduleIdAndCount(Long scheduleId);
	public List<Object[]> getBatchWiseConformationDetails(String searchType, Date startDate, Date endDate,Date todayDate);
	public List<Long> getUpcomingBatchConfirmation(Date fromDate,Date toDate,String type,Date todayDate);
	public List<Object[]> getScheduleAvailableCallsCountLocationWiseInfo(Long campId,Long programId,Long scheduleId,Long scheduleStatusId,List<Long> inviteeIdsList);
	public List<Long> getScheduleWiseInviteesListByLocationIdLocationType(Long scheduleId,Long locationTypeId,List<Long> locationIdsList);
	public List<Object[]> getAvailableCallCountsForBatch(Long campId,Long programId,Long scheduleId,Long scheduleStatusId,Long batchId);
	public Long getAllCallersCount(Date startDate,Date endDate,String type);
	public Long getBatchMembersCountByStatus(Long batchId,Long statusId,String callPurpose);
	public List<Object[]> getScheduleAvailableCallsCountParliamentWiseInfo(Long campId,Long programId,Long scheduleId,Long scheduleStatusId,List<Long> inviteeIdsList);
	public List<Object[]> getCallBackLaterMembersCount(Long campId, Date startDate, Date endDate);
	public List<Object[]> getInviteeCountOfCadreProgramWise(Long cadreId);
	public List<Object[]> getLatestRemarkOfCandidateOfProgram(Long programId,Long cadreId);
	
}
