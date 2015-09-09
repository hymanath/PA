package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.TrainingCampAttendance;

public interface ITrainingCampAttendanceDAO extends GenericDao<TrainingCampAttendance,Long>{
	
	public List<Object[]>	 getAttendedCountForBatchesByLocation(List<Long> batchIds);
	
	public List<Object[]> getAttendedCadreCountByBatchIds(List<Long> batchIds,String type);
	public List<Object[]> getInvitedCadreCountByBatchIds(List<Long> batchIds,String type);
	public List<Object[]> getCompletedCounts(List<Long> batchIds);
	
	public List<Object[]> getAttendedlocWiseCountsByProgramOrCampOrBatch(String queryString,Long programId,Long campId,Long batchId);
	 public Long getAttendedCountByBatch(Long batchId);
	 public List<Object[]> getDateWiseCountsByBatch(Long batchId);
}
