package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampAttendance;

public interface ITrainingCampAttendanceDAO extends GenericDao<TrainingCampAttendance,Long>{
	
	public List<Object[]>	 getAttendedCountForBatchesByLocation(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds);
	
	public List<Object[]> getAttendedCadreCountByBatchIds(List<Long> batchIds,String type,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getInvitedCadreCountByBatchIds(List<Long> batchIds,String type,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getCompletedCounts(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds);
	
	public List<Object[]> getAttendedlocWiseCountsByProgramOrCampOrBatch(String queryString,List<Long> programId,Long campId,Long batchId,Date fromDate,Date toDate,Date currDate,String callFrom,List<Long> enrollmentYrIds);
	public Long getAttendedCountByBatch(Long batchId,Date fromDate,Date todate,List<Long> programYearIds,List<Long> enrollmentYearIds);
	public List<Object[]> getDateWiseCountsByBatch(Long batchId,Date fromDate,Date toDate);
	public List<Object[]> getCampWiseAttendedCountByProgram(Long programId,Date fromDate,Date toDate);
	public Long getAttendedCountByCamp(Long programId,Long campId,Date fromDate,Date toDate);
	public List<Object[]> getDateWiseAttendedAndAbsentCandidates(Long batchId,Long enrollmentYearId);
	public List<Object[]> getInviteeCountsinAttendedCounts(List<Long> batchIds,String type,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getAttendedCountOfCadreProgramWise(Long cadreId);
	public List<Object[]> getAttendedTrainingCampBatchDetailsOfCadre(Long programId,Long cadreId);
	public List<Long> getCompletedCountsForADay(Long batchId,Date date);
	public List<Object[]> getCompletedCountsForABatch(Long batchId,List<Date> dates,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getCompletedCountDetails(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds);
	//public List<Object[]> getInviteeAttendedCounts(List<Long> batchIds,Date fromDate,Date toDate);
	//public List<Object[]> getInviteeAttendedDetails(List<Long> batchIds,Date fromDate,Date toDate);
	public List<Object[]> getInviteeAttendedCountsForCenter(Long centerId,Long programId,Date fromDate,Date toDate,List<Long> cadreIdsLsit);
	public List<Long> getAttendedDetailsForCenter(Long centerId,Long programId,Date fromDate,Date toDate,List<Long> cadreIdsLsit);
	public List<Object[]> getSpeakersAttendedDetailsForCenter(Long centerId,Long programId,Date fromDate,Date toDate,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getSpeakersAttendedAreaDetailsForCenter(Long centerId,Long programId,Date fromDate,Date toDate,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getProgramCampBatchDetailsForAMemberBasedOnCadreId(List<Long> cadreIdList,Date fromDate,Date toDate);
	public List<Object[]> getTodaySpeakersAttendedDetails(Date toDayDate,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getDayWiseInviteeCountsForBatch(Long batchId,List<Long> programYearIds,List<Long> enrollmentYearIds);
	public List<Long> getInviteeCadreIdsForADay(Long batchId,Date date,List<Long> programYearIds,List<Long> enrollmentYearIds);
	public List<Long> getNonInviteesNoDaysCount(Long batchId,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getNonInviteesCadreBtBatch(Long batchId,Long enrollmentYearId);
	public List<Object[]> getInviteeAttendedCountsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getInviteeAttendedDetailsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Long> getInviteeCadreIds(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getAttendedNonInviteesCountForBatchesByLocation(List<Long> batchIds,List<Long> cadreIds,List<Long> enrollmentYearIds,List<Long> programYearIds);
	
	public List<Object[]> getAttendedCountsForBatches(List<Long> batchIds,List<Long> enrollmentYrIds,List<Long> programYearIds);
	public Long getNonInviteesAttendedCountByBatch(Long batchId,Date fromDate,Date toDate,String searchTypeStr,List<Long> staffCadreIdsList,List<Long> enrollmentYearIds);
	public Long getInviteesAttendedCountByBatch(Long batchId,Date fromDate,Date toDate,String searchTypeStr,List<Long> staffCadreIdsList,List<Long> enrollmentYearIds);
	public List<Object[]> getAttendedBatchDetailsByTdpCadreId(Long tdpCadreId,Long programId);
	public Long getAttendedCountByLocation(Long id,String searchType);
	public List<Long> getAttendedCadreIdsByLocation(Long id,String searchType);
	
	public List<Object[]> getTotalAttenedCadresByTrainingCampProgram(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date toDate);
	public List<Object[]> getTotalAttenedCadresByCommitteeLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date toDate);
	public List<Object[]> getTotalAttenedCadresOfTrainingCampProgramByDistrict(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date toDate, String status, Long distId);
	public List<Object[]> getUserWiseTotalAttenedCadresCntForTrainingProgram(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date toDate);
	public List<Object[]> getTotalAttenedCadresOfTrainingCampProgramByLocationType(Long userAccessLevelId,List<Long> userAccessLevelValues,String locationType,Long stateId,Date toDate,List<Long> enrollmentYearIds,List<Long> programIdList);
	public List<Object[]> getTotalAttendedForTrainingCampStateLevel(List<Long> programIdList, Long stateId, Date toDate, List<Date> dateList, String option,List<Long> enrollmentYrIds);
	public List<Object[]> getStateDistrictTrainingProgramAttendedDetails(Long campId, List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds);
	public List<Object[]> getMlaMpInchargeTrainingProgramAttendedDetails(Long campId, List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds);  
	public List<Object[]> getDestWiseAttendedMembers(List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds);   
	public List<Object[]> getAttendedMemberCadreId(Long distId,Long programId,List<Date> datesList);
	public List<Object[]> getMembersDetails(List<Long> attendedCadreIds);
	public List<Object[]> getAttendedMemberCadreOverview(Long distId,Long programId);
	public List<Object[]> getTotalAttenedCadresOfTrainingCampProgramByUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date toDate, String status, Long locationId,String locationType,Long userType,String levelType,List<Long> enrollmentYearIds,List<Long> trainingCampProgramIds);
	public Date getLastUpdatedTime();
	public List<Object[]> getStDistTrainingPrgAttendedDtlsCmtLvL(Long campId, List<Long> programIdList, Long stateId, Date toDate, List<Long> designationIdList);
	public List<Object[]> getMlaMpInchargeTrngPrgAttendedDtlsPubRep(Long campId, List<Long> programIdList, Long stateId, Date toDate, List<Long> designationIdList);
	public List<Object[]> getDestictWiseAttendedMembers(List<Long> programIdList, Long stateId, Date toDate);
	public List<Object[]> getDayWisePresent(List<Long> programIdList,Long stateId,List<String> dateList);
	public List<Object[]> getTrainingCampAttendanceSummary(List<Long> cadreIds);
	public List<Object[]> getBatchIds(List<Long> tdpCadreIdsList);
	public List<Object[]> getCampDetails(List<Long> batchIds);
	public Long getTotalAttendedCountByBatch(Long batchId,Date fromDate,Date toDate,String searchTypeStr,List<Long> staffCadreIdsList,List<Long> enrollmentYearIds);
	public List<Object[]> getInviteeAttendedCount(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programIds,List<Long> campIds ,List<Long> scheduleIds,List<Long> roleIds,Date startDate,Date endDate,Set<Long> staffCadreIds);
	public List<Object[]> getTotalAttendedCount(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programIds,List<Long> campIds ,List<Long> scheduleIds,Date startDate,Date endDate,Set<Long> staffCadreIds);
	public List<Object[]> getDayWiseTrainingCampDetailsCount(List<Long> enrollmentYearIds,List<Long> programIdsList,List<Long> batchIdsList);
	public List<Object[]> getAttendedCountForTrainingCamp(Long accessLevelValue, List<Long> userAccessLevelValues, Date fromDate, Date toDate, Long enrollmentYearId,List<Long> programId);
	public List<Object[]> getInviteAttendedCountForTrainingCamp(Long accessLevelValue, List<Long> userAccessLevelValues, Long enrollmentYearId,List<Long> programIdsList);
}
