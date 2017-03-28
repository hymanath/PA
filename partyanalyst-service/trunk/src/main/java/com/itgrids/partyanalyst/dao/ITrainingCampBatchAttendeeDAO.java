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
    
	public Long getConfirmedCountsByBatch(Long batchId,Date fromDate,Date toDate,String searchTypeStr,List<Long> staffCadreIdsList);
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
	public List<Object[]> getInvitedCountsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList);
	public List<Object[]> getInvitedDetailsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList);
	public Long getTotalSpeakersCountDetails(List<Long> cadreIdList,Date fromDate,Date toDate);
	public List<Long> getTodaySpeakersDetails(Date todayDate);
	public Long getInvitedCountByLocation(Long id,String searchType);
	public List<Long> getInvitedCadreIdsByLocation(Long id,String searchType);
	public List<Object[]> getTotalInvitedForTrainingCampStateLevel(List<Long> programIdList, Long stateId, Date toDate);
	public List<Object[]> getStateDistrictTrainingProgramInvitedDetails(Long campId, List<Long> programIdList, Long stateId, Date toDate);  
	public List<Object[]> getMlaMpInchargeTrainingProgramInvitedDetails(Long campId, List<Long> programIdList, Long stateId, Date toDate);
	public List<Object[]> getDistWiseInvitedMembers(List<Long> programIdList, Long stateId, Date toDate);
    public List<Object[]> getInvitedMemberCadreId(Long distId, Long programId);
    public List<Object[]> getStDistTrainingPrgInvitedDtlsCmtLvL(Long campId, List<Long> programIdList, Long stateId, Date toDate, List<Long> designationIdList);
    public List<Object[]> getMlaMpInchargeTrngPrgInvitedDtlsPubRep(Long campId, List<Long> programIdList, Long stateId, Date toDate, List<Long> designationIdList);
    public List<Object[]> getTotalInvitedCadreIdForTrainingCampStateLevel(List<Long> programIdList, Long stateId, Date toDate);
    public List<Object[]> getTrainingCampInviteeSummary(List<Long> cadreIds);
}
