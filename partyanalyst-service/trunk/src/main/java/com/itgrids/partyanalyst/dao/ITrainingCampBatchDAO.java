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
	public List<Object[]> getCompletedBatchIds(Date currDate,String type,List<Long> batchIds);
	public Long getMaxNumbersForBacth(Long batchId);
	public List<Object[]> getCampDistrictsByBatchId(Long batchId);
	public Object[] getBatchDates(Long batchId,Date fromDate,Date toDate);
	public List<Object[]> getCentersAndBatchCountByProgram(Long programId,Date fromDate,Date toDate);
	public Object[] getBatchCountByCamp(Long programId,Long campId,Date fromDate,Date toDate);
	public List<Long> getBatchIds(Date startDate,Date endDate,Long stateId);
	public Object[] getBatchDatesWithOutDates(Long batchId);
	public List<Object[]> getBatchesInfoByProgramAndCamp(Long programId,Long campId);
	public List<TrainingCampBatch> getAllRecordsByBatchId(Long batchId);
	public List<Object[]> getCampConstsByBatchId(Long batchId);
	public List<Object[]> getBatcheIdsForACampOrProgram(Long programId,Long campId,Date fromDate,Date toDate,String type);
	public List<Object[]> getBatchAndCampNameForABatch(Long batchId);
	//public List<Object[]> getAllBatchesForTrainers();
	public List<Object[]> getAllCentersForTrainers();
	public List<Object[]> getBatches(String type,Long programId,Long campId);
	public List<String> getBatchNameWithDateAndCamp(Date date,Long campId);
	public List<String> getExcudingTdpCadreIdsList();
	public List<Object[]> getBatchInviteeDetails(List<Long> batchIds);
	public List<Object[]> getFromAndToDate(Long programId);
}
