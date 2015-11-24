package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampBatchAttendee;

public interface ITrainingCampBatchAttendeeDAO extends GenericDao<TrainingCampBatchAttendee, Long>{
	
	public List<Object[]> getBatchWiseProgramWiseAcceptedMemeberDetails(String searchType, Date startDate, Date endDate);
    
	public List<Object[]> getTdpCadreDetailsforASchedule(List<Long> schedulesList,Long batchId);
	public List<Object[]> getAchievementsForCadreBySchedule(List<Long> schedulesList,Long batchId);
	public List<Object[]> getGoalsForCadreBySchedule(List<Long> schedulesList,Long batchId);
	
	public Object[] getCadreDetailsByCadreIdAndBatchId(Long tdpCadreId,Long batchId);
	
	public List<String> getAttendeesForATrainingCampBatch(Long trainingCampBatchId);
	public List<Object[]> getRunningUpcomingCounts(List<Long> batchIds);
    
	public Long getConfirmedCountsByBatch(Long batchId,Date fromDate,Date toDate);
	public List<Object[]> getConfirmedCadreByBatch(Long batchId);
	public List<TrainingCampBatchAttendee> getAttendeeDetailsByInviteeId(Long inviteeId, Long batchId,Long scheduleId);
	public List<Long> getRunningUpcomingAttendeeCounts(Long batchId);
	public List<Object[]> getRunningUpcomingCountDetails(List<Long> batchIds);
	//public List<Object[]> getInvitedCounts(List<Long> batchIds);
	//public List<Object[]> getInvitedDetails(List<Long> batchIds);
	public List<Object[]> getInvitedCountsForCenter(Long centerId,Long programId,Date fromDate,Date toDate,List<Long> cadreIdsLsit);
	public List<Long> getInvitedDetailsForCenter(Long centerId,Long programId,Date statrtDate,Date endDate,List<Long> cadreIdsLsit);
	public List<Object[]> getSpeakersDetails(Date fromdate, Date toDate);
	public List<Object[]> getProgramCampBatchDetailsForAMemberBasedOnCadreId(List<Long> cadreIdList,Date fromDate,Date toDate);
}
