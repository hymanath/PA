package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampBatch;

public interface ITrainingCampBatchDAO extends GenericDao<TrainingCampBatch,Long>{
	
	public List<Object[]> getBatchesForSchedule(Long scheduleId);
	
	public List<Object[]> getAllBatchesForSchedules(List<Long> scheduleIds);
	public List<Object[]> getBatchsInfoByProgramAndCamp(List<String> datesList,List<Long> campIdsList);
	public List<Object[]> getTrainingCampBatchesOfSchedule(Long trainingCampScheduleId);
	public List<Object[]> getCompletedBatchIds(Date currDate,String type,List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public Long getMaxNumbersForBacth(Long batchId);
	public List<Object[]> getCampDistrictsByBatchId(Long batchId,List<Long> enrollmentYrIds,List<Long> programIds);
	public Object[] getBatchDates(Long batchId,Date fromDate,Date toDate,List<Long> programYearIds,List<Long> enrollmentYearIds);
	public List<Object[]> getCentersAndBatchCountByProgram(Long programId,Date fromDate,Date toDate);
	public Object[] getBatchCountByCamp(Long programId,Long campId,Date fromDate,Date toDate);
	public List<Long> getBatchIds(Date startDate,Date endDate,Long stateId,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public Object[] getBatchDatesWithOutDates(Long batchId,Long enrollmentYearId);
	public List<Object[]> getBatchesInfoByProgramAndCamp(Long programId,Long campId);
	public List<TrainingCampBatch> getAllRecordsByBatchId(Long batchId);
	public List<Object[]> getCampConstsByBatchId(Long batchId,List<Long> enrollmentYrIds,List<Long> programIds);
	public List<Object[]> getBatcheIdsForACampOrProgram(List<Long> programId,Long campId,Date fromDate,Date toDate,String type,List<Long> enrollmentYrIds);
	public List<Object[]> getBatchAndCampNameForABatch(Long batchId,List<Long> enrollmentYrIds,List<Long> programIds);
	//public List<Object[]> getAllBatchesForTrainers();
	public List<Object[]> getAllCentersForTrainers();
	public List<Object[]> getBatches(String type,Long programId,Long campId);
	public List<String> getBatchNameWithDateAndCamp(Date date,Long campId,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<String> getExcudingTdpCadreIdsList(List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getBatchInviteeDetails(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds);
	public List<Object[]> getFromAndToDate(Long programId);
	public List<Long> getRunningBatchIds(Date todayDate);
	public List<Object[]> getTraingCampBatchDetaisByDatesAndProgramIdsAndEnroleMentIds(Date fromDate,Date toDate,List<Long> enrollmentYearIds,List<Long> programYearIds,String type);
	public List<Object[]> getMinAndMaxDatesOfTraingCamp();
	public List<Object[]> getProgramIdsAndNameBasedOnBatchList(List<Long> batchIdsList);
	public List<Object[]> getProgramIdAndBatchIdListByPassingEnrollmentYearId(Long yearId);
}
