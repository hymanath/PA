package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampBatch;

public interface ITrainingCampBatchDAO extends GenericDao<TrainingCampBatch,Long>{
	
	public List<Object[]> getBatchesForSchedule(Long scheduleId);
	
	public List<Object[]> getAllBatchesForSchedules(List<Long> scheduleIds);
	
	public List<Object[]> getTrainingCampBatchesOfSchedule(Long trainingCampScheduleId);
	public List<Object[]> getCompletedBatchIds(Date currDate,String type,List<Long> batchIds);
	public Long getMaxNumbersForBacth(Long batchId);
	public List<Object[]> getCampDistrictsByBatchId(Long batchId);
	public Object[] getBatchDates(Long batchId);
	public List<Object[]> getCentersAndBatchCountByProgram(Long programId);
	public Object[] getBatchCountByCamp(Long programId,Long campId);
	public List<Long> getBatchIds(Date startDate,Date endDate,Long stateId);
}
