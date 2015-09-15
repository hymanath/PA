package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.TrainingCampAttendance;

public interface ITrainingCampAttendanceDAO extends GenericDao<TrainingCampAttendance,Long>{
	
	public List<Object[]>	 getAttendedCountForBatchesByLocation(List<Long> batchIds);
	
	public List<Object[]> getAttendedCadreCountByBatchIds(List<Long> batchIds,String type);
	public List<Object[]> getInvitedCadreCountByBatchIds(List<Long> batchIds,String type);
	public List<Object[]> getCompletedCounts(List<Long> batchIds);
	
	public List<Object[]> getAttendedlocWiseCountsByProgramOrCampOrBatch(String queryString,Long programId,Long campId,Long batchId,Date fromDate,Date toDate);
	public Long getAttendedCountByBatch(Long batchId,Date fromDate,Date todate);
	public List<Object[]> getDateWiseCountsByBatch(Long batchId,Date fromDate,Date toDate);
	public List<Object[]> getCampWiseAttendedCountByProgram(Long programId,Date fromDate,Date toDate);
	public Long getAttendedCountByCamp(Long programId,Long campId,Date fromDate,Date toDate);
	public List<Object[]> getDateWiseAttendedAndAbsentCandidates(Long batchId);
	public List<Object[]> getInviteeCountsinAttendedCounts(List<Long> batchIds,String type);
	public List<Object[]> getAttendedCountOfCadreProgramWise(Long cadreId);
	public List<Object[]> getAttendedTrainingCampBatchDetailsOfCadre(Long programId,Long cadreId);
}
